package v2;

import org.joml.Vector3f;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class App {

  public static void main(String[] args) {
    var camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
    var cube = new GameItem(Cube.VERTICES, 1f, new Vector3f(10, 10, -10), new Vector3f(0, 0, 0));
    var transformation = new Transformation();
    var view = new View(camera, transformation, cube);
    var window = new Window();
    window.setView(view);

    newScheduledThreadPool(1)
        .scheduleAtFixedRate(
            () -> {
              window.repaint();
            },
            0,
            1000 / 120,
            MILLISECONDS);

    window.show();
  }
}
