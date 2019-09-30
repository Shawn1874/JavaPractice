package linkedlist;

/*
 *  Java Program to Implement Singly Linked List
 */

/**
 * Class Node
 */
public class Node<E> {
	private E data;
	private Node<E> next;

	/**
	 * Constructor
	 */
	public Node() {
	}

	/**
	 * Constructor
	 */
	public Node(E d, Node<E> n) {
		data = d;
		next = n;
	}

	/**
	 * Sets next link to reference node
	 * 
	 * @param node a reference to the node
	 */
	public void setNext(Node<E> node) {
		next = node;
	}

	/**
	 * Sets the data of the node
	 * 
	 * @param data a reference to the data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Gets the node that the next link references
	 * 
	 * @return a reference to the next node
	 */
	public Node<E> getNext() {
		return next;
	}

	/**
	 * Gets the data of the node
	 * 
	 * @return a reference to the data
	 */
	public E getData() {
		return data;
	}
}
