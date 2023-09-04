package card;

public enum CardType {
    DENARI, SPADE, COPPE, BASTONI;

    @Override
    public String toString() {
        return String.format("%c", name().charAt(0));
    }
}
