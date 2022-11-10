package chapter5;

import org.joml.Matrix4f;

public class Transformation {

  private static final float FOV = (float) Math.toRadians(60.0f);
  private static final float Z_NEAR = 0.01f;
  private static final float Z_FAR = 1000f;

  private final int width;
  private final int height;
  private final Matrix4f projectionMatrix;
  private final Matrix4f worldMatrix;

  public Transformation(int width, int height) {
    this.width = width;
    this.height = height;
    this.projectionMatrix = new Matrix4f();
    this.worldMatrix = new Matrix4f();
  }

  public Matrix4f projectionMatrix() {
    var aspectRatio = (float) this.width / this.height;
    return this.projectionMatrix.identity().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
  }

  public Matrix4f worldMatrix(Model model) {
    return worldMatrix
        .identity()
        .translate(model.getPosition())
        .rotateX((float) Math.toRadians(model.getRotation().x))
        .rotateY((float) Math.toRadians(model.getRotation().y))
        .rotateZ((float) Math.toRadians(model.getRotation().z))
        .scale(model.getScale());
  }
}
