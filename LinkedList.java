import java.util.ArrayList;
import java.util.NoSuchElementException;

public class LinkedList<K extends Comparable<K>> {

	private Node<K> firstNode, lastNode;
	private int numItems;
	
	/**
	 * Constructor of LinkedList object
	 */
	public LinkedList() {
		firstNode = lastNode = null;
		numItems = 0;
	}
	
	/**
	 * Adds a new node with the given key to the front of the LinkedList
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void addToFront(K key) throws DuplicateKeyException{
		Node<K> newNode = new Node<>(key);
		if (lookup(key) != null)
			throw new DuplicateKeyException();
		if (numItems == 0) 
			firstNode = lastNode = newNode;
		else {
			newNode.setNextOrRight(firstNode);
			firstNode.setPreviousOrLeft(newNode);
			firstNode = newNode;
		}
		numItems++;
	}
	
	/**
	 * Adds a new node with the given key to the back of the LinkedList
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void addToBack(K key) throws DuplicateKeyException{
		Node<K> newNode = new Node<>(key);
		if (lookup(key) != null)
			throw new DuplicateKeyException();
		if (numItems == 0) 
			lastNode = firstNode = newNode;
		else {
			newNode.setPreviousOrLeft(lastNode);
			lastNode.setNextOrRight(newNode);
			lastNode = newNode;
		}
		numItems++;
	}
	
	/**
	 * Adds a new node with the given key in the appropriate spot in the sorted LinkedlIst
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void add(K key) throws DuplicateKeyException{
		Node<K> newNode = new Node<K>(key);
		Node<K> currNode = firstNode;
		if (lookup(key) != null)
			throw new DuplicateKeyException();
		if (numItems == 0) {
			addToFront(key);
			return;
		}
		else {
			while (currNode.getKey().compareTo(key) < 0) {
				if (currNode == lastNode) {
					addToBack(key);
					return;
				}
				else					
				    currNode = currNode.getNextOrRight();
			}
			if (currNode.getPreviousOrLeft() == null) {
				addToFront(key);
				return;
			}
			else {
				currNode.getPreviousOrLeft().setNextOrRight(newNode);
				newNode.setPreviousOrLeft(currNode.getPreviousOrLeft());
				currNode.setPreviousOrLeft(newNode);
				newNode.setNextOrRight(currNode);
				numItems++;
			}
		}		
	}
	
	/**
	 * Removes the node at the front of the LinkedList
	 * @return the key of the node in front of the linkedList
	 */
	public K removeFromFront() {
		K toReturn = firstNode.getKey();
		if (numItems == 0)
			throw new NoSuchElementException("No such element in collection.");
		if (firstNode.getNextOrRight() != null) { 
			firstNode = firstNode.getNextOrRight();	
		    firstNode.setPreviousOrLeft(null);
	    }
		else 
			firstNode = lastNode = null;
		numItems--;
		return toReturn;
	}
	
	/**
	 * Removes the node at the back of the LinkedList
	 * @return 
	 */
	public K removeFromBack() {
		K toReturn = lastNode.getKey();
		if (numItems == 0)
			throw new NoSuchElementException("No such element in collection.");
		if (lastNode.getPreviousOrLeft() != null) {
		    lastNode = lastNode.getPreviousOrLeft();
		    lastNode.setNextOrRight(null);
		}
		else
			lastNode = firstNode = null;
		numItems--;
		return toReturn;
	}
	
	/**
	 * Removes from the LinkedList the node with the given key
	 * @param key
	 */
	public void remove(K key) {
		Node<K> currNode = firstNode;
		if (numItems == 0 || lookup(key) == null)
			throw new NoSuchElementException("No such element in collection.");
		while (!currNode.getKey().equals(key))
			currNode = currNode.getNextOrRight();
		if (currNode.getPreviousOrLeft() == null)
			removeFromFront();
		else if (currNode.getNextOrRight() == null)
			removeFromBack();
		else {
		    currNode.getPreviousOrLeft().setNextOrRight(currNode.getNextOrRight());
		    currNode.getNextOrRight().setPreviousOrLeft(currNode.getPreviousOrLeft());
		    numItems--;
		}
	}
	
	/**
	 * Gets the key of the node at the front of the list
	 * @return the key of the node at the front of the list
	 */
	public K getItemInFront() {
		return firstNode.getKey();
	}
	
	/**
	 * Gets the number of items in the list
	 * @return the number of items of the list
	 */
	public int size() {
		return numItems;
	}
	
	/**
	 * Search through the LinkedList for the node with the given key
	 * @param key
	 * @return the key of the node with the given key if found or null if not found
	 */
	public K lookup(K key) {
		Node<K> currNode = firstNode;
		if (numItems == 0)
			return null;
		while (currNode != null) {
			if (currNode.getKey().equals(key))
				return key;
			currNode = currNode.getNextOrRight();
		}
		return null;
	}
	
	/**
	 * Get values of the linked list in ascending order
	 * @return
	 */
	public ArrayList<K> getValuesAcsending() {
		ArrayList<K> toReturn = new ArrayList<K>();
		if (numItems == 0)
			return null;
		Node<K> currNode = firstNode;
		while (currNode != null) {
			toReturn.add(currNode.getKey());
			currNode = currNode.getNextOrRight();
		}
		return toReturn;
	}
	
	/**
	 * Get values of the linked list in descending order
	 * @return
	 */
	public ArrayList<K> getValuesDecsending() {
		ArrayList<K> toReturn = new ArrayList<K>();
		if (numItems == 0)
			return null;
		Node<K> currNode = lastNode;
		while (currNode != null) {
			toReturn.add(currNode.getKey());
			currNode = currNode.getPreviousOrLeft();
		}
		return toReturn;
	}
	
	/**
	 * @return a String representation of the LinkedList
	 */
	public String toString() {
		if (numItems == 0)
			return "Empty list";
		String toReturn = "";
		Node<K> node = firstNode;
		while (node != null) {
			if (node != firstNode)
				toReturn += " <==> ";
			toReturn += node.getKey();
			node = node.getNextOrRight();
		}
		return toReturn;
	}	
	
	/**
	 * @return a String representation of the LinkedList
	 */
	public String toStringForStack() {
		if (numItems == 0)
			return "Empty list";
		String toReturn = "";
		Node<K> node = firstNode;
		while (node != null) {				
			toReturn += node.getKey();
			if (node == firstNode)
				toReturn += "     <-- Top";
			toReturn += "\n";
			node = node.getNextOrRight();
		}
		return toReturn;
	}	
	
	/**
	 * @return a String representation of the LinkedList
	 */
	public String toStringForQueue() {
		if (numItems == 0)
			return "Empty list";
		String toReturn = "Front <-- ";
		Node<K> node = firstNode;
		while (node != null) {
			toReturn += "| " + node.getKey() + " | ";
			node = node.getNextOrRight();
		}
		toReturn += " <-- Back";
		return toReturn;
	}	
}
