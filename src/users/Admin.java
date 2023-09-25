package users;

import card.Card;
import card.CardType;
import card.Deck;
import other.GameOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Admin {

    private final Random random = new Random();
    private CardType mainCardType;
    private int indexOfCurrentPlayer = 0;

    public List<List<Card>> dealCards(Deck deck, GameOptions gameOptions){

        prepareDeck(deck, gameOptions);

        List<List<Card>> playersCardsList = new ArrayList<>();
        List<Card> deckCards = deck.getDeckCards();

        int startNumberOfCards = getStartNumberOfCards(gameOptions);

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){

            playersCardsList.add(new LinkedList<>());

            for(int j = 0; j < startNumberOfCards; ++j){
                Card tempCard = deckCards.get(random.nextInt(deckCards.size()));
                deckCards.remove(tempCard);
                playersCardsList.get(i).add(tempCard);
            }
        }

        chooseMainCardType();

        return playersCardsList;
    }

    public void prepareDeck(Deck deck, GameOptions gameOptions){
        if(gameOptions == GameOptions.THREE_PLAYERS)
            deck.removeOneWithCardValueTwo();
    }

    public List<Player> initializePlayers(Deck deck, GameOptions gameOptions) {
        List<List<Card>> listPlayersCards = dealCards(deck, gameOptions);
        List<Player> players = new ArrayList<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            players.add(new Player(listPlayersCards.get(i), "Name " + i));
        }

        chooseStartingPlayer(players);

        return players;
    }

    public void chooseMainCardType(){
        CardType[] cardTypes = CardType.values();
        mainCardType = cardTypes[random.nextInt(cardTypes.length)];
    }

    public void dealNextRound(Deck deck, List<Player> players){
        if(deck.getNumberOfDeckCards() == 0) return;

        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(deck.removeOneCard());
        }
    }

    private void chooseStartingPlayer(List<Player> players){
        indexOfCurrentPlayer = random.nextInt(players.size());
    }

    public void findNextPlayer(List<Player> players){
        ++indexOfCurrentPlayer;

        if(indexOfCurrentPlayer >= players.size())
            indexOfCurrentPlayer = 0;
    }

    public int getIndexOfCurrentPlayer() {
        return indexOfCurrentPlayer;
    }

    public CardType getMainCardType() {
        return mainCardType;
    }

    private int getStartNumberOfCards(GameOptions gameOptions){
        return switch (gameOptions){
            case TWO_PLAYERS, FOUR_PLAYERS -> 4;
            case THREE_PLAYERS -> 3;
        };
    }


}
