
package com.shawndfox.numberconverter;

/**
 * A class that converts Hexadecimal numbers to decimal or binary.
 * 
 * @author Shawn Fox
 */
public class FromHexadecimal {
   
   /**
    * Validate that the input argument is a valid hexadecimal number
    * @param input - a string that represents a hexadecimal number to be validated
    * @return true if valid, false if invalid
    */
   public static boolean validate(String input) {
      return input.matches("^[a-fA-F0-9]*$");
   }
   
   /**
    * Convert a hexadecimal number into a decimal number.
    * 
    * @param hexadecimalNumber - the hexadecimal number (e.g., ABCDEF01) as a String
    * @return decimal number as a String
    */
   public static String toDecimal(String hexadecimalNumber) {
      char[] hexDigitsArray = hexadecimalNumber.toCharArray();
      int currentPower = hexadecimalNumber.length() - 1;
      int sum = 0;
      int currentDigit = 0;
      
      for ( char current : hexDigitsArray) {
         if(current >= 'A' && current <= 'F') {
            currentDigit = getDecimalDigit(current);
         }
         else {
            currentDigit = Character.getNumericValue(current);
         }
         sum += currentDigit * Math.pow(16, currentPower);
         --currentPower;
      }
      
      return Integer.toString(sum);
   }
   
   /**
    * Convert a hexadecimal number into a binary number.
    * 
    * @param hexadecimalNumber - the hexadecimal number (e.g., ABCDEF01) as a String
    * @return decimal number as a String
    */
   public static String toBinary(String hexadecimalNumber) {
      return FromDecimal.toBinary(toDecimal(hexadecimalNumber));
   }
   
   /**
    * Convert the non-numeric hexadecimal digit into an int
    * 
    * @param value - the non-numeric hexadecimal character (A-F)
    * @return the decimal equivalent of A-F (10-15 respectively)
    */
   private static int getDecimalDigit(char value) {
      int result = 0;
      
      switch(value) {
         case 'A': 
            result = 10;
            break;
         case 'B': 
            result = 11;
            break;
         case 'C': 
            result = 12;
            break;
         case 'D': 
            result = 13;
            break;
         case 'E': 
            result = 14;
            break;
         case 'F': 
            result = 15;
            break;
      }
      
      return result;
   }
}
