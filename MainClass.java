import java.util.NoSuchElementException;

public class MainClass{

	public static void main(String[] args) throws EmptyQueueException {
		
		MutantHashTable<String> table = new MutantHashTable<>(5);
		MutantHashTable<String> newTable = new MutantHashTable<>(5);
		Queue<String> queue;
		Stack<String> stack;
	
		try {
			table.insert("this");
			table.insert("was");				
			table.insert("very");
			table.insert("exciting");
			table.insert("and");
			table.insert("fun");
			table.insert("to");
			table.insert("code");
			table.insert("had");
			table.insert("not");
			table.insert("played");
			table.insert("with");
			table.insert("data");
			table.insert("structures");
			table.insert("for");
			table.insert("ages");
				
			System.out.println("Original Hash Table after first insert operations:\n");
			System.out.println(table.toString());
				
			table.remove("exciting");
			table.remove("ages");
			System.out.println("Table after remove(\"exciting\") and remove(\"ages\") operations:\n\n");
			System.out.println(table.toString());
				
			System.out.println("Number of items in original Hash Table: " + table.count() + "\n\n");
					
			newTable.insert("My");
			newTable.insert("name");
			newTable.insert("?");
			newTable.insert("Y");
			newTable.insert("B.");
			newTable.insert("K");
				
			System.out.println("Second Hash Table after first insert operations:\n\n");
			System.out.println(newTable.toString());
				
			newTable.insert("also");
			newTable.insert("known");
			newTable.insert("as");
			newTable.insert("cyk");
				
			System.out.println("Second Hash Table after first insert(\"also\"), insert(\"known\"), insert(\"as\") and insert(\"cyk\") operations:\n\n");
			System.out.println(newTable.toString());
			
			System.out.println("Number of items in second Hash Table: " + newTable.count() + "\n\n");
			
			table.merge(newTable);
			System.out.println("Original Hash Table after merging it with second table:\n\n");
			System.out.println(table.toString());
				
			System.out.println("Number of items in original Hash Table after merge operation with second table: " + table.count() + "\n\n");
			
			System.out.println("find(\"remove\") operation returns: " + table.find("remove"));
			System.out.println();
				
			System.out.println("find(\"name\") operation returns: " + table.find("name"));
			System.out.println("\n");
			
			System.out.println("getList(16) returns: " + table.getList(16) + "\n");
			System.out.println("getList(7) returns: " + table.getList(7) + "\n");
			System.out.println();
			
			System.out.println("table.toStack() operation returns:\n");
			stack = table.toStack();
			System.out.println(stack.toString());
			System.out.println();
			
			System.out.println("table.toQueue() operation returns:\n");
			queue = table.toQueue();
			System.out.println(queue.toString());
			System.out.println("\n");
		} 
		catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
}
