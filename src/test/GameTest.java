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

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private Game game;
    private Queue<Move> queueMoves = new ArrayDeque<>();
    private List<Player> playerList = new ArrayList<>(Arrays.asList(
            new Player(new ArrayList<>(), "Name 1"),
            new Player(new ArrayList<>(), "Name 2"),
            new Player(new ArrayList<>(), "Name 3"),
            new Player(new ArrayList<>(), "Name 4")
            ));

    private List<Card> cardsList = new ArrayList<>(Arrays.asList(
            new Card(CardType.SPADE, CardValue.THREE),
            new Card(CardType.COPPE, CardValue.TWO),
            new Card(CardType.DENARI, CardValue.JACK),
            new Card(CardType.COPPE, CardValue.KING)
    ));

    private List<Move> movesList = new ArrayList<>(Arrays.asList(
            new Move(playerList.get(0), cardsList.get(0)),
            new Move(playerList.get(1), cardsList.get(1)),
            new Move(playerList.get(2), cardsList.get(2)),
            new Move(playerList.get(3), cardsList.get(3))
    ));



    @BeforeMethod
    public void setUp() {
        Admin adminMock = mock(Admin.class);
        final CardType mainCardType = CardType.COPPE;

        when(adminMock.getMainCardType()).thenReturn(mainCardType);

        game = new main.Game(GameOptions.TWO_PLAYERS);
        game.setAdmin(adminMock);

        // Setting values for round calculation

    }

    @Test
    public void TestRound() {

        queueMoves.add(movesList.get(0));
        queueMoves.add(movesList.get(1));
        queueMoves.add(movesList.get(2));
        queueMoves.add(movesList.get(3));

        game.calculateRound(queueMoves);

        Assert.assertEquals(16, playerList.get(3).getPoints());
    }

    @Test
    public void TestRound2() {

        cardsList.get(0).setCardType(CardType.DENARI);
        cardsList.get(0).setCardValue(CardValue.THREE);

        cardsList.get(1).setCardType(CardType.DENARI);
        cardsList.get(1).setCardValue(CardValue.ACE);

        cardsList.get(2).setCardType(CardType.BASTONI);
        cardsList.get(2).setCardValue(CardValue.JACK);

        cardsList.get(3).setCardType(CardType.BASTONI);
        cardsList.get(3).setCardValue(CardValue.SEVEN);

        queueMoves.add(movesList.get(0));
        queueMoves.add(movesList.get(1));
        queueMoves.add(movesList.get(2));
        queueMoves.add(movesList.get(3));

        game.calculateRound(queueMoves);

        printPlayers();

        Assert.assertEquals(23, playerList.get(1).getPoints());
    }

    private void printPlayers(){
        for(Player player : playerList)
            System.out.println(player.getPoints());
    }
}
