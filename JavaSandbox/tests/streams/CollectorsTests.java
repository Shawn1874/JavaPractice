package streams;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import streams.Employee.Department;

class CollectorsTests {
   private List<Employee> employees = List.of(
         new Employee("Carlton", "Steve", Employee.Department.REGULATORY, 45_000),
         new Employee("Fox", "Shawn", Employee.Department.SW, 80_000),
         new Employee("Montanna", "Joe", Employee.Department.SALES, 50_000),
         new Employee("Rice", "Jerry", Employee.Department.SALES, 55_000));

   @Test
   void testGroupingBy() {
      Employee[] employees = {
         new Employee("fox", "shawn", Employee.Department.SW, 100_000),
         new Employee("fox", "Steve", Employee.Department.REGULATORY, 45_000),
         new Employee("fox", "shawn", Employee.Department.SW, 80_000),
         new Employee("fox", "shawn", Employee.Department.SALES, 50_000),
         new Employee("fox", "shawn", Employee.Department.SALES, 55_000)
      };
      
      List<Employee> list = Arrays.asList(employees);
      
      Map<Department, Long> employeeCountByDept = list.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
      
      assertEquals(2, employeeCountByDept.get(Department.SALES));
   }
   
   @Test
   void testJoiningWithComma() {
         
         // Get employees with first name that begins with S, and then join into a comma
         // separated list
         String names = employees.stream()
               .filter(e -> e.firstName.startsWith("S"))
               .map(e -> String.format("%s %s", e.firstName, e.lastName))
               .collect(Collectors.joining(", "));
         
         assertEquals("Steve Carlton, Shawn Fox", names);
   }
   
   @Test
   void testToMap() {
      Map<String, Double> employeeSalaries = employees.stream()
            .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
      assertEquals(4, employeeSalaries.size());
      assertEquals(45_000, employeeSalaries.get("Steve Carlton"));
   }
}

class Employee {
   enum Department {
      SW, IT, SALES, REGULATORY
   }

   public String lastName;
   public    String firstName;
   public Department dept;
   private double salary;
   private String fullName;
   
   public Department getDepartment() {
      return dept;
   }

   public Employee(String lastName, String firstName, Department dept, double salary) {
      super();
      this.lastName = lastName;
      this.firstName = firstName;
      this.dept = dept;
      this.salary = salary;
      fullName = String.format("%s %s", firstName, lastName);
   }
   
   public double getSalary() {
      return salary;
   }
   
   public String getName() {
      return fullName;
   }
}
