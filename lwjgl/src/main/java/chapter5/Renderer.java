package chapter5;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

  private final ShaderProgram shaderProgram;
  private final Transformation transformation;

  public Renderer(int width, int height) {
    this.shaderProgram = new ShaderProgram();
    this.shaderProgram.createVertexShader(Utils.resourceAsString("vertex.vs"));
    this.shaderProgram.createFragmentShader(Utils.resourceAsString("fragment.fs"));
    this.shaderProgram.link();

    this.transformation = new Transformation(width, height);
    this.shaderProgram.createUniform("projectionMatrix");
    this.shaderProgram.createUniform("worldMatrix");
  }

  public void render(Model[] models) {
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    this.shaderProgram.bind();
    this.shaderProgram.setUniform("projectionMatrix", this.transformation.projectionMatrix());

    for (Model model : models) {
      var worldMatrix = this.transformation.worldMatrix(model);
      this.shaderProgram.setUniform("worldMatrix", worldMatrix);
      model.getMesh().render();
    }

    this.shaderProgram.unbind();
  }

  public void cleanup() {
    this.shaderProgram.cleanup();
  }
}
