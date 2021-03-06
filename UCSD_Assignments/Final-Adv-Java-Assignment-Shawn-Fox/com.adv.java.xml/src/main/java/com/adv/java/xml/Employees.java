package com.adv.java.xml;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;

/**
 * An abstraction for an employees container.  The employees container
 * supports XML serialization and deserialization.
 * 
 * @author Shawn D. Fox
 *
 */
public class Employees {
   
   @XmlElement(name="Employee")
   private ArrayList<Employee> employees = new ArrayList<>();
   
   /**
    * return a reference to the employees container.
    * 
    * @return a modifiable reference to the employees container.
    */
   public ArrayList<Employee> getEmployees() { 
      return employees; 
   }
   
   /**
    * Iterate over all employees and write a all employee data to an .xml file 
    * 
    */
   public static void printSalaryReport(Employees employees, String fileName) {
      
      try(FileWriter employeesFile = new FileWriter(fileName);
         BufferedWriter writer = new BufferedWriter(employeesFile)) {
         JAXB.marshal(employees, writer);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
