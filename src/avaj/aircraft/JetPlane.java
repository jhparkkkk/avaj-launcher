package avaj.aircraft;

public class JetPlane extends Aircraft {
    JetPlane(long p_id, String p_name, avaj.coordinates.Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public void updateConditions() {
        // Implementation for updating conditions based on weather
    }
}