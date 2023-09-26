package users.players;

import card.Card;

import java.util.List;

public abstract class AbstractPlayer {
    protected List<Card> playerCards;

    protected String nickname;

    protected int points = 0;


    public AbstractPlayer(List<Card> playerCards, String nickname) {
        this.playerCards = playerCards;
        this.nickname = nickname;
    }

    public abstract Card playRound();

    public boolean isPlayerDone(){
        return this.playerCards.size() == 0;
    }

    public void incrementPoints(int points){
        this.points += points;
    }

    public void addCard(Card card){
        this.playerCards.add(card);
    }

    public int getPoints() {
        return points;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPointsToZero(){
        this.points = 0;
    }
}
