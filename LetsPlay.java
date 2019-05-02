//--------------------------------------------------------
//Assignment 4
//Written by: David Rady 40098177
//For COMP 248 Section R - Fall 2018
//--------------------------------------------------------

//The purpose of this assignment is to familiarize and practice with the use of classes and types and how to use them in a main file
//This assignment requires 3 classes to be defined prior to writing the driver. The driver uses these classes in the program to run the game
//The game also requires the use of multiple loops of every kind and multiple if-else statements, almost every topic covered in the course was used in this assignment
//This game asks the user for size of a garden and number of players and sets up a game so that every player has a garden of size x size.
//Each player rolls a dice to see who starts and then once started, each player rolls the dice and depending on the outcome they will plant something in their garden (or a rabbit will eat a space in their garden
//The first player to fill up their garden wins


import java.util.Scanner;

public class LetsPlay {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		//multiple comments to print a welcome message and the rules of the game
		System.out.println("\t\t\t\t-------****-------****-------****-------****-----****-----");
		System.out.println("\t\t\t\t\t  Welcome to Crazy Nancy's Garden Game!");
		System.out.println("\t\t\t\t-------****-------****-------****-------****-----****-----");
		System.out.println("\n\nTo win this game you need some luck with the dice and a bit of strategy. \nYour garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\nYou roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.");
		System.out.println("\nRolls and their outcome:\n---------------------\n\t3: plant a tree (2x2) and a flower (1x1)\n\t6: plant 2 flowers (2 times 1x1)\n\t12: plant 2 trees (2 times 2x2)\n\t5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)\n\tAny other EVEN rolls: plant a tree (2x2) \n\tAny other ODD rolls: plant a flower (1x1)");
		System.out.println("\nMinimum number of players: 2.\nMinimum garden size: 3x3.\nYou can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\nand I will check to make sure all 4 locations are free.\nOkay .. Let's start the game! May the best gardener win!!!");
		System.out.println("\n\n\nThe default garden size is a 3x3 square. You can use this default board size or change the size");
		System.out.println("Enter 0 to use the default garden size or -1 to enter your own size: ");
		
		//prompt user. Initializes an integer for the user to choose the default size (3x3) or to choose their own size
		int sizeOption = keyboard.nextInt();
		
		//variable used for size of the garden
		int size=0;
		
		//array of type Player called players
		Player [] players;
		
		//while-loop to make sure the user chooses one of the valid options
		while (sizeOption != -1 && sizeOption !=0) {
			
			System.out.println("Sorry but " + sizeOption + " is not a legal choice. Enter your choice:");
			
			//prompt user again while not invalid input
			sizeOption = keyboard.nextInt();
		}
		
		
		//if the user chooses to input their own size of the garden
		if (sizeOption == -1) {
			
			//Prompt user. Asks for size of garden as long as it's at least 3
			System.out.println("What size board would you like? (minimum size 3) ");
			
			size = keyboard.nextInt();	
			
			//makes sure the user inputs a size of at least 3
			while (size < 3) {
				
				System.out.println("Sorry but the size of a garden must be at least 3. Please enter the size of the garden you would like (minimum 3)");
				
				size = keyboard.nextInt();
			}
		}
		
		//if user chooses 0 then size of garden is set to 3
		else if (sizeOption == 0) {
			size = 3;
		}
		
		//Prompt user. Asks for number of players
		System.out.println("How many gardeners will there be (minimum 2 required to play, max allowed 10)");
		
		//variable for number of players
		int numberOfPlayers = keyboard.nextInt();
		
		//keep asking for a valid number of players until valid option is inputed (at least 2 and max 10)
		while (numberOfPlayers < 2 || numberOfPlayers > 10) {
			System.out.println("Sorry but " + numberOfPlayers + " is not a legal choice. Enter number of Gardeners:");
			numberOfPlayers = keyboard.nextInt();
		}
		
		System.out.println("Great, " + numberOfPlayers + " players it will be!\n");
		
		//creates the array of players with a length equal to number of players since each player is an element
		players = new Player[numberOfPlayers];
		
		//variable String used for the user to enter a name for each player
		String name;
		
		//asks for the name of every player using this for-loop
		for (int i = 0; i<numberOfPlayers; i++) {
			//prompt user
			System.out.println("--> Name of player " + (i+1) + " (no spaces allowed): ");
			name = keyboard.next();
			//index i in array players becomes a new Player with a name and size
			players[i] = new Player(name, size);
		}
		
		System.out.println("Let's see who goes first ...");
		
		//boolean value used to repeat the loop of rolling the dice until a starter is chosen
		boolean repeat = false;
		
		//do-while loop to roll dice to see who starts. Will keep running until the value of the previous boolean is changed
		rerollDiceLoop:	do {
			
			//creates a new array of dice which will store the values of dice for each player
			int [] dice = new int [players.length];
		
			//creates new Dice object
			Dice startDice = new Dice();
		
			//stores a rolled dice value in the array called dice and prints each persons name with the value of their dice rolled
			for (int i = 0; i<numberOfPlayers; i++) {
				dice[i] = startDice.rollDice();	
				System.out.println(players[i].getName() + " rolled a(n) " + dice[i]);
			}
			
			//this following for-loop is used to find which player had the highest roll
			//string to store the players name with the highest roll
			String playerName = players[0].getName();
			
			//integer to store the max dice value
			int max = dice[0];
			
			//integer to store the index of the highest roll
			int indexHighestRoll = 0;
			
			//iterates through the loop and makes the variable max equal to the highest value in the loop, makes the playerName equal to the player with this highest roll, makes the indexHighestRoll equal to the index of the number with the highest roll
			for(int i = 1; i < dice.length;i++) {
				if(dice[i] > max) {
					max = dice[i];
					playerName = players[i].getName();
					indexHighestRoll = i;
				}
			
			}
		
			//this double for-loop is used check if any players rolled the same value on the dice. If so then the entire rolling process restarts
			for(int i = 0; i < dice.length; i++){
				//variable to check if any dice have the same dice values
				int sameDiceVal = dice[i];
				for(int j = i + 1; j < dice.length; j++){
					//if no dice have same values then the loop for rerolling the dice comes to an end
					if (sameDiceVal != dice[j]) {
						repeat = true;
					}
					//if any players rolled the same dice values then the reroll process keeps going until no dice have the same values
					else if (sameDiceVal == dice[j]) {
						//keeps the loop going
						repeat = false;
						System.out.println("We will start over as " + sameDiceVal + " was rolled multiple times\n");
						//takes it back to the top of the rerollDiceLoop
						continue rerollDiceLoop;
					}
	            
				}
			
			}
	
			//the following double for-loop is used to change the array of players to begin with the player with the highest roll and then every player 
			Player change;
        
			for (int i = 0; i < indexHighestRoll; i++) {
				change = players[0];
				for (int j = 0; j < players.length-1; j++) {
					players[j] = players[j+1];
				}
            players[players.length - 1] = change;
        }
        
		System.out.println(playerName + " goes first.");
		

		//condition of the do-while loop to roll the dice
		} while (repeat == false);
		

		
		System.out.println("\n\nTime to play!!!!!\n------------------");
		
		//boolean value used to keep running the game until its value is changed by a garden becoming full
		boolean gameRepeat = false;
		//variable to count number of rounds players
		int numberRoundsPlayed = 0;
		
		//do-while loop to keep running the game until a garden is full
		gameLoop: do {
			
		//creates new Dice object
		Dice gameDice = new Dice();
		
		//starting with the player with the highest roll (which is now at index 0 of array players) it rolls a dice for each player
		for (int i = 0; i<players.length; i++) {
			int valDice = gameDice.rollDice();
			System.out.println("\n\n" + players[i].getName() + " you rolled a " + valDice + " " + gameDice.toString());
	
			//if player rolls a 3, plant a tree and a flower
			if (valDice == 3) {
				
				System.out.println("You must plant a tree (2x2) and a flower (1x1)\n");
				
				//shows the players garden
				System.out.println(players[i].showGarden());
				
				//as long as there is room to plant a tree it will run the following code
				if (players[i].howManyTreesPossible() > 0) {
				
					//shows how many places the player can plant a tree
					System.out.println("\nLets start with the tree. You have " + players[i].howManyTreesPossible() + " places to do this.");
					
					//prompt user for coordinates
					System.out.print("Enter coordinates as row column:");
					
					int r = keyboard.nextInt();
					
					int c = keyboard.nextInt();
	
					//loop that will keep looping until user enters valid coordinates for the tree to be planted
					boolean invalidTreeLocation = true;
						
					while (invalidTreeLocation == true) {
							
						//conditions that if are true then the coordinates are not valid
						if (r >= size || c >= size || (r+1) >= size || (c+1) >= size) {
							
						System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
						
						r = keyboard.nextInt();
						
						c = keyboard.nextInt();
						
						} 
						
						//conditions that must be met for the location of the tree planting to be valid which will exit the loop that asks for valid coordinates
						else if (r < size && c < size && r+1 < size && c+1 < size)
							invalidTreeLocation = false;
					}
					
					
						//if (r,c), (r+1,c) (r,c+1) and/or (r+1,c+1) are taken already then a loop occurs to make sure the player inputs coordinates for a free space
						if (players[i].whatIsPlanted(r, c) != '-' || players[i].whatIsPlanted(r+1, c) != '-' || players[i].whatIsPlanted(r, c+1) != '-' || players[i].whatIsPlanted(r+1, c+1) != '-') {
							
							boolean spotTaken = true;
							
							while (spotTaken == true) {
								
							System.out.println("** Sorry that location is already taken up.");
							
							//prompt user
							System.out.println("Please enter a new set of coordinates:");
							
							r = keyboard.nextInt();
							
							c = keyboard.nextInt();
							
							//conditions that must be for a tree to be able to be planted. Makes sure the location of (r,c), (r+1,c) (r,c+1), (r+1,c+1) are free to plant a tree. Breaks the loop that asks for valid coordinates
							if (players[i].whatIsPlanted(r, c) == '-' && players[i].whatIsPlanted(r+1, c) == '-' && players[i].whatIsPlanted(r, c+1) == '-' && players[i].whatIsPlanted(r+1, c+1) == '-')
							spotTaken = false;
							
							} 
							
						}
						
						//plants the tree in the garden in the chosen location
						players[i].plantTreeInGarden(r, c);
					}
				
				//if no room for a tree to planted, skips their turn
				else {
					System.out.println("\nSorry no room to plant a tree! You miss a turn.\n");
				}
				
				
				
				System.out.println("You still have a flower (1x1) flower to plant.\n");
				
				//shows players garden
				System.out.println(players[i].showGarden());
				
				//lets user know how many places they have to plant the flower
				System.out.println("\nYou now have " + players[i].howManyFlowersPossible() + " places to do this.");
				
				//prompt user for coordinates of the flower planting
				System.out.print("Enter coordinates as row column:");
				
				int r1 = keyboard.nextInt();
				
				int c1 = keyboard.nextInt();
				
				//boolean value to run a loop to make sure the flower location is possible for planting
				boolean invalidFlowerLocation = true;
				
				while (invalidFlowerLocation == true) {
						
					//conditions that make coordinates invalid
					if (r1 >= size || c1 >= size) {
						
					//prompt user for valid coordinates
					System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
					
					r1 = keyboard.nextInt();
					
					c1 = keyboard.nextInt();
					
					} 
					
					//conditions that are valid locations for a flower to be planted. Breaks the loop that asks for valid coordinates
					else if (r1 < size && c1 < size)
						invalidFlowerLocation = false;
				}
				
				//if location is already taken by another tree or flower then it is invalid
				if (players[i].whatIsPlanted(r1, c1) != '-') {
					boolean spotTaken = true;
					//runs a loop until user inputs a location that is valid
					while (spotTaken == true) {
					System.out.println("** Sorry that location is already taken up by a " + players[i].whatIsPlanted(r1,c1));
					//prompt user for valid coordinates
					System.out.println("Please enter a new set of coordinates:");
					r1 = keyboard.nextInt();
					
					c1 = keyboard.nextInt();
					
					//exits the loop that asks user for a location that is available
					if (players[i].whatIsPlanted(r1, c1) == '-') 
						spotTaken = false;
					
					
					}
				}
				//plants the flower in the garden
				players[i].plantFlowerInGarden(r1, c1);
				
				
				//this for-loop iterates through every player and if any players garden is full, the loop to keep running the game will stop
				for (int j=0; j<players.length; j++) {
					
					if (players[j].isGardenFull() == true) {
						//changes the value of the boolean which repeats the game so that the game can now stop when a winner is found
						gameRepeat = true;
						continue gameLoop;
					}
				}
			}
			
			
		
			//if dice rolls a 6. plant 2 flowers
			else if (valDice == 6) {

				System.out.println("You must plant 2 flowers (1x1)\n");
				
				//shows players garden
				System.out.println(players[i].showGarden());
				
				//lets user know how many places they have to plant the flower
				System.out.println("\nFirst flower. You have " + players[i].howManyFlowersPossible() + " places to do this.");
				
				//prompt user for coordinates of the flower planting
				System.out.print("Enter coordinates as row column:");
				
				int r = keyboard.nextInt();
				
				int c = keyboard.nextInt();
				
				
				//boolean value to run a loop to make sure the flower location is possible for planting
				boolean invalidFlowerLocation = true;
				
				while (invalidFlowerLocation == true) {
						
					//conditions that make coordinates invalid
					if (r >= size || c >= size) {
						
					//prompt user for valid coordinates
					System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
					
					r = keyboard.nextInt();
					
					c = keyboard.nextInt();
					
					} 
					
					//conditions that make the location valid. Exits the loop that asks for a valid input (within bounds)
					else if (r < size && c < size)
						invalidFlowerLocation = false;
				}
				
				//if location is already taken by another tree or flower then it is invalid
				if (players[i].whatIsPlanted(r, c) != '-') {
					boolean spotTaken = true;
					//runs a loop until user inputs a location that is valid
					while (spotTaken == true) {
					System.out.println("** Sorry that location is already taken up by a " + players[i].whatIsPlanted(r,c));
					//prompt user for valid coordinates
					System.out.println("Please enter a new set of coordinates:");
					r = keyboard.nextInt();
					
					c = keyboard.nextInt();
					
					//exits the loop that asks user for a location that is available
					if (players[i].whatIsPlanted(r, c) == '-') 
						spotTaken = false;
					
					
					}
				}
				//plants flower in garden
				players[i].plantFlowerInGarden(r, c);
				
				
				System.out.println("You still have a flower (1x1) flower to plant.\n");
				
				//shows the players garden
				System.out.println(players[i].showGarden());
				
				//shows number possible locations for flower to be planted
				System.out.println("\nYou now have " + players[i].howManyFlowersPossible() + " places to do this.");
				
				//prompt user for coordinates
				System.out.print("Enter coordinates as row column:");
				
				int r1 = keyboard.nextInt();
				
				int c1 = keyboard.nextInt();
				
				//boolean value to run a loop to make sure the flower location is possible for planting
				boolean invalidFlowerLocation1 = true;
				
				while (invalidFlowerLocation1 == true) {
						
					//conditions that make coordinates invalid
					if (r1 >= size || c1 >= size) {
						
					//prompt user for proper coordinates
					System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
					
					r1 = keyboard.nextInt();
					
					c1 = keyboard.nextInt();
					
					} 
					
					//exits the loop that asks for coordintates
					else if (r1 < size && c1 < size)
						invalidFlowerLocation1 = false;
				}
				
				//Already explained in the previous dice rolls. Checks that the location inputed by user is not taken already
				if (players[i].whatIsPlanted(r1, c1) != '-') {
					boolean spotTaken = true;
					while (spotTaken == true) {
					System.out.println("** Sorry that location is already taken up by a " + players[i].whatIsPlanted(r1,c1));
					//prompt user
					System.out.println("Please enter a new set of coordinates:");
					r1 = keyboard.nextInt();
					
					c1 = keyboard.nextInt();
					
					if (players[i].whatIsPlanted(r1, c1) == '-') 
						spotTaken = false;
					
					
					}
				}
				//plants flower in garden
				players[i].plantFlowerInGarden(r1, c1);	
				
				
				//this for-loop iterates through every player and if any players garden is full, the loop to keep running the game will stop
				for (int j=0; j<players.length; j++) {
					
					if (players[j].isGardenFull() == true) {
						//changes the value of the boolean which repeats the game so that the game can now stop when a winner is found
						gameRepeat = true;
						continue gameLoop;
					}
				}
		}
		
			
			
			//if dice rolls a 12. plant 2 trees
			//this code within this segment has already been seen previously in this assignment since there is much repetition at this point, comments are a bit more vague on stuff that is already explained
			else if (valDice == 12) {
				
				System.out.println("You must plant 2 trees (2x2)\n");
				
				//shows players garden
				System.out.println(players[i].showGarden());
				
				//makes sure there is at least one possible location to plant a tree
				if (players[i].howManyTreesPossible() > 0) {
				
					System.out.println("\nFirst tree. You have " + players[i].howManyTreesPossible() + " places to do this.");
					
					//prompt user for coordinates
					System.out.print("Enter coordinates as row column:");
					
					int r = keyboard.nextInt();
					
					int c = keyboard.nextInt();
	
					
					boolean invalidTreeLocation = true;
					
					//loop until valid coordinates are inputed
					while (invalidTreeLocation == true) {
							
						if (r >= size || c >= size || (r+1) >= size || (c+1) >= size) {
							
						System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
						
						r = keyboard.nextInt();
						
						c = keyboard.nextInt();
						
						} 
						
						//exit the loop
						else if (r < size && c < size && r+1 < size && c+1 < size)
							invalidTreeLocation = false;
					}
					
					
					//makes sure the coordinate location is free to plant a tree
					if (players[i].whatIsPlanted(r, c) != '-' || players[i].whatIsPlanted(r+1, c) != '-' || players[i].whatIsPlanted(r, c+1) != '-' || players[i].whatIsPlanted(r+1, c+1) != '-') {
							
							boolean spotTaken = true;
							
							//loop until an available location is inputed
							while (spotTaken == true) {
								
							System.out.println("** Sorry that location is already taken up.");
							
							System.out.println("Please enter a new set of coordinates:");
							
							r = keyboard.nextInt();
							
							c = keyboard.nextInt();
							
							//exits the loop because the location is valid if these conditions are met
							if (players[i].whatIsPlanted(r, c) == '-' && players[i].whatIsPlanted(r+1, c) == '-' && players[i].whatIsPlanted(r, c+1) == '-' && players[i].whatIsPlanted(r+1, c+1) == '-')
								spotTaken = false;
							
							} 
							
						}
						
						//plants the tree in the garden
						players[i].plantTreeInGarden(r, c);
					}
				
				//if no room for a tree then it skips the turn
				else {
					System.out.println("\nSorry no room to plant a tree! You miss a turn.\n");
					continue;
				}
				
				
				//if room for another tree then another tree will be planted
				if (players[i].howManyTreesPossible() > 0) {
					
					//shows players garden
					System.out.println(players[i].showGarden());
					
					//says hows many locations available to plant a tree
					System.out.println("\nSecond tree. You have " + players[i].howManyTreesPossible() + " places to do this.");
					
					//prompt user 
					System.out.print("Enter coordinates as row column:");
					
					int r = keyboard.nextInt();
					
					int c = keyboard.nextInt();
	
					
					boolean invalidTreeLocation = true;
						
					//loop to make sure location inputed is valid
					while (invalidTreeLocation == true) {
							
						if (r >= size || c >= size || (r+1) >= size || (c+1) >= size) {
							
						//prompt user
						System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
						
						r = keyboard.nextInt();
						
						c = keyboard.nextInt();
						
						} 
						
						//exits the loop
						else if (r < size && c < size && r+1 < size && c+1 < size)
							invalidTreeLocation = false;
					}
					
					
					//makes sure the locations the tree will take up are all empty locations
					if (players[i].whatIsPlanted(r, c) != '-' || players[i].whatIsPlanted(r+1, c) != '-' || players[i].whatIsPlanted(r, c+1) != '-' || players[i].whatIsPlanted(r+1, c+1) != '-') {
							
							boolean spotTaken = true;
							
							//loop to make sure the location are empty
							while (spotTaken == true) {
								
							System.out.println("** Sorry that location is already taken up.");
							
							//prompt user
							System.out.println("Please enter a new set of coordinates:");
							
							r = keyboard.nextInt();
							
							c = keyboard.nextInt();
							
							//exits the loop if needed locations are available
							if (players[i].whatIsPlanted(r, c) == '-' && players[i].whatIsPlanted(r+1, c) == '-' && players[i].whatIsPlanted(r, c+1) == '-' && players[i].whatIsPlanted(r+1, c+1) == '-')
							spotTaken = false;
							
							} 
							
						}
						
						//plants the tree in the garden
						players[i].plantTreeInGarden(r, c);
					}
				
				//if no room to plant a tree then skips the turn
				else {
					System.out.println("\nSorry no room to plant a tree! You miss a turn.\n");
					continue;
				}
				
				//this for-loop iterates through every player and if any players garden is full, the loop to keep running the game will stop
				for (int j=0; j<players.length; j++) {
					
					if (players[j].isGardenFull() == true) {
						//changes the value of the boolean which repeats the game so that the game can now stop when a winner is found
						gameRepeat = true;
						continue gameLoop;
					}
				}
				
			}
			

			//if dice rolls 5 or 10, rabbit eats a random location in the garden
			else if (valDice == 5 || valDice == 10) {
				
				//makes sure there is at least one item planted in the garden for the rabbit to eat. otherwise skips the turn
				if (players[i].howManyFlowersPossible() != size*size) {
					//show players garden
					System.out.println(players[i].showGarden());
					
					//random coordinates are chosen for the rabbit to eat
					int r = (int)(Math.random()*size);
					int c = (int)(Math.random()*size);
					
					//if the location has nothing planted there then a loop will run until the location has something planted in it
					if (players[i].whatIsPlanted(r, c) == '-') {
						
						boolean spotTaken = true;
						
						//loop to make sure the rabbit picks a location where something is planted
						while (spotTaken == true) {
						
						r = (int)(Math.random()*size);
						
						c = (int)(Math.random()*size);
						
						//exits loop if something is planted in the given location
						if (players[i].whatIsPlanted(r, c) != '-')
						
							spotTaken = false;
						
						} 
						
					}
					//calls the method eatHere() which will eat make the character at that location back to '-'
					players[i].eatHere(r, c);
					
					//lets user know which location was eaten by rabbit
					System.out.println("\nThe rabbit ate whatever was planted in location (" + r + "," + c + ")\n");
					
					//shows garden again
					System.out.println(players[i].showGarden());
				}
				
				//if nothing planted in garden then skips turn
				else System.out.println("The rabbit has nothing to eat yet, you skip a turn");
				
				
			}
			
			//if dice rolls 2, 4 or 8. Plant a tree
			else if (valDice == 2 || valDice == 4 || valDice == 8) {
				
				System.out.println("You must plant a tree (2x2)\n");
				
				//shows players garden
				System.out.println(players[i].showGarden());
				
				//makes sure there is room to plant a treee
				if (players[i].howManyTreesPossible() > 0) {
				
					System.out.println("\nYou have " + players[i].howManyTreesPossible() + " places to do this.");
					
					//prompt user
					System.out.print("Enter coordinates as row column:");
					
					int r = keyboard.nextInt();
					
					int c = keyboard.nextInt();
	
					
					boolean invalidTreeLocation = true;
						
					//loop that makes sure user enters valid coordinates
					while (invalidTreeLocation == true) {
							
						if (r >= size || c >= size || (r+1) >= size || (c+1) >= size) {
							
						//prompt user
						System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
						
						r = keyboard.nextInt();
						
						c = keyboard.nextInt();
						
						} 
						
						//exits the loop
						else if (r < size && c < size && r+1 < size && c+1 < size)
							invalidTreeLocation = false;
					}
					
					
					//makes sure locations for that tree to be planted are available
					if (players[i].whatIsPlanted(r, c) != '-' || players[i].whatIsPlanted(r+1, c) != '-' || players[i].whatIsPlanted(r, c+1) != '-' || players[i].whatIsPlanted(r+1, c+1) != '-') {
							
							boolean spotTaken = true;
							//loop that makes sure the needed locations are available for the tree to be planted
							while (spotTaken == true) {
								
							System.out.println("** Sorry that location is already taken up.");
							
							//prompt user
							System.out.println("Please enter a new set of coordinates:");
							
							r = keyboard.nextInt();
							
							c = keyboard.nextInt();
							
							//exits the loop
							if (players[i].whatIsPlanted(r, c) == '-' && players[i].whatIsPlanted(r+1, c) == '-' && players[i].whatIsPlanted(r, c+1) == '-' && players[i].whatIsPlanted(r+1, c+1) == '-')
							spotTaken = false;
							
							} 
							
						}
						
						//plants the tree in the garden
						players[i].plantTreeInGarden(r, c);
					}
				
				//if no room to plant a tree then the turn is skipped
				else {
					System.out.println("\nSorry no room to plant a tree! You miss a turn.\n");
				}
				
				//this for-loop iterates through every player and if any players garden is full, the loop to keep running the game will stop
				for (int j=0; j<players.length; j++) {
					
					if (players[j].isGardenFull() == true) {
						//changes the value of the boolean which repeats the game so that the game can now stop when a winner is found
						gameRepeat = true;
						continue gameLoop;
					}
				}
			}
			
			//if dice rolls 7, 9 or 11. Plant a flower
			else if (valDice == 7 || valDice == 9 || valDice == 11) {
				
				System.out.println("You must plant a flower (1x1)\n");
				
				//shows the players garden
				System.out.println(players[i].showGarden());
				
				//says how many locations are available for the flower to be planted
				System.out.println("\nYou have " + players[i].howManyFlowersPossible() + " places to do this.");
				
				//prompt user
				System.out.print("Enter coordinates as row column:");
				
				int r = keyboard.nextInt();
				
				int c = keyboard.nextInt();
				
				
				//boolean value to run a loop to make sure the flower location is possible for planting
				boolean invalidFlowerLocation = true;
				
				//loop making sure coordinates are valid
				while (invalidFlowerLocation == true) {
						
					if (r >= size || c >= size) {
						
					//prompt user
					System.out.println("** Sorry either the row or column is not in the range of 0 to " + size + "\nor your tree will be off the grid. Try again");
					
					r = keyboard.nextInt();
					
					c = keyboard.nextInt();
					
					} 
					
					//exits the loop
					else if (r < size && c < size)
						invalidFlowerLocation = false;
				}
				
				//makes sure the given location is empty
				if (players[i].whatIsPlanted(r, c) != '-') {
					boolean spotTaken = true;
					//loop making sure location is empty
					while (spotTaken == true) {
					System.out.println("** Sorry that location is already taken up by a " + players[i].whatIsPlanted(r,c));
					//prompt user
					System.out.println("Please enter a new set of coordinates:");
					r = keyboard.nextInt();
					
					c = keyboard.nextInt();
					
					//exits the loop
					if (players[i].whatIsPlanted(r, c) == '-') 
						spotTaken = false;
					
					
					}
				}
				//plants the flower in the garden by calling the method plantFlowerInGarden()
				players[i].plantFlowerInGarden(r, c);
				
				
				//this for-loop iterates through every player and if any players garden is full, the loop to keep running the game will stop
				for (int j=0; j<players.length; j++) {
					
					if (players[j].isGardenFull() == true) {
						//changes the value of the boolean which repeats the game so that the game can now stop when a winner is found
						gameRepeat = true;
						continue gameLoop;
					}
				}
				
			}
			
		}
		
		//adds one to the number of rounds played everytime this loop goes around
		numberRoundsPlayed++;
		
		//while loop that keeps running the game until a garden is full
		} while (gameRepeat == false);
		
		//prints number of rounds played
		System.out.println("\nFINAL RESULTS \n-------------\nHere are the gardens after " + numberRoundsPlayed + " rounds.");
		
		//this for-loop displays all the players gardens when the game is ended
		for (int i = 0; i<players.length; i++) {
			System.out.println(players[i].getName() + "'s garden:\n");
			System.out.println(players[i].showGarden());
			System.out.println();
		}
		
		System.out.println();
		

		//this for-loop iterates through all the players to check if their gardens are full
		for (int j=0; j<players.length; j++) {
					
			//if a garden is full then it will print which player has the full garden (the winner of the game) and displays the winner and an exit statement
			if (players[j].isGardenFull() == true) {

						System.out.println("And the winner is ..... " + players[j].getName() + "!!!!!");
						System.out.println("What a beautiful garden you have.\n\nHope you had fun!!!!");
					}
				}
		
		
		keyboard.close();
	}
		


}

