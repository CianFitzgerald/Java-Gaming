package Assignment;

public class LimitedPlayer extends Player {

	// no argument constructor with default values
	// inherited from superclass
	public LimitedPlayer() {
	}

	// constructor with specified arguments
	public LimitedPlayer(String name, int points) {
		super(name, points);
	}

	// Method to add 3 points to limited player score
	@Override
	public void addPoints() {
		this.points += 3;
	}

	// Method to deduct 1 points from limited player score
	@Override
	public void deductPoints() {
		this.points -= 1;
	}

	// method to return name and points as a string
	// includes (Limited) after name
	@Override
	public String toString() {
		return name + "(Limited)" + " : " + points;
	}
}
