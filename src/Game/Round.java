package Game;

import Game.Card.Card;
import Game.Card.Deck;
import Game.Card.Suit;
import Game.Hand.Hand;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Round {

  public static void main(String[] args) throws IOException {
    play(initializeAI(1));
  }

  public static Player play(Seat currentSeat) throws IOException {
    LinkedList<Hand> currentStack = new LinkedList<>();
    int passCounter = 0;
    do {
      if(currentSeat.getPlayer().isHuman()){
        System.out.println(currentSeat.getPlayer().toString());
      }
      Hand lastPlay;
      if(passCounter == 3){
        System.out.println("Everyone passed");
        lastPlay = currentSeat.getPlayer().play(false);
        currentStack.clear();
        passCounter = 0;
      } else {
        try {
          if(currentSeat.getPlayer().isHuman()){
            printStack(currentStack);
          }
          lastPlay = currentSeat.getPlayer().play(currentStack.getLast());
        } catch (NoSuchElementException e){
          //Very first play
          lastPlay = currentSeat.getPlayer().play(true);
        }
      }
      if(lastPlay == null){
        //Passed
        System.out.println("Passed");
        passCounter++;
      } else {
        passCounter = 0;
        System.out.println("Played");
        lastPlay.printHand();
        currentStack.addLast(lastPlay);
      }
      currentSeat = currentSeat.getNext();
    } while(!currentSeat.getPlayer().finished());
    return currentSeat.getPlayer();
  }

  private static void printStack(LinkedList<Hand> currentStack) {
    System.out.println("\nCurrent pile:\n");
    currentStack.getLast().printHand();
  }

  /*
    1: Easy
    2: Medium
   */
  private static Seat initializeAI(int level){
    Seat first = new Seat(new Player("Player"));
    Seat current = first;
    for(int i = 0; i < 3; i++){
      Player p;
      switch(level){
        case 1:
        default:
          p = new EasyBot(i + "");
      }
      current.setNext(new Seat(p));
      current = current.getNext();
    }
    current.setNext(first);
    return initiatePlayerCards(current);
  }

  private static Seat initializePlayers(String[] names) {
    assert (names.length == 4);
    Seat currentSeat = null;
    Seat first = null;
    for(String name : names){
      if(currentSeat == null){
        currentSeat = new Seat(new Player(name));
        first = currentSeat;
      } else {
        currentSeat.setNext(new Seat(new Player(name)));
        currentSeat = currentSeat.getNext();
      }
    }
    currentSeat.setNext(first);
    return initiatePlayerCards(currentSeat);
  }

  private static Seat initiatePlayerCards(Seat currentSeat){
    Deck deck = new Deck();
    Seat first = currentSeat;
    for(int i = 0; i < 52; i++){
      Card c = deck.deal();
      currentSeat.getPlayer().deal(c);
      if (c.getValue() == 3 && c.getSuit() == Suit.DIAMONDS){
        first = currentSeat;
      }
      currentSeat = currentSeat.getNext();
    }
    for(int i = 0; i < 4; i++){
      currentSeat.getPlayer().orderCard();
      currentSeat = currentSeat.getNext();
    }
    return first;
  }

}
