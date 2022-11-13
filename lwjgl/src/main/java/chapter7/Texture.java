package chapter7;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.nio.ByteBuffer;
import lombok.Getter;
import lombok.SneakyThrows;

public class Texture {

  @Getter private final int textureId;

  @SneakyThrows
  public Texture(String textureFilename) {
    var decoder = new PNGDecoder(Texture.class.getResourceAsStream(textureFilename));
    var buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
    decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
    buffer.flip();

    this.textureId = glGenTextures();
    glBindTexture(GL_TEXTURE_2D, this.textureId);

    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
    glTexImage2D(
        GL_TEXTURE_2D,
        0,
        GL_RGBA,
        decoder.getWidth(),
        decoder.getHeight(),
        0,
        GL_RGBA,
        GL_UNSIGNED_BYTE,
        buffer);
    glGenerateMipmap(GL_TEXTURE_2D);
  }
}
