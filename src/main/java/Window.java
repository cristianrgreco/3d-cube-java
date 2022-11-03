import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Window {

  private static final Map<RenderingHints.Key, Object> RENDERING_HINTS =
      Map.of(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

  private final JFrame frame;
  private final List<Component> components;

  public Window(String title, World world) {
    components = new ArrayList<>();
    frame =
        new JFrame(title) {
          @Override
          public void paint(Graphics g) {
            super.paint(g);
            var g2d = (Graphics2D) g;
            g2d.addRenderingHints(RENDERING_HINTS);
            g2d.clearRect(0, 0, World.WIDTH, World.HEIGHT);
            components.forEach(component -> component.paint(g2d, world));
          }
        };
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setSize(World.WIDTH, World.HEIGHT);
    frame.setResizable(false);
  }

  public void addComponent(Component component) {
    components.add(component);
  }

  public void show() {
    frame.setVisible(true);
  }
}
