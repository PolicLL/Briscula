package main;

import card.Card;
import users.players.AbstractPlayer;

public record Move(AbstractPlayer player, Card card) {
    @Override
    public String toString() {
        return player.toString() + " " + card.toString();
    }
}
