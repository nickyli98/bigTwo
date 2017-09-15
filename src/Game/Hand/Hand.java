package Game.Hand;

import static Game.Card.CardUtils.orderCards;
import static Game.Hand.HandChecker.handCheck;

import Game.Card.Card;
import java.util.LinkedList;

public class Hand {

  private final LinkedList<Card> cards;
  private final HandEnum hand;

  public Hand(LinkedList<Card> cards) {
    this.cards = orderCards(cards);
    hand = handCheck(this.cards);
  }

  public Hand() {
    cards = null;
    hand = HandEnum.Invalid;
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public HandEnum getHand() {
    return hand;
  }

  public void printHand() {
    System.out.println(hand.toString());
    StringBuilder sb = new StringBuilder();
    for(Card c : cards) {
      sb.append(c.toString());
      sb.append(", ");
    }
    sb.reverse().replace(0, 1, "").reverse();
    System.out.println(sb.toString() + "\n");
  }
}
