package org.zed.snakez;


import java.util.ArrayList;
import org.jline.jansi.Ansi.Color;
import org.jline.terminal.Terminal;

public class AsciiArt {
  private ArrayList<Color> logoTheme;
  private ArrayList<Color> controlsTheme;
  private ArrayList<Color> gameOverTheme;
  private ArrayList<Color> pauseTheme;



  AsciiArt(Terminal terminal, Theme theme)  {
    logoTheme = theme.getTheme();
    controlsTheme = theme.getTheme();
    gameOverTheme = theme.getTheme();
    pauseTheme = theme.getTheme();
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

  public String getControls() {
      return Controls;
  }

   public String getGameOver() {
      return GameOver;
  }
  
   public String getPause() {
    return Pause;
  }

  public ArrayList<Color> getLogoTheme() {
    return logoTheme;
  }

  public ArrayList<Color> getControlsTheme() {
    return controlsTheme;
  }

  public ArrayList<Color> getGameOverTheme() {
    return gameOverTheme;
  }

  public ArrayList<Color> getPauseTheme() {
    return pauseTheme;
  }

}
