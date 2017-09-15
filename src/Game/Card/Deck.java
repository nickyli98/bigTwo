package Game.Card;

public class Deck {

  private Card[] deck;
  private int cardsUsed;

  public Deck(){
    deck = new Card[52];
    initializeCards();
    shuffle();
  }

  private void initializeCards(){
    initializeSuit(Suit.HEARTS, 0);
    initializeSuit(Suit.SPADES, 13);
    initializeSuit(Suit.CLUBS, 26);
    initializeSuit(Suit.DIAMONDS, 39);
  }

  private void initializeSuit(Suit suit, int pos){
    for(int i = 0; i < 13; i++){
      deck[pos + i] = new Card(i + 1, suit);
    }
  }

  private void shuffle(){
    for(int i = deck.length - 1; i > 0; i--){
      int random = (int) (Math.random() * (i + 1));
      Card temp = deck[i];
      deck[i] = deck[random];
      deck[random] = temp;
    }
    cardsUsed = 0;
  }

  public Card deal(){
    if(cardsUsed == 52) {
      throw new IllegalStateException("Deck is empty");
    }
    return deck[cardsUsed++];
  }
}
