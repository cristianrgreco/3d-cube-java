import javax.swing.*;

public class Frame {

  public static final int WIDTH = 400;
  public static final int HEIGHT = 400;

  private final JFrame jFrame;

  public Frame(String title) {
    this.jFrame = new JFrame(title);
    this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.jFrame.setLocationRelativeTo(null);
    this.jFrame.setSize(WIDTH, HEIGHT);
    this.jFrame.setResizable(false);
  }

  public void addView(View view) {
    view.register(this.jFrame.getContentPane());
  }

  public void show() {
    jFrame.setVisible(true);
  }
}
