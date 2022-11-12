package chapter6;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

public class Model {

  @Getter private final Mesh mesh;

  @Getter private final Vector3f position;
  @Getter @Setter private float scale;
  @Getter private final Vector3f rotation;

  public Model(Mesh mesh) {
    this.mesh = mesh;
    this.position = new Vector3f(0, 0, 0);
    this.scale = 1;
    this.rotation = new Vector3f(0, 0, 0);
  }

  public void setPosition(float x, float y, float z) {
    this.position.x = x;
    this.position.y = y;
    this.position.z = z;
  }

  public void setRotation(float x, float y, float z) {
    this.rotation.x = x;
    this.rotation.y = y;
    this.rotation.z = z;
  }
}
