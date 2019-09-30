package com.shawnfox.TextFileIO;
import java.util.ArrayList;

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
}
