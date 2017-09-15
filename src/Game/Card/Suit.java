package Game.Card;

public enum Suit {
  SPADES(4), HEARTS(3), CLUBS(2), DIAMONDS(1);

  public final int rank;

  Suit(int rank){
    this.rank = rank;
  }
}
