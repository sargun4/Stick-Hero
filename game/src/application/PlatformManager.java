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

    private void initializeStartingPlatforms() {
        // Initialize the initial platforms when the game starts
        // You can customize the starting platforms as needed
        double initialPlatformWidth = 100;
        double initialPlatformHeight = 20;
        platforms.add(new Platform(100, 400, initialPlatformWidth, initialPlatformHeight));
        platforms.add(new Platform(300, 400, initialPlatformWidth, initialPlatformHeight));
    }

    public void generateNextPlatform() {
        // Generate a new platform after a successful attempt by the player
//        double lastPlatformX = platforms.get(platforms.size() - 1).getX();
//        double lastPlatformWidth = platforms.get(platforms.size() - 1).getWidth();
//        double newPlatformX = lastPlatformX + lastPlatformWidth + random.nextDouble() * 50 + 50; // Random width between 50 and 100
//        double newPlatformWidth = random.nextDouble() * 100 + 50; // Random width between 50 and 150
        double newPlatformHeight = 20; // You can customize the platform height

//        platforms.add(new Platform(newPlatformX, 400, newPlatformWidth, newPlatformHeight));
    }

    public void update(double deltaTime) {
        // Update all platforms
        for (Platform platform : platforms) {
            platform.update(deltaTime);
        }
    }

    public void draw(GraphicsContext gc) {
        // Draw all platforms
        for (Platform platform : platforms) {
            platform.draw(gc);
        }
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
    public void reset() {
        // Reset the platforms to their initial state
        platforms.clear(); // You may need to reinitialize the platforms as needed
    }
}
