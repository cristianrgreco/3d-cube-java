package chapter3;

import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class Utils {

  private Utils() {}

  @SneakyThrows
  public static String resourceAsString(String resource) {
    return Resources.toString(Resources.getResource(resource), StandardCharsets.UTF_8);
  }
}
