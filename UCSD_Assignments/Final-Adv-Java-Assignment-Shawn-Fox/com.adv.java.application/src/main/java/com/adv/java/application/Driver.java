/**
 * 
 */
package com.adv.java.application;

import com.adv.java.xml.Employee;
import com.adv.java.xml.EmployeeReader;
import com.adv.java.xml.Employees;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.adv.java.iostream.*;
import com.adv.java.streams.EmployeeStatisticsGenerator;

/**
 * This class is a test driver for an application that demonstrates the following concepts
 * each which is defined within a separate module.
 * 1) XML serialization via JAXB, and also using buffered reader and writer objects.
 * 2) IOStream classes used for processing user inputs, reading and writing files.
 * 3) java.util.stream for filtering and reduction operations
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static String employeesFileName = "employees.xml";
   
   /**
    * This the entry point of the application.
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Reading employees using JAXB to deserialize the data.");
      var employeeReader = new EmployeeReader();
      Employees employees = employeeReader.readEmployees(employeesFileName);
      System.out.printf("%d employee(s) read%n", employees.getEmployees().size());
      
      // Read in the new employees which demonstrates the use of a scanner
     var employeeEntry = new EmployeeDataEntry();
     var newEmployees = employeeEntry.readNewEmployees();
     System.out.printf("%d new employee(s) read%n", newEmployees.size());
     employees.getEmployees().addAll(newEmployees);
     
     // Generate statistics using streams and reduction operations, and print the
     // information to the console.
     var listOfEmployees = employees.getEmployees();
     String columns = "Count\t Min\t Max\t Average\t";
     var salaryStats = EmployeeStatisticsGenerator.getSalarySummary(listOfEmployees);
     var hoursWorkedStats = EmployeeStatisticsGenerator.getHoursWorkedSummary(listOfEmployees);

     System.out.println();
     System.out.println("Wage statistics");
     System.out.println(columns);
     System.out.printf("%d\t %.2f\t %.2f\t %.2f%n", 
           salaryStats.getCount(), 
           salaryStats.getMin(), 
           salaryStats.getMax(), 
           salaryStats.getAverage());

     System.out.println();
     System.out.println("Hours worked statistics");
     System.out.println(columns);
     System.out.printf("%d\t %.2f\t %.2f\t %.2f%n", 
           hoursWorkedStats.getCount(), 
           hoursWorkedStats.getMin(), 
           hoursWorkedStats.getMax(), 
           hoursWorkedStats.getAverage());

     System.out.println();
     System.out.println(Employee.getColumnHeaders());
     
     listOfEmployees.stream()
        .sorted(Comparator.comparing(Employee::getName))
        .forEach(System.out::println);
      /*
       * for(var e : listOfEmployees) { System.out.println(e); }
       */
     // Demonstrate File I/O and Serialization by writing all employees
     System.out.println("Writing employees using JAXB to serialize the data.");
     Employees.printSalaryReport(employees, employeesFileName);
     System.out.println("Finished!");
   }
}
