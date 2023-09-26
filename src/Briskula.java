import game.Game;
import other.GameMode;
import other.GameOptionNumberOfPlayers;

public class Briskula {

    public static void main(String[] args){

        Game game = new Game(GameOptionNumberOfPlayers.FOUR_PLAYERS, GameMode.BOTS_AND_HUMAN);

        game.startGame();


    }
}
