package com.ei.scheduler.observer;

 
public interface Observer {
    /**
     * Called when there is a task update or conflict.
     * @param message Notification message
     */
    void update(String message);
}
