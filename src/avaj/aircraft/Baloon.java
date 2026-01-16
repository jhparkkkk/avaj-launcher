package avaj.aircraft;

import avaj.coordinates.CoordinatesFactory;

public class Baloon extends Aircraft {
    Baloon(long p_id, String p_name, avaj.coordinates.Coordinates p_coordinates) {
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
                longitude += 2;
                height += 4;
                message += ": Let's enjoy the good weather and take some pics.";
                break;
            case "RAIN":
                height -= 5;
                message += ": It's raining. Better watch out for lightning.";
                break;
            case "FOG":
                height -= 3;
                message += ": Can't see much through this fog.";
                break;
            case "SNOW":
                height -= 15;
                message += ": It's snowing. We're gonna need some serious insulation.";
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
        return "Baloon#" + this.name + "(" + this.id + ")";
    }
}
