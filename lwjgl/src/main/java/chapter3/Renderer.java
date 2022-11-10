package chapter3;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

  private ShaderProgram shaderProgram;

  public void init() {
    this.shaderProgram = new ShaderProgram();
    this.shaderProgram.createVertexShader(Utils.resourceAsString("vertex.vs"));
    this.shaderProgram.createFragmentShader(Utils.resourceAsString("fragment.fs"));
    this.shaderProgram.link();
  }

  public void render(Mesh mesh) {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    this.shaderProgram.bind();

    glBindVertexArray(mesh.getVaoId());
    glEnableVertexAttribArray(0);

    glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());

    glDisableVertexAttribArray(0);
    glBindVertexArray(0);

    this.shaderProgram.unbind();
  }

  public void cleanup() {
    if (this.shaderProgram != null) {
      this.shaderProgram.cleanup();
    }
  }
}
