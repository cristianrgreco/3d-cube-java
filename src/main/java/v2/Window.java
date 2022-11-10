package v2;

import javax.swing.*;

public class Window {
  public static final float WIDTH = 400;
  public static final float HEIGHT = 400;

  private final JFrame jFrame;

  public Window() {
    jFrame = new JFrame("Spinning 3D Cube");
    jFrame.setSize((int) WIDTH, (int) HEIGHT);
    jFrame.setResizable(false);
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void setView(View view) {
    this.jFrame.getContentPane().removeAll();
    this.jFrame.getContentPane().add(view.getView());
  }

  public void show() {
    this.jFrame.setVisible(true);
  }

  public void repaint() {
    this.jFrame.repaint();
  }
}
