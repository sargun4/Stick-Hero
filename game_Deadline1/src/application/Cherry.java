package application;


public class Cherry {
    private double x;
    private double y;
    private double radius;   
    private boolean isCollected;
    private int count;

    public Cherry(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isCollected = false;
    }

   
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
         
    }
    public void buyCherry(int num_cherries_to_buy) {
		
	}
    public void decrementCherries() {
		
	}
    
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
