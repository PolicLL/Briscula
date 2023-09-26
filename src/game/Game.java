package game;

import other.GameMode;
import other.GameOptionNumberOfPlayers;

public class Game {

    private GameManager gameManager;

    public Game(GameOptionNumberOfPlayers gameOptions, GameMode gameMode) {
        gameManager = new GameManager(gameOptions, gameMode);
    }

    public void startGame(){
        while(!gameManager.isGameOver()){
            gameManager.playRound();
        }
    }
}
