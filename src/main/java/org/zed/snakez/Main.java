package org.zed.snakez;

public class Main {
  public static void main(final String[] args) {
    final Game game = new Game();
    final Thread inputHandler = new Thread(new InputHandler(game.getTerminal(), game.getSnake()));
    inputHandler.start();
    game.start();
  }
}
