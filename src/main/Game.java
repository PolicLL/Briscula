package main;

import card.Deck;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Game {

    private List<Player> players;
    private Admin admin;
    private Deck deck;
    private GameOptions gameOptions;
    private GameJudge gameJudge;

    private GameManager gameManager;

    public Game(GameOptions gameOptions) {
        initializeValues(gameOptions);
        //prepareGame(gameOptions);
    }

    // INITIALIZATION

    private void initializeValues(GameOptions gameOptions){
        deck = new Deck();
        admin = new Admin();
        players = new ArrayList<>();
        gameJudge = new GameJudge(admin);
        gameManager = new GameManager(gameOptions);

        this.gameOptions = gameOptions;


    }




    // GAME
/*
    public void startGame(){
        while(!isGameOver()){
            playRound();
        }
    }
  */
    private void dealNextRound(){
        if(this.deck.getNumberOfDeckCards() == 0) return;

        for(int i = 0; i < players.size(); ++i){
            players.get(i).getPlayerCards().add(deck.removeOneCard());
        }
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

    @Override
    public String toString() {
        String output = "";

        for(Player player : players)
            output += String.format("Player's cards : %s | Points : %d\n", player, player.getPoints());

        return output;
    }
}
