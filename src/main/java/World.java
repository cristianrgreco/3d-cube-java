public class World {

  public Vector3 transform(Vector3 vertex) {
    var orthographicProjection =
        new double[][] {
          {1, 0, 0},
          {0, 1, 0},
          {0, 0, 1}
        };

    return vertex.mul(orthographicProjection);
  }
}
