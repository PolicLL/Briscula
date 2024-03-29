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
                deckCards.add(new Card(type, value));
            }
        }
    }


    public Card removeOneCard(){
        return deckCards.remove(random.nextInt(deckCards.size()));
    }

    public Card removeOneWithCardValueTwo(){
        int index = -1;
        do
        {
            index = random.nextInt(deckCards.size());
        }
        while(deckCards.get(index).getCardValue() != CardValue.TWO);

        return deckCards.remove(index);
    }

    public List<Card> getDeckCards() {
        return deckCards;
    }

    public int getNumberOfDeckCards(){
        return this.deckCards.size();
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
