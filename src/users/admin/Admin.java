package users.admin;

import card.Card;
import card.CardType;
import card.Deck;
import other.GameOptions;
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

    public Admin() {
        deck = new Deck();
    }

    public void prepareDeckAndPlayers(GameOptions gameOptions){
        prepareDeck(deck, gameOptions);
        initializePlayers(gameOptions);
        chooseMainCardType();
        chooseStartingPlayer();

        System.out.println("STARTING PLAYER : " + indexOfCurrentPlayer);
    }

    public void prepareDeck(Deck deck, GameOptions gameOptions){
        if(gameOptions == GameOptions.THREE_PLAYERS)
            deck.removeOneWithCardValueTwo();
    }

    private void initializePlayers(GameOptions gameOptions) {
        List<List<Card>> listPlayersCards = dealCards(deck, gameOptions);
        this.players = new ArrayList<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            players.add(new Bot(listPlayersCards.get(i), "Name " + i));
        }

        players.remove(3);
        players.add(new RealPlayer(listPlayersCards.get(3), "Name 3"));
    }

    public List<List<Card>> dealCards(Deck deck, GameOptions gameOptions){
        List<List<Card>> playersCardsList = new ArrayList<>();
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

        return playersCardsList;
    }

    private void chooseStartingPlayer(){
        indexOfCurrentPlayer = random.nextInt(players.size());
    }

    public void chooseMainCardType(){
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

    private int getStartNumberOfCards(GameOptions gameOptions){
        return switch (gameOptions){
            case TWO_PLAYERS, FOUR_PLAYERS -> 4;
            case THREE_PLAYERS -> 3;
        };
    }


}
