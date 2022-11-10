package v2;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

public class GameItem {

  @Getter private final Vector3f[] vertices;
  @Getter @Setter private float scale;
  @Getter @Setter private Vector3f position;
  @Getter @Setter private Vector3f rotation;

  public GameItem(Vector3f[] vertices, float scale, Vector3f position, Vector3f rotation) {
    this.vertices = vertices;
    this.scale = scale;
    this.position = position;
    this.rotation = rotation;
  }
}
