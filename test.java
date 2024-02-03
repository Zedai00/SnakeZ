public class ColoredConsoleBlurEffect {
  public static void main(String[] args) {
    String text = "Hello, Blur!";
    int blurIntensity = 3;

    for (int i = 0; i < blurIntensity; i++) {
      for (int j = 0; j < text.length(); j++) {
        char currentChar = text.charAt(j);
        char blurredChar = blurCharacter(currentChar, i);

        // Print blurred character with color
        printColored(blurredChar,
                     i % 7 + 31); // Modulo 7 to get a variety of colors,
                                  // starting from ANSI color code 31
      }
      System.out.println();
    }
  }

  private static void printColored(char character, int colorCode) {
    // ANSI escape code for setting text color
    System.out.print("\u001B[" + colorCode + "m" + character + "\u001B[0m");
  }

  private static char blurCharacter(char original, int intensity) {
    // Adjust the ASCII value of the character based on the intensity
    int blurredAscii = (int)original + intensity;
    return (char)blurredAscii;
  }
}
