package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
  public static void main(final String[] args) {
    State state = State.FIRST;
    try (Terminal terminal = TerminalBuilder.builder().system(true).dumb(false).build()) {
      final Game game = new Game(state, terminal);
      final Thread inputHandler = new Thread(new InputHandler(game));
      inputHandler.start();
      game.start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
