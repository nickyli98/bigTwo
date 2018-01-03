package Game.Hand;

import static Game.Card.CardUtils.orderCards;
import static Game.Hand.HandChecker.handCheck;

import Game.Card.Card;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

  public Hand(Card[] tempList) {
    cards = new LinkedList<>(Arrays.asList(tempList));
    hand = handCheck(cards);
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public int getCardAmount(){
    return cards.size();
  }

  public int getTotalScore(){
    //TODO lol not sure if works
    return cards.stream().mapToInt(x -> x.getCardValue()).sum();
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
    sb.setLength(sb.length() - 2);
    System.out.println(sb.toString() + "\n");
  }

  public boolean isLegal() {
    return hand != HandEnum.Invalid;
  }

  public boolean contains(List<Card> c){
    return cards.containsAll(c);
  }
}
