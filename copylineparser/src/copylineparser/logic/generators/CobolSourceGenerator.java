/*
 * Created on 05.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import java.util.Iterator;

import copylineparser.logic.model.cobol.AlphanumericFieldType;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldValue;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SignPosition;
import copylineparser.logic.model.cobol.SimpleFieldValue;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CobolSourceGenerator extends SourceGenerator
{

    public static String getCobolRepresantation(FieldType type, int fieldLength)
    {
        if (type instanceof AlphanumericFieldType)
        {
            return getCobolRepresantation((AlphanumericFieldType) type,
                    fieldLength);
        }
        else if (type instanceof NumericFieldType)
        {
            return getCobolRepresantation((NumericFieldType) type, fieldLength);
        }
        else
        {
            return "";
        }
    }

    public static String getCobolRepresantation(AlphanumericFieldType type,
            int fieldLength)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("PIC ");
        buf.append(type.symbol());
        buf.append("(");
        buf.append(String.valueOf(fieldLength));
        buf.append(")");
        return buf.toString();
    }

    public static String getCobolRepresantation(NumericFieldType type,
            int fieldLength)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("PIC ");
        
        if (type.getSignPosition() == SignPosition.LEADING)
        {
            buf.append(type.getSignSymbol());
        }
                 
        buf.append(type.symbol());
        
        int preCommaLen = fieldLength - type.getPostCommaLength();
        
        if (type.isDecimal())
        {
            buf.append("(");
            buf.append(String.valueOf(preCommaLen));
            buf.append(")");
            
            buf.append(type.getCommaSymbol());
            buf.append(type.symbol());
            
            buf.append("(");
            buf.append(String.valueOf(type.getPostCommaLength()));
            buf.append(")");
        }
        else
        {
            buf.append("(");
            buf.append(String.valueOf(fieldLength));
            buf.append(")");
        }
        
        if (type.getSignPosition() == SignPosition.TAILING)
        {
            buf.append(type.getSignSymbol());
        }

        if (type.isPacked())
        {
            //man denke an das Leerzeichen vorweg!
            buf.append(" PACKED-DECIMAL");
        }

        return buf.toString();
    }
    
    
    public CobolSourceGenerator()
    {
        super();
    }
    
    public synchronized String generateSource(Field field)
    {
        this.handle(field);
        return this.buf.toString();
    }
    
    private void makeTabs(Field field)
    {
        while(field.hasParent())
        {
            buf.append("  ");
            field = field.getParent();
        }
    }

    protected void handle(Field field)
    {
        if (field.hasCommentary())
        {
            buf.append(field.getCommentary());
            buf.append("\n");
        }
        
        makeTabs(field);
        
        buf.append(String.valueOf(field.getStepNumber()));
        buf.append(" ");
        buf.append(field.getName());

        if (field.isRedefinition())
        {
            buf.append(" REDEFINES ");
            buf.append(field.getRedefinedField().getName());
        }

        if (field.getType() != null)
        {
            buf.append(" ");
            this.handle(field.getType(), field.length());
        }
        
        if (field.isArray())
        {
            buf.append(" OCCURS ");
            buf.append(String.valueOf(field.getOccurence()));
        }

        if (field.hasDefaultValue())
        {
            buf.append(" ");
            Iterator<FieldValue> valIt = field.defaultValues().iterator();
            while (valIt.hasNext())
            {
                FieldValue val = valIt.next();
                this.handle(val, field.getType());
                if (valIt.hasNext())
                {
                    buf.append(", ");
                }
            }
        }

        buf.append(".\n");

        if (field.isGroup())
        {
            for (Field f : field)
            {
                this.handle(f);
            }
        }
    }

    protected void handle(AlphanumericFieldType type, int fieldLength)
    {
        buf.append(getCobolRepresantation(type, fieldLength));
    }

    protected void handle(NumericFieldType type, int fieldLength)
    {
        buf.append(getCobolRepresantation(type, fieldLength));
    }

    protected void handle(SimpleFieldValue value, FieldType type)
    {
        try
        {
            if (type.isAlphanumericType())
            {
                buf.append("\"");
                buf.append(value.getContent());
                buf.append("\"");
            }
            else
            {
                buf.append(value.getContent());
            }
        }
        catch (NullPointerException e)
        {
            buf.append(value.getContent());
        }
    }

    protected void handle(FieldValueRange range, FieldType type)
    {
        buf.append(range.getStart().getContent());
        buf.append(" THROUGH ");
        buf.append(range.getEnd().getContent());
    }

}
