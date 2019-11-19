/**
 * 
 */
package com.adv.java.streams;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import com.adv.java.xml.Employee;

/**
 * @author Shawn D.Fox
 *
 */
public class EmployeeStatisticsGenerator {

   /**
    * Produce a summary statistics object for employee salaries
    * @param employees - a list of employees
    * @return
    */
   public static DoubleSummaryStatistics getSalarySummary(List<Employee> employees) {
      return employees.stream()
            .mapToDouble(e -> e.getHourlySalary().doubleValue())
            .summaryStatistics();
   }

   /**
    * Produce a summary statistics object for hours worked by employees
    * @param employees - a list of employees
    * @return
    */
   public static DoubleSummaryStatistics getHoursWorkedSummary(List<Employee> employees) {
      return employees.stream()
            .mapToDouble(e -> e.getHoursWorked().doubleValue())
            .summaryStatistics();
   }
}
