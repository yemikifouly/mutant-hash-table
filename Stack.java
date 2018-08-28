import java.util.EmptyStackException;

public class Stack<K extends Comparable<K>> {
	
	private LinkedList<K> list;
	
	/**
	 * Constructor of Stack class
	 */
	public Stack() {
		list = new LinkedList<>();
	}
	
	/**
	 * Checks whether the stack is empty or not
	 * @return true if stack is empty and false if it is not
	 */
	public boolean isEmpty() {
		if (list.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * Adds a node with the given key to the stack
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void push(K key) throws DuplicateKeyException{
		list.addToFront(key);
	}
	
	/**
	 * Removes the node at the top of the stack
	 * @return the key of the node removed
	 */
	public K pop(){
		if (list.size() == 0)
			throw new EmptyStackException();
		return list.removeFromFront();
	}
	
	/**
	 * Gets the node at the top of the stack
	 * @return the key of the node at the top of the stack
	 */
	public K peek() {
		if (list.size() == 0)
			throw new EmptyStackException();
		return list.getItemInFront();
	}
	
	/**
	 * Gets the number of items in the stack
	 * @return the number of items in the stack
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * @return a String representation of the stack
	 */
	public String toString() {
		return list.toStringForStack();
	}
}
