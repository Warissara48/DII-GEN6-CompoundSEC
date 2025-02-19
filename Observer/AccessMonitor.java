package Observer;
import java.util.ArrayList;
import java.util.List;

public class AccessMonitor {
    private List<AccessObserver> observers = new ArrayList<>();

    public void addObserver(AccessObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (AccessObserver observer : observers) {
            observer.update(message);

        }
    }
}