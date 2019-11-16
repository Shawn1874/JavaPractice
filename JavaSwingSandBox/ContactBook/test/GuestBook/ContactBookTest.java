/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuestBook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  JUnit tests to demonstrate the correct behavior of the ContactBook class.
 * 
 * @author Shawn D Fox
 */
public class ContactBookTest {
   
   Contact shawnfox = new Contact("Shawn", "Fox", "619-867-5309", "5230 Mount Alifan Dr");
   Contact chrisjohnson = new Contact("Chris", "Johnson", "858-321-7105", "1234 Broad Street");
   Contact teddyroosevelt = new Contact("Teddy", "Roosevelt", "123-456-7890", "1600 Pennsylvania Ave NW");
      
   public ContactBookTest() {
   }
   
   @Test
   void testAddValidContact() {
      ContactBook contacts = new ContactBook();
      contacts.addContact(teddyroosevelt);
      contacts.addContact(shawnfox);
      contacts.addContact(chrisjohnson);
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
              () -> contacts.addContact(shawnfox));
      assertEquals("Contact already exists in the contact book!", exception.getMessage());
      assertEquals(3, contacts.getNumberOfContacts());
      
      String expected = String.format("%s\n\n%s\n\n%s\n\n", 
              chrisjohnson.toString(),
              shawnfox.toString(),
              teddyroosevelt.toString());
      
      System.out.println(contacts.toString());
      assertEquals(expected, contacts.toString());
   }
}
