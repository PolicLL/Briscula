package test;

import card.Card;
import card.CardType;
import card.CardValue;
import exceptions.DuplicateCardException;
import main.GameJudge;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import users.admin.Admin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertThrows;

/**
 * This test class is responsible for testing Card functions
 * It has testcases which are responsible for checking does comparison between
 * different Card objects works correctly.
 */

public class CardTest {

    private GameJudge gameJudge;
    private CardType mainCardType = CardType.COPPE;
    private Card firstCard, secondCard;

    @BeforeMethod
    public void setUp() {
        Admin adminMock = mock(Admin.class);
        when(adminMock.getMainCardType()).thenReturn(mainCardType);

        gameJudge = new GameJudge(adminMock);

        firstCard = new Card();
        secondCard = new Card();
    }

    @Test
    public void TestNonMainTypeCards() {

        firstCard.setCardType(CardType.SPADE);
        firstCard.setCardValue(CardValue.ACE);

        secondCard.setCardType(CardType.SPADE);
        secondCard.setCardValue(CardValue.THREE);

        // ASSERTS

        Assert.assertTrue(firstCard.isCardValueBiggerThan(secondCard));

        secondCard.setCardValue(CardValue.FOUR);
        Assert.assertTrue(firstCard.isCardValueBiggerThan(secondCard));

        secondCard.setCardValue(CardValue.THREE);
        Assert.assertTrue(firstCard.isCardValueBiggerThan(secondCard));
    }

    @Test
    public void TestBothCardsAreNotMainCardType() throws DuplicateCardException {
        firstCard.setCardType(CardType.SPADE);
        firstCard.setCardValue(CardValue.ACE);

        secondCard.setCardType(CardType.DENARI);
        secondCard.setCardValue(CardValue.TWO);

        // ASSERTS

        assertFalse(gameJudge.isSecondCardStronger(firstCard, secondCard));

        secondCard.setCardType(CardType.SPADE);
        secondCard.setCardValue(CardValue.THREE);
        assertFalse(gameJudge.isSecondCardStronger(firstCard, secondCard));

        firstCard.setCardValue(CardValue.JACK);
        Assert.assertTrue(gameJudge.isSecondCardStronger(firstCard, secondCard));
    }

    @Test
    public void TestFirstCardMainType() throws DuplicateCardException {
        firstCard.setCardType(CardType.SPADE);
        firstCard.setCardValue(CardValue.ACE);

        secondCard.setCardType(CardType.DENARI);
        secondCard.setCardValue(CardValue.TWO);

        // ASSERTS

        assertFalse(gameJudge.isSecondCardStronger(firstCard, secondCard));

        secondCard.setCardType(CardType.SPADE);
        secondCard.setCardValue(CardValue.THREE);
        assertFalse(gameJudge.isSecondCardStronger(firstCard, secondCard));
    }

    @Test
    public void TestSecondCardMainType() throws DuplicateCardException {
        firstCard.setCardType(CardType.SPADE);
        firstCard.setCardValue(CardValue.ACE);

        secondCard.setCardType(CardType.COPPE);
        secondCard.setCardValue(CardValue.TWO);

        // ASSERTS

        Assert.assertTrue(gameJudge.isSecondCardStronger(firstCard, secondCard));

        secondCard.setCardValue(CardValue.TWO);
        Assert.assertTrue(gameJudge.isSecondCardStronger(firstCard, secondCard));
    }

    @Test
    public void TestBothCardsMainType() throws DuplicateCardException {
        firstCard.setCardType(CardType.COPPE);
        firstCard.setCardValue(CardValue.TWO);

        secondCard.setCardType(CardType.COPPE);
        secondCard.setCardValue(CardValue.THREE);

        // ASSERTS

        Assert.assertTrue(gameJudge.isSecondCardStronger(firstCard, secondCard));

        firstCard.setCardValue(CardValue.ACE);
        assertFalse(gameJudge.isSecondCardStronger(firstCard, secondCard));
    }

    @Test
    public void TestDuplicateCardException() {
        firstCard.setCardType(CardType.COPPE);
        firstCard.setCardValue(CardValue.TWO);

        secondCard.setCardType(CardType.COPPE);
        secondCard.setCardValue(CardValue.TWO);

        assertThrows(DuplicateCardException.class, () -> {
            gameJudge.isSecondCardStronger(firstCard, secondCard);
        });
    }
}
