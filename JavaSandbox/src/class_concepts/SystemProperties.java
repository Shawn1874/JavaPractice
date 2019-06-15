package class_concepts;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class SystemProperties {
   
   public static void main(String[] args) throws IOException {
      // Print system properties
      Properties systemProperties = System.getProperties();
      for(Map.Entry<Object, Object> property : systemProperties.entrySet()) {
         System.out.println(property);
      }
      
      SecurityManager securityMgr = System.getSecurityManager();
      System.out.println(securityMgr == null ? "Security manager is null" : "Security manager exists");
   }
}
