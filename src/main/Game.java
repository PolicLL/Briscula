package main;

import card.Card;
import card.CardType;
import card.Deck;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.*;

public class Game {

    private List<Player> players;
    private Admin admin;
    private Deck deck;
    private GameOptions gameOptions;

    private Random random = new Random();

    private int indexOfCurrentPlayer = 0;

    public Game(GameOptions gameOptions) {
        initializeValues(gameOptions);

        prepareGame(gameOptions);
    }

    // INITIALIZATION

    private void initializeValues(GameOptions gameOptions){
        deck = new Deck();
        admin = new Admin();
        players = new ArrayList<>();

        this.gameOptions = gameOptions;
    }

    private void prepareGame(GameOptions gameOptions){
        prepareDeck();

        List<List<Card>> playersCards =  admin.dealCards(deck, gameOptions);

        initializePlayers(gameOptions, playersCards);
        chooseStartingPlayer();
    }

    private void prepareDeck(){
        if(gameOptions == GameOptions.THREE_PLAYERS)
            deck.removeOneCard();
    }

    private void initializePlayers(GameOptions gameOptions, List<List<Card>> playersCards) {
        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            addPlayer(new Player(playersCards.get(i), "Name " + i));
        }
    }

    private void chooseStartingPlayer(){
        indexOfCurrentPlayer = random.nextInt(this.players.size());
    }

    private void addPlayer(Player player){
        this.players.add(player);
    }

    // GAME

    public void startGame(){
        while(isGameOver()){
            playRound();
        }
    }

    private boolean isGameOver(){
        return this.deck.getNumberOfDeckCards() == 0;
    }

    private void playRound(){
        Player tempPlayer = null;

        Queue<Card> queueCards = new ArrayDeque<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            tempPlayer = players.get(indexOfCurrentPlayer);

            Card tempCard = tempPlayer.playRound();

            queueCards.add(tempCard);

            findNextPlayer();
        }

        calculateRound(queueCards);
    }

    private void findNextPlayer(){
        ++indexOfCurrentPlayer;

        if(indexOfCurrentPlayer >= players.size())
            indexOfCurrentPlayer = 0;
    }

    private void calculateRound(Queue<Card> queueCards){
        CardType mainCardType = admin.getMainCardType();

        int tempPointsInRound = 0;

        Player roundWinner = null;

        roundWinner.incrementPoints(tempPointsInRound);
    }

    //

    @Override
    public String toString() {
        String output = "";

        for(Player player : players)
            output += String.format("Player's cards : %s | Points : %d\n", player, player.getPoints());

        return output;
    }
}
