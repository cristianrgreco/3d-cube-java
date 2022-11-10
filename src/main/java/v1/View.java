package v1;

import javax.swing.*;
import java.awt.*;

public class View {

  private final Drawable[] drawables;
  private final JPanel panel;

  public View(Drawable... drawables) {
    this.drawables = drawables;
    this.panel =
        new JPanel() {
          @Override
          public void paint(Graphics g) {
            super.paintComponent(g);
            for (Drawable drawable : drawables) {
              drawable.draw((Graphics2D) g);
            }
          }
        };
  }

  public void update(World world) {
    for (Drawable drawable : this.drawables) {
      drawable.update(world);
    }
  }

  public void draw() {
    this.panel.repaint();
  }

  public void register(Container container) {
    container.add(this.panel, BorderLayout.CENTER);
  }
}
