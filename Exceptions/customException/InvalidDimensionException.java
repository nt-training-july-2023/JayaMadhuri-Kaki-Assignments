package customException;

@SuppressWarnings("serial")
public class InvalidDimensionException extends Exception {
	public InvalidDimensionException(String message) {
		super(message);
	}
}