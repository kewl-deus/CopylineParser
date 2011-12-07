package copylineparser.logic;

import java.util.Hashtable;

import copylineparser.logic.exception.ParserException;
import copylineparser.logic.exception.StructureException;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldTypeFactory;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;
import copylineparser.logic.model.cobol.TypeDefinitionMissmatchException;
import copylineparser.logic.token.CardinalToken;
import copylineparser.logic.token.IdentifierToken;
import copylineparser.logic.token.Token;
import copylineparser.logic.token.ValueToken;

/**
 * @author 010627
 * 
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu
 * ändern: Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und
 * Kommentare
 */
public class Parser
{
    private ParserTokenStream toBeParsed;

    private Field currentField;

    private FieldTypeFactory typeFactory = new FieldTypeFactory();

    /*
     * Speichert alle geparsten Felder Achtung: Key=Feldname, d.h. FILLER werden
     * überschrieben!
     */
    private Hashtable<String, Field> allParsedFields = new Hashtable<String, Field>();

    public Parser(ParserTokenStream toBeParsed)
    {
        this.toBeParsed = toBeParsed;
        this.toBeParsed.start();
        this.toBeParsed.skip();
    }

    public Token currentToken()
    {
        return toBeParsed.getCurrent();
    }

    public int currentPosition()
    {
        return currentToken().getPosition();
    }

    private void skipToken()
    {
        toBeParsed.skip();
    }

    private void skipToGoOnToken()
    {
        while (!(currentToken().isDotSymbolToken() || currentToken()
                .isEndToken()))
        {
            skipToken();
        }
        skipToken();
    }

    public Field parse() throws StructureException
    {
        Field root = new Field(1, "COPYSTRECKE");
        this.currentField = root;
        while (!this.currentToken().isEndToken())
        {
            this.parseField();
            skipToGoOnToken();
        }
        return root;
    }

    private void parseField() throws StructureException
    {
        String commentary = parseCommentary();
        if (currentToken().isEndToken())
        {
            return;
        }

        int stepNumber = parseStepNumber();
        String name = parseFieldname();

        // create Field with Basic Info
        Field field = new Field(stepNumber, name, commentary);

        // check: Redefinition?
        Field redefinedField = parseRedefinition();
        field.setRedefinition(redefinedField);

        int occurence = 1;
        if (currentToken().isOccursSymbolToken())
        {
            occurence = parseOccurence();
        }
        field.setOccurence(occurence);

        // ACHTUNG: Rücksprung
        if (currentToken().isDotSymbolToken())
        {
            // Gruppenfeld ohne Type-Def.
            this.updateFieldStructure(field);
            // fertig
            return;
        }

        int len = 0;
        FieldType type = null;
        // awaiting field-definition
        if (currentToken().isPictureSymbolToken())
        {
            parsePictureSymbolToken();
            type = parseFieldType();
            len = parseFieldLength(type);
        }

        if (type instanceof NumericFieldType)
        {
            NumericFieldType nft = (NumericFieldType) type;

            if ((!nft.isSigned()) && (currentToken().length() == 1))
            {
                char possibleSignSymbol = currentToken().getContents()
                        .charAt(0);
                // if (nft.isSignSymbol(possibleSignSymbol))
                // wird in set-Methode geprüft
                nft.setSignSymbol(possibleSignSymbol);
            }

            try
            {
                parsePackedDecimalSymbolToken();
                nft.setPacked(true);
            }
            catch (ParserException e)
            {
                // darf schiefgehen, nur test ob gepackt
            }
            if (!nft.isPacked())
            {
                // zweite Möglichkeit testen
                try
                {
                    parseComp3SymbolToken();
                    nft.setPacked(true);
                }
                catch (ParserException e)
                {
                    // darf ebenfalls schiefgehen
                }
            }

        }

        // Occurs kann sowohl ohne als auch mit PIC-Klausel auftreten
        if (currentToken().isOccursSymbolToken())
        {
            occurence = parseOccurence();
        }

        field.setLength(len);
        field.setType(type);
        field.setOccurence(occurence);

        if (currentToken().isValueSymbolToken())
        {
            parseDefaultFieldValue(field);
        }

        this.updateFieldStructure(field);
    }

    private void updateFieldStructure(Field f) throws StructureException
    {
        this.allParsedFields.put(f.getName(), f);

        if (f.getStepNumber() <= 1)
        {
            throw new StructureException(
                    "Die Stufennummern von Copystreckenfeldern müssen grösser als 1 sein. Das Feld "
                            + f.getName()
                            + " hat jedoch die Stufennummer "
                            + f.getStepNumber() + ".", currentPosition());
        }

        while (f.getStepNumber() <= this.currentField.getStepNumber())
        {
            this.currentField = this.currentField.getParent();
        }
        this.currentField.add(f);
        f.setParent(this.currentField);
        this.currentField = f;
    }

    private Field parseRedefinition() throws ParserException
    {
        try
        {
            parseRedefinesSymbolToken();
        }
        catch (ParserException redefEx)
        {
            // keine Redefinition-Klausel vorhanden
            // Exception nicht werfen!
            return null;
        }

        // wenn hierhin gekommen: Redefinition-Klausel ist vorhanden!
        String redefinedName = parseFieldname();
        Field redefinedField = this.allParsedFields.get(redefinedName);
        return redefinedField;
    }

    private int parseFieldLengthInBrakets() throws ParserException
    {
        int len = 0;
        parseOpenBracketSymbolToken();
        if (currentToken().isCardinalToken())
        {
            String value = ((CardinalToken) currentToken()).getValue();
            skipToken();
            len = Integer.parseInt(value);
        }
        else
        {
            throw new ParserException("Fieldlength expected!",
                    currentPosition(), currentToken());
        }
        parseCloseBracketSymbolToken();
        return len;
    }

    private int parseFieldLengthWithoutBrackets(FieldType type, String typeToken)
            throws ParserException
    {
        try
        {
            return type.parseLength(typeToken);
        }
        catch (TypeDefinitionMissmatchException e)
        {
            throw new ParserException("Invalid Fieldtype definition! "
                    + e.getMessage(), currentPosition(), currentToken());
        }
    }

    private int parseFieldLength(FieldType type) throws ParserException
    {
        // currentToken ist noch immer TypeToken
        String typeToken = ((ValueToken) currentToken()).getValue()
                .toUpperCase();
        // [S99V9](2).
        // [S9](2)V99.
        // [S9](2)V9(2).
        // [S99V99].
        skipToken();

        int len = 0;

        if (currentToken().isOpenBracketSymbolToken())
        {
            // S99V9[(]2).
            // S9[(]2)V99.
            // S9[(]2)V9(2).
            len = parseFieldLengthInBrakets();
        }
        else
        {
            // S99V99[.] == fertig
            len = parseFieldLengthWithoutBrackets(type, typeToken);
            // postCommaLength hat bereits FieldTypeFactory gesetzt
            // hier muss frühzeitig zurückgesprungen werden,
            // sonst läuft man ungewollt! in die untere Verarbeitung von
            // isDotSymbolToken()
            return len;
        }

        if (type.isNumericType())
        {
            NumericFieldType numType = (NumericFieldType) type;
            int postCommaLen = 0;

            // S99V9(2)[.] == fertig
            if (currentToken().isDotSymbolToken())
            {
                // Kommazeichen in TypeDef vorhanden?
                for (char c : typeToken.toCharArray())
                {
                    if (numType.isCommaSymbol(c))
                    {
                        // Nachkomma-Länge stand in den Klammern
                        postCommaLen = len;
                        len += parseFieldLengthWithoutBrackets(type, typeToken);
                        len--; // die 9 von V9 abziehen
                        numType.setPostCommaLength(postCommaLen);
                        break;
                    }
                }
                return len;
            }

            // S9(2)[V99].
            // S9(2)[V9](2).
            if (currentToken().isValueToken())
            {
                String postCommaTypeToken = ((ValueToken) currentToken())
                        .getValue();
                skipToken();

                // S9(2)V99[.] == fertig
                if (currentToken().isDotSymbolToken())
                {
                    try
                    {
                        postCommaLen = numType
                                .parsePostCommaLength(postCommaTypeToken);
                    }
                    catch (TypeDefinitionMissmatchException e)
                    {
                        throw new ParserException(
                                "Invalid Fieldtype definition! "
                                        + e.getMessage(), currentPosition(),
                                currentToken());
                    }
                    // Vorkomma-Länge stand in Klammern
                    len += parseFieldLengthWithoutBrackets(type,
                            postCommaTypeToken);
                    /*
                     * parsePostCommaLength und parseFieldLengthWithoutBrackets
                     * sind nicht dasselbe: letzterer zählt das Komma mit falls
                     * es sich um ein gedrucktes (nicht-gedachtes) Zeichen hält,
                     * also ',' statt 'V'
                     */
                    numType.setPostCommaLength(postCommaLen);
                    return len;
                }

                // S9(2)V9[(]2).
                if (currentToken().isOpenBracketSymbolToken())
                {
                    postCommaLen = parseFieldLengthInBrakets();
                    // S9(2)V9(2)[.] == fertig
                    len += postCommaLen;
                    /*
                     * Länge ergibt sich aus 1.Klammer + 2.Klammer +
                     * Trennzeichen wenn z.B. statt 'V' ein ',' steht -> len+1
                     */
                    for (char c : postCommaTypeToken.toCharArray())
                    {
                        if (numType.isCommaSymbol(c)
                                & numType.isPrintedSymbol(c))
                        {
                            len++;
                        }
                    }
                    numType.setPostCommaLength(postCommaLen);
                    return len;
                }
            }

        }
        return len;
    }

    private FieldType parseFieldType() throws ParserException
    {
        if (currentToken().isValueToken())
        {
            String typeToken = ((ValueToken) currentToken()).getValue();
            // Token wird nur angeschaut, aber nicht geskippt!
            String upperCaseValue = typeToken.toUpperCase();
            FieldType type = null;

            try
            {
                type = typeFactory.createFieldType(upperCaseValue);
            }
            catch (TypeDefinitionMissmatchException e)
            {
                throw new ParserException("Invalid Fieldtype definition! "
                        + e.getMessage(), currentPosition(), currentToken());
            }

            return type;

        }
        else
        {
            throw new ParserException("Invalid Fieldtype definition!",
                    currentPosition(), currentToken());
        }

    }

    private String parseFieldname() throws ParserException
    {
        if (currentToken().isIdentifierToken())
        {
            String result = ((IdentifierToken) currentToken()).getValue();
            skipToken();
            return result;
        }
        else
        {
            throw new ParserException("Fieldname expected!", currentPosition(),
                    currentToken());
        }
    }

    private int parseStepNumber() throws ParserException
    {
        if (currentToken().isCardinalToken())
        {
            String result = ((CardinalToken) currentToken()).getValue();
            int stepNumber = Integer.parseInt(result);
            skipToken();
            return stepNumber;
        }
        else
        {
            throw new ParserException("Stepnumber for Field expected!",
                    currentPosition(), currentToken());
        }
    }

    private String parseCommentary()
    {
        StringBuffer buf = new StringBuffer();
        while (currentToken().isCommentToken())
        {
            buf.append(currentToken().getContents());
            buf.append("\n");
            skipToken();
        }
        return buf.toString();
    }

    private int parseOccurence() throws ParserException
    {
        parseOccursSymbolToken();
        if (currentToken().isCardinalToken())
        {
            String card = ((CardinalToken) currentToken()).getValue();
            int occurence = Integer.parseInt(card);
            skipToken();
            if (currentToken().isTimesSymbolToken())
            {
                skipToken();
            }
            return occurence;
        }
        else
        {
            throw new ParserException("Invalid Occurence definition!",
                    currentPosition(), currentToken());
        }
    }

    private void parseDefaultFieldValue(Field field) throws ParserException
    {
        parseValueSymbolToken();
        if (currentToken().isValueToken())
        {
            String s = ((ValueToken) currentToken()).getValue();
            skipToken();
            if (currentToken().isThroughSymbolToken())
            {
                SimpleFieldValue startVal = new SimpleFieldValue(s);
                parseThroughSymbolToken();
                if (currentToken().isValueToken())
                {
                    SimpleFieldValue endVal = new SimpleFieldValue(
                            ((ValueToken) currentToken()).getValue());
                    skipToken();
                    field
                            .addDefaultValue(new FieldValueRange(startVal,
                                    endVal));
                }
                else
                {
                    throw new ParserException(
                            "Endvalue of THROUGH expression expected!",
                            currentPosition(), currentToken());
                }
            }
            else if (currentToken().isCommaSymbolToken()
                    || currentToken().isConstantToken()
                    || currentToken().isCardinalToken())
            {
                field.addDefaultValue(new SimpleFieldValue(s));

                while (!currentToken().isDotSymbolToken())
                {
                    if (currentToken().isCardinalToken()
                            || currentToken().isConstantToken())
                    {
                        field.addDefaultValue(new SimpleFieldValue(
                                ((ValueToken) currentToken()).getValue()));
                        skipToken();
                    }
                    else if (currentToken().isCommaSymbolToken())
                    {
                        skipToken();
                    }
                    else
                    {
                        throw new ParserException("Value expected!",
                                currentPosition(), currentToken());
                    }
                }
            }
            else if (currentToken().isDotSymbolToken())
            {
                field.addDefaultValue(new SimpleFieldValue(s));
            }
        }
        else
        {
            throw new ParserException("Value expected!", currentPosition(),
                    currentToken());
        }
    }

    private String parseIdentifier() throws ParserException
    {
        if (currentToken().isIdentifierToken())
        {
            String result = ((IdentifierToken) currentToken()).getValue();
            skipToken();
            return result;
        }
        else
        {
            throw new ParserException("Identifier expected!",
                    currentPosition(), currentToken());
        }
    }

    private void parsePictureSymbolToken() throws ParserException
    {
        if (currentToken().isPictureSymbolToken())
            skipToken();
        else
            throw new ParserException("PIC or PICTURE expected!",
                    currentPosition(), currentToken());
    }

    private void parseDotSymbolToken() throws ParserException
    {
        if (currentToken().isDotSymbolToken())
            skipToken();
        else
            throw new ParserException("Dot expected!", currentPosition(),
                    currentToken());
    }

    private void parseOpenBracketSymbolToken() throws ParserException
    {
        if (currentToken().isOpenBracketSymbolToken())
            skipToken();
        else
            throw new ParserException("Opening Bracket expected!",
                    currentPosition(), currentToken());
    }

    private void parseCloseBracketSymbolToken() throws ParserException
    {
        if (currentToken().isCloseBracketSymbolToken())
            skipToken();
        else
            throw new ParserException("Closing Bracket expected!",
                    currentPosition(), currentToken());
    }

    private void parseOccursSymbolToken() throws ParserException
    {
        if (currentToken().isOccursSymbolToken())
            skipToken();
        else
            throw new ParserException("OCCURS expected!", currentPosition(),
                    currentToken());
    }

    private void parseValueSymbolToken() throws ParserException
    {
        if (currentToken().isValueSymbolToken())
            skipToken();
        else
            throw new ParserException("VALUE expected!", currentPosition(),
                    currentToken());
    }

    private void parseThroughSymbolToken() throws ParserException
    {
        if (currentToken().isThroughSymbolToken())
            skipToken();
        else
            throw new ParserException("THROUGH or THRU expected!",
                    currentPosition(), currentToken());
    }

    private void parsePackedDecimalSymbolToken() throws ParserException
    {
        if (currentToken().isPackedDecimalSymbolToken())
            skipToken();
        else
            throw new ParserException("PACKED-DECIMAL expected!",
                    currentPosition(), currentToken());
    }

    private void parseComp3SymbolToken() throws ParserException
    {
        if (currentToken().isComp3SymbolToken())
            skipToken();
        else
            throw new ParserException("COMP-3 expected!", currentPosition(),
                    currentToken());
    }

    private void parseBinarySymbolToken() throws ParserException
    {
        if (currentToken().isBinarySymbolToken())
            skipToken();
        else
            throw new ParserException("BINARY expected!", currentPosition(),
                    currentToken());
    }

    private void parseRedefinesSymbolToken() throws ParserException
    {
        if (currentToken().isRedefinesSymbolToken())
            skipToken();
        else
            throw new ParserException("REDEFINES expected!", currentPosition(),
                    currentToken());
    }
}
