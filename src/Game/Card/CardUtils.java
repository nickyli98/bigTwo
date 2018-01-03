package Game.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardUtils {

  //Post: puts them in order from smallest to largest (3 - 2)
  public static LinkedList<Card> orderCards(List<Card> cards) {
    LinkedList<Card> newList = new LinkedList<>();
    while(!cards.isEmpty()){
      Card min = cards.stream().min(new CardComparator()).orElse(null);
      if(min == null){
        //TODO ??????
        return null;
      }
      cards.remove(min);
      newList.addLast(min);
    }
    return newList;
  }

  public static List<Card> getAllCards(int val, List<Card> cards){
    List<Card> sameValue = new ArrayList<>();
    for(Card c: cards){
      if (c.getValue() == val){
        sameValue.add(c);
      }
    }
    return sameValue;
  }

  public static List<Card> getAllCards(Suit suit, List<Card> cards){
    List<Card> sameValue = new ArrayList<>();
    for(Card c: cards){
      if (c.getSuit() == suit){
        sameValue.add(c);
      }
    }
    return sameValue;
  }

}
