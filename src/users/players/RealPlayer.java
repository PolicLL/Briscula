package users.players;

import card.Card;

import java.util.List;
import java.util.Scanner;

public class RealPlayer extends AbstractPlayer{

    private Scanner scanner;

    public RealPlayer(List<Card> playerCards, String nickname) {
        super(playerCards, nickname);

        scanner = new Scanner(System.in);
    }

    @Override
    public Card playRound() {

        System.out.println("Your cards : ");
        for(int i = 0; i < this.playerCards.size(); ++i)
            System.out.println(i + " " + playerCards.get(i));

        String input = "";
        int numberInput = -1;


        do{
            System.out.print("Choose card : ");
            input = scanner.next();
            numberInput = Integer.valueOf(input);
        }
        while(numberInput < 0 || numberInput >= playerCards.size());

        Card tempCard = playerCards.get(numberInput);

        playerCards.remove(tempCard);

        return tempCard;
    }
}
