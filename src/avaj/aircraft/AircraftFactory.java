package avaj.aircraft;
import  avaj.aircraft.Flyable;

import  avaj.aircraft.Helicopter;
import  avaj.aircraft.JetPlane;
import  avaj.aircraft.Baloon;

public final class AircraftFactory {
    private static long nextId = 1;
    private static final AircraftFactory INSTANCE = new AircraftFactory();

    private AircraftFactory() {}

    public static AircraftFactory getFactory() {
        return INSTANCE;
    }

    public static Flyable newAircraft(String p_type, String p_name, avaj.coordinates.Coordinates p_coordinates) {
        long id = nextId++;
        Flyable flyable = null;

        switch (p_type) {
            case "Helicopter":
                flyable = new Helicopter(id, p_name, p_coordinates);
                break;
            case "JetPlane":
                flyable = new JetPlane(id, p_name, p_coordinates);
                break;
            case "Baloon":
                flyable = new Baloon(id, p_name, p_coordinates);
                break;
            default:
                System.out.println("Unknown aircraft type: " + p_type);
                break;
        }
        return flyable;
    }
    
}
