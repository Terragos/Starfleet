import java.util.Random;
import java.util.Scanner;

public class PhaseCalculation {
	
	public static int numShips = 0;
	public static Starship[] starships;
	public static int numImpulses = 0;
	
	public static void PhaseCalc() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("How many ships?");
		int shipsNumInput = keyboard.nextInt();
		numShips = shipsNumInput;
		
		starships = new Starship[numShips];
		
		for(int i = 0; i < numShips; i++) {

			Starship star = new Starship();		
			System.out.print("Ship " + (i+1) + " Name : ");
			String nameInput = keyboard.next();
			star.name = nameInput;
			System.out.print("Ship " + (i+1) + " Speed: ");
			int speedInput = keyboard.nextInt();
			star.speed = speedInput;
			
			System.out.println();
			
			starships[i] = star;
			
		}
	
		// numImpulses is basically just the max speed among ships
		for(int i = 0; i < numShips; i++) {	
			if (starships[i].speed > numImpulses) {
				numImpulses = starships[i].speed;
			}
		}
	
		for(int i = 0; i < numShips; i++) {	
			starships[i].spi = (double) starships[i].speed / (double) numImpulses;			
		}
		
		
		System.out.print("=============");
		for(int k = 0; k < numShips; k++) {	
			for(int j = 0; j < (starships[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.println("=====");
		
		int shipTotal = 0;
		for (int i = 0; i <= numShips-1; i++) {
			shipTotal = shipTotal + starships[i].name.length() + 4;
		}

		for (int i = 1; i <= ((18 + shipTotal)-11)/2; i++) {
			System.out.print(" ");
		}
		System.out.println("SHIP SPEEDS");
		
		System.out.print("-------------");
		for(int k = 0; k < numShips; k++) {	
			for(int j = 0; j < (starships[k].name.length()); j++) {
			System.out.print("-");
			}
			System.out.print("----");
		}
		System.out.println("-----");

		System.out.print("               ");
	 	for (int i = 0; i <= numShips - 1; i++) {
	 		System.out.print(starships[i].name + "    ");
	 	}

		System.out.println();
		
		System.out.print("=========");
		for(int k = 0; k < numShips; k++) {	
			for(int j = 0; j < (starships[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.println("=========");
		
		for (int i = 1; i <= ((18 + shipTotal)-29)/2; i++) {
			System.out.print(" ");
		}
		System.out.println("Press RETURN for next Impulse");		
		
		System.out.print("=============");
		
		for(int k = 0; k < numShips; k++) {	
			for(int j = 0; j < (starships[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.print("=====");
		
		System.out.println();

	
		for(int i = 1; i <= numImpulses; i++) {
			if (i < 10) {
				System.out.print("Impulse 0" + i + ":    ");
			} else {
				System.out.print("Impulse " + i + ":    ");
			}

			for(int k = 0; k < numShips; k++) {	
				starships[k].distrv += starships[k].spi;		
				if(starships[k].distrv - 1 >= -.001) {
					starships[k].distrv--;
					System.out.print(starships[k].name + "    ");
				}else {
					for(int j = 0; j < (starships[k].name.length()); j++) {
						System.out.print(" ");
					}
					System.out.print("    ");
				}
			}

			System.out.println();
			
			waitForSpace();
			
		}  
	}
	
	/* rollDice */
	public static int rollDice(int numOfDice, int numOfSides, boolean print)
	{
		Random randomGenerator = new Random();
		int dieRoll;
		int totalDieRoll = 0;
		
		for(int i = 0; i < numOfDice; i++) {
			dieRoll = randomGenerator.nextInt(numOfSides) + 1;
			totalDieRoll += dieRoll;
		}
		
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
	
	/* Wait for Space */
	public static void waitForSpace () {
		Scanner keyboard = new Scanner(System.in);  //  Create a Scanner object
		boolean notSpace = true;
		while (notSpace) {
			String inputString = keyboard.nextLine();  //  Read user input
			if (inputString.contentEquals("")) {
				notSpace = false;
			}
		}
			
	}
	
}
