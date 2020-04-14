import java.util.Random;
import java.util.Scanner;

public class PhaseCalculation {
	
	public static int NumShips = 0;
	public static Starship[] starships;
	public static int numImpulses = 0;
	
	public static void PhaseCalc() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("How many ships?");
		int shipsNumInput = keyboard.nextInt();
		NumShips = shipsNumInput;
		
		starships = new Starship[NumShips];
		
		for(int i = 0; i < NumShips; i++) {

			Starship star = new Starship();		
			System.out.print("Ship " + (i+1) + " Name: ");
			String nameInput = keyboard.next();
			star.name = nameInput;
			System.out.print("Ship " + (i+1) + " Speed: ");
			int speedInput = keyboard.nextInt();
			star.speed = speedInput;
			
			starships[i] = star;
		}
	
		// NumImpulses is basically just the max speed among ships
		for(int i = 0; i < NumShips; i++) {	
			if (starships[i].speed > numImpulses) {
				numImpulses = starships[i].speed;
			}
		}
	
		for(int i = 0; i < NumShips; i++) {	
			starships[i].spi = (double) starships[i].speed / (double) numImpulses;			
		}
		
	/*
		System.out.println("================================================");
		System.out.println("                   Ship Speeds");
		System.out.println("------------------------------------------------");
		System.out.println("              " + s1.name + "    " + s2.name + "    " + s3.name);
		System.out.print("                " + s1.speed);
		for (int x = 1; x<=s1.name.length()+2; x++) {
			System.out.print(" ");
		}
		System.out.print(s2.speed);
		for (int x = 1; x<=s2.name.length()+3; x++) {
			System.out.print(" ");
		}
		System.out.println(s3.speed);
		System.out.println("================================================");
		System.out.println("          Press RETURN for next Impulse");		
		System.out.println("================================================");
		System.out.println();
	
	*/
	
		for(int i = 1; i <= numImpulses; i++) {	
			if (i < 10) {
				System.out.print("Impulse 0" + i + ": ");
			} else {
				System.out.print("Impulse " + i + ": ");
			}

			for(int k = 0; k < NumShips; k++) {	
				starships[k].distrv += starships[k].spi;		
				if(starships[k].distrv - 1 >= -.001) {
					starships[k].distrv--;
					System.out.print(starships[k].name + " ");
				}else {
					for(int j = 0; j < (starships[k].name.length() + 1); j++) {
						System.out.print(" ");
					}
				}
			}
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
