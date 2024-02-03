package org.zed.snakez;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;

public class Theme {
  private Color backgroundColor;
  private ArrayList<ArrayList<Color>> themeColors;

  Theme() {
    backgroundColor = Color.BLACK;
    themeColors = createThemeArray();
  }

  public ArrayList<Color> getTheme() {
    while (true) {
      ArrayList<Color> c = themeColors.get(
          ThreadLocalRandom.current().nextInt(themeColors.size()));
      if (c.get(0) != backgroundColor)
        return c;
    }
  }

  private ArrayList<ArrayList<Color>> createThemeArray() {
    Color[] JlineColors = Color.values();
    ArrayList<ArrayList<Color>> Colors = new ArrayList<>();
    int index = 0;
    for (Color foreground : JlineColors) {
      for (Color background : JlineColors) {
        if (foreground != background &&
            (foreground != Color.DEFAULT && background != Color.DEFAULT) &&
            background != Color.BLACK) {
          Colors.add(new ArrayList<>());
          Colors.get(index).add(background);
          Colors.get(index).add(foreground);
          index++;
        }
      }
    }
    return Colors;
  }

  public Color getBackground() {
    return backgroundColor;
  }
}
