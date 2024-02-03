package org.zed.snakez;


import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;

public class AsciiArt {
  private Color logoBg;
  private Color logoFg;
  private Color controlsBg;
  private Color controlsFg;
  private Color gameOverBg;
  private Color gameOverFg;
  private Color pauseBg;
  private Color pauseFg;



  AsciiArt(Terminal terminal, Theme theme)  {
    logoBg = theme.getColor();
    logoFg = theme.getColor();
    controlsBg = theme.getColor();
    controlsFg = theme.getColor();
    gameOverBg = theme.getColor();
    gameOverFg = theme.getColor();
    pauseBg = theme.getColor();
    pauseFg = theme.getColor();
  }
  
  private String Logo = """
  ╔──────────────────────────────────────────────────╗
  │███████╗███╗   ██╗ █████╗ ██╗  ██╗███████╗███████╗│
  │██╔════╝████╗  ██║██╔══██╗██║ ██╔╝██╔════╝╚══███╔╝│
  │███████╗██╔██╗ ██║███████║█████╔╝ █████╗    ███╔╝ │
  │╚════██║██║╚██╗██║██╔══██║██╔═██╗ ██╔══╝   ███╔╝  │
  │███████║██║ ╚████║██║  ██║██║  ██╗███████╗███████╗│
  │╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝│
  ╚──────────────────────────────────────────────────╝
  """; 
private String Controls = """
▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
▐      Controls        ▌
▐  P - [ Play-Pause ]  ▌
▐  W - [ Up ]          ▌
▐  A - [ Left ]        ▌
▐  S - [ Down ]        ▌
▐  D - [ Right ]       ▌
▐  Q - [ Quit ]        ▌
▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌
""";

  private String GameOver = """
▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
▐                                            ▌
▐    ░█▀▀░█▀█░█▄█░█▀▀░░░█▀█░█░█░█▀▀░█▀▄░█    ▌
▐    ░█░█░█▀█░█░█░█▀▀░░░█░█░▀▄▀░█▀▀░█▀▄░▀    ▌
▐    ░▀▀▀░▀░▀░▀░▀░▀▀▀░░░▀▀▀░░▀░░▀▀▀░▀░▀░▀    ▌
▐                                            ▌
▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌
""";
  private String Pause = """
┌──────────────────┐
│╔═╗╔═╗╦ ╦╔═╗╔═╗╔╦╗│
│╠═╝╠═╣║ ║╚═╗║╣  ║║│
│╩  ╩ ╩╚═╝╚═╝╚═╝═╩╝│
└──────────────────┘
""";

  public String getLogo() {
      return Logo;
  }

  public Color getLogoBg() {
    return logoBg;
  }

  public Color getLogoFg() {
    return logoFg;
  }

  public String getControls() {
      return Controls;
  }

  public Color getControlsBg() {
    return controlsBg;
  }

  public Color getControlsFg() {
    return controlsFg;
  }

   public String getGameOver() {
      return GameOver;
  }

  public Color getGameOverBg() {
    return gameOverBg;
  }

  public Color getGameOverFg() {
    return gameOverFg;
  }
  
   public String getPause() {
    return Pause;
  }

  public Color getPauseBg() {
    return pauseBg;
  }

  public Color getPauseFg() {
    return pauseFg;
  }
}
