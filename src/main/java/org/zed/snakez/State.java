package org.zed.snakez;

class State {
  private States state;

  public enum States {
    PLAY, PAUSE
  }

  State() {
    this.state = States.PAUSE;
  }

  public synchronized States getState() {
    return state;
  }

  public synchronized void setState(States state) {
    this.state = state;
  }
}
