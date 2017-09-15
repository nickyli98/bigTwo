package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {

  private static String playerSelectionInstructions
      = "Please select cards for your next play:\n"
      + "Enter numbers separated by spaces for which card you want to play:\n"
      + "For example: 1 2 3 4\n"
      + "Enter 'pass' to pass\n";

  public static int[] getPlayerSelection() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    mainLoop:
      while(true) {
        System.out.println(playerSelectionInstructions);
        String line = reader.readLine();
        if (line.equals("pass")) {
          return null;
        }
        String[] lineArray = line.split(" ");
        int[] intArray = new int[lineArray.length];
        int counter = 0;
        for (String s : lineArray) {
          try {
            intArray[counter] = (Integer.parseInt(s) - 1);
            counter++;
          } catch (NumberFormatException e) {
            continue mainLoop;
          }
        }
        return intArray;
      }
  }

}
