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
  private Color borderFg = Utils.getRandomColor();
  private Color borderBg = Utils.getRandomColor();

  public Renderer(Terminal terminal, Game game) {
    this.terminal = terminal;
    this.game = game;
    screen = new String[terminal.getHeight()][terminal.getWidth()];
    buffer = new String[terminal.getHeight()][terminal.getWidth()];
    initializeArrays();
  }

  private void initializeArrays() {
    for (int i = 0; i < terminal.getHeight(); i++) {
      Arrays.fill(screen[i], " ");
      Arrays.fill(buffer[i], " ");
    }
  }

  public void renderGame(Snake snake, Food food) {
    drawBorder(borderFg, borderBg);
    if (snake.foodCheck(food, terminal)) {
      game.updateSpeed();
    }
    renderFood(food.getX(), food.getY(), food.getColor());
    renderSnake(snake);
    renderScore();
    updateScreen();
  }

  private void renderScore() {
    terminal.puts(Capability.cursor_address, (int) terminal.getHeight() % 1,
        (int) terminal.getWidth() % 10);
    terminal.flush();
    System.out.print("Score: " + game.getScore());
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

  private void drawBorder(Color bg, Color fg) {
    for (int i = 0; i < terminal.getWidth(); i++) {
      buffer[0][i] = Ansi.ansi().fg(fg).bg(bg).a('-').reset().toString();
      buffer[terminal.getHeight() - 1][i] = Ansi.ansi().fg(fg).bg(bg).a('-').reset().toString();
    }
    for (int i = 0; i < terminal.getHeight(); i++) {
      buffer[i][0] = Ansi.ansi().fg(fg).bg(bg).a('|').reset().toString();
      buffer[i][terminal.getWidth() - 1] = Ansi.ansi().fg(fg).bg(bg).a('|').reset().toString();
    }
  }

  public void renderStartMenu() {
    AsciiArt ascii = new AsciiArt(terminal);
    ascii.drawMenu();
  }

  public void renderFood(int x, int y, Color color) {
    if (isWithinBounds(x, y)) {
      buffer[x][y] = Ansi.ansi().fg(color).a('@').reset().toString();
    }
  }

  private void renderSnake(Snake snake) {
    for (Bit bit : snake.bits) {
      if (isWithinBounds(bit.getCurrX(), bit.getCurrY())) {
        buffer[bit.getPrevX()][bit.getPrevY()] = Ansi.ansi().reset().a(" ").reset().toString();
        buffer[bit.getCurrX()][bit.getCurrY()] = Ansi.ansi().fg(bit.color).a("O").reset().toString();
      }
    }
  }

  private boolean isWithinBounds(int x, int y) {
    return x > 0 && x < terminal.getHeight() - 1 && y > 0 &&
        y < terminal.getWidth() - 1;
  }
}
