# Java-Gaming
Java implementation of a simple games system with points and a leaderboard

In order to implement the project a total of 4 classes were used, these include:

Console – The main method is contained within this class which provides the user with a games console with various menus, games and a player leader board on termination.

Player – This is the superclass which stores information associated with each player who plays.

VIPPlayer – This is a subclass of the player class that inherits the majority of the superclass methods, except that it deals with points additions and deductions differently for VIP players.

LimitedPlayer – Similarly, this is a subclass of Player that deals with point additions and deductions differently for limited players.


# Player class
The player class creates objects that store information associated with each individual player. This information includes their name and their points total, these have default values “N/A” and 0 respectively. A separate constructor class allows a player to be created with a specific name and points total. Getter and setter methods were implemented for both name and points. Two methods in particular which are of importance are the addpoints and deductPoints methods, these add 5 points for every game won by the player and deduct 2 point for every game lost. A comparator interface is also implemented such that when the run is terminated, the array list of objects can be displayed in descending order based on points total using the Collections.sort method, this ensures that the leader board is ordered properly. This is also used to ensure that the leader board which persists between runs in a text file is kept in order. Finally, the toString method simply returns the name and points total of the player.

# VIPPLayer class
This subclass extends the Player superclass. All getter and setter methods are inherited from the superclass. The no argument constructor is changed such that VIP players begin with 10 points rather than 0. Three methods in particular are overridden within this subclass, for each game won the addPoints method will assign 10 points to the total points of a VIP player, and for each game lost the deductPoints method will only deduct 1 point. As well as this the toString method is overridden such that (VIP) is displayed after the users name on the leader board.

# LimitedPlayer class
This subclass also extends the Player superclass. Once again all getter and setter methods are inherited. addPoints only adds 3 points to a limited players total score and deductPoints deducts 1. The toString method is overridden such that (Limited) is displayed after the users name on the leader board.

# Console Class
The console class provides the menu, game and leader board functionality to the project. Through the use of two do-while loops and a number of switch statements a welcome menu and a games menu were created. These run until such time that the user decides to exit them using a specific switch case, in which case the while loop breaks . The welcome menu allows the player to create a new player or quit. If a new player is created the user will be prompted to enter a user name, this and all other user input was dealt with using the scanner class. After the user has entered their name the setName method is used to assign the inputted name to the user, they are randomly assigned a player status as either regular, VIP, or limited. This assignment was performed randomly for each player. Each instance of Player or any of its subclasses are stored in an array list, each player begins with a default zero points, except VIP players who begin with 10. The player is then met by the games menu where they can decide upon a number of games.

The games themselves were implemented in single methods and call upon the addPoints and deductPoints methods in order to add or deduct points according to the game outcome. These games are implemented such that a win corresponds to an addition of points to the players score and a loss results in a deduction, a tie results in no change. These games include Heads or tails, Rock Paper Scissors and Higher Lower. Therefore, each run of the programme from the welcome menu creates a new instance of the Player class or one of its subclasses and these users are then stored in an array list. Once the programme is terminated by the user this array list is called upon to provide the details regarding the users which have played. Using the comparator interface described in the Player class section, the array list of players is sorted in order of score total and appended to a text file before it is printed out onto the console for the user to see. This text file “Unsorted_Leaderboard” is set up such that it is appended to on each run with the list of users that have played. Using this file an ordered leader board is created that persists between runs. This is implemented in the method “leaderboard”, a new array list of the player class is created and a bufferedReader reads the Unsorted_Leaderboard file line by line splitting the string into names and points which are then used to create new instances which are added to the array list, then using the comparator interface once again the list is sorted according to the users points total. Then finally using a bufferedWriter this array list is written to a new text file “Sorted_Leaderboard”. This leader board updates for each run of the programme with any new players that are created.

<img width="1029" alt="Screenshot 2022-01-18 at 17 03 29" src="https://user-images.githubusercontent.com/70771077/149984011-103affa1-cd71-452b-8d6c-705cf03fcde9.png">
