//--------------------------------------------------------
//Assignment 3
//Written by: David Rady 40098177
//For COMP 248 Section R - Fall 2018
//--------------------------------------------------------

//purpose of this assignment is described in driver class

public class Dice {

	//the two integers to record the value of each die 
	private int valueOfDie1;
	private int valueOfDie2;
	
	//default constructor which automatically sets the value of each die to 0
	public Dice() {
		valueOfDie1 = 0;
		valueOfDie2 = 0;
	}
	
	//accessor method to get the value of die 1
	public int getValueDie1() {
		return valueOfDie1;
	}
	
	//accessor method to get the value of die 2
	public int getValueDie2() {
		return valueOfDie2;
	}
	
	
	//randomly assigns a value between 1 and 6 to each die and then returns the sum of the two dice
	public int rollDice() {
		valueOfDie1 = (int)(Math.random()*6+1);
		valueOfDie2 = (int)(Math.random()*6+1);
		return (valueOfDie1 + valueOfDie2);
	}
	
	//toString() method to return the content of each die as a string
	public String toString() 
	{
		return ("(Die 1: " + valueOfDie1 + "  Die 2: " + valueOfDie2 +")");
	}
}
