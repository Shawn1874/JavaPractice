package com.shawndfox.javafxconcurrency;

import com.shawndfox.javafxconcurrency.model.FileProperties;
import com.shawndfox.javafxconcurrency.model.WordCounterTask;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Shawn D. Fox
 */
public class WordCounterController implements Initializable {

    @FXML
    private TableView<FileProperties> fileTable;
    
    private final ObservableList<FileProperties> fileTableEntries = FXCollections.observableArrayList();
    
    ExecutorService threads = Executors.newCachedThreadPool();
    
    private String initialDirectory;

    @FXML
    void addFileToTable(ActionEvent event) throws IOException {
       FileChooser dlg = new FileChooser();
       dlg.setTitle("Pick a text file to analyze!");
       dlg.setInitialDirectory(new File(initialDirectory));
       dlg.getExtensionFilters().addAll(
         new ExtensionFilter("Text Files", "*.md", "*.txt", "*.xml", "*.html", "*.conf"));
         
       File chosen = dlg.showOpenDialog(null);
       if(chosen != null) {
         FileProperties fp = new FileProperties();
         fp.setTheFile(chosen);
         fp.setWordCount("");
         fileTableEntries.add(fp);
         initialDirectory = chosen.getParent();
       }
    }

    /**
     * For each entry in the file table, start a background thread to determine the word count of the file.
     * 
     * @param event 
     */
    @FXML
    void startCounting(ActionEvent event) {
       Integer wordCount = 100;
       
       for(FileProperties entry : fileTableEntries) {
          //construct a task, and register an event so that  
          Task<String> task = new WordCounterTask(entry.getTheFile());
          entry.bindWordCount(task.valueProperty());
          threads.submit(task);
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileTable.setItems(fileTableEntries);
        TableColumn<FileProperties, String> nameColumn = new TableColumn<>("File Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("fileName"));
        
        TableColumn<FileProperties, String> wordCountColumn = new TableColumn<>("Word Count");
        wordCountColumn.setCellValueFactory(new PropertyValueFactory("wordCount"));
        
        fileTable.getColumns().setAll(nameColumn, wordCountColumn);
        
        initialDirectory = System.getProperty("user.dir");
    }    
}
