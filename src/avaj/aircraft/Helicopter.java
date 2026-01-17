package avaj.aircraft;

import avaj.coordinates.CoordinatesFactory;

public class Helicopter extends Aircraft {
    Helicopter(long p_id, String p_name, avaj.coordinates.Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();
        String message = this.toString();

        switch (weather) {
            case "SUN":
                longitude += 10;
                height += 2;
                message += ": This is hot.";
                break;
            case "RAIN":
                longitude += 5;
                message += ": This is wet.";
                break;
            case "FOG":
                longitude += 1;
                message += ": This is foggy.";
                break;
            case "SNOW":
                height -= 12;
                message += ": This is snowy.";
                break;
            default:
                break;
        }

        if (height <= 0) {
            System.out.println(message);
            this.weatherTower.unregister(this);
            return;
        }
        if (height > 100) {
              height = 100;
        }
        this.coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "Helicopter#" + this.name + "(" + this.id + ")";
    }
}