package game;

import card.Card;
import card.CardType;
import exceptions.DuplicateCardException;
import users.admin.Admin;

import java.util.Queue;

public class GameJudge {

    private Admin admin;

    public GameJudge(Admin admin) {
        this.admin = admin;
    }

    public void calculateRound(Queue<Move> queueMoves)  {
        int tempPointsInRound = 0;

        Move roundWinnerMove = queueMoves.remove();
        tempPointsInRound += roundWinnerMove.card().getPoints();

        while(!queueMoves.isEmpty()){
            Move tempMove = queueMoves.remove();

            tempPointsInRound += tempMove.card().getPoints();

            try {
                if(isSecondCardStronger(roundWinnerMove.card(), tempMove.card()))
                    roundWinnerMove = tempMove;
            } catch (DuplicateCardException e) {
                throw new RuntimeException(e);
            }
        }

        roundWinnerMove.player().incrementPoints(tempPointsInRound);
    }

    public boolean isSecondCardStronger(Card firstPlayersCard, Card secondPlayersCard) throws DuplicateCardException {
        CardType mainCardType = admin.getMainCardType();

        if(firstPlayersCard.equals(secondPlayersCard))
            throw new DuplicateCardException();

        if(!firstPlayersCard.isMainType(mainCardType) && secondPlayersCard.isMainType(mainCardType)){
            return true;
        }
        else if(firstPlayersCard.isMainType(mainCardType) && secondPlayersCard.isMainType(mainCardType)){
            return secondPlayersCard.isCardValueBiggerThan(firstPlayersCard);
        }

        else if(firstPlayersCard.isSameType(secondPlayersCard)){
            return secondPlayersCard.isCardValueBiggerThan(firstPlayersCard);
        }

        return false;
    }
}
