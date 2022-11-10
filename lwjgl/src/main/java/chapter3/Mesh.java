package chapter3;

import lombok.Getter;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Mesh {

  @Getter private final int vaoId;
  @Getter private final int vboId;
  @Getter private final int vertexCount;

  public Mesh(float[] positions) {
    FloatBuffer verticesBuffer = null;
    try {
      verticesBuffer = memAllocFloat(positions.length);
      this.vertexCount = positions.length / 3;
      verticesBuffer.put(positions).flip();

      this.vaoId = glGenVertexArrays();
      glBindVertexArray(this.vaoId);

      this.vboId = glGenBuffers();
      glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
      glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
      glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
      glBindBuffer(GL_ARRAY_BUFFER, 0);

      glBindVertexArray(0);
    } finally {
      if (verticesBuffer != null) {
        memFree(verticesBuffer);
      }
    }
  }

  public void cleanUp() {
    glDisableVertexAttribArray(0);

    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glDeleteBuffers(this.vboId);

    glBindVertexArray(0);
    glDeleteVertexArrays(this.vaoId);
  }
}
