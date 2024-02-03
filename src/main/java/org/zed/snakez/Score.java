package org.zed.snakez;

import org.jline.jansi.Ansi.Color;

public class Score {
  private Color bg;
  private int score;

  public int getScore() {
    return this.score;
  }

  public void updateScore() {
    this.score++;
  }

  Score(Theme theme) {
    this.score = 0;
    bg = theme.getColor();
  }

  public Color getBg() {
    return bg;
  }
}
