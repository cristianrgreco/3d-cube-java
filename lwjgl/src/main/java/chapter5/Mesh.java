package chapter5;

import lombok.Getter;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Mesh {

  @Getter private final int vaoId;
  @Getter private final int vboId;
  @Getter private final int colourVboId;
  @Getter private final int idxVboId;
  @Getter private final int vertexCount;

  public Mesh(float[] positions, float[] colours, int[] indices) {
    this.vertexCount = indices.length;

    this.vaoId = glGenVertexArrays();
    glBindVertexArray(this.vaoId);

    this.vboId = glGenBuffers();
    var verticesBuffer = memAllocFloat(positions.length);
    verticesBuffer.put(positions).flip();
    glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
    memFree(verticesBuffer);
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

    this.colourVboId = glGenBuffers();
    var coloursBuffer = memAllocFloat(colours.length);
    coloursBuffer.put(colours).flip();
    glBindBuffer(GL_ARRAY_BUFFER, this.colourVboId);
    glBufferData(GL_ARRAY_BUFFER, coloursBuffer, GL_STATIC_DRAW);
    memFree(coloursBuffer);
    glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

    this.idxVboId = glGenBuffers();
    var indicesBuffer = memAllocInt(indices.length);
    indicesBuffer.put(indices).flip();
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.idxVboId);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
    memFree(indicesBuffer);
  }

  public void render() {
    glBindVertexArray(this.vaoId);
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glDrawElements(GL_TRIANGLES, this.vertexCount, GL_UNSIGNED_INT, 0);

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
    glBindVertexArray(0);
  }

  public void cleanUp() {
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    glDeleteBuffers(this.vboId);
    glDeleteBuffers(this.colourVboId);
    glDeleteBuffers(this.idxVboId);

    glDeleteVertexArrays(this.vaoId);
  }
}
