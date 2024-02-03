package org.zed.snakez;

import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;

public class Theme {
  private Color backgroundColor;
  private Color previousColor;

  Theme() {
    backgroundColor = Color.BLACK;
  }

  public Color getColor() {
    Color[] colors = { Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA,
        Color.YELLOW, Color.CYAN, Color.WHITE };
    while (true) {
      Color c = colors[ThreadLocalRandom.current().nextInt(colors.length)];
      if (c != backgroundColor && c != previousColor)
        previousColor = c;
      return c;
    }
  }

  public Color getBackground() {
    return backgroundColor;
  }
}
