package Assignment;

public class VIPPlayer extends Player {

	// no argument constructor with default values
	public VIPPlayer() {
		this("N/A", 10);
	}

	// constructor with specified arguments
	public VIPPlayer(String name, int points) {
		super(name, points);
	}

	// Method to add 10 points to VIP player score
	@Override
	public void addPoints() {
		this.points += 10;

	}

	// Method to deduct 1 points from VIP player score
	@Override
	public void deductPoints() {
		this.points -= 1;
	}

	// method to return name and points as a string
	// includes (VIP) after name
	@Override
	public String toString() {
		return name + "(VIP)" + " : " + points;
	}
}
