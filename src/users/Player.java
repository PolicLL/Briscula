package users;

import card.Card;

import java.util.List;

public class Player {

    private List<Card> playerCards;

    public Player(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public void printCards(){
        this.playerCards.forEach(element -> System.out.print(element + " "));
    }

    public int getPoints(){
        int numberOfPoints = 0;

        for(Card card : playerCards)
            numberOfPoints += card.cardPoints();

        return  numberOfPoints;
    }

    @Override
    public String toString() {
        String output = "";

        for(Card card : playerCards)
            output += card.toString() + " ";

        return output;
    }
}
