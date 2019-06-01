package com.shawnfox.java2.assignment3;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class Employees {

   @XmlElement(name="Employee")
   private Map<Long, Employee> employees = new HashMap<>();
   
   public Map<Long, Employee> getEmployees() { 
      return employees; 
   }
}
