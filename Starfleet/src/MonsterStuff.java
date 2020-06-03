
import java.math.*;

public class MonsterStuff {

	public static Starship currentMonster;
	public static Starship currentPlanet;

	public static int labResearch[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10},   //  distance
										 {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},   //  die roll 1 
										 { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0},   //  die roll 2
										 { 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0},   //  die roll 3
										 { 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0},   //  die roll 4
										 { 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0},   //  die roll 5
										 { 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0}};  //  die roll 6
	
	public static int crewUnitsLost[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11},   //  distance
										   {4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 0},   //  die roll 1 
										   {4, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 0},   //  die roll 2
										   {3, 3, 3, 2, 2, 2, 0, 0, 0, 0, 0, 0},   //  die roll 3
										   {3, 3, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0},   //  die roll 4
										   {2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 5
										   {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  //  die roll 6
	
	public static int mindWipe[][] = {{0, 1, 2, 3, 4, 5},   //  distance
									  {6, 5, 4, 3, 2, 1},   //  die roll 1 
									  {5, 4, 3, 2, 1, 0},   //  die roll 2
									  {4, 3, 2, 1, 0, 0},   //  die roll 3
									  {3, 2, 1, 1, 0, 0},   //  die roll 4
									  {2, 2, 1, 0, 0, 0},   //  die roll 5
									  {2, 1, 0, 0, 0, 0}};  //  die roll 6
	
	public static int MorayEelDamage[] = {0, 20, 18, 15, 12, 10, 5};		// Die roll of {0, 1, 2, 3, 4, 5, 6}
	
	public static int PlanetCrusherDamage[] = {0, 40, 30, 20, 10, 5, 1};	// Die roll of {0, 1, 2, 3, 4, 5, 6}
	
	public static int MonsterDamage() {
		
		int monsterDamage = 0;
		int whichMonster = 1;
		
		if (whichMonster != -1) {
			System.out.println();
			int print = ShipSetup.PrintCurrentThingsInGame("MONSTER", "");
			System.out.println();
			System.out.print("Which monster attacked? [RETURN to cancel] ");
//			whichMonster = -5;
			whichMonster = ShipSetup.GetAdjustedInput(print, "MONSTER", "");

			currentMonster =  Driver.currentGameYard.list[whichMonster];
			
			if (currentMonster.name == "Planet Crusher") {						//  SM1.0 - PLANET CRUSHER
				monsterDamage = PlanetCrusher();
				
			} else if (currentMonster.name == "Space Amoeba") {					//  SM2.0 - SPACE AMOEBA
				monsterDamage = SpaceAmoeba();
				
			} else if (currentMonster.name == "Moray Eel") {					//  SM3.0 - MORAY EEL
				monsterDamage = MorayEel();
				
			} else if (currentMonster.name == "Cosmic Cloud") {					//  SM4.0 - COSMIC CLOUD
				monsterDamage = CosmicCloud();
				
			} else if (currentMonster.name == "Sunsnake") {						//  SM5.0 - SUNSNAKE
				monsterDamage = Sunsnake();
				
			} else if (currentMonster.name == "Mind Monster") {					//  SM6.0 - MIND MONSTER
				monsterDamage = MindMonster();
				
			} else if (currentMonster.name == "Space Dragon") {					//  SM7.0 - SPACE DRAGON
				monsterDamage = SpaceDragon();
				
			} else if (currentMonster.name.contains("Arastoz")) {		//  SM8.0 - ARASTOZ
				monsterDamage = ArastozAttack(currentMonster.shipType);
				
			}
			
//			System.out.println("Total Monster Damage: " + totalMonsterDamage);
		}
		
		return monsterDamage;
	}

	public static int PlanetCrusher() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = PlanetCrusherDamage[die];
		System.out.println();
		System.out.println("Planet Crusher does " + damage + " points of damage.  Apply as normal weapon damage.");

//		DamageToPlanet(-1, damage);
//		damage = 0;

		return damage;
	}
		
	public static int SpaceAmoeba() {
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Closest distance to Space Amoeba this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 9);
		if (dist >= 0) {
			damage = labResearch[die][dist] * 2;
			System.out.println();
			System.out.println("Space Amoeba does " + damage + " points of damage.  Apply as normal weapon damage.");
		
//			DamageAllocation.DamageAlloc(-1, damage);
//			damage = 0;
			}
		
		return damage;
	}
	
	public static int MorayEel() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = MorayEelDamage[die];
		System.out.println();
		System.out.println("Moray Eel does " + damage + " points of damage.  Damage BYPASSES shields.");

//		DamageAllocation.DamageAlloc(-1, damage);
//		damage = 0;

		return damage;
	}
				
	public static int CosmicCloud() {
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Closest distance to Cosmic Cloud this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 10);
		if (dist >= 0) {
			System.out.print("Were shields at maximum power? ");
			String yesOrNo = Driver.getInput("YN");
			int multiplier = 1;
			if (yesOrNo.equalsIgnoreCase("N")) {
				multiplier = 2;
			}
			damage = crewUnitsLost[die][dist] * multiplier;
			System.out.println();
			System.out.println("Cosmic Clouds kills " + damage + " crew units.  No ship damage.");
		}
		
		damage = 0;
		return damage;
	}
					
	public static int Sunsnake() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = MorayEelDamage[die];
		System.out.println();
		System.out.println("Sunsnake does " + damage + " points of damage.  Apply as normal weapon damage.");

//		DamageAllocation.DamageAlloc(-1, damage);
//		damage = 0;

		return damage;
	}
						
	public static int MindMonster() {
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Closest distance to Mind Monster this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 10);
		if (dist >=0 ) {
			System.out.print("Were shields at maximum power? ");
			String yesOrNo = Driver.getInput("YN");
			int multiplier = 1;
			if (yesOrNo.equalsIgnoreCase("N")) {
				die = 1;
				dist = dist - 1;
				if (dist == -1) {
					dist = 0;
				}
				multiplier = 2;
			}
			damage = mindWipe[die][dist] * multiplier;
			System.out.println();
			System.out.println("Mind Monster mind-wipes " + damage + " crew units.  No ship damage.");
		}
		
		damage = 0;
		return damage;
	}
							
	public static int MindMonsterAttacksPlanet() {			//  Returns mind wipe data as damage to planet
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Planet's closest distance to Mind Monster this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 10);
		if (dist >=0 ) {
			damage = mindWipe[die][dist];
			System.out.println("Mind Monster mind wipes " + damage + " crew units.");
		}
		
		return damage;
	}

	public static int SpaceDragon() {
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Need code for Space Dragon damage.");
		
		return damage;
	}
		
	public static int ArastozAttack(String size) {
		
		System.out.println("size: " + size);

		int monsterSize = 0;
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		
		monsterSize = Integer.parseInt(size.substring(8,9));
		
		System.out.println("monsterSize: " + monsterSize);
		
		System.out.print("Closest distance to Arastoz this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 9);
		if (dist >= 0) {
			damage = labResearch[die][dist] * monsterSize;
			System.out.println();
			System.out.println("Arastoz does " + damage + " points of damage.  Apply as normal weapon damage.");
		}

		return damage;
	}
	
	public static void DidArastozCombine() {

		String yesOrNo = "";
		System.out.println();
		System.out.print("Did any parts of Arastoz combine? ");
		yesOrNo = Driver.getInputNoCancel("YN");
		if (yesOrNo.contentEquals("Y")) {
			System.out.println();
			System.out.println("     Name\tBase\tRemaining");                   //  Print only Arastoz monster list
			System.out.println("     Name\tStr\tHit Pts");                   //  Print only Arastoz monster list
			System.out.println();
			for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
				String extraSpace = ShipSetup.getExtraSpaces(i, 2);
				
				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
				}
				if(Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
					System.out.print(extraSpace + i + ")  " + Driver.currentGameYard.list[i-1].name);
					ShipSetup.getExtraSpaces(i, 2);
					System.out.print("\t " + Driver.currentGameYard.list[i-1].speed + "\t " + Driver.currentGameYard.list[i-1].shipType.substring(8,10) + "\t  " + Driver.currentGameYard.list[i-1].ssd[24].remaining);
					System.out.println();
				}
			}
			
//			int arastoz1RemainingHP = 0;
			int arastoz1DamageSoFar = 0;
			int arastoz1SizeClass = 0;
//			int arastoz2RemainingHP = 0;
			int arastoz2DamageSoFar = 0;
			int arastoz2SizeClass = 0;
			int newCombinedSizeClass = 0;
//			boolean answerSame = true;
			int arastozNum1 = 0;
			
			while (arastozNum1 != -1) {
				System.out.println();
				System.out.println("Which 2 Arastoz Monsters have combined? [RETURN to cancel]");
				System.out.print("#1: ");
				arastozNum1 = Driver.getNumber(1, 4);
				if (arastozNum1 != -1) {
					arastozNum1--;
					System.out.print("#2: ");
					int arastozNum2 = Driver.getNumber(1, 4);

					if (arastozNum2 != -1) {
						arastozNum2--;
						arastoz1SizeClass = Integer.parseInt(Driver.currentGameYard.list[arastozNum1].sizeClass);
						arastoz1DamageSoFar = Driver.currentGameYard.list[arastozNum1].ssd[24].numOfThisPart - Driver.currentGameYard.list[arastozNum1].ssd[24].remaining;
						arastoz2SizeClass = Integer.parseInt(Driver.currentGameYard.list[arastozNum2].sizeClass);
						arastoz2DamageSoFar = Driver.currentGameYard.list[arastozNum2].ssd[24].numOfThisPart - Driver.currentGameYard.list[arastozNum2].ssd[24].remaining;
						
						String newSuffix = "";
						String letterSuffix = "";
						if (arastozNum1 != arastozNum2) {
							int combinedDamageSoFar = arastoz1DamageSoFar + arastoz2DamageSoFar;
							newCombinedSizeClass = arastoz1SizeClass + arastoz2SizeClass;
							int newCombinedHP = ((int) Math.round(Math.pow(2, newCombinedSizeClass-1) * 100 * Driver.MonsterBPVModifier)) - combinedDamageSoFar;
							
							
							newSuffix = Integer.toString(newCombinedSizeClass) + "x";
							letterSuffix = Driver.currentGameYard.list[arastozNum2].name.substring(8, Driver.currentGameYard.list[arastozNum2].name.length());
							
							//  Combine piece 1 and 2 into #1's spot
							Driver.currentGameYard.list[arastozNum1].ssd[24].remaining = newCombinedHP;   
							Driver.currentGameYard.list[arastozNum1].ssd[24].numOfThisPart = (int) Math.round(Math.pow(2.0, newCombinedSizeClass-1) * 100 * Driver.MonsterBPVModifier);
							Driver.currentGameYard.list[arastozNum1].shipType = "Arastoz " + newSuffix;
							Driver.currentGameYard.list[arastozNum1].sizeClass = Integer.toString(newCombinedSizeClass);
							Driver.currentGameYard.list[arastozNum1].name = Driver.currentGameYard.list[arastozNum1].name + letterSuffix;
							Driver.currentGameYard.list[arastozNum1].speed = 16 - (2 * newCombinedSizeClass);
							
							RemoveMonsterFromGame(arastozNum2);									//  Remove #2 piece 

							if (newCombinedSizeClass == 4) {
								Driver.currentGameYard.list[arastozNum1].name = "Arastoz ABCD";
								System.out.println();
								System.out.println("*** Arastoz is fully combined! ***");
							}
							
							System.out.println();
							System.out.println("     \t\t\t\tBase\tRemaining");					//  Print only Arastoz monster list
							System.out.println("     Name\t\tSpeed\tStr\tHit Pts");				//  Print only Arastoz monster list
							System.out.println();
							for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
								String extraSpace = ShipSetup.getExtraSpaces(i, 2);
								
								if(Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
									System.out.print(extraSpace + i + ")  " + Driver.currentGameYard.list[i-1].name + "    ");
									ShipSetup.getExtraSpaces(i, 2);
									System.out.print("\t " + Driver.currentGameYard.list[i-1].speed + "\t " + Driver.currentGameYard.list[i-1].shipType.substring(8,10) + "\t  " + Driver.currentGameYard.list[i-1].ssd[24].remaining);
									System.out.println();
								}
							}
							arastozNum1 = -2;
							
						} else {
							System.out.println();
							System.out.println("*** An Arastoz unit cannot self-combine!  Try again. ***");
							arastozNum1 = -2;
						}
						if (newCombinedSizeClass == 4) {
							arastozNum1 = -1;
						}
					}
				}
			}

		}
	}
	
	public static void RemoveMonsterFromGame(int whichShip) {
		for (int n = whichShip; n <= Driver.currentGameYard.numShips; n++) {	
			Driver.currentGameYard.list[n] = Driver.currentGameYard.list[n+1];
		}
		Driver.currentGameYard.numShips--;
	}
	
	public static int MonsterDamageFromShip(int numMonster, int damageToMonster) {
//		int whichMonster = -5;
		
		if (numMonster < 0) {
			System.out.println();
			int print = ShipSetup.PrintCurrentThingsInGame("MONSTER", "HEALTH");
			System.out.println();
			System.out.print("Which monster to deal damage to? [RETURN to cancel] ");
			numMonster = ShipSetup.GetAdjustedInput(print, "MONSTER", "");
		}
		
		if (numMonster >= 0) {
			currentMonster = Driver.currentGameYard.list[numMonster];

			System.out.println(currentMonster.name + " takes " + damageToMonster + " damage points.");

			currentMonster.ssd[24].remaining = currentMonster.ssd[24].remaining - damageToMonster;
			System.out.println(currentMonster.name + " has " + currentMonster.ssd[24].remaining + " remaining health points.");
			if (currentMonster.ssd[24].remaining <= 0) {
				System.out.println(currentMonster.name + " has been defeated.");
				Driver.currentGameYard.removeShipFromShipyard(numMonster+1);
			}
			damageToMonster = 0;
		}
		return damageToMonster;
	}
	
	public static int DamageToPlanet(int numPlanet, int damageToPlanet) {
//		int whichPlanet = -5;
		
		if (numPlanet < 0) {
			System.out.println();
			int print = ShipSetup.PrintCurrentThingsInGame("OTHER", "HEALTH");
			System.out.println();
			System.out.print("Which planet to deal damage to? [RETURN to cancel] ");
			numPlanet = ShipSetup.GetAdjustedInput(print, "OTHER", "");
		}

		if (numPlanet >= 0) {
			currentPlanet = Driver.currentGameYard.list[numPlanet];
			
			System.out.println(currentPlanet.name + " takes " + damageToPlanet + " damage points.");
			
			currentPlanet.ssd[24].remaining = currentPlanet.ssd[24].remaining - damageToPlanet;
			System.out.println(currentMonster.name + " has " + currentPlanet.ssd[24].remaining + " remaining health points.");
			if (currentPlanet.ssd[24].remaining <= 0) {
				System.out.println(currentPlanet.name + " has been destroyed.");
				Driver.currentGameYard.removeShipFromShipyard(numPlanet+1);
			}
			damageToPlanet = 0;
		}
		return damageToPlanet;
	}
	
	public static int MonsterAttacksPlanet(int damageToPlanet) {
		int whichPlanet = -5;
		
		while (whichPlanet < 0) {
			System.out.println();
			
			//  Print Current Planets In Game
			
			int print = ShipSetup.PrintCurrentThingsInGame("OTHER", "HEALTH");
			System.out.println();
			System.out.print("Which planet to deal damage to? [RETURN to cancel] ");
			whichPlanet = -5;
			whichPlanet = ShipSetup.GetAdjustedInput(print, "OTHER", "");
			System.out.println("whichPlanet: " + whichPlanet);

			if(whichPlanet == -1) {
				break;
			}
			
			System.out.println(Driver.currentGameYard.list[whichPlanet].name + " takes " + damageToPlanet + " damage points.");

			Driver.currentGameYard.list[whichPlanet].ssd[24].remaining = Driver.currentGameYard.list[whichPlanet].ssd[24].remaining - damageToPlanet;
			System.out.println(Driver.currentGameYard.list[whichPlanet].name + " has " + Driver.currentGameYard.list[whichPlanet].ssd[24].remaining + " remaining health points.");
			if (Driver.currentGameYard.list[whichPlanet].ssd[24].remaining <= 0) {
				System.out.println(Driver.currentGameYard.list[whichPlanet].name + " has been destroyed.  Gave Over!");
				Driver.currentGameYard.removeShipFromShipyard(whichPlanet);
			}
			damageToPlanet = 0;
		}
		return damageToPlanet;
	}

	public static void MonsterScenarioCheck() {
		String monsterName = "";
		int monsterDamageRemaining = 0;
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
				monsterName = Driver.currentGameYard.list[i].name;
				monsterDamageRemaining = Driver.currentGameYard.list[i].ssd[24].remaining;
			}
		}
		
		if (Driver.labResearchRequired == 0) {
			System.out.println();
			System.out.println("No Lab research is required to defeat monster.");
			System.out.println("Defeat monster through normal wepaons damage.");
			for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
				if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
					System.out.println(Driver.currentGameYard.list[i].name + " has " + Driver.currentGameYard.list[i].ssd[24].remaining + " HP remaining.");
				}
			}
		}
		
		if (monsterName != "" && Driver.labResearchRequired > 0) {
			System.out.println("Lab Research Points required on");
			System.out.println(monsterName + " before rolling for");
			System.out.println("Victory Conditions: " + Driver.labResearchRequired);
			ShipSetup.PrintCurrentThingsInGame("Monster", "Health");
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("Race\t\tLab Points");
			System.out.println("\t\tAcquired");
			System.out.println("--------------------------------");
			
			String extraTab = "";
			int totalPoints = 0;
			for (int i = 0; i <= Driver.labResearches.length-1; i++) {
				if (Driver.labResearches[i].acquiredPoints > 0) {
					if (Driver.labResearches[i].race.length() < 5) {
						extraTab = "\t";
					}
					System.out.print(Driver.labResearches[i].race + extraTab + "\t" + Driver.labResearches[i].acquiredPoints);
					if (Driver.labResearches[i].acquiredPoints >= Driver.labResearchRequired) {
						System.out.print("\t<-- Lab Research complete.  Roll for Victory Conditions in Main Menu.");
					}
					System.out.println();
					totalPoints = totalPoints + Driver.labResearches[i].acquiredPoints;
				}
			}
			if (totalPoints == 0) {
				System.out.println("None\t\tNone");
			}
			System.out.println("--------------------------------");
			
		} else if (monsterName == "") {
			System.out.println();
			System.out.println("There is no monster in the game to collect lab research on.");
		}
	}

	public static void RollForVictoryConditions() {
		String monsterName = "";
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
				monsterName = Driver.currentGameYard.list[i].name;
			}
		}
		
		if (Driver.labResearchRequired == 0) {
			System.out.println();
			System.out.println("No Lab research is required to defeat monster.");
			System.out.println("Defeat monster through normal wepaons damage.");
			for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
				if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
					System.out.println(Driver.currentGameYard.list[i].name + " has " + Driver.currentGameYard.list[i].ssd[24].remaining + " HP remaining.");
				}
			}
		
		} else if (monsterName != "" && Driver.labResearchRequired > 0) {
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                  MONSTER SCENARIO VICTORY CONDITIONS                     |");		
			System.out.println("|==========================================================================|");
			System.out.println("|  DIE ROLL     HOW TO DESTORY MONSTER                                     |");		
			System.out.println("|--------------------------------------------------------------------------|");
			System.out.println("|     1    :    Monster can be destroyed by a suicide shuttlecraft.        |");		
			System.out.println("|                                                                          |");		
			System.out.println("|     2    :    Monster can be destroyed if a tractor beam is              |");		
			System.out.println("|                   attached to it.                                        |");		
			System.out.println("|                                                                          |");		
			System.out.println("|     3    :    Monster can be destroyed by 200 points of damage with      |");		
			System.out.println("|                   any weapons.                                           |");		
			System.out.println("|                                                                          |");		
			System.out.println("|     4    :    Monster can be destroyed by a probe used a weapon.         |");		
			System.out.println("|                                                                          |");		
			System.out.println("|     5    :    Insufficient data.  Acculmulate 100 more points of         |");		
			System.out.println("|                   lab research data and try again.                       |");		
			System.out.println("|                                                                          |");		
			if (Driver.MonsterScenario != 5) {  //  All other monsters except Sunsnake
				System.out.println("|     6    :    Communication established with monster.  It is friendly,   |");		
				System.out.println("|                   and you are not required to destroy it.  If you have   |");		
				System.out.println("|                   scored damage on it, you lose this scenario.           |");
			} else if (Driver.MonsterScenario == 5) {  //  Sunsnake only
				System.out.println("|     6    :    Monster cannot be destroyed by forces at your disposal.    |");		
				System.out.println("|                   Save as many crew on the planet as possible and        |");		
				System.out.println("|                   leave the system.                                      |");
			}
			System.out.println("|==========================================================================|");
			System.out.println();
			int die = DamageAllocation.rollDice(1, 6);
			if (die == 1) {
				System.out.println("Your Victory Condition:");
				System.out.println("   Destroy the monster with a suicide shuttlecraft.");
				
			} else if (die == 2) {
				System.out.println("Your Victory Condition:");
				System.out.println("   Destroy the monster by attaching a tractor beam to it.");
				
			} else if (die == 3) {
				System.out.println("Your Victory Condition:");
				System.out.println("   Destroy the monster by 200 points of damage from any weapon(s).");
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
						System.out.println("   " + Driver.currentGameYard.list[i].name + " has " + Driver.currentGameYard.list[i].ssd[24].remaining + " HP remaining.");
						if (Driver.currentGameYard.list[i].ssd[24].remaining <= 0) {
							System.out.println();
							System.out.println("You win the scenario!");
						}
					}
				}
				
			} else if (die == 4) {
				System.out.println("Your Victory Condition:");
				System.out.println("   Destroy the monster by using a probe as a weapon.");
				
			} else if (die == 5) {
				System.out.println("Insufficient Data:");
				System.out.println("   Acculmulate 100 more points of lab research data and try again.");
				Driver.labResearchAquired = 0;
				Driver.labResearchRequired = 100;
				
			} else if (die == 6 && Driver.MonsterScenario != 5) {      //  All other monsters except Sunsnake
				System.out.println("Your Victory Condition:");
				System.out.println("   Communication established with monster.");
				System.out.println("   It is friendly, and you are not required to destroy it.");		
				int damageTaken = 0;
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
						damageTaken = damageTaken + Driver.currentGameYard.list[i].ssd[24].numOfThisPart - Driver.currentGameYard.list[i].ssd[24].remaining;
						System.out.print("   " + Driver.currentGameYard.list[i].name + " has taken " + damageTaken + " damage and ");
					}
					if (damageTaken == 0) {
						System.out.println("you win the scenario!");	
					} else {
						System.out.println("you lose the scenario!");
					}
				}
				
			} else if (die == 6 && Driver.MonsterScenario == 5) {     //  Sunsnake
				System.out.println("Your Victory Condition:");
				System.out.println("   Monster cannot be destroyed by forces at your disposal.");		
				System.out.println("   Save as many crew on the planet as possible and leave the system.");
			}
		} else if (monsterName == "") {
			System.out.println();
			System.out.println("There is no Monster in the game, so there is no need for Victory Conditions.");
		}
	} 
	
	public static void labResearchPoints() {
		//  Also Some Monster Damage
		int labResearch[][] = {{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10},   //  distance
							   {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},   //  die roll 1 
							   { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0},   //  die roll 2
							   { 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0},   //  die roll 3
							   { 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0},   //  die roll 4
							   { 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0},   //  die roll 5
							   { 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0}};  //  die roll 6
		int raceNum = -1;
		int die = 0;
		int dist = 0;
		int numLabs = 0;
		int research = 0;
		
		System.out.println();
		System.out.println("|===============================================================================|");
		System.out.println("|                             LAB RESEARCH BY RACE                              |");
		System.out.println("|---------------+---------------+---------------+---------------+---------------|");
		String extraSpaces = "";
		String suffix = "";
		int count = 0;
		for (int i = 0; i <= Driver.labResearches.length-1; i++) {
			if (count == 4) {
				extraSpaces = ShipSetup.getExtraSpaces(Driver.labResearches[i].acquiredPoints, 3);
				suffix = extraSpaces + "    |";
				
			}
			
			System.out.print("|   " + Driver.labResearches[i].race.substring(0, 3) + ": " + Driver.labResearches[i].acquiredPoints + suffix + "  \t");
			count++;
			if (count == 5) {
				System.out.println();
				count = 0;
			}
			suffix = "";
		}
		if (count != 0) {
			for (int i = 1; i <= 5-count; i++) {
				System.out.print("|               ");
			}
			System.out.println("|");
		}
		
		System.out.println("|===============================================================================|");
		
		System.out.println();
		int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");

		int shipResearching = -1;

		boolean cont = true;
		while (cont) {
			System.out.println();
			System.out.print("Which ship is collecting research data? [RETURN for cancel] ");
			shipResearching = ShipSetup.GetAdjustedInput(print, "SHIP", "");
			if(shipResearching != -1) {
//				System.out.println(Driver.currentGameYard.list[shipResearching].name);
				String raceOfCurrentShip = Driver.currentGameYard.list[shipResearching].race;
				String raceOfCurrentShipMain = raceOfCurrentShip;
				String nameOfCurrentShip = Driver.currentGameYard.list[shipResearching].name;
				raceNum = -1;
				for (int i = 0; i <= Driver.labResearches.length-1; i++) {
					if (raceOfCurrentShip == Driver.labResearches[i].race) {
						raceNum = i;
					}
				}
				
				if (raceNum == -1) {  //  If the ship's race is CIVILIAN or ALL FLEETS
					System.out.print("Which main ship is the " + nameOfCurrentShip + " collecting data for? ");
					int shipResearchingMain = Driver.getNumber(1, Driver.currentGameYard.numStarships);
					raceOfCurrentShipMain = Driver.currentGameYard.list[shipResearchingMain-1].race;
//					System.out.println("raceOfCurrentShipMain: " + raceOfCurrentShipMain);
					for (int i = 0; i <= Driver.labResearches.length-1; i++) {
						if (raceOfCurrentShipMain == Driver.labResearches[i].race) {
							raceNum = i;
//							System.out.println("raceNum (raceOfCurrentShipMain): " + raceNum);
						}
					}
				}
				
				System.out.print("Is ship using [L]abs or a [P]robe? [RETURN for cancel] ");
				String researchType = Driver.getInput("LP");
		
				if (researchType.equalsIgnoreCase("L")) {
					die = DamageAllocation.rollDice(1,6);
					numLabs = Driver.currentGameYard.list[shipResearching].ssd[17].remaining;
					//  Find race in LabResearch array
					
					System.out.print("Closest distance this turn [0-9, RETURN to cancel]: ");
					dist = Driver.getNumber(0, 9);
					if (dist != -1) {
						System.out.println(nameOfCurrentShip + " has " + numLabs + " functioning labs.");
						research = labResearch[die][dist] * numLabs;
						System.out.println(nameOfCurrentShip + "'s lab(s) research points this turn: " + research);
//					int indivShipResearch = research + Driver.currentGameYard.list[shipResearching-1].labPoints;
//					System.out.println(nameOfCurrentShip + "'s lab(s) provide " + indivShipResearch + " points so far this game.");
//					System.out.println("raceNum: " + raceNum);
						Driver.labResearches[raceNum].acquiredPoints = Driver.labResearches[raceNum].acquiredPoints + research;
						String plural = "s";
						if (raceOfCurrentShip == "Federation") {
							plural = "";
						}
						System.out.println("TOTAL research points by the " + raceOfCurrentShipMain + plural + " so far: " + Driver.labResearches[raceNum].acquiredPoints);
					}
					
				} else if (researchType.equalsIgnoreCase("P")) {
					System.out.print("How many probes were launched at 5 hexes from target or closer [RETURN to cancel]: ");
					int numProbes = Driver.getNumber(0, 100);
					if (numProbes != -1) {
						research = numProbes * 20;
						System.out.println(nameOfCurrentShip + " research points this turn gained by probes: " + research);
//					int indivShipResearch = research + Driver.currentGameYard.list[shipResearching-1].labPoints;
//					System.out.println(nameOfCurrentShip + "'s lab(s) provide " + indivShipResearch + " points so far this game.");
//					System.out.println("raceNum: " + raceNum);
						Driver.labResearches[raceNum].acquiredPoints = Driver.labResearches[raceNum].acquiredPoints + research;
						String plural = "s";
						if (raceOfCurrentShip == "Federation") {
							plural = "";
						}
						System.out.println("TOTAL research points by the " + raceOfCurrentShipMain + plural + " so far: " + Driver.labResearches[raceNum].acquiredPoints);
					}
				}				
			} else {
				cont = false;
			}
		}
	}

	public static void AdjustMonsterHP() {
		String monsterName = "";
		int monsterDamageRemaining = 0;
		boolean cont = true;
		int whichMonsterAdjust = 0;
		
		while (cont) {
			System.out.println();
			int print = ShipSetup.PrintCurrentThingsInGame("MONSTER", "HEALTH");
			System.out.print("Which Monster to adjust HP? [RETURN to cancel] ");
			
			whichMonsterAdjust = ShipSetup.GetAdjustedInput(print, "MONSTER", "HEALTH");
			if (whichMonsterAdjust != -1) {
				currentMonster = Driver.currentGameYard.list[whichMonsterAdjust];
				
				System.out.print("What should " + currentMonster.name + "'s HP be? ");
				monsterDamageRemaining = Driver.getNumber(1, 10000);
				if (monsterDamageRemaining != -1) {
					currentMonster.ssd[24].remaining = monsterDamageRemaining;
				}
			} else {
				cont = false;
			}
		}
	}
	
	public static void ModifyMonsterBPV () {
		Driver.MonsterBPVModifier = 1;
	
		if (Driver.MonsterBPVModifierApplied == true) {
			System.out.println("Monster Modifier already applied!!!  Cannot repeat!!!");

		} else {
			double totalShipBPV = 0;
			
			for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
				
				if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
					
					int intBPV = 0;
					String thisBPV = Driver.currentGameYard.list[i].BPV;
					String race = Driver.currentGameYard.list[i].race;
					
					if (Driver.currentGameYard.list[i].BPV.length() > 0) {
						intBPV = Driver.GetEconomicBPV(race, thisBPV);
					}
					totalShipBPV = totalShipBPV + intBPV;
				}
			}
			
			System.out.println("Current Starship BPVs:");
			ShipSetup.PrintCurrentThingsInGame ("SHIP", "BPV");
			System.out.println("Starship(s) total BPV:\t" + (int) totalShipBPV);
			Driver.MonsterBPVModifier = totalShipBPV / 125.0;
			System.out.println();
			
			System.out.println("Current Monster HPs:");
			ShipSetup.PrintCurrentThingsInGame ("MONSTER", "HEALTH");

			String labResearchBlurb = "";
			if (Driver.labResearchRequired > 0) {
				System.out.println("Lab Research pts required to collect: " + Driver.labResearchRequired);
				labResearchBlurb = "and/or Lab Research ";
			}

			System.out.println();
			System.out.println("Monster Modifier:\t" + Driver.MonsterBPVModifier);
			System.out.println("Apply Monster " + labResearchBlurb + "BPV Modifier? ");
			
			String yesOrNo = Driver.getInput ("YN");
			
			if (yesOrNo.equalsIgnoreCase("Y")) {
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
						Driver.currentGameYard.list[i].ssd[24].numOfThisPart = (int) Math.round(Driver.currentGameYard.list[i].ssd[24].numOfThisPart * Driver.MonsterBPVModifier);
						Driver.currentGameYard.list[i].ssd[24].remaining = (int) Math.round(Driver.currentGameYard.list[i].ssd[24].remaining * Driver.MonsterBPVModifier);
					}
				}
				Driver.labResearchRequired = (int) Math.round(Driver.labResearchRequired * Driver.MonsterBPVModifier);
				System.out.println();
				System.out.println("Modified Monster HPs:");
				ShipSetup.PrintCurrentThingsInGame ("MONSTER", "HEALTH");
				Driver.MonsterBPVModifierApplied = true;
				
				if (Driver.labResearchRequired > 0) {
					System.out.println("Lab Research pts required to collect: " + Driver.labResearchRequired);
				}
			} else {
				System.out.println("Monster HPs have NOT been modified");
			}
		}
	}
	
	public static void ZeroOutMonsterAndLabResearchValues() {
		Driver.labResearchAquired = 0;
		Driver.labResearchRequired = 0;
		Driver.MonsterScenario = 0;
		Driver.MonsterBPVModifier = 1.0;
		Driver.MonsterBPVModifierApplied = false;
	}
	
}
