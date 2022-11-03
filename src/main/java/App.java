import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class App {

  public static void main(String[] args) {
    var world = new World();
    var cube = new Cube();
    var view = new View(world, cube);
    var frame = new Frame("3D Cube");

    frame.setView(view);
    newScheduledThreadPool(1).scheduleAtFixedRate(view::tick, 0, 1000 / 120, MILLISECONDS);
    frame.show();
  }
}
