package linkedlist;

import java.util.List;

/**
 * This is an implementation of a linked list that only supports forward
 * iteration.
 *
 * @author Shawn D. Fox
 * @param <E>
 */
public class SinglyLinkedList<E> implements LinkedListInterface<E>
{
   private Node<E> first;
   private int size = 0;
   
   /**
    * Default constructor of an empty linked list.
    * 
    */
   public SinglyLinkedList() {
      
   }
   
   /**
    * Construct a linked list from another object that implements the List<E> interface.
    * 
    * @param values 
    */
   public SinglyLinkedList(List<E> values) {
      values.forEach((value) -> {
         addLast(value);
      });
   }
   
   /**
    * Construct a linked list from a variable length arg parameter.
    * 
    * @param values
    */
   public SinglyLinkedList(E... values) {
      for(E value : values) {
         addLast(value);
      }
   }

   /**
    * Check if list is empty
    *
    * @return true if the list is empty
    */
   @Override
   public boolean isEmpty()
   {
      return size == 0;
   }

   /**
    * Returns the number of elements in this list.
    *
    * @return the number of elements in this list
    */
   @Override
   public int size()
   {
      return size;
   }

   /**
    * Insert an element at beginning of the list
    *
    * @param data - data to be inserted
    */
   @Override
   public void addFirst(E data)
   {
      if (null == first) {
         first = new Node<>(data, null);
         size = 1;
      }
      else {
         Node<E> temp = first;
         first = new Node<>(data, temp);
         ++size;
      }
   }

   /**
    * Insert an element at the end of the list
    *
    * @param data - data to be inserted
    */
   @Override
   public final void addLast(E data)
   {

      Node<E> current = first;

      if (null == first) {
         first = new Node<>(data, null);
         size = 1;
      }
      else {
         while (current.getNext() != null) {
            current = current.getNext();
         }

         Node<E> last = new Node<>(data, null);
         current.setNext(last);
         ++size;
      }
   }

   /**
    * Adds the specified element to the specified position in this list.
    *
    * @param data object to be appended to this list
    * @param index - the position to insert the data
    *
    * @throws IndexOutOfBoundsException
    */
   @Override
   public void add(E data, int index) throws IndexOutOfBoundsException
   {
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException(String.format(
                 "Index %d is out of bounds. Size is %d", index, size()));
      }

      if (0 == size) {
         first = new Node<E>(data, null);
      }
      else if (0 == index) {
         Node<E> temp = first;
         first = new Node<E>(data, temp);
      }
      else {
         // Find the node just before the specified index and append a new
         // node
         Node<E> currentNode = first;
         int count = 0;

         while (count++ < index - 1) {
            currentNode = currentNode.getNext();
         }

         Node<E> temp = currentNode.getNext();
         currentNode.setNext(new Node<>(data, temp));
      }

      ++size;
   }

   /**
    * Removes the element at the specified position in this list. Shifts any
    * subsequent elements to the left (subtracts one from their indices).
    * Returns the element that was removed from the list.
    *
    * @param index the index of the element to be removed
    * @return the element previously at the specified position
    *
    * @throws IndexOutOfBoundsException {@inheritDoc}
    */
   @Override
   public E remove(int index) throws IndexOutOfBoundsException
   {
      if (index < 0 || index > size - 1) {
         throw new IndexOutOfBoundsException(String.format(
                 "Index %d is out of bounds. Size is %d", index, size()));
      }
      Node<E> toRemove;

      if (0 == index) {
         toRemove = first;
         first = first.getNext();
      }
      else {
         Node<E> currentNode = first;
         int current = 0;

         while (current++ < index - 1) {
            currentNode = currentNode.getNext();
         }

         toRemove = currentNode.getNext();
         currentNode.setNext(toRemove.getNext() != null ? toRemove.getNext() : null);
      }

      --size;
      return toRemove.getData();
   }

   /**
    * @return a string representation of the list
    */
   @Override
   public String toString()
   {
      Node<E> current = first;
      StringBuilder builder = new StringBuilder();

      while (current != null) {
         if (builder.length() != 0) {
            builder.append(", ");
         }
         builder.append(current.getData());
         current = current.getNext();
      }

      return builder.toString();
   }

   /**
    * outputs list contents
    */
   @Override
   public void print()
   {
      System.out.println(toString());
   }
}
