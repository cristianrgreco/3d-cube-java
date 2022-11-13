package chapter7;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

import lombok.Getter;
import org.joml.Vector2f;

public class Mouse {

  private final long windowHandle;

  private final Vector2f previousPosition;
  private final Vector2f currentPosition;
  @Getter private final Vector2f offset;

  @Getter private boolean inWindow = false;
  @Getter private boolean leftButtonPressed = false;
  @Getter private boolean rightButtonPressed = false;

  public Mouse(long windowHandle) {
    this.windowHandle = windowHandle;

    this.previousPosition = new Vector2f(-1, -1);
    this.currentPosition = new Vector2f(0, 0);
    this.offset = new Vector2f();

    glfwSetCursorPosCallback(
        this.windowHandle,
        (window, xPos, yPos) -> {
          currentPosition.x = (float) xPos;
          currentPosition.y = (float) yPos;
        });
    glfwSetCursorEnterCallback(this.windowHandle, (window, entered) -> inWindow = entered);
    glfwSetMouseButtonCallback(
        this.windowHandle,
        (window, button, action, mods) -> {
          leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
          rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
  }

  public void update() {
    offset.x = 0;
    offset.y = 0;

    if (previousPosition.x > 0 && previousPosition.y > 0 && inWindow) {
      var deltaX = currentPosition.x - previousPosition.x;
      var deltaY = currentPosition.y - previousPosition.y;

      if (deltaX != 0) {
        offset.y = deltaX;
      }
      if (deltaY != 0) {
        offset.x = deltaY;
      }
    }

    previousPosition.x = currentPosition.x;
    previousPosition.y = currentPosition.y;
  }
}
