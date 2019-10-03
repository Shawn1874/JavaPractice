/**
 * 
 */
package com.shawnfox.java4.fileio;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Shawn D. Fox
 *
 */
public class BinaryFileDemo extends Demonstration {

   @Override
   public void demonstrate() throws IOException {
      try (var consoleInput = new Scanner(System.in);
           var out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
              Employee staff[] = readData(consoleInput);
              writeData(staff, out);
       } 
       catch(Exception e) {
          
       }
       
       // retrieve all records into a new array
       try
       {
          Employee[] newStaff = readEmployees();

          // print the newly read employee records
          for (Employee e : newStaff)
             System.out.println(e);
       }
       catch (Exception e) {
          
       }
   }

   /**
    * Writes all employees in an array to a print writer
    * 
    * @param employees an array of employees
    * @param out       a print writer
    */
   private void writeData(Employee[] employees, DataOutput out) throws IOException {
      // write number of employees
      out.writeInt(employees.length);

      for (Employee e : employees) {
         out.writeInt(e.getName().length());
         out.writeChars(e.getName());
         out.writeInt(e.getHireDay().toString().length());
         out.writeChars(e.getHireDay().toString());
         out.writeDouble(e.getSalary());
      }
   }
   
   private Employee[] readEmployees() {
      Employee[] employees = null;
      
      try (DataInputStream inputStream = new DataInputStream(new FileInputStream("employee.dat"))) {
         int numEmployees = inputStream.readInt();
         employees = new Employee[numEmployees];
         
         int length;
         String name;
         String dateString;
         LocalDate date;
         Double salary;
         
         for (int n = 0; n < numEmployees; ++n) {
            length = inputStream.readInt();
            name = readFixedString(length, inputStream);
            length = inputStream.readInt();
            dateString = readFixedString(length, inputStream);
            date = LocalDate.parse(dateString);
            salary = inputStream.readDouble();
            Employee temp = new Employee(name, salary, date);
            employees[n] = temp;
         }
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return employees;
   }
   
   private String readFixedString(int size, DataInput in) throws IOException
   {  
      var b = new StringBuilder(size);
      int i = 0;
      var done = false;
      while (!done && i < size)
      {  
         char ch = in.readChar();
         i++;
         if (ch == 0) done = true;
         else b.append(ch);
      }
      in.skipBytes(2 * (size - i));
      return b.toString();
   }
}
