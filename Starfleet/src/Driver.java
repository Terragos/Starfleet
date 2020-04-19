import java.util.Scanner;

//import Starship.Race;

public class Driver {

	public static int numImpulses = 0;
	public static Shipyard currentGameYard = new Shipyard("Current Game Shipyard");
	public static Shipyard defaultYard = Shipyard.setupDefaultShipyard();

	/* Main Method */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;

		System.out.println("|============================================================|");
		System.out.println("|                 STAR FLEET BATTLES UTILITY                 |");
		System.out.println("|============================================================|");
		System.out.println("|       Java Code by D. Brian Weese and Harrison Weese       |");
		System.out.println("|============================================================|");
		System.out.println();
		System.out.println();
		System.out.print("Would you like to do an initial setup of ships, objects and/or monsters for the game? [Y][N] ");
		
		String doSetup = getInput("YN");
		
		if (doSetup.equalsIgnoreCase("Y")) {			
			System.out.println();
			System.out.println("|============================================================|");
			System.out.println("|   Enter Ship Info.  Hit RETURN to continue to Main Menu    |");
			System.out.println("|============================================================|");
			System.out.println();

			while(cont) {
				Starship star = new Starship();		
				System.out.print("Ship " + (currentGameYard.numShips + 1) + " Name     : ");
		
				String nameInput = keyboard.nextLine();
				if (nameInput.contentEquals("")) {
				//break;
					cont = false;
				} else {
					star.name = nameInput;

					System.out.print("Ship " + (currentGameYard.numShips + 1) + " Speed    : ");
					int speedInput = getNumber(0, 32);
					star.speed = speedInput;

					System.out.print("Ship " + (currentGameYard.numShips + 1) + " Turn Mode: ");
					String turnModeInput = getInput("AABCDEFXY");
					star.turnMode = turnModeInput;
					System.out.println();

					currentGameYard.addShipToShipyard(star);
				}	
			}
		}		
		
//  TEST DUMMY STARSHIP INFO
		
//		Starship star = new Starship();
//		star.name = "Fed-CV 1";
//		star.turnMode = "D";
//		star.speed = 5;
//		starships[0] = star;
//
//		star = new Starship();
//		star.name = "Kli-D7";
//		star.turnMode = "E";
//		star.speed = 10;
//		starships[1] = star;
//		
//		star = new Starship();
//		star.name = "Rom-WH";
//		star.turnMode = "A";
//		star.speed = 12;
//		starships[2] = star;
//		
//		star = new Starship();
//		star.name = "Monster 1";
//		star.turnMode = "A";
//		star.speed = 2;
//		starships[3] = star;
//		
//		star = new Starship();
//		star.name = "Monster 2";
//		star.turnMode = "B";
//		star.speed = 14;
//		starships[4] = star;
//		
//		star = new Starship();
//		star.name = "Fed-CV 2";
//		star.turnMode = "B";
//		star.speed = 7;
//		starships[5] = star;
//		
//		star = new Starship();
//		star.name = "Kli-F5";
//		star.turnMode = "C";
//		star.speed = 21;
//		starships[6] = star;
//		
//		star = new Starship();
//		star.name = "Asteroid";
//		star.turnMode = "C";
//		star.speed = 2;
//		starships[7] = star;
//		
//		star = new Starship();
//		star.name = "Fed-CV 3";
//		star.turnMode = "C";
//		star.speed = 8;
//		starships[8] = star;
//		
//		star = new Starship();
//		star.name = "Kli-DN";
//		star.turnMode = "E";
//		star.speed = 8;
//		starships[9] = star;
//		
//		star = new Starship();
//		star.name = "Asteroid";
//		star.turnMode = "C";
//		star.speed = 2;
//		starships[10] = star;
//		
//		star = new Starship();
//		star.name = "Fed-CV 3";
//		star.turnMode = "C";
//		star.speed = 8;
//		starships[11] = star;
//		
//		numShips = 12;

// END TEST DUMMY STARSHIP INFO
		
		
		cont = true;
		
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|============================================================|");
			System.out.println("|                         SFB MAIN MENU                      |");
			System.out.println("|============================================================|");
			System.out.println("|        [M]odify, Add or Remove Ship Name & Speeds          |");
			System.out.println("|        [I]mpulse Movement Procedure                        |");
			System.out.println("|        [W]eapon Damage Procedure                           |");
			System.out.println("|        [D]amage Allocation Procedure                       |");
			System.out.println("|        [S]hipyard                                          |");
			System.out.println("|============================================================|");
			System.out.println("|                            [Q]uit                          |");
			System.out.println("|============================================================|");

			String userInput = getInput("MIWDQS");
			
			int damageTotal = 0;
			if(userInput.equalsIgnoreCase("M")) {
				ShipSetup.ShipSetupOrModify("N");     // Pass "N" to NOT go on to Impulse Procedure ("Y" to go to...)
			} else if(userInput.equalsIgnoreCase("I")) {
				PhaseCalculation.PhaseCalc();
			} else if(userInput.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam(-1);
			} else if(userInput.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc(-1);
			} else if(userInput.equalsIgnoreCase("S")) {
				defaultYard.displayShipyardMenu(-1);
			} else if(userInput.equalsIgnoreCase("Q")) {
				System.out.println("Exiting Program...");
				break;
			}
			
			System.out.println();
			System.out.println();
		}
	}
	
//  GET LETTER AS INPUT THAT IS IN PASSED STRING
	
	public static String getInput(String word) {
		Scanner keyboard = new Scanner(System.in);
		
		boolean cont = true;
		
		String inputLetter = "";
		int location = -2;
		while (location < 0) {
			inputLetter = keyboard.nextLine().toUpperCase();
			location = word.indexOf(inputLetter);                  //  Getting position of user's input of the "word" string passed through
			if (location == -1 ) {                                 //  location = -1 if character is not found in "word" string
				for (int d = 0; d <= word.length()-1; d++) {
					System.out.print("[" + word.charAt(d) + "]");  //  Remind user what letter inputs are being looked for
				}
			}
		}
		return inputLetter;
	}
	
//  GET NUMBER AS INPUT THAT IS BETWEEN PASSED SMALL/BIG

	public static int getNumber(int small, int big) {
		Scanner keyboard = new Scanner(System.in);
		
		String userInput = "";					//  Initialize string

		int intFromString = -1;
		
		int input = 0;
		boolean cont = true;
		
		while(cont) {
			try {
				input = keyboard.nextInt();
				if (input < small || input > big) {
					System.out.print("[" + (small+1) + "-" + big + "]"); //  Remind user what number range is being looked for
				} else { 
					cont = false;
				}
			} catch (Exception e) {		
				System.out.print("[" + (small+1) + "-" + big + "]");	 // Remind user what number range is being looked for
				input = 0; // This line and 
				keyboard.nextLine(); // this line prevent a very bad infinite loop
			}
		}
		
//		if(input == 0) {System.out.println("??");}
		
		return input;
	}
	
}
