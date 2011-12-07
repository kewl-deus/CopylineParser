import java.lang.reflect.Field;
import java.util.HashMap;

public class SymbolWithPosition extends java_cup.runtime.Symbol
{
    private int line;

    private int column;
    
    private HashMap<Integer, String> symMap;

    public SymbolWithPosition(int type, int line, int column)
    {
        this(type, line, column, -1, -1, null);
    }

    public SymbolWithPosition(int type, int line, int column, Object value)
    {
        this(type, line, column, -1, -1, value);
    }

    public SymbolWithPosition(int type, int line, int column, int left, int right,
            Object value)
    {
        super(type, left, right, value);
        this.line = line;
        this.column = column;
        this.initSymMap();
    }
    
    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }

    public String toString()
    {
        return "line " + line + ", column " + column + ", sym: " + getSymName(sym)
                + (value == null ? "" : (", value: '" + value + "'"));
    }
    
    private String getSymName(int symNo)
    {
    	return this.symMap.get(symNo);
    }
    
    private void initSymMap()
    {
    	this.symMap = new HashMap<Integer,String>();
    	try
		{
			Field[] syms = sym.class.getDeclaredFields();
			for (Field symConst : syms)
			{
				symMap.put(symConst.getInt(null), symConst.getName());
			}
		}
		catch (SecurityException e)
		{
			System.out.println("Unable to init SymMap");
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Unable to init SymMap");
		}
		catch (IllegalAccessException e)
		{
			System.out.println("Unable to init SymMap");
		}
    }
}
