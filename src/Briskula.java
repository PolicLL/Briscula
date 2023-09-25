import main.Game;
import other.GameOptions;

public class Briskula {

    public static void main(String[] args){

        /*

        Deck deck = new Deck();

        System.out.println(deck);

        // Shuffling

        Collections.shuffle(deck.getDeckCards());

        System.out.println(deck);

        deck.removeOneCard();

        System.out.println(deck);

        //

        //deck.dealCards(6);

        Game game = new Game(GameOptions.TWO_PLAYERS);


        System.out.println(game);

        // Queue test

        Queue<Integer> myQueue = new ArrayDeque<>();

        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);

        System.out.println(myQueue.peek());

         */


        Game game = new Game(GameOptions.FOUR_PLAYERS);

        game.startGame();

    }
}
