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

    private Random random = new Random();

    private int indexOfCurrentPlayer = 0;

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
            tempPlayer = players.get(indexOfCurrentPlayer);

            Card tempCard = tempPlayer.playRound();

            queueMoves.add(new Move(tempPlayer, tempCard));

            findNextPlayer();
        }

        gameJudge.calculateRound(queueMoves);

        dealNextRound();
    }

    private void dealNextRound(){
        if(this.deck.getNumberOfDeckCards() == 0) return;

        for(int i = 0; i < players.size(); ++i){
            players.get(i).getPlayerCards().add(deck.removeOneCard());
        }
    }


    private void findNextPlayer(){
        ++indexOfCurrentPlayer;

        if(indexOfCurrentPlayer >= players.size())
            indexOfCurrentPlayer = 0;
    }
}
