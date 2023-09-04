package users;

import card.Card;

import java.util.List;

public class Player {

    private List<Card> playerCards;

    private String nickname;

    private int points = 0;

    public Player(List<Card> playerCards, String nickname) {
        this.playerCards = playerCards;
        this.nickname = nickname;
    }

    public void printCards(){
        this.playerCards.forEach(element -> System.out.print(element + " "));
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String output = "";

        for(Card card : playerCards)
            output += card.toString() + " ";

        return output;
    }
}
