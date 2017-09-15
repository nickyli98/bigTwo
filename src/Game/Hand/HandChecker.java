package Game.Hand;

import Game.Card.Card;
import Game.Card.Suit;
import java.util.LinkedList;
import java.util.List;

import static Game.Constants.*;

public class HandChecker {

  public static HandEnum handCheck(LinkedList<Card> cards){
    int amountOfCards = cards.size();
    if(!validAmounts.contains(amountOfCards)){
      return HandEnum.Invalid;
    }
    if(amountOfCards == 1){
      return HandEnum.Single;
    }
    int[] array = getValuesArray(cards);
    if(amountOfCards == 5){
      return setHandCheck(cards, array);
    } else {
      return duplicateHandCheck(array);
    }
  }

  //Pre: size = 2, 3
  private static HandEnum duplicateHandCheck(int[] valuesArray) {
    for(int i : valuesArray){
      if(i == 2){
        return HandEnum.Pair;
      }
      if(i == 3){
        return HandEnum.Triples;
      }
    }
    return HandEnum.Invalid;
  }

  //Pre: cards.size = 5
  private static HandEnum setHandCheck(List<Card> cards, int[] valuesArray) {
    boolean straight = straightCheck(cards);
    boolean flush = flushCheck(cards);
    boolean fours = foursCheck(valuesArray);
    boolean fullHouse = fullHouseCheck(valuesArray);
    if(straight && flush){
      return HandEnum.StraightFlush;
    }
    if(fours){
      return HandEnum.FourOfaKind;
    }
    if(fullHouse){
      return HandEnum.FullHouse;
    }
    if(flush){
      return HandEnum.Flush;
    }
    if(straight){
      return HandEnum.Straight;
    }
    return HandEnum.Invalid;
  }

  private static boolean fullHouseCheck(int[] valuesArray) {
    boolean pair = false;
    boolean triple = false;
    for(int i : valuesArray){
      if(i == 2){
        pair = true;
      }
      if(i == 3){
        triple = true;
      }
    }
    return pair && triple;
  }

  private static boolean foursCheck(int[] valuesArray) {
    for(int i : valuesArray){
      if(i == 4){
        return true;
      }
    }
    return false;
  }

  private static int[] getValuesArray(List<Card> cards) {
    int[] array = new int[13];
    for(Card c : cards){
      array[c.getValue()]++;
    }
    return array;
  }

  private static boolean flushCheck(List<Card> cards) {
    Suit s = cards.get(0).getSuit();
    return cards.stream().allMatch(s1 -> s1.getSuit() == s);
  }

  private static boolean straightCheck(List<Card> cards) {
    int val = cards.get(0).getValue();
    for(int i = 1; i < 5; i++){
      if(val != 13){
        if(++val != cards.get(i).getValue()){
          return false;
        }
      } else {
        if(cards.get(i).getValue() == 1){
          val = 1;
        }
      }
    }
    return true;
  }

}
