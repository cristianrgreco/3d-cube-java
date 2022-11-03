import javax.swing.*;

public class Frame {

  public static final int WIDTH = 400;
  public static final int HEIGHT = 400;

  private final JFrame frame;

  public Frame(String title) {
    this.frame = new JFrame(title);
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.frame.setLocationRelativeTo(null);
    this.frame.setSize(WIDTH, HEIGHT);
    this.frame.setResizable(false);
  }

  public void setView(View view) {
    this.frame.getContentPane().removeAll();
    this.frame.getContentPane().add(view.getPanel());
  }

  public void show() {
    frame.setVisible(true);
  }
}
