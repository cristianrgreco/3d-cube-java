import javax.swing.*;
import java.awt.*;

public class Frame {

  public static final int WIDTH = 450;
  public static final int HEIGHT = 550;

  private final JFrame jFrame;

  public Frame(String title) {
    this.jFrame = new JFrame(title);
    this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.jFrame.setLocationRelativeTo(null);
    this.jFrame.setSize(WIDTH, HEIGHT);
    this.jFrame.setLayout(new BorderLayout());
    this.jFrame.setResizable(false);
  }

  public void addControls(Controls controls) {
    this.jFrame.getContentPane().add(controls.getJPanel(), BorderLayout.NORTH);
  }

  public void addView(View view) {
    view.register(this.jFrame.getContentPane());
  }

  public void show() {
    jFrame.setVisible(true);
  }
}
