package card;

public enum CardValue {
    TWO(0), THREE(10), FOUR(0),
    FIVE(0), SIX(0), SEVEN(0),
    JACK(2), KNIGHT(3), KING(4), ACE(11);

    private int points;

    CardValue(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", this.name(), this.points);
    }
}
