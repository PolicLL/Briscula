package users.players;

import card.Card;

import java.util.List;

public class SmartBot extends AbstractPlayer {

    public SmartBot(List<Card> playerCards, String nickname) {
        super(playerCards, nickname);
    }

    @Override
    public Card playRound() {
        return null;
    }




}
