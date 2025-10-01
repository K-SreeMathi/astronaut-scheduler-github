package com.ei.scheduler.observer;

 
public class AstronautObserver implements Observer {

    private final String name;

    public AstronautObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("[" + name + "] Notification: " + message);
    }
}
