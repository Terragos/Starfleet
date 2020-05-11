import java.util.Scanner;

public class ShipSetup {

//	public static void ShipSetupOrModify(String goToImpProc) {
//		
//		Scanner keyboard = new Scanner(System.in);
//		boolean cont = true;
//		
//		while(cont) {
//			System.out.println();
//			System.out.println();
//			System.out.println("|==========================================================================|");
//			System.out.println("|                         SHIP MODIFICATION MENU                           |");
//
//			if (Driver.currentGameYard.numShips > 0) {
//				System.out.println("|==========================================================================|");
//				System.out.println("|                  Current ship, object and monster list:                  |");
//				System.out.println("|==========================================================================|");
//				System.out.println();
//
//				PrintCurrentThingsInGame("Ship Monster Other", "Speed");
//				
//				System.out.println();
//			}
//						
//			System.out.println("|==========================================================================|");
//			System.out.println("|       Add Ship from [S]hipyard    [M]odify Speeds    [R]emove Ship       |");
//			if (goToImpProc == "Y") { 																 	 // Print this line only if coming
//				System.out.println("|                    RETURN to go to Impulse Procedure                     |");   // from the Impulse Procedure 
//			} else if (goToImpProc == "N") {
//				System.out.println("|                      RETURN to return to Main Menu                       |");   // Print this line only if coming 
//			}																						 	 // from the Main Menu
//			System.out.println("|==========================================================================|");
//
//			String userInput = Driver.getInput("SMR");
//
//			if (userInput.contentEquals("")) {
//				//break;
//				cont = false;
//
//			} else if (userInput.equalsIgnoreCase("S")) {
//				Driver.defaultYard.displayShipyardMenu(1);
//
//			} else if (userInput.equalsIgnoreCase("M")) {
//				boolean cont2 = true;
//				while (cont2) {
//					
//					System.out.print("Modify which ship's speed? [RETURN to cancel]");
//				
//					int modifyInput = Driver.getNumber(1, Driver.currentGameYard.numShips);	
//
//					if(modifyInput == -1) {
//						cont2 = false;
//						//break;
//					} else {
//						System.out.print("Ship " + Driver.currentGameYard.list[modifyInput-1].name + "'s NEW Speed: ");
//						int speedInput = Driver.getNumberNoCancel(0, 32);
//						Driver.currentGameYard.list[modifyInput-1].speed = speedInput;
//						System.out.println();
//					}
//				}
//			} else if (userInput.equalsIgnoreCase("R")) {
//				System.out.print("Remove which ship? [RETURN to cancel] ");
//				int removeInput = -1;
//
//				removeInput = Driver.getNumber(1, Driver.currentGameYard.numShips);				//  Get a new input
//				if (removeInput > 0) {
//					System.out.print("Are you sure you want to remove: " + Driver.currentGameYard.list[removeInput-1].name);
//					String yesOrNo = Driver.getInput("YN");
//					if (yesOrNo.contentEquals("Y")) {
//						Driver.currentGameYard.removeShipFromShipyard(removeInput);
//					}
//				}
//			}	
//		}
//	}
	
	public static void SortCurrentShipyard() {			// Sort ships by speed, fastest first
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
	
	public static void PrintCurrentShipsInGame() {
		ShipSetup.SortCurrentShipyard();														// Always SORT ships before printing to screen

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("     \t\t\t\t\tHexes  \tH.E.T.\t");
		System.out.println("     \t\t\t\tTurn   Before\tBreak\tSensor\tScanner");
		System.out.println("     Ship Name\t\tSpeed\tMode   Turning\tDown\tLock-On\tDist Mod");
		System.out.println("--------------------------------------------------------------------------");
		
		LockOnCalculations();    // Roll for Sensor Lock-On and determine Scanner resolution number 
		
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {

			Starship ship = Driver.currentGameYard.list[i-1];
			
			int HexMinToTurn = PhaseCalculation.FindHexMinToTurn(ship.turnMode, ship.speed);
			String HexMin = Integer.toString(HexMinToTurn);
			if (ship.turnMode =="*" ) {
				HexMin = "*";
			}

			String extraSpace = getExtraSpaces(i, 2);
			
			String spacer = "";
			if (ship.name.length() < 18) {
				for (int x = 1; x <= 18-ship.name.length(); x++) {
					spacer = spacer + " ";
				}
			}
			System.out.print(extraSpace + (i) + ")  " + ship.name + spacer);
			
			extraSpace = getExtraSpaces(i-1, 1);
	        
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
				System.out.print("\t" + ship.scannerNums[scannerIndex]);
			}else {
				System.out.print("\t- ");
			}
			if (ship.speed == 0) {
				System.out.print("\t<--- Speed is ZERO");
			}
			System.out.println();
		}

	}
	
//	public static void PrintCurrentMoving ThingsInGameNameOnly(String whatToPrint, String whatAspect) {		//  whatToPrint = "Ships", "Monsters", and/or "Other" 
//
//		whatToPrint = whatToPrint.toUpperCase();
//		
//		ShipSetup.SortCurrentShipyard();
//
//		System.out.println("---------------------------------");
//		System.out.println("     Name");
//		System.out.println("---------------------------------");
//		
//		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
//
//			Starship ship = Driver.currentGameYard.list[i-1];
//			
//			String extraSpace = getExtraSpaces(i, 2);
//			
//			if (whatToPrint.toUpperCase().contains("SHIP")) {
//				if (ship.race != "Monster" && ship.race != "Other") {
//					System.out.print(extraSpace + (i) + ")  " + ship.name);
//					if (whatAspect.toUpperCase().contains("speed"))
//						System.out.print("\t" + ship.speed);
//					else if (whatAspect.toUpperCase().contains("BPV"))
//						System.out.print("\t" + ship.BPV);
//				}
//			}
//				
//			if (whatToPrint.toUpperCase().contains("MONSTER")) {
//				if (ship.race == "Monster") {
//					System.out.println(extraSpace + (i) + ")  " + ship.name);
//					if (whatAspect.toUpperCase().contains("speed"))
//						System.out.print("\t" + ship.speed);
//					else if (whatAspect.toUpperCase().contains("Health"))
//						System.out.print("\t" + ship.ssd[24].remaining);
//				}
//			}
//				
//			if (whatToPrint.toUpperCase().contains("OTHER")) {
//				if (ship.race == "Other") {
//					System.out.println(extraSpace + (i) + ")  " + ship.name);
//					if (whatAspect.toUpperCase().contains("speed"))
//						System.out.print("\t" + ship.speed);
//					else if (whatAspect.toUpperCase().contains("Health"))
//						System.out.print("\t" + ship.ssd[24].remaining);
//				}
//			}
//		}
//	}
	
	public static int GetAdjustedInput(int printSpecific, String whatToPrint, String whatAspect1) {
		int inputNum = Driver.getNumber(0, printSpecific);
		
		whatToPrint = whatToPrint.toUpperCase();
		
		int k = 0;
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			
			if (whatToPrint.contains("SHIP") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
				if(k + 1 == inputNum) {
					return i;
				}else {
					k++;
				}
			}
			
			if (whatToPrint.contains("MONSTER") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) { 
				if(k + 1 == inputNum) {
					return i;
				}else {
					k++;
				}
			}
			
			if (whatToPrint.contains("OTHER") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.PLANET) { 
				if(k + 1 == inputNum) { 
					return i;
				} else {
					k++;
				}
			}			
		}
		return -1;
	}
	
	public static int PrintCurrentThingsInGame(String whatToPrint, String whatAspect1 /*, String whatAspect2 */) { 

		whatToPrint = whatToPrint.toUpperCase();
		whatAspect1 = whatAspect1.toUpperCase();
//		whatAspect2 = whatAspect2.toUpperCase();
		
		ShipSetup.SortCurrentShipyard();

		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		System.out.println("     NAME\t\t" + whatAspect1);
//		System.out.println("     Name\t" + whatAspect1 + "\t" + whatAspect2);
		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		
		int printNum = 0;
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {

			Starship ship = Driver.currentGameYard.list[i-1];
			
			String extraSpace = getExtraSpaces(i, 2);
			String extraTab = "";
			if (ship.name.length() < 11 ) {
				extraTab = "\t";
			}
			
			if (whatToPrint.contains("SHIP")) {
				if (ship.kindOfShip == Starship.Ship.STARSHIP) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);

					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
						if (ship.speed == 0) {
							System.out.print("\t  <--- SPEED IS ZERO!");
						}
					} else if (whatAspect1.contains("BPV"))
						System.out.print("\t" + ship.BPV);
					
					System.out.println();
				}
			}
				
			if (whatToPrint.contains("MONSTER")) {
				if (ship.kindOfShip == Starship.Ship.MONSTER) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
						if (ship.speed == 0) {
							System.out.print("\t  <--- SPEED IS ZERO!");
						}
					} else if (whatAspect1.contains("HEALTH"))
						System.out.print("\t" + ship.ssd[24].remaining);

					System.out.println();
				}
			}
				
			if (whatToPrint.contains("OTHER")) {
				if (ship.kindOfShip == Starship.Ship.PLANET) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
					} else if (whatAspect1.contains("HEALTH"))
						System.out.print("\t" + ship.ssd[24].remaining);

					System.out.println();
				}
			}
		}
		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		return printNum;
	}
	
//	public static void PrintCurrentMonstersInGameNameAndHealth() { 
//
//		ShipSetup.SortCurrentShipyard();
//
//		System.out.println("---------------------------------");
//		System.out.println("     Name\t\tHealth");
//		System.out.println("---------------------------------");
//		
//		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
//
//			Starship ship = Driver.currentGameYard.list[i-1];
//			
//			String extraSpace = getExtraSpaces(i, 2);
//			
//			System.out.print(extraSpace + (i) + ")  " + ship.name + "     ");
//			getExtraSpaces(i, 2);
//			System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].ssd[24].remaining);
//			System.out.println();
//		}
//	}
	
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

//	public static void PrintCurrentNonMonstersInGameThatHaveSSD() {
//		ShipSetup.SortCurrentShipyard();
//		System.out.println("     Ship\tShip\t");
//		System.out.println("     Name\tSpeed\t");
//		System.out.println();
//		
//		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
//
//			String extraSpace = getExtraSpaces(i, 2);
//
//			if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
//				Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
//			}
//			
//			if (Driver.currentGameYard.list[i-1].hasSSD && Driver.currentGameYard.list[i-1].race != "Monster" && Driver.currentGameYard.list[i-1].race != "Other") {
//				System.out.print(extraSpace + i + ")  " + Driver.currentGameYard.list[i-1].name);
//				getExtraSpaces(i, 2);
//				System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].speed + "\t ");
//				System.out.println();
//			}
//		}
//	}
//	
//	public static void PrintCurrentShipsInGameThatHaveSSD() {
//		ShipSetup.SortCurrentShipyard();														// Always SORT ships before printing to screen
//
//		System.out.println("     Ship\tShip\t");
//		System.out.println("     Name\tSpeed\t");
//		System.out.println();
//		
//		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
//
//			String extraSpace = getExtraSpaces(i, 2);
//
//			if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
//				Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
//			}
//			
//			if(Driver.currentGameYard.list[i-1].hasSSD) {
//				System.out.print(extraSpace + i + ")  " + Driver.currentGameYard.list[i-1].name);
//				getExtraSpaces(i, 2);
//				System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].speed + "\t ");
//				System.out.println();
//			}
//		}
//	}
	
	public static String getExtraSpaces (int num, int maxDigits) {

		String extraSpace = "";
		int digitCount = 0;
		
        if (num == 0) {
        	digitCount = 1;
        } else {
        	while(num != 0) {
        		num /= 10;
        		++digitCount;
        	}
        }
        for (int i = 1; i <= maxDigits-digitCount; i++) {
        	extraSpace = extraSpace + " ";
        }
        return extraSpace;
    } 
}

//	public static void PrintCurrentSpecificRaceInGame(String printWhichRace, String printWhichShipType) {
//		System.out.println("     Name\tSpeed\t");
//		System.out.println();
//
//		if (printWhichRace.equalsIgnoreCase("ALL")) {
//			for (int i = 1, print = 1; i <= Driver.currentGameYard.numShips; i++) {
//	
//				String extraSpace = getExtraSpaces(i, 2);
//	
//				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
//					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
//				}
//				
//				if(Driver.currentGameYard.list[i-1].hasSSD) {
//					System.out.print(extraSpace + (print++) + ")  " + Driver.currentGameYard.list[i-1].name);
//					getExtraSpaces(i, 2);
//					System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].speed + "\t ");
//					System.out.println();
//				}
//			}
//		} else  {
//			for (int i = 1, print = 1; i <= Driver.currentGameYard.numShips; i++) {
//				
//				String extraSpace = getExtraSpaces(i, 2);
//	
//				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
//					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
//				}
//				
//				if(Driver.currentGameYard.list[i-1].race.equalsIgnoreCase(printWhichRace)) {
//					System.out.print(extraSpace + (print++) + ")  " + Driver.currentGameYard.list[i-1].name);
//					getExtraSpaces(i, 2);
//					System.out.print("\t " + extraSpace + Driver.currentGameYard.list[i-1].speed + "\t ");
//					System.out.println();
//				}
//			}
//		}
//	}
