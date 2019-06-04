package com.shawnfox.java2.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

class EmployeeTests {

   @Test
   void testFirstNameOnly() throws InvalidSalaryException {
      Employee shawn = new Employee("Shawn", BigDecimal.valueOf(35), BigDecimal.valueOf(40));
      assertEquals("Shawn", shawn.getName());
      assertEquals(BigDecimal.valueOf(40), shawn.getHoursWorked());
      assertEquals( "Shawn, 35.00, 40, 1400.00", shawn.toString());
      
      shawn.setHourlySalary(BigDecimal.valueOf(50));
      assertEquals(BigDecimal.valueOf(50.00).setScale(2), shawn.getHourlySalary());
      
      shawn.setHoursWorked(BigDecimal.valueOf(43.5));
      assertEquals(BigDecimal.valueOf(43.5), shawn.getHoursWorked());
      assertEquals("Shawn, 50.00, 43.5, 2175.00", shawn.toString());
   }
   
   /*
    * @Test void testUniqueIdGeneration() throws InvalidSalaryException { Employee
    * shawn = new Employee("Shawn", BigDecimal.valueOf(33),
    * BigDecimal.valueOf(40)); Employee steve = new Employee("Steve",
    * BigDecimal.valueOf(41.75), BigDecimal.valueOf(30)); Employee mary = new
    * Employee("Mary", BigDecimal.valueOf(42), BigDecimal.valueOf(21.2)); Employee
    * ming = new Employee("Ming", BigDecimal.valueOf(35.0),
    * BigDecimal.valueOf(33.6));
    * 
    * assertNotEquals(shawn.getUniqueId(), steve.getUniqueId());
    * assertNotEquals(shawn.getUniqueId(), mary.getUniqueId());
    * assertNotEquals(shawn.getUniqueId(), ming.getUniqueId());
    * assertNotEquals(ming.getUniqueId(), steve.getUniqueId());
    * assertNotEquals(ming.getUniqueId(), mary.getUniqueId());
    * assertNotEquals(mary.getUniqueId(), steve.getUniqueId()); }
    */
   
   @Test
   void testInvalidHourlySalary() {
      assertThrows(InvalidSalaryException.class, () -> new Employee(
            "Ming", 
            BigDecimal.valueOf(10.9), BigDecimal.valueOf(33.6)));
     
   }
   
   @Test
   void testHourlySalaryRounding() {
      Employee shawn = new Employee("Shawn", BigDecimal.valueOf(33.277), BigDecimal.valueOf(40));
      assertEquals(BigDecimal.valueOf(33.28), shawn.getHourlySalary());
   }
   
   @Test
   void testInvalidName() {
      assertThrows(IllegalArgumentException.class, () -> Employee.testName("Steve1"));
      assertThrows(IllegalArgumentException.class, () -> Employee.testName("1Steve1"));
      assertThrows(IllegalArgumentException.class, () -> Employee.testName("Ste2ve"));
      Employee.testName("Steve");
      Employee.testName("Shawn Fox");
      Employee.testName("Robert C Fox Jr");
      assertThrows(IllegalArgumentException.class, () -> Employee.testName("   "));
   }
}
