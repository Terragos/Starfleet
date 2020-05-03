import java.util.Scanner;

public class WeaponsDamage {
	
	public final static int MAXDIST = 300;
	public static Starship currentShip;
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void WeaponsDam (int impulseNumber) {   //  If yes > 0 then in the middle of an Impulse Movement Procedure
		int totalDamage = 0;
		
		boolean reset = false;
		while (!reset) {

			System.out.println();
			ShipSetup.SortCurrentShipyard();														// Always SORT ships before printing to screen
			ShipSetup.PrintCurrentNonMonstersInGameThatHaveSSD();
//		ShipSetup.PrintCurrentShipsInGameThatHaveSSD();
			
			System.out.println();
			System.out.print("Which ship is firing? [RETURN to cancel] ");
			int shipNumFiring = -5;
	
			shipNumFiring = Driver.getNumber(1, Driver.currentGameYard.numStarships);				//  Get a new input
	
			if(shipNumFiring == -1) {
				System.out.println();
				PhaseCalculation.PrintImpulseHeader();
				return;
			}
			
			currentShip = Driver.currentGameYard.list[shipNumFiring-1];
	
			boolean monsterInGame = false;
			for (int i = 0; i <= Driver.currentGameYard.numShips-1; i++) {
				if (Driver.currentGameYard.list[i].race == "Monster") {
					monsterInGame = true;
				}
			}
			
			boolean cont = true;
			while(cont) {
				if (totalDamage == 0) {
					System.out.println();
					System.out.println();
					System.out.println("|==========================================================================|");
					System.out.println("|                         WEAPONS DAMAGE PROCEDURE                         |");
					System.out.println("|==========================================================================|");
					System.out.println("|  Type [1] Phaser  [P]hoton Torpedo [S/P/O]    Disruptor [B]olt           |");
					System.out.println("|  Type [2] Phaser  P[L]asma Torpedo [R/2/G/F]  [T]ractor-Repulsor Beam    |");
					System.out.println("|  Type [3] Phaser  [F]usion Beam [S/O/X]       [S]uicide Shuttle (Admin)  |");
					System.out.println("|  Type [4] Phaser  [H]ellbore [S/O/D]          Dro[N]e                    |");
					System.out.println("|  Pr[O]be          L[A]b                                                  |");
					System.out.println("|==========================================================================|");
					System.out.println("|                        Go to [D]amage Allocation                         |");
					if (monsterInGame == true) {
						System.out.println("|                      Damage to [M]onster from Ship                       |");
					}
					if (impulseNumber == -1) {
						System.out.println("|                          [R]eturn to Main Menu                           |");
					} else {
						System.out.println("|                      Return to [I]mpulse Procedure                       |");
					}
					System.out.println("|==========================================================================|");
				}
	
				System.out.println();
				System.out.print("Weapon:   ");
	
				String weaponInput = "";
				while (weaponInput.length() == 0) {                           //  Do NOT accept RETURN as a valid answer
					weaponInput = Driver.getInput("1234PLFHDTQIRSNAOM");
				}
				
				int typeInput = 0;
				int energyInput = 0;
				int photonEnergyInput = 0;
	
				//  Asking what type of Photon Torpedo			
				if (weaponInput.equalsIgnoreCase("P")) {
					System.out.print("Photon Type [S]tandard [P]roximity [O]verload:   ");
					String weaponTypeInput = Driver.getInput("SPO");
					String options = "SPO"; 
					typeInput = options.indexOf(weaponTypeInput);            // Getting number: S=1, P=2, O=3
					
					if (typeInput == 3) {
						System.out.print("Total Energy [5-8]:   ");          //  If type=3 then get energy allocated value
						photonEnergyInput = Driver.getNumberNoCancel(5, 8);
					} 
				}
					
				//  Asking what type of Plasma Torpedo
				if (weaponInput.equalsIgnoreCase("L")) {
					System.out.print("Plasma Type [R] [2]G [G] [F]:   ");
					String weaponTypeInput = Driver.getInput("R2GF");
					String options = "R2GF"; 
					typeInput = options.indexOf(weaponTypeInput);           // Getting number: R=1, 2=2, G=3, F=4 
				}
	
				//  Asking what type of Fusion Beam	
				if (weaponInput.equalsIgnoreCase("F")) {
					System.out.print("Type [S]tandard [O]verloaded [X]suicide:   ");
					String weaponTypeInput = Driver.getInput("SOX");
					String options = "SOX"; 
					typeInput = options.indexOf(weaponTypeInput)-1;         // Getting number: O=1, X=2 
				}
					
				//  Asking what type of Hellbore
				if (weaponInput.equalsIgnoreCase("H")) {
					System.out.print("Type [S]tandard [O]verloaded [D]irect-Fire:   ");
					String weaponTypeInput = Driver.getInput("SOD");
					String options = "SOD"; 
					typeInput = options.indexOf(weaponTypeInput);           // Getting number: S=1, O=2, D=3 
				}
				
				int distanceInput = 0;
				int numberInput = 0;
				if ("1234PLFHTQO".contains(weaponInput)) {
					System.out.print("Distance: ");
					distanceInput = Driver.getNumber(0, MAXDIST);
					
					// MODIFY DISTANCE FOR SENSOR LOCK-ON FAILURE
					if (currentShip.race != "Monster" && currentShip.lockedOn == false) {    //  Monster always have lock on
						distanceInput *= 2;
					}
					
					// MODIFY DISTANCE FOR SCANNER NUMBER
					int nextScannerNum = currentShip.ssd[23].numOfThisPart - currentShip.ssd[23].remaining;
					distanceInput += currentShip.scannerNums[nextScannerNum];
					if (currentShip.race != "Monster" && (currentShip.lockedOn == false || nextScannerNum > 0)) {
						System.out.println("New Distance " + distanceInput + " (modified due to Sensor Lock-on failure and/or Scanner resolution number)");
					}
					
					System.out.print("Number:   ");
					numberInput = Driver.getNumber(0, 200);
				} else if (weaponInput.equalsIgnoreCase("S") || weaponInput.equalsIgnoreCase("N")) {
					System.out.print("Number:   ");
					numberInput = Driver.getNumber(0, 200);
				}
				
				if (distanceInput >= 0 && numberInput > 0) {
					if (weaponInput.equalsIgnoreCase("1")) {
						System.out.print("Phaser-I\t");
						totalDamage = type1Phaser(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("2")) {
						System.out.print("Phaser-II\t");
						totalDamage = type2Phaser(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("3")) {
						System.out.print("Phaser-III\t");
						totalDamage = type3Phaser(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("4")) {
						System.out.print("Phaser-IV\t");
						totalDamage = type4Phaser(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("P")) {
						System.out.print("Photon Torpedo\t");
						totalDamage = photonTorpedo(typeInput, energyInput, numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("L")) {
						System.out.print("Plasma Torpedo\t");
						totalDamage = plasmaTorpedo(typeInput, numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("F")) {
						System.out.print("Fusion Beam\t");
						totalDamage = fusionBeam(numberInput, distanceInput, totalDamage);
						
	//				} else if(weaponInput.equalsIgnoreCase("O")) {
	//					System.out.println("Overloaded Fusion Beam");
	//					totalDamage = fusionBeamOverloaded(typeInput, numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("B")) {
						System.out.print("Disruptor Bolt\t");
						totalDamage = disruptorBolt(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("T")) {
						System.out.print("Tractor-Repulsor Beam\t");
						totalDamage = tractorRepulsorBeam(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("H")) {
						System.out.print("Hellbore\t");
						totalDamage = hellbore(typeInput, numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("S")) {
						System.out.print("Suicide Shuttle\t");
						totalDamage = adminSuicideShuttle(numberInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("O")) {
						System.out.print("Probe\t");
						totalDamage = probe(numberInput, distanceInput, totalDamage);
						
					} else if(weaponInput.equalsIgnoreCase("N")) {
						System.out.print("Drone\t");
						totalDamage = drone(numberInput, totalDamage);
						
					}
				}
				
				if(weaponInput.equalsIgnoreCase("D")) {
					DamageAllocation.DamageAlloc(totalDamage);
					totalDamage = 0;
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("M")) {
					System.out.print("Deal damage to a Monster\t");
					totalDamage = MonsterStuff.MonsterDamageFromShip(totalDamage);
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("A")) {
					System.out.print("Gathering Lab Research Data\t");
					MonsterStuff.labResearchPoints();
				
				} else if(weaponInput.equalsIgnoreCase("I")) {
					System.out.println();
					PhaseCalculation.PrintImpulseHeader();
					reset = true;
//					break;
				
				} else if(weaponInput.equalsIgnoreCase("R")) {
					reset = true;
//					break;
					
				}
				
				if("1234PLFHTQSNO".contains(weaponInput)) {
					System.out.println("\tTotal Damage: " + totalDamage);
				}
			}
		}

	}
	
	//  TYPE 1 PHASER
	public static int type1Phaser(int num, int dist, int total) {
		int intPhaser1[][] = {{0, 1, 2, 3, 4, 5, 6, 9,16,26,51,76},   //  distance
							  {9, 8, 7, 6, 5, 5, 4, 3, 2, 1, 1, 0},   //  die roll 1 
							  {8, 7, 6, 5, 5, 4, 3, 2, 1, 1, 0, 0},   //  die roll 2
							  {7, 5, 5, 4, 4, 4, 3, 1, 0, 0, 0, 0},   //  die roll 3
							  {6, 4, 4, 4, 4, 3, 2, 0, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 4, 4, 3, 3, 1, 0, 0, 0, 0, 0},   //  die roll 5
							  {4, 4, 3, 3, 2, 2, 0, 0, 0, 0, 0, 0}};  //  die roll 6
		if (dist >=6 && dist <=8) {
			dist = 6;
		} else if (dist >= 9 && dist <= 15) {
			dist = 7;			
		} else if (dist >= 16 && dist <= 25) {
			dist = 8;			
		} else if (dist >= 26 && dist <= 50) {
			dist = 9;			
		} else if (dist >= 51 && dist <= 75) {
			dist = 10;			
		} else if (dist >= 76) {
			dist = 11;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser1[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  TYPE 2 PHASER
	public static int type2Phaser(int num, int dist, int total) {
		int intPhaser2[][] = {{0, 1, 2, 3, 4, 9,16,31,51},   //  distance
							  {6, 5, 5, 4, 3, 2, 1, 1, 0},   //  die roll 1
							  {6, 5, 4, 4, 3, 2, 1, 0, 0},   //  die roll 2
							  {6, 4, 4, 4, 1, 1, 0, 0, 0},   //  die roll 3
							  {5, 4, 4, 3, 1, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 3, 3, 0, 0, 0, 0, 0},   //  die roll 5
							  {5, 3, 3, 3, 0, 0, 0, 0, 0}};  //  die roll 6
		if (dist >=4 && dist <=8) {
			dist = 4;
		} else if (dist >= 9 && dist <= 15) {
			dist = 5;			
		} else if (dist >= 16 && dist <= 30) {
			dist = 6;			
		} else if (dist >= 31 && dist <= 50) {
			dist = 7;			
		} else if (dist >= 51) {
			dist = 11;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser2[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  TYPE 3 PHASER
	public static int type3Phaser(int num, int dist, int total) {
		int intPhaser3[][] = {{0, 1, 2, 3, 4, 9,16},   //  distance
							  {4, 4, 4, 3, 1, 1, 0},   //  die roll 1
							  {4, 4, 4, 2, 1, 0, 0},   //  die roll 2
							  {4, 4, 4, 1, 0, 0, 0},   //  die roll 3
							  {4, 4, 3, 0, 0, 0, 0},   //  die roll 4
							  {4, 3, 2, 0, 0, 0, 0},   //  die roll 5
							  {3, 3, 1, 0, 0, 0, 0}};  //  die roll 6
		if (dist >=4 && dist <=8) {
			dist = 4;
		} else if (dist >= 9 && dist <= 15) {
			dist = 5;			
		} else if (dist >= 16) {
			dist = 6;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser3[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  TYPE 4 PHASER
	public static int type4Phaser(int num, int dist, int total) {
		int intPhaser4[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,14,18,26,41,71,101},   //  distance
							  {20,20,20,20,20,20,20,15,12,10, 8, 6, 5, 4, 3, 2, 1,  0},   //  die roll 1
							  {20,20,20,20,20,20,15,12,11, 9, 8, 6, 4, 3, 2, 1, 0,  0},   //  die roll 2
							  {20,20,20,20,15,15,12,11,10, 8, 7, 5, 4, 2, 1, 0, 0,  0},   //  die roll 3
							  {20,20,20,20,15,15,11,10, 9, 8, 6, 4, 3, 1, 0, 0, 0,  0},   //  die roll 4
							  {15,15,15,15,12,12,10, 9, 8, 7, 5, 3, 2, 0, 0, 0, 0,  0},   //  die roll 5
							  {15,15,15,15,10,10, 9, 8, 7, 6, 5, 3, 1, 0, 0, 0, 0,  0}};  //  die roll 6
		if (dist >=11 && dist <=13) {
			dist = 11;
		} else if (dist >= 14 && dist <= 17) {
			dist = 12;			
		} else if (dist >= 18 && dist <= 25) {
			dist = 13;			
		} else if (dist >= 26 && dist <= 40) {
			dist = 14;			
		} else if (dist >= 41 && dist <= 70) {
			dist = 15;			
		} else if (dist >= 71 && dist <= 100) {
			dist = 16;			
		} else if (dist >= 101) {
			dist = 17;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser4[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  PHOTON TORPEDO
	public static int photonTorpedo(int type, int energy, int num, int dist, int total) {
		int intPhoton[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,31},   //  distance
							 {0, 0, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 0},   //  Standard (int type = 1)
							 {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 3, 0},   //  Proximity (int type = 2) 
							 {6, 6, 5, 4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0}};  //  Overloaded (int type = 3)

		int photonDamage = 0;
		if (type == 1) {
			photonDamage = 8;
		} else if (type == 2) {
			photonDamage = 4;
		} else {
			photonDamage = energy * 2;
		}
		
		if (dist >=13 && dist <=30) {
			dist = 13;
		} else if (dist >= 31) {
			dist = 14;			
		}
		
		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			if (die <= intPhoton[type][dist]) {
				int damage =+ photonDamage;
				total += damage;
			} else {
			}
		}
		if (type == 3 && dist <= 1) {
			feedbackDamage = num * (energy - 4);
			System.out.println("");
			System.out.println("Feedback Damage: " + feedbackDamage);
			System.out.println("");
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  PLASMA TORPEDO
	public static int plasmaTorpedo(int type, int num, int dist, int total) {
		int intPlasma[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31},   //  distance
				  			 {50,50,50,50,50,50,50,50,50,50,50,35,35,35,35,35,25,25,25,25,25,20,20,20,20,20,10,10,10, 5, 1, 0},   //  Type R
				  			 {30,30,30,30,30,30,30,30,30,30,30,22,22,22,22,22,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0},   //  Type GII
				  			 {20,20,20,20,20,20,20,20,20,20,20,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  Type G
				  			 {20,20,20,20,20,20,15,15,15,15,15,10,10, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Type F

		if (dist >= 31) {
			dist = 31;			
		}

		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int damage = intPlasma[type][dist];
			total += damage;
		}
		if (dist <= 1) {
			feedbackDamage = total/4;
			System.out.println("");
			System.out.println("Feedback Damage: " + feedbackDamage);
			System.out.println("");
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  FUSION BEAM
	public static int fusionBeam(int num, int dist, int total) {
		int intFusion[][] = {{ 0, 1, 2, 3,11,16,25},   //  distance
							 {13, 8, 6, 4, 3, 2, 0},   //  die roll 1
							 {11, 8, 5, 3, 2, 1, 0},   //  die roll 2
							 {10, 7, 4, 2, 1, 0, 0},   //  die roll 3
							 { 9, 6, 3, 1, 1, 0, 0},   //  die roll 4
							 { 8, 5, 3, 1, 0, 0, 0},   //  die roll 5
							 { 8, 4, 2, 0, 0, 0, 0}};  //  die roll 6
		if (dist >=3 && dist <=10) {
			dist = 3;
		} else if (dist >= 1 && dist <= 15) {
			dist = 4;			
		} else if (dist >= 16 && dist <= 24) {
			dist = 5;			
		} else if (dist >= 25) {
			dist = 6;			
		}
		
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intFusion[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  FUSION BEAM OVERLOADED (int type 1) / SUICIDE (int type 2)
	public static int fusionBeamOverloaded(int type, int num, int dist, int total) {
		int intFusionOver[][] = {{ 0, 1, 2, 3, 9},   //  distance
							     {19,12, 9, 6, 0},   //  die roll 1
								 {16,12, 7, 4, 0},   //  die roll 2
								 {15,10, 6, 3, 0},   //  die roll 3
								 {13, 9, 4, 1, 0},   //  die roll 4
								 {12, 7, 4, 1, 0},   //  die roll 5
								 {12, 6, 3, 0, 0}};  //  die roll 6
		if (dist >=3 && dist <=8) {
			dist = 3;
		} else if (dist >= 9) {
			dist = 4;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intFusionOver[die][dist] * type;
			total += damage;
		}
		
		if (type == 2) {
			System.out.println("");
			System.out.println(num + "Fusion Beam Destroyed AND " + num + " points internal damage");
			System.out.println("");
		}
		
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  DISRUPTOR BOLT
	public static int disruptorBolt(int num, int dist, int total) {
		int intDisruptor[][] = {{0, 1, 2, 3, 4, 9,16,31,51},   //  distance
				  			    {6, 5, 5, 4, 3, 2, 1, 1, 0},   //  die roll 1
				  			    {6, 5, 4, 4, 3, 2, 1, 0, 0},   //  die roll 2
				  			    {6, 4, 4, 4, 1, 1, 0, 0, 0},   //  die roll 3
				  			    {5, 4, 4, 3, 1, 0, 0, 0, 0},   //  die roll 4
				  			    {5, 4, 3, 3, 0, 0, 0, 0, 0},   //  die roll 5
				  			    {5, 3, 3, 3, 0, 0, 0, 0, 0}};  //  die roll 6
		if (dist >=6 && dist <=8) {
			dist = 6;
		} else if (dist >= 9 && dist <= 15) {
			dist = 7;			
		} else if (dist >= 16 && dist <= 25) {
			dist = 8;			
		} else if (dist >= 26 && dist <= 50) {
			dist = 9;			
		} else if (dist >= 51 && dist <= 75) {
			dist = 10;			
		} else if (dist >= 76) {
			dist = 11;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intDisruptor[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  TRACTOR-REPULSOR BEAM
	public static int tractorRepulsorBeam(int num, int dist, int total) {
		int intTracRep[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,19,26},   //  distance
							  {20,20,20,20,20,20,18,18,18,12,12,12,12, 8, 3, 0},   //  die roll 1
							  {20,20,20,20,20,20,15,15,15, 9, 9, 9, 9, 5, 2, 0},   //  die roll 2
							  {20,20,20,20,18,18,12,12,12, 6, 6, 6, 6, 3, 1, 0},   //  die roll 3
							  {20,20,20,20,15,15, 9, 9, 9, 3, 3, 3, 3, 2, 0, 0},   //  die roll 4
							  {18,18,18,18,12,12, 6, 6, 6, 2, 2, 2, 2, 1, 0, 0},   //  die roll 5
							  {15,15,15,15, 9, 9, 3, 3, 3, 1, 1, 1, 1, 0, 0, 0}};  //  die roll 6
		if (dist >=13 && dist <=18) {
			dist = 13;
		} else if (dist >= 19 && dist <= 25) {
			dist = 14;			
		} else if (dist >= 26) {
			dist = 15;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intTracRep[die][dist];
			total += damage;
		}
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	//  HELLBORE
	public static int hellbore(int type, int num, int dist, int total) {
		int hellbore[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,16,23,41},   //  distance
						    {20,20,17,15,15,13,13,13,13,10, 8, 4, 0},   //  Base (Standard) (int type = 1)
							{30,30,25,22,22,19,19,19,19, 0, 0, 0, 0},   //  Overloaded (int type = 2)
						    {10,10, 9, 8, 8, 7, 7, 7, 7, 5, 4, 2, 0},   //  Direct-Fire (int type = 3)
							{11,11,10, 9, 9, 8, 8, 8, 8, 7, 6, 5, 0}};  //  roll 2d6 <= this number is a hit 
		if (dist >=9 && dist <=15) {
			dist = 9;
		} else if (dist >= 16 && dist <= 22) {
			dist = 10;			
		} else if (dist >= 23 && dist <= 40) {
			dist = 11;			
		} else if (dist >= 41) {
			dist = 12;			
		}
		
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,11) + 1;
			System.out.println("Die roll: " + die);
			if (die <= hellbore[4][dist]) {
				int damage = hellbore[type][dist];
				System.out.println("Damage: " + hellbore[type][dist]);
				total += damage;
			}
		}
		
		if (type >= 1 && type <= 2) {
			System.out.println();
			System.out.println("Subtract GSR from total Damage done,");
			System.out.println("then apply 1/2 to weakest shield, 1/2 distributed equally to other shields.");
		} else if (type == 3) {
			System.out.println();
			System.out.println("All damage is applied to facing shield (instead of distributed).");
		}
		if (type == 2 && dist == 0) {
			System.out.println("Attacking ship takes 1 damage to each shield.");
		}
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		return total;
	}
	
	public static int adminSuicideShuttle(int num, int total) {
		int startTotal = total;
		total = total + (num * 18);
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		return total;
	}
	
	public static int drone(int num, int total) {
		int startTotal = total;
		int droneDamage = 0;
		
		System.out.println("Drone Types: 0 = IS, 1 = I, 2 = II, 3 = III, 4 = IV, 5 = IV");
		
		for (int i = 1; i <= num; i++) {
			System.out.print("Drone Type : ");
			int droneType = Driver.getNumber(0, 5);

			if (droneType == -1) {
				if (droneType == 0) {
					droneDamage =+ 8;
				} else if (droneType >= 1 && droneType <= 3) {
					droneDamage =+ 12;
				} else if (droneType == 4 || droneType == 5) {
					droneDamage =+ 24;
				}
				total = total + droneDamage;
			}
		}
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		return total;
	}

	public static int probe(int num, int dist, int total) {
		int startTotal = total;
		
		int die = 0;
	
		for (int i = 1; i <= num; i++) {
			die = DamageAllocation.rollDice(1, 6);
			if (die >= dist) {
				total = total + 8;
			}
		}
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		return total;
	}

}
