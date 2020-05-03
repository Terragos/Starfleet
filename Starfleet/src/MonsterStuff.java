
public class MonsterStuff {

	public static Starship currentMonster;

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
		
		int totalMonsterDamage = 0;
		int monsterDamage = 0;
		int whichMonster = -5;
		
		
		while (whichMonster < 0) {
			System.out.println();
			ShipSetup.PrintCurrentShipsInGameThatHaveSSD();
			System.out.print("Which monster attacked? [RETURN to cancel] ");
			whichMonster = -5;
			whichMonster = Driver.getNumber(1, Driver.currentGameYard.numShips);

			if(whichMonster == -1) {
				break;
			}

			currentMonster =  Driver.currentGameYard.list[whichMonster-1];
			
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
				monsterDamage = Arastoz(currentMonster.name);
				
			}
			
			whichMonster = -1;
			totalMonsterDamage = totalMonsterDamage + monsterDamage;

			System.out.println("Total Monster Damage: " + (totalMonsterDamage));
		}
		return totalMonsterDamage;
	}

	public static int PlanetCrusher() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = PlanetCrusherDamage[die];
		System.out.println();
		System.out.println("Planet Crusher does " + damage + " points of damage.  Apply as normal weapon damage.");
		
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
			}
		
		return damage;
	}
	
	public static int MorayEel() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = MorayEelDamage[die];
		System.out.println();
		System.out.println("Moray Eel does " + damage + " points of damage.  Damage BYPASSES shields.");

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
		
		return damage;
	}
					
	public static int Sunsnake() {
		int die = DamageAllocation.rollDice(1,6);
		int damage = MorayEelDamage[die];
		System.out.println();
		System.out.println("Sunsnake does " + damage + " points of damage.  Apply as normal weapon damage.");
		
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
			System.out.println("Mind Monster mind wipes " + damage + " crew units.  No ship damage.");
		}
		
		return damage;
	}
							
	public static int SpaceDragon() {
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		System.out.print("Need code for Space Dragon damage.");
		
		return damage;
	}
		
	public static int Arastoz(String fullName) {
		
		System.out.println("fullName: " + fullName);

		int monsterSize = 0;
		int damage = 0;
		int die = DamageAllocation.rollDice(1,6);
		
		if (fullName == "Arastoz 1x") {
			monsterSize = 1;
		} else if (fullName == "Arastoz 2x") {
			monsterSize = 2;
		} else if (fullName == "Arastoz 3x") {
			monsterSize = 4;
		} else if (fullName == "Arastoz 4x") {
			monsterSize = 8;
		}
		
		System.out.println("monsterSize: " + monsterSize);
		
		System.out.print("Closest distance to Arastoz this turn [RETURN to cancel]: ");
		int dist = Driver.getNumber(0, 9);
		if (dist >= 0) {
			damage = labResearch[die][dist] * monsterSize;
			System.out.println();
			System.out.println("Arastoz does " + damage + " points of damage.  Apply as normal weapon damage.");
			System.out.println("Race\tLab Points Acquired");
		}

		return damage;
	}
	
	public static void DidArastozCombine() {

		String yesOrNo = "";
		System.out.println();
		System.out.print("Did any parts of Arastoz combine? ");
		yesOrNo = Driver.getInputNoCancel("YN");
		if (yesOrNo.contentEquals("Y")) {

			System.out.println("     Name\tBase\tRemaining");                   //  Print only Arastoz monster list
			System.out.println("     Name\tStr\tHit Pts");                   //  Print only Arastoz monster list
			System.out.println();
			for (int i = 1, print = 1; i <= Driver.currentGameYard.numShips; i++) {
				String extraSpace = ShipSetup.getExtraSpaces(i, 2);
				
				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
				}
				if(Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
					System.out.print(extraSpace + (print++) + ")  " + Driver.currentGameYard.list[i-1].name);
					ShipSetup.getExtraSpaces(i, 2);
					System.out.print("\t  " + extraSpace + Driver.currentGameYard.list[i-1].shipType.substring(8,10) + "\t  " + Driver.currentGameYard.list[i-1].ssd[24].remaining);
					System.out.println();
				}
			}
			
			int arastoz1RemainingHP = 0;
			int arastoz1BaseStrength = 0;
			int arastoz2RemainingHP = 0;
			int arastoz2BaseStrength = 0;
			boolean answerSame = true;
			while (answerSame == true) {
				System.out.println("Which 2 Arastoz Monsters have combined? [RETURN to cancel]");
				System.out.print("     #1: ");
				int arastozNum1 = Driver.getNumber(1, 4);
				if (arastozNum1 != -1) {
					arastoz1RemainingHP = Driver.currentGameYard.list[arastozNum1].ssd[24].remaining;
					arastoz1BaseStrength = Integer.parseInt(Driver.currentGameYard.list[arastozNum1].shipType.substring(8,9));
					System.out.println("Driver.currentGameYard.list[arastozNum1].shipType.substring(8,9) |" + Driver.currentGameYard.list[arastozNum1].shipType.substring(8,9) + "|");
					System.out.print("     #2: ");
					int arastozNum2 = Driver.getNumber(1, 4);
					arastoz2RemainingHP = Driver.currentGameYard.list[arastozNum2].ssd[24].remaining;
					arastoz2BaseStrength = Integer.parseInt(Driver.currentGameYard.list[arastozNum2].shipType.substring(8,9));
					System.out.println("Driver.currentGameYard.list[arastozNum2].shipType.substring(8,9) |" + Driver.currentGameYard.list[arastozNum2].shipType.substring(8,9) + "|");
					if (arastozNum1 == arastozNum2) {
						System.out.println("Arastoz can't combine with itself!  Try again.");
					} else {
						answerSame = false;
					}
					
					int combinedStrength = 0;
					System.out.println("arastoz1BaseStrength + arastoz2BaseStrength: " + arastoz1BaseStrength + " + " + arastoz2BaseStrength);
					
					if (arastoz1BaseStrength + arastoz2BaseStrength == 2) {
						combinedStrength = arastoz1RemainingHP + arastoz2RemainingHP;		//  100+100 = 200 - any damage so far
						
					} else if (arastoz1BaseStrength + arastoz2BaseStrength == 3) {
						combinedStrength = 100 + arastoz1RemainingHP + arastoz2RemainingHP;  //  100+200 = 400 (yes 400) - any damage so far
						
					} else if (arastoz1BaseStrength + arastoz2BaseStrength == 4) {
						combinedStrength = 400 + arastoz1RemainingHP + arastoz2RemainingHP;  //  200+200 or 100+300 = 800 - any damage so far
					}
					System.out.println("combinedStrength" + combinedStrength);

					Driver.currentGameYard.list[arastozNum1].ssd[24].remaining = combinedStrength;  //  Combine piece 1 and 2 into #1's spot  
					Driver.currentGameYard.removeShipFromShipyard(arastozNum2);						//  Remove #2 piece 
					
				}
			}
			System.out.println();
			System.out.println("     Name\tBase\tRemaining");                   //  Print only Arastoz monster list
			System.out.println("     Name\tStr\tHit Pts");                   //  Print only Arastoz monster list
			System.out.println();
			for (int i = 1, print = 1; i <= Driver.currentGameYard.numShips; i++) {
				String extraSpace = ShipSetup.getExtraSpaces(i, 2);
				
				if (Driver.currentGameYard.list[i-1].name.length() <=3 ) {
					Driver.currentGameYard.list[i-1].name = Driver.currentGameYard.list[i-1].name + "   ";
				}
				if(Driver.currentGameYard.list[i-1].shipType.contains("Arastoz")) {
					System.out.print(extraSpace + (print++) + ")  " + Driver.currentGameYard.list[i-1].name);
					ShipSetup.getExtraSpaces(i, 2);
					System.out.print("\t  " + extraSpace + Driver.currentGameYard.list[i-1].shipType.substring(8,10) + "\t  " + Driver.currentGameYard.list[i-1].ssd[24].remaining);
					System.out.println();
				}
			}

		}
	}
	
	public static void PrintOnlyArastozRaceInGame() {
	}
	
	public static int monsterDamageFromShip(int damageToMonster) {
		int whichMonster = -5;
		
		while (whichMonster < 0) {
			System.out.println();
			System.out.println();
			ShipSetup.PrintCurrentShipsInGameThatHaveSSD();
			System.out.print("Which monster to deal damage to? [RETURN to cancel] ");
			whichMonster = -5;

			whichMonster = Driver.getNumber(1, Driver.currentGameYard.numShips);

			if(whichMonster == -1) {
				break;
			}
			
			currentMonster = Driver.currentGameYard.list[whichMonster-1];

			System.out.println(currentMonster.name + " takes " + damageToMonster + " damage points.");

			currentMonster.ssd[24].remaining = currentMonster.ssd[24].remaining - damageToMonster;
			System.out.println(currentMonster.name + " has " + currentMonster.ssd[24].remaining + " remaning health points.");
			if (currentMonster.ssd[24].remaining <= 0) {
				System.out.println(currentMonster.name + " has been defeated.");
			}
			damageToMonster = 0;
		}
		return damageToMonster;
	}
	
	public static void MonsterScenarioCheck() {
		System.out.println("==============================");
		System.out.println("Monster Name\tHealth");
		System.out.println("------------------------------");
		
		for (int i = 0; i <= Driver.currentGameYard.numShips-1; i++) {
			if (Driver.currentGameYard.list[i].race == "Monster") {
				System.out.println(Driver.currentGameYard.list[i].shipType + "\t" + Driver.currentGameYard.list[i].ssd[24].remaining);
			}
		}
		System.out.println("==============================");

		System.out.println();
		System.out.println("==============================");
		System.out.println("Race\t\tLab Points");
		System.out.println("\t\tAcquired");
		System.out.println("------------------------------");
		
		String extraTab = "";
		for (int i = 0; i <= Driver.labResearches.length-1; i++) {
			if (Driver.labResearches[i].acquiredPoints > 0) {
				if (Driver.labResearches[i].race.length() < 5) {
					extraTab = "\t";
				}
				System.out.println(Driver.labResearches[i].race + extraTab + "\t" + Driver.labResearches[i].acquiredPoints);
			}
		}
		System.out.println("==============================");
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

		
		System.out.println("|===============================================================================|");
		ShipSetup.PrintCurrentShipsInGame();

		int shipResearching = -1;

		boolean cont = true;
		while (cont) {
			System.out.println();
			System.out.print("Which ship is collecting research data? [RETURN for cancel] ");
			shipResearching = Driver.getNumber(1, Driver.currentGameYard.numStarships);
			if(shipResearching > 0) {
				String raceOfCurrentShip = Driver.currentGameYard.list[shipResearching-1].race;
				String raceOfCurrentShipMain = raceOfCurrentShip;
				String nameOfCurrentShip = Driver.currentGameYard.list[shipResearching-1].name;
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
					numLabs = Driver.currentGameYard.list[shipResearching-1].ssd[17].remaining;
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
}
