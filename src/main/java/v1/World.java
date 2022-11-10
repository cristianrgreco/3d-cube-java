package v1;

import org.joml.Matrix4f;

public class World {

  private static final double[][] ORTHOGRAPHIC_PROJECTION = {
    {1, 0, 0},
    {0, 1, 0},
    {0, 0, 1}
  };

  private static final float FOV = (float) Math.toRadians(60.0f);
  private static final float Z_NEAR = 0.01f;
  private static final float Z_FAR = 1000.0f;

  public Matrix4f getProjectionMatrix() {
    float aspectRatio = ((float) Frame.WIDTH) / Frame.HEIGHT;
    return new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
  }

  public double[][] getOrthographicProjection() {
    return ORTHOGRAPHIC_PROJECTION;
  }

  public double[][] getWeakPerspectiveProjection(double z) {
    double distance = 0;
    var scale = 1 / (distance - z);
    return new double[][] {
      {scale, 0, 0},
      {0, scale, 0},
      {0, 0, 1}
    };
  }
}
