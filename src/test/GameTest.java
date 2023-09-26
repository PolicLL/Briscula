package test;

import card.Card;
import card.CardType;
import card.CardValue;
import game.GameJudge;
import game.Move;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import users.admin.Admin;
import users.players.Bot;

import java.util.*;

import static org.mockito.Mockito.mock;

public class GameTest {

    private GameJudge gameJudge;
    private Queue<Move> queueMoves = new ArrayDeque<>();
    private List<Bot> playerList = new ArrayList<>(Arrays.asList(
            new Bot(new ArrayList<>(), "Name 1"), new Bot(new ArrayList<>(), "Name 2"),
            new Bot(new ArrayList<>(), "Name 3"), new Bot(new ArrayList<>(), "Name 4")
            ));

    private List<Card> cardsList = new ArrayList<>(Arrays.asList(
            new Card(), new Card(), new Card(), new Card()
    ));

    private List<Move> movesList = new ArrayList<>(Arrays.asList(
            new Move(playerList.get(0), cardsList.get(0)), new Move(playerList.get(1), cardsList.get(1)),
            new Move(playerList.get(2), cardsList.get(2)), new Move(playerList.get(3), cardsList.get(3))
    ));

    CardType mainCardType;

    @BeforeMethod
    public void setUp() {
        // Set up mock
        Admin adminMock = mock(Admin.class);
        Mockito.when(adminMock.getMainCardType()).thenAnswer((Answer<CardType>) invocation -> mainCardType);

        gameJudge = new GameJudge(adminMock);

        setPlayersPointsToZero();
    }

    @Test
    public void TestRoundMoreCardsOfMainType() {

        mainCardType = CardType.COPPE;

        cardsList.get(0).setCardType(CardType.SPADE);
        cardsList.get(0).setCardValue(CardValue.THREE);

        cardsList.get(1).setCardType(CardType.COPPE);
        cardsList.get(1).setCardValue(CardValue.TWO);

        cardsList.get(2).setCardType(CardType.DENARI);
        cardsList.get(2).setCardValue(CardValue.JACK);

        cardsList.get(3).setCardType(CardType.COPPE);
        cardsList.get(3).setCardValue(CardValue.KING);

        addMovesAndCalculateRound();

        Assert.assertEquals(16, playerList.get(3).getPoints());
    }

    @Test
    public void TestRoundMoreCardsOfMainType2() {

        mainCardType = CardType.BASTONI;

        cardsList.get(0).setCardType(CardType.DENARI);
        cardsList.get(0).setCardValue(CardValue.THREE);

        cardsList.get(1).setCardType(CardType.DENARI);
        cardsList.get(1).setCardValue(CardValue.ACE);

        cardsList.get(2).setCardType(CardType.BASTONI);
        cardsList.get(2).setCardValue(CardValue.JACK);

        cardsList.get(3).setCardType(CardType.BASTONI);
        cardsList.get(3).setCardValue(CardValue.SEVEN);

        addMovesAndCalculateRound();

        Assert.assertEquals(23, playerList.get(2).getPoints());
    }

    @Test
    public void TestRoundNoCardsOfMainType() {

        mainCardType = CardType.COPPE;

        cardsList.get(0).setCardType(CardType.DENARI);
        cardsList.get(0).setCardValue(CardValue.THREE);

        cardsList.get(1).setCardType(CardType.DENARI);
        cardsList.get(1).setCardValue(CardValue.SEVEN);

        cardsList.get(2).setCardType(CardType.BASTONI);
        cardsList.get(2).setCardValue(CardValue.JACK);

        cardsList.get(3).setCardType(CardType.BASTONI);
        cardsList.get(3).setCardValue(CardValue.SEVEN);

        addMovesAndCalculateRound();

        Assert.assertEquals(12, playerList.get(0).getPoints());
    }

    @Test
    public void TestRoundSmallCardValueMainType() {

        mainCardType = CardType.COPPE;

        cardsList.get(0).setCardType(CardType.DENARI);
        cardsList.get(0).setCardValue(CardValue.THREE);

        cardsList.get(1).setCardType(CardType.DENARI);
        cardsList.get(1).setCardValue(CardValue.SEVEN);

        cardsList.get(2).setCardType(CardType.BASTONI);
        cardsList.get(2).setCardValue(CardValue.JACK);

        cardsList.get(3).setCardType(CardType.COPPE);
        cardsList.get(3).setCardValue(CardValue.TWO);

        addMovesAndCalculateRound();

        Assert.assertEquals(12, playerList.get(3).getPoints());
    }

    private void printPlayers(){
        playerList.forEach(element -> System.out.println(element.getPoints()));
    }

    private void setPlayersPointsToZero(){ playerList.forEach(element -> element.setPointsToZero()); }

    private void addMovesAndCalculateRound(){
        queueMoves.add(movesList.get(0));
        queueMoves.add(movesList.get(1));
        queueMoves.add(movesList.get(2));
        queueMoves.add(movesList.get(3));

        gameJudge.calculateRound(queueMoves);
    }
}
