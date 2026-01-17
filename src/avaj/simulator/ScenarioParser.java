package avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import avaj.aircraft.AircraftFactory;
import avaj.aircraft.Flyable;
import avaj.coordinates.Coordinates;
import avaj.coordinates.CoordinatesFactory;
import avaj.exceptions.InvalidScenarioException;

public class ScenarioParser {
    
    public SimulationConfig parse(String filename) throws InvalidScenarioException {
        int lineNumber = 0;
        SimulationConfig config = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (lineNumber == 0) {
                    int simulationCount = parseSimulationCount(line);
                    config = new SimulationConfig(simulationCount);
                } else {
                    if (config == null) {
                        throw new InvalidScenarioException("Missing simulation count before aircraft data.");
                    }
                    Flyable aircraft = parseAircraft(line, lineNumber);
                    config.addAircraft(aircraft);
                }
                lineNumber++;
            }
            if (config == null) {
                throw new InvalidScenarioException("Scenario file is empty or invalid.");
            }
            if (config.getAircraftCount() == 0) {
                throw new InvalidScenarioException("No aircraft defined in the scenario.");
            }
        } catch (IOException e) {
            throw new InvalidScenarioException("Error parsing scenario file: " + e.getMessage());
        }

        return config;
    }

    private int parseSimulationCount(String line) throws InvalidScenarioException {
        int simulations;
        try {
            simulations = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("First line must be a valid integer for number of simulations.", e);
        }

        if (simulations <= 0) {
            throw new InvalidScenarioException("Number of simulations must be positive.");
        }
        return simulations;
    }

    private Flyable parseAircraft(String line, int lineNumber) throws InvalidScenarioException {
        String[] fields = line.split("\\s+");
        if (fields.length != 5) {
            throw new InvalidScenarioException("Line " + (lineNumber) + ": Expected 5 fields [TYPE NAME LON LAT HEIGHT], found " + fields.length + ".");           
        }

        try {
            String type = fields[0];
            String name = fields[1];
            int longitude = Integer.parseInt(fields[2]);
            int latitude = Integer.parseInt(fields[3]);
            int height = Integer.parseInt(fields[4]);

            if (longitude < 0 || latitude < 0 || height < 0) {
                throw new InvalidScenarioException("Line " + (lineNumber) + ": Coordinates must be non-negative integers.");
            }
            validateCoordinates(longitude, latitude, height, lineNumber);

            Coordinates coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
            Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates);

            return aircraft;
     
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Line " + (lineNumber) + ": Coordinates must be valid integers.", e);
        }

    }

    private void validateCoordinates(int longitude, int latitude, int height, int lineNumber) throws InvalidScenarioException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidScenarioException("Line " + (lineNumber) + ": Coordinates must be non-negative integers.");
        }

    }
}
