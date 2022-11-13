package chapter7;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glDrawElements;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;
import static org.lwjgl.system.MemoryUtil.memAllocInt;
import static org.lwjgl.system.MemoryUtil.memFree;

public class MeshTexture implements Mesh {

  private final int vaoId;
  private final int vboId;
  private final int textureVboId;
  private final Texture texture;
  private final int idxVboId;
  private final int vertexCount;

  public MeshTexture(float[] positions, float[] textureCoords, int[] indices, Texture texture) {
    this.vertexCount = indices.length;
    this.texture = texture;

    this.vaoId = glGenVertexArrays();
    glBindVertexArray(this.vaoId);

    this.vboId = glGenBuffers();
    var verticesBuffer = memAllocFloat(positions.length);
    verticesBuffer.put(positions).flip();
    glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
    memFree(verticesBuffer);
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

    this.textureVboId = glGenBuffers();
    var textureCoordsBuffer = memAllocFloat(textureCoords.length);
    textureCoordsBuffer.put(textureCoords).flip();
    glBindBuffer(GL_ARRAY_BUFFER, this.textureVboId);
    glBufferData(GL_ARRAY_BUFFER, textureCoordsBuffer, GL_STATIC_DRAW);
    memFree(textureCoordsBuffer);
    glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

    this.idxVboId = glGenBuffers();
    var indicesBuffer = memAllocInt(indices.length);
    indicesBuffer.put(indices).flip();
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.idxVboId);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
    memFree(indicesBuffer);
  }

  @Override
  public void render() {
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, this.texture.getTextureId());

    glBindVertexArray(this.vaoId);
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glDrawElements(GL_TRIANGLES, this.vertexCount, GL_UNSIGNED_INT, 0);

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
    glBindVertexArray(0);
  }

  @Override
  public void cleanUp() {
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    glDeleteBuffers(this.vboId);
    glDeleteBuffers(this.textureVboId);
    glDeleteBuffers(this.idxVboId);

    glDeleteVertexArrays(this.vaoId);
  }
}
