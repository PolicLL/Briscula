package main;

import card.Card;
import card.CardType;
import card.Deck;
import exceptions.DuplicateCardException;
import other.GameOptions;
import users.Admin;
import users.Player;

import java.util.*;

public class Game {

    private List<Player> players;
    private Admin admin;
    private Deck deck;
    private GameOptions gameOptions;

    private Random random = new Random();

    private int indexOfCurrentPlayer = 0;

    public Game(GameOptions gameOptions) {
        initializeValues(gameOptions);

        prepareGame(gameOptions);
    }

    // INITIALIZATION

    private void initializeValues(GameOptions gameOptions){
        deck = new Deck();
        admin = new Admin();
        players = new ArrayList<>();

        this.gameOptions = gameOptions;
    }

    private void prepareGame(GameOptions gameOptions){
        prepareDeck();

        List<List<Card>> playersCards =  admin.dealCards(deck, gameOptions);

        initializePlayers(gameOptions, playersCards);
        chooseStartingPlayer();
    }

    private void prepareDeck(){
        if(gameOptions == GameOptions.THREE_PLAYERS)
            deck.removeOneCard();
    }

    private void initializePlayers(GameOptions gameOptions, List<List<Card>> playersCards) {
        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            addPlayer(new Player(playersCards.get(i), "Name " + i));
        }
    }

    private void chooseStartingPlayer(){
        indexOfCurrentPlayer = random.nextInt(this.players.size());
    }

    private void addPlayer(Player player){
        this.players.add(player);
    }

    // GAME

    public void startGame(){
        while(isGameOver()){
            playRound();
        }
    }

    // also check if all other players have no more cards
    private boolean isGameOver(){
        return this.deck.getNumberOfDeckCards() == 0 && arePlayersDone();
    }

    private boolean arePlayersDone(){
        for(Player tempPlayer : players)
            if(!tempPlayer.isPlayerDone())
                return false;

        return true;
    }

    private void playRound(){
        Player tempPlayer = null;

        Queue<Move> queueMoves = new ArrayDeque<>();

        for(int i = 0; i < gameOptions.getNumberOfPlayers(); ++i){
            tempPlayer = players.get(indexOfCurrentPlayer);

            Card tempCard = tempPlayer.playRound();

            queueMoves.add(new Move(tempPlayer, tempCard));

            findNextPlayer();
        }

        calculateRound(queueMoves);
    }

    private void findNextPlayer(){
        ++indexOfCurrentPlayer;

        if(indexOfCurrentPlayer >= players.size())
            indexOfCurrentPlayer = 0;
    }

    public void calculateRound(Queue<Move> queueMoves)  {
        int tempPointsInRound = 0;

        Move roundWinnerMove = queueMoves.remove();
        tempPointsInRound += roundWinnerMove.card().getPoints();

        while(!queueMoves.isEmpty()){
            Move tempMove = queueMoves.remove();

            tempPointsInRound += tempMove.card().getPoints();

            try {
                if(isSecondCardStronger(roundWinnerMove.card(), tempMove.card()))
                    roundWinnerMove = tempMove;
            } catch (DuplicateCardException e) {
                throw new RuntimeException(e);
            }
        }

        roundWinnerMove.player().incrementPoints(tempPointsInRound);
    }

/*
    public boolean isSecondCardStronger(Card firstPlayersCard, Card secondPlayersCard){
        CardType mainCardType = admin.getMainCardType();

        if(firstPlayersCard.isMainType(mainCardType)) {
            if(!secondPlayersCard.isMainType(mainCardType)) return false;
        }
        else {
            if(secondPlayersCard.isMainType(mainCardType)) {
                return false;
            }
        }

        if(firstPlayersCard.isSameType(secondPlayersCard)) return firstPlayersCard.isCardValueBiggerThan(secondPlayersCard);

        return true;
    }
 */

    public boolean isSecondCardStronger(Card firstPlayersCard, Card secondPlayersCard) throws DuplicateCardException {
        CardType mainCardType = admin.getMainCardType();

        if(firstPlayersCard.equals(secondPlayersCard))
            throw new DuplicateCardException();

        if(!firstPlayersCard.isMainType(mainCardType) && secondPlayersCard.isMainType(mainCardType)){
            return true;
        }
        else if(firstPlayersCard.isMainType(mainCardType) && secondPlayersCard.isMainType(mainCardType)){
            return secondPlayersCard.isCardValueBiggerThan(firstPlayersCard);
        }

        else if(firstPlayersCard.isSameType(secondPlayersCard)){
            return secondPlayersCard.isCardValueBiggerThan(firstPlayersCard);
        }

        return false;
    }

    //


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        String output = "";

        for(Player player : players)
            output += String.format("Player's cards : %s | Points : %d\n", player, player.getPoints());

        return output;
    }
}
