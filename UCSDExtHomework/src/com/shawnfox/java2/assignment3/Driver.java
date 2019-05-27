package com.shawnfox.java2.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

   private static final Logger log = Logger.getLogger(Driver.class.getName());

   private static Scanner keyboard = new Scanner(System.in);
   
   private static String employeesFileName = "employees.csv";
   
   private static Predicate<String> nameTest = (String name) -> { 
      if(name != null && !name.isEmpty()) {
         return true;
      }
      else {
         log.log(Level.SEVERE, "Invalid input! The string must be non-null and not empty");
         return false;
      }
   };
   
   private static Predicate<String> hourlySalaryTest = (String salary) -> { 
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
   
   private static Predicate<String> hoursWorkedTest = (String hours) -> { 
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
   
   /**
    * This is the entry point for the application.
    * 
    * @param args - no arguments are currently supported
    */
   public static void main(String[] args) {
      final InputStream inputStream = Driver.class.getResourceAsStream("logging.properties");
      try
      {
          LogManager.getLogManager().readConfiguration(inputStream);
      }
      catch (final IOException e)
      {
          Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
          Logger.getAnonymousLogger().severe(e.getMessage());
      }
      
      log.setLevel(Level.FINEST);
      
      try {
         log.addHandler(new FileHandler("Assignment3.log"));
      } catch (SecurityException e) {
         log.log(Level.SEVERE, 
               "Couldn't add the File Handler.  Check the file name and directory permissions.", 
               e);
      } catch (IOException e) {
         log.log(Level.SEVERE, 
               "Couldn't add the File Handler.  Check the file name and directory permissions.", 
               e);
      }
      
      log.entering(Driver.class.getName(), "main");

      String firstName = "";
      BigDecimal hourlySalary;
      BigDecimal hoursWorked;
      
      Map<Long, Employee> employees = new HashMap<>();

      // Determine the number of employees that will be entered
      int numEmployees = getNumberOfEmployees();
      System.out.printf("You entered %s employees%n", numEmployees);
      
      
      for (int n = 0; n < numEmployees; n++) {
         // For each employee, ask for the name, hourly salary, and hours worked for the week
         System.out.println("Enter the information for employee " + (n + 1));
         firstName = getData("Enter the first name of the employee.", nameTest);
         hourlySalary = new BigDecimal(getData("Enter the hourly salary of the employee.", hourlySalaryTest));
         hoursWorked = new BigDecimal(getData("Enter the hours that the employee worked this week.", hoursWorkedTest));
         
         Employee newEmployee = new Employee(firstName, hourlySalary, hoursWorked);
         employees.put(newEmployee.getUniqueId(), newEmployee);
         log.log(Level.INFO, "Added employee" + newEmployee.getFirstName());
      }
      
      log.log(Level.INFO, "Printing the salary report.");
      printSalaryReport(employees);
      
      log.exiting(Driver.class.getName(), "main");
   }
   
   /**
    * Iterate over all employees and write a .csv file with columns for employee info
    * and the calculated weekly pay.
    * 
    * @param employees - container of employees
    */
   private static void printSalaryReport(Map<Long, Employee> employees) {

      log.entering(Driver.class.getName(), "printSalaryReport");
      
      try(FileWriter employeesFile = new FileWriter(employeesFileName);
          BufferedWriter writer = new BufferedWriter(employeesFile)) {
         writer.write(Employee.getColumnHeaders());
         writer.newLine();
         
         for(Employee current : employees.values()) {
            writer.write(current.toString());
            writer.newLine();
         }
      } catch (IOException e) {
         log.severe(e.getMessage());
      }
      
      log.exiting(Driver.class.getName(), "printSalaryReport");
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
   
   /**
    * Get data from the console entered by the user, and return the data as a string. Loop 
    * continuously until the user enters data that is valid according to the supplied predicate.
    * 
    * @param msg - A message to print at the start of each loop.
    * @param isValid - a predicate implementation that tests a string and returns a boolean indicating the validity
    * @return the String validated by the predicate
    */
   private static String getData(String msg, Predicate<String> isValid) {
      String entry = "";
      
      do {
         System.out.println(msg);
         entry = keyboard.nextLine();
      } while(!isValid.test(entry));
      
      return entry;
   }
}
