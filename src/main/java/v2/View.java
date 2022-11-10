package v2;

import org.joml.Vector4f;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class View {
  private static final float FOV = (float) Math.toRadians(60.0f);
  private static final float Z_NEAR = 0.01f;
  private static final float Z_FAR = 1000f;

  private final JPanel jPanel;

  public View(Camera camera, Transformation transformation, GameItem... gameItems) {
    this.jPanel =
        new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            var projectionMatrix =
                transformation.projectionMatrix(FOV, Window.WIDTH, Window.HEIGHT, Z_NEAR, Z_FAR);
            var viewMatrix = transformation.viewMatrix(camera);

            for (GameItem gameItem : gameItems) {
              var modelViewMatrix = transformation.modelViewMatrix(gameItem, viewMatrix);
              var viewModelVertices =
                  Arrays.stream(gameItem.getVertices())
                      .map(
                          vertex ->
                              new Vector4f(vertex, 1.0f).mul(projectionMatrix.mul(modelViewMatrix)))
                      .toArray(Vector4f[]::new);

              var g2d = (Graphics2D) g;
              g2d.setRenderingHint(
                  RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
              g2d.setColor(Color.BLACK);

              drawLine(g2d, viewModelVertices[0], viewModelVertices[1]);
              drawLine(g2d, viewModelVertices[1], viewModelVertices[2]);
              drawLine(g2d, viewModelVertices[2], viewModelVertices[3]);
              drawLine(g2d, viewModelVertices[3], viewModelVertices[0]);
//              drawLine(g2d, viewModelVertices[0], viewModelVertices[1]);
//              drawLine(g2d, viewModelVertices[0], viewModelVertices[1]);
//              drawLine(g2d, viewModelVertices[1], viewModelVertices[2]);
//              drawLine(g2d, viewModelVertices[2], viewModelVertices[3]);
//              drawLine(g2d, viewModelVertices[3], viewModelVertices[0]);

//              drawLine(g2d, viewModelVertices[4], viewModelVertices[5]);
//              drawLine(g2d, viewModelVertices[5], viewModelVertices[6]);
//              drawLine(g2d, viewModelVertices[6], viewModelVertices[7]);
//              drawLine(g2d, viewModelVertices[7], viewModelVertices[4]);
//
//              drawLine(g2d, viewModelVertices[4], viewModelVertices[0]);
//              drawLine(g2d, viewModelVertices[5], viewModelVertices[1]);
//              drawLine(g2d, viewModelVertices[6], viewModelVertices[2]);
//              drawLine(g2d, viewModelVertices[7], viewModelVertices[3]);
            }
          }
        };
    this.jPanel.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
              camera.movePosition(0, -1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
              camera.movePosition(0, 1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
              camera.movePosition(-1, 0, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
              camera.movePosition(1, 0, 0);
            }

            if (e.getKeyCode() == KeyEvent.VK_Z) {
              camera.movePosition(0, 0, 1);
            }
            if (e.getKeyCode() == KeyEvent.VK_X) {
              camera.movePosition(0, 0, -1);
            }

            if (e.getKeyCode() == KeyEvent.VK_A) {
              camera.moveRotation(-1, 0, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
              camera.moveRotation(0, -1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
              camera.moveRotation(0, 1, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
              camera.moveRotation(1, 0, 0);
            }
          }
        });
    this.jPanel.setFocusable(true);
    this.jPanel.requestFocus();
  }

  private void drawLine(Graphics2D g, Vector4f a, Vector4f b) {
    g.draw(new Line2D.Float(a.x(), a.y(), b.x(), b.y()));
  }

  public JComponent getView() {
    return jPanel;
  }
}
