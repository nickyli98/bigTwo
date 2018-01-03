package Game;

import static Game.Parser.getPlayerSelection;

import Game.Card.Card;
import Game.Card.Suit;
import Game.Hand.Hand;
import Game.Hand.HandComparator;
import Game.Hand.HandEnum;
import java.io.IOException;
import java.util.LinkedList;

import static Game.Card.CardUtils.*;

public class Player {

  private final String name;
  private LinkedList<Card> cards;

  public Player(String name) {
    this.name = name;
    cards = new LinkedList<>();
  }

  public void deal(Card c){
    cards.add(c);
  }

  public void orderCard(){
    cards = orderCards(cards);
  }

  @Override
  public String toString(){
    return name + "\nCards:\n" + printCards();
  }

  public boolean isHuman(){
    return true;
  }

  private String printCards() {
    StringBuilder sb = new StringBuilder();
    for(Card c : cards){
      sb.append(c.toString());
      sb.append(", ");
    }
    sb.setLength(sb.length() - 2);
    return sb.toString();
  }

  public boolean finished(){
    return cards.isEmpty();
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public Hand play(boolean isFirst) throws IOException {
    System.out.println("You may begin");
    Hand hand = null;
    do {
      hand = playHelper(hand);
      if (hand == null) {
        return null;
      }
    } while(!isValidPlay(hand, isFirst));
    cards.removeAll(hand.getCards());
    return hand;
  }

  public Hand play(Hand lastPlay) throws IOException {
    System.out.println("You may play");
    Hand hand = null;
    LinkedList<Card> temp = new LinkedList<>();
    do {
      hand = playHelper(hand);
      if (hand == null) {
        return null;
      }
    } while(!isValidPlay(hand, lastPlay));
    cards.removeAll(hand.getCards());
    return hand;
  }

  private Hand playHelper(Hand hand) throws IOException {
    if(hand != null){
      System.out.println("Invalid card selection\nPlease choose again");
    }
    int[] selection = getPlayerSelection();
    if(selection == null) {
      return null;
    }
    LinkedList<Card> temp = new LinkedList<>();
    for(int index : selection){
      try {
        temp.add(cards.get(index));
      } catch (IndexOutOfBoundsException e){
        return new Hand();
      }
    }
    hand = new Hand(temp);
    return hand;
  }

  private boolean isValidPlay(Hand hand) {
    return hand.getHand() != HandEnum.Invalid;
  }

  private boolean isValidPlay(Hand hand, Boolean first) {
    if(hand.getCards().contains(new Card(3, Suit.DIAMONDS))){
      return isValidPlay(hand);
    } else {
      return false;
    }
  }

  private boolean isValidPlay(Hand hand, Hand lastPlay) {
    if(isValidPlay(hand)){
      if(lastPlay.getCards().size() == hand.getCards().size()){
        return new HandComparator().compare(hand, lastPlay) > 0;
      }
    }
    return false;
  }
}
