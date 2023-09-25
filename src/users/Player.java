package users;

import card.Card;

import java.util.List;
import java.util.Random;

public class Player {

    private List<Card> playerCards;

    private String nickname;

    private int points = 0;

    private Random random = new Random();

    public Player(List<Card> playerCards, String nickname) {
        this.playerCards = playerCards;
        this.nickname = nickname;

        this.points = 0;
    }

    //

    public Card playRound(){
        Card tempCard = playerCards.get(random.nextInt(playerCards.size()));
        playerCards.remove(tempCard);

        return tempCard;
    }

    public boolean isPlayerDone(){
        return this.playerCards.size() == 0;
    }

    public void incrementPoints(int points){
        this.points += points;
    }

    //


    public int getPoints() {
        return points;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPointsToZero(){
        this.points = 0;
    }

    @Override
    public String toString() {
        String output = "";

        for(Card card : playerCards)
            output += card.toString() + " ";

        return output;
    }
}
