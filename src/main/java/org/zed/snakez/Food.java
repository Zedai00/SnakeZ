package org.zed.snakez;

import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;

class Food {
    private int x;
    private int y;
    private Color color;

    public Color getColor() {
        return color;
    }

    public Food(final Terminal terminal) {
        spawn(terminal.getHeight(), terminal.getWidth());
    }

    public void spawn(final int maxX, final int maxY) {
        x = ThreadLocalRandom.current().nextInt(1, maxX);
        y = ThreadLocalRandom.current().nextInt(1, maxY);
        color = Utils.getRandomColor();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
