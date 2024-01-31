package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

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
