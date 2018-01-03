package Game.Card;

public class Card {

  private final int value;
  private final Suit suit;

  public Card(int value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  public int getValue() {
    return value;
  }

  public Suit getSuit() {
    return suit;
  }

  public int getCardValue(){
    int val = value;
    if(val == 1 || val == 2){
      val += 13;
    }
    val -= 3;
    return val * 4 + suit.getValue();
  }

  @Override
  public String toString(){
    String val;
    switch(value){
      case 13:
        val = "King";
        break;
      case 12:
        val = "Queen";
        break;
      case 11:
        val = "Jack";
        break;
      case 1:
        val = "Ace";
        break;
      default:
        val = String.valueOf(value);
        break;
    }
    return val + " - " + suit.toString();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card){
      Card c = (Card) obj;
      return c.getSuit() == this.getSuit() && c.getValue() == this.getValue();
    }
    return false;
  }
}
