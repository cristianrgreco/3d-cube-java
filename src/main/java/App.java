import javax.swing.*;
import java.awt.*;

public class App {

  public static void main(String[] args) {
    var world = new World();
    var cubeComponent = new CubeComponent();

    var frame = new JFrame("3D Cube") {
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        var g2d = (Graphics2D) g;
        g.clearRect(0, 0, 400, 400);
        cubeComponent.paint(world, g2d);
      }
    };
    frame.setSize(400, 400);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);
  }
}
