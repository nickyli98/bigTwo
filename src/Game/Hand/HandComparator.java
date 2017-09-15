package Game.Hand;

import Game.Card.Card;
import Game.Card.CardComparator;
import java.util.Comparator;
import java.util.LinkedList;

public class HandComparator implements Comparator<Hand> {

  @Override
  public int compare(Hand h1, Hand h2){
    int h1Rank = h1.getHand().rank;
    int h2Rank = h2.getHand().rank;
    if(h1Rank != h2Rank){
      return Integer.compare(h1Rank, h2Rank);
    }
    switch(h1.getHand()){
      case StraightFlush:
      case Straight:
      case Flush:
      case Triples:
      case Pair:
      case Single:
        return new CardComparator().compare(h1.getCards().getLast(), h2.getCards().getLast());
      case FullHouse:
      case FourOfaKind:
        return Integer.compare(getMiddle(h1.getCards()), getMiddle(h2.getCards()));
      default:
        throw new IllegalArgumentException("Invalid hands");
    }
  }

  private int getMiddle(LinkedList<Card> cards) {
    return cards.get(2).getValue();
  }
}
