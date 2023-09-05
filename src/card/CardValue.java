package card;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    TWO(0) , FOUR(0), FIVE(0),
    SIX(0), SEVEN(0), JACK(2),
    KNIGHT(3), KING(4),
    THREE(10), ACE(11);

    private static Map<CardValue, Integer> cardValuesMap = new HashMap<>();

    static {
        CardValue[] cardValues = CardValue.values();

        for(int i = 0; i < cardValues.length; ++i)
            cardValuesMap.put(cardValues[i], i);
    }

    private int points;

    CardValue(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public boolean isBiggerThan(CardValue tempCardValue){
        return cardValuesMap.get(this) > cardValuesMap.get(tempCardValue);
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", this.name(), this.points);
    }
}
