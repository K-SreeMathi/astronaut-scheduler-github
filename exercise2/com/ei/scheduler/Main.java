package com.ei.scheduler;

import com.ei.scheduler.model.Task;
import com.ei.scheduler.model.Priority;
import java.time.LocalTime;
import com.ei.scheduler.factory.TaskFactory;
public class Main {
    public static void main(String[] args) {
        System.out.println("Astronaut Daily Schedule Organizer started...");

        Task t1 = new Task("Morning Exercise", LocalTime.of(7,0), LocalTime.of(8,0), Priority.HIGH);
        Task t2 = new Task("Team Meeting", LocalTime.of(9,0), LocalTime.of(10,0), Priority.MEDIUM);
        Task t3 = TaskFactory.createTask("Lunch Break", "12:00", "13:00", Priority.LOW);
        System.out.println(t3);
        System.out.println(t1);
        System.out.println(t2);
    }
}
