/**
 * 
 */
package jdbc.derby;

import java.sql.SQLException;

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   /**
    * @param args
    */
   public static void main(String[] args) {
      try
      {
         var testDB = new TestDB();
         testDB.runTest();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

}
