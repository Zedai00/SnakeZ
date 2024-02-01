package org.zed.snakez;

public class Main {
  public static void main(final String[] args) {
    State state = new State();
    final Game game = new Game(state);
    final Thread inputHandler = new Thread(new InputHandler(game));
    inputHandler.start();
    game.start();
  }
}
