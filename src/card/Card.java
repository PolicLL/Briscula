package card;

public record Card(CardType cardType, CardValue cardValue) {

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

    public boolean isBigger(Card secondCard){
        return this.cardValue.isBigger(secondCard.cardValue);
    }

    public int getPoints(){
        return this.cardValue.getPoints();
    }




}
