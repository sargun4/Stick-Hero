package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//properties for the cherry's radius and whether it's collected.
//There are getter and setter methods for the isCollected property.
//Additional methods are provided for checking if the player is near the cherry and for collecting the cherry.
//The draw method is updated to only draw the cherry if it's not collected, and we use GraphicsContext to draw it as a red oval
public class Cherry {
    private double x;
    private double y;
    private double radius;  // The radius of the cherry
    private boolean isCollected;

    public Cherry(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isCollected = false;
    }

    // Getter and setter methods for isCollected property
    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public boolean isPlayerNear(double playerX, double playerY, double playerRadius) {
        // Calculate the distance between the player and the cherry
        double distance = Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
        
        // If the distance is less than the sum of playerRadius and cherry's radius, they are near
        return distance < (playerRadius + radius);
    }


    public void collect(StickHeroPlayer player) {
        if (!isCollected && isPlayerNear(player.getX(), player.getY(), player.getPlayerDimensions())) {
            player.collectCherry();
            isCollected = true;
        }
    }


    public void draw(GraphicsContext gc) {
        if (!isCollected) {
            gc.setFill(Color.RED);
            gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        }
    }

}
