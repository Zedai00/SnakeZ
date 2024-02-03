package org.zed.snakez;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

class InputHandler implements Runnable {
    private final Terminal terminal;
    private final Game game;
    private final NonBlockingReader keyReader;
    private final Control control;

    public InputHandler(Game game) {
        this.game = game;
        this.terminal = game.getTerminal();
        this.keyReader = terminal.reader();
        this.control = game.getControl();
    }

    @Override
    public void run() {
        int key;
        try {
            while (true) {
                key = keyReader.read();
                synchronized (game) {
                    if (game.getState() == State.FIRST) {
                        if (control.play(key)) {
                            game.notify();
                        } else if (control.quit(key)) {
                            game.quit();
                        }
                    } else if (game.getState() == State.PLAY) {
                        if (control.play(key)) {
                            synchronized (game) {
                                if (game.getState() == State.PAUSE) {
                                    game.setState(State.PLAY);
                                } else {
                                    game.setState(State.PAUSE);
                                }
                            }
                        } else if (control.quit(key)) {
                            game.quit();
                        } else {
                            game.handleInput(key);
                        }
                    } else if (game.getState() == State.GAMEOVER) {
                        if (control.play(key)) {
                            game.setState(State.FIRST);
                            game.notify();
                        } else if (control.quit(key)) {
                            game.quit();
                        }
                    } else if (game.getState() == State.PAUSE) {
                        if (control.play(key)) {
                            game.setState(State.PLAY);
                            game.notify();
                        } else if (control.quit(key)) {
                            game.quit();
                        }
                    }
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
