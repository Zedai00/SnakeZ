package org.zed.snakez;

import java.util.Arrays;
import org.jline.jansi.Ansi;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

class Renderer {
  private Terminal terminal;
  private String[][] screen;
  private String[][] buffer;
  private Game game;
  private Food food;
  private Theme theme;
  private Color borderFg;
  private AsciiArt ascii;
  private Color borderBg;
  private Score score;

  public Renderer(Terminal terminal, Game game, Theme theme, Food food,
                  Score score, AsciiArt ascii) {
    this.terminal = terminal;
    this.game = game;
    this.food = food;
    this.score = score;
    this.ascii = ascii;
    screen = new String[terminal.getHeight()][terminal.getWidth()];
    buffer = new String[terminal.getHeight()][terminal.getWidth()];
    this.theme = theme;
    initializeArrays();
    borderBg = theme.getColor();
    borderFg = theme.getColor();
    drawBorder(borderFg, borderBg);
    ascii = new AsciiArt(terminal, theme);
  }

  public Color getBorderFg() { return borderFg; }

  public Color getBorderBg() { return borderBg; }

  public void initializeArrays() {
    for (int i = 0; i < terminal.getHeight(); i++) {
      Arrays.fill(
          screen[i],
          Ansi.ansi().bg(theme.getBackground()).a(" ").reset().toString());
      Arrays.fill(
          buffer[i],
          Ansi.ansi().bg(theme.getBackground()).a(" ").reset().toString());
    }
  }

  public void renderGame(Snake snake, Food food) {
    renderScore();
    if (snake.foodCheck(food, terminal)) {
      game.updateSpeed();
      food.setSymbol(food.getRandomSymbol());
    }
    renderFood(food.getX(), food.getY(), food.getColor());
    renderSnake(snake);
    updateScreen();
  }

  private void renderScore() {
    drawBorder(borderBg, borderFg);
    String[] scoreArray = String.format("Score: " + score.getScore()).split("");
    int y = (int)(terminal.getWidth() * 0.05);
    for (int i = 0; i < scoreArray.length; i++) {
      String s =
          Ansi.ansi().bg(score.getBg()).a(scoreArray[i]).reset().toString();
      buffer[0][y + i] = s;
    }
  }

  public void updateScreen() {
    for (int i = 0; i < terminal.getHeight(); i++) {
      for (int j = 0; j < terminal.getWidth(); j++) {
        if (buffer[i][j] != screen[i][j]) {
          terminal.puts(Capability.cursor_address, i, j);
          terminal.flush();
          System.out.print(String.valueOf(buffer[i][j]));
          screen[i][j] = buffer[i][j];
        }
      }
    }
    terminal.flush();
  }

  public void drawBorder(Color bg, Color fg) {
    for (int i = 0; i < terminal.getWidth(); i++) {
      buffer[0][i] = Ansi.ansi().bg(bg).a(" ").reset().toString();
      buffer[terminal.getHeight() - 1][i] =
          Ansi.ansi().bg(bg).a(" ").reset().toString();
    }
    for (int i = 0; i < terminal.getHeight(); i++) {
      buffer[i][0] = Ansi.ansi().bg(bg).a(" ").reset().toString();
      buffer[i][terminal.getWidth() - 1] =
          Ansi.ansi().bg(bg).a(" ").reset().toString();
    }
  }

  public void renderStartMenu() {
    initializeArrays();
    drawBorder(borderFg, borderBg);
    renderLogo();
    renderControls();
    updateScreen();
  }

  public void renderPauseMenu() {
    drawBorder(borderFg, borderBg);
    renderPause();
    renderControls();
    updateScreen();
  }

  public void renderGameOver() {
    initializeArrays();
    drawBorder(borderFg, borderBg);
    renderGameOverText();
    renderControls();
    updateScreen();
  }

  private void renderControls() {
    String[][] controlsArray = convertTo2DArray(ascii.getControls());
    drawText(controlsArray, terminal.getHeight() * 0.4,
             terminal.getWidth() * 0.43, ascii.getControlsBg(),
             ascii.getControlsFg());
  }

  private void renderLogo() {
    String[][] logoArray = convertTo2DArray(ascii.getLogo());
    drawText(logoArray, terminal.getHeight() * 0.1, terminal.getWidth() * 0.33,
             ascii.getLogoBg(), ascii.getLogoFg());
  }

  private void renderPause() {
    String[][] pauseArray = convertTo2DArray(ascii.getPause());
    drawText(pauseArray, terminal.getHeight() * 0.25,
             terminal.getWidth() * 0.44, ascii.getPauseBg(),
             ascii.getPauseFg());
  }

  public void renderGameOverText() {
    String[][] gameOverArray = convertTo2DArray(ascii.getGameOver());
    drawText(gameOverArray, terminal.getHeight() * 0.15,
             terminal.getWidth() * 0.36, ascii.getGameOverBg(),
             ascii.getGameOverFg());
  }

  private void drawText(String[][] textArray, double x, double y, Color bg,
                        Color fg) {
    for (int i = 0; i < textArray.length; i++) {
      for (int j = 0; j < textArray[i].length; j++) {
        String cellContent = textArray[i][j];

        String l = Ansi.ansi().bg(bg).fg(fg).a(cellContent).reset().toString();

        buffer[(int)x][(int)y + j] = l;
      }
      x++;
    }
  }

  public void renderFood(int x, int y, Color color) {
    buffer[x][y] = Ansi.ansi()
                       .bg(theme.getBackground())
                       .fg(color)
                       .a(food.getSymbol())
                       .reset()
                       .toString();
  }

  private void renderSnake(Snake snake) {
    for (Bit bit : snake.bits) {
      if (isWithinBounds(bit.getCurrX(), bit.getCurrY())) {
        buffer[bit.getPrevX()][bit.getPrevY()] =
            Ansi.ansi().bg(theme.getBackground()).a(" ").reset().toString();
        buffer[bit.getCurrX()][bit.getCurrY()] =
            Ansi.ansi().bg(snake.getColor()).a(" ").reset().toString();
      }
    }
  }

  private boolean isWithinBounds(int x, int y) {
    return x > 0 && x < terminal.getHeight() - 1 && y > 0 &&
        y < terminal.getWidth() - 1;
  }

  private String[][] convertTo2DArray(String text) {
    String[] rows = text.split("\n");
    String[][] logo2DArray = new String[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      logo2DArray[i] = rows[i].split("");
    }
    return logo2DArray;
  }
}
