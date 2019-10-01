package com.shawnfox.java4.fileio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EmployeeTest {

   private Employee casey = new Employee("Casey Smith", 125000, 2018, 9, 1);
   
   @Test
   void testEmployeeMethods() {
      assertEquals(casey.getName(), "Casey Smith");
      assertEquals(casey.getSalary(), 125000);
      assertEquals(casey.getHireDay(), LocalDate.of(2018, 9, 1));
      
      casey.raiseSalary(10);
      assertEquals(casey.getSalary(), 137500);
   }
}
