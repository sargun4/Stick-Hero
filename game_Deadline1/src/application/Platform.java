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

    public double getWidth() {
    	return width;
    }

    public boolean isCollision(double playerX, double playerY, double playerWidth, double playerHeight) {
        
        return playerX < x + width &&
               playerX + playerWidth > x &&
               playerY < y + height &&
               playerY + playerHeight > y;
    }

    public void update(double deltaTime) {
         
    }
 
}

