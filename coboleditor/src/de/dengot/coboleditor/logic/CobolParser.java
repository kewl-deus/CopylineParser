package de.dengot.coboleditor.logic;

import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import de.dengot.coboleditor.model.CobolDivision;
import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.model.CobolSection;
import de.dengot.coboleditor.ui.editor.CobolSourceViewerConfiguration;

public class CobolParser
{
	// die ersten 7 Zeichen sind zu ignorieren
	private final int COLUMN_PARSE_OFFSET = 7;

	private final String DIVISION = "DIVISION";

	private final String SECTION = "SECTION";

	private IDocument document;

	private int lineNo;

	private int lineCount;

	private CobolProgram currentProgram;

	private CobolDivision currentDivision;

	private CobolSection currentSection;
	
	static boolean initialised = false;	

	public CobolParser()
	{
	}

	public CobolProgram parse(String programName, IDocument doc)
	{
		try
		{
			this.document = doc;
			this.lineNo = 0;
			this.lineCount = this.document.getNumberOfLines();

			this.currentProgram = new CobolProgram(programName, 0, document.getLength());
			this.parseCobolProgram();
			initialised=true;
			return currentProgram;
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private void parseCobolProgram() throws BadLocationException
	{
		String prevline = "NOT";
		System.out.println("going to parse now...");
		while (lineNo < lineCount)
		{
			IRegion region = document.getLineInformation(lineNo);
			String text = document.get(region.getOffset(), region.getLength());

			if (text.length() > COLUMN_PARSE_OFFSET && text.charAt(COLUMN_PARSE_OFFSET - 1) != '*')
			{
				if (text.contains(DIVISION))
				{
					this.currentDivision = new CobolDivision(parseDivisionName(text), region.getOffset(), region.getLength());
					this.currentProgram.add(this.currentDivision);
				}
				if (text.contains(SECTION))
				{
					this.currentSection = new CobolSection(parseSectionName(text), region.getOffset(), region.getLength());
					this.currentDivision.addSection(this.currentSection);
				}
				//if ( text.contains("A010-INITIALISATION") ) { System.out.println(lineNo+":"+text+":prevline is:"+prevline);}
				if ( prevline.equals("EOS") )
				{
					if ( text.substring(8).trim().split(" ").length == 1 && 
						 text.substring(8).trim().contains(".") &&
						 ! Arrays.asList(CobolSourceViewerConfiguration.KEYWORDS).contains(text.substring(8).trim().replace(".", "")))
					{
						this.currentSection = new CobolSection(parseParaName(text), region.getOffset(), region.getLength());
						this.currentDivision.addSection(this.currentSection);
					}
				}
			}
			storeCodeLine(text);
			lineNo++;
			if ( text.length() >= 7 && ! text.substring(6,7).equals("*") && ! text.substring(6,7).equals("/") )
			{
			   //if ( text.substring(8).trim().contains(".") && text.substring(8).trim().split(" ").length == 1 )
			   if ( text.substring(7).trim().contains(".") )
			   {
			      prevline = "EOS";
			   }
			   else
			   {
				  prevline = "NOT";
			   }
			}
		}
		System.out.println("parsing done");		
	}

	private String parseDivisionName(String text)
	{
		return text.substring(COLUMN_PARSE_OFFSET, text.indexOf(DIVISION)).trim()+ " " + DIVISION;
	}

	private String parseSectionName(String text)
	{
		return text.substring(COLUMN_PARSE_OFFSET, text.indexOf(SECTION)).trim();
	}

	private String parseParaName(String text)
	{
		return text.substring(COLUMN_PARSE_OFFSET).trim();
	}
	
	private void storeCodeLine(String text)
	{
		String code = null;
		try
		{
			code = text.substring(COLUMN_PARSE_OFFSET);
			try
			{
				this.currentProgram.addCodeLine(code);
			}
			catch (NullPointerException e)
			{
			}

			try
			{
				this.currentDivision.addCodeLine(code);
			}
			catch (NullPointerException e)
			{
			}

			try
			{
				this.currentSection.addCodeLine(code);
			}
			catch (NullPointerException e)
			{
			}
		}
		catch (StringIndexOutOfBoundsException siob)
		{
		}

	}

}
