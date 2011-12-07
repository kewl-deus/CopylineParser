/*
 * Created on 22.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TextpositionFormatter
{
    private SortedSet<LinePosition> linePositions = new TreeSet<LinePosition>();

    public TextpositionFormatter()
    {
    }
    

    public void inititialize(String source)
    {
        this.linePositions.clear();
        this.getLineNumbers(source);
    }

    public LineColumn getLineAndColunm(int posInText)
    {
        Iterator<LinePosition> lineIt = this.linePositions.iterator();
        LinePosition lp = new LinePosition(0, 0);
        while (lineIt.hasNext() && (lp.startpos < posInText))
        {
            lp = lineIt.next();
        }

        return new LineColumn(lp.line - 1, lp.startpos - posInText);
    }

    private void getLineNumbers(String source)
    {
        // 1.Zeile eingenhändig einfügen
        linePositions.add(new LinePosition(1, 1));

        int lineCounter = 2;
        for (int i = 0; i < source.length(); i++)
        {
            if (source.charAt(i) == '\n')
            {
                linePositions.add(new LinePosition(lineCounter++, i + 1));
            }
        }
//        for (LinePosition lp : this.linePositions)
//        {
//            System.out.println(lp);
//        }
    }

    private class LinePosition implements Comparable<LinePosition>
    {
        int line;

        int startpos;

        public LinePosition(int line, int startpos)
        {
            this.line = line;
            this.startpos = startpos;
        }

        public int compareTo(LinePosition other)
        {
            if (this.line == other.line)
                return 0;
            if (this.line > other.line)
                return 1;
            else
                return -1;
        }

        public String toString()
        {
            return line + " starts @ " + startpos;
        }
    }

}
