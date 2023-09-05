package test;

import card.Card;
import card.CardType;
import card.CardValue;
import main.Game;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import other.GameOptions;
import users.Admin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This test class is responsible for testing Card functions
 * It has testcases which are responsible for checking does comparison between
 * different Card objects works correctly.
 */

public class CardTest {

    private Game game;
    private CardType mainCardType = CardType.COPPE;

    @BeforeMethod
    public void setUp() {

        Admin adminMock = mock(Admin.class);

        when(adminMock.getMainCardType()).thenReturn(mainCardType);

        game = new Game(GameOptions.TWO_PLAYERS);
        game.setAdmin(adminMock);
    }

    @Test
    public void IsCardValueBiggerThanComparison() {

        Card mainCard = new Card(CardType.SPADE, CardValue.ACE);
        Card secondCard = new Card(CardType.SPADE, CardValue.THREE);
        Assert.assertTrue(mainCard.isCardValueBiggerThan(secondCard));

        secondCard.setCardValue(CardValue.FOUR);
        Assert.assertTrue(mainCard.isCardValueBiggerThan(secondCard));

        secondCard.setCardValue(CardValue.THREE);
        Assert.assertTrue(mainCard.isCardValueBiggerThan(secondCard));
    }

    @Test
    public void testBothCardsAreNotMainCardType() {

        Card firstPlayersCard = new Card(CardType.SPADE, CardValue.ACE);
        Card secondPlayersCard = new Card(CardType.DENARI, CardValue.TWO);

        boolean result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertFalse(result);

        secondPlayersCard.setCardType(CardType.SPADE);
        secondPlayersCard.setCardValue(CardValue.THREE);
        result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertFalse(result);

        firstPlayersCard.setCardValue(CardValue.JACK);
        result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertTrue(result);
    }

    @Test
    public void testFirstCardMainType() {
        Card firstPlayersCard = new Card(CardType.COPPE, CardValue.TWO);
        Card secondPlayersCard = new Card(CardType.DENARI, CardValue.TWO);

        boolean result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertFalse(result);

        secondPlayersCard.setCardType(CardType.SPADE);
        secondPlayersCard.setCardValue(CardValue.THREE);
        result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertFalse(result);
    }

    @Test
    public void testSecondCardMainType() {
        Card firstPlayersCard = new Card(CardType.SPADE, CardValue.TWO);
        Card secondPlayersCard = new Card(CardType.COPPE, CardValue.ACE);

        boolean result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertTrue(result);

        secondPlayersCard.setCardValue(CardValue.TWO);
        result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertTrue(result);
    }

    @Test
    public void testBothCardsMainType() {
        Card firstPlayersCard = new Card(CardType.COPPE, CardValue.TWO);
        Card secondPlayersCard = new Card(CardType.COPPE, CardValue.THREE);

        boolean result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertTrue(result);

        firstPlayersCard.setCardValue(CardValue.ACE);
        result = game.isSecondCardStronger(firstPlayersCard, secondPlayersCard);
        Assert.assertFalse(result);
    }
}
