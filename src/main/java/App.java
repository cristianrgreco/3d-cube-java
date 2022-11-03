import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) {
    var world = new World();
    var window = new Window("3D Cube", world);
    window.addComponent(new Cube());
    Executors.newScheduledThreadPool(1)
        .scheduleAtFixedRate(window::draw, 1000, 500, TimeUnit.MILLISECONDS);
    window.show();
  }
}
