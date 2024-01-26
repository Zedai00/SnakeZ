package org.zed.snakez;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlockingReader;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();
    Thread inputHandeler = new Thread(new InputHandeler(game.getTerminal(), game.getSnake()));
    inputHandeler.start();
    game.start();
  }
}

class Game {
  private Terminal terminal;
  private Renderer renderer;
  private Snake snake;
  private Food food;

  Game() {
    try {
      terminal = TerminalBuilder.builder().system(true).dumb(false).build();
      renderer = new Renderer(terminal);
      snake = new Snake(terminal);
      food = new Food(terminal);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Snake getSnake() {
    return this.snake;
  }

  public Terminal getTerminal() {
    return this.terminal;
  }

  private void initialize() {
    terminal.puts(Capability.clear_screen);
    terminal.puts(Capability.cursor_invisible);
    renderer.renderFood(food.getX(), food.getY());
  }

  public void start() {
    initialize();
    while (true) {
      snake.move();
      snake.checkCollision(food, terminal);
      renderer.renderGame(snake, food);
      try {
        Thread.sleep(100); // Adjust sleep time based on your desired game speed
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Renderer {
  private Terminal terminal;

  public Renderer(Terminal terminal) {
    this.terminal = terminal;
  }

  public void renderGame(Snake snake, Food food) {
    renderSnake(snake);
    renderFood(food.getX(), food.getY());
  }

  private void renderSnake(Snake snake) {
    for (Bit bit : snake.bits) {
      terminal.puts(Capability.cursor_address, bit.prevX, bit.prevY);
      System.out.print("b");
      terminal.flush();
    }

    for (Bit bit : snake.bits) {
      terminal.puts(Capability.cursor_address, bit.currX, bit.currY);
      System.out.print("O");
      terminal.flush();
    }

    terminal.flush();
  }

  public void renderFood(int x, int y) {
    // terminal.puts(Capability.save_cursor);
    terminal.puts(Capability.cursor_address, x, y);
    terminal.flush();
    System.out.print("@");
    // terminal.puts(Capability.restore_cursor);
  }
}

class InputHandeler implements Runnable {
  private Terminal terminal;
  private Snake snake;

  public InputHandeler(Terminal terminal, Snake snake) {
    this.terminal = terminal;
    this.snake = snake;
  }

  @Override
  public void run() {
    NonBlockingReader keyReader = terminal.reader();
    try {
      while (true) {
        snake.setDirection(keyReader.read());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

enum Direction {
  UP, DOWN, LEFT, RIGHT
}

class Bit {
  int currX;
  int currY;
  int prevX;
  int prevY;

  public Bit(int x, int y) {
    this.currX = x;
    this.currY = y;
  }
}

class Snake {
  ArrayList<Bit> bits;
  private Direction direction;

  public Snake(Terminal terminal) {
    bits = new ArrayList<>();
    bits.add(new Bit(terminal.getWidth() / 2, terminal.getHeight() / 2));
    direction = Direction.values()[ThreadLocalRandom.current().nextInt(
        Direction.values().length)];
  }

  public void checkCollision(Food food, Terminal terminal) {
    foodCheck(food, terminal);
    wallCheck(terminal);
  }

  private void wallCheck(Terminal terminal) {
    Bit bit = bits.getFirst();
    if ((bit.currX >= terminal.getWidth() || bit.currX <= 0) ||
        (bit.currY >= terminal.getHeight() || bit.currY <= 0)) {
      terminal.puts(Capability.cursor_visible);
      System.exit(0);
    }
  }

  private void foodCheck(Food food, Terminal terminal) {
    if (bits.getFirst().currX == food.x && bits.getFirst().currY == food.y) {
      addBit();
      respawnFood(food, terminal);
    }
  }

  private void respawnFood(Food food, Terminal terminal) {
    food.spawn(terminal.getWidth(), terminal.getHeight());
  }

  private void addBit() {
    bits.add(new Bit(bits.getLast().prevX, bits.getLast().prevY));
  }

  public void move() {
    for (int i = 0; i < this.bits.size(); i++) {
      Bit bit = this.bits.get(i);
      if (i == 0) {
        bit.prevX = bit.currX;
        bit.prevY = bit.currY;
        switch (this.direction) {
          case UP -> bit.currX--;
          case DOWN -> bit.currX++;
          case LEFT -> bit.currY--;
          case RIGHT -> bit.currY++;
        }

      } else {
        bit.prevX = bit.currX;
        bit.prevY = bit.currY;
        bit.currY = this.bits.get(i - 1).prevY;
        bit.currX = this.bits.get(i - 1).prevX;
      }
    }
  }

  void setDirection(int key) {
    if (key == 87 || key == 119) {
      this.direction = Direction.UP;
    } else if (key == 65 || key == 97) {
      this.direction = Direction.LEFT;
    } else if (key == 83 || key == 115) {
      this.direction = Direction.DOWN;
    } else if (key == 68 || key == 100) {
      this.direction = Direction.RIGHT;
    }
  }
}

class Food {
  int x;
  int y;

  public Food(Terminal terminal) {
    spawn(terminal.getWidth(), terminal.getHeight());
  }

  public void spawn(int maxX, int maxY) {
    x = ThreadLocalRandom.current().nextInt(1, maxX);
    y = ThreadLocalRandom.current().nextInt(1, maxY);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
