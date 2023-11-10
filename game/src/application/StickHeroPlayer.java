package application;
import javafx.scene.canvas.GraphicsContext;

public class StickHeroPlayer {
    private double x;
    private double y;
    private double stickLength;
    private boolean isExtending;
    private int cherriesCollected;
    private int revivalsLeft;
    private int score;

    public StickHeroPlayer(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setStickLength(0);
        this.isExtending = false;
        this.setCherriesCollected(0);
        this.revivalsLeft = 3; // or any desired number
        this.setScore(0);
    }

    public void update(double deltaTime) {
        // Update player's position and other game logic here
        if (isExtending) {
            // If the stick is extending, update the stick length or handle stick animation
            stickLength = calculateStickLength(); // Update stick length
        } else {
            // Handle player's movement when not extending the stick
            // Check for collisions with platforms or other game objects
        }
    }


//    this implementation, we check if the player is not already extending the stick, and if not, we set the isExtending flag to true to indicate that the stick is being extended. You should handle the timing of stick extension according to your game's rules.
    public void extendStick() {
        if (!isExtending) {
            // Start extending the stick
            isExtending = true;
        }
    }

//    The releaseStick() method is used to release the stick when the player releases the key or control.
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
        setCherriesCollected(getCherriesCollected() + 1);
        setScore(getScore() + 100); // Award points for collecting cherries
    }

    public boolean canRevive() {
        return revivalsLeft > 0;
    }

    public void revive() {
        if (canRevive()) {
            revivalsLeft--;
            setCherriesCollected(getCherriesCollected() - 10); // Deduct cherries for revival
            // Implement the revival logic
        }
    }

    public void draw(GraphicsContext gc) {
        // Draw the player and stick
        // Use gc.strokeLine() to draw the stick
        // Implement player animations
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

	public double getPlayerDimensions() {
	    // Assuming the player's character is not a circle but a human-like shape
	    // Use dimensions that approximate the player's shape
	    double playerWidth = 30.0;  // Replace with the actual width of the hitbox
	    double playerHeight = 60.0;  // Replace with the actual height of the hitbox

	    // Return the maximum dimension (radius) to represent the player's hitbox
	    return Math.max(playerWidth, playerHeight) / 2;
	}

	public int getCherriesCollected() {
		return cherriesCollected;
	}

	public void setCherriesCollected(int cherriesCollected) {
		this.cherriesCollected = cherriesCollected;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getStickLength() {
		return stickLength;
	}

	public void setStickLength(double stickLength) {
		this.stickLength = stickLength;
	}

}
