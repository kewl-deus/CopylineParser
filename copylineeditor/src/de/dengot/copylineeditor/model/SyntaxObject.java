/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.model;

import java.util.Iterator;
import java.util.Vector;

import de.dengot.copylineeditor.logic.scanner.token.IdentifierToken;




/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class SyntaxObject
{
    protected static final int StandardPosition = 0;

    private IdentifierToken name;

    private int startPosition;

    private int endPosition;

    private Vector objects;

    public SyntaxObject(IdentifierToken name)
    {
        startPosition = 0;
        endPosition = 0;
        objects = new Vector();
        this.name = name;
    }

    public SyntaxObject(IdentifierToken name, int startPosition, int endPosition)
    {
        this.startPosition = 0;
        this.endPosition = 0;
        objects = new Vector();
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.name = name;
    }

    public String getName()
    {
        return name.getValue();
    }

    public void setName(IdentifierToken name)
    {
        this.name = name;
    }

    public IdentifierToken getNameToken()
    {
        return name;
    }

    public int getStartPosition()
    {
        return startPosition;
    }

    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }

    public int getEndPosition()
    {
        return endPosition;
    }

    public void setEndPosition(int endPosition)
    {
        this.endPosition = endPosition;
    }

    public int getNameStartPosition()
    {
        return name.getPosition();
    }

    public void register(SyntaxObject object)
    {
        if (!objects.contains(object))
            objects.add(object);
    }

    public void deregister(SyntaxObject object)
    {
        objects.remove(object);
    }

    public Iterator iteratorOnObservers()
    {
        return objects.iterator();
    }

    protected void update()
    {
        SyntaxObject current;
        for (Iterator i = objects.iterator(); i.hasNext(); current
                .notifyUpdate(this))
            current = (SyntaxObject) i.next();

    }

    public void notifyUpdate(SyntaxObject syntaxobject)
    {
    }

}