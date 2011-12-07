/*
 * Created on 20.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.sql;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SqlForeignKeyConstraint
{
    private SqlTableDefinition sourceTable;

    private SqlTableDefinition targetTable;

    private SqlColumnDefinition sourceColumn;

    private SqlColumnDefinition targetColumn;

    /**
     * @param sourceTable
     * @param targetTable
     * @param sourceColumn
     * @param targetColumn
     */
    public SqlForeignKeyConstraint(SqlTableDefinition sourceTable,
            SqlColumnDefinition sourceColumn, SqlTableDefinition targetTable,
            SqlColumnDefinition targetColumn)
    {
        //TODO: Checke Integrität, am besten: registriere ForeignKey an TableDef
        this.sourceTable = sourceTable;
        this.targetTable = targetTable;
        this.sourceColumn = sourceColumn;
        this.targetColumn = targetColumn;
    }

    public SqlColumnDefinition getSourceColumn()
    {
        return sourceColumn;
    }

    public void setSourceColumn(SqlColumnDefinition sourceColumn)
    {
        this.sourceColumn = sourceColumn;
    }

    public SqlTableDefinition getSourceTable()
    {
        return sourceTable;
    }

    public void setSourceTable(SqlTableDefinition sourceTable)
    {
        this.sourceTable = sourceTable;
    }

    public SqlColumnDefinition getTargetColumn()
    {
        return targetColumn;
    }

    public void setTargetColumn(SqlColumnDefinition targetColumn)
    {
        this.targetColumn = targetColumn;
    }

    public SqlTableDefinition getTargetTable()
    {
        return targetTable;
    }

    public void setTargetTable(SqlTableDefinition targetTable)
    {
        this.targetTable = targetTable;
    }

    public String createConstraintName()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("FK_");
        buf.append(this.getSourceTable().getName());
        buf.append("_");
        buf.append(this.getSourceColumn().getName());
        return buf.toString();
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("FOREIGN KEY (");
        buf.append(this.getSourceColumn().getName());
        buf.append(") REFERENCES ");
        buf.append(this.getTargetTable().getName());
        buf.append("(");
        buf.append(this.getTargetColumn().getName());
        buf.append(")");
        return buf.toString();
    }

    public String toStringStandalone()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("ALTER TABLE ");
        buf.append(this.getSourceTable().getName());
        buf.append(" ADD CONSTRAINT ");
        buf.append(this.createConstraintName());
        buf.append(" ");
        buf.append(this.toString());
        return buf.toString();
    }
    
    public String toStringXML()
    {
        StringBuffer buf = new StringBuffer();
        /*
         * <foreign-key foreignTable="periode">
			<reference local="periode_id" foreign="id"/>
		</foreign-key>
         */
        buf.append("<foreign-key foreignTable='");
        buf.append(this.getTargetTable().getName());
        buf.append("'\n");
        buf.append("<reference local='");
        buf.append(this.getSourceColumn().getName());
        buf.append("' foreign='");
        buf.append(this.getTargetColumn().getName());
        buf.append("'/>\n");
        buf.append("</foreign-key>");
        return buf.toString();
    }
}
