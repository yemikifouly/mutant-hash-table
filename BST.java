public class BST<K extends Comparable<K>> {
	
    private Node<K> root;
    private int numItems;
    
    public BST() {
    	root = null;
    	numItems = 0;
    }
    
    public void insert(K key) throws DuplicateKeyException{
    	root = insert(root, key);
    }
    
    private Node<K> insert(Node<K> node, K key) throws DuplicateKeyException{
    	if (node == null) {
    		numItems++;
    		return new Node<K>(key);
    	}
    	if (node.getKey().equals(key))
    		throw new DuplicateKeyException();
    	else if (key.compareTo(node.getKey()) < 0) {
    		node.setPreviousOrLeft(insert(node.getPreviousOrLeft(), key));
    		return node;
    	}
    	else {
    		node.setNextOrRight(insert(node.getNextOrRight(), key));
    		return node;
    	}   	
    }
    
    public boolean lookup(K key) {
    	return lookup(root, key);
    }
    
    private boolean lookup(Node<K> node, K key){
    	if (node == null)
    		return false;
    	if (node.getKey().equals(key))
    		return true;
    	else if (key.compareTo(node.getKey()) < 0) {
    		return lookup(node.getPreviousOrLeft(), key);
    	}
    	else {
    		return lookup(node.getNextOrRight(), key);
    	}   	
    } 
    
    public int size() {
    	return numItems;
    }
    
    public String toString() {
    	return toString(root);
    }
    
    private String toString(Node<K> root) {
    	String toReturn = "";
    	if (root == null)
    		return toReturn;
    	toReturn += root.getKey() + " ";
    	if (root.getPreviousOrLeft() != null)
    		toReturn += toString(root.getPreviousOrLeft());
    	if (root.getNextOrRight() != null)
    		toReturn += toString(root.getNextOrRight());
    	return toReturn;
    }
}
