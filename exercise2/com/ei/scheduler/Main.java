package com.ei.scheduler;

import com.ei.scheduler.manager.ScheduleManager;
import com.ei.scheduler.model.Priority;
import com.ei.scheduler.model.Task;
import com.ei.scheduler.observer.AstronautObserver;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Astronaut Daily Schedule Organizer ===");

        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new AstronautObserver("Astronaut1"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View All Tasks");
            System.out.println("5. View Tasks by Priority");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();

                    System.out.print("Enter start time (HH:mm): ");
                    String start = sc.nextLine();

                    System.out.print("Enter end time (HH:mm): ");
                    String end = sc.nextLine();

                    System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
                    String pr = sc.nextLine();

                    Task newTask = new Task(
                            desc,
                            LocalTime.parse(start),
                            LocalTime.parse(end),
                            Priority.valueOf(pr.toUpperCase())
                    );

                    if (manager.addTask(newTask)) {
                        System.out.println("Task added successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter description of task to remove: ");
                    String toRemove = sc.nextLine();
                    manager.removeTask(toRemove);
                    break;

                case 3:
                    System.out.print("Enter description of task to mark completed: ");
                    String toComplete = sc.nextLine();
                    manager.markTaskCompleted(toComplete);
                    break;

                case 4:
                    System.out.println("\nAll Tasks:");
                    List<Task> all = manager.viewTasks();
                    for (Task t : all) {
                        System.out.println(t);
                    }
                    break;

                case 5:
                    System.out.print("Enter priority to filter (HIGH, MEDIUM, LOW): ");
                    String filterPr = sc.nextLine();
                    List<Task> filtered = manager.viewTasksByPriority(Priority.valueOf(filterPr.toUpperCase()));
                    for (Task t : filtered) {
                        System.out.println(t);
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
