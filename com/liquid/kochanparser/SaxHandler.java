package com.liquid.kochanparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler 
{
	private TimeTable owner; // TimeTable object that created this SaxHandler 
	private int trc = -1; // counts the <tr> tags that appears in html, for the purpose of throwing things into apriopriate places
	private int tdc = -1; // counts the <td> tags that appear between the <tr> tags. resets on any <tr> encounter
	
	private String currentName;
	private Attributes currentAttr;
	
	public void startDocument () throws SAXException
	{
		
	}
	
	public void endDocument () throws SAXException
	{
		
	}
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		currentName = qName;
		currentAttr = attributes;
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
		
	}
	
	public void characters (char ch[], int start, int length) throws SAXException
	{
		String value = new String (ch, start, length).trim ();
		if (value.length() == 0) return;
		
		//System.out.println (currentName + " " + value + " " + trc + " " + tdc);
		
		if ("th".equals (currentName) && tdc >= 2)
		{
			owner.addDay (value);
		}
		if (("span".equals (currentName) || "a".equals (currentName)) && trc > 2 && trc <= 11)
		{
			this.parseAttributes (currentAttr, value);
		}
	}
	
	public void ignorableWhiteSpace (char ch[], int start, int lenght) throws SAXException
	{
		
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
	
	private void parseAttributes (Attributes attributes, String value) throws SAXException
	{
		int count = attributes.getLength ();
		for (int i = 0; i < count; i++)
		{
			String attrValue = attributes.getValue (i);
		
			if ("p".equals (attrValue))
			{
				this.owner.getDay (tdc - 2).addLesson ();
				this.owner.getDay (tdc - 2).getCurrentLesson ().setIndex (trc - 3);
				this.owner.getDay (tdc - 2).getCurrentLesson ().setName (value);
				
				if (this.owner.getDay (tdc - 2).getSize () > 1 && this.owner.getDay (tdc - 2).getCurrentLesson ().getIndex () == this.owner.getDay (tdc - 2).getPreviousLesson ().getIndex ())
				{
					this.owner.getDay (tdc - 2).getCurrentLesson ().setGroup (2);
					this.owner.getDay (tdc - 2).getPreviousLesson ().setGroup (1); 
				}
			}
			
			if ("n".equals (attrValue))
			{
				this.owner.getDay (tdc - 2).getCurrentLesson ().setTeacherCode (value);
			}
			if ("s".equals (attrValue))
			{
				this.owner.getDay (tdc - 2).getCurrentLesson ().setClassroom (value);
			}
		}
	}
}


