public class World {

  private static final double[][] ORTHOGRAPHIC_PROJECTION = {
    {1, 0, 0},
    {0, 1, 0},
    {0, 0, 1}
  };

  private double distance = 0;

  public double[][] getOrthographicProjection() {
    return ORTHOGRAPHIC_PROJECTION;
  }

  public double[][] getWeakPerspectiveProjection(double z) {
    var scale = 1 / (distance - z);
    return new double[][] {
      {scale, 0, 0},
      {0, scale, 0},
      {0, 0, 1}
    };
  }
}
