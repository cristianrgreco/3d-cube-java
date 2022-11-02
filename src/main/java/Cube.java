public class Cube {
  private final Vector3[] vertices;

  public Cube() {
    vertices =
        new Vector3[] {
          new Vector3(-0.5, -0.5, -0.5),
          new Vector3(0.5, -0.5, -0.5),
          new Vector3(0.5, 0.5, -0.5),
          new Vector3(-0.5, 0.5, -0.5),
          new Vector3(-0.5, -0.5, 0.5),
          new Vector3(0.5, -0.5, 0.5),
          new Vector3(0.5, 0.5, 0.5),
          new Vector3(-0.5, 0.5, 0.5),
        };
  }

  public Vector3[] getVertices() {
    return vertices;
  }
}
