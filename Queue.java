public class Queue<K extends Comparable<K>> {
	
	private LinkedList<K> list;
	
	/**
	 * Constructor of Queue class
	 */
	public Queue() {
		list = new LinkedList<>();
	}
	
	/**
	 * Checks whether the queue is empty or not
	 * @return true if the queue is empty and false if not
	 */
	public boolean isEmpty() {
		if (list.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * Adds a node with the given key to the end of the queue
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void enqueue(K key) throws DuplicateKeyException{
		list.addToBack(key);
	}
	
	/**
	 * Removes the node at the front of the queue
	 * @return the key of the node removed
	 * @throws EmptyQueueException
	 */
	public K dequeue() throws EmptyQueueException {
		if (list.size() == 0)
			throw new EmptyQueueException();
		return list.removeFromFront();
	}
	
	/**
	 * Gets the number of items in the queue
	 * @return the number of items in the queue
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * @return a String representation of the queue
	 */
	public String toString() {
		return list.toStringForQueue();
	}
}
