package streams;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
      assertEquals(2, employeeCountByDept.get(Department.SW));
      assertEquals(1, employeeCountByDept.get(Department.REGULATORY));
   }
   
   /**
    * Demonstrate the use of GroupingBy using Collectors.maxBy.  Find the largest salary for each
    * department.
    */
   @Test
   void testGroupingWithMaxBy() {
      List<Employee> employees = List.of(
            new Employee("Smith", "Carol", Employee.Department.REGULATORY, 45_000),
            new Employee("Rogers", "Mike", Employee.Department.REGULATORY, 65_000),
            new Employee("Lynn", "Kristie", Employee.Department.REGULATORY, 25_000),
            new Employee("Fox", "Shawn", Employee.Department.SW, 80_000),
            new Employee("Fox", "Logan", Employee.Department.SW, 120_000),
            new Employee("Weidler", "Shannon", Employee.Department.SW, 82_000),
            new Employee("Piscopo", "Joe", Employee.Department.SALES, 50_000),
            new Employee("Rice", "Steven", Employee.Department.SALES, 55_000));
      
      Map<Department, Optional<Employee>> maxSalaryPerDept = employees
            .stream()
            .collect(Collectors.groupingBy(
                  Employee::getDepartment, 
                  Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
      
      assertTrue(maxSalaryPerDept.get(Department.REGULATORY).isPresent());
      assertTrue(maxSalaryPerDept.get(Department.SW).isPresent());
      assertTrue(maxSalaryPerDept.get(Department.SALES).isPresent());
      assertEquals(120_000, maxSalaryPerDept.get(Department.SW).get().getSalary());
      assertEquals(55_000, maxSalaryPerDept.get(Department.SALES).get().getSalary());
      assertEquals(65_000, maxSalaryPerDept.get(Department.REGULATORY).get().getSalary());
   }
   
   /**
    * Demonstrate building of a list after filtering a stream.  Demonstrate the 
    * Collections.removeIf algorithm after the list is constructed.
    */
   @Test
   void testToList() {
      List<Employee> results =
            employees.stream().filter(s -> s.dept == Department.SALES)
                  .collect(Collectors.toList());  // No side-effects!
      
      assertEquals(2, results.size());
      assertFalse(results.removeIf(e -> e.getDepartment() == Department.SW));
      assertFalse(results.removeIf(e -> e.getDepartment() == Department.REGULATORY));
   }
   
   /**
    * Demonstrate building of a list after filtering a stream.  Demonstrate the 
    * Collections.removeIf algorithm after the list is constructed.
    */
   @Test
   void testToSet() {
      Set<Employee> results =
            employees.stream().filter(s -> s.dept == Department.SALES)
                  .collect(Collectors.toSet());  // No side-effects!
      
      assertEquals(2, results.size());
      assertFalse(results.removeIf(e -> e.getDepartment() == Department.SW));
      assertFalse(results.removeIf(e -> e.getDepartment() == Department.REGULATORY));
   }
   
   /**
    * Demonstrate the concept of a SummaryStatistics data structure using a stream
    * of employee objects.
    */
   @Test
   void testIntSummaryStats() {
      DoubleSummaryStatistics stats = employees.stream()
            .collect(Collectors.summarizingDouble(Employee::getSalary));
      assertEquals(45_000, stats.getMin());
      assertEquals(80_000, stats.getMax());
      assertEquals(230_000, stats.getSum());
      assertEquals(4, stats.getCount());
   }
   
   /**
    * Demonstrate the concept of a SummaryStatistics data structure using a stream
    * of employee objects.  The result is collected into a map of department stats.
    */
   @Test
   void testIntSummaryStatsByDept() {
      List<Employee> employees = List.of(
            new Employee("Smith", "Carol", Employee.Department.REGULATORY, 45_000),
            new Employee("Rogers", "Mike", Employee.Department.REGULATORY, 65_000),
            new Employee("Lynn", "Kristie", Employee.Department.REGULATORY, 25_000),
            new Employee("Fox", "Shawn", Employee.Department.SW, 80_000),
            new Employee("Fox", "Logan", Employee.Department.SW, 120_000),
            new Employee("Weidler", "Shannon", Employee.Department.SW, 82_000),
            new Employee("Piscopo", "Joe", Employee.Department.SALES, 50_000),
            new Employee("Rice", "Steven", Employee.Department.SALES, 55_000));
      
      Map<Employee.Department, DoubleSummaryStatistics> stats = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingDouble(Employee::getSalary)));
      
      assertEquals(80_000, stats.get(Employee.Department.SW).getMin());
      assertEquals(120_000, stats.get(Employee.Department.SW).getMax());
      assertEquals(282_000, stats.get(Employee.Department.SW).getSum());
      assertEquals(3, stats.get(Employee.Department.SW).getCount());
      
      assertEquals(105_000, stats.get(Employee.Department.SALES).getSum());
      assertEquals(2, stats.get(Employee.Department.SALES).getCount());
      
      assertEquals(135_000, stats.get(Employee.Department.REGULATORY).getSum());
      assertEquals(3, stats.get(Employee.Department.REGULATORY).getCount());
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
   
   /**
    * Demonstrate construction of a key value map using a stream.
    */
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
