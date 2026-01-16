package avaj.tower;

import avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(avaj.coordinates.Coordinates coordinates) {
        String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        return weather;
    }

    public WeatherTower() {
        System.out.println("WeatherTower created.");
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}

