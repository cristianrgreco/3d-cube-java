public class World {

  public static final int WIDTH = 400;
  public static final int HEIGHT = 400;

  private float angle;

  public World() {
    angle = 45;
  }

  public Vector3 transform(Vector3 vertex) {
    var rotMatrixX =
        new double[][] {
          {1, 0, 0},
          {0, Math.cos(angle), -Math.sin(angle)},
          {0, Math.sin(angle), Math.cos(angle)},
        };
    var rotMatrixY =
        new double[][] {
          {Math.cos(angle), 0, -Math.sin(angle)},
          {0, 1, 0},
          {Math.sin(angle), 0, Math.cos(angle)}
        };
    var rotMatrixZ =
        new double[][] {
          {Math.cos(angle), -Math.sin(angle), 0},
          {Math.sin(angle), Math.cos(angle), 0},
          {0, 0, 1}
        };
    var orthographicProjection =
        new double[][] {
          {1, 0, 0},
          {0, 1, 0},
          {0, 0, 1}
        };

    return vertex.mul(rotMatrixX).mul(rotMatrixY).mul(rotMatrixZ).mul(orthographicProjection);
  }
}
