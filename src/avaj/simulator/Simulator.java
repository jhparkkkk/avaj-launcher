package avaj.simulator;

import avaj.coordinates.*;
import avaj.tower.WeatherTower;
import avaj.weather.WeatherProvider;

import avaj.aircraft.*;

import java.io.File;                  
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {

    public static void main(String[] args) {
        System.out.println("Simulator started.");
        System.out.print(args[0] + "\n");
        File inputFile = new File(args[0]);
        int lineCount = 0;
        int simulationCount = 0;
        WeatherTower weatherTower = new WeatherTower();
        try (Scanner myReader = new Scanner(inputFile)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                if (lineCount == 0) {
                    // Process the first line (e.g., number of simulations)
                    int simulations = Integer.parseInt(data.trim());
                    System.out.println("Number of simulations: " + simulations);
                    simulationCount = simulations;
                } else {
                    String[] aircrafElements = data.split(" ");
                    // Process aircraft data lines
                    if (aircrafElements.length != 5) {
                        System.out.println("Invalid aircraft data format.");
                    } else {
                        String type = aircrafElements[0];
                        String name = aircrafElements[1];
                        int longitude = Integer.parseInt(aircrafElements[2]);
                        int latitude = Integer.parseInt(aircrafElements[3]);
                        int height = Integer.parseInt(aircrafElements[4]);
                        Coordinates coordinates = CoordinatesFactory.createCoordinates(longitude, latitude, height);
                        Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates, lineCount);
                        weatherTower.register(aircraft);
                    }
                }
                lineCount++;

            }
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid number format in the input file.");
        }

        for (int i = 0; i < simulationCount; i++) {
            System.out.println("Simulation step: " + (i + 1));
            weatherTower.changeWeather();
        }

    }
    
}
