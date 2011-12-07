/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.NumericFieldType;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FileUtils
{
    static final String EBCDIC_CHARSET_NAME = "Cp1047";

    static final char[] EBCDIC_CHARSET = getEbcdicCharset();

    private static char[] getEbcdicCharset()
    {
        char[] charset = new char[256];
        char[] tmp;
        int i = 0;
        i = 0x40;
        charset[i] = ' ';

        i = 0x4B;
        charset[i] = '.';
        i++;
        charset[i] = '<';
        i++;
        charset[i] = '(';
        i++;
        charset[i] = '+';
        i++;
        charset[i] = '|';
        i++;
        charset[i] = '&';

        i = 0x5A;
        charset[i] = '!';
        i++;
        charset[i] = '$';
        i++;
        charset[i] = '*';
        i++;
        charset[i] = ')';
        i++;
        charset[i] = ';';
        i++;
        charset[i] = ' '; // Zeichen für logisches NOT
        i++;
        charset[i] = '-';
        i++;
        charset[i] = '/';
        i++;
        charset[i] = '§';
        i++;
        charset[i] = '[';
        i++;
        charset[i] = ']';

        i = 0x67;
        charset[i] = 'ß';

        i = 0x6A;
        charset[i] = '^';
        i++;
        charset[i] = ',';
        i++;
        charset[i] = '%';
        i++;
        charset[i] = '_';
        i++;
        charset[i] = '>';
        i++;
        charset[i] = '?';

        i = 0x7A;
        charset[i] = ':';
        i++;
        charset[i] = '#';
        i++;
        charset[i] = '@';
        i++;
        charset[i] = '\'';
        i++;
        charset[i] = '=';
        i++;
        charset[i] = '"';

        i = 0x81;
        tmp = "abcdefghi".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0x8B;
        tmp = "AÖÜ".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0x91;
        tmp = "jklmnopqr".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xA2;
        tmp = "stuvwxyz".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xAB;
        tmp = "äöü".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xC0;
        tmp = "{ABCDEFGHI".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xD0;
        tmp = "}JKLMNOPQR".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xE2;
        tmp = "STUVWXYZ".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xF0;
        tmp = "0123456789".toCharArray();
        for (char c : tmp)
        {
            charset[i++] = c;
        }

        i = 0xFF;
        charset[i] = '~';

        return charset;
    }

    public static String getFileContent(File file) throws IOException
    {
        int size = (int) file.length();
        int offset = 0;
        FileReader in = new FileReader(file);
        char data[] = new char[size];
        while (in.ready())
        {
            offset += in.read(data, offset, size - offset);
        }
        in.close();
        String content = new String(data, 0, offset);
        return content;
    }

    public static List<Copyline> readCopylineContent(File file, int lineLimit)
            throws IOException
    {
        List<Copyline> copylines = new ArrayList<Copyline>();
        LineNumberReader reader = new LineNumberReader(new FileReader(file));
        while (reader.ready() && reader.getLineNumber() < lineLimit)
        {
            copylines.add(new Copyline(reader.getLineNumber() + 1, reader
                    .readLine()));
        }
        return copylines;
    }

    public static List<Copyline> readCopylineContent(File file,
            int copylineLength, int lineLimit) throws IOException
    {
        List<Copyline> copylines = new ArrayList<Copyline>();
        int lineCount = 0;
        FileReader in = new FileReader(file);
        char data[] = new char[copylineLength];
        while (in.read(data) > 0)
        {
            copylines.add(new Copyline(++lineCount, new String(data)));
        }
        in.close();

        return copylines;
    }

    public static List<Copyline> readCopylineContent(File file,
            Field copylineRootField, int lineLimit) throws IOException
    {
        List<Copyline> copylines = new ArrayList<Copyline>();
        int lineCount = 0;

        // TODO: so einfach ist es nicht! arrays müssen aufgelöst werden!
        List<Field> elementaryFields = new ArrayList<Field>();
        fetchElementaryFieldsResolveArrays(copylineRootField, 1,
                elementaryFields);

        Collections.sort(elementaryFields, new Comparator<Field>()
        {
            public int compare(Field f1, Field f2)
            {
                int f1pos = f1.getStartPosition();
                int f2pos = f1.getStartPosition();
                if (f1pos < f2pos)
                    return -1;
                if (f1pos > f2pos)
                    return 1;
                // else
                return 0;
            }
        });

        int copylineLength = 0;
        int[] fieldSizes = new int[elementaryFields.size()];
        // initialisiert auf false!
        boolean[] isPacked = new boolean[elementaryFields.size()];
        int fieldIndex = 0;
        // Elementare Felder haben alle einen Typ!
        for (Field f : elementaryFields)
        {
            FieldType type = f.getType();

            if (type instanceof NumericFieldType)
            {
                NumericFieldType numType = (NumericFieldType) type;
                isPacked[fieldIndex] = numType.isPacked();
                if (numType.isPacked())
                {
                    // mit 10 multiplizieren um mit int zu rechnen, statt float
                    int packedLength = f.length() * 10;
                    // gepackt wird nur die Hälfte Platz benötigt
                    packedLength >>= 1; // durch 2
                    // Halb-Byte für Vorzeichen bzw. Endezeichen aufaddieren
                    packedLength += 5;
                    // wenn ungerade (nicht durch 2 teilbar)
                    // noch ein leeres Halb-Byte aufaddieren
                    if ((packedLength & 0x01) == 1)
                    {
                        // true == nicht durch 2 teilbar
                        // weil niedrigstes bit=1
                        packedLength += 5;
                    }
                    // rücknahme des 10er-Faktors
                    packedLength /= 10;
                    fieldSizes[fieldIndex] = packedLength;
                    copylineLength += packedLength;
                }
                else
                {
                    fieldSizes[fieldIndex] = f.length();
                    copylineLength += f.length();
                }
            }
            else
            {
                isPacked[fieldIndex] = false;
                fieldSizes[fieldIndex] = f.length();
                copylineLength += f.length();
            }
            fieldIndex++;
        }

        DataInputStream input = new DataInputStream(new FileInputStream(file));

        int ebcdicIndex = 0;
        int pos = 0;

        byte[] data = new byte[copylineLength];

        // skip 2 bytes == CRLF
        // input.skipBytes(2);
        while ((input.read(data) != -1) && (lineCount < lineLimit))
        {
            StringBuffer buf = new StringBuffer();
            pos = 0;

            for (int i = 0; i < fieldSizes.length; i++)
            {
                if (isPacked[i])
                {
                    // Teilbereich ausschneiden
                    int packedDataLen = fieldSizes[i];
                    byte[] packedData = new byte[packedDataLen];
                    System.arraycopy(data, pos, packedData, 0, packedDataLen);
                    buf.append(String.valueOf(unpack(packedData)));
                }
                else
                {
                    for (int dataIndex = pos; dataIndex < pos + fieldSizes[i]; dataIndex++)
                    {
                        ebcdicIndex = data[dataIndex] & 0xFF;
                        buf.append(EBCDIC_CHARSET[ebcdicIndex]);
                    }
                }
                pos += fieldSizes[i];
            }
            // System.out.println(buf.toString());
            copylines.add(new Copyline(++lineCount, buf.toString()));

            // skip 2 bytes == CRLF
            // input.skipBytes(2);
        }
        input.close();

        return copylines;
    }

    static int unpack(byte[] packedBytes)
    {
        int unpacked[] = new int[packedBytes.length * 2];
        int unpackedIndex = 0;

        int vorzeichen = 1;
        int high = 0;
        int low = 0;

        for (int i = 0; i < packedBytes.length; i++)
        {
            int packedByte = packedBytes[i] & 0xFF;

            // 1. Splitte Byte in High- und Low- Nibble
            high = packedByte >> 4;
            low = packedByte & 0x0F;

            if (high > 9)
            {
                break;
            }
            if (low > 9)
            {
                unpacked[unpackedIndex++] = high;
                if (low == 0x0D)
                {
                    vorzeichen = -1;
                }
                break;
            }
            unpacked[unpackedIndex++] = high;
            unpacked[unpackedIndex++] = low;
        }

        int unpackedValue = 0;
        int multiplier = 1;
        for (int i = unpackedIndex - 1; i >= 0; i--)
        {
            unpackedValue += unpacked[i] * multiplier;
            multiplier *= 10;
        }

        return unpackedValue; // * vorzeichen;
    }

    /*
     * sortiert aus: Filler, Redefinitionen, Gruppen, Switches liefert: alle
     * Nicht-Gruppen, aber Schalter (Switchgroups) Arrays werden aufgelöst in
     * Feld x Occurence
     */
    private static void fetchElementaryFieldsResolveArrays(Field field,
            int occurence, List<Field> storage)
    {
        if (field.isFiller() || field.isRedefinition() || field.isSwitch())
        {
            return;
        }
        if (field.isGroup() & !field.isSwitchGroup())
        {
            for (Field kid : field)
            {
                fetchElementaryFieldsResolveArrays(kid, occurence
                        * field.getOccurence(), storage);
            }
        }
        else
        {
            // Felder die ArrayElemente darstellen werden einfach mehrmals in
            // die Liste eingefügt
            for (int i = 0; i < occurence; i++)
            {
                storage.add(field);
            }
        }
    }

}
