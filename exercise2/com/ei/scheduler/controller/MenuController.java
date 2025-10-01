package com.ei.scheduler.controller;

import com.ei.scheduler.manager.ScheduleManager;
import com.ei.scheduler.model.Priority;
import com.ei.scheduler.model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
        String desc = sc.nextLine().trim();
        if (desc.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

        try {
            System.out.print("Enter start time (HH:mm): ");
            LocalTime startTime = LocalTime.parse(sc.nextLine().trim());

            System.out.print("Enter end time (HH:mm): ");
            LocalTime endTime = LocalTime.parse(sc.nextLine().trim());

            if (endTime.isBefore(startTime)) {
                System.out.println("End time must be after start time.");
                return;
            }

            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            String pr = sc.nextLine().trim().toUpperCase();
            Priority priority;
            try {
                priority = Priority.valueOf(pr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Use HIGH, MEDIUM, or LOW.");
                return;
            }

            Task newTask = new Task(desc, startTime, endTime, priority);
            if (manager.addTask(newTask)) {
                System.out.println("Task added successfully.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:mm (e.g., 07:30).");
        }
    }

    private void removeTask() {
        System.out.print("Enter description of task to remove: ");
        String desc = sc.nextLine().trim();
        if (desc.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        manager.removeTask(desc);
    }

    private void markCompleted() {
        System.out.print("Enter description of task to mark completed: ");
        String desc = sc.nextLine().trim();
        if (desc.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
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
        String pr = sc.nextLine().trim().toUpperCase();
        try {
            Priority priority = Priority.valueOf(pr);
            List<Task> filtered = manager.viewTasksByPriority(priority);
            if (filtered.isEmpty()) {
                System.out.println("No tasks found for priority " + pr);
            } else {
                filtered.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority entered. Use HIGH, MEDIUM, or LOW.");
        }
    }

    // Helper method for safe integer input
    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
