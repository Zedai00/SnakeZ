package org.zed.snakez;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class test {
  public static void main(String[] args) {
    try {
      while (true) {
        Terminal terminal = TerminalBuilder.builder().system(true).dumb(false).build();
        final NonBlockingReader keyReader = terminal.reader();
        System.out.print(keyReader.read());
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
