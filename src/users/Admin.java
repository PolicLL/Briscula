package users;

import card.Card;
import card.Deck;
import other.GameOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Admin {

    private final Random random = new Random();

    public List<List<Card>> dealCards(Deck deck, GameOptions gameOptions){
        List<List<Card>> playersCardsList = new ArrayList<>();
        List<Card> deckCards = deck.getDeckCards();

        int startNumberOfCards = getStartNumberOfCards(gameOptions);

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){

            playersCardsList.set(i, new LinkedList<>());

            for(int j = 0; j < startNumberOfCards; ++j){
                Card tempCard = deckCards.get(random.nextInt(deckCards.size()));
                deckCards.remove(tempCard);
                playersCardsList.get(i).add(tempCard);
            }
        }

        return playersCardsList;
    }

    private int getStartNumberOfCards(GameOptions gameOptions){
        return switch (gameOptions){
            case TWO_PLAYERS, FOUR_PLAYERS -> 4;
            case THREE_PLAYERS -> 3;
        };
    }


}
