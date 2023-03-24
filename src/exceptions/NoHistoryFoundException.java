package exceptions;

public class NoHistoryFoundException extends Exception {
	public NoHistoryFoundException(String errorMessage) {
		super(errorMessage);
	}
}
