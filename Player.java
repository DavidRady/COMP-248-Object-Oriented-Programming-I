//--------------------------------------------------------
//Assignment 3
//Written by: David Rady 40098177
//For COMP 248 Section R - Fall 2018
//--------------------------------------------------------

//purpose of this assignment is described in driver class


public class Player {

	//string name that stores a players name
	private String name;
	
	//garden object which is the players garden
	private Garden garden;
	
	
	//constructor which takes two parameters, string for players name and integer for size of garden
	public Player(String name, int sizeOfGarden) {
        
		this.name = name;
        
		garden = new Garden(sizeOfGarden);
    }
	
	//method to return players name
	public String getName() {
		
       return name;
    }
	
	
	//method which will return the result of a call to the method countPossibleFlowers() by the attribute garden
	public int howManyFlowersPossible() {
		     
		return garden.countPossibleFlowers();    
	}
	
	//method which will return the result of a call to the method countPossibleTrees() by the attribute garden
	public int howManyTreesPossible() {
	        
		return garden.countPossibleTrees();    
	}
	
	
	//method which will return the result of a call to the method getInLocation(r,c) by the attribute garden. Returns the character of what is planted in that location
	public char whatIsPlanted(int r, int c) {
        
		return garden.getInLocation(r, c);
    }
	
	//method which will call the method plantTree() to plant a tree in garden in location[r][c], [r+1][c], [r][c+1] and [r+1][c+1].
	public void plantTreeInGarden(int r, int c) {
		
		garden.plantTree(r, c);
	}
	
	//which will call the method plantFlower() to plant a flower in garden in location [r][c].
	public void plantFlowerInGarden(int r, int c) {
		
		garden.plantFlower(r, c);
	}
	
	//method  which will call the method removeFlower() to remove a flower in garden in location [r][c], which means assigning a '-' to the location [r][c]. Note the same method removeFlower() will be used to remove a portion of a tree.
	public void eatHere(int r, int c) {
		
		garden.removeFlower(r, c);
	}
	
	//method which will return the result of a call to the method gardenFull() by the attribute garden.
	public boolean isGardenFull() {
		
		return garden.gardenFull();
	}
	
	//which displays the content of the garden object as in figure 3
	public String showGarden() {
		
		return garden.toString();
	}

}
