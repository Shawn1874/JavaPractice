package com.shawndfox.colorchooser;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorChooserController implements Initializable {

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Slider alphaSlider;

    @FXML
    private RestrictiveTextField redTextField;

    @FXML
    private RestrictiveTextField greenTextField;

    @FXML
    private RestrictiveTextField blueTextField;

    @FXML
    private RestrictiveTextField alphaTextField;
    
    @FXML
    private Rectangle colorRectangle;
    
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Bind text fields to the corresponding slider
       NumberFormat nf;
       nf = NumberFormat.getIntegerInstance();
       redTextField.textProperty().bindBidirectional(redSlider.valueProperty(), nf);
       greenTextField.textProperty().bindBidirectional(greenSlider.valueProperty(), nf);
       blueTextField.textProperty().bindBidirectional(blueSlider.valueProperty(), nf);
       NumberFormat df = NumberFormat.getNumberInstance();
       df.setMaximumFractionDigits(2);
       alphaTextField.textProperty().bindBidirectional(alphaSlider.valueProperty(), 
                                                       df);
       
       // Listeners that set Rectangle's fill based on slider changes
       redSlider.valueProperty().addListener(
         (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            red = newValue.intValue();
            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
         });
       
       greenSlider.valueProperty().addListener(
         (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            green = newValue.intValue();
            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
         });
       
       blueSlider.valueProperty().addListener(
         (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            blue = newValue.intValue();
            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
         });
       
       alphaSlider.valueProperty().addListener(
         (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            alpha = newValue.doubleValue();
            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
         });
    }
}
