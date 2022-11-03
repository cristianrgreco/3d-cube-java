import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Map;

public class Cube implements Drawable {

  private static final Map<RenderingHints.Key, Object> RENDERING_HINTS =
      Map.of(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

  private static final Vector3[] VERTICES =
      new Vector3[] {
        new Vector3(-0.5, -0.5, -0.5),
        new Vector3(0.5, -0.5, -0.5),
        new Vector3(0.5, 0.5, -0.5),
        new Vector3(-0.5, 0.5, -0.5),
        new Vector3(-0.5, -0.5, 0.5),
        new Vector3(0.5, -0.5, 0.5),
        new Vector3(0.5, 0.5, 0.5),
        new Vector3(-0.5, 0.5, 0.5),
      };

  private Vector3[] vertices = VERTICES;
  private double angle = 0;
  private double scale = 150;
  private double rotationVelocity = 0.01;
  private Vector3 position = new Vector3(Frame.WIDTH / 2, Frame.HEIGHT / 2, 0);

  @Override
  public void update(World world) {
    this.angle += rotationVelocity;

    var rotMatrixX =
        new double[][] {
          {1, 0, 0},
          {0, Math.cos(angle), -Math.sin(angle)},
          {0, Math.sin(angle), Math.cos(angle)},
        };
    var rotMatrixY =
        new double[][] {
          {Math.cos(angle), 0, -Math.sin(angle)},
          {0, 1, 0},
          {Math.sin(angle), 0, Math.cos(angle)}
        };
    var rotMatrixZ =
        new double[][] {
          {Math.cos(angle), -Math.sin(angle), 0},
          {Math.sin(angle), Math.cos(angle), 0},
          {0, 0, 1}
        };

    this.vertices =
        Arrays.stream(VERTICES)
            .map(
                vertex ->
                    vertex
                        .mul(rotMatrixX)
                        .mul(rotMatrixY)
                        .mul(rotMatrixZ)
                        .mul(this.scale)
                        .add(this.position)
                        .mul(world.getProjectionMatrix()))
            .toArray(Vector3[]::new);
  }

  @Override
  public void draw(Graphics2D g) {
    g.setRenderingHints(RENDERING_HINTS);

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

  private void drawLine(Graphics2D g, Vector3 a, Vector3 b) {
    g.draw(new Line2D.Double(a.x(), a.y(), b.x(), b.y()));
  }
}
