package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

class Game {
  private Terminal terminal;
  private Renderer renderer;
  private Snake snake;
  private Food food;
  private Score score;
  private int speed = 90;
  private State state;
  private AsciiArt ascii;
  private Control control;

  Game(State state, Terminal terminal) {
    try {
      this.terminal = terminal;
      terminal.puts(Capability.clear_screen);
      terminal.enterRawMode();
      this.state = state;
      this.control = new Control();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public Renderer getRenderer() {
    return renderer;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getSpeed() {
    return speed;
  }

  public void updateSpeed() {
    this.speed++;
  }

  public Snake getSnake() {
    return this.snake;
  }

  public Terminal getTerminal() {
    return this.terminal;
  }

  public Food getFood() {
    return food;
  }

  public Score getScore() {
    return score;
  }

  public AsciiArt getAscii() {
    return ascii;
  }

  public Control getControl() {
    return control;
  }

  public void start() {
    initialize();
    renderer.renderStartMenu();
    if (getState() != State.PLAY) {
      try {
        synchronized (this) {
          wait();
          setState(State.PLAY);
        }
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    runLoop();
  }

  private void runLoop() {
    int c = 0;
    while (true) {
      if (getState() == State.PLAY) {
        if (c == 0) {
          terminal.puts(Capability.clear_screen);
          c = 1;
        }
        snake.move();
        if (snake.checkCollision(food, terminal)) {
          GameOver();
        }
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

  public void GameOver() {
    renderer.renderGameOver();
    setState(State.GAMEOVER);
    synchronized (this) {
      try {
        wait();
        if (getState() == State.FIRST) {
          initialize();
          setState(State.PLAY);
          notify();

        } else if (getState() == State.QUIT) {
          quit();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void initialize() {
    terminal.enterRawMode();
    terminal.puts(Capability.cursor_invisible);
    Utils util = new Utils();
    food = new Food(terminal, util);
    score = new Score(util);
    ascii = new AsciiArt(terminal, util);
    renderer = new Renderer(terminal, this, util, food, score, ascii);
    snake = new Snake(terminal, this, util, score, ascii);
  }

  public void handleInput(int key) {
    snake.setDirection(key);
  }

  public void quit() {
    System.out.print("\u001B[H\u001B[2J");
    System.out.print("\u001B[H\u001B[2J");
    System.exit(0);
  }
}
