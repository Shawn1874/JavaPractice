package com.shawndfox.javafxconcurrency;

import com.shawndfox.javafxconcurrency.model.FileProperties;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class WordCounterController implements Initializable {
    @FXML
    private ComboBox<?> fileList;

    @FXML
    private TableView<FileProperties> fileTable;
    
    private final ObservableList<FileProperties> fileTableEntries = FXCollections.observableArrayList();

    @FXML
    void addFileToTable(ActionEvent event) throws IOException {
       FileChooser dlg = new FileChooser();
       dlg.setTitle("Pick a text file to analyze!");
       File chosen = dlg.showOpenDialog(null);
       if(chosen != null) {
         FileProperties fp = new FileProperties();
         fp.setFileName(chosen.getName());
         fp.setWordCount("0");
         fileTableEntries.add(fp);
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
    }    
}
