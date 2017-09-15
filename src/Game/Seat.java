package Game;

public class Seat {

  //Functionally serves as a circular array holding the players

  private Seat next;
  private final Player player;

  public Seat(Player player) {
    this.player = player;
  }

  public Seat getNext() {
    return next;
  }

  public void setNext(Seat next) {
    this.next = next;
  }

  public Player getPlayer() {
    return player;
  }

}
