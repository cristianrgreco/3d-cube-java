package v2;

import lombok.Getter;
import org.joml.Vector3f;

public class Camera {

  @Getter private final Vector3f position;
  @Getter private final Vector3f rotation;

  public Camera(Vector3f position, Vector3f rotation) {
    this.position = position;
    this.rotation = rotation;
  }

  public void movePosition(float offsetX, float offsetY, float offsetZ) {
    if (offsetZ != 0) {
      this.position.x += (float) Math.sin(Math.toRadians(this.rotation.y)) * -1.0f * offsetZ;
      this.position.z += (float) Math.cos(Math.toRadians(this.rotation.y)) * offsetZ;
    }
    if (offsetX != 0) {
      this.position.x += (float) Math.sin(Math.toRadians(this.rotation.y - 90)) * -1.0f * offsetX;
      this.position.z += (float) Math.cos(Math.toRadians(this.rotation.y - 90)) * offsetX;
    }
    this.position.y += offsetY;
  }

  public void moveRotation(float offsetX, float offsetY, float offsetZ) {
    this.rotation.x += offsetX;
    this.rotation.y += offsetY;
    this.rotation.z += offsetZ;
  }
}
