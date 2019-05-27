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
   private String lastName = "";
   private String firstName = "";
   private String middleName = "";
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
    * @exception IllegalArgumentException
    * @param lastName      must be a non-empty string
    * @param firstName     must be a non-empty string
    * @param middleName    can be an empty string if there is no middle name for the employee 
    * @param hourlySalary  must be greater than 0
    * @param hoursWorked   must be greater than 0
    */
   public Employee(String lastName, String firstName, String middleName, BigDecimal hourlySalary, BigDecimal hoursWorked) {
      super();
      testHourlySalary(hourlySalary);
      testHoursWorked(hoursWorked);
      testName(lastName);
      testName(firstName);
      
      this.lastName = lastName;
      this.firstName = firstName;
      this.middleName = middleName;
      this.hourlySalary = hourlySalary;
      this.hoursWorked = hoursWorked;
      this.uniqueId = generateEmployeeId();
   }
   
   /**
    * Parameterized constructor.
    * 
    * @exception IllegalArgumentException
    * @param firstName     must be a non-empty string
    * @param hourlySalary  must be greater than 0
    * @param hoursWorked   must be greater than 0
    */
   public Employee(String firstName, BigDecimal hourlySalary, BigDecimal hoursWorked) {
      super();
      testHourlySalary(hourlySalary);
      testHoursWorked(hoursWorked);
      testName(firstName);
      
      this.firstName = firstName;
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
    */
   public static void testHourlySalary(BigDecimal hourlySalary) {
      if(hourlySalary.compareTo(MINIMUM_WAGE) < 0)
         throw new IllegalArgumentException(String.format("The hourly salary must exceed minimum wage %s", MINIMUM_WAGE));
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
      return String.format("%s, %s, %s, %s", firstName, hourlySalary, hoursWorked, hourlySalary.multiply(hoursWorked));
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
    * Get the employee's last name.
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * Set the employee's last name.
    * @exception IllegalArgumentException
    * 
    * @param lastName
    */
   public void setLastName(String lastName) {
      testName(lastName);
      this.lastName = lastName;
   }

   /**
    * Get the employee's first name.
    * 
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * Set the employee's first name.
    * @exception IllegalArgumentException
    * 
    * @param firstName
    */
   public void setFirstName(String firstName) {
      testName(firstName);
      this.firstName = firstName;
   }

   /**
    * Get the employee's middle name.
    * 
    * @return the middleName
    */
   public String getMiddleName() {
      return middleName;
   }

   /**
    * Set the middle name of the employee.  If there is none, use an empty string.
    * 
    * @param middleName the middleName to set
    */
   public void setMiddleName(String middleName) {
      if(middleName == null)
         middleName = "";
      
      this.middleName = middleName;
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
    * @exception IllegalArgumentException
    * @param hourlySalary the hourlySalary to set
    */
   public void setHourlySalary(BigDecimal hourlySalary) {
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
