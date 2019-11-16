package GuestBook;

import java.util.Comparator;
import java.util.Objects;

/**
 * A class that stores information for a contact that can be stored in an 
 * address book.
 * 
 * @author Shawn D. Fox
 */
public class Contact implements Comparable<Contact> {


   private String firstName;
   private String lastName;
   private String phoneNumber;
   private String address;
   
   /**
    * This is a constructor that requires a valid value for all fields.
    * 
    * @param firstName The first name of the contact
    * @param lastName The last name of the contact
    * @param phoneNumber The phone number of the contact
    * @param address the home address of the contact
    * @throws IllegalArgumentException if either name is null or empty
    */
   public Contact(String firstName, String lastName, String phoneNumber, String address) {
      if(!validateName(firstName) || !validateName(lastName)) {
         throw new IllegalArgumentException("A name parameter cannot be null or empty");
      }
      
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber == null ? "" : phoneNumber;
      this.address = address == null ?  "" : address;
   }
   
   /**
    * Validates that the argument is not null and not empty.
    * 
    * @param name
    * @return true if the name is valid and false if it is invalid
    */
   public static boolean validateName(String name) {
      return (name != null && !name.isEmpty());
   }
   
   /**
    * Compares two contact objects by first name, then last name, then address, then phone number.
    * The only system requirement was to compare by first name, but comparing the remaining fields
    * allows the use of Collections.binarySearch to find duplicates and presents the sorted list
    * in a more useful way.
    * 
    * @param otherContact
    * @return the result of comparing the two objects using this.firstName.compareTo
    */
   @Override
   public int compareTo(Contact otherContact) {
      
      return Comparator.comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
         .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER)
         .thenComparing(Contact::getAddress, String.CASE_INSENSITIVE_ORDER)
         .thenComparing(Contact::getPhoneNumber)
         .compare(this, otherContact);
   }
   
   /**
    * Builds a hash code using all fields.
    * 
    * @return hash code for this object
    */
   @Override
   public int hashCode() {
      int hash = 3;
      hash = 43 * hash + Objects.hashCode(this.firstName);
      hash = 43 * hash + Objects.hashCode(this.lastName);
      hash = 43 * hash + Objects.hashCode(this.phoneNumber);
      hash = 43 * hash + Objects.hashCode(this.address);
      return hash;
   }

   /**
    * Compares two Contact objects and determines whether they are the same or logically equal.
    * 
    * @param obj
    * @return true if obj is the same object or logically equivalent, false otherwise
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      
      final Contact other = (Contact) obj;
      
      return (Objects.equals(this.firstName, other.firstName) &&
              Objects.equals(this.lastName, other.lastName)  &&
              Objects.equals(this.phoneNumber, other.phoneNumber) &&
              Objects.equals(this.address, other.address));
   }
   
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Contact:\n");
      builder.append(String.format("First Name:\t%s\n", firstName));
      builder.append(String.format("Last Name:\t%s", lastName));
      
      if(!address.isEmpty()) {
         builder.append(String.format("\nAddress:\t%s", address));
      }
      
      if(!phoneNumber.isEmpty()) {
         builder.append(String.format("\nPhone:\t%s", phoneNumber));
      }
      return builder.toString();
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   /**
    * @param phoneNumber the phoneNumber to set
    */
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber == null ? "" : phoneNumber;
   }

   /**
    * @return the address
    */
   public String getAddress() {
      return address;
   }

   /**
    * @param address the address to set
    */
   public void setAddress(String address) {
      this.address = address == null ? "" : address;
   }
}
