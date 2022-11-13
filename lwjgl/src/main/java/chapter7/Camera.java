package chapter7;

import lombok.Getter;
import org.joml.Vector3f;

public class Camera {

  private static final float KEYBOARD_SENSITIVITY = 0.05f;
  private static final float MOUSE_SENSITIVITY = 1;

  @Getter private final Vector3f position;
  @Getter private final Vector3f rotation;

  public Camera() {
    this.position = new Vector3f(0, 0, 0);
    this.rotation = new Vector3f(0, 0, 0);
  }

  public void movePosition(Vector3f offset) {
    offset.mul(KEYBOARD_SENSITIVITY);

    if (offset.z != 0) {
      position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * offset.z;
      position.z += (float) Math.cos(Math.toRadians(rotation.y)) * offset.z;
    }
    if (offset.x != 0) {
      position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offset.x;
      position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * offset.x;
    }
    position.y += offset.y;
  }

  public void moveRotation(Vector3f offset) {
    offset.mul(MOUSE_SENSITIVITY);

    rotation.x += offset.x;
    rotation.y += offset.y;
    rotation.z += offset.z;
  }
}
