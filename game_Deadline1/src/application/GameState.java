package application;

import java.io.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private StickHeroPlayer player;
    private PlatformManager platformManager;

    public void save(StickHeroPlayer player, PlatformManager platformManager) {
      
    }

    public GameState load() {
        GameState loadedState = null;
        return loadedState;
    }

    public StickHeroPlayer getPlayer() {
        return player;
    }

    public PlatformManager getPlatformManager() {
        return platformManager;
    }
}
 