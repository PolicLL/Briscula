package card;

import java.util.Objects;

public class Card {

    private CardType cardType;
    private CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardType == card.cardType && cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, cardValue);
    }

    @Override
    public String toString() {
        return String.format("%s-%s", cardType.toString(), cardValue.toString());
    }

    public boolean isMainType(CardType mainCardType){
        return this.cardType == mainCardType;
    }

    public boolean isSameType(Card otherCard){
        return this.cardType == otherCard.cardType;
    }

    public boolean isCardValueBiggerThan(Card secondCard){
        return this.cardValue.isBiggerThan(secondCard.cardValue);
    }


    // SETTERS GETTERS

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public int getPoints(){
        return this.cardValue.getPoints();
    }




}
