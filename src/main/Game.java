package main;

import card.Card;
import card.Deck;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private Admin admin;
    private Deck deck;
    private GameOptions gameOptions;

    public Game(GameOptions gameOptions) {
        initializeValues(gameOptions);

        prepareGame(gameOptions);
    }

    private void initializeValues(GameOptions gameOptions){
        deck = new Deck();
        admin = new Admin();
        players = new ArrayList<>();

        this.gameOptions = gameOptions;
    }

    private void prepareGame(GameOptions gameOptions){
        prepareDeck();

        List<List<Card>> playersCards =  admin.dealCards(deck, gameOptions);

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            addPlayer(new Player(playersCards.get(i)));
        }
    }

    private void prepareDeck(){
        if(gameOptions == GameOptions.THREE_PLAYERS)
            deck.removeOneCard();
    }

    private void addPlayer(Player player){
        this.players.add(player);
    }

    @Override
    public String toString() {
        String output = "";

        for(Player player : players)
            output += String.format("Player's cards : %s | Points : %d", player, player.getPoints());

        return output;
    }
}
