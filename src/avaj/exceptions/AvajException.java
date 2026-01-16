package avaj.exceptions;

public class AvajException extends Exception {
    
    public AvajException(String message) {
        super(message);
    }
    
    public AvajException(String message, Throwable cause) {
        super(message, cause);
    }
}