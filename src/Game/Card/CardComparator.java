package Game.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

  @Override
  public int compare(Card c1, Card c2) {
    if(c1 == null || c2 == null){
      return 0;
    }
    int v1 = c1.getValue();
    int v2 = c2.getValue();
    if(v1 == v2){
      return Integer.compare(c1.getSuit().rank, c2.getSuit().rank);
    }
    return new CardValComparator().compare(v1, v2);
  }

  private class CardValComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
      if(o1 == 2 || o1 == 1){
        o1 += 13;
      }
      if(o2 == 2 || o2 == 1){
        o2 += 13;
      }
      return Integer.compare(o1, o2);
    }
  }
}
