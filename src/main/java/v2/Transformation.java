package v2;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/chapter06/chapter6.html
 * https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/chapter08/chapter8.html
 */
public class Transformation {
  private final Matrix4f modelViewMatrix;
  private final Matrix4f projectionMatrix;
  private final Matrix4f viewMatrix;

  public Transformation() {
    this.modelViewMatrix = new Matrix4f();
    this.projectionMatrix = new Matrix4f();
    this.viewMatrix = new Matrix4f();
  }

  public Matrix4f projectionMatrix(float fov, float width, float height, float zNear, float zFar) {
    var aspectRatio = width / height;
    return projectionMatrix.identity().perspective(fov, aspectRatio, zNear, zFar);
  }

  public Matrix4f viewMatrix(Camera camera) {
    return viewMatrix
        .identity()
        .rotate((float) Math.toRadians(camera.getRotation().x()), new Vector3f(1, 0, 0))
        .rotate((float) Math.toRadians(camera.getRotation().y()), new Vector3f(0, 1, 0))
        .translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());
  }

  public Matrix4f modelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
    modelViewMatrix
            .identity()
            .translate(gameItem.getPosition())
            .rotateX((float) Math.toRadians(-gameItem.getRotation().x()))
            .rotateY((float) Math.toRadians(-gameItem.getRotation().y()))
            .rotateZ((float) Math.toRadians(-gameItem.getRotation().z()))
            .scale(gameItem.getScale());
    return new Matrix4f(viewMatrix).mul(modelViewMatrix);
  }
}
