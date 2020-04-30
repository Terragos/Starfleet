import java.util.Scanner;

public class ShipSetup {

	public static void ShipSetupOrModify(String goToImpProc) {
		
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                         SHIP MODIFICATION MENU                           |");

			if (Driver.currentGameYard.numShips > 0) {
				System.out.println("|==========================================================================|");
				System.out.println("|                  Current ship, object and monster list:                  |");
				System.out.println("|==========================================================================|");
				System.out.println();

				SortCurrentShipyard();														// Always SORT ships before printing to screen
				PrintCurrentShipsInGame();
				
				System.out.println();
			}
						
			System.out.println("|==========================================================================|");
			System.out.println("|               [A]dd Ship    [M]odify Ship    [R]emove Ship               |");
			if (goToImpProc == "Y") { 																 	 // Print this line only if coming
				System.out.println("|                    RETURN to go to Impulse Procedure                     |");   // from the Impulse Procedure 
			} else if (goToImpProc == "N") {
				System.out.println("|                      RETURN to return to Main Menu                       |");   // Print this line only if coming 
			}																						 	 // from the Main Menu
			System.out.println("|==========================================================================|");

			String userInput = Driver.getInput("AMR");
			
			if (userInput.contentEquals("")) {
				//break;
				cont = false;

			} else if (userInput.equalsIgnoreCase("A")) {
			
				System.out.println("Would you like to add ships [M]anually or from the [S]hipyard? ");
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
							if(nameInput.length() > 10)
								nameInput = nameInput.substring(0, 10);
							star.name = nameInput;
	
							System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Speed    : ");
							int speedInput = Driver.getNumberNoCancel(0, 32);
							star.speed = speedInput;
	
							System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("AABCDEFXY");
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

			} else if (userInput.equalsIgnoreCase("M")) {
				boolean cont2 = true;
				while (cont2) {
					
					System.out.print("Modify which ship? [RETURN to cancel]");
				
					int modifyInput = Driver.getNumber(1, Driver.currentGameYard.numShips);	

					if(modifyInput == -1) {
						cont2 = false;
						//break;
					}else {
					
						System.out.print("[N]ame, [S]peed, [T]urn Mode or [A]ll? [RETURN to cancel] ");
						String nameSpeedBoth = Driver.getInput("NSTA");
						
						if(nameSpeedBoth.equalsIgnoreCase("N") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Name : ");
							String nameInput = keyboard.nextLine();
							if(nameInput.length() > 10)
								nameInput = nameInput.substring(0, 10);
							Driver.currentGameYard.list[modifyInput-1].name = nameInput;
						}
						if(nameSpeedBoth.equalsIgnoreCase("S") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " NEW Speed: ");
							int speedInput = Driver.getNumberNoCancel(0, 32);
							Driver.currentGameYard.list[modifyInput-1].speed = speedInput;
						}
						if(nameSpeedBoth.equalsIgnoreCase("T") || nameSpeedBoth.equalsIgnoreCase("A")) {
							System.out.print("Ship " + (modifyInput) + " Turn Mode: ");
							String turnModeInput = Driver.getInput("ABCDEF");
							Driver.currentGameYard.list[modifyInput-1].turnMode = turnModeInput.toUpperCase();
						}
						if(nameSpeedBoth.equalsIgnoreCase("")) {
							//continue;
						}
						System.out.println();
					}
				}
			} else if (userInput.equalsIgnoreCase("R")) {
				System.out.print("Remove which ship? [RETURN to cancel] ");
				int removeInput = -1;

				removeInput = Driver.getNumber(1, Driver.currentGameYard.numShips);				//  Get a new input
				if (removeInput > 0) {
					System.out.print("Are you sure you want to remove: " + Driver.currentGameYard.list[removeInput-1].name);
					String yesOrNo = Driver.getInput("YN");
					if (yesOrNo.contentEquals("Y")) {
						Driver.currentGameYard.removeShipFromShipyard(removeInput);
					}
				}
			}	
		}
	}
	
	// Sort ships by speed, fastest first
	public static void SortCurrentShipyard() {
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
	
	// PRINT MODIFIED LIST OF SHIPS TO SCREEN
	public static void PrintCurrentShipsInGame() {
		System.out.println("     \t\t\t\tHexes  \tH.E.T.\t\tScanner");
		System.out.println("     Ship\tShip\tTurn   Before\tBreak\tSensor\tDist");
		System.out.println("     Name\tSpeed\tMode   Turning\tDown\tLock-On\tMod");
		System.out.println();
		
		LockOnCalculations();    // Roll for Sensor Lock-On and determine Scanner resolution number 
		
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {

			Starship ship = Driver.currentGameYard.list[i-1];
//			boolean hasSSD = ship.hasSSD;
//			Part[] ssd = ship.ssd;
//			Part sensor = ssd[22];
			
			int HexMinToTurn = PhaseCalculation.FindHexMinToTurn(ship.turnMode, ship.speed);
			String HexMin = Integer.toString(HexMinToTurn);
			if (ship.turnMode =="*" ) {
				HexMin = "*";
			}

			String extraSpace = getExtraSpaces(i, 2);
			
			if (ship.name.length() <=3 ) {
				ship.name = ship.name + "   ";
			}
			System.out.print(extraSpace + (i) + ")  " + ship.name);

			getExtraSpaces(i-1, 2);
	        
			// Sets up the display variable for 
			// whether or not ship is locked on 
			String locked = "Yes";
			if(ship.lockedOn == false) {
				if(ship.hasSSD == false) 
					locked = "-";
				else 
					locked = "No";
			}
			int sensorIndex = 0;
			int scannerIndex = 0;
			if (ship.hasSSD == true) {
				sensorIndex = ship.ssd[22].numOfThisPart - ship.ssd[22].remaining;
				scannerIndex = ship.ssd[23].numOfThisPart - ship.ssd[23].remaining;
			}
			
			System.out.print("\t " + extraSpace + ship.speed + "\t " + ship.turnMode + "\t  " 
					+ HexMin + "\t " + ship.breakDown + "\t" + locked);
			
			if(ship.hasSSD) {
				System.out.println("\t" + ship.scannerNums[scannerIndex]);
			}else {
				System.out.println("\t- ");
			}
			if (ship.speed == 0) {
				System.out.print("\t<--- Speed is ZERO");
			}
		}

	}
	
	public static void LockOnCalculations() {
		
		int die = 0;
		/*
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			Starship ship = Driver.currentGameYard.list[i];
			boolean hasSSD = ship.hasSSD;
			Part[] ssd = ship.ssd;
			Part sensor = ssd[22];
			int remainingSensors = sensor.remaining;
			
			if
		}
		*/
		
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			//  SENSOR LOCK-ON OR NOT
//			int indexOfNextSensor = Driver.currentGameYard.list[i].ssd[22].numOfThisPart - Driver.currentGameYard.list[i].ssd[22].remaining;
			
			if(Driver.currentGameYard.list[i].hasSSD && Driver.currentGameYard.list[i].ssd[22].remaining > 0) {
				//number = Driver.currentGameYard.list[i].ssd[22].numOfThisPart - Driver.currentGameYard.list[i].ssd[22].remaining;
				die = DamageAllocation.rollDice(1,6);
				int minNeededRoll = Driver.currentGameYard.list[i].sensorNums[Driver.currentGameYard.list[i].ssd[22].numOfThisPart - Driver.currentGameYard.list[i].ssd[22].remaining];
				Driver.currentGameYard.list[i].lockedOn = false;
				if (die <= minNeededRoll) {
					Driver.currentGameYard.list[i].lockedOn = true;
				}
			}
		}
		
	}

	public static void PrintCurrentShipsInGameThatHaveSSD() {
		System.out.println("     Ship\tShip\t");
		System.out.println("     Name\tSpeed\t");
		System.out.println();
		
		for (int i = 1, print = 1; i <= Driver.currentGameYard.numShips; i++) {

			String extraSpace = getExtraSpaces(i, 2);

			if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
				Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
			}
			
			if(Driver.currentGameYard.list[i-1].hasSSD) {
				System.out.print(extraSpace + (print++) + ")  " + Driver.currentGameYard.list[i-1].name);
				getExtraSpaces(i, 2);
				System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].speed + "\t ");
				System.out.println();
			}
		}
	}
	
	public static String getExtraSpaces (int num, int maxDigits) {

		String extraSpace = "";
		int digitCount = 0;
		
        while(num != 0) {
            num /= 10;
            ++digitCount;
        }
        for (int i = 1; i <= maxDigits-digitCount; i++) {
        	extraSpace = extraSpace + " ";
        }
        return extraSpace;
    } 
}
