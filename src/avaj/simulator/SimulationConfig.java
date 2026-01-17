package avaj.simulator;

import avaj.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;

public class SimulationConfig {

    private final int simulationCount;
    private final List<Flyable> aircrafts;

    public SimulationConfig(int p_simulationCount) {
        this.simulationCount = p_simulationCount;
        this.aircrafts = new ArrayList<>();
    }

    public void addAircraft(Flyable p_aircraft) {
        this.aircrafts.add(p_aircraft);
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    public List<Flyable> getAircrafts() {
        return aircrafts;
    }

    public int getAircraftCount() {
        return aircrafts.size();
    }
    
}
