/**
 * 
 */
package com.adv.java.xml;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

/**
 * @author Shawn D. Fox
 *
 */
public class EmployeeReader {
   public Employees readEmployees(String fileName) {
      var employees = new Employees();
      try (BufferedReader input = Files.newBufferedReader(Paths.get(fileName))) {
         employees = JAXB.unmarshal(input, Employees.class);
      } 
      catch (Exception e1) {
         employees = new Employees();
      }
      return employees;
   }
}
