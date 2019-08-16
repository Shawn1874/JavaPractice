package strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringMethods {

   String test = "We hold these truths to be self evident";
   
   /**
    * Demonstrates the String.contains method.  The argument is a CharSequence
    * so the contains method can receive any argument that can be represented
    * as a CharSequence.
    */
   @Test
   void testContains() {
      assertTrue(test.contains("truth"));
      
      StringBuffer jesseb = new StringBuffer("self evident");
      assertTrue(test.contains(jesseb));
      
      StringBuilder sb = new StringBuilder("to be");
      assertTrue(test.contains(sb));
   }
   
   /**
    * String does not provide String to primitive types or primitive wrapper types.  For that
    * see the Parse methods of the primitive wrapper types.
    */
   @Test
   void testValueOf() {
      assertEquals("1", String.valueOf(1));
      assertEquals("1000000000", String.valueOf(1_000_000_000L));
      assertEquals("25.5", String.valueOf(25.5F));
      assertEquals("25.5", String.valueOf(25.5)); // double
      assertEquals("2500", String.valueOf(new Integer(2500))); // Object
      assertEquals("true", String.valueOf(true));
      assertEquals("false", String.valueOf(false));
   }
   
   /**
    * String overrides equals so it can be used for an equivalence test.  equals
    * does not simply test that the references are the same.
    */
   @Test
   void testEquals() {
      String name = "shawn";
      String name2 = "shawn";
      String name3 = new String("shawn");
      
      // equivalence or test of same object?
      assertTrue(name == name2);  // both refer to same object
      assertFalse(name == name3); // refer to different objects
      assertTrue(name.equals(name2));
      assertTrue(name2.equals(name3));
   }
   
   /**
    * String overrides equals so it can be used for an equivalence test.  equals
    * does not simply test that the references are the same.
    */
   @Test
   void testEqualsIgnoreCase() {
      String name = "shawn";
      String nameCaps = "Shawn";
      
      assertTrue(nameCaps.equalsIgnoreCase(name));
   }

   /**
    * This version of String.join is used with a variable length argument list.
    */
   @Test
   void testJoin() {
      String message = String.join("-", "Java", "is", "cool");
      assertEquals("Java-is-cool", message);
   }
   
   /**
    * The overload of join that consumes an iterable is used to join various
    * types of collections or an array
    */
   @Test
   void testJoinIterables() {
      String names[] = { "Jerry", "Ronnie", "Joe" };
      String namesCsv = String.join(", ", names);
      assertEquals("Jerry, Ronnie, Joe", namesCsv);
   }
   
   @Test
   void testSubstring() {
      String msg = "Each turned his face with a ghastly pang, and cursed me with his eye!";
      assertEquals("face", msg.substring(16, 20));
      assertEquals("face", msg.subSequence(16, 20));  // implements CharSequence interface method
      assertEquals("eye!", msg.substring(65));
   }
   
   @Test
   void testReplace() {
      String msg = "Each turned his neck with a ghastly pang, and cursed me with his eye!";
      String msg2 = "Each turned his face with a ghastly pang, and cursed me with his eye!";
      assertEquals(msg2, msg.replace("neck", "face"));
   }
   
   @Test
   void testMatches() {
      String regex = "[a-z]{2}[0-9]{3}";
      
      // test matches
      String test1 = "az059";
      assertTrue(test1.matches(regex));
      test1 = "ab123";
      assertTrue(test1.matches(regex));
      test1 = "zz999";
      assertTrue(test1.matches(regex));
      
      // test failures
      String test2 = "AZ123";
      assertFalse(test2.matches(regex));
      test2 = "abc123";
      assertFalse(test2.matches(regex));
      test2 = "yz0123";
      assertFalse(test2.matches(regex));
      test2 = "yz.123";
      assertFalse(test2.matches(regex));
      
   }
}
