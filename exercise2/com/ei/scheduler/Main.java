package com.ei.scheduler;

import com.ei.scheduler.factory.TaskFactory;
import com.ei.scheduler.manager.ScheduleManager;
import com.ei.scheduler.model.Priority;
import com.ei.scheduler.model.Task;
import com.ei.scheduler.observer.AstronautObserver;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Astronaut Daily Schedule Organizer started...");

        ScheduleManager manager = ScheduleManager.getInstance();

        // Add observer
        manager.addObserver(new AstronautObserver("Astronaut1"));

        // Create tasks
        Task t1 = TaskFactory.createTask("Morning Exercise", "07:00", "08:00", Priority.HIGH);
        Task t2 = TaskFactory.createTask("Team Meeting", "09:00", "10:00", Priority.MEDIUM);
        Task t3 = TaskFactory.createTask("Lunch Break", "12:00", "13:00", Priority.LOW);
        Task t4 = TaskFactory.createTask("Training", "09:30", "10:30", Priority.HIGH); // overlapping

        // Add tasks
        manager.addTask(t1);
        manager.addTask(t2);
        manager.addTask(t3);
        manager.addTask(t4); // conflict notification

        // View all tasks
        System.out.println("\nAll Scheduled Tasks:");
        for (Task t : manager.viewTasks()) {
            System.out.println(t);
        }
 
        manager.removeTask("Team Meeting");
               
        manager.removeTask("Non-existent Task");
    }
}
