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
		
		ShipSetup.SortShips();															// Always SORT ships before printing to screen

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
					String userInput = Driver.getInput("WTDSF");
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
	}
	

	public static void PrintImpulseHeader() {
		System.out.print("=============");
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.println("=================");
		
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
		System.out.println("-----------------");

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
		System.out.println("=====================");
		
		for (int i = 1; i <= ((18 + shipTotal)-29)/2; i++) {     //  CENTERS TEXT
			System.out.print(" ");
		}
		System.out.print("Press RETURN for next Impulse");		
		for (int i = 1; i <= ((17 + shipTotal)-29)/2 - 2; i++) {     //  CENTERS TEXT
			System.out.print(" ");
		}
		System.out.print("[W][T][D][S][F]");		

		System.out.println();
		System.out.print("=============");
		
		for(int k = 0; k < Driver.currentGameYard.numShips; k++) {	
			for(int j = 0; j < (Driver.currentGameYard.list[k].name.length()); j++) {
			System.out.print("=");
			}
			System.out.print("====");
		}
		System.out.print("=================");
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
		
		System.out.print("Drone Speed: ");
		int droneSpeed = Driver.getNumber(-1, 32);
		
		Starship drone = new Starship(Starship.Ship.DRONE, droneSpeed, droneName);
		Driver.currentGameYard.addShipToShipyard(drone);
	}

	// ADD SHUTTLE METHOD - ON-THE-FLY	
	public static void AddShuttle() {	
		System.out.print("Shuttle Name : ");
		String shuttleName = keyboard.nextLine();
		
		System.out.print("Shuttle Speed: ");
		int shuttleSpeed = Driver.getNumber(-1, 32);
		
		Starship shuttle = new Starship(Starship.Ship.SHUTTLE, shuttleSpeed, shuttleName);
		Driver.currentGameYard.addShipToShipyard(shuttle);
	}

	// ADD FIGHTER METHOD - ON-THE-FLY
	public static void AddFighter() {		
		System.out.print("Fighter Name : ");
		String fighterName = keyboard.nextLine();
		
		System.out.print("Fighter Speed: ");
		int fighterSpeed = Driver.getNumber(-1, 32);
		
		Starship fighter = new Starship(Starship.Ship.FIGHTER, fighterSpeed, fighterName);
		Driver.currentGameYard.addShipToShipyard(fighter);
	}	
}
