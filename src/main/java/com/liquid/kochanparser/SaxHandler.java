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

    private int currentLesson = -4;// currentLesson and currentDay are set to negative values
    private int currentDay = -3;   // because of the <tr> and <td> tags at the beginning of
                                   // the HTML file not containing any important data (so the indexing begins from 0)
    private String currentName = "";
    private String currentAttribute = "";
    private int currentGroup = 0; // 0 - current lesson is not grouped; -1 - current lesson is going to be grouped
                                  // 1 - group 1; 2 - group 2
    private String currentSubject = "";
    private String currentTeacher = "";
    private String currentClassroom = "";
    private String currentClass = "";

    /**
     * Class constructor
     *
     * @param table TimeTable object in which this handler is supposed to be created
     */
    public SaxHandler (TimeTable table)
    {
        this.owner = table;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        currentName = qName;
        if ("tr".equals (qName))
        {
            currentLesson++;
            currentDay = -3;
        }
        if ("td".equals (qName) || "th".equals (qName)) currentDay++;
        if ("span".equals (qName) && currentGroup == -1)
            currentGroup = 1;
        else if ("span".equals (qName) && currentGroup == 1)
            currentGroup = 2;
        if ("span".equals (qName) || "a".equals (qName))
        {
            int length = attributes.getLength ();
            for (int i = 0; i < length; i++)
            {
                String value = attributes.getValue (i);
                if ("p".equals (value) || "n".equals (value) || "s".equals (value) || "o".equals (value))
                    currentAttribute = value;
                if ("font-size:85%".equals (value))
                    currentGroup = -1;
            }
        }
        if ("br".equals (qName) && currentGroup == 1 && currentDay >= 0 && currentDay <= 4 &&
                !checkIfEmpty (currentSubject, currentTeacher, currentClassroom, currentClass))
        {
            owner.getDay (currentDay).addLesson (currentSubject, currentTeacher, currentClassroom, currentClass);
            owner.getDay (currentDay).getCurrentLesson ().setGroup (currentGroup);
            currentSubject = ""; currentTeacher = ""; currentClassroom = ""; currentClass = "";
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException
    {
        if ("td".equals (qName) && currentDay >= 0 && currentDay <= 4 &&
            !checkIfEmpty (currentSubject, currentTeacher, currentClassroom, currentClass))
        {
            owner.getDay (currentDay).addLesson (currentSubject, currentTeacher, currentClassroom, currentClass);
            if (currentGroup == 1 || currentGroup == 2)
                owner.getDay (currentDay).getCurrentLesson ().setGroup (currentGroup);
            currentGroup = 0;
            currentSubject = ""; currentTeacher = ""; currentClassroom = ""; currentClass = "";
        }
    }

    @Override
    public void characters (char[] ch, int start, int length) throws SAXException
    {
        String value = new String (ch, start, length).trim ();
        if (value.length () == 0) return;

        //System.out.println (currentName + " " + currentAttribute + " "  + value + " " + currentLesson + " " + currentDay);

        if ("p".equals (currentAttribute))
        {
            currentSubject = value;
        }
        if ("n".equals (currentAttribute))
        {
            currentTeacher = value;
        }
        if ("s".equals (currentAttribute))
        {
            currentClassroom = value;
        }
        if ("o".equals (currentAttribute))
        {
            currentClass = value;
        }
        if ("th".equals (currentName) && currentDay >= 0)
        {
            owner.addDay (value);
        }
        if ("td".equals (currentName) && currentDay == -1)
        {
            String[] values = new String (value.toCharArray (), 3, value.length () - 3).split ("-");
            owner.getStarthours ().add (new Time (values[0]));
            owner.getEndhours ().add (new Time (values[1]));
        }
        if ("span".equals (currentName) && "-------".equals (value))
            currentGroup = 2;
    }

    /**
     * Checks if the entire set of lesson data have been already collected.
     * @param name Subject name
     * @param code Teacher code
     * @param room Classroom
     * @param _class Class
     * @return
     */
    private Boolean checkIfEmpty (String name, String code, String room, String _class)
    {
        if (name.length () != 0 && code.length () != 0 && room.length () != 0) return false;
        else if (_class.length () != 0 && name.length () != 0 && room.length () != 0) return false;
        else if (code.length () != 0 && _class.length () != 0 && name.length () != 0) return false;
        else return true;
    }

    /**
     * A getter for the TimeTable owner object
     *
     * @return The TimeTable object this handler was created in
     */
    public TimeTable getOwner () { return owner; }
}
