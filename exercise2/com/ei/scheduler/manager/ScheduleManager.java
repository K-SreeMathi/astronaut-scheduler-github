package com.ei.scheduler.manager;

import com.ei.scheduler.model.Task;
import com.ei.scheduler.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ScheduleManager {

    // Singleton instance
    private static ScheduleManager instance;

    // List of tasks
    private final List<Task> tasks;

    // List of observers
    private final List<Observer> observers;

    // Private constructor
    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Get the single instance
    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    // Add observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notify observers
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Add a new task with conflict check
    public boolean addTask(Task newTask) {
        for (Task t : tasks) {
            if (isOverlap(t, newTask)) {
                notifyObservers("Conflict with task: " + t.getDescription());
                return false;
            }
        }
        tasks.add(newTask);
        Collections.sort(tasks, Comparator.comparing(Task::getStartTime));
        return true;
    }

    // Remove a task by description
    public boolean removeTask(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                tasks.remove(t);
                notifyObservers("Removed task: " + description);
                return true;
            }
        }
        notifyObservers("Task not found: " + description);
        return false;
    }

    // View all tasks
    public List<Task> viewTasks() {
        return new ArrayList<>(tasks);
    }

    // Check overlap between two tasks
    private boolean isOverlap(Task t1, Task t2) {
        return !(t2.getEndTime().isBefore(t1.getStartTime()) || t2.getStartTime().isAfter(t1.getEndTime()));
    }
    // Mark a task as completed by description
    public boolean markTaskCompleted(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                t.markCompleted(); 
                notifyObservers("Task marked completed: " + description);
                return true;
            }
        }
        notifyObservers("Task not found: " + description);
        return false;
    }

}
