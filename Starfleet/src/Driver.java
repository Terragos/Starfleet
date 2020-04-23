import java.util.Scanner;

//TODO, NEED CODE TO EMPTY OUT KEYBOARD BUFFER  ???  In Impulse procedure after toggling TORPEDO, maybe ???  (there is an extra space)
//TODO, in Damage Allocation if user input "N" (instead of RETURN) zero out "that part"'s number of reaming systems
//TODO, IS it possible to change the name of "PhaseCalculation" to "ImpulseCalculation"?  Everywhere "Phase" --> "Impulse" ???

public class Driver {

	public final static boolean TESTING = false;
	public static int numImpulses = 0;
	public static Shipyard currentGameYard = new Shipyard("Current Game Shipyard");
	public static Shipyard defaultYard = Shipyard.setupDefaultShipyard();
	public static Scanner keyboard = new Scanner(System.in);

	/* Main Method */
	public static void main(String[] args) {
		
		if(Driver.TESTING) {
			System.out.println("|==============================================================================|");
			System.out.println("|            HERE ARE SEVERAL TEST SHIPS THAT HAVE BEEN LOADED                 |");
			System.out.println("|===================== =========================================================|");
			System.out.println();
			
			currentGameYard.addShipToShipyard(defaultYard.list[0]);
			currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 15) + 1;
			currentGameYard.addShipToShipyard(defaultYard.list[3]);
			currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
			currentGameYard.addShipToShipyard(defaultYard.list[18]);
			currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
//			currentGameYard.addShipToShipyard(defaultYard.list[54]);
//			currentGameYard.list[3].speed = DamageAllocation.rollDice(1, 15) + 1;
//			currentGameYard.addShipToShipyard(defaultYard.list[60]);
//			currentGameYard.list[4].speed = DamageAllocation.rollDice(1, 10) + 1;
//			currentGameYard.addShipToShipyard(defaultYard.list[65]);
//			currentGameYard.list[5].speed = DamageAllocation.rollDice(1, 10) + 1;
//			currentGameYard.addShipToShipyard(defaultYard.list[297]);
//			currentGameYard.list[6].speed = DamageAllocation.rollDice(1, 5) + 1;
			
			ShipSetup.SortCurrentShipyard();
			ShipSetup.PrintCurrentShipsInGame();
			System.out.println();
			System.out.println();
			
		}
		
		boolean cont = true;

		System.out.println("|==============================================================================|");
		System.out.println("|                         STAR FLEET BATTLES UTILITY                           |");
		System.out.println("|==============================================================================|");
		System.out.println("|              Java Code by Harrison Weese and D. Brian Weese                  |");
		System.out.println("|==============================================================================|");
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
			System.out.println("|==========================================================================|");
			System.out.println("|                             SFB MAIN MENU                                |");
			System.out.println("|==========================================================================|");
			System.out.println("|            [M]odify, Add, Remove or View Ship Name & Stats               |");
			System.out.println("|            [I]mpulse Movement Procedure                                  |");
			System.out.println("|            [W]eapon Damage Procedure                                     |");
			System.out.println("|            [D]amage Allocation Procedure                                 |");
			System.out.println("|            [S]hipyard                                                    |");
			System.out.println("|            [C]hange SSD Numbers (because of Program Crash)               |");
			System.out.println("|==========================================================================|");
			System.out.println("|                                [Q]uit                                    |");
			System.out.println("|==========================================================================|");

			String userInput = getInput("MIWDQSC");
			String userInput3 = "";
			
			int damageTotal = 0;
			if(userInput.equalsIgnoreCase("M")) {
				ShipSetup.ShipSetupOrModify("N");     // Pass "N" to NOT go on to Impulse Procedure ("Y" to go to...)
			} else if(userInput.equalsIgnoreCase("I")) {
				if (currentGameYard.numShips > 0) { 
					PhaseCalculation.PhaseCalc();
				} else {
					System.out.print("You have no ships assigned to the current game.  Would you like to add some ships?");
					userInput3 = getInput("YN");
					if (userInput3.equalsIgnoreCase("Y")) {
						ShipSetup.ShipSetupOrModify("N");
					}
				}
				System.out.println();
			} else if(userInput.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam(-1);
			} else if(userInput.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc(-1);
			} else if(userInput.equalsIgnoreCase("S")) {
				defaultYard.displayShipyardMenu(-1);
			} else if(userInput.equalsIgnoreCase("C")) {
				//  Allow user to ADD ships and tell how many systems left of each type
				ModifyShipSystems();
				
			} else if(userInput.equalsIgnoreCase("Q")) {
				System.out.println("Exiting Program...");
				break;
			}
			
			System.out.println();
			System.out.println();
		}
	}
	
	public static void ModifyShipSystems() {
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                    SHIP SSD CHANGE/MODIFICATION MENU                     |");

			if (Driver.currentGameYard.numShips > 0) {
				System.out.println("|==========================================================================|");
				System.out.println("|                  Current ship, object and monster list:                  |");
				System.out.println("|==========================================================================|");
				System.out.println();

				ShipSetup.SortCurrentShipyard();
				ShipSetup.PrintCurrentShipsInGame();
				
				System.out.println();
			}
						
			System.out.println("|==========================================================================|");
			System.out.println("|       [A]dd Ship from Shipyard   [M]odify Systems   [R]emove Ship        |");
			System.out.println("|                      RETURN to return to Main Menu                       |"); 
			System.out.println("|==========================================================================|");

			String userInput = Driver.getInput("AMR");
			
			if (userInput.contentEquals("")) {
				//break;
				cont = false;

			} else if (userInput.equalsIgnoreCase("A")) {
			
				Driver.defaultYard.displayShipyardMenu(1);

			} else if (userInput.equalsIgnoreCase("M")) {
				
				boolean cont2 = true;
				while (cont2) {
					
					int dummyArray[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
					System.out.print("Modify systems of which ship? [0 to cancel]");
				
					int shipNumInput = Driver.getNumber(0, Driver.currentGameYard.numShips);	

					if(shipNumInput == 0) {
						cont2 = false;
						//break;
					}else {
						System.out.println();
						System.out.println("Alternate Parts on SSDs include:");
						System.out.println("\tFlag Bridge = Security");
						System.out.println("\tTorpedo = Photon, Disruptor Bolt, Plasma, SFG");
						System.out.println("\tTractor = Web");
						System.out.println("\tDrone = ADD");
						System.out.println();
						System.out.println("Please indicate how many boxes are left on the SSD \nfor the following systems (-1 = cancel):");

						int cancel = 0;
						for (int numPart = 0; numPart <=24; numPart++) {
							System.out.print("\t" + currentGameYard.list[shipNumInput-1].ssd[numPart].name + ": ");
							dummyArray[numPart] = getNumber(-1, 100);
							if (dummyArray[numPart] < 0) {
								System.out.println("Modification cancelled");
								cancel = -1;
								break;
							}
						}
						if (cancel == 0) {
							for (int numPart = 0; numPart <=24; numPart++) {
								currentGameYard.list[shipNumInput-1].ssd[numPart].numOfThisPart = dummyArray[numPart];
							}
						}
						System.out.println();
						ShipSetup.PrintCurrentShipsInGame();
						System.out.println();
					}
				}
				
			} else if (userInput.equalsIgnoreCase("R")) {
				
				System.out.print("Remove which ship? [0 to cancel] ");
				int removeInput = -1;

				removeInput = Driver.getNumber(0, Driver.currentGameYard.numShips);				//  Get a new input

				Driver.currentGameYard.removeShipFromShipyard(removeInput);

			}	
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
			if (inputLetter.equalsIgnoreCase("AA") || inputLetter.equalsIgnoreCase("AAA") || inputLetter.equalsIgnoreCase("AAAA")) {
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
		return input;
	}
	
}
