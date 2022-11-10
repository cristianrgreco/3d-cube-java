package chapter4;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

  private final ShaderProgram shaderProgram;

  public Renderer() {
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
    glEnableVertexAttribArray(1);

    glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

    this.shaderProgram.unbind();
  }

  public void cleanup() {
    this.shaderProgram.cleanup();
  }
}
