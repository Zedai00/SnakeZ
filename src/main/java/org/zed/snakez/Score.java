package org.zed.snakez;

import java.util.ArrayList;
import org.jline.jansi.Ansi.Color;

public class Score {
  private ArrayList<Color> theme;
  private int score;

  public int getScore() {
    return this.score;
  }

  public void updateScore() {
    this.score++;
  }

  Score(Theme theme) {
    this.score = 0;
    this.theme = theme.getTheme();
  }

  public ArrayList<Color> getTheme() {
    return theme;
  }
}
