package avaj.aircraft;

import avaj.coordinates.CoordinatesFactory;

public class JetPlane extends Aircraft {
    JetPlane(long p_id, String p_name, avaj.coordinates.Coordinates p_coordinates) {
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
                latitude += 10;
                height += 5;
                message += ": Oh no sunny day!";
                break;
            case "RAIN":
                latitude += 5;
                message += ": Oh no rainy day!";
                break;
            case "FOG":
                latitude += 1;
                message += ": Oh no foggy day!";
                break;
            case "SNOW":
                height -= 7;
                message += ": Oh no snowy day!";
                break;
            default:
                break;
        }

        if (height <= 0) {
            System.out.println(this.toString() + ": Landing.");
            this.weatherTower.unregister(this);
        }
        if (height > 100) {
              height = 100;
        }
        this.coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
        System.out.println(message);
    }
    @Override
    public String toString() {
        return "JetPlane#" + this.name + "(" + this.id + ")";
    }
}