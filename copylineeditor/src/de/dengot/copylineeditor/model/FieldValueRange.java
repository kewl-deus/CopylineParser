package de.dengot.copylineeditor.model;

/**
 * @author 010627
 * 
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu
 * ändern: Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und
 * Kommentare
 */
public class FieldValueRange extends FieldValue
{
    private SimpleFieldValue start;

    private SimpleFieldValue end;

    public FieldValueRange(SimpleFieldValue start, SimpleFieldValue end)
    {
        this.start = start;
        this.end = end;
    }

    /**
     * @return Returns the end.
     */
    public SimpleFieldValue getEnd()
    {
        return end;
    }

    /**
     * @param end
     *            The end to set.
     */
    public void setEnd(SimpleFieldValue end)
    {
        this.end = end;
    }

    /**
     * @return Returns the start.
     */
    public SimpleFieldValue getStart()
    {
        return start;
    }

    /**
     * @param start
     *            The start to set.
     */
    public void setStart(SimpleFieldValue start)
    {
        this.start = start;
    }

    public boolean isRange()
    {
        return true;
    }

    public String toString()
    {
        return this.start.toString() + " - " + this.end.toString();
    }

    public boolean equals(String content)
    {
//        long valToCompare = Long.parseLong(content);
//        long startVal = Long.parseLong(this.start.getContent());
//        long endVal = Long.parseLong(this.end.getContent());

        return (this.start.getContent().compareTo(content) <= 0)
                && (this.end.getContent().compareTo(content) >= 1);
    }

}
