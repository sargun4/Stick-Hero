package application;
 
import java.io.Serializable;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Main extends Application implements Serializable{
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 600;

    private StickHeroPlayer player;
    private PlatformManager platformManager;   

    private GameState gameState;
    private int high_score;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
    }

    private void handleKeyPress(KeyCode code) {
         
    }

    // Method to restart the level
    private void restartLevel() {
         
    }

    // Method to save the game state
    private void saveGame() {
         
    }

    // Method to load a saved game state
    private void loadGame() {
         
    }

	public int getHigh_score() {
		return high_score;
	}

//	public void setHigh_score(int high_score) {
//		this.high_score = high_score;
//	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
