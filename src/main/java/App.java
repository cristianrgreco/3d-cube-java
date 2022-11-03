import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class App {

  public static void main(String[] args) {
    var cube = new Cube();
    var view = new View(cube);
    var frame = new Frame("3D Cube");
    var world = new World();

    frame.addView(view);

    newScheduledThreadPool(1)
        .scheduleAtFixedRate(
            () -> {
              view.update(world);
              view.draw();
            },
            0,
            1000 / 120,
            MILLISECONDS);

    frame.show();
  }
}
