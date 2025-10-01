package com.ei.scheduler.model;

import java.time.LocalTime;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private boolean completed;

    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    // Getters
    public String getDescription() { return description; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    // Setters
    public void setDescription(String description) { this.description = description; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setPriority(Priority priority) { this.priority = priority; }

    // Mark task as completed
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return startTime + "-" + endTime + ": " + description + " [" + priority + "]" + (completed ? " (Completed)" : "");
    }
}
