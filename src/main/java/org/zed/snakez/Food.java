package org.zed.snakez;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;

class Food {
    private int x;
    private int y;
    private ArrayList<Color> color;
    private Theme theme;
    private String symbol;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ArrayList<Color> getColor() {
        return color;
    }

    private String[] symbols = { "⬤", "⬣", "⬢", "Ｏ", "Ꙩ", "Ꙫ" };

    public Food(final Terminal terminal, Theme theme) {
        this.theme = theme;
        spawn(terminal.getHeight(), terminal.getWidth());
        this.symbol = getRandomSymbol();
    }

    public void spawn(final int maxX, final int maxY) {
        x = ThreadLocalRandom.current().nextInt(1, maxX - 1);
        y = ThreadLocalRandom.current().nextInt(1, maxY - 1);
        color = theme.getTheme();
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

    public String getSymbol() {
        return this.symbol;
    }

    public String getRandomSymbol() {
        return symbols[ThreadLocalRandom.current().nextInt(symbols.length)];
    }
}
