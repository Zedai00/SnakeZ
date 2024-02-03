package org.zed.snakez;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

class Snake {
    ArrayList<Bit> bits;
    private Direction direction;
    private Score score;
    private Theme theme;
    private Color color;
    private Control control;

    public Color getColor() {
        return color;
    }

    public Snake(final Terminal terminal, Game game, Theme theme, Score score,
            AsciiArt ascii) {
        this.score = score;
        this.theme = theme;
        this.color = theme.getColor();
        this.control = game.getControl();
        bits = new ArrayList<>();
        final int height = terminal.getHeight();
        final int width = terminal.getWidth();
        bits.add(new Bit(height / 2, width / 2, theme));
        direction = Direction.values()[ThreadLocalRandom.current().nextInt(
                Direction.values().length)];
    }

    public boolean checkCollision(final Food food, final Terminal terminal) {
        final Bit bit = bits.getFirst();
        if ((bit.getCurrX() >= terminal.getHeight() - 1 || bit.getCurrX() <= 0) ||
                (bit.getCurrY() >= terminal.getWidth() || bit.getCurrY() <= 0)) {
            terminal.puts(InfoCmp.Capability.cursor_visible);
            return true;
        }
        return false;
    }

    public boolean foodCheck(final Food food, final Terminal terminal) {
        if (bits.getFirst().getCurrX() == food.getX() &&
                bits.getFirst().getCurrY() == food.getY()) {
            addBit();
            respawnFood(food, terminal);
            score.updateScore();
            return true;
        } else
            return false;
    }

    public void move() {
        for (int i = 0; i < this.bits.size(); i++) {
            final Bit bit = this.bits.get(i);
            if (i == 0) {
                bit.setPrevX(bit.getCurrX());
                bit.setPrevY(bit.getCurrY());
                switch (this.direction) {
                    case UP -> bit.setCurrX(bit.getCurrX() - 1);
                    case DOWN -> bit.setCurrX(bit.getCurrX() + 1);
                    case LEFT -> bit.setCurrY(bit.getCurrY() - 1);
                    case RIGHT -> bit.setCurrY(bit.getCurrY() + 1);
                }

            } else {
                bit.setPrevX(bit.getCurrX());
                bit.setPrevY(bit.getCurrY());
                bit.setCurrY(this.bits.get(i - 1).getPrevY());
                bit.setCurrX(this.bits.get(i - 1).getPrevX());
            }
        }
    }

    void setDirection(final int key) {
        if (control.up(key)) {
            this.direction = Direction.UP;
        } else if (control.left(key)) {
            this.direction = Direction.LEFT;
        } else if (control.down(key)) {
            this.direction = Direction.DOWN;
        } else if (control.right(key)) {
            this.direction = Direction.RIGHT;
        }
    }

    private void respawnFood(final Food food, final Terminal terminal) {
        food.spawn(terminal.getHeight(), terminal.getWidth());
    }

    private void addBit() {
        bits.add(new Bit(bits.getLast().getPrevX(), bits.getLast().getPrevY(), theme));
    }
}
