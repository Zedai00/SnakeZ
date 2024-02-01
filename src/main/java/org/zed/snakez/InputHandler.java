package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

class InputHandler implements Runnable {
    private final Terminal terminal;
    private final Game game;

    public InputHandler(Game game) {
        this.game = game;
        this.terminal = game.getTerminal();
    }

    @Override
    public void run() {
        final NonBlockingReader keyReader = terminal.reader();
        try {
            while (true) {
                game.handleInput(keyReader.read());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
