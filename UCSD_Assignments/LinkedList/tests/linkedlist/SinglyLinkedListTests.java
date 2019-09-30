package linkedlist;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn D. Fox
 */
public class SinglyLinkedListTests {
   
   public SinglyLinkedListTests() {
   }
   
   @Test
   public void addFirst() {
      SinglyLinkedList<Integer> values = new SinglyLinkedList<>();
      assertEquals(0, values.size());
      assertEquals(true, values.isEmpty());
      values.addFirst(20);
      assertEquals(1, values.size());
      assertEquals(false, values.isEmpty());
      assertEquals("20", values.toString());
      values.addFirst(15);
      values.addFirst(10);
      values.addFirst(5);
      assertEquals("5, 10, 15, 20", values.toString());
      assertEquals(4, values.size());
   }
   
   @Test
   public void addLast() {
      SinglyLinkedList<Integer> values = new SinglyLinkedList<>();
      assertEquals(0, values.size());
      assertEquals(true, values.isEmpty());
      values.addLast(5);
      assertEquals(1, values.size());
      assertEquals(false, values.isEmpty());
      assertEquals("5", values.toString());
      values.addLast(10);
      values.addLast(15);
      values.addLast(20);
      assertEquals("5, 10, 15, 20", values.toString());
      assertEquals(4, values.size());
   }
   
   @Test
   public void testAdd() {
      SinglyLinkedList<Integer> values = new SinglyLinkedList<>();
      values.add(10, 0);
      values.add(20, 1);
      values.add(5, 0);  // size == 3, contains 5, 10, 20
      values.add(15, 2);
      assertEquals("5, 10, 15, 20", values.toString());
      assertEquals(4, values.size());
      
      values.addFirst(0);
      values.addLast(25);
      assertEquals("0, 5, 10, 15, 20, 25", values.toString());
      assertEquals(6, values.size());
      
      IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () -> values.add(30, 7));
      assertEquals("Index 7 is out of bounds. Size is 6", e.getMessage());
   }
   
   @Test
   public void testRemove() {
      Contact shawn = new Contact("shawn", "fox", "619-867-5309", "5230 Mount Alifan Dr");
      Contact jeff = new Contact("jeff", "wilson", "619-123-3333", "5230 Mount Abraham Dr");
      Contact mary = new Contact("mary", "jane", "858-222-4312", "5228 Mount Everest Dr");
   
      SinglyLinkedList<Contact> values = new SinglyLinkedList<>();
      values.add(shawn, 0);
      values.add(jeff, 0);
      values.addFirst(mary);
      assertEquals(3, values.size());
      
      IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () -> values.remove(4));
      assertEquals("Index 4 is out of bounds. Size is 3", e.getMessage());
      assertEquals(3, values.size());
      
      Contact removed = values.remove(0);
      assertEquals(2, values.size());
      assertEquals("mary", removed.getFirstName());
      
      removed = values.remove(1);
      assertEquals(1, values.size());
      assertEquals("shawn", removed.getFirstName()); 
   }
   
   @Test
   public void testListInterfaceConstructor() {
      ArrayList<Integer> integers = new ArrayList<>();
      integers.add(10);
      integers.add(20);
      integers.add(30);
      integers.add(40);
      integers.add(50);
      
      SinglyLinkedList<Integer> ll = new SinglyLinkedList(integers);
      assertEquals(5, ll.size());
      
      Integer removed = ll.remove(2);
      assertEquals(30, removed);
   }
   
   @Test
   public void testListVarArgsConstructor() {
      SinglyLinkedList<Integer> ll = new SinglyLinkedList(10, 20, 30, 40, 50);
      assertEquals(5, ll.size());
      
      Integer removed = ll.remove(3);
      assertEquals(40, removed);
   }
}
