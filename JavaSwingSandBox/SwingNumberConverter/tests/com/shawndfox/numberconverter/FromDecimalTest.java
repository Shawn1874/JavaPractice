/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.numberconverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test cases for the the FromDecimal class.
 * 
 * @author Shawn Fox
 */
public class FromDecimalTest {
   
   public FromDecimalTest() {
   }
   
   @Test
   public void testValidate() {
      assertTrue(FromDecimal.validate("25"));
      assertTrue(FromDecimal.validate("4500"));
      assertFalse(FromDecimal.validate("ab25"));
      assertFalse(FromDecimal.validate("-25"));
   }
   
   @Test
   public void testToBinary() {
      assertEquals("1101", FromDecimal.toBinary("13"));
      assertEquals("10101110", FromDecimal.toBinary("174"));
      assertEquals("11111111", FromDecimal.toBinary("255"));
   }
   
   @Test
   public void testToHexadecimal() {
      assertEquals("F", FromDecimal.toHexadecimal("15"));
      assertEquals("8B2F", FromDecimal.toHexadecimal("35631"));
      assertEquals("1D8A", FromDecimal.toHexadecimal("7562"));
   }
}
