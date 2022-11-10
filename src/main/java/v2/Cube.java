package v2;

import org.joml.Vector3f;

public class Cube {

  public static final Vector3f[] VERTICES =
      new Vector3f[] {
        new Vector3f( -0.5f,  0.5f, 0.0f),
        new Vector3f(-0.5f, -0.5f, 0.0f),
        new Vector3f(0.5f, -0.5f, 0.0f),
        new Vector3f(0.5f,  0.5f, 0.0f)
      };

  //  public static final Vector3f[] VERTICES =
  //      new Vector3f[] {
  //        new Vector3f(-0.5f, -0.5f, -0.5f),
  //        new Vector3f(0.5f, -0.5f, -0.5f),
  //        new Vector3f(0.5f, 0.5f, -0.5f),
  //        new Vector3f(-0.5f, 0.5f, -0.5f),
  //        new Vector3f(-0.5f, -0.5f, 0.5f),
  //        new Vector3f(0.5f, -0.5f, 0.5f),
  //        new Vector3f(0.5f, 0.5f, 0.5f),
  //        new Vector3f(-0.5f, 0.5f, 0.5f),
  //      };
}
