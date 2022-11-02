import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class CubeComponent {
  private final Cube cube;
  private double scale;
  private Vector3 translation;

  public CubeComponent() {
    cube = new Cube();
    scale = 150;
    translation = new Vector3(150, 150, 0);
  }

  public void paint(World world, Graphics2D g) {
    var vertices =
        Arrays.stream(cube.getVertices())
            .map(world::transform)
            .map(v -> v.mul(scale).add(translation))
            .toArray(Vector3[]::new);

    g.setColor(Color.BLACK);

    drawLine(g, vertices[0], vertices[1]);
    drawLine(g, vertices[0], vertices[1]);
    drawLine(g, vertices[1], vertices[2]);
    drawLine(g, vertices[2], vertices[3]);
    drawLine(g, vertices[3], vertices[0]);

    drawLine(g, vertices[4], vertices[5]);
    drawLine(g, vertices[5], vertices[6]);
    drawLine(g, vertices[6], vertices[7]);
    drawLine(g, vertices[7], vertices[4]);

    drawLine(g, vertices[4], vertices[0]);
    drawLine(g, vertices[5], vertices[1]);
    drawLine(g, vertices[6], vertices[2]);
    drawLine(g, vertices[7], vertices[3]);
  }

  private void drawLine(Graphics2D g, Vector3 pointA, Vector3 pointB) {
    g.draw(new Line2D.Double(pointA.x(), pointA.y(), pointB.x(), pointB.y()));
  }
}
