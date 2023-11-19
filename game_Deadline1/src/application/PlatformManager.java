package application;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//This PlatformManager class keeps track of a list of platforms, generates new platforms after successful attempts, and provides methods for updating and drawing the platforms. The platform generation includes random widths to add variety to the game. 
public class PlatformManager {
    private List<Platform> platforms;
    private Random random;

    public PlatformManager() {
        platforms = new ArrayList<>();
        random = new Random();
        initializeStartingPlatforms();
    }

    public void initializeStartingPlatforms() {
        
    }

    public void generateNextPlatform() {
         
    }

    public void update(double deltaTime) {
        
    }

    public void draw(GraphicsContext gc) {
        
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
//    public void reset() {
//        platforms.clear();  
//    }
}
