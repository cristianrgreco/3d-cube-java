package chapter5;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

  private int vertexShaderId;
  private int fragmentShaderId;
  private final int programId;
  private final Map<String, Integer> uniforms;

  public ShaderProgram() {
    this.programId = glCreateProgram();
    if (this.programId == 0) {
      throw new RuntimeException("Could not create Shader");
    }
    this.uniforms = new HashMap<>();
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

  public void createUniform(String uniformName) {
    var uniformLocation = glGetUniformLocation(this.programId, uniformName);
    if (uniformLocation < 0) {
      throw new RuntimeException("Could not find uniform: " + uniformName);
    }
    uniforms.put(uniformName, uniformLocation);
  }

  public void setUniform(String uniformName, Matrix4f value) {
    var uniformLocation = this.uniforms.get(uniformName);
    if (uniformLocation == null) {
      throw new RuntimeException("Uniform does not exist: " + uniformName);
    }
    try (var stack = MemoryStack.stackPush()) {
      var buffer = stack.mallocFloat(16);
      value.get(buffer);
      glUniformMatrix4fv(uniformLocation, false, buffer);
    }
  }
}
