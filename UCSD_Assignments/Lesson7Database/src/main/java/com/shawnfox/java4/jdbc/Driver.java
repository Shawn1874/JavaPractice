/**
 * 
 */
package com.shawnfox.java4.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * This application demonstrates creating and connecting to a Derby database, creating
 * a table, inserting rows, and performing query operations in order to demonstrate
 * an understanding of the lesson materials.
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static final String DATABASE_URL = "jdbc:derby:COREJAVA;create=true";
   
   /**
    * Entry point of the application.
    * 
    * @param args
    */
   public static void main(String[] args) {
      try
      {
         var driver = new Driver();
         Connection db = driver.populateDatabase();
         System.out.println("Closing the connection");
         db.close();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }
   
   /**
    * Reads the resource file containing the SQL commands to populate the database, 
    * gets a connection to the database and creates the database if it doesn't 
    * exist yet, and then populates the database by executing all commands within
    * the resource file.
    * 
    * @return
    */
   public Connection populateDatabase() {
      var input = Driver.class.getClassLoader().getResourceAsStream("Lessons.sql");
      System.out.println("Connecting to: " + DATABASE_URL);
      
      try (BufferedReader lessonsReader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
           Connection connection = DriverManager.getConnection(DATABASE_URL);
           Statement stat = connection.createStatement())
      {
         System.out.println("Connected");
         String line;
         
         while( (line = lessonsReader.readLine()) != null) {
            if (!line.isBlank()) {
               stat.executeUpdate(line);
            }
            System.out.println(line);
         }
         
         stat.executeUpdate("DROP TABLE Lessons");

         return connection;
      }
      catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

}
