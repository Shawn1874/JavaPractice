package GuestBook;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the data model for the application which stores all contacts.
 * It does not allow duplicate contacts. It provides a validation service to 
 * allow a Contact to be validated before attempting to add it to the contact book.
 * 
 * @author Shawn D. Fox
 */
public class ContactBook {
   private ArrayList<Contact> allContacts = new ArrayList<>(16);
   
   /**
    * Add a contact to the Contact book.  
    * 
    * @param data 
    * @throws IllegalArgumentException if the contact has an invalid first or last name.
    */
   public void addContact(Contact data) {
      if(allContacts.isEmpty()) {
         allContacts.add(data);
      }
      else {
         int insertionPoint = Collections.binarySearch(allContacts, data);
         if(insertionPoint >= 0) {
            throw new IllegalArgumentException("Contact already exists in the contact book!");
         }
         else {
            allContacts.add(Math.abs(insertionPoint) - 1, data);
         }
      }
   }
   
   /**
    * Build a formatted string of all contacts within the address book. The 
    * string will have double spacing between the contacts.
    * 
    * @return a string that contains all contacts sorted by first name.
    */
   @Override
   public String toString() {
      StringBuilder text = new StringBuilder();
      
      for(Contact current : allContacts) {
         text.append(current);
         text.append("\n\n");
      }
      
      return text.toString();
   }
   
   /**
    * Get the number of contacts within the contact book.
    * 
    * @return number of contacts stored
    */
   public int getNumberOfContacts() {
      return allContacts.size();
   }
}
