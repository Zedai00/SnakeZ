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
  private Utils util;
  private Color borderFg;

  private Color borderBg;

  private Score score;
  private AsciiArt startMenu;

  public Renderer(Terminal terminal, Game game, Utils util, Food food,
      Score score) {
    this.terminal = terminal;
    this.game = game;
    this.food = food;
    this.score = score;
    screen = new String[terminal.getHeight()][terminal.getWidth()];
    buffer = new String[terminal.getHeight()][terminal.getWidth()];
    this.util = util;
    initializeArrays();
    borderBg = util.getColor();
    borderFg = util.getColor();
    drawBorder(borderFg, borderBg);
    startMenu = new AsciiArt(terminal, util);
  }

  public Color getBorderFg() {
    return borderFg;
  }

  public Color getBorderBg() {
    return borderBg;
  }

  private void initializeArrays() {
    for (int i = 0; i < terminal.getHeight(); i++) {
      Arrays.fill(
          screen[i],
          Ansi.ansi().bg(util.getBackground()).a(" ").reset().toString());
      Arrays.fill(
          buffer[i],
          Ansi.ansi().bg(util.getBackground()).a(" ").reset().toString());
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
    String s = Ansi.ansi()
        .bg(score.getBg())
        .a("Score: " + score.getScore())
        .reset()
        .toString();
    terminal.puts(Capability.cursor_address, (int) terminal.getHeight() % 1,
        (int) terminal.getWidth() % 10);
    terminal.flush();
    System.out.print(s);
  }

  private void updateScreen() {
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
      buffer[terminal.getHeight() - 1][i] = Ansi.ansi().bg(bg).a(" ").reset().toString();
    }
    for (int i = 0; i < terminal.getHeight(); i++) {
      buffer[i][0] = Ansi.ansi().bg(bg).a(" ").reset().toString();
      buffer[i][terminal.getWidth() - 1] = Ansi.ansi().bg(bg).a(" ").reset().toString();
    }
  }

  public void renderStartMenu() {
    startMenu.drawMenu();
  }

  public void renderFood(int x, int y, Color color) {
    buffer[x][y] = Ansi.ansi()
        .bg(util.getBackground())
        .fg(color)
        .a(food.getSymbol())
        .reset()
        .toString();
  }

  private void renderSnake(Snake snake) {
    for (Bit bit : snake.bits) {
      if (isWithinBounds(bit.getCurrX(), bit.getCurrY())) {
        buffer[bit.getPrevX()][bit.getPrevY()] = Ansi.ansi().bg(util.getBackground()).a(" ").reset().toString();
        buffer[bit.getCurrX()][bit.getCurrY()] = Ansi.ansi().bg(snake.getColor()).a(" ").reset().toString();
      }
    }
  }

  private boolean isWithinBounds(int x, int y) {
    return x > 0 && x < terminal.getHeight() - 1 && y > 0 &&
        y < terminal.getWidth() - 1;
  }
}
