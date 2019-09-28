
package com.shawndfox.numberconverter;

/**
 * A class that converts binary numbers to hexadecimal or decimal.
 * 
 * @author Shawn Fox
 */
public class FromBinary {
   
   /**
    * Validate that the input argument is a valid decimal number
    * @param input - a string that represents a hexadecimal number to be validated
    * @return true if valid, false if invalid
    */
   public static boolean validate(String input) {
      return input.matches("^[0-1]*$");
   }
      
   /**
   * Converts a binary number to decimal.
   * 
    * @param binaryNumber - the binary number as a String
    * @return the decimal number as a string
    */
   public static String toDecimal(String binaryNumber) {
      char[] binaryDigitsArray = binaryNumber.toCharArray();
      int currentPower = binaryNumber.length() - 1;
      int currentDigit = 0;
      int sum = 0;
      
      for ( char current : binaryDigitsArray) {
         if(current == '1') {
            currentDigit = Character.getNumericValue(current);
            sum += Math.pow(2, currentPower);
         }
         --currentPower;
      }
      
      return Integer.toString(sum);
   }
   
  /**
   * Converts a binary number to hexadecimal.
   * 
   * @param binaryNumber - the binary number as a String
   * @return the hexadecimal number as a String
   */
   public static String toHexaDecimal(String binaryNumber) {
      return FromDecimal.toHexadecimal(toDecimal(binaryNumber));
   }
}
