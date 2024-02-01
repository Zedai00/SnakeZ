package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlocking;
import org.jline.utils.NonBlockingReader;
import org.zed.snakez.State.States;

class Game {
  private Terminal terminal;
  private Renderer renderer;

  private Snake snake;
  private Food food;
  private Score score;
  private int speed = 90;
  private State state;

  public Renderer getRenderer() {
    return renderer;
  }

  public int getSpeed() {
    return speed;
  }

  public void updateSpeed() {
    this.speed++;
  }

  Game(State state) {
    try {
      terminal = TerminalBuilder.builder().system(true).dumb(false).build();
      terminal.puts(Capability.clear_screen);
      terminal.enterRawMode();
      Utils util = new Utils();
      this.state = state;
      food = new Food(terminal, util);
      score = new Score(util);
      renderer = new Renderer(terminal, this, util, food, score);
      snake = new Snake(terminal, this, util, score);
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
    while (state.getState() != States.PLAY)
      continue;
    runLoop();
  }

  private void runLoop() {
    int c = 0;
    while (true) {
      if (state.getState() == States.PLAY) {
        if (c == 0) {
          terminal.puts(Capability.clear_screen);
          c = 1;
        }
        snake.move();
        snake.checkCollision(food, terminal);
        renderer.renderGame(snake, food);
        try {
          Thread.sleep(this.getSpeed());
        } catch (final InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        renderer.renderStartMenu();
        renderer.drawBorder(renderer.getBorderFg(), renderer.getBorderBg());
        c = 0;
      }
    }
  }

  private void initialize() {
    terminal.enterRawMode();
    terminal.puts(Capability.cursor_invisible);
  }

  public void handleInput(int key) {
    if (key == 112 || key == 80) {
      if (state.getState() == States.PAUSE) {
        state.setState(States.PLAY);
        terminal.puts(Capability.clear_screen);
      } else {
        state.setState(States.PAUSE);
        terminal.puts(Capability.clear_screen);
      }
    } else {
      snake.setDirection(key);
    }
  }
}
