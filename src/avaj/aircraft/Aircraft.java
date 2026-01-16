package avaj.aircraft;
import avaj.coordinates.Coordinates;

public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    
    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
        
    }

    public void updateConditions() {
        // Default implementation (can be overridden by subclasses)
    }

    public String getName() {
        return this.name;
    }
}
