import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   /**
    * @param args
    */
   public static void main(String[] args) {
      //System.out.println("Hello World");

      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse("JobResult_UCSDExt.xml");
         Element root = doc.getDocumentElement();
         System.out.println(root.getNodeName());
         
         NodeList children = root.getChildNodes();
         for (int i = 0; i < children.getLength(); i++)
         {
            Node child = children.item(i);
            if (child instanceof Element) {
               var childElement = (Element) child;
               System.out.println(childElement.getNodeName());
            }
         }
      }
      catch(Exception e) {
         e.printStackTrace();
      }

   }

}
