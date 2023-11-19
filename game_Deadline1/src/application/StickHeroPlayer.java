package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

public class StickHeroPlayer  {
    private double x;
    private double y;
    private double stickLength;
    private boolean isExtending;
    private int cherriesCollected;
    private int revivalsLeft;
    private int score;
    private double extensionStartTime; // Variable to store the time when stick extension starts
    private boolean isFlipping; // Variable to track if the player is flipping
    private double flipStartTime; // Variable to store the time when flipping starts


    public StickHeroPlayer(double x, double y) {
        this.x = x;
        this.y = y;
        this.stickLength = 0;
        this.isExtending = false;
        this.cherriesCollected = 0;
        this.revivalsLeft = 3; // or any desired number
        this.score = 0;
        this.extensionStartTime = 0;
        this.isFlipping = false;
        this.flipStartTime = 0;
    }
     public void update(double deltaTime, PlatformManager platformManager) {
        
    }
 
    public void flip() {
         
    }

    private void checkCollision(PlatformManager platformManager) {
        
         
    }

    public void extendStick() {
        
    }

    public void releaseStick() {
         
    }



    private double calculateStickLength() { 
        return Math.min(100, stickLength + 1); // Increment stick length
    }

    public void collectCherry() {
        
    }

    private int calculateCherryScore() {
         
        return cherriesCollected  ;
    }

    public boolean canRevive() {
        return revivalsLeft > 0;
    }

    public void revive() {
        if (canRevive()) {
            revivalsLeft--;
            cherriesCollected -= 10; // Deduct cherries for revival
             
            isExtending = false; // Reset the extending flag after revival
        }
    }

    public void draw(GraphicsContext gc) {
         
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getStickLength() {
        return stickLength;
    }

    public void setStickLength(double stickLength) {
        this.stickLength = stickLength;
    }

    public int getCherriesCollected() {
        return cherriesCollected;
    }

    public void setCherriesCollected(int cherriesCollected) {
        this.cherriesCollected = cherriesCollected;
    }

    public int getRevivalsLeft() {
        return revivalsLeft;
    }

    public void setRevivalsLeft(int revivalsLeft) {
        this.revivalsLeft = revivalsLeft;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPlayerDimensions() { 
        double playerWidth = 30.0;  // Replace with the actual width of the hitbox
        double playerHeight = 60.0;  // Replace with the actual height of the hitbox
 
        return Math.max(playerWidth, playerHeight) / 2;
    }
}

