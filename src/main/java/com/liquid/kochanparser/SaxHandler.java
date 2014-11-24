package com.liquid.kochanparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by liquid on 24.11.14.
 */
public class SaxHandler extends DefaultHandler
{
    private TimeTable owner;

    /**
     * Class constructor.
     * Arguments:
     * table - the TimeTable objects that is supposed to create the handler
    */
    public SaxHandler (TimeTable table)
    {
        this.owner = table;
    }

    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        
    }
}
