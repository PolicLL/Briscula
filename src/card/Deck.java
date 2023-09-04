package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deckCards;
    private CardType[] cardTypes = CardType.values();
    private CardValue[] cardValues = CardValue.values();
    private Random random = new Random();

    public Deck() {
        deckCards = new ArrayList<>();
        fillDeck();
    }

    private void fillDeck() {
        deckCards.clear();

        for (CardType type : cardTypes) {
            for (CardValue value : cardValues) {
                deckCards.add(new Card(type, value, 0));
            }
        }
    }


    public void removeOneCard(){
        deckCards.remove(random.nextInt(deckCards.size()));
    }

    public List<Card> getDeckCards() {
        return deckCards;
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("\n");

        int numPlayers = isGameWithThreePlayers() ? 3 : 4;
        int cardsPerRow = isGameWithThreePlayers() ? 13 : 10;

        for (int i = 0; i < numPlayers; ++i) {
            List<Card> tempElements = deckCards.subList(i * cardsPerRow, (i + 1) * cardsPerRow);

            for (Card element : tempElements)
                output.append(element).append(" ");

            output.append("\n");
        }

        output.append("Number of cards : ").append(deckCards.size()).append("\n");

        return output.toString();
    }

    private boolean isGameWithThreePlayers() {
        return deckCards.size() == 39;
    }

}
