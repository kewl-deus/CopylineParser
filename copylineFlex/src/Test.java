import java.io.FileReader;
import java.io.PrintStream;
import java.util.Date;

import java_cup.runtime.Symbol;

public class Test
{
	static final boolean do_debug_parse = true;
	
	static final String PATH = "C:\\Dokumente und Einstellungen\\010627\\Lokale Dateien\\eclipse\\copylineFlex\\testinput\\";

	public static void main(String[] args)
	{
		String filename = PATH + "fb4500dc.INL";
		testScanner(filename);
		testParser(filename);
	}

	public static void testScanner(String fileToScan)
	{
		try
		{
			PrintStream ps = new PrintStream(PATH + "scanresults.txt");
			ps.append("Start at: " + (new Date()).toString() + "\n");
			ps.append("Lexing [" + fileToScan + "]\n");
			CopylineScanner scanner = new CopylineScanner(new FileReader(
					fileToScan));

			Symbol s;
			do
			{
				s = scanner.next_token();
				ps.append("token: " + s + "\n");
			}
			while (s.sym != sym.EOF);

			ps.append("Scan finished.");
			System.out.println("Scan finished.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

	}

	public static void testParser(String filename)
	{

		try
		{
			System.out.println("Parsing [" + filename + "]");
			CopylineScanner s = new CopylineScanner(new FileReader(filename));
			parser p = new parser(s);

			Symbol parseTree;

			if (do_debug_parse)
				parseTree = p.debug_parse();
			else
				parseTree = p.parse();

			System.out.println("Parsing done.");
			
			FieldBuilder fb = FieldBuilder.getInstance();

		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
			System.exit(1);
		}
	}

}
