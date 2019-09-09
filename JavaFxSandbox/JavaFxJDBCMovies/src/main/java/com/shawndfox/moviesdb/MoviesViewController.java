package com.shawndfox.moviesdb;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.RowFilter;

public class MoviesViewController implements Initializable
{

   @FXML
   private Label label;
   private static final String URL = "jdbc:derby:Movies;create=true";
   private final String nameOfMoviesTable = "MOVIES";
   private final String getMovies = "Select * from MOVIES";

   private Connection connection;
   private PreparedStatement insertMovie;

   @FXML
   private TextField descrTextField;

   @FXML
   private TextField ratingTextField;

   @FXML
   private TextField titleTextField;

   // query the database and display results in JTable
   @FXML
   void submitQueryButtonPressed(ActionEvent event)
   {
      // perform a new query
//      try {
//         tableModel.setQuery(queryTextArea.getText());
//      } 
//      catch (SQLException sqlException) {
//         displayAlert(Alert.AlertType.ERROR, "Database Error", 
//            sqlException.getMessage());
//         
//         // try to recover from invalid user query 
//         // by executing default query
//         try {
//            tableModel.setQuery(DEFAULT_QUERY);
//            queryTextArea.setText(DEFAULT_QUERY);
//         } 
//         catch (SQLException sqlException2) {
//            displayAlert(Alert.AlertType.ERROR, "Database Error", 
//               sqlException2.getMessage());
//            tableModel.disconnectFromDatabase(); // close connection  
//            System.exit(1); // terminate application
//         } 
//      } 
   }

   /**
    * The event handler for the Add Movie Button
    * @param event 
    */
   @FXML
   void addMovie(ActionEvent event)
   {
      String ratingText = ratingTextField.getText();
      //int rating = Integer.parseInt(ratingText);
      String titleText = titleTextField.getText();
      String descriptionText = descrTextField.getText();
      
      try
      {
         insertMovie.setString(1, titleText);
         insertMovie.setString(2, ratingText);
         insertMovie.setString(3, descriptionText);
         
         insertMovie.execute();
         
         Statement stmt = connection.createStatement();
         ResultSet set = stmt.executeQuery(getMovies);
         while(set.next()) {
            System.out.println(set.getRow());
         }
      }
      catch(SQLException e) {
         
      }
   }

   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
      try {
         System.out.println("Connecting to database URL: " + URL);
         connection = DriverManager.getConnection(URL);

         if (moviesTableExists(connection)) {
            // The table already  exists
            System.out.println("table exists!");
         }
         else {
            Statement stmt = connection.createStatement();
            System.out.println("Creating Table Movies");
            String primaryKey = "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)";
            String cmd = String.format("CREATE TABLE %s (%s, name VARCHAR(255), rating INT, description VARCHAR(255), CONSTRAINT primary_key PRIMARY KEY (id))",
                                       nameOfMoviesTable,
                                       primaryKey);
            stmt.execute(cmd);
         }
         
         // Prepare a statement for future use
         String preparedStatement = String.format("INSERT INTO %s (name, rating, description) VALUES (?, ?, ?)", nameOfMoviesTable );
         insertMovie = connection.prepareStatement(preparedStatement);
      }
      catch (SQLException sqlException) {
         System.out.println("test");
         sqlException.printStackTrace();
      }

   }

   /**
    * Determines whether or not a table already exists within the database.
    *
    * @param conn the database connection which must have already been opened
    * @return true if the table exists, and false otherwise
    * @throws SQLException
    */
   private boolean moviesTableExists(Connection conn) throws SQLException
   {
      DatabaseMetaData dbMetaData = conn.getMetaData();
      ResultSet rs = dbMetaData.getTables(null, null, nameOfMoviesTable, null);
      boolean value = rs.next();
      return value;
   }
}
