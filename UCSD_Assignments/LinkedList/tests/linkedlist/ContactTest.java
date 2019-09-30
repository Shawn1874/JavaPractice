/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests to demonstrate the correct behavior of the Contact class.
 * 
 * @author Shawn D. Fox
 */
public class ContactTest {
   
   Contact testContact = new Contact("Shawn", "Fox", "619-867-5309", "5230 Mount Alifan Dr");
   Contact testContact2 = new Contact("Shawn", "Fox", null, "5230 Mount Alifan Dr");
   Contact testContact3 = new Contact("Shawn", "Fox", null, "");
   
   public ContactTest() {
      
   }
   
   @Test
   void testConstruction() {
      IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
              () -> new Contact(null, "Fox", "619-867-5309", "5230 Mount Alifan Dr"));
      assertEquals(thrown.getMessage(), "A name parameter cannot be null or empty");
      thrown = assertThrows(IllegalArgumentException.class, 
              () -> new Contact("", "Fox", "619-867-5309", "5230 Mount Alifan Dr"));
      assertEquals(thrown.getMessage(), "A name parameter cannot be null or empty");
      thrown = assertThrows(IllegalArgumentException.class, 
              () -> new Contact("Shawn", null, "619-867-5309", "5230 Mount Alifan Dr"));
      assertEquals(thrown.getMessage(), "A name parameter cannot be null or empty");
      thrown = assertThrows(IllegalArgumentException.class, 
              () -> new Contact("Shawn", "", "619-867-5309", "5230 Mount Alifan Dr"));
      assertEquals(thrown.getMessage(), "A name parameter cannot be null or empty");
      
      Contact shawnfox = new Contact("Shawn", "Fox", "619-867-5309", "5230 Mount Alifan Dr");
      
      assertAll("shawnfox", 
              () -> assertEquals("Shawn", shawnfox.getFirstName()),
              () -> assertEquals("Fox", shawnfox.getLastName()),
              () -> assertEquals("619-867-5309", shawnfox.getPhoneNumber()),
              () -> assertEquals("5230 Mount Alifan Dr", shawnfox.getAddress())
      );
   }
   
   @Test
   void testSetFirstName() {
      String newFirstName = "Rick";
      testContact.setFirstName(newFirstName);
      assertEquals(newFirstName, testContact.getFirstName());
   }
   
   @Test
   void testSetLastName() {
      String newLastName = "James";
      testContact.setLastName(newLastName);
      assertEquals(newLastName, testContact.getLastName());
   }
   
   @Test
   void testSetPhoneNumber() {
      String newNumber = "858-309-8475";
      testContact.setPhoneNumber(newNumber);
      assertEquals(newNumber, testContact.getPhoneNumber());
   }
   
   @Test
   void testCompareTo() {
      Contact shawnfox = new Contact("Shawn", "Fox", "858-309-8475", "5230 Mount Alifan Dr");
      Contact shawnfox2 = new Contact("Shawn", "Fox", "858-309-8475", "5230 Mount Alifan Dr");
      Contact chrisjohnson = new Contact("Chris", "Johnson", "858-321-7105", "1234 Broad Street");
      Contact teddyroosevelt = new Contact("Teddy", "Roosevelt", "123-456-7890", "1600 Pennsylvania Ave NW");
      
      assertAll("compareTo",
         () -> assertTrue(shawnfox.compareTo(shawnfox2) == 0),
         () -> assertTrue(chrisjohnson.compareTo(shawnfox) < 0),
         () -> assertTrue(teddyroosevelt.compareTo(shawnfox) > 0)
      );
   }
   
   @Test
   void testToString() {
      assertEquals("Contact:\nFirst Name:\tShawn\nLast Name:\tFox\nAddress:\t5230 Mount Alifan Dr\nPhone:\t619-867-5309", testContact.toString());
      assertEquals("Contact:\nFirst Name:\tShawn\nLast Name:\tFox\nAddress:\t5230 Mount Alifan Dr", testContact2.toString());
      assertEquals("Contact:\nFirst Name:\tShawn\nLast Name:\tFox", testContact3.toString());
   }
}
