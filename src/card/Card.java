package card;

public record Card(CardType cardType, CardValue cardValue, int cardPoints) {

    @Override
    public String toString() {
        return String.format("%s-%s", cardType.toString(), cardValue.toString());
    }

    public int getCardPoints() {
        return this.cardValue.getPoints();
    }
}
