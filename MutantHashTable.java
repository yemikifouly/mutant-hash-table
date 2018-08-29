import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MutantHashTable<K extends Comparable<K>> {

	private LinkedList<K>[] table;
	private ArrayList<K> items;
	private ArrayList<Integer> bucketsIndexes;
	private int tableSize, numItems, numBuckets;
	private double loadFactor;
	
	/**
	 * Constructor of the Hash Table
	 * @param initialSize
	 * @param loadFactor
	 */
	public MutantHashTable(int initialSize) {
		this.table = (LinkedList<K>[]) new LinkedList[initialSize];
		this.items = new ArrayList<>();
		this.bucketsIndexes = new ArrayList<>();
		this.tableSize = initialSize;
		this.numItems = 0;
		this.numBuckets = 0;
		this.loadFactor = 0.75;
	}
	
	/**
	 * Gets the number of slots in the table
	 * @return the table size
	 */
	public int getTableSize() {
		return tableSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<K> getItems(){
		return items;
	}
	
	/**
	 * Inserts the node with the given key into the table and resize the table if necessary
	 * @param key
	 * @throws DuplicateKeyException
	 */
	public void insert(K key) throws DuplicateKeyException{
		int index = getIndex(key);
		if (table[index] == null) {
			table[index] = new LinkedList<>();
			numBuckets++;
			bucketsIndexes.add(index);
		}
		table[index].add(key);
		numItems++;
		items.add(key);
		double currLoadFactor = numBuckets / (double)tableSize;
		if (currLoadFactor >= loadFactor) 
			table = resizeTable();
	}
    
	/**
	 * Gets the index of the table where the node with the given key must be stored
	 * @param key
	 * @return
	 */
    private int getIndex(K key) {
    	int index = key.hashCode();
		index = index % tableSize;
		if (index < 0)
			index += tableSize;
		return index;
    }
    
    /**
     * Creates a new table of twice the size of to store all elements
     * @return the newly created table
     * @throws DuplicateKeyException
     */
    private LinkedList<K>[] resizeTable() throws DuplicateKeyException{
    	tableSize = 2 * tableSize;
    	numBuckets = 0;
    	LinkedList<K>[] newTable = (LinkedList<K>[]) (new LinkedList[tableSize]);
    	ArrayList<Integer> newBucketsIndexes = new ArrayList<>();
    	bucketsIndexes.sort(null);
    	for (int i = 0; i < bucketsIndexes.size(); i++) {
    		LinkedList<K> linkedList = table[bucketsIndexes.get(i)];
    		if (linkedList != null) {
    			ArrayList<K> listOfKeys = linkedList.getValuesAcsending();
    			for (int j = 0; j < listOfKeys.size(); j++) {
    				K key = listOfKeys.get(j);
    				int index = getIndex(key);
    				if (newTable[index] == null) {
    					newTable[index] = new LinkedList<>();
    					numBuckets++;
    					newBucketsIndexes.add(index);
    				}
    				newTable[index].add(key);
    			}
    		}			
	}
    	//System.out.println("\nAlert: The table has been resized.\n");
    	bucketsIndexes = newBucketsIndexes;
    	return newTable;
    }
	
    /**
     * Removes the node with the given key from the Hash Table
     * @param key
     */
    public void remove(K key) {
	int index = getIndex(key);
	if (table[index] == null)
            throw new NoSuchElementException();
	table[index].remove(key);
	numItems--;
	if (table[index].size() == 0) {
	    table[index] = null;
	    numBuckets--;
	    bucketsIndexes.remove(new Integer(index));
	}
	items.remove(key);
    }
	
	/**
	 * Merge the given table with the current table
	 * @param hashTable
	 * @throws DuplicateKeyException
	 */
	public void merge(MutantHashTable<K> hashTable) throws DuplicateKeyException{
		ArrayList<K> itemsInNewTable = hashTable.getItems();
		for (int i = 0; i < itemsInNewTable.size(); i++) {
			K key = itemsInNewTable.get(i);
			if (find(key) != -1)
				continue;
			insert(itemsInNewTable.get(i));
		}
	}
	
	/**
	 * Gets the number of items in the Hash Table
	 * @return the number of items in the table
	 */
	public int count() {
		return numItems;
	}
	
	/**
	 * Gets the list stored in the table at the index mapped to by the given key
	 * @param key
	 * @return
	 */
	public LinkedList<K> getList(int index){
		if (index < 0 || index >= tableSize)
			return null;
		return table[index];
	}
	
	/**
	 * Check if the node with the given key is in the Hash Table
	 * @param key
	 * @return the key if the node is in the table and null if not
	 */
	public int find(K key) {
		int index = getIndex(key);
		if (table[index] == null || table[index].lookup(key) == null)
			return -1;
		return index;
	}	

	/**
	 * Converts the Hash Table to a Stack
	 * @return the Stack obtained from the Hash Table
	 * @throws DuplicateKeyException
	 */
	public Stack<K> toStack() throws DuplicateKeyException{
		Stack<K> stack = new Stack<>();
		for (int i = 0; i < items.size(); i++) {
			stack.push(items.get(i));
		}
		return stack;
	}
	
	/**
	 * Converts Hash Table to a Queue
	 * @return the Queue obtained from the Hash Table
	 * @throws DuplicateKeyException
	 */
    public Queue<K> toQueue() throws DuplicateKeyException{
		Queue<K> queue = new Queue<>();
		for (int i = 0; i < items.size(); i++)
			queue.enqueue(items.get(i));
		return queue;
	}

    
        /**
        * @return a String representation of the mutant Hash Table
        */
        public String toString() {
    	    String toReturn = "";
    	    String content = "\nContent: ";
    	    bucketsIndexes.sort(null);
    	    for (int i = 0; i < bucketsIndexes.size(); i++) 
    	        toReturn += "Index: " + bucketsIndexes.get(i) + content + table[bucketsIndexes.get(i)].toString() + "\n\n";
    	    return toReturn;
        }
}
