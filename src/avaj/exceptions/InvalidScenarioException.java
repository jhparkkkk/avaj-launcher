package avaj.exceptions;

public class InvalidScenarioException extends AvajException {
    
    public InvalidScenarioException(String message) {
        super(message);
    }
    
    public InvalidScenarioException(String message, Throwable cause) {
        super(message, cause);
    }
}