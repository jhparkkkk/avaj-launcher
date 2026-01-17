package avaj.simulator;

import avaj.aircraft.Flyable;
import avaj.tower.WeatherTower;

public class SimulationRunner {
    
    public void run(SimulationConfig config) {
        WeatherTower weatherTower = new WeatherTower();

        for (Flyable aircraft : config.getAircrafts()) {
            aircraft.registerTower(weatherTower);
        }

        for (int i = 0; i < config.getSimulationCount(); i++) {
            System.out.println("\nSimulation [" + (i + 1) + "]");
            weatherTower.changeWeather();
        }
    }
}
