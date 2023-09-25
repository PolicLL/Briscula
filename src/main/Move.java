package main;

import card.Card;
import users.Player;

public record Move(Player player, Card card) {
    @Override
    public String toString() {
        return player.toString() + " " + card.toString();
    }
}
