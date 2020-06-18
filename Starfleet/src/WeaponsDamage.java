import java.util.Scanner;

public class WeaponsDamage {
	
	public final static int MAXDIST = 300;
	public static Starship currentShip;
	public static Starship targetShip;
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void WeaponsDam (int impulseNumber) {   //  If yes > 0 then in the middle of an Impulse Movement Procedure
		int totalDamage = 0;
		
		boolean reset = false;
		while (!reset) {

			System.out.println();
			int print = ShipSetup.PrintCurrentThingsInGame("SHIP SHUTTLE MONSTER", "");
			System.out.println();
			System.out.print("Which ship is firing? [RETURN to cancel] ");
			int shipNumFiring = -5;
			shipNumFiring = ShipSetup.GetAdjustedInput(print, "SHIP SHUTTLE MONSTER", "");
			System.out.println("shipNumFiring: " + shipNumFiring);
			
			if(shipNumFiring == -1) {
				System.out.println();
				PhaseCalculation.PrintImpulseHeader();
				return;
			}
			System.out.println("Firing Ship: " + Driver.currentGameYard.list[shipNumFiring].name);
			
			
			System.out.println();
			System.out.print("Which ship is being targeted? [RETURN to cancel] ");
			int shipNumTarget = -5;
			shipNumTarget = ShipSetup.GetAdjustedInput(print, "SHIP SHUTTLE MONSTER", "");
			System.out.println("shipNumTarget: " + shipNumTarget);
			
			if(shipNumTarget == -1) {
				System.out.println();
				PhaseCalculation.PrintImpulseHeader();
				return;
			}
			System.out.println("Target Ship: " + Driver.currentGameYard.list[shipNumTarget].name);
			
//			Driver.electronicWarfareNet = GetEWshift(shipNumFiring, shipNumTarget);
			
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
					PrintWeaponsMenu(impulseNumber);
				}
	
				System.out.println();
				System.out.print("Weapon:   ");
	
				String weaponInput = "";

				weaponInput = Driver.getInput("1234PLFHDABTQIRSNOMEVZMW");
				
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
					
				} else if(weaponInput.equalsIgnoreCase("A")) {
					if (totalDamage == 0) {
						totalDamage = plasmaticPulsarDevice(totalDamage);
					} else if (totalDamage > 0) {
						System.out.print("Total damage (" + totalDamage + ") will be zeroed out.  OK? ");
						String ok = Driver.getInput("YN");
						if (ok.equalsIgnoreCase("Y")) {
							totalDamage = plasmaticPulsarDevice(totalDamage);
						}
					}
					
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
					
					if (Driver.currentGameYard.list[shipNumTarget].kindOfShip == Starship.Ship.MONSTER) {
						MonsterStuff.MonsterDamageFromShip(shipNumTarget, totalDamage);
					} else if (Driver.currentGameYard.list[shipNumTarget].kindOfShip == Starship.Ship.PLANET) {
						MonsterStuff.DamageToPlanet(shipNumTarget, totalDamage);
					} else if (Driver.currentGameYard.list[shipNumTarget].kindOfShip == Starship.Ship.STARSHIP) {
						DamageAllocation.DamageAlloc(shipNumTarget, totalDamage);
					}

					totalDamage = 0;
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("M")) {
					System.out.print("Deal damage to a Monster\t");
					totalDamage = MonsterStuff.MonsterDamageFromShip(shipNumTarget, totalDamage);
					reset = false;
					cont = false;
					
				} else if(weaponInput.equalsIgnoreCase("I")) {
					System.out.println();
					PhaseCalculation.PrintImpulseHeader();
					reset = true;
					break;
				
				} else if(weaponInput.equalsIgnoreCase("W")) {
					PrintWeaponsMenu(impulseNumber);
					
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
	
	
	
	
	
	public static void PrintWeaponsMenu(int impulseNumber) {
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|                         WEAPONS DAMAGE PROCEDURE                         |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|  Type [1] Phaser  [P]hoton Torpedo [S/P/O]    Disruptor [B]olt           |");
		System.out.println("|  Type [2] Phaser  P[L]asma Torpedo [R/2/G/F]  [T]ractor-Repulsor Beam    |");
		System.out.println("|  Type [3] Phaser  [F]usion Beam [S/O/X]       [S]uicide Shuttle (Admin)  |");
		System.out.println("|  Type [4] Phaser  [H]ellbore [S/O/D]          Dro[N]e                    |");
		System.out.println("|  Pr[O]be          [E]SG                       Displacement De[V]ice      |");
		System.out.println("|                   [M]ines & Bombs                                        |");
		System.out.println("|                                                                          |");
		System.out.println("|==========================================================================|");
		System.out.println("|                             Single Volley Only                           |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|  Pl[A]smatic Pulsar Device                                               |");
		System.out.println("|                                                                          |");
		System.out.println("|==========================================================================|");
		System.out.println("|                                   Other                                  |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|                            [Z] = Force Damage                            |");
		System.out.println("|==========================================================================|");
		System.out.println("|                        Go to [D]amage Allocation                         |");
		System.out.println("|                          Print [W]eapons Menu                            |");
//		if (monsterInGame == true) {
//			System.out.println("|                      Damage to [M]onster from Ship                       |");
//		}
		if (impulseNumber == -1) {
			System.out.println("|                          [R]eturn to Main Menu                           |");
		} else {
			System.out.println("|                      Return to [I]mpulse Procedure                       |");
		}
		System.out.println("|==========================================================================|");
	}
	
	
	
	
	
	public static double GetEWshift(int shipNumFiring, int shipNumTarget) {
		int asteroidECM = 0;
		if(TerrainStuff.TerrainTypeList.contains("A")) {
			System.out.print("How many Asteroid hexes are being fired through (+ECM modifier for target)? ");
			asteroidECM = Driver.getNumberNoCancel(1,100);
		}
		
		int nebluaeECM = 0;
		if(TerrainStuff.TerrainTypeList.contains("N")) {
			System.out.println("Nebulae +ECM modifier for target)");
			nebluaeECM = 9;
		}
		
		int cloakECM = 0;
		if(Driver.currentGameYard.list[shipNumTarget].cloakOn) {
			System.out.println("Targetted ship is CLOAKED. (+ECM modifier for target)");
			cloakECM = 5;
		}

		currentShip = Driver.currentGameYard.list[shipNumFiring];
		targetShip = Driver.currentGameYard.list[shipNumTarget];
		
		int terrainECM = asteroidECM + cloakECM + nebluaeECM;
		
		Driver.electronicWarfareNet = Math.sqrt(targetShip.ECM + terrainECM - currentShip.ECCM);
		if (Driver.electronicWarfareNet < 0) {
			Driver.electronicWarfareNet = 0;
		}
		
		if (Driver.TESTING) {
			System.out.println("ECCM + terrain - ECM: " + Driver.electronicWarfareNet);
		}
		
		return Driver.electronicWarfareNet;
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
	
	
	
	
	
	public static int[] GetEWadjustment(int die, int dist, int maxDist) {
		int EW[] = {0,0};
		
		if (Driver.TESTING) {
			System.out.println();
			System.out.println("   die: " + die + "\t   dist: " + dist);
			System.out.println("ECCM-ECM: " + Driver.electronicWarfareNet);
		}

//		Driver.electronicWarfareNet = GetEWshift(shipNumFiring, shipNumTarget);

		int newDie = die + (int) Driver.electronicWarfareNet;
		int newDist = dist;
		if (newDie > 6) {
			newDist = newDist + newDie - 6;
			if (newDist > maxDist) {
				newDist = maxDist;
			}
			newDie = 6;
		}
		
		EW[0] = newDie;
		EW[1] = newDist;

		if (Driver.TESTING) {
			System.out.println("newDie: " + newDie + "\tnewDist: " + newDist);
		}
		return EW;
	}

	
	
	
	
	public static int AdjustDamageForCloak(int damage) {
		
		if (targetShip.cloakOn) {
			System.out.print("damage: " + damage);
			int cloakDie = DamageAllocation.rollDice(1,6);
			if (cloakDie == 1 || cloakDie == 2) {
				//  no effect
			} else if (cloakDie == 3 || cloakDie == 4) {
					damage = (int) (Math.round((double) damage / 2));
			} else if (cloakDie == 5 || cloakDie == 6) {
				damage = (int) (Math.round((double) damage / 4));
			} else if (cloakDie >= 7) {
				damage = 0;
			}
			System.out.print("\tAdjusted damage for cloak: " + damage);
			System.out.println();
		}

		return damage;
	}
	
	
	
	
	
	public static int type1Phaser(int total) {
		int intPhaser1[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76},   //  distance
							  {9, 8, 7, 6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  die roll 1 
							  {8, 7, 6, 5, 5, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 2
							  {7, 5, 5, 4, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 3
							  {6, 4, 4, 4, 4, 3, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 4, 4, 3, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
							  {4, 4, 3, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.println("Phaser-I");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 75);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;
		
//		if (targetShip.cloakOn) {
//			effectiveDistance = effectiveDistance + 5;
//		}
				
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 75);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intPhaser1[die][distanceInput];
//			int damage = intPhaser1[EWadjDie][EWadjDist];	//  EWadj[0]=adj die roll / EWadj[1]=adj distance
//			damage = AdjustDamageForCloak(damage);
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	
	
	
	
	public static int type2Phaser(int total) {
		int intPhaser2[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51},   //  distance
							  {6, 5, 5, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  die roll 1
							  {6, 5, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 2
							  {6, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 3
							  {5, 4, 4, 3, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 4
							  {5, 4, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
							  {5, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.println("Phaser-II");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 50);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;
		
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, distanceInput, 50);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intPhaser2[EWadjDie][distanceInput];
//			int damage = intPhaser2[EWadjDie][EWadjDist];	//  EWadj[0]=adj die roll / EWadj[1]=adj distance
//			damage = AdjustDamageForCloak(damage);
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	
	
	
	
	//  TYPE 3 PHASER
	public static int type3Phaser(int total) {
		int intPhaser3[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16},   //  distanceInputance
							  {4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  die roll 1
							  {4, 4, 4, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 2
							  {4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 3
							  {4, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 4
							  {4, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
							  {3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.println("Phaser-III");
		
		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 15);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 15);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intPhaser3[EWadjDie][distanceInput];
//			int damage = intPhaser3[EWadjDie][EWadjDist];	//  EWadj[0]=adj die roll / EWadj[1]=adj distance
//			damage = AdjustDamageForCloak(damage);
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	
	
	
	
	//  TYPE 4 PHASER
	public static int type4Phaser(int total) {
		int intPhaser4[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101},   //  distance
							  {20,20,20,20,20,20,20,15,12,10, 8, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1,  0},   //  die roll 1
							  {20,20,20,20,20,20,15,12,11, 9, 8, 6, 6, 6, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0},   //  die roll 2
							  {20,20,20,20,15,15,12,11,10, 8, 7, 5, 5, 5, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0},   //  die roll 3
							  {20,20,20,20,15,15,11,10, 9, 8, 6, 4, 4, 4, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0},   //  die roll 4
							  {15,15,15,15,12,12,10, 9, 8, 7, 5, 3, 3, 3, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0},   //  die roll 5
							  {15,15,15,15,10,10, 9, 8, 7, 6, 5, 3, 3, 3, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;

		System.out.println();
		System.out.println("Phaser-IV");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 100);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, distanceInput, 100);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intPhaser4[EWadjDie][distanceInput];
//			int damage = intPhaser4[EWadjDie][EWadjDist];	//  EWadj[0]=adj die roll / EWadj[1]=adj distance
//			damage = AdjustDamageForCloak(damage);
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	
	
	
	
	//  PHOTON TORPEDO
	public static int photonTorpedo(int total) {
		int intPhoton[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31},   //  distance
							 {0, 0, 5, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  Standard (int type = 1)
							 {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},   //  Proximity (int type = 2) 
							 {6, 6, 5, 4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Overloaded (int type = 3)

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int photonDamage = 0;
		double energy = 0;
		int feedbackDamage = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.print("Photon Torpedo: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [P]roximity [O]verload", "SPO");
		if (weaponTypeNum == 3) {
			System.out.print("Total Energy [4.5-8.0]:   ");				//  If type=3 then get energy allocated value
			energy = Driver.getNumberDoubleNoCancel(4.5, 8.0);
		} 

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 30);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		if (weaponTypeNum == 1) {
			photonDamage = 8;
		} else if (weaponTypeNum == 2) {
			photonDamage = 4;
		} else if (weaponTypeNum == 3) {
			photonDamage = (int) (energy * 2);
		}
		
		if (Driver.TESTING) {
			System.out.println("weaponTypeInput: " + weaponTypeNum);
			System.out.println("energy: " + energy);
			System.out.println("photonDamage: " + photonDamage);
		}
		
		for(int i = 0; i < numberInput; i++) {
			int damage = 0;
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 30);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			if (EWadjDie <= intPhoton[weaponTypeNum][EWadjDist]) {
				System.out.print("HIT!  ");
				damage = damage + photonDamage;
				total = total + damage;
			} else {
				System.out.print("Miss  ");
			}
		}
		System.out.println();

		if (weaponTypeNum == 3 && effectiveDistance <= 1) {
			feedbackDamage = (int) ((double) numberInput * (energy - 4));
			System.out.println("---------------------------------------------------------");
			System.out.println("Feedback Damage: " + feedbackDamage + "  (On facing shield of firing ship)");
			System.out.println("---------------------------------------------------------");
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}

	
	
	
	
	//  PLASMATIC PULSAR DEVICE
	public static int plasmaticPulsarDevice(int total) {
		int intPlasmaticPulsar[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41},   //  distance
									  {0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},   //  hit probability 
									  {0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}};  //  damage

		String PlasmaticPulsarSplash[][] = {{"0", "1",     "2",     "3",     "4",     "5",     "6"    },   //  Chart is in reverse order
											{"0", "0+1+0", "1+1+0", "1+1+1", "1+2+1", "1+3+1", "1+4+1"},
											{"0", "1+0",   "1+1",   "2+1",   "2+2",   "3+2",   "3+3"  }};
		
		total = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.println("Plasmatic Pulsar");

		System.out.print("Do you need to roll for wave-lock (first pulse or wave-lock lost)? ");
		String input = Driver.getInput("YN");
			
		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, 75);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;
		
//		if (targetShip.cloakOn) {
//			effectiveDistance = effectiveDistance + 5;
//		}
				
		int die = DamageAllocation.rollDice(2,6);
		int EWadj[] = GetEWadjustment(die, effectiveDistance, 75);
		int EWadjDie = EWadj[0];
		int EWadjDist = EWadj[1];
		int damage = intPlasmaticPulsar[2][distanceInput];
		
		if (input.equalsIgnoreCase("Y")) {
			if (die <= intPlasmaticPulsar[1][distanceInput]) {
				System.out.println("Wave-lock ACHEIVED this impulse. Total damage to apply " + damage);
				System.out.println("Volley Damage: " + PlasmaticPulsarSplash[1][damage] + " (3 shields)");
				System.out.print("        or     " + PlasmaticPulsarSplash[2][damage] + "   (2 shields, LOF directly along hex side");
				if (damage == 1 || damage == 3 || damage == 5) {
					System.out.print(", extra pt to stronger shield");
				}
				System.out.print(")\n");
				total = damage;
			} else {
				System.out.println("Wave-lock FAILED this impulse. Total damage to apply " + damage);
			}
		} else {
			System.out.println("Wave-lock MAINTAINED this impulse.");
			System.out.println("Volley Damage: " + PlasmaticPulsarSplash[1][damage] + " (3 shields)");
			System.out.print("        or     " + PlasmaticPulsarSplash[2][damage] + "   (2 shields, LOF directly along hex side");
			if (damage == 1 || damage == 3 || damage == 5) {
				System.out.print(", extra pt to stronger shield");
			}
			System.out.print(")\n");
			total = damage;
		}
		
		System.out.println();
		System.out.println("Volley Damage: " + (total));
		System.out.println("Go DIRECTLY to Damage Allocation");
		
		return total;
	}

	
	
	
	
	//  PLASMA TORPEDO
	public static int plasmaTorpedo(int total) {
		int intPlasma[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31},   //  distance
				  			 {50,50,50,50,50,50,50,50,50,50,50,35,35,35,35,35,25,25,25,25,25,20,20,20,20,20,10,10,10, 5, 1, 0},   //  Type R
				  			 {30,30,30,30,30,30,30,30,30,30,30,22,22,22,22,22,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0},   //  Type GII
				  			 {20,20,20,20,20,20,20,20,20,20,20,15,15,15,15,15,10,10,10, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  Type G
				  			 {20,20,20,20,20,20,15,15,15,15,15,10,10, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  //  Type F
							 {10,10,10,10,10,10, 8, 8, 8, 8, 8, 5, 5, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Type D (fighters)

		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.print("Plasma Torpedo: ");
		int weaponTypeNum = GetWeaponType("Type [R] [2]G [G] [F] [D]", "R2GFD");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		if (effectiveDistance >= 31) {
			effectiveDistance = 31;			
		}

		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int damage = intPlasma[weaponTypeNum][effectiveDistance];
			total = total + damage;
		}
		if (effectiveDistance <= 1) {
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
		int intFusion[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25},   //  distance
							 {13, 8, 6, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},   //  die roll 1
							 {11, 8, 5, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  die roll 2
							 {10, 7, 4, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 3
							 { 9, 6, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 4
							 { 8, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
							 { 8, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
	
		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		if (effectiveDistance >=3 && effectiveDistance <=10) {
			effectiveDistance = 3;
		} else if (effectiveDistance >= 11 && effectiveDistance <= 15) {
			effectiveDistance = 4;			
		} else if (effectiveDistance >= 16 && effectiveDistance <= 24) {
			effectiveDistance = 5;			
		} else if (effectiveDistance >= 25) {
			effectiveDistance = 6;			
		}
		
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, distanceInput, 24);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intFusion[EWadjDie][EWadjDist];
//			damage = AdjustDamageForCloak(damage);
			total = total + damage;
		}
		
		System.out.println();
		System.out.print("Volley Damage: " + (total-startTotal));
		
		return total;
	}
	
	
	
	
	
	//  FUSION BEAM OVERLOADED (int type 2) / SUICIDE (int type 3)
	public static int fusionBeamOverloaded(int weaponTypeNum, int total) {
		int intFusionOver[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},   //  distance
							     {19,12, 9, 6, 6, 6, 6, 6, 6, 0},   //  die roll 1
								 {16,12, 7, 4, 4, 4, 4, 4, 4, 0},   //  die roll 2
								 {15,10, 6, 3, 3, 3, 3, 3, 3, 0},   //  die roll 3
								 {13, 9, 4, 1, 1, 1, 1, 1, 1, 0},   //  die roll 4
								 {12, 7, 4, 1, 1, 1, 1, 1, 1, 0},   //  die roll 5
								 {12, 6, 3, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int startTotal = total;
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 8);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intFusionOver[EWadjDie][EWadjDist] * (weaponTypeNum-1);  //  Overload=1 (2-1), Suicide=2 (3-1)
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
		int intDisruptor[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41},   //  distance
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},   //  Standard (type = 1)
				  			    {6, 5, 5, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  Overloaded (type = 2)
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},   //  DERFACS (type = 3)
				  			    {0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},   //  UIM (type = 4)
				  			    {6, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Overloaded/UIM (type = 5)
		
		int intDisruptorDamage[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41},   //  distance
									  { 0, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},   //  Standard (type = 1)
									  {10,10, 8, 8, 8, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  Overloaded (type = 2)
		
		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.print("Disruptor Bolt: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [O]verload [D]ERFACS [U]IM O[V]erload/UIM", "SODUV");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;
		
		int damageType = 1;
		if (weaponTypeNum == 2 || weaponTypeNum == 5) {
			damageType = 2;
		}
		
		if (Driver.TESTING) {
			System.out.println("type: " + weaponTypeNum);
			System.out.println("damageType: " + damageType);
		}
		
		int feedbackDamage = 0;
		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int damage = 0;
			int die = DamageAllocation.rollDice(1,6);
//			if (targetShip.cloakOn) {
//				if (die <= intDisruptor[weaponTypeNum][effectiveDistance]) {
//					damage = damage + intDisruptorDamage[damageType][effectiveDistance];
//					System.out.print("HIT!  ");
//					total = total + damage;
//				} else {
//					System.out.print("Miss  ");
//				}
//			} else {
				int EWadj[] = GetEWadjustment(die, effectiveDistance, 40);
				int EWadjDie = EWadj[0];
				int EWadjDist = EWadj[1];
//			System.out.println("die: " + die + "\tintDisruptor[type][distanceInput]: " + intDisruptor[type][distanceInput]);
				if (EWadjDie <= intDisruptor[weaponTypeNum][EWadjDist]) {
					damage = damage + intDisruptorDamage[damageType][EWadjDist];
					System.out.print("HIT!  ");
					total = total + damage;
				} else {
					System.out.print("Miss  ");
				}
				
//			}
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
		int intTracRep[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26},   //  distance
							  {20,20,20,20,20,20,18,18,18,12,12,12,12, 8, 8, 8, 8, 8, 8, 3, 3, 3, 3, 3, 3, 3, 0},   //  die roll 1
							  {20,20,20,20,20,20,15,15,15, 9, 9, 9, 9, 5, 5, 5, 5, 5, 5, 2, 2, 2, 2, 2, 2, 2, 0},   //  die roll 2
							  {20,20,20,20,18,18,12,12,12, 6, 6, 6, 6, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 0},   //  die roll 3
							  {20,20,20,20,15,15, 9, 9, 9, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 4
							  {18,18,18,18,12,12, 6, 6, 6, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
							  {15,15,15,15, 9, 9, 3, 3, 3, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6

		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;

		System.out.println();
		System.out.println("Tractor-Repulsor Beam");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 25);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
			int damage = intTracRep[EWadjDie][EWadjDist];
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
		int hellbore[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41},   //  distance
						    {20,20,17,15,15,13,13,13,13,10,10,10,10,10,10,10, 8, 8, 8, 8, 8, 8, 8, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},   //  Base (Standard) (int type = 1)
							{30,30,25,22,22,19,19,19,19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  Overloaded (int type = 2)
						    {10,10, 9, 8, 8, 7, 7, 7, 7, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},   //  Direct-Fire (int type = 3)
							{11,11,10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0}};  //  roll 2d6 <= this number is a hit 

		int numberInput = 0;
		int distanceInput = 0;
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.print("Hellbore: ");
		int weaponTypeNum = GetWeaponType("Type [S]tandard [O]verloaded [D]irect-Fire", "SOD");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		int startTotal = total;
		for(int i = 0; i < numberInput; i++) {
			int die = DamageAllocation.rollDice(2,6);
			int EWadj[] = GetEWadjustment(die, effectiveDistance, 40);
			int EWadjDie = EWadj[0];
			int EWadjDist = EWadj[1];
//			System.out.println("Die roll: " + die);
			if (EWadjDie <= hellbore[4][EWadjDist]) {
				System.out.print("HIT!  ");
				int damage = hellbore[weaponTypeNum][effectiveDistance];
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
		int effectiveDistance = 0;
		
		System.out.println();
		System.out.print("Probe:");

		System.out.print("Number:   ");
		numberInput = Driver.getNumberNoCancel(0, 200);

		System.out.print("Distance: ");
		distanceInput = Driver.getNumberNoCancel(0, MAXDIST);
//		effectiveDistance = SensorScannerMod(distanceInput);
		effectiveDistance = distanceInput;

		for (int i = 1; i <= numberInput; i++) {
			die = DamageAllocation.rollDice(1, 6);
			if (die >= effectiveDistance) {
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
