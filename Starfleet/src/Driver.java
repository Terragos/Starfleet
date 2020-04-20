import java.util.Scanner;

//TODO, if no ships, dont do impulses

public class Driver {

	public final static boolean TESTING = false;
	public static int numImpulses = 0;
	public static Shipyard currentGameYard = new Shipyard("Current Game Shipyard");
	public static Shipyard defaultYard = Shipyard.setupDefaultShipyard();
	public static Scanner keyboard = new Scanner(System.in);

	/* Main Method */
	public static void main(String[] args) {
		
		if(Driver.TESTING) {
			currentGameYard.addShipToShipyard(defaultYard.list[30]);
			currentGameYard.addShipToShipyard(defaultYard.list[36]);
			currentGameYard.addShipToShipyard(defaultYard.list[72]);
			for(int i = 0;  i < currentGameYard.numShips; i++) {
				if((i+1) * 4 <= 32)
					currentGameYard.list[i].speed = (i+1) * 4;
			}
		}
		
		boolean cont = true;

		System.out.println("|==============================================================================|");
		System.out.println("|                         STAR FLEET BATTLES UTILITY                           |");
		System.out.println("|==============================================================================|");
		System.out.println("|              Java Code by Harrison Weese and D. Brian Weese                  |");
		System.out.println("|==============================================================================|");
		System.out.println();
		System.out.println();
		System.out.print("Would you like to add ships [M]anually or from the [S]hipyard? [0 = Main Menu] ");

		String userInput2 = Driver.getInput("MS0");
		
		if (userInput2.equalsIgnoreCase("M")) {
			boolean cont2 = true;
			while(cont2) {
				Starship star = new Starship();		
				System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Name     : ");
		
				String nameInput = keyboard.nextLine();
				
				if (nameInput.contentEquals("")) {
				//break;
					cont2 = false;
				} else {
					if(nameInput.length() > 10)
						nameInput = nameInput.substring(0, 10);
					star.name = nameInput;

					System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Speed    : ");
					int speedInput = Driver.getNumber(0, 32);
					star.speed = speedInput;
//						System.out.println();

					System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Turn Mode: ");
					String turnModeInput = Driver.getInput("ABCDEFXY-");
					star.turnMode = turnModeInput.toUpperCase();
											
					System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Break Down [?-6] ('-' = n/a): ");
					String breakDownString = "-";
					String breakDownInput = Driver.getInput("-123456");
					if (breakDownInput == "-") {
						breakDownString = "-";
					} else {
						breakDownString = breakDownInput.concat("-6");
					}
					star.breakDown = breakDownString;
					System.out.println();

					Driver.currentGameYard.addShipToShipyard(star);
				}	
			}
		} else if (userInput2.equalsIgnoreCase("S")) {
			Driver.defaultYard.displayShipyardMenu(1);
		}
		
		
		cont = true;
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                             SFB MAIN MENU                                |");
			System.out.println("|==========================================================================|");
			System.out.println("|            [M]odify, Add, Remove or View Ship Name & Stats               |");
			System.out.println("|            [I]mpulse Movement Procedure                                  |");
			System.out.println("|            [W]eapon Damage Procedure                                     |");
			System.out.println("|            [D]amage Allocation Procedure                                 |");
			System.out.println("|            [S]hipyard                                                    |");
			System.out.println("|==========================================================================|");
			System.out.println("|                                [Q]uit                                    |");
			System.out.println("|==========================================================================|");

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
			if (inputLetter.equalsIgnoreCase("AA")) {
				location = 0;
			} else if (location == -1 ) {                          //  location = -1 if character is not found in "word" string
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
