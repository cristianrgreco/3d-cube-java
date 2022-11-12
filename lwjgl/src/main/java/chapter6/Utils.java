package chapter6;

import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class Utils {

  private Utils() {}

  @SneakyThrows
  public static String resourceAsString(String resource) {
    return Resources.toString(Resources.getResource(resource), StandardCharsets.UTF_8);
  }
}
