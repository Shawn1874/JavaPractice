/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.numberconverter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit test cases for the the FromBinary class.
 * 
 * @author Shawn Fox
 */
public class FromBinaryTest {
   
   public FromBinaryTest() {
   }
   
   @Test
   public void testValidate() {
      assertTrue(FromBinary.validate("0010"));
      assertTrue(FromBinary.validate("101"));
      assertTrue(FromBinary.validate("11110100"));
      assertFalse(FromBinary.validate("ab10"));
      assertFalse(FromBinary.validate("-&10"));
   }

   @Test
   public void testToDecimal() {
      assertEquals("8", FromBinary.toDecimal("1000"));
      assertEquals("10", FromBinary.toDecimal("1010"));
      assertEquals("57", FromBinary.toDecimal("111001"));
      assertEquals("49360", FromBinary.toDecimal("1100000011010000"));
   }
   
   @Test
   public void testToHexadecimal() {
      assertEquals("F", FromBinary.toHexaDecimal("1111"));
      assertEquals("A", FromBinary.toHexaDecimal("1010"));
      assertEquals("C0D0", FromBinary.toHexaDecimal("1100000011010000"));
   }
}
