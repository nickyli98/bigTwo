package Game;

import Game.Card.Card;
import Game.Card.Deck;
import Game.Hand.Hand;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Round {

  public static void main(String[] args) throws IOException {
    play(new String[]{"1", "2", "3", "4"});
  }

  public static Player play(String[] names) throws IOException {
    assert(names.length == 4);
    Seat currentSeat = initializePlayers(names);
    LinkedList<Hand> currentStack = new LinkedList<>();
    int passCounter = 0;
    do {
      currentSeat = currentSeat.getNext();
      System.out.println(currentSeat.getPlayer().toString());
      Hand lastPlay;
      if(passCounter == 3){
        System.out.println("Everyone passed");
        lastPlay = currentSeat.getPlayer().play();
        currentStack.clear();
        passCounter = 0;
      } else {
        try {
          printStack(currentStack);
          lastPlay = currentSeat.getPlayer().play(currentStack.getLast());
        } catch (NoSuchElementException e){
          //Very first play
          //TODO make sure they play diamond 3
          lastPlay = currentSeat.getPlayer().play();
        }
      }
      if(lastPlay == null){
        //Passed
        passCounter++;
        continue;
      } else {
        passCounter = 0;
        currentStack.addLast(lastPlay);
      }
    } while(!currentSeat.getPlayer().finished());
    return currentSeat.getPlayer();
  }

  private static void printStack(LinkedList<Hand> currentStack) {
    System.out.println("\nCurrent pile:\n");
    currentStack.getLast().printHand();
  }

  private static Seat initializePlayers(String[] names) {
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
    Deck deck = new Deck();
    for(int i = 0; i < 52; i++){
      currentSeat.getPlayer().deal(deck.deal());
      currentSeat = currentSeat.getNext();
    }
    for(int i = 0; i < 4; i++){
      currentSeat.getPlayer().orderCard();
      currentSeat = currentSeat.getNext();
    }
    //TODO return the dude before Diamond 3
    return currentSeat;
  }

}
