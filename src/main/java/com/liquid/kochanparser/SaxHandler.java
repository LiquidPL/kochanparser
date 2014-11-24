package com.liquid.kochanparser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler 
{
	private TimeTable owner; // TimeTable object that created this SaxHandler 
	private int trc = -1; // counts the <tr> tags that appears in html, for the purpose of throwing things into apriopriate places
	private int tdc = -1; // counts the <td> tags that appear between the <tr> tags. resets on any <tr> encounter
	
	private String currentqName;
	private AttributesImpl currentAttr;
	
    private String currentName = "";
    private String currentCode = "";
    private String currentRoom = "";
    private String currentClass = "";
	
	public void startDocument () throws SAXException
	{
		
	}
	
	public void endDocument () throws SAXException
	{
		
	}
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		currentqName = qName;
		currentAttr = new AttributesImpl (attributes);
		//System.out.println (qName + " " + trc + " " + tdc);
		if ("tr".equals (qName))
		{
			this.trc++;
			this.tdc = -1;
		}
		if ("td".equals (qName) || "th".equals (qName))
		{
			this.tdc++;
		}
	}
	
	public void endElement (String uri, String localName, String qName) throws SAXException
	{
		if ("td".equals (qName) && tdc >= 2 && this.checkIfEmpty (currentName, currentCode, currentRoom, currentClass) == false)
		{
		    this.owner.getDay (tdc - 2).addLesson (currentName, currentCode, currentRoom, currentClass);
		    if (owner.getDay (tdc - 2).getSize () > 1 && owner.getDay (tdc - 2).getPreviousLesson ().getGroup () == 1) this.owner.getDay (tdc - 2).getCurrentLesson ().setGroup (2);
		    currentName = ""; currentCode = ""; currentRoom = ""; currentClass = "";
		}
	}
	
	public void characters (char ch[], int start, int length) throws SAXException
	{
		String value = new String (ch, start, length).trim ();
		if (value.length() == 0) return;
		
		//System.out.println (currentName + " " + value + " " + trc + " " + tdc);
		
		if ("th".equals (currentqName) && tdc >= 2)
		{
			owner.addDay (value);
		}
		if (("span".equals (currentqName) || "a".equals (currentqName)) && trc > 2 && trc <= 11)
		{
			this.parseAttributes (currentAttr, value);
		}
	}
	
	public void ignorableWhiteSpace (char ch[], int start, int length) throws SAXException
	{
		
	}
	
	private void parseAttributes (AttributesImpl attributes, String value) throws SAXException
    {
        int count = attributes.getLength ();
        for (int i = 0; i < count; i++)
        {
            String attrValue = attributes.getValue (i);
        
            if ("p".equals (attrValue))
            {
                if (currentName != "") this.handleGroups ();
                this.currentName = value;
            }
            
            if ("n".equals (attrValue))
            {
                this.currentCode = value;
            }
            if ("s".equals (attrValue))
            {
                this.currentRoom = value;
            }
            if ("o".equals (attrValue))
            {
                this.currentClass = value;
            }
        }
    }
    
    private void handleGroups ()
    {
        this.owner.getDay (tdc - 2).addLesson (currentName, currentCode, currentRoom, currentClass);
        this.owner.getDay (tdc - 2).getCurrentLesson ().setGroup (1);
    }
    
    private Boolean checkIfEmpty (String name, String code, String room, String _class)
    {
        if (name.length () != 0 && code.length () != 0 && room.length () != 0) return false;
        else if (_class.length () != 0 && name.length () != 0 && room.length () != 0) return false;
        else if (code.length () != 0 && _class.length () != 0 && name.length () != 0) return false;
        else return true;
    }
    
	public SaxHandler (TimeTable owner)
	{
		this.setOwner(owner);
	}

	public TimeTable getOwner()
	{
		return owner;
	}

	public void setOwner(TimeTable owner) 
	{
		this.owner = owner;
	}
}


