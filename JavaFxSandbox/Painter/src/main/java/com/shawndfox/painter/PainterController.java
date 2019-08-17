package com.shawndfox.painter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PainterController implements Initializable {

    @FXML
    private Pane drawingArea;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;
    
    private enum PenSize { 
       SMALL(2), 
       MEDIUM(4), 
       LARGE(6); 
       
       private final int radius;
       
       PenSize(int radius) { this.radius = radius; }
       
       public int getRadius() { return radius; }
    }
    
    private PenSize radius = PenSize.MEDIUM; 
    private Paint brushColor  = Color.BLACK;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       blackRadioButton.setUserData(Color.BLACK);
       redRadioButton.setUserData(Color.RED);
       greenRadioButton.setUserData(Color.GREEN);
       blueRadioButton.setUserData(Color.BLUE);
       smallRadioButton.setUserData(PenSize.SMALL);
       mediumRadioButton.setUserData(PenSize.MEDIUM);
       largeRadioButton.setUserData(PenSize.LARGE);
    }
    
    @FXML
    void clearButtonPressed(ActionEvent event) {
      drawingArea.getChildren().clear();
    }

    @FXML
    void colorRadioButtonSelected(ActionEvent event) {
       brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
       Circle circle = new Circle(event.getX(), event.getY(), radius.getRadius(), brushColor);
       drawingArea.getChildren().add(circle);
    }

    @FXML
    void sizeRadioButtonSelected(ActionEvent event) {
       radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
       int count = drawingArea.getChildren().size();
       
       if(count > 0) {
          drawingArea.getChildren().remove(count - 1);
       }
    }

}
