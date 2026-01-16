package avaj.exceptions;

public class UnknownAircraftTypeException extends AvajException {
    
    public UnknownAircraftTypeException(String type) {
        super("Unknown aircraft type: " + type);
    }
}