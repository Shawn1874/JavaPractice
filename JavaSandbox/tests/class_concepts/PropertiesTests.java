package class_concepts;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PropertiesTests {

   /**
    * two argument getproperty method returns a default value if requested property doesn't
    * exist.
    */
   @Test
   void testGetNonExistentProperty() throws IOException {
      String value = System.getProperty("does.not.exist", "test");
      assertEquals("test", value);
   }
   
   /**
    * test basic set/get methods and other supporting operations for managing a properties
    * object
    */
   @Test
   void testModifyProperties() {
      Properties props = new Properties();
      props.setProperty("name", "Shawn");
      assertEquals("Shawn", props.getProperty("name"));
      
      props.setProperty("hometown", "San Diego");
      Set<String> keys = props.stringPropertyNames();
      assertEquals(2, keys.size());
      assertTrue(keys.contains("hometown"));
      assertFalse(keys.contains("San Diego"));
   }

}
