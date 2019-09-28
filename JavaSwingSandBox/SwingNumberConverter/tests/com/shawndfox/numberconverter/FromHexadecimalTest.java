/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.numberconverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test cases for the the FromHexadecimal class.
 * 
 * @author Shawn Fox
 */
public class FromHexadecimalTest {
   
   public FromHexadecimalTest() {
   }
   
   @Test
   public void testValidate() {
      assertTrue(FromHexadecimal.validate("FEDCBA01"));
      assertTrue(FromHexadecimal.validate(""));
      assertTrue(FromHexadecimal.validate("9876543210"));
      assertTrue(FromHexadecimal.validate("a"));
      assertFalse(FromHexadecimal.validate("-a"));
      assertFalse(FromHexadecimal.validate("vab"));
   }
   
   @Test
   public void testToBinary() {
      assertEquals("1111", FromHexadecimal.toBinary("F"));
      assertEquals("1111000000011111", FromHexadecimal.toBinary("F01F"));
      assertEquals("1111110000111111010101111001101", FromHexadecimal.toBinary("7E1FABCD"));
   }
   
   @Test
   public void testToHexadecimal() {
      assertEquals("15", FromHexadecimal.toDecimal("F"));
      assertEquals("61471", FromHexadecimal.toDecimal("F01F"));
      assertEquals("2116004813", FromHexadecimal.toDecimal("7E1FABCD"));
   }
}
