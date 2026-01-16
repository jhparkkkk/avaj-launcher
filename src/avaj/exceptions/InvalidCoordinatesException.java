package avaj.exceptions;

public class InvalidCoordinatesException extends AvajException {
    
    public InvalidCoordinatesException(String message) {
        super(message);
    }
    
    public InvalidCoordinatesException(int longitude, int latitude, int height) {
        super("Invalid coordinates: (" + longitude + ", " + latitude + ", " + height + ")");
    }
}