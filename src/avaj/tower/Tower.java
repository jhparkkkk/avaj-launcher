package avaj.tower;

import avaj.aircraft.Flyable;
import java.util.List;
import java.util.ArrayList;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable  p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
        }
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionsChanged() {
        System.out.println("Tower: conditionsChanged called.");
    }


}
