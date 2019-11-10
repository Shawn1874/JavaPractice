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
import java.sql.ResultSetMetaData;
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
         driver.printDatabaseMetadata();
         driver.populateDatabase();
         driver.executeQueryStatements();
         driver.dropTables();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   /**
    * Construct a Driver object.  Connects to the database via a DriverManager.
    * 
    * @throws SQLException
    */
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
   
   /**
    * Print database metadata and connection information.
    */
   public void printDatabaseMetadata() {
      try {
         System.out.println();
         System.out.println("Printing Database Metadata");
         System.out.printf("IsClosed: %b%n", dbConnection.isClosed());
         System.out.printf("IsReadOnly: %b%n", dbConnection.isReadOnly());
         System.out.printf("IsValid: %b%n", dbConnection.isValid(2));
         System.out.printf("Auto Commit Enabled: %b%n", dbConnection. getAutoCommit());
         System.out.printf("Schema Name: %s%n", dbConnection.getSchema());
         
         var metaData = dbConnection.getMetaData();
         System.out.printf("Product Name: %s%n", metaData.getDatabaseProductName());
         System.out.printf("Product Version: %s%n", metaData.getDatabaseProductVersion());
         System.out.printf("Driver name: %s%n", metaData.getDriverName());
         System.out.printf("Driver version: %s%n", metaData.getDriverVersion());
         System.out.printf("Supports Column Aliasing: %b%n", metaData.supportsColumnAliasing());
         System.out.printf("Supports expressions in order by: %b%n", metaData.supportsExpressionsInOrderBy());
         System.out.printf("Supports extended SQL grammar: %b%n", metaData.supportsExtendedSQLGrammar());
         System.out.println();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Demonstrates the execution of query statements that use filtering and ordering
    * statements.
    */
   private void executeQueryStatements() {
      String selectAll = "SELECT * FROM Lessons";
      String orderByDesc = "ORDER BY Lesson_Num Desc";
      String orderTitleAsc = "ORDER BY TITLE";
      String beginsOrEndsWithS = "WHERE TITLE LIKE 'S%' OR TITLE LIKE '%s'";
      String between38 = "WHERE Lesson_Num BETWEEN 3 AND 8";
      System.out.println("Demonstrate the use of Query Statements");
      
      try (var statement = dbConnection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, 
            ResultSet.CONCUR_READ_ONLY)) {
         ResultSet rs = statement.executeQuery(selectAll);
         System.out.println(selectAll);
         printResultSet(rs);
         rs.close();
         
         String query = String.format("%s %s", 
               selectAll, 
               orderByDesc);
         rs = statement.executeQuery(query);
         System.out.println(query);
         printResultSet(rs);
         rs.close();
         
         query = String.format("%s %s", 
               selectAll, 
               orderTitleAsc);
         rs = statement.executeQuery(query);
         System.out.println(query);
         printResultSet(rs);
         rs.close();
         
         query = String.format("%s %s %s", 
               selectAll, 
               beginsOrEndsWithS,
               orderByDesc);
         rs = statement.executeQuery(query);
         System.out.println(query);
         printResultSet(rs);
         rs.close();
         
         query = String.format("%s %s", 
               selectAll, 
               between38);
         rs = statement.executeQuery(query);
         System.out.println(query);
         printResultSet(rs);
         rs.close();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Display a result set to the console
    * 
    * @param rs - the ResultSet that was returned by a query
    */
   private void printResultSet(ResultSet rs) {
      ResultSetMetaData rsmd;
      try {
         rsmd = rs.getMetaData();
         System.out.println(rsmd.getColumnName(1) + ", " + rsmd.getColumnName(2));
         
         while (rs.next())
            System.out.println(rs.getString(1) + ", " + rs.getString(2));
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Drop the table from the database.
    * 
    * @throws SQLException
    */
   private void dropTables() throws SQLException {
      System.out.println("Dropping tables");
      var statement = dbConnection.createStatement();
      statement.executeUpdate("DROP TABLE Lessons");
   }

}
