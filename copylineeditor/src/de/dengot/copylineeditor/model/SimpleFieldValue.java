package de.dengot.copylineeditor.model;

/**
 * @author 010627
 * 
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu
 * ändern: Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und
 * Kommentare
 */
public class SimpleFieldValue extends FieldValue
{
    private String content;

    public SimpleFieldValue(String content)
    {
        this.content = content;
    }

    /**
     * @return Returns the content.
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content
     *            The content to set.
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    public boolean isRange()
    {
        return false;
    }

    public String toString()
    {
        return this.content;
    }

    public boolean equals(String content)
    {
        char[] chars = content.toCharArray();
        if (this.content.equalsIgnoreCase("SPACE")
                || this.content.equalsIgnoreCase("SPACES"))
        {
            for (char c : chars)
            {
                if (c != ' ')
                {
                    return false;
                }
            }
            return true;
        }
        else if (this.content.equalsIgnoreCase("ZERO")
                || this.content.equalsIgnoreCase("ZEROES"))
        {
            for (char c : chars)
            {
                if (c != '0')
                {
                    return false;
                }
            }
            return true;
        }

        else
        {
            return this.content.equals(content);
        }
    }

    public boolean isSimple()
    {
        return true;
    }

}
