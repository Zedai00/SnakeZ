package org.zed.snakez;

public class Control {
  int lowp = 112;
  int upp = 80;
  int upq = 81;
  int lowq = 113;
  int upw = 87;
  int loww = 119;
  int upa = 65;
  int lowa = 97;
  int ups = 83;
  int lows = 115;
  int upd = 68;
  int lowd = 100;

  public boolean play(int key) {
    return (key == lowp || key == upp);
  }

  public boolean quit(int key) {
    return (key == upq || key == lowq);
  }

  public boolean up(int key) {
    return (key == upw || key == loww);
  }

  public boolean left(int key) {
    return (key == upa || key == lowa);
  }

  public boolean down(int key) {
    return (key == ups || key == lows);
  }

  public boolean right(int key) {
    return (key == upd || key == lowd);
  }
}
