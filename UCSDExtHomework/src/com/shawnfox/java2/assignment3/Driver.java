package com.shawnfox.java2.assignment3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.*;

/**
 * 
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static final Logger log = Logger.getGlobal();

   private static Scanner keyboard = new Scanner(System.in);
   
   /**
    * 
    * @param args
    */
   public static void main(String[] args) {
      log.entering(Driver.class.getName(), "main");
      
      // TODO: 3 points - Ask the user how many employees there are.  For each employee
      // prompt for entry of all fields required to build the object.

      String lastName = "";
      String firstName = "";
      String middleName = "";
      BigDecimal hourlySalary;
      BigDecimal hoursWorked;
      
      Map<Long, Employee> employees = new HashMap<>();

      // Determine the number of employees that will be entered
      int numEmployees = getNumberOfEmployees();
      System.out.printf("You entered %s employees%n", numEmployees);
      
      Predicate<String> nameTest = (String name) -> { 
         if(name != null && !name.isEmpty()) {
            return true;
         }
         else {
            System.out.println("Invalid input! The string must be non-null and not empty");
            return false;
         }
      };
      
      Predicate<String> hourlySalaryTest = (String salary) -> { 
         boolean result = false;
         try {
            BigDecimal value = new BigDecimal(salary);
            Employee.testHourlySalary(value);
            result = true;
         }
         catch (NumberFormatException e) {
            log.severe(String.format("%s cannot be converted to BigDecimal", salary));
         }
         catch(IllegalArgumentException e) {
            log.severe(e.getMessage());
         }
         return result;
      };
      
      Predicate<String> hoursWorkedTest = (String hours) -> { 
         boolean result = false;
         try {
            BigDecimal value = new BigDecimal(hours);
            Employee.testHourlySalary(value);
            result = true;
         }
         catch (NumberFormatException e) {
            log.severe(String.format("%s cannot be converted to BigDecimal", hours));
         }
         catch(IllegalArgumentException e) {
            log.severe(e.getMessage());
         }
         return result;
      };
      
      for (int n = 0; n < numEmployees; n++) {
         // For each employee, ask for the name, hourly salary, and hours worked for the week
         
         firstName = getData(nameTest);
         lastName = getData(nameTest);
         middleName = getData(nameTest);
         
         hourlySalary = new BigDecimal(getData(hourlySalaryTest));
         hoursWorked = new BigDecimal(getData(hoursWorkedTest));
         
         Employee newEmployee = new Employee(lastName, firstName, middleName, hourlySalary, hoursWorked);
         employees.put(newEmployee.getUniqueId(), newEmployee);
      }
      
      printSalaryReport(employees);
      
      log.exiting(Driver.class.getName(), "main");
   }
   
   // TODO 6 points - create a static method printSalaryReport and build a .csv file
   
   /**
    * Iterate over all employees and write a .csv file with columns for employee info
    * and the calculated weekly pay.
    * 
    * @param <K> - type of the key used to access employees
    * @param <V> - type of the value used to store employee info
    * @param employees - container of employees
    */
   private static <K, V> void printSalaryReport(Map<K,V> employees) {
      for(V current : employees.values()) {
         System.out.println(current);
      }
   }
   
   /**
    * Ask for the number of employees until an integer >= 0 is entered.
    * 
    * @return the number of employees
    */
   private static int getNumberOfEmployees() {
      int numEmployees = 0;
      
      
      do {
         System.out.println("Enter the number of employees");
         
         if(!keyboard.hasNextInt()) {
            System.out.println("Invalid number! Please enter a number.");
         }
         else {
            numEmployees = keyboard.nextInt();
            if(numEmployees <= 0) {
               System.out.println("Please enter a number >= 0.");
            }
         }
         keyboard.nextLine();
         
      } while(numEmployees <= 0);
      
      return numEmployees;
   }
   
   private static String getData(Predicate<String> isValid) {
      String entry = "";
      
      do {
         entry = keyboard.nextLine();
      } while(!isValid.test(entry));
      
      return entry;
   }
}
