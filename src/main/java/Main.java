import controller.TimeController;
import view.Window;

public class Main {
    public static void main(String[] args) throws Exception {
        new TimeController(new Window()).begin();
    }
}
