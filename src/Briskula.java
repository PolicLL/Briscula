import card.Deck;
import main.Game;
import other.GameOptions;

import java.util.Collections;

public class Briskula {

    public static void main(String[] args){

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




    }
}
