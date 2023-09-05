package card;

public class Card {

    private CardType cardType;
    private CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
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
