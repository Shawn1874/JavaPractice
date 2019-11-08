package jdbc.derby;

import java.sql.*;
import java.io.*;

/**
 * This program tests that the database and the JDBC driver are correctly configured.
 * @version 1.03 2018-05-01
 * @author Cay Horstmann
 */
public class TestDB
{

   private static final String DATABASE_URL = "jdbc:derby:HelloWorld;create=true";
   
   /**
    * Runs a test by creating a table, adding a value, showing the table contents, and
    * removing the table.
    */
   public void runTest() throws SQLException, IOException
   {
      try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement stat = conn.createStatement())
      {
         stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
         stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

         try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
         {
            if (result.next())
               System.out.println(result.getString(1));
         }
         stat.executeUpdate("DROP TABLE Greetings");
      }
   }
}
