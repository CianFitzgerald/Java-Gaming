package Assignment;

import java.util.Comparator;

public class Player {
	public String name;
	public int points;

	// no argument constructor for Player with default values
	public Player() {
		this("N/A", 0);
	}

	// constructor with specified arguments
	public Player(String name, int points) {
		this.name = name;
		this.points = points;
	}

	// getter method for points
	public int getPoints() {
		return points;
	}

	// setter method for points
	public void setPoints(int points) {
		this.points = points;
	}

	// getter method for name
	public String getName() {
		return name;
	}

	// setter method for name
	public void setName(String name) {
		this.name = name;
	}

	// Method to add 5 points to regular player score
	public void addPoints() {
		this.points += 5;
	}

	// Method to deduct 2 points to regular player score
	public void deductPoints() {
		this.points -= 2;
	}

	// comparator interface used to compare objects based on points
	// objects returned in descending order based on points
	public static Comparator<Player> pointsComparer = new Comparator<Player>() {

		public int compare(Player P1, Player P2) {
			int points1 = P1.getPoints();
			int points2 = P2.getPoints();
			return points2 - points1;

		}
	};

	// method to return name and points as a string
	@Override
	public String toString() {
		return name + " : " + points;
	}
}
