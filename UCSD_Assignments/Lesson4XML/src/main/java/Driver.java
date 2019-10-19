import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
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
      System.out.println();
      demonstrateXpathParser(fileName);
   }

   /**
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
    * 
    * @param fileName - the name of the XML file
    */
   public static void demonstrateSaxParser(String fileName) {

      System.out.println("Results of XML Parsing using SAX Parser:");
      
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse(fileName);
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   /**
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
         var result = xpath.evaluate("/jobresult/serial/text()", doc);
         System.out.println(String.format("serial: %s", result));
         result = xpath.evaluate("jobresult/data/visible-string/text()", doc);
         System.out.println(String.format("visible-string: %s", result.trim()));
         result = xpath.evaluate("jobresult/data/structure/unsigned/text()", doc);
         System.out.println(String.format("unsigned: %s", result));
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
