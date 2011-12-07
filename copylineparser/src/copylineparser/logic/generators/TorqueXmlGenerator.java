/*
 * Created on 26.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import copylineparser.logic.model.cobol.Field;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TorqueXmlGenerator extends SqlSourceGenerator
{

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#generateSource(copylineparser.logic.model.cobol.Field)
     */
    public String generateSource(Field field)
    {
        super.handle(field);

        this.buf = new StringBuffer();

        buf
                .append("\n<?xml version='1.0' encoding='ISO-8859-1' standalone='no'?>\n");
        buf.append(super.getTableDefinition().toStringXML());
        return buf.toString();
    }

}
