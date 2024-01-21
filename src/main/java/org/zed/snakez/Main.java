package org.zed.snakez;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlockingReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().system(true).dumb(false).build();
        terminal.puts(Capability.clear_screen);
        terminal.enterRawMode();
        int termWidth = terminal.getWidth();
        int termHeight = terminal.getHeight();
        Snake snake = new Snake(termHeight, termWidth);
        terminal.puts(Capability.cursor_invisible);
        int foodX = ThreadLocalRandom.current().nextInt(1, termHeight);
        int foodY = ThreadLocalRandom.current().nextInt(1, termWidth);
        terminal.puts(Capability.cursor_address, foodX, foodY);
        terminal.flush();
        System.out.printf("@");
        new Movement(terminal, snake).start();
        snake.bits.getFirst().currX = termHeight / 2;
        snake.bits.getFirst().currY = termWidth / 2;
        int sleepBit = 0;
        int sleepSnake = 150;
        while (true) {
            for (int i = 0; i < snake.bits.size(); i++) {
                Bit bit = snake.bits.get(i);
                if (i == 0) {
                    bit.prevX = bit.currX;
                    bit.prevY = bit.currY;
                    switch (snake.move) {
                        case UP -> bit.currX--;
                        case DOWN -> bit.currX++;
                        case LEFT -> bit.currY--;
                        case RIGHT -> bit.currY++;
                    }

                } else {
                    bit.prevX = bit.currX;
                    bit.prevY = bit.currY;
                    bit.currY = snake.bits.get(i - 1).prevY;
                    bit.currX = snake.bits.get(i - 1).prevX;
                }
                if ((bit.currX >= termHeight || bit.currX <= 0) || (bit.currY >= termWidth || bit.currY <= 0)) {
                    System.out.println("\033[?25h");
                    System.exit(0);
                }
                terminal.puts(Capability.cursor_address, bit.currX, bit.currY);
                terminal.flush();
                System.out.print("O");
                bit = snake.bits.getFirst();
                if (bit.currX == foodX && bit.currY == foodY) {
                    foodX = ThreadLocalRandom.current().nextInt(1, termHeight);
                    foodY = ThreadLocalRandom.current().nextInt(1, termWidth);
                    terminal.puts(Capability.save_cursor);
                    terminal.puts(Capability.cursor_address, foodX, foodY);
                    terminal.flush();
                    System.out.print("@");
                    terminal.puts(Capability.restore_cursor);
                    snake.addBit();
                    sleepSnake -= 5;
                }
                try {
                    Thread.sleep(sleepBit);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(sleepSnake);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bit bit = snake.bits.getLast();
            terminal.puts(InfoCmp.Capability.cursor_address, bit.currX, bit.currY);
            terminal.flush();
            System.out.print(" ");
        }
    }
}

class Movement extends Thread {

    Terminal terminal;
    Snake snake;

    Movement(Terminal terminal, Snake snake) {
        this.terminal = terminal;
        this.snake = snake;
    }

    void move(int k) {
        if (k == 87 || k == 119) {
            snake.move = Move.UP;
        } else if (k == 65 || k == 97) {
            snake.move = Move.LEFT;
        } else if (k == 83 || k == 115) {
            snake.move = Move.DOWN;
        } else if (k == 68 || k == 100) {
            snake.move = Move.RIGHT;
        }
    }

    public void run() {
        NonBlockingReader keyReader;
        while (true) {
            keyReader = this.terminal.reader();
            try {
                int k = keyReader.read();
                move(k);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

enum Move {
    UP, DOWN, LEFT, RIGHT;
}

class Bit {
    int currX;
    int currY;
    int prevX;
    int prevY;

}

class Snake {
    ArrayList<Bit> bits = new ArrayList<>();
    Move move;

    Snake(int termHeight, int termWidth) {
        move = Move.values()[ThreadLocalRandom.current().nextInt(Move.values().length)];
        Bit bit = new Bit();
        bits.add(bit);
    }

    void addBit() {
        Bit bit = new Bit();
        bits.add(bit);
    }
}