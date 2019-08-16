/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shawn D. Fox
 */
public class TipCalculatorController implements Initializable
{
   private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent = NumberFormat.getPercentInstance();
   private BigDecimal tipPercentage = new BigDecimal(0.15);

   @FXML
   private TextField amountTextField;
   @FXML
   private TextField tipTextField;
   @FXML
   private TextField totalTextField;
   @FXML
   private Slider tipPercentageSlider;
   @FXML
   private Label tipPercentageLabel;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
      currency.setRoundingMode(RoundingMode.HALF_UP);
      
      tipPercentageSlider.valueProperty().addListener(
         (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
            String formatted = percent.format(tipPercentage);
            tipPercentageLabel.setText(formatted);
         });
   }

   @FXML
   private void calculateButtonPressed(ActionEvent event)
   {
      try {
         BigDecimal amount = new BigDecimal(amountTextField.getText());
         BigDecimal tip = amount.multiply(tipPercentage);
         BigDecimal total = amount.add(tip);

         tipTextField.setText(currency.format(tip));
         totalTextField.setText(currency.format(total));
      }
      catch (NumberFormatException ex) {
         amountTextField.setText("Enter amount");
         amountTextField.selectAll();
         amountTextField.requestFocus();
      }
   }
}
