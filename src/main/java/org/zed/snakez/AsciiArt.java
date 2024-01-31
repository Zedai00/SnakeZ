package org.zed.snakez;


import org.jline.jansi.Ansi;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

public class AsciiArt {
  private Terminal terminal;

  AsciiArt(Terminal terminal)  {
    this.terminal = terminal;
  }
private static String logo = """
╔──────────────────────────────────────────────────╗
│███████╗███╗   ██╗ █████╗ ██╗  ██╗███████╗███████╗│
│██╔════╝████╗  ██║██╔══██╗██║ ██╔╝██╔════╝╚══███╔╝│
│███████╗██╔██╗ ██║███████║█████╔╝ █████╗    ███╔╝ │
│╚════██║██║╚██╗██║██╔══██║██╔═██╗ ██╔══╝   ███╔╝  │
│███████║██║ ╚████║██║  ██║██║  ██╗███████╗███████╗│
│╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝│
╚──────────────────────────────────────────────────╝
""";

  
public void drawMenu() {
    Color bg = Utils.getRandomColor();
    Color fg = Utils.getRandomColor();
    String[] logoArray = logo.split("\n");
    terminal.puts(Capability.clear_screen);
    for (int i = 0; i < logoArray.length; i++) {
      double x = terminal.getHeight()*0.1+i;
      double y = terminal.getWidth()*0.35;
      terminal.puts(Capability.cursor_address, x,y);
      terminal.flush();
      String l = Ansi.ansi().bg(bg).fg(fg).a(logoArray[i]).reset().toString();
      System.out.print(l);
    }
    terminal.puts(Capability.cursor_address, terminal.getHeight()*0.455,terminal.getWidth()*0.450);
    terminal.flush();
    String l = Ansi.ansi().fg(Utils.getRandomColor()).bg(Utils.getRandomColor()).a(Ansi.Attribute.BLINK_FAST).a("Press Enter To Start").reset().toString();
    System.out.print(l);
    }
}
