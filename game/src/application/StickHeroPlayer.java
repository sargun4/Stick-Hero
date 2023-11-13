package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

public class StickHeroPlayer {
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
//    isFlipping and flipStartTime variables to track the flipping state and the time when 
//    flipping starts. The update method checks for user input to initiate flipping and calculates 
//    the flip time when flipping is in progress. If the flip time falls within a certain range 
//    (e.g., between 0.2 and 1.0 seconds), it considers it a successful flip and calls the collectCherry method
    public void update(double deltaTime, PlatformManager platformManager) {
        if (isExtending) {
        	if (extensionStartTime == 0) {
                // Start timing when stick extension begins
                extensionStartTime = System.nanoTime() * 1e-9; // Convert nanoseconds to seconds
            }

            // If the stick is extending, update the stick length or handle stick animation
            stickLength = calculateStickLength(); // Update stick length
        } else {
            // Handle player's movement when not extending the stick
            // Check for collisions with platforms or other game objects
            checkCollision(platformManager);

            // Check for user input to initiate flipping
            if (isFlipping && flipStartTime == 0) {
                // Start timing when flipping begins
                flipStartTime = System.nanoTime() * 1e-9; // Convert nanoseconds to seconds
            }
            if (isFlipping && flipStartTime > 0) {
                double flipTime = System.nanoTime() * 1e-9 - flipStartTime;
                // Check if the flip time is within a certain range for successful flip
                if (flipTime >= 0.2 && flipTime <= 1.0) {
                    collectCherry(); // Collect cherry when successfully flipped
                }
                // Reset flipping variables for the next flip
                isFlipping = false;
                flipStartTime = 0;
            }
        }
    }

    // Method to handle user input for flipping
    public void flip() {
        isFlipping = true;
    }

    private void checkCollision(PlatformManager platformManager) {
        // Check collision with platforms and handle accordingly
        for (Platform platform : platformManager.getPlatforms()) {
            if (platform.isCollision(x, y, stickLength, getPlayerDimensions())) {
                // Successful landing on a platform
                double landingTime = System.nanoTime() * 1e-9; // Convert nanoseconds to seconds
                double extensionTime = landingTime - extensionStartTime;

                // Check if the extension time is within a certain range for successful landing
                if (extensionTime >= 0.5 && extensionTime <= 2.0) {
                    score += 100; // Award points for successful landing
                } else {
                    // Failed to time the stick extension correctly, end the game
                    // You may want to reset the game or show a game over screen
                    // Handle the game over logic as needed
                }
                // Reset timing variables for the next extension
                extensionStartTime = 0;
                return;
            }
        }

        // Failed to land on a platform, end the game or initiate reviving logic
        if (revivalsLeft > 0) {
            // Placeholder for reviving logic
            isExtending = true; // Extend the stick for reviving
        } else {
            // Game over logic
            // You may want to reset the game or show a game over screen
            // Handle the game over logic as needed
        }
    }

    public void extendStick() {
        if (!isExtending) {
            // Start extending the stick
            isExtending = true;
        }
    }

    public void releaseStick() {
        if (isExtending) {
            // Release the stick and calculate the length
            stickLength = calculateStickLength(); // Calculate the stick length
            isExtending = false;
        }
    }



    private double calculateStickLength() {
        // Implement your logic to calculate the stick length here
        // You may want to limit the maximum stick length based on game rules
        // For example, you can use time-based calculations
        // Replace the following line with your actual calculation
        return Math.min(100, stickLength + 1); // Increment stick length
    }

    public void collectCherry() {
        cherriesCollected++;
        int cherryScore = calculateCherryScore();
        score += cherryScore; // Award points for collecting cherries based on score calculation
    }

    private int calculateCherryScore() {
        // Implement your scoring logic based on cherries collected
        // Adjust the formula based on how you want to encourage higher scores
        // This is just a placeholder, you can make it more complex or dynamic
        return cherriesCollected * 100;
    }

    public boolean canRevive() {
        return revivalsLeft > 0;
    }

    public void revive() {
        if (canRevive()) {
            revivalsLeft--;
            cherriesCollected -= 10; // Deduct cherries for revival
            // Implement the revival logic
            isExtending = false; // Reset the extending flag after revival
        }
    }

    public void draw(GraphicsContext gc) {
        // Draw the player and stick
        gc.setFill(Color.BLUE); // Set player color (replace with your player color)
        gc.fillOval(x - 5, y - 5, 20, 40); // Draw player (replace with your player shape)

        // Draw the stick
        gc.setStroke(Color.BROWN); // Set stick color
        gc.setLineWidth(5); // Set stick width
        gc.strokeLine(x, y, x, y - stickLength); // Draw the stick
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
        // Assuming the player's character is not a circle but a human-like shape
        // Use dimensions that approximate the player's shape
        double playerWidth = 30.0;  // Replace with the actual width of the hitbox
        double playerHeight = 60.0;  // Replace with the actual height of the hitbox

        // Return the maximum dimension (radius) to represent the player's hitbox
        return Math.max(playerWidth, playerHeight) / 2;
    }
}

