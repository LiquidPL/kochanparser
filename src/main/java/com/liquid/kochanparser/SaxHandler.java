package com.liquid.kochanparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for the SAX parser.
 * The functions should be called by the parser, rather than on their own.
 *
 * @author Krzysztof Gutkowski (LiquidPL)
 * @version dev
 */
public class SaxHandler extends DefaultHandler
{
    private TimeTable owner;

    /**
     * Class constructor
     * @param table TimeTable object in which this handler is supposed to be created
    */
    public SaxHandler (TimeTable table)
    {
        this.owner = table;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

    }

    public void endElement (String uri, String localName, String qName) throws SAXException
    {

    }

    public void characters (char[] ch, int start, int length)
    {

    }

    /**
     * A getter for the TimeTable owner object
     *
     * @return The TimeTable object this handler was created in
     */
    public TimeTable getOwner () { return owner; }
}
