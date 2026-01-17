package avaj.simulator;

import avaj.exceptions.AvajException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Simulator {
    private static final String OUTPUT_FILENAME = "simulation.txt";
    
    public static void main(String[] args) {
        PrintStream originalOut = System.out;
        SimulationConfig config = null;
        
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException(
                    "Usage: java avaj.simulator.Simulator <scenario_file>"
                );
            }
            
            String scenarioFile = args[0];
            
            if (!new File(scenarioFile).exists()) {
                throw new IllegalArgumentException("File not found: " + scenarioFile);
            }
            
            PrintStream fileOut = new PrintStream(new FileOutputStream(OUTPUT_FILENAME));
            System.setOut(fileOut);
            
            try {
                ScenarioParser parser = new ScenarioParser();
                config = parser.parse(scenarioFile);
                
                SimulationRunner runner = new SimulationRunner();
                runner.run(config);
                
            } finally {
                fileOut.close();
                System.setOut(originalOut);
            }
            
            System.out.println("Simulation completed successfully.");
            System.out.println("Output written to " + OUTPUT_FILENAME);
            
            if (config != null) {
                System.out.println("Aircraft simulated: " + config.getAircraftCount());
                System.out.println("Simulation cycles: " + config.getSimulationCount());
            }
            
        } catch (AvajException e) {
            System.setOut(originalOut);
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
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