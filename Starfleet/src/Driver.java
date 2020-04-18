import java.util.Scanner;

public class Driver {

	public static int numImpulses = 0;
	public static int numShips = 0;
	public static Starship[] starships;
	

// MAIN Method
	
	public static void main(String[] args) {

		starships = new Starship[50];
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;

		System.out.println("|============================================================|");
		System.out.println("|                 STAR FLEET BATTLES UTILITY                 |");
		System.out.println("|============================================================|");
		System.out.println("|       Java Code by D. Brian Weese and Harrison Weese       |");
		System.out.println("|============================================================|");
		System.out.println();
		System.out.println();
		System.out.println("Would you like to do an initial setup of ships, objects and/or monsters for the game? [Y][N] ");
		
		String doSetup = getInput("YN");
		
		if (doSetup.equalsIgnoreCase("Y")) {			
			System.out.println();
			System.out.println("|============================================================|");
			System.out.println("|   Enter Ship Info.  Hit RETURN to continue to Main Menu    |");
			System.out.println("|============================================================|");
			System.out.println();

			while(cont) {
				Starship star = new Starship();		
				System.out.print("Ship " + (numShips + 1) + " Name     : ");
		
				String nameInput = keyboard.nextLine();
				if (nameInput.contentEquals("")) {
				//break;
					cont = false;
				} else {
					star.name = nameInput;

					System.out.print("Ship " + (numShips + 1) + " Speed    : ");
					int speedInput = getNumber(0, 32);
					star.speed = speedInput;
					System.out.println();

					System.out.print("Ship " + (numShips + 1) + " Turn Mode: ");
					String turnModeInput = getInput("AABCDEFXY");
					star.turnMode = turnModeInput;
					System.out.println();

					starships[numShips] = star;
				
					numShips++;
				}	
			}
		}


//  TEST DUMMY STARSHIP INFO
		
		Starship star = new Starship();
		star.name = "Fed-CV 1";
		star.turnMode = "D";
		star.speed = 5;
		starships[0] = star;
		
		star = new Starship();
		star.name = "Kli-D7";
		star.turnMode = "E";
		star.speed = 10;
		starships[1] = star;
		
		star = new Starship();
		star.name = "Rom-WH";
		star.turnMode = "A";
		star.speed = 12;
		starships[2] = star;
		
		star = new Starship();
		star.name = "Monster 1";
		star.turnMode = "A";
		star.speed = 2;
		starships[3] = star;
		
		star = new Starship();
		star.name = "Monster 2";
		star.turnMode = "B";
		star.speed = 14;
		starships[4] = star;
		
		star = new Starship();
		star.name = "Fed-CV 2";
		star.turnMode = "B";
		star.speed = 7;
		starships[5] = star;
		
		star = new Starship();
		star.name = "Kli-F5";
		star.turnMode = "C";
		star.speed = 21;
		starships[6] = star;
		
		star = new Starship();
		star.name = "Asteroid";
		star.turnMode = "C";
		star.speed = 2;
		starships[7] = star;
		
		star = new Starship();
		star.name = "Fed-CV 3";
		star.turnMode = "C";
		star.speed = 8;
		starships[8] = star;		
		star = new Starship();
		star.name = "Kli-F5";
		star.turnMode = "C";
		star.speed = 21;
		starships[9] = star;
		
		star = new Starship();
		star.name = "Asteroid";
		star.turnMode = "C";
		star.speed = 2;
		starships[10] = star;
		
		star = new Starship();
		star.name = "Fed-CV 3";
		star.turnMode = "C";
		star.speed = 8;
		starships[11] = star;
		
		numShips = 12;

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
			System.out.println("|============================================================|");
			System.out.println("|                            [Q]uit                          |");
			System.out.println("|============================================================|");

			String userInput = getInput("MIWDQmiwdq");
			
			int damageTotal = 0;
			if(userInput.equalsIgnoreCase("M")) {
				ShipSetup.ShipSetupOrModify("N");     // Pass "N" to NOT go on to Impulse Procedure ("Y" to go to...)
			} else if(userInput.equalsIgnoreCase("I")) {
				PhaseCalculation.PhaseCalc();
			} else if(userInput.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam(-1);
			} else if(userInput.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc(-1);
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
		
		while(intFromString < 0) {
			userInput = keyboard.nextLine();
			try {
				intFromString = Integer.parseInt(userInput.trim());		//  Test input to see if it is actually an integer (and not a letter)
			} catch (NumberFormatException ne) {
				if (intFromString == -1) {
					System.out.print("[" + small + "-" + big + "]");	//  Remind user what number range is being looked for
					intFromString = -1;
				}
			}
		}
		
		if (intFromString <= small || intFromString >= big) {
			System.out.print("[" + small + "-" + big + "]");		//  Remind user what number range is being looked for
		}
		return intFromString;
	}
	
//	public static void StringToInteger() {                               //  EXMAPLE OF ERROR CATCH
//		System.out.println("Enter the dimensions of a rectangle");
//		System.out.print("L:");
//		Scanner s = new Scanner(System.in);
//		try{
//			// convert the string read from the scanner into Integer type
//			Integer length = Integer.parseInt(s.nextLine());
//			System.out.print("W:");
//			s = new Scanner(System.in);
//			Integer width = Integer.parseInt(s.nextLine());
//			// Printing the area of rectangle
//			System.out.println("Area of rectangle:"+width*length);
//		}
//		catch(NumberFormatException ne){
//			System.out.println("Invalid Input");
//		}
//		finally{
//			s.close();
//		}
//
//	}

}
