package org.zed.snakez;


import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;

public class AsciiArt {
  private Color logoBg;
  private Color logoFg;
  private Color textBg;
  private Color textFg;
  private Color controlsBg;
  private Color controlsFg;
  private Color gameOverBg;
  private Color gameOverFg;


  AsciiArt(Terminal terminal, Utils util)  {
    logoBg = util.getColor();
    logoFg = util.getColor();
    textBg = util.getColor();
    textFg = util.getColor();
    controlsBg = util.getColor();
    controlsFg = util.getColor();
    gameOverBg = util.getColor();
    gameOverFg = util.getColor();
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
    
  public String getLogo() {
      return Logo;
  }

  public String getControls() {
      return Controls;
  }

  public String getGameOver() {
      return GameOver;
  }

  public Color getLogoBg() {
    return logoBg;
  }

  public Color getLogoFg() {
    return logoFg;
  }

  public Color getTextBg() {
    return textBg;
  }

  public Color getTextFg() {
    return textFg;
  }

  public Color getControlsBg() {
    return controlsBg;
  }

  public Color getControlsFg() {
    return controlsFg;
  }
  
  public Color getGameOverBg() {
    return gameOverBg;
  }
  
  public Color getGameOverFg() {
    return gameOverFg;
  }

}
