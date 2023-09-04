package test;

import card.Card;
import card.CardType;
import card.CardValue;
import main.Game;
import org.testng.annotations.Test;
import other.GameOptions;
import users.Admin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardTest {


    @Test
    public void CardTestComparison() {

    }

    @Test
    public void testIsFirstCardStronger() {
        // Create a mock admin object
        Admin admin = mock(Admin.class);

        // Define the return value when getMainCardType() is called
        when(admin.getMainCardType()).thenReturn(CardType.SPADE);

        // Create an instance of your class under test and inject the mock admin
        Game yourClassUnderTest = new Game(GameOptions.TWO_PLAYERS);

        yourClassUnderTest.setAdmin(admin);


        Card firstPlayersCard = new Card(CardType.SPADE, CardValue.ACE);
        Card secondPlayersCard = new Card(CardType.DENARI, CardValue.ACE);

        boolean result = yourClassUnderTest.isFirstCardStronger(firstPlayersCard, secondPlayersCard);


    }




}

