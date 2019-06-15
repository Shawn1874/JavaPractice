package generics.containers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import class_concepts.Planet;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class LinkedListTests {

   LinkedList<Integer> list = new LinkedList<>(Arrays.asList(5, 10, 15, 20));
   LinkedList<String>  emptyList = new LinkedList<>();
   
   @Test
   @Order(1)
   void testAddMethods() {
      
      // test list.add
      assertTrue(list.add(25));
      assertEquals(5, list.size());
      
      // test list.add at index, list.get, list.peek, and list.peekFirst
      list.add(0, 0);
      assertEquals(0, list.get(0));
      assertEquals(0, list.peek());
      assertEquals(0, list.peekFirst());
   }
   
   @Test
   @Order(2)
   void testAddAll() {
      assertTrue(list.addAll(Arrays.asList(50, 55, 60)));
      assertEquals(7, list.size());
      assertEquals(60, list.peekLast());
      assertTrue(list.addAll(5, Arrays.asList(30, 35, 40, 45)));
      assertEquals(11, list.size());
      assertEquals(60, list.peekLast());
   }
   
   /**
    * Asserts that element() throws if empty, whereas other methods return null.
    */
   @Test
   void testElement() {
      assertThrows(NoSuchElementException.class, () -> emptyList.element());
      assertEquals(null, emptyList.peek());
      assertEquals(null, emptyList.peekFirst());
      assertEquals(null, emptyList.peekLast());
   }
}
