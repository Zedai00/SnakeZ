package org.zed.snakez;

import org.jline.jansi.Ansi.Color;

class Bit {
    private int currX;
    private int currY;
    private int prevX;
    private int prevY;
    public Color color;

    public Bit(final int x, final int y) {
        this.currX = x;
        this.currY = y;
        this.color = Utils.getRandomColor();
    }

    public int getCurrX() {
        return currX;
    }

    public void setCurrX(final int currX) {
        this.currX = currX;
    }

    public int getCurrY() {
        return currY;
    }

    public void setCurrY(final int currY) {
        this.currY = currY;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(final int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(final int prevY) {
        this.prevY = prevY;
    }
}
