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
}

class Employee {
   enum Department {
      SW, IT, SALES, REGULATORY
   }

   String lastName;
   String firstName;
   Department dept;
   double salary;
   
   
   public Department getDepartment() {
      return dept;
   }

   public Employee(String lastName, String firstName, Department dept, double salary) {
      super();
      this.lastName = lastName;
      this.firstName = firstName;
      this.dept = dept;
      this.salary = salary;
   }
}
