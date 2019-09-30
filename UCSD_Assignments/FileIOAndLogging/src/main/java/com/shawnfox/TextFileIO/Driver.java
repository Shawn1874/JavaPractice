package com.shawnfox.TextFileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.*;
import javax.xml.bind.JAXB;

/**
 * The Driver class contains the entry point of the application which prompts the user for
 * entry of employee information, builds a container of employee objects, and writes
 * the data to a file named employees.csv.
 *  
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static final Logger log = Logger.getLogger(Driver.class.getName());
   private static Scanner keyboard = new Scanner(System.in);
   private static String employeesFileName = "employees";
   
   /**
    * The nameTest Predicate validates that the name entered is not null and 
    * valid according to the Employee.test
    */
   private static IsValid<String> nameTest = (String name) -> { 
      boolean result = false;
      
      try {
         Employee.testName(name);
         result = true;
      } catch (IllegalArgumentException e) {
         log.log(Level.SEVERE, 
                 "Invalid input! The string must be non-null, and contain whitespace, or the laters a-z or A-Z");
      }
      
      return result;
   };
   
   /**
    * The hourlySalaryTest Predicate validates that the salary value is 
    * Convertible into a BigDecimal object, and that it satisfies the 
    * requirements of the Employee class using its testHourlySalary method.
    */
   private static IsValid<String> hourlySalaryTest = (String salary) -> { 
      boolean result = false;
      try {
         BigDecimal value = new BigDecimal(salary);
         Employee.testHourlySalary(value);
         result = true;
      }
      catch (NumberFormatException e) {
         log.severe(String.format("%s cannot be converted to BigDecimal", salary));
      }
      catch(InvalidSalaryException e) {
         log.severe(e.getMessage());
      }
      return result;
   };
   
   /**
    * The hourlySalaryTest Predicate validates that the salary value is 
    * Convertible into a BigDecimal object, and that it satisfies the 
    * requirements of the Employee class using its testHoursWorked method.
    */
   private static IsValid<String> hoursWorkedTest = (String hours) -> { 
      boolean result = false;
      try {
         BigDecimal value = new BigDecimal(hours);
         Employee.testHoursWorked(value);
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
    * This is the entry point for the application.  It loads the properties for the logger that will be used,
    * prompts for the number of employee objects that will be created, prompts for the information for each
    * new employee, and prints the employees to a .csv file.
    * 
    * @param args no arguments are currently supported
    */
   public static void main(String[] args) {
      Employees allEmployees;
      try (BufferedReader input = Files.newBufferedReader(Paths.get(employeesFileName + ".xml"))) {
         allEmployees = JAXB.unmarshal(input, Employees.class);
      } 
      catch (Exception e1) {
         allEmployees = new Employees();
         log.info("Existing employee file doesn't exist.");
      }
      
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

      String name = "";
      BigDecimal hourlySalary;
      BigDecimal hoursWorked;

      // Determine the number of employees that will be entered
      int numEmployees = getNumberOfEmployees();
      System.out.printf("You entered %s employees%n", numEmployees);
      
      
      for (int n = 0; n < numEmployees; n++) {
         // For each employee, ask for the name, hourly salary, and hours worked for the week
         System.out.println("Enter the information for employee " + (n + 1));
         name = getData("Enter the first name of the employee.", nameTest);
         hourlySalary = new BigDecimal(getData("Enter the hourly salary of the employee.", hourlySalaryTest));
         hoursWorked = new BigDecimal(getData("Enter the hours that the employee worked this week.", hoursWorkedTest));
         
         Employee newEmployee = new Employee(name, hourlySalary, hoursWorked);
         allEmployees.getEmployees().add(newEmployee);
         log.log(Level.INFO, "Added employee : " + newEmployee );
      }
      
      log.log(Level.INFO, "Printing the salary report.");
      printSalaryReport(allEmployees);
      printSalaryReportXml(allEmployees);
      
      log.exiting(Driver.class.getName(), "main");
   }
   
   /**
    * Iterate over all employees and write a .csv file with columns for employee info
    * and the calculated weekly pay.
    * 
    * @param employees container of employees
    */
   private static void printSalaryReport(Employees employees) {

      log.entering(Driver.class.getName(), "printSalaryReport");
      String fileName = employeesFileName + ".csv";
      
      try(FileWriter employeesFile = new FileWriter(fileName);
          BufferedWriter writer = new BufferedWriter(employeesFile)) {
         writer.write(Employee.getColumnHeaders());
         writer.newLine();
         
         for(Employee current : employees.getEmployees()) {
            writer.write(current.toString());
            writer.newLine();
         }
      } catch (IOException e) {
         log.severe(e.getMessage());
      }
      
      log.exiting(Driver.class.getName(), "printSalaryReport");
   }
   
   /**
    * Iterate over all employees and write a all employee data to an .xml file 
    * 
    * @param employees container of employees
    */
   private static void printSalaryReportXml(Employees employees) {

      log.entering(Driver.class.getName(), "printSalaryReportXml");
      String fileName = employeesFileName + ".xml";
      
      try(FileWriter employeesFile = new FileWriter(fileName);
         BufferedWriter writer = new BufferedWriter(employeesFile)) {
         JAXB.marshal(employees, writer);
      } catch (IOException e) {
         log.severe(e.getMessage());
      }
      
      log.exiting(Driver.class.getName(), "printSalaryReportXml");
   }
   
   /**
    * Ask for the number of employees until an integer greater than or equal to 0 is entered.
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
    * @param msg A message to print at the start of each loop.
    * @param isValid a predicate implementation that tests a string and returns a boolean indicating the validity
    * @return the String validated by the predicate
    */
   private static String getData(String msg, IsValid<String> isValid) {
      String entry = "";
      
      do {
         System.out.println(msg);
         entry = keyboard.nextLine();
      } while(!isValid.test(entry));
      
      return entry;
   }
}
