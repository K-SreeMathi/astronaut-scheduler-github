package exercise1.behavioral;

import java.util.ArrayList;
import java.util.List;

public class TaskManager implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void addTask(String taskName) {
        System.out.println("Task added: " + taskName);
        notifyObservers("New task added: " + taskName);
    }

    public void removeTask(String taskName) {
        System.out.println("Task removed: " + taskName);
        notifyObservers("Task removed: " + taskName);
    }
}
