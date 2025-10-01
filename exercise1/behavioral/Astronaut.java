package exercise1.behavioral;

public class Astronaut implements Observer {
    private final String name;

    public Astronaut(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("[" + name + "] Notification: " + message);
    }
}
