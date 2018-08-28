public class Node<K extends Comparable<K>> implements Comparable<Node<K>>{
	
	private K key;
	private Node<K> prevOrLeft, nextOrRight;
	
	/**
	 * Constructor of Node object
	 * @param key
	 */
	public Node(K key) {
		this(null, key, null);
	}
	
	/**
	 * Constructor of Node object
	 * @param nextOrRight
	 * @param key
	 * @param prevOrLeft
	 */
	public Node(Node<K> prevOrLeft, K key, Node<K> nextOrRight) {
		this.prevOrLeft = prevOrLeft;
		this.key = key;
		this.nextOrRight = nextOrRight;
	}
	
	/**
	 * Sets the key of a node object
	 * @param key
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	/**
	 * Sets the previous node (or left node in case of BST) of the current node
	 * @param previous
	 */
	public void setPreviousOrLeft(Node<K> previous) {
		this.prevOrLeft = previous;
	}
	
	/**
	 * Sets the next node (or right node in case of BST) of the current node object
	 * @param next
	 */
	public void setNextOrRight(Node<K> next) {
		this.nextOrRight = next;
	}
	
	/**
	 * Gets the key of the node object
	 * @return the key
	 */
	public K getKey() {
		return this.key;
	}
	
	/**
	 * Gets the previous node (or left node in case of BST) of the current node object
	 * @return the previous (or left) node
	 */
	public Node<K> getPreviousOrLeft(){
		return this.prevOrLeft;
	}
	
	/**
	 * Gets the next node (or right node in case of BST) of the current node object
	 * @return
	 */
	public Node<K> getNextOrRight(){
		return this.nextOrRight;
	}
	
	@Override
	public int compareTo(Node<K> node) {
		return this.getKey().compareTo(node.getKey());
	}
	
	/**
	 * @return a String representation of the node object
	 */
	public String toString() {
		String toReturn = "";
		if (this.getPreviousOrLeft() == null)
			toReturn += "Previous node data: Previous node does not exist.\n";
		else
			toReturn += "Previous node data: " + this.getPreviousOrLeft().getKey() + "\n";
		toReturn += "Node data: " + this.getKey() + "\nNext node data: ";
		if (this.getNextOrRight() == null)
			toReturn += "Next node does not exist.";
		else
			toReturn += this.getNextOrRight().getKey();
		return toReturn;
	}
}
