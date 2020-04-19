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
			for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
				if (i <= 9) {
					System.out.print(" ");
				}
				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
				}
				System.out.print(i + ")  " + Driver.currentGameYard.list[i-1].name);
				System.out.println("\t" + Driver.currentGameYard.list[i-1].speed + "\t" + Driver.currentGameYard.list[i-1].turnMode); 
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
			
				System.out.println("Would you like to add a ship [M]anually or from the [S]hipyard? ");
				String userInput2 = Driver.getInput("MS");
				
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
							star.name = nameInput;
	
							System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Speed    : ");
							int speedInput = Driver.getNumber(0, 32);
							star.speed = speedInput;
	//						System.out.println();
	
							System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("AABCDEFXY");
							star.turnMode = turnModeInput.toUpperCase();
							System.out.println();
													
							Driver.currentGameYard.addShipToShipyard(star);
						}	
					}
				} else if (userInput2.equalsIgnoreCase("S")) {
					Driver.defaultYard.displayShipyardMenu(1);
				}

			} else if (userInput.equalsIgnoreCase("M")) {
				boolean cont2 = true;
				while (cont2) {
					
					System.out.print("Modify which ship? [0 to cancel]");
				
					int modifyInput = Driver.getNumber(0, Driver.currentGameYard.numShips);	

					if(modifyInput == 0) {
						cont2 = false;
						//break;
					}else {
					
						System.out.print("[N]ame, [S]peed, [T]urn Mode or [A]ll? [0 to cancel] ");
						String nameSpeedBoth = Driver.getInput("NSTA0");
						
						if(nameSpeedBoth.equalsIgnoreCase("N") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Name : ");
							String nameInput = keyboard.nextLine();
							Driver.currentGameYard.list[modifyInput-1].name = nameInput;
						} else if(nameSpeedBoth.equalsIgnoreCase("S") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Speed: ");
							int speedInput = Driver.getNumber(-1, 32);
							Driver.currentGameYard.list[modifyInput-1].speed = speedInput;
						} else if(nameSpeedBoth.equalsIgnoreCase("T") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("AABCDEFXY");
							Driver.currentGameYard.list[modifyInput-1].turnMode = turnModeInput.toUpperCase();
						} else if(nameSpeedBoth.equalsIgnoreCase("0")) {
							//continue;
						}
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
	
	public static void SortShips () {
		Starship temp = new Starship();
		Driver.currentGameYard.list[Driver.currentGameYard.numShips] = temp;

		for (int x = 0; x <= Driver.currentGameYard.numShips-1; x++) {
			for (int y = 0; y <= Driver.currentGameYard.numShips-2; y++) {
				if (Driver.currentGameYard.list[y].speed < Driver.currentGameYard.list[y+1].speed) {
					
					Driver.currentGameYard.list[49] = Driver.currentGameYard.list[y];
					Driver.currentGameYard.list[y] = Driver.currentGameYard.list[y+1];
					Driver.currentGameYard.list[y+1] = Driver.currentGameYard.list[49];
				}
			}
		}
	}
}
