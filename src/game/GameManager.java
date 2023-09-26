package game;

import card.Card;
import other.GameMode;
import other.GameOptionNumberOfPlayers;
import users.admin.Admin;
import users.players.AbstractPlayer;

import java.util.ArrayDeque;
import java.util.Queue;

public class GameManager {
    private GameOptionNumberOfPlayers gameOptions;
    private Admin admin;
    private GameJudge gameJudge;

    public GameManager(GameOptionNumberOfPlayers gameOptions, GameMode gameMode) {
        initializeValues(gameOptions);
        prepareGame(gameOptions, gameMode);

        System.out.println("Main card type : " + admin.getMainCardType());
    }

    // INITIALIZATION

    private void initializeValues(GameOptionNumberOfPlayers gameOptions){
        this.gameOptions = gameOptions;
        admin = new Admin();
        gameJudge = new GameJudge(admin);
    }

    private void prepareGame(GameOptionNumberOfPlayers gameOptions, GameMode gameMode){
        admin.prepareDeckAndPlayers(gameOptions, gameMode);
    }

    // End of game checking

    public boolean isGameOver(){
        return admin.getDeck().getNumberOfDeckCards() == 0 && arePlayersDone();
    }

    private boolean arePlayersDone(){
        for(AbstractPlayer tempPlayer : admin.getPlayers())
            if(!tempPlayer.isPlayerDone())
                return false;

        return true;
    }

    // Round play

    public void playRound(){
        AbstractPlayer tempPlayer;

        Queue<Move> queueMoves = new ArrayDeque<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            tempPlayer = admin.getCurrentPlayer();
            Card tempCard = tempPlayer.playRound();
            queueMoves.add(new Move(tempPlayer, tempCard));

            System.out.println("Temp move : " + i + " -> " + tempPlayer.getNickname() + " | " + tempCard);
        }

        //printPlayersCard(queueMoves);

        gameJudge.calculateRound(queueMoves);
        admin.dealNextRound();

        printPlayersValues();
    }

    // Prints

    private void printPlayersCard(Queue<Move> queueMoves){
        queueMoves.forEach(e ->  System.out.println(e.card()));
        System.out.println();
    }

    private void printPlayersValues(){
        admin.getPlayers().forEach(e -> System.out.println("[" + e.getNickname() + "] : " + e.getPoints()));
        System.out.println();
    }

}
