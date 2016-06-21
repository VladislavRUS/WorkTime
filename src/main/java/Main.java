import controller.TimeController;
import view.Window;

public class Main {
    public static void main(String[] args) {
        new TimeController(new Window()).begin();
    }
}
