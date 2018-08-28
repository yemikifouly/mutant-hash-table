
public class DuplicateKeyException extends Exception{

	public DuplicateKeyException() {
		super("Key already exists in collection");
	}
}
