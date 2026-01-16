package avaj.aircraft;

import avaj.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower; 
    public abstract void updateConditions();
    public String registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        weatherTower.register(this);
        return " registered to weather tower.";
    };
}
