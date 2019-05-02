//--------------------------------------------------------
//Assignment 3
//Written by: David Rady 40098177
//For COMP 248 Section R - Fall 2018
//--------------------------------------------------------

//purpose of this assignment is described in driver class

public class Garden {
  
	//2D character array representing the garden
	char[][] garden;

	//default constructor which initializes the size of the garden to a 3x3
	public Garden() {
    
		garden = new char[3][3];
    
		initializeGarden();
	}

	
  //constructor that takes an integer value as its argument and initializes a garden of the inputted size x size
	public Garden(int size) {
    
		garden = new char[size][size];
    
		initializeGarden();
	}

	
	//this method initializes every space in the garden to '-'
	private void initializeGarden() {
    
		//double for-loop to iterate through every value of the garden to assign every space to '-'
		for (int i = 0; i < garden.length; i++)
      
			for (int j = 0; j < garden.length; j++) {
				garden[i][j] = '-';
			}
	}

	
	//method which returns the value stored in a specific place 
	public char getInLocation(int r, int c) {
		
		return garden[r][c];
	}

	
	//method which plants a flower in a specific place
	public void plantFlower(int r, int c) {
    
		garden[r][c] = 'f';
	}

	
	//method which plants a tree which takes up 4 places ((r,c), (r,c+1), (r+1,c), (r+1,c+1)), the inputted value to store the tree is the top left of the 4 places
	public void plantTree(int r, int c) {
  
		garden[r][c] = 't';
		garden[r][c+1] = 't';
		garden[r+1][c] = 't';
		garden[r+1][c+1] = 't';
	}

  
	//method which removes a value from a specific place and assigns it back to value '-'
	public void removeFlower(int r, int c) {
    
		garden[r][c] = '-';
	}

  
	//method which counts and returns the possible amount of trees that can be placed in the garden
	public int countPossibleTrees() {
    
		//creates a counter for number of possible trees that can be planted starting at 0
		int numPossibleTrees = 0;
    
		//double for-loop to iterate through every element
		for (int i = 0; i < garden.length; i++)
      
			for (int j = 0; j < garden.length; j++) {
				
				//if any element has value '-' it will later 
				if (garden[i][j] == '-') {
					
					//if the value of i+1 or j+1 is greater than or equal to value of length of garden then it will loop back around to the for-loop
					if (i + 1 >= garden.length || j + 1 >= garden.length)
						
						continue;
					
					//by this point if the spaces (i,j), (i+1,j), (i,j+1), (i+1,j+1) all have the value '-' then there is room for a tree to be planted 
					else if (garden[i+1][j] == '-' && garden[i+1][j+1] == '-' && garden[i][j+1] == '-')
						//counter for number of trees that can be planted goes up by one
						numPossibleTrees += 1;
				}		
			}
		
		return numPossibleTrees;
	}

  
	//method which counts and returns the possible number of flowers that can be plants
	public int countPossibleFlowers() {
   
		//creates a counter for the number of possible flowers that can be planted starting at 0
		int numPossibleFlowers = 0;
    
		//double for-loop to iterate through every element 
		for (int i = 0; i < garden.length; i++)
     
			for (int j = 0; j < garden.length; j++) {
      
				//if an element has the value '-' it means there is a free space to plant a flower 
				if (garden[i][j] == '-')
					//counter for number of flowers that can be planted goes up by one
					numPossibleFlowers += 1;

			}

		return numPossibleFlowers;
	}

  
	//method to return true if garden is full, false if there is any free space for something to be planted
	public boolean gardenFull() {
		//assigned variable for number of free spaces which is equivalent to how many flowers can be planted since flowers are 1x1
		int numFreeSpaces = countPossibleFlowers();
    
		//if there are no free spaces (garden is full) it returns true
		if (numFreeSpaces == 0)
      
			return true;
    
		//if there are still free spaces in the garden then it returns false
		else
      
			return false;
	}


  
	//method to return as a String the content of a garden object as an N x N square
	public String toString() {
		
		//formatting 
		System.out.print("  |  ");
	
		//initialized a count 
		int count = 0;
		//while-loop using the count to print out the numbers above each column of the garden
		while (count<garden.length) {
			System.out.print(count + "  ");
			count++;
		}
	
	
		//initiallize a string called garden to print out the garden as a square
		String garden = " ";
		
		//double for-loop to print the garden as an N x N square with appropriate spacing and numbering for each row
		for (int i = 0; i < this.garden.length; i++) {
    	
			garden = garden + "\n\n";
			garden = garden + i + " | ";
    	
			for (int j = 0; j < this.garden[0].length; j++) {
				garden = garden + " " + this.garden[i][j] + " ";
			}
		}
		return garden;
	}

}
