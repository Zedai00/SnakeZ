package org.zed.snakez;

import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;

public class Utils {
  public static Color getRandomColor() {
    Color[] color = Color.values();
    return color[ThreadLocalRandom.current().nextInt(color.length)];
  }
}
