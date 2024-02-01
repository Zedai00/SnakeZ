package org.zed.snakez;


import org.jline.jansi.Ansi;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

public class AsciiArt {
  private Terminal terminal;
  private Color logoBg;
  private Color logoFg;
  private Color textBg;
  private Color textFg;

  AsciiArt(Terminal terminal, Utils util)  {
    this.terminal = terminal;
    logoFg = util.getColor();
    logoBg = util.getColor();
    textFg = util.getColor();
    textBg = util.getColor();
  }
private static String Logo = """
╔──────────────────────────────────────────────────╗
│███████╗███╗   ██╗ █████╗ ██╗  ██╗███████╗███████╗│
│██╔════╝████╗  ██║██╔══██╗██║ ██╔╝██╔════╝╚══███╔╝│
│███████╗██╔██╗ ██║███████║█████╔╝ █████╗    ███╔╝ │
│╚════██║██║╚██╗██║██╔══██║██╔═██╗ ██╔══╝   ███╔╝  │
│███████║██║ ╚████║██║  ██║██║  ██╗███████╗███████╗│
│╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝│
╚──────────────────────────────────────────────────╝
"""; 
private static String Controls = """
▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
▐       Controls       ▌
▐  P - [ Play-Pause ]  ▌
▐  W - [ Up ]          ▌
▐  A - [ Left ]        ▌
▐  S - [ Down ]        ▌
▐  D - [ Right ]       ▌
▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌
""";
public void drawMenu() {
    String[] logoArray = Logo.split("\n");
    terminal.puts(Capability.clear_screen);
    double x = 0.0;
    double y = 0.0;
    for (int i = 0; i < logoArray.length; i++) {
      x = terminal.getHeight()*0.1+i;
      y = terminal.getWidth()*0.35;
      terminal.puts(Capability.cursor_address, x,y);
      terminal.flush();
      String l = Ansi.ansi().bg(logoBg).fg(logoFg).a(logoArray[i]).reset().toString();
      System.out.print(l);
    }
    x+=2;
    String[] controlsArray = Controls.split("\n");
    for (int i = 0; i < controlsArray.length; i++) {
      x++;
      y = terminal.getWidth()*0.43;
      terminal.puts(Capability.cursor_address, x,y);
      terminal.flush();
      String l = Ansi.ansi().bg(textBg).fg(textFg).a(controlsArray[i]).reset().toString();
      System.out.print(l);
    }

    // terminal.puts(Capability.cursor_address, terminal.getHeight()*0.455,terminal.getWidth()*0.450);
    // terminal.flush();
    // String l = Ansi.ansi().fg(textFg).bg(textBg).a(Ansi.Attribute.BLINK_FAST).a("Press P To Play/Pause\nW - Up\n A - Left\nS - Down\nD - Right").reset().toString();
    // System.out.print(l);
    }
}
