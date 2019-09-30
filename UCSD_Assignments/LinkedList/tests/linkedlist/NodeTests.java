/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kempo_000
 */
public class NodeTests {
   
   public NodeTests() {
   }
   
   @Test
   public void testDefaultConstructor() {
      Node<Integer> node = new Node<>();
      assertEquals(null, node.getData());
      assertEquals(null, node.getNext());
      
      node.setData(25);
      assertEquals(25, node.getData());
      
      Node<Integer> node2 = new Node<>();
      node.setData(30);
      assertEquals(30, node.getData());
      node.setNext(node2);
      assertEquals(node2, node.getNext());
   }
   
   @Test
   public void testTwoParameterConstructor() {
      Node<String> node2 = new Node<>("fox", null);
      Node<String> node = new Node<>("shawn", node2);
      assertEquals(node2, node.getNext());
      assertEquals(null, node2.getNext());
   }
}
