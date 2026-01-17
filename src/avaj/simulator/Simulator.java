package avaj.simulator;

import avaj.coordinates.*;
import avaj.tower.WeatherTower;
import avaj.weather.WeatherProvider;

import avaj.aircraft.*;

import java.io.File;                  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import avaj.exceptions.*;

public class Simulator {

    static String outputFilename = "simulation.txt";

    public static void main(String[] args) {
        PrintStream originalOut = System.out;
        
        try {
        if (args.length != 1) {
            throw new InvalidScenarioException("Usage: java avaj.simulator.Simulator <scenario_file>");
        }
        
        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            throw new InvalidScenarioException("File not found: " + args[0]);
        }
        
        try (PrintStream fileOut = new PrintStream(new FileOutputStream(outputFilename))) {
            System.setOut(fileOut);
            
            int lineCount = 0;
            int simulationCount = 0;
            WeatherTower weatherTower = new WeatherTower();
            
            try (Scanner myReader = new Scanner(inputFile)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    
                    if (data.trim().isEmpty()) {
                        continue;
                    }
                    
                    if (lineCount == 0) {
                        int simulations = Integer.parseInt(data.trim());
                        if (simulations <= 0) {
                            throw new InvalidScenarioException("Number of simulations must be positive.");
                        }
                        simulationCount = simulations;
                    } else {
                        String[] aircraftElements = data.trim().split("\\s+");
                        if (aircraftElements.length != 5) {
                            throw new InvalidScenarioException("Line " + (lineCount + 1) + ": Invalid aircraft data format.");
                        }
                        
                        String type = aircraftElements[0];
                        String name = aircraftElements[1];
                        int longitude = Integer.parseInt(aircraftElements[2]);
                        int latitude = Integer.parseInt(aircraftElements[3]);
                        int height = Integer.parseInt(aircraftElements[4]);
                        
                        Coordinates coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
                        Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates, lineCount);
                        if (aircraft == null) {
                            throw new UnknownAircraftTypeException(type);
                        }
                        
                        aircraft.registerTower(weatherTower);
                    }
                    lineCount++;
                }
            } catch (FileNotFoundException e) {
                throw new InvalidScenarioException("Cannot read file: " + args[0]);
            }

            for (int i = 0; i < simulationCount; i++) {
                System.out.print("\nSimulation " + (i + 1) + ":\n");
                weatherTower.changeWeather();

            }
            
        } 
        System.setOut(originalOut);
        System.out.println("Simulation completed. Output written to simulation.txt");
        
    } catch (AvajException e) {
        System.setOut(originalOut);
        System.err.println("Error: " + e.getMessage());
        System.exit(1);
    } catch (Exception e) {
        System.setOut(originalOut);
        System.err.println("Unexpected error: " + e.getMessage());
        e.printStackTrace();
        System.exit(1);
    }
}
    
}
