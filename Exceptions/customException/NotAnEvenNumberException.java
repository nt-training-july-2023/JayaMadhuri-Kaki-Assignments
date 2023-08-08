package customException;
@SuppressWarnings("serial")
public class NotAnEvenNumberException extends Exception {
	public NotAnEvenNumberException(String message) {
		super(message);
	}
}