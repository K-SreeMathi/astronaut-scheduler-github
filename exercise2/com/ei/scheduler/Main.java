package com.ei.scheduler;

import com.ei.scheduler.controller.MenuController;
import com.ei.scheduler.manager.ScheduleManager;
import com.ei.scheduler.observer.AstronautObserver;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Astronaut Daily Schedule Organizer ===");

        // Initialize Schedule Manager (Singleton)
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new AstronautObserver("Astronaut1"));

        // Start the interactive menu
        MenuController menu = new MenuController(manager);
        menu.start();
    }
}
