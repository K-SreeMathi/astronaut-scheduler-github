package exercise1.behavioral;

public class ObserverExample {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Astronaut astro1 = new Astronaut("Astronaut1");
        Astronaut astro2 = new Astronaut("Astronaut2");

        manager.addObserver(astro1);
        manager.addObserver(astro2);

        manager.addTask("Morning Exercise");
        manager.addTask("Team Meeting");
        manager.removeTask("Team Meeting");
    }
}
