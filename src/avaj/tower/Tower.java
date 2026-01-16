package avaj.tower;

import avaj.aircraft.Flyable;
import java.util.List;
import java.util.ArrayList;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable  p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
            System.out.println("Tower says: " + p_flyable + " registered to weather tower.");
            p_flyable.registerTower((WeatherTower) this);
        }

    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            Flyable flyable = observers.get(i);
            flyable.updateConditions();
        }
    }


}
