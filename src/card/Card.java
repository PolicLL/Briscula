package card;

public record Card(CardType cardType, CardValue cardValue, int cardPoints) {

    @Override
    public String toString() {
        return String.format("%s-%s", cardType.toString(), cardValue.toString());
    }

    @Override
    public int cardPoints() {
        return this.cardValue.getPoints();
    }
}
