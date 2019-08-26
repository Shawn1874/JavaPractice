package com.shawndfox.javafxconcurrency;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class WordCounterController implements Initializable {
    @FXML
    private ComboBox<?> fileList;

    @FXML
    void addFileToTable(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
