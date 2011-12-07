/*
 * Created on 05.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import copylineparser.logic.model.cobol.AlphanumericFieldType;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldValue;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class SourceGenerator
{
    protected StringBuffer buf;

    public SourceGenerator()
    {
        this.buf = new StringBuffer();
    }
    
    public abstract String generateSource(Field field);

    protected abstract void handle(Field field);
    
    

    protected void handle(FieldType type, int fieldLength)
    {
        if (type instanceof AlphanumericFieldType)
        {
            this.handle((AlphanumericFieldType) type, fieldLength);
        }
        else if (type instanceof NumericFieldType)
        {
            this.handle((NumericFieldType) type, fieldLength);
        }
    }
    
    protected abstract void handle(AlphanumericFieldType type, int fieldLength);

    protected abstract void handle(NumericFieldType type, int fieldLength);
    
    
    protected void handle(FieldValue value, FieldType type)
    {
        if (value instanceof SimpleFieldValue)
        {
            this.handle((SimpleFieldValue) value, type);
        }
        else if (value instanceof FieldValueRange)
        {
            this.handle((FieldValueRange) value, type);
        }
    }

    protected abstract void handle(SimpleFieldValue value, FieldType type);

    protected abstract void handle(FieldValueRange range, FieldType type);
    
}
