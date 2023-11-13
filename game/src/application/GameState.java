package application;

import java.io.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    // Add fields for the game state (player, platforms, etc.)
    private StickHeroPlayer player;
    private PlatformManager platformManager;

    public void save(StickHeroPlayer player, PlatformManager platformManager) {
        // Save the game state
        this.player = player;
        this.platformManager = platformManager;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saved_game.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState load() {
        // Load the game state
        GameState loadedState = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saved_game.ser"))) {
            loadedState = (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return loadedState;
    }

    // Add getters and setters as needed
    public StickHeroPlayer getPlayer() {
        return player;
    }

    public PlatformManager getPlatformManager() {
        return platformManager;
    }
}


//The GameState class implements the Serializable interface to allow for object serialization.
//It contains fields to store the relevant game state information (e.g., the player, platformManager).
//The save method serializes the current state and writes it to a file named "saved_game.ser."
//The load method reads the serialized data from the file and returns a new GameState object.
