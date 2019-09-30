/**
 * 
 */
package com.shawnfox.TextFileIO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * An abstraction that represents the data for an employee.
 * 
 * @author Shawn D. Fox
 *
 */
public class Employee {
   public static BigDecimal MINIMUM_WAGE = new BigDecimal("11.0"); 
   private static int wageScale = 2;
   private String name = "";
   private BigDecimal hourlySalary = MINIMUM_WAGE;
   private BigDecimal hoursWorked = BigDecimal.ZERO;
   
   public Employee() {
   }
   
   /**
    * Parameterized constructor which takes 3 parameters, and sets the Decimal used for the hourlySalary
    * to the default scale of 2 decimal places.  The name string is trimmed of leading and trailing
    * whitespace before it is saved.
    * 
    * @param name must be a non-empty string with at least one non-numeric character
    * @param hourlySalary The hourly salary of the employee which must be greater than MINIMUM_WAGE
    * @param hoursWorked The hours worked by the employee for the week which must be greater than or equal to 0
    */
   public Employee(String name, BigDecimal hourlySalary, BigDecimal hoursWorked) {
      super();
      testHourlySalary(hourlySalary);
      testHoursWorked(hoursWorked);
      testName(name);
      this.name = name.trim();
      this.hourlySalary = hourlySalary.setScale(wageScale, RoundingMode.HALF_UP);
      this.hoursWorked = hoursWorked;
   }
   
   /**
    * Validates the value of hoursWorked
    * 
    * @param hoursWorked The hours worked by the employee for the week which must be greater than or equal to 0
    */
   public static void testHoursWorked(BigDecimal hoursWorked) {
      if(hoursWorked.compareTo(BigDecimal.ZERO) < 0)
         throw new IllegalArgumentException("The hours worked must be greater than 0!");
   }
   
   /**
    * Validates the value of hourlySalary
    * @param hourlySalary The hourly salary of the employee which must be greater than MINIMUM_WAGE
    * @throws InvalidSalaryException 
    */
   public static void testHourlySalary(BigDecimal hourlySalary) throws InvalidSalaryException {
      if(hourlySalary.compareTo(MINIMUM_WAGE) < 0) {
         throw new InvalidSalaryException(
               String.format("The hourly salary must exceed minimum wage %s", MINIMUM_WAGE),
               MINIMUM_WAGE);
      }
   }
   
   /**
    * Validates that the name is not null, contains at least one
    * non-whitespace character, and contains only whitespace and
    * alphabetic characters a-z or A-Z
    * 
    * @param name the name of employee to test
    */
   public static void testName(String name) {
      if(name == null  || !name.matches("^[\\s]*[a-zA-Z]+[a-zA-Z\\s]*$")) {
         String msg = "A name cannot be null, and can only contain letters and whitespace.";
         throw new IllegalArgumentException(msg);
      }
   }
   
   /**
    * Generate a string representation of an employee object.  Returns a comma separated list as 
    * firstName, hourlySalary, hoursWorked, hourlySalary*hoursWorked
    */
   @Override
   public String toString() {
      return String.format("%s, %s, %s, %s", name, hourlySalary, hoursWorked, 
            hourlySalary.multiply(hoursWorked).setScale(2, RoundingMode.HALF_UP));
   }
   
   /**
    * Generate a comma separated string of column names that can be used in combination with toString
    * 
    * @return String
    */
   public static String getColumnHeaders() {
      return "Employee Name, Salary, Hours, Weekly Pay";
   }

   /**
    * Get the employee's first name.
    * 
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * Set the employee's name, after trimming whitespace. The name string is trimmed of
    *  leading and trailing whitespace before it is saved.
    * 
    * @param name the name of the employee
    */
   public void setName(String name) {
      testName(name);
      this.name = name.trim();
   }

   /**
    * Get the hourly salary of the employee
    * 
    * @return the hourlySalary
    */
   public BigDecimal getHourlySalary() {
      return hourlySalary;
   }

   /**
    * Set the new hourly salary.
    * 
    * @param hourlySalary The hourly salary of the employee which must be greater than MINIMUM_WAGE
    * @exception InvalidSalaryException 
    */
   public void setHourlySalary(BigDecimal hourlySalary) throws InvalidSalaryException {
      testHourlySalary(hourlySalary);
      this.hourlySalary = hourlySalary.setScale(wageScale, RoundingMode.HALF_UP);
   }

   /**
    * Get the number of hours worked by the employee during the last 
    * pay period.
    * 
    * @return hoursWorked
    */
   public BigDecimal getHoursWorked() {
      return hoursWorked;
   }

   /**
    * Set the number of hours worked.
    * 
    * @exception IllegalArgumentException
    * @param hoursWorked The hours worked by the employee for the week which must be greater than or equal to 0
    */
   public void setHoursWorked(BigDecimal hoursWorked) {
      testHoursWorked(hoursWorked);
      this.hoursWorked = hoursWorked;
   }
}
