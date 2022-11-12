package chapter6;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

  private static final float FOV = (float) Math.toRadians(60.0f);
  private static final float Z_NEAR = 0.01f;
  private static final float Z_FAR = 1000f;

  private final Matrix4f projectionMatrix;
  private final Matrix4f modelViewMatrix;
  private final Matrix4f viewMatrix;

  public Transformation() {
    this.projectionMatrix = new Matrix4f();
    this.modelViewMatrix = new Matrix4f();
    this.viewMatrix = new Matrix4f();
  }

  public Matrix4f projectionMatrix(int width, int height) {
    var aspectRatio = (float) width / height;
    return this.projectionMatrix.identity().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
  }

  public Matrix4f viewMatrix(Camera camera) {
    return this.viewMatrix
        .identity()
        .rotate((float) Math.toRadians(camera.getRotation().x), new Vector3f(1, 0, 0))
        .rotate((float) Math.toRadians(camera.getRotation().y), new Vector3f(0, 1, 0))
        .translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
  }

  public Matrix4f modelViewMatrix(Model model, Matrix4f viewMatrix) {
    modelViewMatrix
        .identity()
        .translate(model.getPosition())
        .rotateX((float) Math.toRadians(-model.getRotation().x))
        .rotateY((float) Math.toRadians(-model.getRotation().y))
        .rotateZ((float) Math.toRadians(-model.getRotation().z))
        .scale(model.getScale());

    var viewCurr = new Matrix4f(viewMatrix);
    return viewCurr.mul(modelViewMatrix);
  }
}
