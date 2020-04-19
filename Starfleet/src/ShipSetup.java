import java.util.Scanner;

public class ShipSetup {

	public static void ShipSetupOrModify(String goToImpProc) {
		
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|============================================================|");
			System.out.println("|                  SHIP MODIFICATION MENU                    |");
			System.out.println("|============================================================|");
			System.out.println("|           Current ship, object and monster list:           |");
			System.out.println("|============================================================|");
			System.out.println();

			SortShips();															// Always SORT ships before printing to screen
			
			System.out.println("     NAME\tSPEED\tTURN MODE");    					// PRINT MODIFIED LIST OF SHIPS TO SCREEN
			System.out.println();
			for (int i = 1; i <= Driver.numShips; i++) {
				if (i <= 9) {
					System.out.print(" ");
				}
				if (Driver.starships[i-1].name.length() <=3 ) {
					Driver.starships[i-1].name = Driver.starships[i-1].name + "   ";
				}
				System.out.print(i + ")  " + Driver.starships[i-1].name);
				System.out.println("\t" + Driver.starships[i-1].speed + "\t" + Driver.starships[i-1].turnMode); 
			}
			
			System.out.println();
			System.out.println("|=============================================================|");
			System.out.println("|                   [A]dd [M]odify [R]emove                   |");
			if (goToImpProc == "Y") { 																 	 // Print this line only if coming
				System.out.println("|             RETURN to go to Impulse Procedure               |");   // from the Impulse Procedure 
			} else if (goToImpProc == "N") {
				System.out.println("|               RETURN to return to Main Menu                 |");   // Print this line only if coming 
			}																						 	 // from the Main Menu
			System.out.println("|=============================================================|");
			System.out.println();

			String userInput = Driver.getInput("AMR");
			
			if (userInput.contentEquals("")) {
				//break;
				cont = false;

			} else if (userInput.equalsIgnoreCase("A")) {
			
				System.out.println("Would you like to add a ship [M]amually or from the [S]hipyard? ");
				String userInput2 = Driver.getInput("MS");
				
				if (userInput2.equalsIgnoreCase("M")) {
					boolean cont2 = true;
					while(cont2) {
						Starship star = new Starship();		
						System.out.print("Ship " + (Driver.numShips + 1) + " Name     : ");
				
						String nameInput = keyboard.nextLine();
						if (nameInput.contentEquals("")) {
						//break;
							cont2 = false;
						} else {
							star.name = nameInput;
	
							System.out.print("Ship " + (Driver.numShips + 1) + " Speed    : ");
							int speedInput = Driver.getNumber(0, 32);
							star.speed = speedInput;
	//						System.out.println();
	
							System.out.print("Ship " + (Driver.numShips + 1) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("AABCDEFXY");
							star.turnMode = turnModeInput.toUpperCase();
							System.out.println();
							
							Driver.starships[Driver.numShips] = star;
						
							Driver.numShips++;
						}	
					}
				} else if (userInput2.equalsIgnoreCase("S")) {
					Shipyard.GotoShipyard(1);
				}

			} else if (userInput.equalsIgnoreCase("M")) {
				System.out.print("Modify which ship? [0 to cancel]");
				
				int modifyInput = -1;
				while (modifyInput <= -1 || modifyInput >= Driver.numShips) {
					modifyInput = Driver.getNumber(0, Driver.numShips);								//  Get number between 0 and numShips
					if (modifyInput > 0 && modifyInput <= Driver.numShips) {						//  Checking for between 1 and numShips
						System.out.print("[N]ame, [S]peed, [T]urn Mode or [A]ll? [0 to cancel] ");
						String nameSpeedBoth = Driver.getInput("NSTA0");
						if (nameSpeedBoth.equalsIgnoreCase("N") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Name : ");
							String nameInput = keyboard.nextLine();
							Driver.starships[modifyInput-1].name = nameInput;
						}	
						if (nameSpeedBoth.equalsIgnoreCase("S") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Speed: ");
							int speedInput = Driver.getNumber(0, 32);
							Driver.starships[modifyInput-1].speed = speedInput;
						}
						if (nameSpeedBoth.equalsIgnoreCase("T") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("AABCDEFXY");
							Driver.starships[modifyInput-1].turnMode = turnModeInput.toUpperCase();
						}
						System.out.println();
					}
				}
			}
			
			/*
			if (userInput.equalsIgnoreCase("R")) {
				System.out.print("Remove which ship? [0 to cancel] ");
				int removeInput = -1;
				while (removeInput <= -1 || removeInput >= Driver.numShips+1) {		//  While the input is out of range, return -1
					removeInput = Driver.getNumber(0, Driver.numShips);				//  Get a new input
				}
				if (removeInput >= 1 && removeInput <= Driver.numShips) {			//  If the input is in range
					for (int n = removeInput; n <= Driver.numShips; n++) {			//  then move all ships above that space down 1
						Driver.starships[n-1] = Driver.starships[n];
					}
					Driver.numShips--;												//  And reduce numShips by 1
				}
			}
			*/
			
			if (userInput.equalsIgnoreCase("R")) {
				System.out.print("Remove which ship? [0 to cancel] ");
				int removeInput = -1;

				removeInput = Driver.getNumber(0, Driver.numShips);				//  Get a new input

				for (int n = removeInput; n <= Driver.numShips; n++) {			//  then move all ships above that space down 1
					Driver.starships[n-1] = Driver.starships[n];
				}
				Driver.numShips--;												//  And reduce numShips by 1
			}
			
		}
	}
	
	public static void SortShips () {
		Starship temp = new Starship();
		Driver.starships[Driver.numShips] = temp;

		for (int x = 0; x <= Driver.numShips-1; x++) {
			for (int y = 0; y <= Driver.numShips-2; y++) {
				if (Driver.starships[y].speed < Driver.starships[y+1].speed) {
					
					Driver.starships[49] = Driver.starships[y];
					Driver.starships[y] = Driver.starships[y+1];
					Driver.starships[y+1] = Driver.starships[49];
				}
			}
		}
	}
}
