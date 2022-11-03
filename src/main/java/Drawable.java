import java.awt.*;

public interface Drawable {

    Vector3[] update();

    void draw(Graphics2D g, Vector3[] vertices);
}
