package test;

import card.Card;
import card.CardType;
import card.CardValue;
import main.Game;
import main.Move;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private Game game;
    private Queue<Move> queueMoves = new ArrayDeque<>();

    @BeforeMethod
    public void setUp() {
        Admin adminMock = mock(Admin.class);
        CardType mainCardType = CardType.COPPE;

        when(adminMock.getMainCardType()).thenReturn(mainCardType);

        game = new main.Game(GameOptions.TWO_PLAYERS);
        game.setAdmin(adminMock);

        // Setting values for round calculation

    }

    @Test
    public void TestRound() {

        Player player1 = new Player(new ArrayList<>(), "Name 1");
        Player player2 = new Player(new ArrayList<>(), "Name 2");
        Player player3 = new Player(new ArrayList<>(), "Name 3");
        Player player4 = new Player(new ArrayList<>(), "Name 4");

        Card card1 = new Card(CardType.SPADE, CardValue.THREE);
        Card card2 = new Card(CardType.COPPE, CardValue.TWO);
        Card card3 = new Card(CardType.DENARI, CardValue.JACK);
        Card card4 = new Card(CardType.COPPE, CardValue.KING);

        Move move1 = new Move(player1, card1);
        Move move2 = new Move(player2, card2);
        Move move3 = new Move(player3, card3);
        Move move4 = new Move(player4, card4);

        queueMoves.add(move1);
        queueMoves.add(move2);
        queueMoves.add(move3);
        queueMoves.add(move4);

        game.calculateRound(queueMoves);

        Assert.assertEquals(16, player4.getPoints());
    }
}
