package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private StickHeroPlayer player;
    private PlatformManager platformManager;  // Add this line
    private GraphicsContext gc;  // Add this line

    private GameState gameState;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stick Hero Game");
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        player = new StickHeroPlayer(WIDTH / 2, HEIGHT - 20);
        platformManager = new PlatformManager();  // Initialize platformManager
        gameState = new GameState();

        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                }
                double deltaTime = (now - lastUpdate) * 1e-9; // Convert nanoseconds to seconds

                // Update game logic here
                player.update(deltaTime, platformManager);
                drawGame();

                lastUpdate = now;
            }
        }.start();

        primaryStage.show();
    }

    private void drawGame() {
        // Clear the canvas
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw platforms, player, cherries, etc.
        platformManager.draw(gc);
        player.draw(gc);
        // Draw other game elements
    }


    // Method to handle key presses for game controls
    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE:
                player.extendStick();
                break;
            case R:
                restartLevel();
                break;
            case S:
                saveGame();
                break;
            case L:
                loadGame();
                break;
            // Add more controls as needed
        }
    }

    // Method to restart the level
    private void restartLevel() {
        // Reset player, platforms, and other game elements
        player = new StickHeroPlayer(WIDTH / 2, HEIGHT - 20);
        platformManager.reset();
        // Reset other game elements as needed
    }

    // Method to save the game state
    private void saveGame() {
        // Save the current game state to the GameState object
        gameState.save(player, platformManager);
    }

    // Method to load a saved game state
    private void loadGame() {
        // Load the game state from the GameState object
        GameState loadedState = gameState.load();
        // Apply the loaded state to the game
        player = loadedState.getPlayer();
        platformManager = loadedState.getPlatformManager();
        // Apply other loaded elements as needed
    }
}
