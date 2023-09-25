package main;

import card.Card;
import card.Deck;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.*;

public class GameManager {
    private List<Player> players;
    private Admin admin;
    private Deck deck;
    private GameOptions gameOptions;
    private GameJudge gameJudge;

    public GameManager(GameOptions gameOptions) {
        initializeValues(gameOptions);
        prepareGame(gameOptions);
    }

    // INITIALIZATION

    private void initializeValues(GameOptions gameOptions){
        deck = new Deck();
        admin = new Admin();
        players = new ArrayList<>();
        gameJudge = new GameJudge(admin);
        this.gameOptions = gameOptions;
    }

    public void prepareGame(GameOptions gameOptions){
        players = admin.initializePlayers(deck, gameOptions);
        admin.chooseMainCardType();
    }

    // GAME

    public void startGame(){
        while(!isGameOver()){
            playRound();
        }
    }

    // also check if all other players have no more cards
    private boolean isGameOver(){
        return this.deck.getNumberOfDeckCards() == 0 && arePlayersDone();
    }

    private boolean arePlayersDone(){
        for(Player tempPlayer : players)
            if(!tempPlayer.isPlayerDone())
                return false;

        return true;
    }

    private void playRound(){
        Player tempPlayer;

        Queue<Move> queueMoves = new ArrayDeque<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            tempPlayer = players.get(admin.getIndexOfCurrentPlayer());

            Card tempCard = tempPlayer.playRound();

            queueMoves.add(new Move(tempPlayer, tempCard));

            admin.findNextPlayer(players);
        }

        gameJudge.calculateRound(queueMoves);

        admin.dealNextRound(deck, players);

        printPlayersValues();

    }

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
        players.forEach(e -> System.out.println(e.getPoints()));
        System.out.println();
    }

}
