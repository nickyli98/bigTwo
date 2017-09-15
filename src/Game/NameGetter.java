package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NameGetter {

  static String[] getPlayerNames(int playerAmount) throws IOException {
    String[] playerNames = new String[playerAmount];
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    for(int i = 0; i < playerAmount; i++){
      System.out.println("Player " + (i + 1) + " - Please enter a name: ");
      playerNames[i] = reader.readLine();
      while(!isValidName(playerNames[i])){
        System.out.println("Names cannot contain numbers or symbols");
        playerNames[i] = reader.readLine();
      }
    }
    return playerNames;
  }

  public static String nameFormatter(String name){
    int length = name.length();
    if(name.charAt(length - 1) == ' '){
      return name.substring(0, length - 1);
    }
    return name;
  }

  public static boolean isValidName(String name){
    return name.matches("^[A-Za-z ]+$");
  }

}
