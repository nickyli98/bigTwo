package Game.Hand;

import static Game.Card.CardUtils.orderCards;
import static Game.Constants.validAmounts;

import Game.Card.Card;
import Game.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HandUtils {

  public static List<Hand> getAllPossibleHands(List<Card> cards) {
    List<Hand> hands = new ArrayList<>();
    cards = orderCards(cards);
    hands.addAll(getAllPossibleHands(cards, 1));
    hands.addAll(getAllPossibleHands(cards, 2));
    hands.addAll(getAllPossibleHands(cards, 3));
    hands.addAll(getAllPossibleHands(cards, 5));
    return hands;
  }

  private static List<Hand> getAllPossibleHands(List<Card> cards, int amount) {
    List<Hand> hands = new ArrayList<>();
    Card[] cardArray = new Card[cards.size()];
    cards.toArray(cardArray);
    hands.addAll(getCombinations(cardArray, new Card[amount], 0, cards.size() - 1, 0, amount));
    List<Hand> legalHands = new ArrayList<>();
    for(Hand h: hands){
      if(h.isLegal()){
        legalHands.add(h);
      }
    }
    return legalHands;
  }

  private static List<Hand> getCombinations(Card[] suitableCards, Card[] tempList,
    int start, int end, int index, int length) {
    List<Hand> hands = new ArrayList<>();
    if(index == length){
      hands.add(new Hand(tempList));
    } else {
      for (int i=start; i <= end && end - i + 1 >= length - index; i++) {
        tempList[index] = suitableCards[i];
        hands.addAll(getCombinations(suitableCards, tempList, i+1, end, index+1, length));
      }
    }
    return hands;
  }

  public static void main(String[] args) {
    Card[] cards = new Card[5];
    cards[0] = (new Card(1, Suit.DIAMONDS));
    cards[1] = (new Card(2, Suit.CLUBS));
    cards[2] = (new Card(3, Suit.HEARTS));
    cards[3] = (new Card(4, Suit.SPADES));
    cards[4] = (new Card(5, Suit.SPADES));
    List<Card> c = new LinkedList<>();
    Collections.addAll(c, cards);
    for(Hand h: getAllPossibleHands(c)){
      h.printHand();
    }
  }


  //Minimum card value, maximum card amount
  public static Hand getOptimalHandEasy(List<Card> cards){
    List<Hand> hands;
    for(int amount: validAmounts){
      hands = getAllPossibleHands(cards, amount);
      if(hands.size() != 0){
        return hands.get(0);
      }
    }
    throw new IllegalStateException("There should always be a valid hand");
  }

  public static Hand getOptimalHandEasy(List<Card> cards, List<Card> mustContain){
    assert (cards.contains(mustContain));
    List<Hand> hands;
    for(int amount: validAmounts) {
      hands = getAllPossibleHands(cards, amount);
      if (hands.size() != 0) {
        for (Hand hand : hands) {
          if (hand.contains(mustContain)) {
            return hand;
          }
        }
      }
    }
    throw new IllegalStateException("There should always be a valid hand");
  }

  public static Hand getOptimalHandEasy(List<Card> cards, Hand hand){
    List<Hand> hands = getAllPossibleHands(cards, hand.getCardAmount());
    return hands.stream().filter(x -> new HandComparator().compare(x, hand) > 0)
      .findFirst().orElse(null);
  }


}

