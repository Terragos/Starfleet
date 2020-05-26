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
			int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");
			System.out.println();
			System.out.print("Which ship is firing? [RETURN to cancel] ");
			int shipNumFiring = -5;
	
			shipNumFiring = ShipSetup.GetAdjustedInput(print, "SHIP", "");
//			System.out.println("shipNumFiring: " + shipNumFiring);
			
			if(shipNumFiring == -1) {
				System.out.println();
				PhaseCalculation.PrintImpulseHeader();
				return;
			}
//			System.out.println("Driver.currentGameYard.list[shipNumFiring].name: " + Driver.currentGameYard.list[shipNumFiring].name);
			
			currentShip = Driver.currentGameYard.list[shipNumFiring];
	
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
					System.out.println("|  Pr[O]be          [E]SG                       Displacement De[V]ice      |");
					System.out.println("|                   [M]ines & Bombs                                        |");
					System.out.println("|                                               [Z] = Force Damage         |");
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

				weaponInput = Driver.getInput("1234PLFHDBTQIRSNOMEVZM");
				
				if (weaponInput.equalsIgnoreCase("1")) {
					totalDamage = type1Phaser(totalDamage);
						
				} else if(weaponInput.equalsIgnoreCase("2")) {
					totalDamage = type2Phaser(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("3")) {
					totalDamage = type3Phaser(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("4")) {
					totalDamage = type4Phaser(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("P")) {
					totalDamage = photonTorpedo(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("L")) {
					totalDamage = plasmaTorpedo(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("F")) {
					System.out.println();
					System.out.print("Fusion Beam: ");
					int weaponTypeNum = GetWeaponType("Type [S]tandard [O]verloaded [X]suicide", "SOX");
					if (weaponTypeNum == 1) {
						totalDamage = fusionBeam(totalDamage);
					} else {
						totalDamage = fusionBeamOverloaded(weaponTypeNum, totalDamage);
					}
					
				} else if(weaponInput.equalsIgnoreCase("B")) {
					totalDamage = disruptorBolt(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("T")) {
					totalDamage = tractorRepulsorBeam(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("H")) {
					totalDamage = hellbore(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("S")) {
					totalDamage = adminSuicideShuttle(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("O")) {
					totalDamage = probe(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("N")) {
					totalDamage = drone(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("Z")) {
					System.out.println("Forced Damage:  ");
					System.out.print("How much damage to add to damage total? ");
					int forcedDamage = Driver.getNumber(0, 500);
					if (forcedDamage > 0) {
						totalDamage = totalDamage + forcedDamage;
					}
					
				} else if(weaponInput.equalsIgnoreCase("M")) {
					totalDamage = MinesBombs(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("E")) {
					totalDamage = ESG(totalDamage);
					
				} else if(weaponInput.equalsIgnoreCase("D")) {
					DamageAllocation.DamageAlloc(totalDamage);
					totalDamage = 0;
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("M")) {
					System.out.print("Deal damage to a Monster\t");
					totalDamage = MonsterStuff.MonsterDamageFromShip(totalDamage);
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("I")) {
					System.out.println();
					PhaseCalculation.PrintImpulseHeader();
					reset = true;
					break;
				
				} else if(weaponInput.equalsIgnoreCase("R")) {
					reset = true;
					break;
					
				}
				
				if("1234PLFBHTQSNOEZM".contains(weaponInput)) {
					System.out.println("\tTotal Damage: " + totalDamage);
				}
			}
		}
	}
	
	public static int GetWeaponType(String text, String options) {
		String weaponTypeInput = "";
		int typeNum = 0;
		
		System.out.print(text + ":  ");
		weaponTypeInput = Driver.getInputNoCancel(options);
		
		typeNum = options.indexOf(weaponTypeInput) + 1;
		
		return typeNum;
	}
		
	public static int SensorScannerMod(int distance) {
		int sensorModifier = 1;									//  LOCK-ON SUCCESSFUL
		int newDistance = distance;
		
		if (currentShip.race != "Monster" && currentShip.lockedOn == false) {    //  Monster always have lock on
			sensorModifier = 2;									//  LOCK-ON FAILURE
		}
		
		int scannerModifier = 0;
		int nextScannerNum = currentShip.scannerNums[currentShip.ssd[23].numOfThisPart - currentShip.ssd[23].remaining];

		if (currentShip.race != "Monster" && nextScannerNum > 0) {
			scannerModifier = nextScannerNum;
		}
		
		newDistance = (distance * sensorModifier) + scannerModifier;
		
		if (distance != newDistance) {
			System.out.print("New Distance: " + newDistance);
			if (sensorModifier == 2 && scannerModifier != 0) {
				System.out.println(" (Doubled for SENSOR damage AND Modified by +" + nextScannerNum + " for SCANNER damage)");
			} else if (sensorModifier == 2) {
				System.out.println(" (Doubled for SENSOR damage)");
			} else if (scannerModifier != 0) {
				System.out.println(" (Modified by +" + nextScannerNum + " for SCANNER damage)");
			}
		}

		return newDistance;
	}
	
	public static int type1Phaser(int total) {
		int intPhaser1[][] = {{0, 1, 2, 3, 4, 5, 6, 9,16,26,51,76},   //  distance
							  {9, 8, 7, 6, 5, 5, 4, 3, 2, 1, 1, 0},   //  die roll 1 
							  {8, 7, 6, 5, 5, 4, 3, 2, 1, 1, 0, 0},   //  die roll 2
							  {7, 5, 5, 4, 4, 4, 3, 1, 0, 0, 0, 0},   //  die roll 3
							  {6, 4, 4, 4, 4, 3, 2, 0, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 4, 4, 3, 3, 1, 0, 0, 0, 0, 0},   //  die roll 5
							  {4, 4, 3, 3, 2, 2, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.println("Phaser-I");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 75);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=6 && distanceInput <=8) {
			distanceInput = 6;
		} else if (distanceInput >= 9 && distanceInput <= 15) {
			distanceInput = 7;			
		} else if (distanceInput >= 16 && distanceInput <= 25) {
			distanceInput = 8;			
		} else if (distanceInput >= 26 && distanceInput <= 50) {
			distanceInput = 9;			
		} else if (distanceInput >= 51 && distanceInput <= 75) {
			distanceInput = 10;			
		} else if (distanceInput >= 76) {
			distanceInput = 11;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser1[die][distanceInput];
			total += damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	public static int type2Phaser(int total) {
		int intPhaser2[][] = {{0, 1, 2, 3, 4, 9,16,31,51},   //  distance
							  {6, 5, 5, 4, 3, 2, 1, 1, 0},   //  die roll 1
							  {6, 5, 4, 4, 3, 2, 1, 0, 0},   //  die roll 2
							  {6, 4, 4, 4, 1, 1, 0, 0, 0},   //  die roll 3
							  {5, 4, 4, 3, 1, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 3, 3, 0, 0, 0, 0, 0},   //  die roll 5
							  {5, 3, 3, 3, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.println("Phaser-II");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 50);
		distanceInput = SensorScannerMod(distanceInput);
		
		if (distanceInput >=4 && distanceInput <=8) {
			distanceInput = 4;
		} else if (distanceInput >= 9 && distanceInput <= 15) {
			distanceInput = 5;			
		} else if (distanceInput >= 16 && distanceInput <= 30) {
			distanceInput = 6;			
		} else if (distanceInput >= 31 && distanceInput <= 50) {
			distanceInput = 7;			
		} else if (distanceInput >= 51) {
			distanceInput = 11;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser2[die][distanceInput];
			total += damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  TYPE 3 PHASER
	public static int type3Phaser(int total) {
		int intPhaser3[][] = {{0, 1, 2, 3, 4, 9,16},   //  distanceInputance
							  {4, 4, 4, 3, 1, 1, 0},   //  die roll 1
							  {4, 4, 4, 2, 1, 0, 0},   //  die roll 2
							  {4, 4, 4, 1, 0, 0, 0},   //  die roll 3
							  {4, 4, 3, 0, 0, 0, 0},   //  die roll 4
							  {4, 3, 2, 0, 0, 0, 0},   //  die roll 5
							  {3, 3, 1, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.println("Phaser-III");
		
		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 15);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=4 && distanceInput <=8) {
			distanceInput = 4;
		} else if (distanceInput >= 9 && distanceInput <= 15) {
			distanceInput = 5;			
		} else if (distanceInput >= 16) {
			distanceInput = 6;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser3[die][distanceInput];
			total += damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  TYPE 4 PHASER
	public static int type4Phaser(int total) {
		int intPhaser4[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,14,18,26,41,71,101},   //  distance
							  {20,20,20,20,20,20,20,15,12,10, 8, 6, 5, 4, 3, 2, 1,  0},   //  die roll 1
							  {20,20,20,20,20,20,15,12,11, 9, 8, 6, 4, 3, 2, 1, 0,  0},   //  die roll 2
							  {20,20,20,20,15,15,12,11,10, 8, 7, 5, 4, 2, 1, 0, 0,  0},   //  die roll 3
							  {20,20,20,20,15,15,11,10, 9, 8, 6, 4, 3, 1, 0, 0, 0,  0},   //  die roll 4
							  {15,15,15,15,12,12,10, 9, 8, 7, 5, 3, 2, 0, 0, 0, 0,  0},   //  die roll 5
							  {15,15,15,15,10,10, 9, 8, 7, 6, 5, 3, 1, 0, 0, 0, 0,  0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.println("Phaser-IV");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 100);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=11 && distanceInput <=13) {
			distanceInput = 11;
		} else if (distanceInput >= 14 && distanceInput <= 17) {
			distanceInput = 12;			
		} else if (distanceInput >= 18 && distanceInput <= 25) {
			distanceInput = 13;			
		} else if (distanceInput >= 26 && distanceInput <= 40) {
			distanceInput = 14;			
		} else if (distanceInput >= 41 && distanceInput <= 70) {
			distanceInput = 15;			
		} else if (distanceInput >= 71 && distanceInput <= 100) {
			distanceInput = 16;			
		} else if (distanceInput >= 101) {
			distanceInput = 17;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser4[die][distanceInput];
			total += damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  PHOTON TORPEDO
	public static int photonTorpedo(int total) {
		int intPhoton[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,31},   //  distance
							 {0, 0, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 0},   //  Standard (int type = 1)
							 {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 3, 0},   //  Proximity (int type = 2) 
							 {6, 6, 5, 4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0}};  //  Overloaded (int type = 3)

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int photonDamage = 0;
		int energy = 0;
		int feedbackDamage = 0;

		System.out.println();
		System.out.print("Photon Torpedo: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [P]roximity [O]verload", "SPO");
		if (weaponTypeNum == 3) {
			System.out.print("Total Energy [5-8]:   ");				//  If type=3 then get energy allocated value
			energy = Driver.getNumberNoCancel(5, 8);
		} 

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 30);
		distanceInput = SensorScannerMod(distanceInput);

		if (weaponTypeNum == 1) {
			photonDamage = 8;
		} else if (weaponTypeNum == 2) {
			photonDamage = 4;
		} else if (weaponTypeNum == 3) {
			photonDamage = energy * 2;
		}
		
		if (Driver.TESTING) {
			System.out.println("weaponTypeInput: " + weaponTypeNum);
			System.out.println("energy: " + energy);
			System.out.println("photonDamage: " + photonDamage);
		}
		
		if (distanceInput >=13 && distanceInput <=30) {
			distanceInput = 13;
		} else if (distanceInput >= 31) {
			distanceInput = 14;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int damage = 0;
			int die = DamageAllocation.rollDice(1,6);
			if (die <= intPhoton[weaponTypeNum][distanceInput]) {
				System.out.print("HIT!  ");
				damage = damage + photonDamage;
				total = total + damage;
			} else {
				System.out.print("Miss  ");
			}
		}
		System.out.println();

		if (weaponTypeNum == 3 && distanceInput <= 1) {
			feedbackDamage = numberInput * (energy - 4);
			System.out.println("---------------------------------------------------------");
			System.out.println("Feedback Damage: " + feedbackDamage + "  (On facing shield of firing ship)");
			System.out.println("---------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  PLASMA TORPEDO
	public static int plasmaTorpedo(int total) {
		int intPlasma[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31},   //  distance
				  			 {50,50,50,50,50,50,50,50,50,50,50,35,35,35,35,35,25,25,25,25,25,20,20,20,20,20,10,10,10, 5, 1, 0},   //  Type R
				  			 {30,30,30,30,30,30,30,30,30,30,30,22,22,22,22,22,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0},   //  Type GII
				  			 {20,20,20,20,20,20,20,20,20,20,20,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  Type G
				  			 {20,20,20,20,20,20,15,15,15,15,15,10,10, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Type F

		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.print("Plasma Torpedo: ");
		int weaponTypeNum = GetWeaponType("Type [R] [2]G [G] [F]", "R2GF");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >= 31) {
			distanceInput = 31;			
		}

		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int damage = intPlasma[weaponTypeNum][distanceInput];
			total = total + damage;
		}
		if (distanceInput <= 1) {
			feedbackDamage = total/4;
			System.out.println("---------------------------------------------------------");
			System.out.println("Feedback Damage: " + feedbackDamage + "  (On facing shield of firing ship)");
			System.out.println("---------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  FUSION BEAM
	public static int fusionBeam(int total) {
		int intFusion[][] = {{ 0, 1, 2, 3,11,16,25},   //  distance
							 {13, 8, 6, 4, 3, 2, 0},   //  die roll 1
							 {11, 8, 5, 3, 2, 1, 0},   //  die roll 2
							 {10, 7, 4, 2, 1, 0, 0},   //  die roll 3
							 { 9, 6, 3, 1, 1, 0, 0},   //  die roll 4
							 { 8, 5, 3, 1, 0, 0, 0},   //  die roll 5
							 { 8, 4, 2, 0, 0, 0, 0}};  //  die roll 6

		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=3 && distanceInput <=10) {
			distanceInput = 3;
		} else if (distanceInput >= 11 && distanceInput <= 15) {
			distanceInput = 4;			
		} else if (distanceInput >= 16 && distanceInput <= 24) {
			distanceInput = 5;			
		} else if (distanceInput >= 25) {
			distanceInput = 6;			
		}
		
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intFusion[die][distanceInput];
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  FUSION BEAM OVERLOADED (int type 2) / SUICIDE (int type 3)
	public static int fusionBeamOverloaded(int weaponTypeNum, int total) {
		int intFusionOver[][] = {{ 0, 1, 2, 3, 9},   //  distance
							     {19,12, 9, 6, 0},   //  die roll 1
								 {16,12, 7, 4, 0},   //  die roll 2
								 {15,10, 6, 3, 0},   //  die roll 3
								 {13, 9, 4, 1, 0},   //  die roll 4
								 {12, 7, 4, 1, 0},   //  die roll 5
								 {12, 6, 3, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=3 && distanceInput <=8) {
			distanceInput = 3;
		} else if (distanceInput >= 9) {
			distanceInput = 4;			
		}
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intFusionOver[die][distanceInput] * (weaponTypeNum-1);  //  Overload=1 (2-1), Suicide=2 (3-1)
			total = total + damage;
		}
		
		if (weaponTypeNum == 3) {
			String plural = "";
			if (numberInput > 1) {
				plural = "s";
			}
			System.out.println("---------------------------------------------------------");
			System.out.println(numberInput + " Fusion Beam" + plural +" destroyed AND " + numberInput + " point" + plural + " internal damage");
			System.out.println("---------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));

		return total;
	}
	
	//  DISRUPTOR BOLT
	public static int disruptorBolt(int total) {
		int intDisruptor[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,16,23,31,41},   //  distance
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 0},   //  Standard (type = 1)
				  			    {6, 5, 5, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0},   //  Overloaded (type = 2)
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 3, 3, 2, 0},   //  DERFACS (type = 3)
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 0},   //  UIM (type = 4)
				  			    {6, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0}};  //  Overloaded/UIM (type = 5)
		
		int intDisruptorDamage[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,16,23,31,41},   //  distance
									  { 0, 5, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 1, 0},   //  Standard (type = 1)
									  {10,10, 8, 8, 8, 6, 6, 6, 6, 0, 0, 0, 0, 0}};  //  Overloaded (type = 2)
		
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.print("Disruptor Bolt: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [O]verload [D]ERFACS [U]IM O[V]erload/UIM", "SODUV");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);
		
		int damageType = 1;
		if (weaponTypeNum == 2 || weaponTypeNum == 5) {
			damageType = 2;
		}
		
		if (Driver.TESTING) {
			System.out.println("type: " + weaponTypeNum);
			System.out.println("damageType: " + damageType);
		}
		
		if (distanceInput >=9 && distanceInput <=15) {
			distanceInput = 9;
		} else if (distanceInput >= 16 && distanceInput <= 22) {
			distanceInput = 10;
		} else if (distanceInput >= 23 && distanceInput <= 30) {
			distanceInput = 11;
		} else if (distanceInput >= 31 && distanceInput <= 40) {
			distanceInput = 12;
		} else if (distanceInput >= 41) {
			distanceInput = 13;
		}
		
		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int damage = 0;
			int die = DamageAllocation.rollDice(1,6);
//			System.out.println("die: " + die + "\tintPhoton[type][distanceInput]: " + intPhoton[type][distanceInput]);
			if (die <= intDisruptor[weaponTypeNum][distanceInput]) {
				damage = damage + intDisruptorDamage[damageType][distanceInput];
				System.out.print("HIT!  ");
				total = total + damage;
			} else {
				System.out.print("Miss  ");
			}
		}
		System.out.println();
		
		if (damageType == 2 && distanceInput == 0 && total > 0) {
			feedbackDamage = numberInput * 2;
			System.out.println("---------------------------------------------------------");
			System.out.println("Feedback Damage: " + feedbackDamage + "  (On facing shield of firing ship)");
			System.out.println("---------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  TRACTOR-REPULSOR BEAM
	public static int tractorRepulsorBeam(int total) {
		int intTracRep[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,19,26},   //  distance
							  {20,20,20,20,20,20,18,18,18,12,12,12,12, 8, 3, 0},   //  die roll 1
							  {20,20,20,20,20,20,15,15,15, 9, 9, 9, 9, 5, 2, 0},   //  die roll 2
							  {20,20,20,20,18,18,12,12,12, 6, 6, 6, 6, 3, 1, 0},   //  die roll 3
							  {20,20,20,20,15,15, 9, 9, 9, 3, 3, 3, 3, 2, 0, 0},   //  die roll 4
							  {18,18,18,18,12,12, 6, 6, 6, 2, 2, 2, 2, 1, 0, 0},   //  die roll 5
							  {15,15,15,15, 9, 9, 3, 3, 3, 1, 1, 1, 1, 0, 0, 0}};  //  die roll 6

		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.println("Tractor-Repulsor Beam");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=13 && distanceInput <=18) {
			distanceInput = 13;
		} else if (distanceInput >= 19 && distanceInput <= 25) {
			distanceInput = 14;			
		} else if (distanceInput >= 26) {
			distanceInput = 15;			
		}
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intTracRep[die][distanceInput];
//			System.out.println("die: " + die + "distanceInput: " + distanceInput + "\tintTracRep[die][distanceInput]: " + intTracRep[die][distanceInput]);
			total = total + damage;
		}

		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  EXPANDING SPHERE GENERATOR
	public static int ESG(int total) {
		double energy = 0.0;
		double radius = 0.0;
		int strength = 0;
		int damageReduction = 0;
		int numberInput = 0;
		int damage = 0;
		int startTotal = total;
		int totalESG = 0;
		
		System.out.println();
		System.out.println("Expanding Sphere: (Multiple ESGs from the same ship count as a single volley)");

		System.out.print("Number:   ");
		numberInput = Driver.getNumber(0, 20);
		if (numberInput > 0) {
			System.out.print("What is the radius of the sphere(s)? [RETURN to cancel] ");
			radius = Driver.getNumber(0, 3);
			if (radius >= 0) {
				for (int i = 1; i <= numberInput; i++) {
					System.out.print("How much energy was allocated to ESG #" + i + "? [RETURN to cancel] ");
					energy = Driver.getNumber(1, 5);
					if (energy > 0) {
						strength = (int) Math.round((4.0 - (radius / 3)) * energy); 
						totalESG = totalESG + (int) strength;
						
					} else {
						total = startTotal;
						break;
					}
				}
				
				System.out.print("By how much has total ESG field strength (" + totalESG + ") been reduced by previous damage? [RETURN = 0] ");
				damageReduction = Driver.getNumber(0, totalESG);
				if (damageReduction == -1) {
					damageReduction = 0;
				}

				totalESG = totalESG - damageReduction;

				if (numberInput > 0 && radius > 0 && energy > 0) {
					System.out.println();
					System.out.print("How much damage (1-" + totalESG + ") to add to total? [RETURN = ALL] ");
					damage = Driver.getNumber(0, totalESG);
					if (damage == -1) {
						damage = totalESG;
					} else {
						totalESG = damage;
					}
					total = total + totalESG;
				}
				
			} else {
				total = startTotal;
			}
			
		} else {
			total = startTotal;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	//  HELLBORE
	public static int hellbore(int total) {
		int hellbore[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,16,23,41},   //  distance
						    {20,20,17,15,15,13,13,13,13,10, 8, 4, 0},   //  Base (Standard) (int type = 1)
							{30,30,25,22,22,19,19,19,19, 0, 0, 0, 0},   //  Overloaded (int type = 2)
						    {10,10, 9, 8, 8, 7, 7, 7, 7, 5, 4, 2, 0},   //  Direct-Fire (int type = 3)
							{11,11,10, 9, 9, 8, 8, 8, 8, 7, 6, 5, 0}};  //  roll 2d6 <= this number is a hit 

		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.print("Hellbore: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [O]verloaded [D]irect-Fire", "SOD");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		if (distanceInput >=9 && distanceInput <=15) {
			distanceInput = 9;
		} else if (distanceInput >= 16 && distanceInput <= 22) {
			distanceInput = 10;			
		} else if (distanceInput >= 23 && distanceInput <= 40) {
			distanceInput = 11;			
		} else if (distanceInput >= 41) {
			distanceInput = 12;			
		}
		
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(2,6);
//			System.out.println("Die roll: " + die);
			if (die <= hellbore[4][distanceInput]) {
				System.out.print("HIT!  ");
				int damage = hellbore[weaponTypeNum][distanceInput];
//				System.out.println("Damage: " + hellbore[type][distanceInput]);
				total = total + damage;
			} else {
				System.out.print("Miss  ");
			}
		}
		
		if (weaponTypeNum == 1 || weaponTypeNum == 2) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Subtract GSR from total Damage done, then apply 1/2 damage to");
			System.out.println("    the weakest shield, and 1/2 distributed equally to other shields.");
			System.out.println("When calculating weakest shield include Specific Shield Reinforcement");
			System.out.println("---------------------------------------------------------------------");
		} else if (weaponTypeNum == 3) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------");
			System.out.println("All damage is applied to facing shield (instead of distributed).");
			System.out.println("---------------------------------------------------------------------");
		}
		if (weaponTypeNum == 2 && distanceInput == 0) {
			System.out.println("Attacking ship takes 1 damage point to each shield.");
			System.out.println("---------------------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	public static int adminSuicideShuttle(int total) {
		int startTotal = total;
		
		int numberInput = 0;
		
		System.out.println();
		System.out.println("Suicide Shuttle:  ");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		total = total + (numberInput * 18);
		
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		
		return total;
	}
	
	public static int drone(int total) {
		int startTotal = total;
		int droneDamage = 0;
		int numberInput = 0;
		
		System.out.println();
		System.out.println("Drone:  ");

		System.out.print("Number:   ");
		numberInput = Driver.getNumber(0, 200);
		
		System.out.println("Drone Types: 0 = IS, 1 = I, 2 = II, 3 = III, 4 = IV, 5 = IV    [RETURN] to cancel");
		
		for (int i = 1; i <= numberInput; i++) {
			System.out.print("Drone Type : ");
			int droneType = Driver.getNumber(0, 5);

			if (droneType != -1) {
				if (droneType == 0) {
//					System.out.println("Drone type 0");
					droneDamage = 8;
				} else if (droneType >= 1 && droneType <= 3) {
//					System.out.println("Drone type 1/2/3");
					droneDamage = 12;
				} else if (droneType == 4 || droneType == 5) {
//					System.out.println("Drone type 4/5");
					droneDamage = 24;
				}
//				System.out.println("total before: " + total);
//				System.out.println("droneDamage: " + droneDamage);
				total = total + droneDamage;
//				System.out.println("total after: " + total);
			} else {
				return 0;
			}
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		
		return total;
	}

	public static int probe(int total) {
		int startTotal = total;
		int die = 0;
		int numberInput = 0;
		int distanceInput = 0;
		
		System.out.println();
		System.out.print("Probe:");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
		distanceInput = SensorScannerMod(distanceInput);

		for (int i = 1; i <= numberInput; i++) {
			die = DamageAllocation.rollDice(1, 6);
			if (die >= distanceInput) {
				System.out.print("HIT!  ");				
				total = total + 8;
			} else {
				System.out.print("Miss  ");
			}
		}
		System.out.println();
		
		System.out.println();
		System.out.print("Volley Damage: " + (total - startTotal));
		
		return total;
	}

	public static int MinesBombs(int total) {
		int speedInput = 0;
		int numberInput = 0;
		int warhead = 0;
		int startTotal = total;
		String typeName = "";
		
		System.out.println();
		System.out.print("Mines/Bombs: ");
		int weaponTypeNum = GetWeaponType("Type [N]uclear Space Mine / [T]ransporter Bomb: ", "NT");

		if (weaponTypeNum == 1) {
			typeName = "Nuclear Space Mine";
			warhead = 35;
		} else if (weaponTypeNum == 2) {
			typeName = "Transporter Bomb";
			warhead = 10;
		}
		
		System.out.print("Number: ");
		numberInput = Driver.getNumberNoCancel(0, 200);
		
		if (numberInput > 0) {
			System.out.print("Speed of ship passing within 1 hex of " + typeName + ": ");
			speedInput = Driver.getNumberNoCancel(0, 40);
			
			if (speedInput >= 0) {
				for (int i = 1; i <= numberInput; i++) {
					int die = DamageAllocation.rollDice(1, 6);
					if (die == 1 || die < speedInput) {
						System.out.print("Hit!  ");
						total = total + warhead;
					} else {
						System.out.print("Miss  ");
					}
				}
			}
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}

}
