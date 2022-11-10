package v1;

public record Vector3(double x, double y, double z) {

  public Vector3 add(Vector3 other) {
    return new Vector3(x + other.x, y + other.y, z + other.z);
  }

  public Vector3 mul(double scalar) {
    return new Vector3(x * scalar, y * scalar, z * scalar);
  }

  public Vector3 mul(double[][] matrix) {
    return new Vector3(
        x * matrix[0][0] + y * matrix[0][1] + z * matrix[0][2],
        x * matrix[1][0] + y * matrix[1][1] + z * matrix[1][2],
        x * matrix[2][0] + y * matrix[2][1] + z * matrix[2][2]);
  }
}
