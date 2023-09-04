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

    public List<List<Card>> dealCards(Deck deck, GameOptions gameOptions){
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

    private void chooseMainCardType(){
        CardType[] cardTypes = CardType.values();
        mainCardType = cardTypes[random.nextInt(cardTypes.length)];
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
