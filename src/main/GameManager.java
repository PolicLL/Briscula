package main;

import card.Card;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.ArrayDeque;
import java.util.Queue;

public class GameManager {
    private GameOptions gameOptions;
    private Admin admin;
    private GameJudge gameJudge;

    public GameManager(GameOptions gameOptions) {
        initializeValues(gameOptions);
        prepareGame(gameOptions);
    }

    // INITIALIZATION

    private void initializeValues(GameOptions gameOptions){
        this.gameOptions = gameOptions;
        admin = new Admin();
        gameJudge = new GameJudge(admin);
    }

    private void prepareGame(GameOptions gameOptions){
        admin.prepareDeckAndPlayers(gameOptions);
    }

    // End of game checking

    public boolean isGameOver(){
        return admin.getDeck().getNumberOfDeckCards() == 0 && arePlayersDone();
    }

    private boolean arePlayersDone(){
        for(Player tempPlayer : admin.getPlayers())
            if(!tempPlayer.isPlayerDone())
                return false;

        return true;
    }

    // Round play

    public void playRound(){
        Player tempPlayer;

        Queue<Move> queueMoves = new ArrayDeque<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            tempPlayer = admin.getCurrentPlayer();
            Card tempCard = tempPlayer.playRound();
            queueMoves.add(new Move(tempPlayer, tempCard));
        }

        gameJudge.calculateRound(queueMoves);
        admin.dealNextRound();
        printPlayersValues();
    }

    // Prints

    private void printStateAfterRound( Queue<Move> queueMoves){
        for(Move tempMove : queueMoves){
            tempMove.player().getPlayerCards().forEach(e -> System.out.print(e + " "));
            System.out.print("POINTS : " + tempMove.player().getPoints() + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private void printPlayersValues(){
        //players.forEach(e -> System.out.println(e.getPoints()));
        System.out.println();
    }

}
