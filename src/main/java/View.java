import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class View {

  private final JPanel panel;

  public View(World world, Drawable... drawables) {
    this.panel =
        new JPanel() {
          @Override
          public void paint(Graphics g) {
            super.paintComponent(g);
            for (Drawable drawable : drawables) {
              var vertices = drawable.update();
              var worldVertices = Arrays.stream(vertices).map(world::transform).toArray(Vector3[]::new);
              drawable.draw((Graphics2D) g, worldVertices);
            }
          }
        };
  }

  public void tick() {
    this.panel.repaint();
  }

  public JPanel getPanel() {
    return panel;
  }
}
