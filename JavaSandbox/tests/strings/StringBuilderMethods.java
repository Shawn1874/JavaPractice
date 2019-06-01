package strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringBuilderMethods {
   
   StringBuilder test = new StringBuilder("xof nwahs");
   StringBuilder reversed = new StringBuilder("shawn fox");

   /**
    * Demonstrate the capacity methods.  Construct an object with an initial capacity
    * and evaluate the results after additional operations.
    */
   @Test
   void testCapacity() {
      StringBuilder test = new StringBuilder(10);
      assertEquals(10, test.capacity());
      
      test.ensureCapacity(-1);
      assertEquals(10, test.capacity());
      
      test.ensureCapacity(12);
      assertEquals(22, test.capacity());
      
      test.ensureCapacity(50);
      assertEquals(50, test.capacity());
      
   }

   /**
    * Demonstrate the reverse method which does mutate the object.  Additionally,
    * StringBuffer does not implement comparable!
    */
   @Test
   void testReverse() {
      StringBuilder testReversed = test.reverse();
      
      // This is only verifying that test and testReversed are references to the same object.
      assertEquals(test, testReversed);
      
      assertTrue(reversed.toString().compareTo(testReversed.toString()) == 0);
   }
   
   /**
    * StringBuilder does not override equals so the default method compares only the 
    * references.  Must use String.compareTo for an equivalence test.
    */
   @Test
   void testInsert() {
      StringBuilder name = new StringBuilder("shawn fox");
      name.insert(6,  "d ");
      assertTrue(name.toString().compareTo("shawn d fox") == 0);
   }
   
   /**
    * It's interesting that the replace method doesn't allow a string to be
    * specified. It can only be done with index values.  The final index
    * is also exclusive.
    */
   @Test
   void testReplace() {
      StringBuilder name = new StringBuilder("shawn fox");
      name.replace(0, 5, "kathy");
      assertTrue(name.toString().compareTo("kathy fox") == 0);
   }

}
