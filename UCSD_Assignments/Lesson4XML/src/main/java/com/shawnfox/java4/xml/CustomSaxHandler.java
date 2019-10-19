/**
 * 
 */
package com.shawnfox.java4.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;;

/**
 * This is an event handler for a SAXParser.
 * 
 * @author Shawn D. Fox
 *
 */
public class CustomSaxHandler extends DefaultHandler {

   private List<String> searchList = new ArrayList<String>(List.of("serial", "visible-string", "unsigned"));
   private StringBuilder foundContent = new StringBuilder();
   private String foundElement;
   
   /**
    * This is the event handler that processes StartElement events.  If the qname is found 
    * within the searchList, then store it so that the characters event will process the
    * text data of the element.
    * 
    * @param namespaceURI - the namespace of the element
    * @param lname - local name
    * @param qname - qualified name
    * @param attrs - the list of attributes for the element
    */
   @Override
   public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) {
      if (searchList.contains(qname)) {
         foundElement = qname;
      }
      else {
         foundElement = null;
      }
   }

   /**
    * Process a text field of an element. Uses the foundElement field to determine
    * whether or not to process the current characters event.
    * 
    * @param ch     - the text content of an element
    * @param start  - the start of the text within the array
    * @param length - the length of the text
    */
   @Override
   public void characters(char[] ch, int start, int length) throws SAXException {

      if (foundElement != null) {
         foundContent.append(String.format("%s: %s%n", foundElement, new String(ch, start, length).trim()));
         foundElement = null;
      }
   }

   /**
    * Build and return a string that represents the state of the object.
    * 
    * @return String
    */
   @Override
   public String toString() {
      return foundContent.toString();
   }
}
