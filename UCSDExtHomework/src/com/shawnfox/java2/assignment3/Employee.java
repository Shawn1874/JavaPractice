/**
 * 
 */
package com.shawnfox.java2.assignment3;

import java.math.BigDecimal;

/**
 * @author Shawn D. Fox
 *
 */
public class Employee {
   public static BigDecimal MINIMUM_WAGE = new BigDecimal("11.0"); 
   private static long nextId = 100_000;
   private String name = "";
   private BigDecimal hourlySalary;
   private BigDecimal hoursWorked;
   private long uniqueId;

   
   /**
    * Get the next available id for a new employee
    */
   private static long generateEmployeeId() {
      return nextId++;
   }
   
   /**
    * Parameterized constructor.
    * 
    * @param name     must be a non-empty string
    * @param hourlySalary  must be greater than 0
    * @param hoursWorked   must be greater than 0
    */
   public Employee(String name, BigDecimal hourlySalary, BigDecimal hoursWorked) {
      super();
      testHourlySalary(hourlySalary);
      testHoursWorked(hoursWorked);
      testName(name);
      
      this.name = name;
      this.hourlySalary = hourlySalary;
      this.hoursWorked = hoursWorked;
      this.uniqueId = generateEmployeeId();
   }
   
   /**
    * Validates that hoursWorked parameter is >= 0
    * 
    * @param hoursWorked
    */
   public static void testHoursWorked(BigDecimal hoursWorked) {
      if(hoursWorked.compareTo(BigDecimal.ZERO) < 0)
         throw new IllegalArgumentException("The hours worked must be greater than 0!");
   }
   
   /**
    * Validates that hourlySalary >= MINIMUM_WAGE
    * @param hourlySalary
    * @throws InvalidSalaryException 
    */
   public static void testHourlySalary(BigDecimal hourlySalary) throws InvalidSalaryException {
      if(hourlySalary.compareTo(MINIMUM_WAGE) < 0)
         throw new InvalidSalaryException(
               String.format("The hourly salary must exceed minimum wage %s", MINIMUM_WAGE),
               MINIMUM_WAGE);
   }
   
   /**
    * Validates that the name is neither empty nor null.
    * 
    * @param name - the first or last name of employee to test
    */
   public static void testName(String name) {
      if(name == null || name.isEmpty()) 
         throw new IllegalArgumentException("A first or last name cannot be null or empty!");
   }
   
   /**
    * Generate a string representation of an employee object.  Returns a comma separated list as 
    * firstName, hourlySalary, hoursWorked, hourlySalary*hoursWorked
    */
   @Override
   public String toString() {
      return String.format("%s, %s, %s, %s", name, hourlySalary, hoursWorked, hourlySalary.multiply(hoursWorked));
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
    * Set the employee's first name.
    * @exception IllegalArgumentException
    * 
    * @param name 
    */
   public void setFirstName(String name) {
      testName(name);
      this.name = name;
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
    * @param hourlySalary the hourlySalary to set
    * @throws InvalidSalaryException 
    */
   public void setHourlySalary(BigDecimal hourlySalary) throws InvalidSalaryException {
      testHourlySalary(hourlySalary);
      this.hourlySalary = hourlySalary;
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
    * @param hoursWorked the hoursWorked to set
    */
   public void setHoursWorked(BigDecimal hoursWorked) {
      testHoursWorked(hoursWorked);
      this.hoursWorked = hoursWorked;
   }

   /**
    * Get the employee id
    * 
    * @return uniqueId
    */
   public long getUniqueId() {
      return uniqueId;
   }
}
