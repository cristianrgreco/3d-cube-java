package chapter4;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

  private int vertexShaderId;
  private int fragmentShaderId;
  private final int programId;

  public ShaderProgram() {
    this.programId = glCreateProgram();
    if (this.programId == 0) {
      throw new RuntimeException("Could not create Shader");
    }
  }

  public void createVertexShader(String shaderCode) {
    this.vertexShaderId = this.create(shaderCode, GL_VERTEX_SHADER);
  }

  public void createFragmentShader(String shaderCode) {
    this.fragmentShaderId = this.create(shaderCode, GL_FRAGMENT_SHADER);
  }

  private int create(String shaderCode, int shaderType) {
    var shaderId = glCreateShader(shaderType);
    if (shaderId == 0) {
      throw new RuntimeException("Error creating shader. Type: " + shaderType);
    }

    glShaderSource(shaderId, shaderCode);
    glCompileShader(shaderId);
    if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
      throw new RuntimeException(
          "Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
    }
    glAttachShader(this.programId, shaderId);
    return shaderId;
  }

  public void link() {
    glLinkProgram(this.programId);
    if (glGetProgrami(this.programId, GL_LINK_STATUS) == 0) {
      throw new RuntimeException(
          "Error linking Shader code: " + glGetProgramInfoLog(this.programId, 1024));
    }

    if (this.vertexShaderId != 0) {
      glDetachShader(this.programId, this.vertexShaderId);
    }
    if (this.fragmentShaderId != 0) {
      glDetachShader(this.programId, this.fragmentShaderId);
    }
  }

  public void bind() {
    glUseProgram(this.programId);
  }

  public void unbind() {
    glUseProgram(0);
  }

  public void cleanup() {
    this.unbind();

    if (programId != 0) {
      glDeleteProgram(this.programId);
    }
  }
}
