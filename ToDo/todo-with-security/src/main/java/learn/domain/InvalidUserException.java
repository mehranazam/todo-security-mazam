package learn.domain;

public class InvalidUserException extends Exception{

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
