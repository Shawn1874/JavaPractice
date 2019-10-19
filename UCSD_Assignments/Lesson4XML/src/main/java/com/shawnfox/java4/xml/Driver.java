package com.shawnfox.java4.xml;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This test driver class contains the code which demonstrates 4 different ways of parsing an 
 * XML file.
 * 
 * @author Shawn D. Fox
 *
 */
public class Driver {

   /**
    * This is the entry  point of the application.
    * @param args
    */
   public static void main(String[] args) {
      String fileName = (args.length == 1 && !args[0].isEmpty()) ? args[0] : "JobResult_UCSDExt.xml";
      demonstrateDomParser(fileName);
      System.out.println();
      demonstrateSaxParser(fileName);
      demonstrateXpathParser(fileName);
      System.out.println();
      demonstrateSTaxParser(fileName);
   }

   /**
    * Demonstrate the processing of an XML file using a DOM parser.
    * 
    * @param fileName
    */
   public static void demonstrateDomParser(String fileName) {
      try {
         System.out.println("Results of XML Parsing using DOM Parser:");
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(fileName);

         printNode( doc.getElementsByTagName("serial"));
         printNode( doc.getElementsByTagName("visible-string") );
         printNode( doc.getElementsByTagName("unsigned"));
         
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Prints the child text element of all nodes.
    * 
    * @param nodes - NodeList object
    */
   static void printNode(NodeList nodes) {
      
      for (int i = 0; i < nodes.getLength(); i++)
      {
         Node child = nodes.item(i);
         if (child instanceof Element) {
            var childElement = (Element) child;
            var childText = (Text) childElement.getFirstChild();
            System.out.println(String.format(
                  "%s: %s", 
                  childElement.getNodeName(), childText.getData().trim()));
         }
      }
   }
   
   /**
    * Demonstrates the parsing of an XML file using a SAXParser and an event driven
    * approach to processing the stream.
    * 
    * @param fileName - the name of the XML file
    */
   public static void demonstrateSaxParser(String fileName) {

      System.out.println("Results of XML Parsing using SAX Parser:");
      
      try {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setNamespaceAware(true);
         SAXParser saxParser = factory.newSAXParser();
         var handler = new CustomSaxHandler();
         saxParser.parse(fileName, handler);
         System.out.println(handler.toString());
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Demonstrates the parsing of an XML file using an XMLStreamReader and a pull
    * approach to processing the stream.
    * 
    * @param fileName - the name of the XML file
    */
   public static void demonstrateSTaxParser(String fileName) {

      System.out.println("Results of XML Parsing using STax Parser:");

      try (var input = new FileInputStream(fileName)) {
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLStreamReader parser = factory.createXMLStreamReader(input);
         List<String> searchList = new ArrayList<String>(List.of("serial", "visible-string", "unsigned"));
         String foundElement;
         
         while (parser.hasNext()) {
            int event = parser.next();
            
            switch(event) {
            case XMLStreamConstants.START_ELEMENT:
               foundElement = parser.getLocalName();
               if (searchList.contains(foundElement))
               {
                  System.out.println(String.format(
                        "%s: %s", 
                        foundElement, 
                        parser.getElementText().trim()));
               }
               break;
            }
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Demonstrates the parsing of an XML file using XPaths.
    * 
    * @param fileName - the name of the XML file
    */
   public static void demonstrateXpathParser(String fileName) {
      System.out.println("Results of XML Parsing using XPATH:");
      
      try {

         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(fileName);
         
         var xpFactory = XPathFactory.newInstance();
         var xpath = xpFactory.newXPath();
         
         // Demonstrate how to get a node from the xpath expression, and then extract the info from 
         // the node.  Note the use of the XPathConstants value to specify the desired return type.
         Element node = (Element) xpath.evaluate("/jobresult/serial", doc, XPathConstants.NODE);
         System.out.println(String.format("%s: %s", node.getNodeName(), ((Text)node.getFirstChild()).getData().trim()));
         
         // Demonstrates how to get the text data with the xpath expression
         var result = xpath.evaluate("jobresult/data/visible-string/text()", doc);
         System.out.println(String.format("visible-string: %s", result.trim()));
         result = xpath.evaluate("jobresult/data/structure/unsigned/text()", doc);
         System.out.println(String.format("unsigned: %s", result));
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
