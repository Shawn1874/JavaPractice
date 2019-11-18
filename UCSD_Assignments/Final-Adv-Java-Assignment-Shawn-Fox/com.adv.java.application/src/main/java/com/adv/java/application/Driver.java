/**
 * 
 */
package com.adv.java.application;

import com.adv.java.xml.EmployeeReader;
import com.adv.java.xml.Employees;
import com.adv.java.iostream.*;

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static String employeesFileName = "employees.xml";
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Reading employees using JAXB to deserialize the data.");
      var employeeReader = new EmployeeReader();
      Employees employees = employeeReader.readEmployees(employeesFileName);
      System.out.printf("%d employees read%n", employees.getEmployees().size());
      
      // Read in the new employees which demonstrates the use of a scanner
     var employeeEntry = new EmployeeDataEntry();
     var newEmployees = employeeEntry.readNewEmployees();
     System.out.printf("%d employees read%n", newEmployees.size());
     employees.getEmployees().addAll(newEmployees);
     
     // Demonstrate File I/O and Serialization by writing all employees
     System.out.println("Writing employees using JAXB to serialize the data.");
     Employees.printSalaryReport(employees, employeesFileName);
     System.out.println("Finished!");
   }
}
