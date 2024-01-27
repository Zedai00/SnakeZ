package org.zed.snakez;

import java.util.concurrent.ThreadLocalRandom;
import org.jline.terminal.Terminal;

class Food {
    int x;
    int y;

    public Food(final Terminal terminal) {
        spawn(terminal.getHeight(), terminal.getWidth());
    }

    public void spawn(final int maxX, final int maxY) {
        x = ThreadLocalRandom.current().nextInt(1, maxX);
        y = ThreadLocalRandom.current().nextInt(1, maxY);
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
