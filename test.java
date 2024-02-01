package org.zed.snakez;

import org.jline.jansi.Ansi;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class test {
  public static void main(String[] args) {
    for (Color color : Color.values()) {
      String l = Ansi.ansi().bg(color).a(color).toString();
      System.out.println(l);
    }
  }
}
