package org.zed.snakez;

import java.io.Reader;

import org.jline.reader.Reference;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlockingReader;

class Game {
  private Terminal terminal;
  private Renderer renderer;
  private Snake snake;
  private Food food;
  private int score = 0;
  private int speed = 150;

  public int getSpeed() {
    return speed;
  }

  public void updateSpeed() {
    this.speed++;
  }

  public int getScore() {
    return score;
  }

  public void updateScore() {
    this.score++;
  }

  Game() {
    try {
      terminal = TerminalBuilder.builder().system(true).dumb(false).build();
      terminal.puts(Capability.clear_screen);
      renderer = new Renderer(terminal, this);
      snake = new Snake(terminal, this);
      food = new Food(terminal);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public Snake getSnake() {
    return this.snake;
  }

  public Terminal getTerminal() {
    return this.terminal;
  }

  public void start() {
    initialize();
    renderer.renderStartMenu();
    try {
      while (true) {
        final NonBlockingReader keyReader = terminal.reader();
        if (keyReader.read() == 13) {
          break;
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    terminal.puts(Capability.clear_screen);
    renderer.renderFood(food.getX(), food.getY(), food.getColor());
    runLoop();
  }

  private void runLoop() {
    while (true) {
      snake.move();
      snake.checkCollision(food, terminal);
      renderer.renderGame(snake, food);
      try {
        Thread.sleep(this.getSpeed());
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void initialize() {
    terminal.enterRawMode();
    terminal.puts(Capability.cursor_invisible);
  }
}
