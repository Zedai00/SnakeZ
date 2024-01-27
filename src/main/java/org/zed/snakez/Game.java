package org.zed.snakez;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;

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
    renderer.renderFood(food.getX(), food.getY());
  }
}
