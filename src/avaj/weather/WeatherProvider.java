package avaj.weather;

import avaj.coordinates.Coordinates;

public final class WeatherProvider {
    private static final WeatherProvider INSTANCE = new WeatherProvider();
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return INSTANCE;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        int index = seed % 4;
        return weather[index];
    }
    
}
