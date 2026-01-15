package avaj.simulator;

import avaj.aircraft.*;
import avaj.coordinates.*;
import avaj.weather.WeatherProvider;
public class Simulator {

    public static void main(String[] args) {
        System.out.println("Simulator started.");

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        Coordinates coords = CoordinatesFactory.createCoordinates(10, 20, 30);
        String weather = weatherProvider.getCurrentWeather(coords);
        System.out.println("Current weather at (10, 20, 30): " + weather);
    }
    
}
