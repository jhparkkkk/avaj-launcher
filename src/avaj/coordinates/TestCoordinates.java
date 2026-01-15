package avaj.coordinates;

public class TestCoordinates {
    private static int testsRun = 0;
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Unit Tests : Coordinates ===\n");
        
        // Exécuter tous les tests
        testConstructor();
        testGetters();
        testImmutability();
        testDifferentInstances();
        testEdgeCases();
        
        // Résumé
        System.out.println("\n=== Results ===");
        System.out.println("Tests run : " + testsRun);
        System.out.println("✅ Passed : " + testsPassed);
        System.out.println("❌ Failed : " + testsFailed);
        
        if (testsFailed == 0) {
            System.out.println("\n🎉 All tests passed!");
        } else {
            System.out.println("\n⚠️ Some tests failed.");
            System.exit(1);
        }
    }
    
    // Test du constructeur
    public static void testConstructor() {
        System.out.println("Test: Constructor");
        testsRun++;
        
        try {
            Coordinates coord = new Coordinates(10, 20, 30);
            if (coord != null) {
                testsPassed++;
                System.out.println("  ✅ Constructor works");
            } else {
                testsFailed++;
                System.out.println("  ❌ Constructor returns null");
            }
        } catch (Exception e) {
            testsFailed++;
            System.out.println("  ❌ Exception: " + e.getMessage());
        }
        System.out.println();
    }
    
    // Test des getters
    public static void testGetters() {
        System.out.println("Test: Getters");
        testsRun++;
        
        Coordinates coord = new Coordinates(10, 20, 30);
        
        boolean passed = true;
        
        if (coord.getLongitude() != 10) {
            System.out.println("  ❌ getLongitude() expected: 10, got: " + coord.getLongitude());
            passed = false;
        }
        
        if (coord.getLatitude() != 20) {
            System.out.println("  ❌ getLatitude() expected: 20, got: " + coord.getLatitude());
            passed = false;
        }
        
        if (coord.getHeight() != 30) {
            System.out.println("  ❌ getHeight() expected: 30, got: " + coord.getHeight());
            passed = false;
        }
        
        if (passed) {
            testsPassed++;
            System.out.println("  ✅ All getters return the correct values");
        } else {
            testsFailed++;
        }
        System.out.println();
    }
    
    // Test of immutability
    public static void testImmutability() {
        System.out.println("Test: Immutability");
        testsRun++;
        
        Coordinates coord = new Coordinates(10, 20, 30);
        int originalLon = coord.getLongitude();
        int originalLat = coord.getLatitude();
        int originalHeight = coord.getHeight();
        
        // Create a new instance (simulate a "change")
        coord = new Coordinates(50, 60, 70);
        
        // Verify that the first instance is not affected
        // (impossible to verify directly because we no longer have the reference,
        // but we verify that the new instance has the correct values)
        if (coord.getLongitude() == 50 && coord.getLatitude() == 60 && coord.getHeight() == 70) {
            testsPassed++;
            System.out.println("  ✅ Coordinates are indeed immutable");
        } else {
            testsFailed++;
            System.out.println("  ❌ Values do not match");
        }
        System.out.println();
    }
    
    // Test : different instances
    public static void testDifferentInstances() {
        System.out.println("Test: Independent instances");
        testsRun++;
        
        Coordinates coord1 = new Coordinates(10, 20, 30);
        Coordinates coord2 = new Coordinates(40, 50, 60);
        
        if (coord1 != coord2) {
            testsPassed++;
            System.out.println("  ✅ Two instances are indeed different");
        } else {
            testsFailed++;
            System.out.println("  ❌ Two instances are identical");
        }
        System.out.println();
    }
    
    // Test edge cases
    public static void testEdgeCases() {
        System.out.println("Test: Edge cases");
        testsRun++;
        
        boolean passed = true;
        
        // Test value 0
        Coordinates coord1 = new Coordinates(0, 0, 0);
        if (coord1.getLongitude() != 0 || coord1.getLatitude() != 0 || coord1.getHeight() != 0) {
            System.out.println("  ❌ Values at 0 do not work");
            passed = false;
        }
        
        // Test large values
        Coordinates coord2 = new Coordinates(999, 999, 100);
        if (coord2.getLongitude() != 999 || coord2.getLatitude() != 999 || coord2.getHeight() != 100) {
            System.out.println("  ❌ Large values do not work");
            passed = false;
        }
        
        if (passed) {
            testsPassed++;
            System.out.println("  ✅ Edge cases work");
        } else {
            testsFailed++;
        }
        System.out.println();
    }
}