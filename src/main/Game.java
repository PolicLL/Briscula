package main;

import other.GameOptions;

public class Game {

    private GameManager gameManager;

    public Game(GameOptions gameOptions) {
        gameManager = new GameManager(gameOptions);
    }

    public void startGame(){
        while(!gameManager.isGameOver()){
            gameManager.playRound();
        }
    }
}
