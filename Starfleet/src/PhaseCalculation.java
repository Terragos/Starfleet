import java.util.Random;
import java.util.Scanner;
import java.awt.Color;

public class PhaseCalculation {
	
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void PhaseCalc() {

		System.out.println();
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|                       IMPULSE MOVEMENT PRCOEDURE                         |");
		System.out.println("|==========================================================================|");
		System.out.println("|                  Current ship, object and monster list:                  |");
		System.out.println("|==========================================================================|");
		System.out.println();
		
		ShipSetup.SortCurrentShipyard();															// Always SORT ships before printing to screen

		ShipSetup.PrintCurrentShipsInGame();
		
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|             Do you need to modify the ship list in any way?              |");
		System.out.println("|                 RETURN to continue to Impulse Procedure                  |");
		System.out.println("|==========================================================================|");
		System.out.println();
		
		String doModify = Driver.getInput("YN");         //  ALLOW USER TO MODIFY CURRENT SHIPS IF NEEDED	

		if (doModify.equalsIgnoreCase("Y")) {		
			ShipSetup.ShipSetupOrModify("Y");         // Pass "Y" to go on to Impulse Procedure ("n" to NOT go on...)
		}
		
		//  START IMPULSE PROCEDURE
	
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

			if (move > 0) {

				if (i < 10) {
					System.out.print("Impulse 0" + i + ":    ");
				} else if (i >= 10) {
					System.out.print("Impulse " + i + ":    ");
				}
			
				for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
					Driver.currentGameYard.list[k].distrv += Driver.currentGameYard.list[k].spi;
	
					if(Driver.currentGameYard.list[k].distrv - 1 >= -.001) {
						Driver.currentGameYard.list[k].distrv--;
						System.out.print(Driver.currentGameYard.list[k].name + "    ");
					} else {
						for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
							System.out.print(" ");
						}
					System.out.print("    ");
					}
				}

				boolean cont3 = true;
				while (cont3) {
					String userInput = Driver.getInput("WTDSFRA");
					if (userInput.contentEquals("")) {
						cont3 = false;
					} else if (userInput.equalsIgnoreCase("W")) {
						WeaponsDamage.WeaponsDam(i);
						cont3 = false;
						System.out.println();
					} else if (userInput.equalsIgnoreCase("T")) {						
						AddOrRemoveTorpedo();
						//  NEED CODE TO EMPTY OUT KEYBOARD BUFFER  ???
					} else if (userInput.equalsIgnoreCase("D")) {
						AddDrone();
					} else if (userInput.equalsIgnoreCase("S")) {
						AddShuttle();
					} else if (userInput.equalsIgnoreCase("F")) {
						AddFighter();
					} else if (userInput.equalsIgnoreCase("R")) {                   // Remove destroyed ship/monster
						System.out.println();
						ShipSetup.PrintCurrentShipsInGame();
						System.out.print("Remove which ship? [RETURN to cancel] ");
						int removeInput = -1;
						removeInput = Driver.getNumber(1, Driver.currentGameYard.numShips);
						if (removeInput > 0) {
							System.out.print("Are you sure you want to remove: " + Driver.currentGameYard.list[removeInput-1].name);
							String yesOrNo = Driver.getInput("YN");
							if (yesOrNo.contentEquals("Y")) {
								Driver.currentGameYard.removeShipFromShipyard(removeInput);
							}
						}
					} else if (userInput.equalsIgnoreCase("A")) {  					// Add ship/monster
						System.out.print("Add \"Add ship code\" here.");
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
		
		System.out.println();
		System.out.print("Are any ships repairing system with Damage Control? ");
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
			System.out.print("Were any monsters in range to do damage? ");
			yesOrNo = Driver.getInputNoCancel("YN");
			int monsterDamage = 0;
			if (yesOrNo.contentEquals("Y")) {
				
				monsterDamage = MonsterStuff.MonsterDamage();
				System.out.println();
				DamageAllocation.DamageAlloc(monsterDamage);
			}
		}
		
		boolean ArastozInGame = false;
		
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
				ArastozInGame = true;
				break;
			}
		}

		if (ArastozInGame == true) {
			MonsterStuff.DidArastozCombine();
		}
	}
	
	public static void AdjustForDamageControl() {

		boolean cont = true;
		
		System.out.println();
		System.out.println();
		System.out.println("|===============================================================================================|");
		System.out.println("|                             END OF TURN DAMAGE CONTROL REPAIR:                                |");
		System.out.println("|===============================================================================================|");
		System.out.println("| SSD  System              Repair  | SSD  System         Repair  | SSD  System          Repair  |");
		System.out.println("|  ##  Name                  Cost  |  ##  Name             Cost  |  ##  Name              Cost  |");
		System.out.println("|----------------------------------+-----------------------------+------------------------------|");
		System.out.println("|  17. ADD                      3  |  17. Hellbore           15  |  19. Repair Box (Cargo)   6  |");
		System.out.println("|  11. APR                      4  |  21. Hull, Aft           1  |  20. Repair Box (F Hull)  6  |");
		System.out.println("|   4. Aux Control              6  |  20. Hull, Front         1  |  21. Repair Box (A Hull)  6  |");
		System.out.println("|  12. Battery                  2  |  10. Impulse             5  |   2. Security             6  |");
		System.out.println("|   1. Bridge                   6  |  18. Lab                 5  |  23. Sensor              10  |");
		System.out.println("|  19. Cargo                    1  |  19. Mine Rack (Cargo)   4  |  24. Scanner             10  |");
		System.out.println("|  22. Damage Control (per pt)  3  |  16. Mine Rack (Shuttle) 4  |  --  Shield               2  |");
		System.out.println("|   2. Displacement Device     25  |  17. PA Panel            5  |  16. Shuttle Bay          2  |");
		System.out.println("|   6. Disruptor: range 40     10  |   5. Phaser-I            5  |   6. Stasis Field Gen    20  |");
		System.out.println("|   6. Disruptor: range 30      8  |   5. Phaser-II           4  |  ??  Special Sensors     15  |");
		System.out.println("|   6. Disruptor: range 22      7  |   5. Phaser-III          2  |  14. Tractor Beam         3  |");
		System.out.println("|   6. Disruptor: range 15      5  |   5. Phaser-IV          10  |   6. Tractor-Repulsor     5  |");
		System.out.println("|   6. Disruptor: range 10      4  |   6. Phaser-G            6  |  13. Transporter          3  |");
		System.out.println("|  17. Drone Rack               3  |   6. Photon Torpedo      8  |  ??  Ubitron Interface    4  |");
		System.out.println("|   3. Emergency Bridge         6  |   6. Plasma-F            5  |   7. Warp Engine, Center 10  |");
		System.out.println("|  17. Expanding Sphere Gen    15  |   6. Plasma-G           10  |   8. Warp Engine, Left   10  |");
		System.out.println("|  --  Excess Damage          N/A  |   6. Plasma-R           20  |   9. Warp Engine, Right  10  |");
		System.out.println("|   2. Flag Bridge              6  |   6. Plasma-S           15  |   7. Warp Reactor         6  |");
		System.out.println("|   6. Fusion Beam              6  |  17. Plasmatic Pulsar   15  |   2. Web                  6  |");
		System.out.println("|                                  |  15. Probe               3  |  ??  Web Caster          15  |");
		System.out.println("|===============================================================================================|");
		System.out.println();

		ShipSetup.PrintCurrentShipsInGame();

		int shipNumToRepair = -1;

		while (cont) {
			System.out.print("Which ship to repair damage? [RETURN for cancel] ");
			shipNumToRepair = Driver.getNumber(0, Driver.currentGameYard.numStarships);

			if(shipNumToRepair > 0) {
				System.out.print("Which system to repair? ");
				int systemToRepair = Driver.getNumberNoCancel(1,24);
				
				if (systemToRepair >= 1 && systemToRepair <= 24) {
					System.out.print("How many of that system to repair? ");
					int quantity = Driver.getNumberNoCancel(0,10);

					if (quantity >= 0) {
						Driver.currentGameYard.list[shipNumToRepair-1].ssd[systemToRepair-1].remaining = Driver.currentGameYard.list[shipNumToRepair-1].ssd[systemToRepair-1].remaining + quantity;
						System.out.println();
					}
					
					if(Driver.TESTING) {
						System.out.println();
						for (int i = 0; i <= 24; i++) {
							System.out.print(Driver.currentGameYard.list[shipNumToRepair-1].ssd[i].name.charAt(0) + ":" + Driver.currentGameYard.list[shipNumToRepair-1].ssd[i].remaining + " ");
						}
						System.out.println();
					}
				}
			}
			if (shipNumToRepair == -1) {
				cont = false;
			}
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
		System.out.println("IMPULSE MOVEMENT PROCEDURE");
		
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

		System.out.print("Min Hex to Turn: ");
	 	for (int i = 0; i <= Driver.currentGameYard.numShips - 1; i++) {
	 		int HexMinToTurn = FindHexMinToTurn(Driver.currentGameYard.list[i].turnMode, Driver.currentGameYard.list[i].speed);
	 		System.out.print(HexMinToTurn + "    ");
	 		for (int k=1; k <= Driver.currentGameYard.list[i].name.length()-1; k++) {
	 			System.out.print(" ");
	 		}
	 	}
	 	
		System.out.println();
		
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
		System.out.print("[W][T][D][S][F][R][A]");		

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
	
	
	// ROLL DICE METHOD
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
		} else if (mode.equalsIgnoreCase("Y")) {
			if (speed <= 11) {
				numHexes = 1;
			} else if (speed >= 12 && speed <= 23) {
				numHexes = 2;
			} else if (speed >= 24) {
				numHexes = 3;
			}			
		} else if (mode.equalsIgnoreCase("X")) {
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
			Starship torp = new Starship(Starship.Ship.TORPEDO, 32, "TORP");
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
		System.out.print("Drone Name : ");
		String droneName = keyboard.nextLine();
		
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
		if (droneType == 0) {
			droneName = droneName + " (IS)";  
		} else if (droneType == 1) {
			droneName = droneName + " (I)";
		} else if (droneType == 2) {
			droneName = droneName + " (II)";
		} else if (droneType == 3) {
			droneName = droneName + " (III)";
		} else if (droneType == 4) {
			droneName = droneName + " (IV)";
		} else if (droneType == 5) {
			droneName = droneName + " (V)";
		}
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
			Starship drone = new Starship(Starship.Ship.DRONE, droneSpeed, droneName);
			Driver.currentGameYard.addShipToShipyard(drone);
		}
	}

	// ADD SHUTTLE METHOD - ON-THE-FLY	
	public static void AddShuttle() {	
		System.out.print("Shuttle Name [RETURN to cancel]: ");
		String shuttleName = keyboard.nextLine();

		if (shuttleName.length() > 0) {
			System.out.print("Shuttle Speed: ");
			int shuttleSpeed = Driver.getNumber(-1, 6);
			
			Starship shuttle = new Starship(Starship.Ship.SHUTTLE, shuttleSpeed, shuttleName);
			Driver.currentGameYard.addShipToShipyard(shuttle);
		}
	}

	// ADD FIGHTER METHOD - ON-THE-FLY
	public static void AddFighter() {		
		System.out.print("Fighter Name [RETURN to cancel]: ");
		String fighterName = keyboard.nextLine();
		
		if (fighterName.length() > 0) {
			System.out.print("Fighter Speed: ");
			int fighterSpeed = Driver.getNumber(-1, 32);
			
			Starship fighter = new Starship(Starship.Ship.FIGHTER, fighterSpeed, fighterName);
			Driver.currentGameYard.addShipToShipyard(fighter);
		}
	}	
}
