package com.ei.scheduler.controller;

import com.ei.scheduler.manager.ScheduleManager;
import com.ei.scheduler.model.Priority;
import com.ei.scheduler.model.Task;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final ScheduleManager manager;
    private final Scanner sc;

    public MenuController(ScheduleManager manager) {
        this.manager = manager;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> markCompleted();
                case 4 -> viewAllTasks();
                case 5 -> viewTasksByPriority();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }

    private void printMenu() {
        System.out.println("\n1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. View All Tasks");
        System.out.println("5. View Tasks by Priority");
        System.out.println("6. Exit");
    }

    private void addTask() {
        System.out.print("Enter description: ");
        String desc = sc.nextLine();

        System.out.print("Enter start time (HH:mm): ");
        String start = sc.nextLine();

        System.out.print("Enter end time (HH:mm): ");
        String end = sc.nextLine();

        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
        String pr = sc.nextLine();

        try {
            Task newTask = new Task(
                    desc,
                    LocalTime.parse(start),
                    LocalTime.parse(end),
                    Priority.valueOf(pr.toUpperCase())
            );

            if (manager.addTask(newTask)) {
                System.out.println("Task added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. " + e.getMessage());
        }
    }

    private void removeTask() {
        System.out.print("Enter description of task to remove: ");
        String desc = sc.nextLine();
        manager.removeTask(desc);
    }

    private void markCompleted() {
        System.out.print("Enter description of task to mark completed: ");
        String desc = sc.nextLine();
        manager.markTaskCompleted(desc);
    }

    private void viewAllTasks() {
        System.out.println("\nAll Tasks:");
        List<Task> all = manager.viewTasks();
        if (all.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            all.forEach(System.out::println);
        }
    }

    private void viewTasksByPriority() {
        System.out.print("Enter priority to filter (HIGH, MEDIUM, LOW): ");
        String pr = sc.nextLine();
        try {
            List<Task> filtered = manager.viewTasksByPriority(Priority.valueOf(pr.toUpperCase()));
            if (filtered.isEmpty()) {
                System.out.println("No tasks found for priority " + pr.toUpperCase());
            } else {
                filtered.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority entered.");
        }
    }

    // Helper method for safe integer input
    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
