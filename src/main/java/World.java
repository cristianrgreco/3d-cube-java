public class World {

  private static final double[][] ORTHOGRAPHIC_PROJECTION = {
    {1, 0, 0},
    {0, 1, 0},
    {0, 0, 1}
  };

  public double[][] getProjectionMatrix() {
    return ORTHOGRAPHIC_PROJECTION;
  }
}
