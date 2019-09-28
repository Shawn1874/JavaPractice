
package com.shawndfox.numberconverter;

/**
 * A class that converts decimal numbers to hexadecimal or binary.
 * 
 * @author Shawn Fox
 */
public class FromDecimal {
   
   /**
    * Validate that the input argument is a valid decimal number
    * @param input - a string that represents a hexadecimal number to be validated
    * @return true if valid, false if invalid
    */
   public static boolean validate(String input) {
      return input.matches("^[0-9]*$");
   }
      
   /**
    * Convert a decimal number into a binary number.
    * 
    * @param decimalNumber - the decimal number as a String
    * @return binary number as a String
    */
   public static String toBinary(String decimalNumber) {
      StringBuilder result = new StringBuilder();
      final int base = 2;
      int input = Integer.parseInt(decimalNumber);
      
      int quotient = 0;
      int remainder = 0;
      
      do {
         quotient = input / base;
         remainder = input % base;
         input = quotient;
         result.insert(0, remainder);
      } while (quotient > 0);
      
      return result.toString();
   }
   
   /**
    * Convert a decimal number into a hexadecimal number.
    * 
    * @param decimalNumber - the decimal number as a String
    * @return hexadecimal number as a String
    */
   public static String toHexadecimal(String decimalNumber) {
      StringBuilder result = new StringBuilder();
      final int base = 16;
      int input = Integer.parseInt(decimalNumber);
      
      int quotient = 0;
      int remainder = 0;
      
      do {
         quotient = input / base;
         remainder = input % base;
         input = quotient;
         if(remainder >= 10) {
            result.insert(0, getLetter(remainder));
         }
         else {
            result.insert(0, remainder);
         }
      } while (quotient > 0);
      
      return result.toString();
   }
   
   
   /**
    * Convert the numeric decimal digit 10-14 into the equivalent hexadecimal
    * character A-F respectively.
    * 
    * @param value - the numeric decimal character 10-15
    * @return the hexadecimal equivalent of 10-15 or an empty string
    */
   private static String getLetter(int value) {
      String result = "";
      
      switch(value) {
         case 10: 
            result = "A";
            break;
         case 11: 
            result = "B";
            break;
         case 12: 
            result = "C";
            break;
         case 13: 
            result = "D";
            break;
         case 14: 
            result = "E";
            break;
         case 15: 
            result = "F";
            break;
      }
      
      return result;
   }
}
