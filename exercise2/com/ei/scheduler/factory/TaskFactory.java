package com.ei.scheduler.factory;

import com.ei.scheduler.model.Task;
import com.ei.scheduler.model.Priority;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Factory class to create Task objects safely with validation.
 */
public class TaskFactory {

    /**
     * Creates a Task object after validating inputs.
     *
     * @param description Task description
     * @param startTimeStr Start time in HH:mm format
     * @param endTimeStr End time in HH:mm format
     * @param priority Task priority
     * @return Task object
     * @throws IllegalArgumentException if input is invalid
     */
    public static Task createTask(String description, String startTimeStr, String endTimeStr, Priority priority) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }

        LocalTime startTime;
        LocalTime endTime;

        try {
            startTime = LocalTime.parse(startTimeStr);
            endTime = LocalTime.parse(endTimeStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm");
        }

        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        return new Task(description, startTime, endTime, priority);
    }
}
