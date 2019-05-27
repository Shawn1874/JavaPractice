/**
 * 
 */
package com.shawnfox.java2.assignment3;

import java.math.BigDecimal;

/**
 * InvalidSalaryException should be thrown in a data entry use case when the program
 * can raise an alert and prompt for entry of correct information.  The minimum salary 
 * is not specified by this class.  Instead this class can be used to convey 
 * information that data entered was not valid, and the entity that throws must
 * specify what a valid value is.  For instance, the minimum salary of an employee
 * could vary depending on the country and state.
 * 
 * @author Shawn D. Fox
 *
 */
public class InvalidSalaryException extends IllegalArgumentException {

   private BigDecimal MINIMUM_SALARY;
   
   /**
    * This two parameter constructor can be used to indicate a message and what the 
    * minimum salary value was.
    * 
    * @param message clarifies the context in which the exception occurred
    * @param minimumSalary decimal value representing the minimum salary
    */
   InvalidSalaryException(String message, BigDecimal minimumSalary) {
      super(message);
      MINIMUM_SALARY = minimumSalary;
   }
   

   /**
    * This three parameter constructor can be used to indicate a message and what the 
    * minimum salary value was.
    * 
    * @param message Clarifies the context in which the exception occurred
    * @param minimumSalary Decimal value representing the minimum salary
    * @param cause The exception that was the root cause
    */
   InvalidSalaryException(String message, BigDecimal minimumSalary, Throwable cause) {
      super(message, cause);
      MINIMUM_SALARY = minimumSalary;
   }
   
   /**
    * Getter for the minimum salary specified when an object was constructed.
    * 
    * @return minimum salary
    */
   public BigDecimal getMinimumSalary() {
      return this.MINIMUM_SALARY;
   }
}
