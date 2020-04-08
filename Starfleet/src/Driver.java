import java.util.Random;

public class Driver {

	/* Method */
	private static void printNums(int num) {
		
		/* Declaring a Variable */
		int x;
		
		/* Initializing a Variable */
		x = num;
		// int x = num 
		// is also valid
		
		/* While Loop */
		while(x >= 0) {
			
			/* Print Statement */
			System.out.println("X: " + x);
			x--;
			
			/* If Statement */
			if(x > 10) {
				System.out.println("DAAAAAANG thats a big number.");
			}
			else {
				// Do Nothing Currently
			}
			
		}
		
	}
	
	public static int rollDice(int numOfDice, int numOfSides, boolean print){
		Random randomGenerator = new Random();
		int dieRoll;
		int totalDieRoll = 0;
		
		for(int i = 0; i < numOfDice; i++) {
			dieRoll = randomGenerator.nextInt(numOfSides) + 1;
			totalDieRoll += dieRoll;
		}
		
		//Hello World
		
		if(numOfDice == 0) {
			System.out.println("Please use more than zero dice rolls.");
		}
		else if(print == true && numOfDice == 1) {
			System.out.println("Die Roll: " + totalDieRoll);
		}
		else if(print == true){
			System.out.println("Total Dice Roll: " + totalDieRoll);
		}
		else {
			//Nothing since print is false
		}
		
		return totalDieRoll;
	}
	
	/* MAIN Method */
	public static void main(String[] args) {
		
		// Die Roll is equal to the die roll that was printed.
		// Do not need to use but we do have access to it here.
		int dieRoll = rollDice(1, 36, true);
		
		/* Calling a Method */
		printNums(5);
		
		/* Making an Object of type Starship */
		StarShip s1 = new StarShip();
		s1.name = "Enterprise";
		s1.race = "Federation";
		s1.speed = 5;
		
		s1.move();
		
		/* Print Using Variables and Concatenation */
		System.out.println("Speed of " + s1.name + " is " + s1.speed + ".");

	}
}
