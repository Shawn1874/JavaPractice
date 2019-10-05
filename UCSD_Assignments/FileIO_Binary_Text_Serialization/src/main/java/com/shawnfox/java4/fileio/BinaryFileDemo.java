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

import com.shawnfox.java4.fileio.Demonstration.PromptUser;

/**
 * @author Shawn D. Fox
 *
 */
public class BinaryFileDemo extends Demonstration {

   /**
    * Demonstrate the concept of binary file I/O.  Prompt the user for employee data, 
    * write it to a binary file, read it back from the file, and then print the
    * data that was read from the file to the console.
    */
   @Override
   public void demonstrate() throws IOException {
      try (var consoleInput = new Scanner(System.in);
           var out = new DataOutputStream(new FileOutputStream("employeeBinary.dat"))) {
              Employee staff[] = readData(consoleInput, PromptUser.Yes);
              writeData(staff, out);
       } 
       catch(Exception e) {
          
       }
       
       // retrieve all records into a new array
       try
       {
          Employee[] staff = readEmployees();
          printEmployees(staff);
       }
       catch (Exception e) {
          
       }
   }

   /**
    * Writes all employees in an array to a DataOutput writer. String fields are preceded
    * by a length so that the reader has the ability to convert a sequence of bytes
    * back to a string.
    * 
    * @param employees an array of employees
    * @param out       a binary stream writer
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
   
   /**
    * Reads the data back into the program in the same order that it was written.
    * 
    * @return an array of employees or null
    */
   private Employee[] readEmployees() {
      Employee[] employees = null;
      
      try (DataInputStream inputStream = new DataInputStream(new FileInputStream("employeeBinary.dat"))) {
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
         e.printStackTrace();
      }
      return employees;
   }
   
   /**
    * Read exactly size characters or until a 0 is read.
    * 
    * @param size number of characters to read
    * @param in a binary stream to read from
    * @return a string constructed from characters read in from the stream
    * @throws IOException
    */
   private String readFixedString(int size, DataInput in) throws IOException
   {  
      var b = new StringBuilder(size);
      int i = 0;
      var done = false;
      while (!done && i < size)
      {  
         char ch = in.readChar();
         i++;
         if (ch == 0) {
            done = true;
         }
         else {
            b.append(ch);
         }
      }
      
      in.skipBytes(2 * (size - i));
      return b.toString();
   }
}
