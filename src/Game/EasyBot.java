package Game;


import static Game.Hand.HandUtils.getOptimalHandEasy;

import Game.Card.Card;
import Game.Card.Suit;
import Game.Hand.Hand;
import java.io.IOException;
import java.util.ArrayList;

public class EasyBot extends Player {

  //Will always play the lowest possible card. Never passes unless they have to

  public EasyBot(String name) {
    super(name);
  }

  //Will attempt to play the lowest card combination, that has the maximum amount of cards
  @Override
  public Hand play(boolean isFirst) throws IOException {
    ArrayList<Card> c = new ArrayList<>();
    c.add(new Card(3, Suit.DIAMONDS));
    if (isFirst){
      return getOptimalHandEasy(this.getCards(), c);
    } else {
      return getOptimalHandEasy(this.getCards());
    }
  }

  @Override
  public Hand play(Hand lastPlay) throws IOException {
    return getOptimalHandEasy(this.getCards(), lastPlay);
  }

  @Override
  public boolean isHuman() {
    return false;
  }
}
