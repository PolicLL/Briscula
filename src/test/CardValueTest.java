package test;

import card.CardValue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardValueTest {

    @Test
    public void TestForThree() {

        CardValue mainCardValue = CardValue.THREE;

        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.TWO));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.FOUR));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.JACK));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.FIVE));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.SIX));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.KING));
        Assert.assertTrue(mainCardValue.isBiggerThan(CardValue.KNIGHT));

        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.ACE));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.THREE));
    }

    @Test
    public void TestForTwo() {

        CardValue mainCardValue = CardValue.TWO;

        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.THREE));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.FOUR));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.FIVE));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.SIX));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.SEVEN));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.JACK));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.KNIGHT));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.KING));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.THREE));
        Assert.assertFalse(mainCardValue.isBiggerThan(CardValue.ACE));
    }

}

