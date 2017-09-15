package Game.Hand;

public enum HandEnum {
  StraightFlush (8, "Straight Flush"),
  FourOfaKind(7, "Four of a Kind"),
  FullHouse(6, "Full House"),
  Flush(5, "Flush"),
  Straight(4, "Straight"),
  Triples(3, "Triples"),
  Pair(2, "Pair"),
  Single(1, "Single"),
  Invalid(0, "Invalid");

  public final int rank;
  public final String name;

  HandEnum(int rank, String name) {
    this.rank = rank;
    this.name = name;
  }


  @Override
  public String toString() {
    return name;
  }
}
