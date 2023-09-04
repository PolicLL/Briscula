import card.Deck;

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



    }
}
