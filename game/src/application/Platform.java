package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform {
    private double x;
    private double y;
    private double width;
    private double height;

    public Platform(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN); // Set the platform color
        gc.fillRect(x, y, width, height); // Draw a filled rectangle representing the platform
    }

    public boolean isCollision(double playerX, double playerY, double playerWidth, double playerHeight) {
        // Check if the player collides with the platform
        return playerX < x + width &&
               playerX + playerWidth > x &&
               playerY < y + height &&
               playerY + playerHeight > y;
    }

    public void update(double deltaTime) {
        // You can add any update logic for the platform here
    }

    // Other methods for updating and handling platforms can be added here
}

