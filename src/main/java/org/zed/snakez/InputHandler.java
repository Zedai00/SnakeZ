package org.zed.snakez;

import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;

class InputHandler implements Runnable {
    private final Terminal terminal;
    private final Snake snake;

    public InputHandler(final Terminal terminal, final Snake snake) {
        this.terminal = terminal;
        this.snake = snake;
    }

    @Override
    public void run() {
        final NonBlockingReader keyReader = terminal.reader();
        try {
            while (true) {
                snake.setDirection(keyReader.read());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
