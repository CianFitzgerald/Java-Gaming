package Assignment;

import java.util.*;
import java.io.*;

public class Console {

	// set up variables for coin toss game
	enum Coin {
		Head, Tail
	};

	// Main method
	public static void main(String[] args) {

		// Create scanner class
		Scanner input = new Scanner(System.in);

		// boolean condition for do while loop initialized as false
		boolean menu1_exit = false;

		// array list created for storing player objects
		List<Player> list = new ArrayList<>();

		// do while loop for welcome menu
		do {

			// print statements with player choices
			System.out.println("Please choose an option: ");
			System.out.println("1. New Player ");
			System.out.println("2. Quit ");

			boolean validInput = false;
			// read user input

			// try block created such that the user will be brought back to welcome menu
			// if incorrect input is entered at any stage.
			while (!validInput) {
				try {
					int choice = input.nextInt();
					validInput = true;

					// switch statement provides menu functionality
					switch (choice) {
					// New player option
					case 1:

						// prompt user to enter name and read in input
						System.out.println("Please enter a name:");
						String user = input.next();

						// randomly generate a number between 0 and 2
						Random randomGenerator = new Random();
						int number = randomGenerator.nextInt(3);

						// if-else statements used to randomly allocate users to the different player
						// types
						if (number == 0) {

							// creating new player instance
							// using setName method with user input
							// adding player to the array list
							Player player = new Player();
							player.setName(user);
							list.add(player);
							System.out.println("Player Status: Regular");

							// calling menu_2 method, this menu allows the user to decide which game to play
							menu_2(input, player, user);
							continue;

						} else if (number == 1) {
							// repeat of above for VIPPlayer class
							Player player = new VIPPlayer();
							player.setName(user);
							list.add(player);
							System.out.println("Player Status: VIP");
							menu_2(input, player, user);
							continue;

						} else if (number == 2) {
							// repeat of above for Limited Player class
							Player player = new LimitedPlayer();
							player.setName(user);
							list.add(player);
							System.out.println("Player Status: Limited");
							menu_2(input, player, user);
							continue;

						}

						// Exit Game option
					case 2:
						// change boolean condition to true and break while loop
						menu1_exit = true;
						break;
					}

				}
				// catch block for when user input is incorrect at menu
				catch (InputMismatchException e) {
					System.err.println("You're going to have to start again! Please enter a digit when requested!!");
					input.next();
				}
			}

		} while (menu1_exit == false);

		// sort method of collections class is used to sort the players
		// in the array list by the comparator specified in player class (Points)
		Collections.sort(list, Player.pointsComparer);
		System.out.println("Player : Points");

		// file writer created with append condition set to true such that
		// same file is appended to on each run of the program
		try (FileWriter fw = new FileWriter("Unsorted_Leaderboard.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			// iterate through array list of players and write them to text file
			// also print the toString method to display current leader board in console
			for (Player player : list) {
				fw.write(player.toString() + "\n");
				System.out.print(player.toString() + "\n");
			}

			// catch block for IOExceptions
		} catch (IOException e) {
			System.err.println("An error has occured while writing to the leaderboard file.");
		}

		// calling leader board method to create sorted leader board that persists
		// between runs
		leaderboard();

		// closing scanner
		input.close();

	}

	// games menu method
	public static void menu_2(Scanner input, Player player, String user) {
		// boolean condition for do while loop initialized as false
		boolean menu2_exit = false;
		do {
			// Display options to user
			System.out.println("Hello " + user + ". Please choose a game, or -1 to quit:");
			System.out.println("1. Coin Flip ");
			System.out.println("2. Higher/lower");
			System.out.println("3. Rock, Paper, Scissors");
			// read input from user
			int choice_two = input.nextInt();
			input.nextLine();

			// switch statement provides menu functionality
			switch (choice_two) {

			// heads or tails option
			case 1: {
				// calls heads or tails method
				HeadsTails(player, input);
				continue;
			}

			// Higher Lower option
			case 2: {
				// calls higher lower method
				HigherLower(player, input);
				continue;
			}

			// Higher Lower option
			case 3: {
				// calls Rock Paper scissors method
				RockPaperScissors(player, input);
				continue;
			}

			// quit option
			case -1: {

				// change boolean condition to true to break while loop
				menu2_exit = true;
			}
			}
		} while (menu2_exit == false);

	}

	// Heads or tails method
	public static void HeadsTails(Player player, Scanner input) {

		// guess initialized as heads or tails
		Coin guess;
		System.out.println("Welcome to Flip the Coin!");
		while (true) {
			// prompt user to input guess and read input
			System.out.print("Heads or Tails? (h = heads, t = tails, q = quit):");
			String user_guess = input.nextLine();

			// assign Coin value to guess dependent on what user enters
			if (user_guess.equals("q")) {
				// Print a final message for the user
				System.out.println("Thanks for playing Flip the Coin!");
				break;
			} else if (user_guess.equals("h")) {
				guess = Coin.Head;

			} else if (user_guess.equals("t")) {
				guess = Coin.Tail;
			} else {
				System.out.println("Try again!");
				continue;
			}

			// toss initialized as heads or tails
			// Math.random used such that 50% chance of heads or tails
			// assign Coin value to toss dependent on outcome
			Coin toss;
			if (Math.random() < 0.5) {
				toss = Coin.Head;
			} else {
				toss = Coin.Tail;
			}

			// if user guess equals toss then notify user and add points to players score
			if (guess == toss) {
				System.out.println("It's " + toss + 's');
				System.out.println("You won, Congratulations! ");
				player.addPoints();

				// if user guess not equal to toss then notify user and deduct points from
				// players score
			} else {
				System.out.println("It's " + toss + 's');
				System.out.println("You lost, hard luck!");
				player.deductPoints();
			}
		}
	}

	// Higher Lower Method
	public static void HigherLower(Player player, Scanner input) {
		System.out.println("Welcome to Higher/Lower!");
		// generate random number between 0 and 100
		Random random = new Random();
		int number = random.nextInt(100);

		// initialize guess as number not within 0-100 range
		int guess = -100;

		// loop while guess is not equal to randomly generated number
		while (guess != number) {
			// prompt user to enter their guess
			System.out.print("Enter your guess (any number between 0-100, -1 to quit): ");
			guess = input.nextInt();

			// notify user if they're too high or low or correct
			// add or deduct points accordingly
			// break loop if correct
			if (guess == -1) {
				System.out.println("Thanks for playing Higher/Lower!");
				break;
			} else if (guess > number) {
				System.out.println("Too high, try again!");
				player.deductPoints();
			} else if (guess < number) {
				System.out.println("Too low, try again!");
				player.deductPoints();

			} else {
				System.out.println("You got it! the number was " + number);
				player.addPoints();
				// Print a final message for the user
				System.out.println("Thanks for playing Higher/Lower!");
				break;
			}
		}
	}

	public static void RockPaperScissors(Player player, Scanner input) {

		// welcome message
		System.out.println("Welcome to Rock, Paper, Scissors!");
		while (true) {

			// prompt user to make move and read input
			System.out.print("Make your move (r = rock, p = paper, s = scissors, q = quit ");
			String user_Move = input.nextLine();

			// break loop if user enters q
			if (user_Move.equals("q")) {
				break;
			}

			// prompt user to try again if they enter any letter which is not r s or p
			else if (!user_Move.equals("r") && !user_Move.equals("s") && !user_Move.equals("p")) {

				System.out.println("Try again!");

			} else {

				// randomly generate a number between 0 and 2
				Random randomGenerator = new Random();
				int random = randomGenerator.nextInt(3);

				// Initialize the string for the opponents move
				String computer_move = " ";

				// assign computers move dependent on random outcome
				if (random == 0) {
					computer_move = "r";
				} else if (random == 1) {
					computer_move = "p";
				} else {
					computer_move = "s";
				}

				// display the computers move
				System.out.println("Computers move: " + computer_move);

				// notify user of the game outcome
				// add or deduct points accordingly

				if (user_Move.equals(computer_move)) {
					System.out.println("It's a tie!");
				} else if ((user_Move.equals("s") && computer_move.equals("p"))
						|| (user_Move.equals("r") && computer_move.equals("s"))
						|| (user_Move.equals("p") && computer_move.equals("r"))) {
					System.out.println("You won!");
					player.addPoints();
				} else {
					System.out.println("You lost!");
					player.deductPoints();
				}

			}

		}

		// Print a final message for the user
		System.out.println("Thanks for playing Rock, Paper, Scissors!");

	}

	// Method for ordered leader board which persists between runs
	// This uses the unsorted leader board from the main method in order to
	// create a new sorted leader board
	public static void leaderboard() {
		try {

			// create array list to store players on leader board
			List<Player> Leaderboard = new ArrayList<>();

			// create bufferedReader to read the unsorted leader board file from above
			String currentLine;
			BufferedReader reader = new BufferedReader(new FileReader("Unsorted_Leaderboard.txt"));

			// read the file line by line
			while ((currentLine = reader.readLine()) != null) {
				// split each line into name and points
				String[] PlayerDetail = currentLine.split(" : ");

				// creating name and points variables
				String name = PlayerDetail[0];
				int points = Integer.parseInt(PlayerDetail[1]);

				// create an instance with the name and points from each line
				// and add them to the array list
				Leaderboard.add(new Player(name, points));

			}

			// comparator used to sort the array list by points
			Collections.sort(Leaderboard, Player.pointsComparer);

			// bufferedWriter created to write the contents of the array list to a text file
			BufferedWriter writer = new BufferedWriter(new FileWriter("Sorted_LeaderBoard.txt"));

			// iterate over the array list and write the name and points of each player to
			// the file
			writer.write("Player : Points" + "\n");
			for (Player leader : Leaderboard) {

				writer.write(leader.name);
				writer.write(" : " + leader.points);
				writer.newLine();
			}

			// close reader and writer
			reader.close();
			writer.close();

			// catch block for IOExceptions
		} catch (IOException e) {
			System.err.println("An error has occured while writing to the leaderboard file.");
		}
	}

}
