/**
 * 
 */
package com.shawnfox.java4.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   private Connection dbConnection;
   
   /**
    * Entry point of the application.
    * 
    * @param args
    */
   public static void main(String[] args) {
      try
      {
         var driver = new Driver();
         driver.populateDatabase();
         driver.executeQueryStatements();
         driver.dropTables();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   public Driver() throws SQLException {
      System.out.printf("Connection Info %s: %n", DATABASE_URL);
      System.out.print("Connecting ");
      dbConnection = DriverManager.getConnection(DATABASE_URL);
      System.out.println("- done.");
   }
   
   /**
    * Reads the resource file containing the SQL commands to populate the database, 
    * gets a connection to the database and creates the database if it doesn't 
    * exist yet, and then populates the database by executing all commands within
    * the resource file.
    * 
    * @return
    */
   public void populateDatabase() {
      var input = Driver.class.getClassLoader().getResourceAsStream("Lessons.sql");
      try (BufferedReader lessonsReader = new BufferedReader(
            new InputStreamReader(input, StandardCharsets.UTF_8));
           Statement stat = dbConnection.createStatement();)
      {
         String line;

         System.out.println("Populating database");
         while( (line = lessonsReader.readLine()) != null) {
            if (!line.isBlank()) {
               stat.executeUpdate(line);
            }
            System.out.println(line);
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private void executeQueryStatements() {
      try (var statement = dbConnection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, 
            ResultSet.CONCUR_READ_ONLY)) {
         ResultSet rs = statement.executeQuery("SELECT * FROM Lessons");
         
         var rsmd = rs.getMetaData();
         System.out.println(rsmd.getColumnName(1) + ", " + rsmd.getColumnName(2));
         
         while (rs.next())
            System.out.println(rs.getString(1) + ", " + rs.getString(2));
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   private void dropTables() throws SQLException {
      System.out.println("Dropping tables");
      var statement = dbConnection.createStatement();
      statement.executeUpdate("DROP TABLE Lessons");
   }

}
