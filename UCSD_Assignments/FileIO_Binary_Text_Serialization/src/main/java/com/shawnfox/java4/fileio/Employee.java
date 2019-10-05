package com.shawnfox.java4.fileio;

import java.io.Serializable;
import java.time.*;

/**
 * A class that contains the fields and method for an employee record.
 * 
 * @author Shawn D. Fox
 *
 */
public class Employee implements Serializable
{
   private String name;
   private double salary;
   private LocalDate hireDay;

   /**
    * 
    * @param name full name of the employee
    * @param salary the annual salary
    * @param year  4 digit year
    * @param month 2 digit month
    * @param day 2 digit day of month
    */
   public Employee(String name, double salary, int year, int month, int day)
   {
      this.name = name;
      this.salary = salary;
      hireDay = LocalDate.of(year, month, day);
   }

   /**
    * 
    * @param name full name of the employee
    * @param salary the annual salary
    * @param hireDay
    */
   public Employee(String name, Double salary, LocalDate hireDay) {
      this(name, salary, hireDay.getYear(), hireDay.getMonthValue(), hireDay.getDayOfMonth());
   }

   /**
    * Getter for the employee name.
    * @return String
    */
   public String getName()
   {
      return name;
   }

   /**
    * Getter for the employee salary
    * @return double
    */
   public double getSalary()
   {
      return salary;
   }

   /**
    * Getter for the hire date of the employee
    * @return LocalDate
    */
   public LocalDate getHireDay()
   {
      return hireDay;
   }

   /**
    * Raises the salary of the employee by a percentage of the current salary.
    * @param byPercent
    */
   public void raiseSalary(double byPercent)
   {
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   /**
    * return a string representation of the object
    * @return String
    */
   public String toString()
   {
      return getClass().getName() 
         + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
   }
}
