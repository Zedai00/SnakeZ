package org.zed.snakez;

import java.util.Arrays;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

class Renderer {
  private Terminal terminal;
  private char[][] screen;
  private char[][] buffer;
  private Game game;

  public Renderer(Terminal terminal, Game game) {
    this.terminal = terminal;
    this.game = game;
    screen = new char[terminal.getHeight()][terminal.getWidth()];
    buffer = new char[terminal.getHeight()][terminal.getWidth()];
    initializeArrays();
  }

  private void initializeArrays() {
    for (int i = 0; i < terminal.getHeight(); i++) {
      Arrays.fill(screen[i], ' ');
      Arrays.fill(buffer[i], ' ');
    }
  }

  public void renderGame(Snake snake, Food food) {
    drawBorder();
    if (snake.foodCheck(food, terminal)) {
      snake.speed--;
    }
    renderFood(food.getX(), food.getY());
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
          System.out.print(buffer[i][j]);
          screen[i][j] = buffer[i][j];
        }
      }
    }
    terminal.flush();
  }

  private void drawBorder() {
    for (int i = 0; i < terminal.getWidth(); i++) {
      buffer[0][i] = '-';
      buffer[terminal.getHeight() - 1][i] = '-';
    }
    for (int i = 0; i < terminal.getHeight(); i++) {
      buffer[i][0] = '|';
      buffer[i][terminal.getWidth() - 1] = '|';
    }
  }

  public void renderFood(int x, int y) {
    if (isWithinBounds(x, y)) {
      buffer[x][y] = '@';
    }
  }

  private void renderSnake(Snake snake) {
    for (Bit bit : snake.bits) {
      if (isWithinBounds(bit.getCurrX(), bit.getCurrY())) {
        buffer[bit.getPrevX()][bit.getPrevY()] = ' ';
        buffer[bit.getCurrX()][bit.getCurrY()] = 'O';
      }
    }
  }

  private boolean isWithinBounds(int x, int y) {
    return x > 0 && x < terminal.getHeight() - 1 && y > 0 &&
        y < terminal.getWidth() - 1;
  }
}
