import java.util.Random;
import java.util.Scanner;
import java.awt.Color;

public class PhaseCalculation {
	
	public static boolean RolledForLockOnAlready = false;
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void PhaseCalc() {
		
		RolledForLockOnAlready = false;
		
		Driver.TurnNumber++;
		
		if (Driver.TurnNumber == 1) {
			System.out.print("Will Electronic Warfare be used during this game? ");
			String yesOrNo = Driver.getInputNoCancel("YN");
			if (yesOrNo.contentEquals("Y")) {
				Driver.ElectronicWarfare = true;
			}
		}
		
		ShipModifyPreImpulseProcedure();

		//  START IMPULSE PROCEDURE
	
		boolean printAllImpulses = true;
		Driver.numImpulses = 32;
		
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {	
			Driver.currentGameYard.list[i].spi = (double) Driver.currentGameYard.list[i].speed / (double) Driver.numImpulses;			
		}

		PrintImpulseHeader();  // PRINTS FANCY HEADER
		System.out.println();
		
		//  BEGIN ACTUAL IMPULSE MOVEMENT PROCEDURE
		for(int i = 1; i <= Driver.numImpulses; i++) {
			
			int move = 0;
			for(int m = 0; m < Driver.currentGameYard.numShips; m++) {	
				if(Driver.currentGameYard.list[m].distrv + Driver.currentGameYard.list[m].spi >= .999) {
					move = move + 1;
				}
			} 

			if (move > 0 || printAllImpulses == true) {

				if (i < 10) {
					System.out.print("Impulse 0" + i + ":    ");
				} else if (i >= 10) {
					System.out.print("Impulse " + i + ":    ");
				}
			
				for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
					Driver.currentGameYard.list[k].distrv += Driver.currentGameYard.list[k].spi;
	
					if(Driver.currentGameYard.list[k].distrv - 1 >= -.001) {
						Driver.currentGameYard.list[k].distrv--;
						if (Driver.currentGameYard.list[k].cloakOn) {
							System.out.print("(" + Driver.currentGameYard.list[k].name + ")  ");
						} else {
							System.out.print(Driver.currentGameYard.list[k].name + "    ");
						}
					} else {
						for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
							System.out.print(" ");
						}
					System.out.print("    ");
					}
				}

				boolean cont3 = true;
				while (cont3) {
					String userInput = Driver.getInput("WTDSFRAIC");
					if (userInput.contentEquals("")) {
						cont3 = false;
						
					} else if (userInput.equalsIgnoreCase("W")) {
						WeaponsDamage.WeaponsDam(i);
//						cont3 = false;
						System.out.println();
						
					} else if (userInput.equalsIgnoreCase("A")) {						
						System.out.println();
						ShipSetup.PrintCurrentThingsInGame("Ship Monster Other Torpedo Drone Shuttle Fighter", "Speed");
						ModifyShipSpeeds();
						System.out.println();
						PrintImpulseHeader();
						
					} else if (userInput.equalsIgnoreCase("T")) {						
						AddOrRemoveTorpedo();
						
					} else if (userInput.equalsIgnoreCase("C")) {						
						ToggleCloakShip();
						
					} else if (userInput.equalsIgnoreCase("D")) {
						AddDrone();
						PrintImpulseHeader();
						
					} else if (userInput.equalsIgnoreCase("S")) {
						AddShuttle();
						PrintImpulseHeader();
						
					} else if (userInput.equalsIgnoreCase("F")) {
						AddFighter();
						PrintImpulseHeader();
						
					} else if (userInput.equalsIgnoreCase("R")) {                   // Remove destroyed ship/monster
						System.out.println();
						Driver.RemoveShip(true);
						PrintImpulseHeader();
						
					} else if (userInput.equalsIgnoreCase("I")) {  					// Toggle ALL impulse or not
						printAllImpulses = !printAllImpulses;
						if (printAllImpulses) {
							System.out.println();
							System.out.println("[Displaying EVERY impulse]");
						} else {
							System.out.println();
							System.out.println("[Displaying ONLY impulses in which something moves]");
						}
						
//					} else if (userInput.equalsIgnoreCase("A")) {  					// Add ship/monster
//						System.out.print("Add \"Add ship code\" here.");
						
					} 
				}
				
			} else {
				for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
					Driver.currentGameYard.list[k].distrv += Driver.currentGameYard.list[k].spi;
	
					if(Driver.currentGameYard.list[k].distrv - 1 >= -.001) {
						Driver.currentGameYard.list[k].distrv--;
					} 
				}
			}
		} 
		
		//  End of Impulse Procedure Things
		
		System.out.println();
		System.out.print("Are any ships repairing a system(s) with Damage Control? ");
		String yesOrNo = Driver.getInputNoCancel("YN");
		if (yesOrNo.contentEquals("Y")) {
			AdjustForDamageControl();
		}

		System.out.println();
		System.out.print("Are any ships using labs or launching probes for scientific research? ");
		yesOrNo = Driver.getInputNoCancel("YN");
		if (yesOrNo.contentEquals("Y")) {
			MonsterStuff.labResearchPoints();
		}

		boolean monsterInGame = false;
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i-1].race == "Monster") {
				monsterInGame = true;
			}
		}
		
		if (monsterInGame == true) {
			System.out.println();
			System.out.print("Were any monsters in range to do damage to a ship? ");
			yesOrNo = Driver.getInputNoCancel("YN");
			int monsterDamage = 0;
			if (yesOrNo.contentEquals("Y")) {
				monsterDamage = MonsterStuff.MonsterDamage();
				System.out.println();
				if (monsterDamage > 0) {
					DamageAllocation.DamageAlloc(monsterDamage);
				}
			}
		}
		
		int ArastozCount = 0;
		boolean ArastozInGame = false;
//		System.out.println("Driver.currentGameYard.numShips: " + Driver.currentGameYard.numShips);
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
//			System.out.println("i: " + i + "    Driver.currentGameYard.list[i-1].shipType: " + Driver.currentGameYard.list[i-1].shipType);
			if (Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
				ArastozInGame = true;
				ArastozCount++;
			}
//			System.out.println("ArastozInGame: " + ArastozInGame);
//			System.out.println("ArastozCount: " + ArastozCount);
		}

		if (ArastozInGame == true && ArastozCount > 1) {
			MonsterStuff.DidArastozCombine();
		}
		
		if (monsterInGame == true) {			//  Special circumstance that Monster do damage to non-ships
			System.out.println();
			if (Driver.MonsterScenario == 1) {
				System.out.print("Did the Planet Crusher attack the planet? ");
				String yesNo = Driver.getInput("YN");
				int monsterDamage = 0;
				if (yesNo.equalsIgnoreCase("Y")) {
					monsterDamage = MonsterStuff.PlanetCrusher();
					System.out.println();
					MonsterStuff.MonsterAttacksPlanet(monsterDamage);
				}
			} else if (Driver.MonsterScenario == 6) {
				System.out.print("Did the Mind Monster attack the planet? ");
				String yesNo = Driver.getInput("YN");
				int monsterDamage = 0;
				if (yesNo.equalsIgnoreCase("Y")) {
					monsterDamage = MonsterStuff.MindMonsterAttacksPlanet();		//  Calculate mind wipe damage as planet damage
					System.out.println();
					MonsterStuff.MonsterAttacksPlanet(monsterDamage);
				}
			}
		}
	}
	
	public static void ToggleCloakShip() {

		boolean cont = true;
		System.out.println();
		int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");
		
		while (cont) {
			System.out.println();
			System.out.print("Which ship is cloaking/uncloaking? [RETURN to cancel] ");
			int shipNumCloaking = -5;
			
			shipNumCloaking = ShipSetup.GetAdjustedInput(print, "SHIP", "");
//			System.out.println("shipNumCloaking: " + shipNumCloaking);
			
			if(shipNumCloaking >= 0) {
				Driver.currentGameYard.list[shipNumCloaking].cloakOn = !Driver.currentGameYard.list[shipNumCloaking].cloakOn;
				System.out.print(Driver.currentGameYard.list[shipNumCloaking].name + " is now ");
				if(Driver.currentGameYard.list[shipNumCloaking].cloakOn) {
					System.out.print("CLOAKED.\n");
				} else {
					System.out.print("UNcloaked.\n");
				}
			}
			
			if(shipNumCloaking == -1) {
				cont = false;
			}
		}
		
		PhaseCalculation.PrintImpulseHeader();
	}
	
	public static void ShipModifyPreImpulseProcedure() {
	
		boolean cont = true;
		while(cont) {
			System.out.println();
			System.out.println();
			System.out.println("|=================================================================================|");
			System.out.println("|                 PRE-IMPULSE PROCEDURE SHIP MODIFICATION MENU                    |");
			System.out.println("|=================================================================================|");
	
			PrintCurrentShipsInGame();
						
			System.out.println("|=================================================================================|");
			if (Driver.ElectronicWarfare) {
				System.out.println("|   Add Ship from [S]hipyard   [M]odify Speeds   [R]emove Ship   [E]CM/ECCM       |");
			} else {
				System.out.println("|         Add Ship from [S]hipyard    [M]odify Speeds    [R]emove Ship            |");
			}
			System.out.println("|                       RETURN to go to Impulse Procedure                         |"); 
			System.out.println("|=================================================================================|");
	
			String userInput = Driver.getInput("SMRE");
			
			if (userInput.contentEquals("")) {
				//break;
				cont = false;
	
			} else if (userInput.equalsIgnoreCase("S")) {
				Driver.defaultYard.displayShipyardMenu(1);
	
			} else if (userInput.equalsIgnoreCase("M")) {
				ModifyShipSpeeds();
				
			} else if (userInput.equalsIgnoreCase("E")) {
				GetElectronicWarfareValues();
				
			} else if (userInput.equalsIgnoreCase("R")) {
				Driver.RemoveShip(false);
			}
		}
		
		if (Driver.ElectronicWarfare) {
			CheckForAnyElectronicWarfareValues();
		}
	}
	
	public static String PrintSpaces(String name) {
		String spaces = "";
		
		for (int i =1; i <= name.length(); i++) {
			spaces = spaces + " ";
		}
		
		return spaces;
	}
	
	public static void CheckForAnyElectronicWarfareValues() {

		int ElectronicWarfareTotal = 0;
		
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
				ElectronicWarfareTotal = ElectronicWarfareTotal + (int) Driver.currentGameYard.list[i].ECM + (int) Driver.currentGameYard.list[i].ECCM;
			}
		}
		if(ElectronicWarfareTotal == 0) {
			System.out.print("No ships have an ECM/ECCM assigned.  Would you like to change this?");
			String yesOrNo = Driver.getInputNoCancel("YN");
			if (yesOrNo.contentEquals("Y")) {
				GetElectronicWarfareValues();
			}
			System.out.println();
		}
	}
	
	public static void GetElectronicWarfareValues() {

		System.out.println("Type in ECM and ECCM for each ship.  RETURN to go to next ship.  (Current speed)");
		
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
				System.out.println();
				System.out.print(Driver.currentGameYard.list[i].name + " - ECM (" + (int) Driver.currentGameYard.list[i].ECM + "): ");
				int ECMInput = Driver.getNumber(0, 100);
				if (ECMInput >= 0) {
					Driver.currentGameYard.list[i].ECM = ECMInput;
				}
				
				String SpacesOfNameLength = PrintSpaces(Driver.currentGameYard.list[i].name);
				System.out.print(SpacesOfNameLength + "   ECCM (" + (int) Driver.currentGameYard.list[i].ECCM + "): ");
				int ECCMInput = Driver.getNumber(0, 100);
				if (ECCMInput >= 0) {
					Driver.currentGameYard.list[i].ECCM = ECCMInput;
				}
			}
		}
		System.out.println();
	}
	
	public static void ModifyShipSpeeds() {

		System.out.println("Type in new speeds for each ship.  RETURN to go to next ship.  (Current speed)");
		
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			System.out.print("New speed for " + Driver.currentGameYard.list[i].name + " (" + Driver.currentGameYard.list[i].speed + "): ");
			int speedInput = Driver.getNumber(0, 32);
			if (speedInput >= 0) {
				Driver.currentGameYard.list[i].speed = speedInput;
				Driver.currentGameYard.list[i].spi = (double) Driver.currentGameYard.list[i].speed / (double) Driver.numImpulses;			
			}
		}
		
		ShipSetup.SortCurrentShipyard();
	}
	
	public static void PrintCurrentShipsInGame() {
		ShipSetup.SortCurrentShipyard();										// Always SORT ships before printing to screen

		if (Driver.ElectronicWarfare) {
			System.out.println("     \t\t\t\tSensor\tCurrent");
			System.out.println("     Ship Name\t\tSpeed\tLock-On\tScanner\tECM/ECCM");
			System.out.println(" ---------------------------------------------------------------------------------");
		} else {
			System.out.println("     \t\t\t\tSensor\tCurrent");
			System.out.println("     Ship Name\t\tSpeed\tLock-On\tScanner");
			System.out.println(" ---------------------------------------------------------------------------------");
		}

		if (!RolledForLockOnAlready) {
			LockOnCalculations();    // Roll for Sensor Lock-On and determine Scanner resolution number 
		}
		
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {

			Starship ship = Driver.currentGameYard.list[i-1];
			
//			int HexMinToTurn = PhaseCalculation.FindHexMinToTurn(ship.turnMode, ship.speed);
//			String HexMin = Integer.toString(HexMinToTurn);
//			if (ship.turnMode =="*" ) {
//				HexMin = "*";
//			}

			String extraSpace = ShipSetup.getExtraSpaces(i, 2);
			
			String spacer = "";
			if (ship.name.length() < 18) {
				for (int x = 1; x <= 18-ship.name.length(); x++) {
					spacer = spacer + " ";
				}
			}
			System.out.print(extraSpace + (i) + ")  " + ship.name + spacer);
			
			extraSpace = ShipSetup.getExtraSpaces(i-1, 1);
	        
			// Sets up the display variable for 
			// whether or not ship is locked on 
			String locked = "Yes";
			if(ship.lockedOn == false) {
//				if(ship.hasSSD == false) 
//					locked = "-";
//				else 
					locked = "No";
			}
			
			int sensorIndex = 0;
			int scannerIndex = 0;
			if (ship.hasSSD == true) {
				sensorIndex = ship.sensorNums[ship.ssd[22].numOfThisPart - ship.ssd[22].remaining];
				scannerIndex = ship.scannerNums[ship.ssd[23].numOfThisPart - ship.ssd[23].remaining];
			}
			
//			System.out.print("\t " + extraSpace + ship.speed + "\t " + ship.turnMode + "\t  " + HexMin + "\t " + ship.breakDown);
			System.out.print("\t " + extraSpace + ship.speed);
			
			if (ship.kindOfShip == Starship.Ship.STARSHIP) {
				if (ship.ssd[22].remaining == 0) {
					System.out.print("\t" + locked + " (X)");
				} else if (ship.ssd[22].remaining > 0) {
					System.out.print("\t" + locked + " (" + sensorIndex + ")");
				}
			} else {
				System.out.print("\t-----");
			}

			if (ship.kindOfShip == Starship.Ship.STARSHIP) {
				if (ship.ssd[23].remaining == 0) {
					System.out.print("  (X)");
				} else if (ship.ssd[22].remaining > 0) {
					System.out.print("\t  +" + scannerIndex);
				}
			} else {
				System.out.print("\t  --");
			}
			
			if (Driver.ElectronicWarfare && ship.kindOfShip == Starship.Ship.STARSHIP) {
				System.out.print("\t " + (int) ship.ECM + " / " + (int) ship.ECCM + "\t");
			}
			
			if (ship.kindOfShip != Starship.Ship.PLANET && ship.speed == 0) {
				System.out.print("\t<--- Speed is ZERO");
			}
			
			System.out.println();
		}

	}
	
	public static void PrintImpulseHeader() {
		System.out.print("=============");
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.println("=======================");
		
		int shipTotal = 0;
		for (int i = 0; i <= Driver.currentGameYard.numShips - 1; i++) {
			shipTotal = shipTotal + Driver.currentGameYard.list[i].name.length() + 4;
		}

		for (int i = 1; i <= ((18 + shipTotal)-26)/2; i++) {   //  CENTERS TEXT
			System.out.print(" ");
		}
		System.out.println("TURN " + Driver.TurnNumber + " / IMPULSE MOVEMENT PROCEDURE");
		
		System.out.print("-------------");
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("-");
			}
			System.out.print("----");
		}
		System.out.println("-----------------------");

		System.out.print("Ship Name:     ");
	 	for (int i = 0; i <= Driver.currentGameYard.numShips - 1; i++) {
	 		System.out.print(Driver.currentGameYard.list[i].name + "    ");
	 	}
		System.out.println();

//		System.out.print("Min Hex to Turn: ");
//	 	for (int i = 0; i <= Driver.currentGameYard.numShips - 1; i++) {
//	 		int HexMinToTurn = FindHexMinToTurn(Driver.currentGameYard.list[i].turnMode, Driver.currentGameYard.list[i].speed);
//	 		System.out.print(HexMinToTurn + "    ");
//	 		for (int k=1; k <= Driver.currentGameYard.list[i].name.length()-1; k++) {
//	 			System.out.print(" ");
//	 		}
//	 	}
//	 	
//		System.out.println();
		
		System.out.print("=========");
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.println("===========================");
		
		for (int i = 1; i <= ((18 + shipTotal)-29)/2; i++) {     //  CENTERS TEXT
			System.out.print(" ");
		}
		System.out.print("Press RETURN for next Impulse");		
		for (int i = 1; i <= ((17 + shipTotal)-29)/2 - 2; i++) {     //  CENTERS TEXT
			System.out.print(" ");
		}
		System.out.print("[W][T][D][S][F][R][A][I]");		

		System.out.println();
		System.out.print("=============");
		
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.print("=======================");
	}

	public static void LockOnCalculations() {
		
		int die = 0;

		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			
			if(Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {

				if(Driver.currentGameYard.list[i].ssd[22].remaining > 0) {
					die = DamageAllocation.rollDice(1,6);
					int minNeededRoll = Driver.currentGameYard.list[i].sensorNums[Driver.currentGameYard.list[i].ssd[22].numOfThisPart - Driver.currentGameYard.list[i].ssd[22].remaining];
					Driver.currentGameYard.list[i].lockedOn = false;
					if (die <= minNeededRoll) {
						Driver.currentGameYard.list[i].lockedOn = true;
//						System.out.println(Driver.currentGameYard.list[i].name + " sensors at " + minNeededRoll + ".  Lock-On SUCCESS!");
					} else {
						Driver.currentGameYard.list[i].lockedOn = false;
//						System.out.println(Driver.currentGameYard.list[i].name + " sensors at " + minNeededRoll + ".  Lock-on FAILURE!");
					}
					
				} else if (Driver.currentGameYard.list[i].ssd[22].remaining == 0) {
					Driver.currentGameYard.list[i].lockedOn = false;
//					System.out.println(Driver.currentGameYard.list[i].name + " sensors destroyed.  Lock-on FAILURE!");
				}
				
			} else {
				Driver.currentGameYard.list[i].lockedOn = true;
//				System.out.println(Driver.currentGameYard.list[i].name + " sensor lock-on SUCCESS!");
			}
		}
		RolledForLockOnAlready = true;
	}


	// FIND TURN MODE NUMBER BASED ON TURN MODE LETTER
	public static int FindHexMinToTurn(String mode, int speed) {
		int numHexes = 0; 
		if (mode.equalsIgnoreCase("F")) {
			if (speed <= 3) {
				numHexes = 1;
			} else if (speed >= 4 && speed <= 5) {
				numHexes = 2;
			} else if (speed >= 5 && speed <= 9) {
				numHexes = 3;
			} else if (speed >= 10 && speed <= 13) {
				numHexes = 4;
			} else if (speed >= 14 && speed <= 17) {
				numHexes = 5;
			} else if (speed >= 18 && speed <= 23) {
				numHexes = 6;
			} else if (speed >= 24 && speed <= 29) {
				numHexes = 7;
			} else if (speed >= 30) {
				numHexes = 8;
			}
		} else if (mode.equalsIgnoreCase("E")) {
			if (speed <= 3) {
				numHexes = 1;
			} else if (speed >= 4 && speed <= 6) {
				numHexes = 2;
			} else if (speed >= 7 && speed <= 10) {
				numHexes = 3;
			} else if (speed >= 11 && speed <= 14) {
				numHexes = 4;
			} else if (speed >= 15 && speed <= 20) {
				numHexes = 5;
			} else if (speed >= 21 && speed <= 20) {
				numHexes = 6;
			} else if (speed >= 30) {
				numHexes = 7;
			}
		} else if (mode.equalsIgnoreCase("D")) {
			if (speed <= 4) {
				numHexes = 1;
			} else if (speed >= 5 && speed <= 8) {
				numHexes = 2;
			} else if (speed >= 9 && speed <= 12) {
				numHexes = 3;
			} else if (speed >= 13 && speed <= 17) {
				numHexes = 4;
			} else if (speed >= 18 && speed <= 24) {
				numHexes = 5;
			} else if (speed >= 25) {
				numHexes = 6;
			}
		} else if (mode.equalsIgnoreCase("C")) {
			if (speed <= 4) {
				numHexes = 1;
			} else if (speed >= 5 && speed <= 9) {
				numHexes = 2;
			} else if (speed >= 10 && speed <= 14) {
				numHexes = 3;
			} else if (speed >= 15 && speed <= 20) {
				numHexes = 4;
			} else if (speed >= 21 && speed <= 27) {
				numHexes = 5;
			} else if (speed >= 28) {
				numHexes = 6;
			}	
		} else if (mode.equalsIgnoreCase("B")) {
			if (speed <= 5) {
				numHexes = 1;
			} else if (speed >= 6 && speed <= 10) {
				numHexes = 2;
			} else if (speed >= 11 && speed <= 15) {
				numHexes = 3;
			} else if (speed >= 16 && speed <= 21) {
				numHexes = 4;
			} else if (speed >= 22 && speed <= 28) {
				numHexes = 5;
			} else if (speed >= 29) {
				numHexes = 6;
			}
		} else if (mode.equalsIgnoreCase("A")) {
			if (speed <= 6) {
				numHexes = 1;
			} else if (speed >= 7 && speed <= 12) {
				numHexes = 2;
			} else if (speed >= 13 && speed <= 19) {
				numHexes = 3;
			} else if (speed >= 20 && speed <= 26) {
				numHexes = 4;
			} else if (speed >= 27) {
				numHexes = 5;
			}
		} else if (mode.equalsIgnoreCase("AA")) { 
			if (speed <= 8) {
				numHexes = 1;
			} else if (speed >= 9 && speed <= 16) {
				numHexes = 2;
			} else if (speed >= 17 && speed <= 24) {
				numHexes = 3;
			} else if (speed >= 25) {
				numHexes = 4;
			}
		} else if (mode.equalsIgnoreCase("AAA")) {
			if (speed <= 11) {
				numHexes = 1;
			} else if (speed >= 12 && speed <= 23) {
				numHexes = 2;
			} else if (speed >= 24) {
				numHexes = 3;
			}			
		} else if (mode.equalsIgnoreCase("AAAA")) {
			numHexes = 1;
		}
		return numHexes;
	}
	
	// ADD TORPEDO METHOD - ON-THE-FLY
	public static void AddOrRemoveTorpedo() {
		int location = findTorpInCurrGameYard(); 
		if(location != -1) {
			Driver.currentGameYard.removeShipFromShipyard(location + 1);
		}else {
			Starship torp = new Starship(Starship.Ship.TORPEDO, 32, "TORP", "Torpedo");
			Driver.currentGameYard.addShipToShipyard(torp);
		}
	}
	
	public static int findTorpInCurrGameYard() {
		
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			if(Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.TORPEDO) {
				return i;
			}
		}
		
		return -1;
	}

	// ADD DRONE METHOD - ON-THE-FLY
	public static void AddDrone() {		
		System.out.print("\nDrone Name (12 char max) [RETURN to cancel]: ");
		String droneName = keyboard.nextLine();
		droneName = capFirstLetter(droneName);
		
		if (droneName.length() > 0) {
			System.out.println("|==============================================================================|");
			System.out.println("|                              DRONE TYPES: (FD1.0)                            |");
			System.out.println("|------------------------------------------------------------------------------|");
			System.out.println("|         Type     Speed    Endurance   Warhead     Damage     Space     Cost  |");
			System.out.println("|                           (in turns)           (to destroy)                  |");
			System.out.println("|  (0)     IS       12          1          8          3         1/2       0    |");
			System.out.println("|  (1)     I         8          3         12          4          1        0    |");
			System.out.println("|  (2)     II       12          2         12          4          1       1/2   |");
			System.out.println("|  (3)     III      12         25         12          4          1        1    |");
			System.out.println("|  (4)     IV        8          3         24          6          2        0    |");
			System.out.println("|  (5)     I        12          2         24          6          2       1/2   |");
			System.out.println("|==============================================================================|");
			System.out.print("Drone Type [RETURN to cancel]: ");
			int droneType = Driver.getNumber(0, 5);
//			if (droneType == 0) {
//				droneName = droneName + " (IS)";  
//			} else if (droneType == 1) {
//				droneName = droneName + " (I)";
//			} else if (droneType == 2) {
//				droneName = droneName + " (II)";
//			} else if (droneType == 3) {
//				droneName = droneName + " (III)";
//			} else if (droneType == 4) {
//				droneName = droneName + " (IV)";
//			} else if (droneType == 5) {
//				droneName = droneName + " (V)";
//			}
			int droneSpeed = 0;
	
			if (droneType > -1) {
				if (droneType == 1 || droneType == 4) {
					System.out.print("Drone Speed: 8");
					droneSpeed = 8;
				} else {
					System.out.print("Drone Speed: 12");
					droneSpeed = 12;
				}
				System.out.println();
				Starship drone = new Starship(Starship.Ship.DRONE, droneSpeed, droneName, "Drone");
				Driver.currentGameYard.addShipToShipyard(drone);
			}
		}
	}

	// ADD SHUTTLE METHOD - ON-THE-FLY	
	public static void AddShuttle() {	
		System.out.print("\nShuttle Name (16 char max) [RETURN to cancel]: ");
		String shuttleName = keyboard.nextLine();

		if (shuttleName.length() > 0) {
			shuttleName = capFirstLetter(shuttleName);
			System.out.print("Shuttle Speed: ");
			int shuttleSpeed = Driver.getNumber(-1, 6);
			
			Starship shuttle = new Starship(Starship.Ship.SHUTTLE, shuttleSpeed, shuttleName, "Shuttle");
			Driver.currentGameYard.addShipToShipyard(shuttle);
		}
	}

	// ADD FIGHTER METHOD - ON-THE-FLY
	public static void AddFighter() {		
		System.out.print("\nFighter Name (16 char max) [RETURN to cancel]: ");
		String fighterName = keyboard.nextLine();
		
		if (fighterName.length() > 0) {
			fighterName = capFirstLetter(fighterName);
			System.out.print("Fighter Speed: ");
			int fighterSpeed = Driver.getNumber(-1, 32);
			
			Starship fighter = new Starship(Starship.Ship.FIGHTER, fighterSpeed, fighterName, "Fighter");
			Driver.currentGameYard.addShipToShipyard(fighter);
		}
	}

	public static void AdjustForDamageControl() {

		boolean cont = true;
		
		System.out.println();
		System.out.println("|===============================================================================================|");
		System.out.println("|                                DAMAGE CONTROL REPAIR COSTS:                                   |");
		System.out.println("|===============================================================================================|");
		System.out.println("|   System                Repair   |   System            Repair  |   System            Repair   |");
		System.out.println("|   Name                    Cost   |   Name                Cost  |   Name                Cost   |");
		System.out.println("|----------------------------------+-----------------------------+------------------------------|");
		System.out.println("|   ADD                        3   |   Hellbore             15   |   Repair Box             6   |");
		System.out.println("|   APR                        4   |   Hull (Front/Aft)      1   |   Security               6   |");
		System.out.println("|   Aux Control                6   |   Impulse               5   |   Sensor                10   |");
		System.out.println("|   Battery                    2   |   Lab                   5   |   Scanner               10   |");
		System.out.println("|   Bridge                     6   |   Mine Rack             4   |   Shield                 2   |");
		System.out.println("|   Cargo                      1   |   PA Panel              5   |   Shuttle Bay            2   |");
		System.out.println("|   Damage Control (per pt)    3   |   Phaser-I              5   |   Stasis Field Gen      20   |");
		System.out.println("|   Displacement Device       25   |         -II             4   |   Special Sensors       15   |");
		System.out.println("|   Disruptor: range 40       10   |         -III            2   |   Tractor Beam           3   |");
		System.out.println("|              range 30        8   |         -IV            10   |   Tractor-Repulsor       5   |");
		System.out.println("|              range 22        7   |   Phaser-G              6   |   Transporter            3   |");
		System.out.println("|              range 15        5   |   Photon Torpedo        8   |   Ubitron Interface      4   |");
		System.out.println("|              range 10        4   |   Plasma-F              5   |   Warp Engine           10   |");
		System.out.println("|   Drone Rack                 3   |         -G             10   |     (Left/Center/Right)      |");
		System.out.println("|   Emergency Bridge           6   |         -R             20   |   Warp Reactor           6   |");
		System.out.println("|   Expanding Sphere Gen      15   |         -S             15   |   Web Generator          6   |");
		System.out.println("|   Excess Damage            N/A   |   Plasmatic Pulsar     15   |   Web Caster            15   |");
		System.out.println("|   Flag Bridge                6   |   Probe                 3   |                              |");
		System.out.println("|   Fusion Beam                6   |                             |                              |");
		System.out.println("|                                  |                             |                              |");
		System.out.println("|===============================================================================================|");
		System.out.println();

		while (cont) {
			int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");
//			System.out.println("print: " + print);
//			System.out.println();
			
			int shipNumToRepair = -1;
			
			System.out.print("Which ship to repair damage? [RETURN for cancel] ");
			shipNumToRepair = ShipSetup.GetAdjustedInput(print, "SHIP", "");
//			System.out.println("shipNumToRepair: " + shipNumToRepair);

			if (shipNumToRepair >= 0) {
				int damagedSystems = 0;

				for (int part = 0; part <=24; part++) {
					if (Driver.currentGameYard.list[shipNumToRepair].ssd[part].numOfThisPart > Driver.currentGameYard.list[shipNumToRepair].ssd[part].remaining) {
						damagedSystems++;
					}
				}
				
				int quantity = 1;
				if (damagedSystems == 0) {
					System.out.println();
					System.out.println(Driver.currentGameYard.list[shipNumToRepair].name + " has NO damaged systems.");
					System.out.println();
					quantity = -1;
				}

				if (damagedSystems > 0) {
					System.out.println();
					System.out.println(Driver.currentGameYard.list[shipNumToRepair].name + " has these damaged systems (Remaining/Max):");
					for (int part = 0; part <=23; part++) {
						int lineLength = 0;
						if (Driver.currentGameYard.list[shipNumToRepair].ssd[part].numOfThisPart > Driver.currentGameYard.list[shipNumToRepair].ssd[part].remaining) {
							String extraSpaces = ShipSetup.getExtraSpaces(part+1, 2);
							System.out.print("   " + extraSpaces +  (part+1) + ". " + Driver.currentGameYard.list[shipNumToRepair].ssd[part].name + " (" + Driver.currentGameYard.list[shipNumToRepair].ssd[part].remaining + "/" + Driver.currentGameYard.list[shipNumToRepair].ssd[part].numOfThisPart + ")");
							
							lineLength = FindLength(Driver.currentGameYard.list[shipNumToRepair].ssd[part].name, Driver.currentGameYard.list[shipNumToRepair].ssd[part].numOfThisPart, Driver.currentGameYard.list[shipNumToRepair].ssd[part].remaining);
							String tabs = "";
							if (lineLength <= 16) {
								tabs = "\t\t";
							} else {
								tabs = "\t";
							}
							
							if (part+1 == 2) {
								System.out.println(tabs + "<-- Security, Wed Generator, Displacement Device");
							} else if (part+1 == 6) {
								System.out.println(tabs + "<-- Disruptor Bolt, Plasma Torpedo, Tractor-Repulsor Beam, Fusion Beam, SFG");
							} else if (part+1 == 16) {
								System.out.println(tabs + "<-- Fighter, Mine Rack, Andromedan Hanger");
							} else if (part+1 == 17) {
								System.out.println(tabs + "<-- ADD, Hellbore, ESG, Power Absorbers, Plasmatic Pulsar, Web Caster");
							} else if (part+1 == 19) {
								System.out.println(tabs + "<-- Repair, Mine Rack");
							} else if (part+1 == 20) {
								System.out.println(tabs + "<-- Repair");
							} else if (part+1 == 21) {
								System.out.println(tabs + "<-- Repair");
							} else {
								System.out.println();
							}
							damagedSystems++;
						}
					}
				}
				
				while (quantity > 0) {
					System.out.print(Driver.currentGameYard.list[shipNumToRepair].name + ": Which system to repair? [RETURN to cancel] ");
					int systemToRepair = Driver.getNumber(1,24);
					
					if (systemToRepair > 0) {
						if (Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].numOfThisPart == Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].remaining) {
							System.out.println("You CANNOT repair system beyond their original max number of SSD boxes.");
							System.out.println();
						} else if (systemToRepair >= 1 && systemToRepair <= 24) {
							System.out.print(Driver.currentGameYard.list[shipNumToRepair].name + ": How many " + Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].name.toUpperCase() + " systems to repair? ");
							quantity = Driver.getNumberNoCancel(0,10);
							
							if (quantity > 0) {
								if (Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].remaining + quantity > Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].numOfThisPart) {
									System.out.println("You CANNOT repair system beyond their original max number of SSD boxes.");
									System.out.println();
								} else {
									Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].remaining = Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].remaining + quantity;
									System.out.println(Driver.currentGameYard.list[shipNumToRepair].name + ": " + Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].name + " now at " + Driver.currentGameYard.list[shipNumToRepair].ssd[systemToRepair-1].remaining + " boxes");
									System.out.println();
								}
							} else {
								System.out.println();
								quantity = -1;
							}
							
							if(Driver.TESTING) {
								System.out.println();
								for (int i = 0; i <= 24; i++) {
									System.out.print(Driver.currentGameYard.list[shipNumToRepair].ssd[i].name.charAt(0) + ":" + Driver.currentGameYard.list[shipNumToRepair].ssd[i].remaining + " ");
								}
								System.out.println();
							}
						} else {
							System.out.println();
							quantity = -1;
						}
					} else {
						System.out.println();
						break;
					}
					
				}
			}
			if (shipNumToRepair == -1) {
				cont = false;
			}
		}
	}

	public static int FindLength(String name, int max, int remain) {
		int lineLength = 0;
		int digitCountMax = 0;
		int digitCountRemain = 0;
		
		if (max == 0) {
			digitCountMax++;
		}
		
		if (remain == 0) {
			digitCountRemain++;
		}
		
        while(max != 0) {
        	max /= 10;
        	digitCountMax++;
        	}

        while(remain != 0) {
        	remain /= 10;
        	digitCountRemain++;
        	}

        lineLength = name.length() + digitCountMax + digitCountRemain + 4;  //  +4 is for _(/)
	
		return lineLength;
	}
	
	public static String capFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
}
