package avaj.aircraft;
import  avaj.aircraft.Flyable;

import  avaj.aircraft.Helicopter;
import  avaj.aircraft.JetPlane;
import  avaj.aircraft.Baloon;

public final class AircraftFactory {
    private static final AircraftFactory INSTANCE = new AircraftFactory();

    private AircraftFactory() {
        System.out.println("AircraftFactory created.");
    }

    public static AircraftFactory getFactory() {
        return INSTANCE;
    }

    public Flyable newAircraft(String p_type, String p_name, avaj.coordinates.Coordinates p_coordinates, long p_id) {
        Flyable flyable = null;

        switch (p_type) {
            case "Helicopter":
                flyable = new Helicopter(p_id, p_name, p_coordinates);
                break;
            case "JetPlane":
                flyable = new JetPlane(p_id, p_name, p_coordinates);
                break;
            case "Baloon":
                flyable = new Baloon(p_id, p_name, p_coordinates);
                break;
            default:
                System.out.println("Unknown aircraft type: " + p_type);
                break;
        }
        return flyable;
    }
    
}
