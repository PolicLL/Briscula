package users.admin;

import card.Card;
import card.CardType;
import card.Deck;
import other.GameMode;
import other.GameOptionNumberOfPlayers;
import users.players.AbstractPlayer;
import users.players.Bot;
import users.players.RealPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Admin {

    private final Random random = new Random();
    private int indexOfCurrentPlayer = 0;
    private CardType mainCardType;
    private List<AbstractPlayer> players;
    private Deck deck;

    private List<List<Card>> playersCardsList;

    public Admin() {
        deck = new Deck();
    }

    public void prepareDeckAndPlayers(GameOptionNumberOfPlayers gameOptions, GameMode gameMode){
        prepareDeck(deck, gameOptions);
        initializePlayers(gameOptions, gameMode);
        chooseMainCardType();
        chooseStartingPlayer();

        System.out.println("STARTING PLAYER : " + indexOfCurrentPlayer);
    }

    private void prepareDeck(Deck deck, GameOptionNumberOfPlayers gameOptions){
        if(gameOptions == GameOptionNumberOfPlayers.THREE_PLAYERS)
            deck.removeOneWithCardValueTwo();
    }

    private void initializePlayers(GameOptionNumberOfPlayers gameOptions,  GameMode gameMode) {
        dealCards(deck, gameOptions);

        if(gameMode == GameMode.ALL_BOTS)
            addBotPlayers(gameOptions);
        else if(gameMode == GameMode.BOTS_AND_HUMAN)
            addBotPlayersAndHuman(gameOptions);
    }

    private void addBotPlayers(GameOptionNumberOfPlayers gameOptions){
        this.players = new ArrayList<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            players.add(new Bot(playersCardsList.get(i), "Name " + i));
        }
    }

    private void addBotPlayersAndHuman(GameOptionNumberOfPlayers gameOptions){
        addBotPlayers(gameOptions);
        addOneHumanPlayer(playersCardsList, "Human Player");

    }

    private void addOneHumanPlayer(List<List<Card>> listPlayersCards, String name){
        players.remove(players.size() - 1);
        players.add(new RealPlayer(listPlayersCards.get(listPlayersCards.size() - 1), name));
    }

    private void dealCards(Deck deck, GameOptionNumberOfPlayers gameOptions){
        playersCardsList = new ArrayList<>();
        List<Card> deckCards = deck.getDeckCards();

        int NUMBER_OF_CARDS_PER_PLAYER = getStartNumberOfCards(gameOptions);

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){

            playersCardsList.add(new LinkedList<>());

            for(int j = 0; j < NUMBER_OF_CARDS_PER_PLAYER; ++j){
                Card tempCard = deckCards.get(random.nextInt(deckCards.size()));
                deckCards.remove(tempCard);
                playersCardsList.get(i).add(tempCard);
            }
        }
    }

    private void chooseStartingPlayer(){
        indexOfCurrentPlayer = random.nextInt(players.size());
    }

    private void chooseMainCardType(){
        CardType[] cardTypes = CardType.values();
        mainCardType = cardTypes[random.nextInt(cardTypes.length)];
    }

    public void dealNextRound(){
        if(deck.getNumberOfDeckCards() == 0) return;

        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(deck.removeOneCard());
        }
    }

    private void findNextPlayer(){
        ++indexOfCurrentPlayer;

        if(indexOfCurrentPlayer >= players.size())
            indexOfCurrentPlayer = 0;
    }

    public AbstractPlayer getCurrentPlayer(){
        AbstractPlayer currentPlayer = players.get(indexOfCurrentPlayer);
        findNextPlayer();
        return currentPlayer;
    }

    public CardType getMainCardType() {
        return mainCardType;
    }

    public List<AbstractPlayer> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    private int getStartNumberOfCards(GameOptionNumberOfPlayers gameOptions){
        return switch (gameOptions){
            case TWO_PLAYERS, FOUR_PLAYERS -> 4;
            case THREE_PLAYERS -> 3;
        };
    }


}
