import java.util.Scanner;
import java.math.*;

public class Driver {

	public static boolean TESTING = false;
	public static int labResearchAquired = 0;
	public static int labResearchRequired = 0;
	public static int MonsterScenario = 0;
	public static double MonsterBPVModifier = 1.0;
	public static boolean MonsterBPVModifierApplied = false;
	public static int numImpulses = 0;
	public static int TurnNumber = 0;
	public static boolean ElectronicWarfare = false;
	public static double electronicWarfareNet = 0;
	public static Shipyard currentGameYard = new Shipyard("Current Game Shipyard");
	public static Shipyard defaultYard = Shipyard.setupDefaultShipyard();
	public static Scanner keyboard = new Scanner(System.in);

	public static String[] labResearchRaceNames = {"Federation", "Klingon", "Romulan", "Kzinti", "Gorn", "Tholian", "Orion", "Hydran", "Lyran", "WYN", "Andromedan", "Frax", "Seltorian, ISC"};
	//public static int[] labResearchTotalPoints = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static LabResearch[] labResearches;
	
	/* Main Method */
	public static void main(String[] args) {

		labResearches = new LabResearch[labResearchRaceNames.length];
		for(int i = 0; i < labResearchRaceNames.length; i++) {
			labResearches[i] = new LabResearch(labResearchRaceNames[i]);
		}
		
		boolean cont = true;
		
		System.out.println("|=================================================================================|");
		System.out.println("|                          STAR FLEET BATTLES UTILITY                             |");
		System.out.println("|---------------------------------------------------------------------------------|");
		System.out.println("|               Java Code by Harrison Weese and D. Brian Weese                    |");
		System.out.println("|=================================================================================|");
		System.out.println();
		System.out.print("Would you like some pre-first turn reminders? ");
		String reminder = getInput("YN");
		
		if (reminder.equalsIgnoreCase("Y")) {
			PreGameReminders();
		}

		cont = true;
		while (cont) {
			System.out.println();
			System.out.println("|=================================================================================|");
			System.out.println("|                                 SFB MAIN MENU                                   |");
			System.out.println("|=================================================================================|");
			System.out.println("|            [V]iew All Objects in the Current Game                               |");
			System.out.println("|                                                                                 |");
			System.out.println("|            [I]mpulse Movement Procedure                                         |");
			System.out.println("|            [W]eapon Damage Procedure                                            |");
			System.out.println("|            [D]amage Allocation Procedure                                        |");
			System.out.println("|                                                                                 |");
			System.out.println("|            [S]hipyard (Add ships from)                                          |");
			System.out.println("|            [R]emove Ships from Current Game                                     |");  
			System.out.println("|            [F]ilter/View All Ships in Shipyard by BPV                           |");
			System.out.println("|                                                                                 |");
			System.out.println("|            [C]hange SSD Numbers (some or all)                                   |");
			System.out.println("|                                                                                 |");
			System.out.println("|            [P]reload Scenario                                                   |");
			System.out.println("|            [M]onster Management                                                 |");
//			System.out.println("|            [M]onster Modification based on BPV Adjustment                       |");
//			System.out.println("|            [L]ab & Damage Check for Monster Scenarios                           |");
//			System.out.println("|            [X] = Monster Damage                |");
//			System.out.println("|            [Y] = Roll for Victory Conditions (Monster Scenarios)                |");
			System.out.println("|            [Z] = Rules to Remember                                              |");
			System.out.println("|                                                                                 |");
			System.out.print("|            [T]oggle On/Off for Testing Purposes ");
			if (TESTING) {
				System.out.print("(ON)                            |");
			} else {
				System.out.print("(OFF)                           |");
			}
			System.out.println();
			System.out.println("|=================================================================================|");
			System.out.println("|                                [Q]uit                                           |");
			System.out.println("|=================================================================================|");

			String userInput = getInput("IWDSRFCPLZVMYT");
			String userInput3 = "";

			int damageTotal = 0;
			if (userInput.equalsIgnoreCase("I")) {
				if (currentGameYard.numShips > 0) {
					PhaseCalculation.PhaseCalc();
				} else {
					System.out.print(
							"You have no ships assigned to the current game.  Add some ships?");
					userInput3 = getInput("YN");
					if (userInput3.equalsIgnoreCase("Y")) {
						defaultYard.displayShipyardMenu(-1);
					}
				}
				System.out.println();
			} else if (userInput.equalsIgnoreCase("V")) {
				ShipSetup.PrintCurrentThingsInGame("Ship Monster Other Torpedo Drone Shuttle Fighter", "Speed");
			} else if (userInput.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam(-1);
			} else if (userInput.equalsIgnoreCase("M")) {
				MonsterManagementMenu();
			} else if (userInput.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc(-1, -1);
			} else if (userInput.equalsIgnoreCase("S")) {
				defaultYard.displayShipyardMenu(-1);
			} else if (userInput.equalsIgnoreCase("R")) {
				RemoveShip(true);
			} else if (userInput.equalsIgnoreCase("F")) {
				FilterShipyard();
			} else if (userInput.equalsIgnoreCase("C")) {
				// Allow user to ADD ships and tell how many systems left of each type
				ModifyShipSystems();
			} else if (userInput.equalsIgnoreCase("P")) {
				PreloadScenario();
//			} else if (userInput.equalsIgnoreCase("L")) {
//				MonsterStuff.MonsterScenarioCheck();
			} else if (userInput.equalsIgnoreCase("Z")) {
				RulesToRemember();
//			} else if (userInput.equalsIgnoreCase("Y")) {
//				MonsterStuff.RollForVictoryConditions();
			} else if (userInput.equalsIgnoreCase("T")) {
				TESTING = !TESTING;
			} else if (userInput.equalsIgnoreCase("Q")) {
				System.out.println("Exiting Program...");
				break;
			}

			System.out.println();
			System.out.println();
		}
	}
	
	public static void ModifyMonsterBPV () {
		MonsterBPVModifier = 1;
	
		if (MonsterBPVModifierApplied == true) {
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
			MonsterBPVModifier = totalShipBPV / 125.0;
			System.out.println();
			
			System.out.println("Current Monster HPs:");
			ShipSetup.PrintCurrentThingsInGame ("MONSTER", "HEALTH");

			String labResearchBlurb = "";
			if (Driver.labResearchRequired > 0) {
				System.out.println("Lab Research pts required to collect: " + Driver.labResearchRequired);
				labResearchBlurb = "and/or Lab Research ";
			}

			System.out.println();
			System.out.println("Monster Modifier:\t" + MonsterBPVModifier);
			System.out.println("Apply Monster " + labResearchBlurb + "BPV Modifier? ");
			
			String yesOrNo = getInput ("YN");
			
			if (yesOrNo.equalsIgnoreCase("Y")) {
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
						Driver.currentGameYard.list[i].ssd[24].numOfThisPart = (int) Math.round(Driver.currentGameYard.list[i].ssd[24].numOfThisPart * MonsterBPVModifier);
						Driver.currentGameYard.list[i].ssd[24].remaining = (int) Math.round(Driver.currentGameYard.list[i].ssd[24].remaining * MonsterBPVModifier);
					}
				}
				Driver.labResearchRequired = (int) Math.round(Driver.labResearchRequired * MonsterBPVModifier);
				System.out.println();
				System.out.println("Modified Monster HPs:");
				ShipSetup.PrintCurrentThingsInGame ("MONSTER", "HEALTH");
				MonsterBPVModifierApplied = true;
				
				if (Driver.labResearchRequired > 0) {
					System.out.println("Lab Research pts required to collect: " + Driver.labResearchRequired);
				}
			} else {
				System.out.println("Monster HPs have NOT been modified");
			}
		}
	}
	
	public static void ZeroOutMonsterAndLabResearchValues() {
		labResearchAquired = 0;
		labResearchRequired = 0;
		MonsterScenario = 0;
		MonsterBPVModifier = 1.0;
		MonsterBPVModifierApplied = false;
	}
	
	
	public static void RemoveShip(boolean print) {
		System.out.println("Driver.currentGameYard.numShips: " + Driver.currentGameYard.numShips);
		if (print == true) {
			ShipSetup.PrintCurrentThingsInGame("Ship Monster Other Torpedo Drone Shuttle Fighter", "");
		}
		System.out.println();
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
	
	public static void MonsterManagementMenu() {
		boolean cont = true;
		int monsterDamage = 0;
		
		while (cont) {
			System.out.println();
			System.out.println("|=================================================================================|");
			System.out.println("|                            MONSTER MANAGEMENT MENU                              |");
			System.out.println("|=================================================================================|");
			System.out.println("|       [A]djust (or View) Monster Hit Points                                     |");
			System.out.println("|       [D]amage to a ship from Monster (then directly to Damage Allocation)      |");
			System.out.println("|       [M]odify Monster Hit Points Based on Ship BPV Adjustment                  |");
			System.out.println("|       [L]ab Research Check                                                      |");
			System.out.println("|       [R]oll for Victory Conditions                                             |");
			System.out.println("|=================================================================================|");
			System.out.println("|                         RETURN to return to Main Menu                           |");
			System.out.println("|=================================================================================|");
			
			String userInput2 = Driver.getInput("AMLDR");

			if (userInput2.equalsIgnoreCase("M")) {
				ModifyMonsterBPV();
			} else if (userInput2.equalsIgnoreCase("A")) {
				MonsterStuff.AdjustMonsterHP();
			} else if (userInput2.equalsIgnoreCase("L")) {
				MonsterStuff.MonsterScenarioCheck();
			} else if (userInput2.equalsIgnoreCase("D")) {
				monsterDamage = MonsterStuff.MonsterDamage();
				DamageAllocation.DamageAlloc(-1, monsterDamage);
			} else if (userInput2.equalsIgnoreCase("R")) {
				MonsterStuff.RollForVictoryConditions();
			} else if (userInput2.equalsIgnoreCase("")) {
				return;
			}
		}
	}
	
	public static void PreGameReminders() {
		System.out.println();
		System.out.println("|=================================================================================|");
		System.out.println("|                            PRE-FIRST TURN REMINDERS                             |");
		System.out.println("|---------------------------------------------------------------------------------|");
		System.out.println("|  ENERGIZING PHASERS:                                                            |");
		System.out.println("|     Phasers must be energized for 1 point total before firing on turn 2.        |");
		System.out.println("|                                                                                 |");
		System.out.println("|  FIRE CONTROL SCANNERS:                                                         |");
		System.out.println("|     Require only 1 point wach turn to operate sensors and sensors.              |");
		System.out.println("|     If energy is not allocated, that ship may NOT fire this turn.               |");
		System.out.println("|                                                                                 |");
		System.out.println("|  BATTERIES:                                                                     |");
		System.out.println("|     Assumed charged at the beginning of the game unlee the scenario says        |");
		System.out.println("|         otherwise.                                                              |");
		System.out.println("|                                                                                 |");
		System.out.println("|  FRACTIONAL ENERGY POINTS:                                                      |");
		System.out.println("|     Fractions of energy points may be combined over multiple systems.           |");
		System.out.println("|     Unused energy can be stored in batteries, up to 1 point per battery,        |");
		System.out.println("|         otherwise the energy is lost.                                           |");
		System.out.println("|  SIDESLIP:                                                                      |");
		System.out.println("|     Ships may \"sideslip\" at a \"slip\" mode of 1, meaning ships must move         |");
		System.out.println("|         at least 1 hex forward before sideslipping again.                       |");
		System.out.println("|                                                                                 |");
		System.out.println("|=================================================================================|");
	}
	
	public static void FilterShipyard() {
		System.out.println();
		System.out.println("=============================================================================================================");
		System.out.println("                                           FILTER SHIPYARD BY BPV                                            ");
		System.out.println("=============================================================================================================");
		System.out.print("[E]conomic or [C]ombat value? ");
		String economicCombat = getInput ("EC");
		System.out.print("Min BPV: ");
		int minBPV = getNumberNoCancel(0,500);
		System.out.print("Max BPV: ");
		int maxBPV = getNumberNoCancel(0,500);

		String hasSSD = "";
		System.out.println("=============================================================================================================");
		System.out.println("ShpYrd\tRace\tShip\tCrew\tBrdg\tBPV\tBreak\tMove\tSpare\tSize\tTurn\tRule\tYear in");
		System.out.println("Number\t\tType\tUnits\tParties\t\tDown\tCost\tShtls\tClass\tMode\tNumber\tService");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < defaultYard.numShips; i++) {
			
			int intBPV = 0;
			String thisBPV = defaultYard.list[i].BPV;
			String thisRace = defaultYard.list[i].race;
			if (economicCombat.equalsIgnoreCase("E")) {
				intBPV = GetEconomicBPV(thisRace, thisBPV);

			} else if (economicCombat.equalsIgnoreCase("C")) {
				intBPV = GetCombatBPV(thisRace, thisBPV);
			}
			
			hasSSD = "";
			String extraSpaces = ShipSetup.getExtraSpaces(i, 3);
			if (defaultYard.list[i].hasSSD == true) {
				hasSSD = " *";
			}
			if (intBPV >= minBPV && intBPV <= maxBPV) {
				System.out.print(extraSpaces + i + ")" + hasSSD + "\t" + defaultYard.list[i].toString());
				System.out.println();
			}
		}
		System.out.println("=============================================================================================================");
	}
	
	public static int GetEconomicBPV (String race, String thisBPV) {
		int adjust = 0;
		int intBPV = 0;
		String stringBPV = "0";
		
		if (race == "Romulan") {
			adjust = 1;
		} else {
			adjust = 0;
		}
		
		if (thisBPV.contains("-")) {
			intBPV = 0;
		} else if (thisBPV.contains("/")) {
			int slashLocation = thisBPV.indexOf("/");
			stringBPV = thisBPV.substring(0, slashLocation);
			intBPV = Integer.parseInt(stringBPV);
		} else {
			stringBPV = thisBPV.substring(0, thisBPV.length()-adjust);
			intBPV = Integer.parseInt(stringBPV);
		}
		
		return intBPV;
	}

	public static int GetCombatBPV (String race, String thisBPV) {
		int adjust = 0;
		int intBPV = 0;
		String stringBPV = "0";
		
		if (race == "Romulan") {
			adjust = 1;
		} else {
			adjust = 0;
		}
		if (thisBPV.contains("-")) {
			intBPV = 0;
		} else if (thisBPV.contains("/")) {
			int slashLocation = thisBPV.indexOf("/");
			stringBPV = thisBPV.substring(slashLocation+1, thisBPV.length()-adjust);
			intBPV = Integer.parseInt(stringBPV);
		} else {
			stringBPV = thisBPV.substring(0, thisBPV.length()-adjust);
			intBPV = Integer.parseInt(stringBPV);
		}
		
		return intBPV;
	}

	
	public static void ModifyShipSystems() {
		boolean cont = true;

		while (cont) {
			System.out.println();
			System.out.println("|=================================================================================|");
			System.out.println("|                        SHIP SSD CHANGE/MODIFICATION MENU                        |");
			System.out.println("|=================================================================================|");
			System.out.println();
			
			int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");
			System.out.println();
			System.out.print("Which ship to modify SSB boxes? [RETURN to cancel] ");
			int shipNum = ShipSetup.GetAdjustedInput(print, "SHIP", "");
			
			if (shipNum != -1) {
				
				PrintSSDsystems(shipNum);

				System.out.println();
				System.out.print("Modify [S]ome or [A]ll SSD boxes for " + Driver.currentGameYard.list[shipNum].name + " [RETURN to cancel]: ");
				String someOrAll = Driver.getInput("SA");
			
				if (someOrAll.equalsIgnoreCase("A")) {
					ChangeAllShipSSDSystems(shipNum);
				
				} else if (someOrAll.equalsIgnoreCase("S")) {
					ChangeSomeShipSSDSystems(shipNum);
				
				} else {
					cont = false;
				}
			} else {
				cont = false;
			}
		}
	}

	public static void PrintSSDsystems(int shipNumInput) {
		String extraSpaces = "";
		String extraSpaces2 = "";
		String extraSpaces3 = "";
		
		System.out.println();
		System.out.println("Current Number of SSD boxes for each system: ");
		for (int numPart = 0; numPart <= 24; numPart++) {
			extraSpaces = " " + ShipSetup.getExtraSpaces(numPart+1, 2);
			extraSpaces2 = ShipSetup.getExtraSpaces(currentGameYard.list[shipNumInput].ssd[numPart].remaining, 2);
			extraSpaces3 = ShipSetup.getExtraSpaces(currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart, 2);
			System.out.print(extraSpaces + (numPart+1) + ")   " + extraSpaces2 + currentGameYard.list[shipNumInput].ssd[numPart].remaining + " / " + extraSpaces3 + currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart + "  " + currentGameYard.list[shipNumInput].ssd[numPart].name);
			if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Flag Bridge") {
				System.out.print(" = Security, Web, Displacement Device");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Torpedo") {
				System.out.print(" = Photon Torpedo, Disruptor Bolt, Plasma Torpedo, SFG, Fusion Beam, Tractor-Repulsor Beam");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Drone") {
				System.out.print(" = ADD, ESG, Hellbore, Plasmatic Pulsars, Power Absorbers");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Shuttle") {
				System.out.print(" = Fighter, Mine Andormedan Hangar");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Cargo") {
				System.out.print(" = Repair, Mine Rack");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name.contains("Hull")) {
				System.out.print(" = Repair");
			}
			System.out.println();
		}
	}
	
	public static void ChangeAllShipSSDSystems(int shipNumInput) {
		int dummyArray[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			
		System.out.println("shipNumInput: " + shipNumInput);
		System.out.println();
		System.out.println("Indicate how many boxes should be left on the " + currentGameYard.list[shipNumInput].name + " SSD for the following systems");
		System.out.println("Hit [RETURN] to keep the current number");
		System.out.println();
		System.out.println("SYSTEM NAME (REMAINING/MAX)");
				
		for (int numPart = 0; numPart <= 20; numPart++) {  //  Allow changes to all systems except Damage Control, Sensor, Scanner, Excess Damage
			if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Flag Bridge") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Flag Bridge = Security, Web, Displacement Device");
				System.out.println("---------------------------------------------------------");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Torpedo") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Torpedo = Photon Torpedo, Disruptor Bolt, Plasma Torpedo,");
				System.out.println("          SFG, Fusion Beam, Tractor-Repulsor Beam");
				System.out.println("---------------------------------------------------------");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Drone") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Drone = ADD, ESG, Hellbore, Plasmatic Pulsars,");
				System.out.println("        Power Absorbers");
				System.out.println("---------------------------------------------------------");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Shuttle") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Shuttle = Fighter, Mine Andormedan Hangar");
				System.out.println("---------------------------------------------------------");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Cargo") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Cargo = Repair, Mine Rack");
				System.out.println("---------------------------------------------------------");
			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name.contains("Hull")) {
				System.out.println("---------------------------------------------------------");
				System.out.println("Front/Aft Hull = Repair");
				System.out.println("---------------------------------------------------------");
			}
			System.out.print("" + currentGameYard.list[shipNumInput].ssd[numPart].name + " (" + currentGameYard.list[shipNumInput].ssd[numPart].remaining + "/" + currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart + "): ");
			dummyArray[numPart] = getNumber(0, 100);
			if (dummyArray[numPart] == -1) {
				dummyArray[numPart] = currentGameYard.list[shipNumInput].ssd[numPart].remaining;
			}							
		}
		
		for (int numPart = 0; numPart <= 20; numPart++) {
			currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart = dummyArray[numPart];
			currentGameYard.list[shipNumInput].ssd[numPart].remaining = dummyArray[numPart];
		}
				
		System.out.println();
		ShipSetup.PrintCurrentThingsInGame("Ship", "Speed");
		System.out.println();
	}
			
	public static void ChangeSomeShipSSDSystems(int shipNumInput) {
		int systemNumToChange = 0;
		int newAmount = 0;
//		String extraSpaces = "";
//		String extraSpaces2 = "";
//		String extraSpaces3 = "";
//
//		System.out.println();
//		System.out.println("Current Number of SSD boxes for each system: ");
//		for (int numPart = 0; numPart <= 24; numPart++) {
//			extraSpaces = " " + ShipSetup.getExtraSpaces(numPart+1, 2);
//			extraSpaces2 = ShipSetup.getExtraSpaces(currentGameYard.list[shipNumInput].ssd[numPart].remaining, 2);
//			extraSpaces3 = ShipSetup.getExtraSpaces(currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart, 2);
//			System.out.print(extraSpaces + (numPart+1) + ")   " + extraSpaces2 + currentGameYard.list[shipNumInput].ssd[numPart].remaining + " / " + extraSpaces3 + currentGameYard.list[shipNumInput].ssd[numPart].numOfThisPart + "  " + currentGameYard.list[shipNumInput].ssd[numPart].name);
//			if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Flag Bridge") {
//				System.out.print(" = Security, Web, Displacement Device");
//			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Torpedo") {
//				System.out.print(" = Photon Torpedo, Disruptor Bolt, Plasma Torpedo, SFG, Fusion Beam, Tractor-Repulsor Beam");
//			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Drone") {
//				System.out.print(" = ADD, ESG, Hellbore, Plasmatic Pulsars, Power Absorbers");
//			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Shuttle") {
//				System.out.print(" = Fighter, Mine Andormedan Hangar");
//			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name == "Cargo") {
//				System.out.print(" = Repair, Mine Rack");
//			} else if (currentGameYard.list[shipNumInput].ssd[numPart].name.contains("Hull")) {
//				System.out.print(" = Repair");
//			}
//			System.out.println();
//		}
		while (systemNumToChange != -1) {
			System.out.println();
			System.out.print("System number to change [RETURN to cancel]: ");
			systemNumToChange = getNumber(0, 100);
			if (systemNumToChange != -1) {
				System.out.print("New number for " + currentGameYard.list[shipNumInput].ssd[systemNumToChange-1].name + " SSD boxes: ");
				newAmount = getNumber(0, 100);
				currentGameYard.list[shipNumInput].ssd[systemNumToChange-1].remaining = newAmount;
			}
		}
	}
				
	public static void RulesToRemember() {

		boolean cont = true;

		System.out.println("|=================================================================================|");
		System.out.println("|                              RULES TO REMEMBER:                                 |");
		System.out.println("|=================================================================================|");
		System.out.println("|   [W]eapons Energy Costs (E0.0 & F0.0)    [S]hield Costs (D3.0)                 |");
		System.out.println("|   [B]atteries (H7.0)                      [D]amage Control (D9.0)               |");
		System.out.println("|   [H]igh Energy Turns (C6.0)              [A]nti-Drone & Drone (E5.0 & FD1.0)   |");
		System.out.println("|   [R]epair Systems (G17.0)                [P]robes (G5.0)                       |");
		System.out.println("|   [T]actical Maneuvers (C5.0)             [L]ife Support  (B3.3)                |");
		System.out.println("|   [C]hanging Speed (C2.2/C12.0)           [L]ife Support  (B3.3)                |");
		System.out.println("|=================================================================================|");
		System.out.println("|                         RETURN to return to Main Menu                           |");
		System.out.println("|=================================================================================|");

		System.out.print("What rule would you like to be reminded of? ");

		while (cont) {
			String userInput2 = Driver.getInput("RAWBSHDPTLC");

			if (userInput2.equalsIgnoreCase("W")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                          WEAPONS ENERGY COSTS:                                  |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("| Rule:  TYPE:                   Energy Cost          Hold Over      Other:       |");
				System.out.println("|                              (Cost per turn)          Cost                      |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("| E2.0   Phaser:                                                  1 to energize   |");
				System.out.println("|          I, II                 1   Any Src             -        before the 1st  |");
				System.out.println("|          III                  1/2  Any Src             -        turn used in    |");
				System.out.println("|          IV                    2   Any Src             -        the game.       |");
				System.out.println("|          Gatling (Ph-III)     1/4 (4 shots/box) Any    -                        |");
				System.out.println("| E3.0   Disrupter Bolt:         2   Any Src             -                        |");
				System.out.println("|          Overloaded            4   Any Src             -      Fdbck Dmg, dist=0 |");
				System.out.println("| E4.0   Photon Torpedo:        2+2  Warp Only     1/box/Any source               |");
				System.out.println("|          Proximity            2+2  Warp Only                                    |");
				System.out.println("|          Overloaded   2+2+(1-4 extra) Warp Only  2/box/Any source  Fdbk Dmg,d<=1|");
				System.out.println("| E7.0   Fusion Beam             2   Any Src             -                        |");
				System.out.println("|          Overloaded            4   Any Src             -                        |");
				System.out.println("| E8.0   Mauler                  X   Any Src             -                        |");
				System.out.println("| E9.0   Tractor-Repulsor Beam  3+3  Any Src             1      Sub. Tractor Beam |");
				System.out.println("| E10.0  Hellbore               3+3  Any Src             3      Surrounding Damage|");
				System.out.println("|          Overloaded           3+6  Any Src             -      Fdbck Dmg, dist=0 |");
				System.out.println("| FP1.0  Plasma Torpedo:                                                          |");
				System.out.println("|          Type-R              2+2+5 Any Src    NO ship/4 starbase  *EPT 2+2+10   |");
				System.out.println("|          Type-S (G-II)       2+2+4 Any Src             2          *EPT 2+2+8    |");
				System.out.println("|          Type-G              2+2+3 Any Src             1          *EPT 2+2+6    |");
				System.out.println("|          Type-F              1+1+3 Any Src             0                        |");
				System.out.println("|          *EPT - Enveloping hits all 6 shields equally (dmg/6, >=1/2 round up)   |");
				System.out.println("| G15.0  Stasis Field Generator  5+10+15+20+25+...       -                        |");
				System.out.println("| G18.0  Displacement Device    2+2  Warp Only           1                        |");
				System.out.println("|        ESG                                                                      |");
				System.out.println("| J2.22  Admin Shuttles (Suicide) 3+3+3 Warp Only        1 Any Src                |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("A")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                                ANTI-DRONES (E5.0)                               |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|                                                                                 |");
				System.out.println("|=================================================================================|");
				System.out.println("|                                 DRONES: (FD1.0)                                 |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   Type     Speed    Endurance   Warhead     Damage     Space     Cost           |");
				System.out.println("|                     (in turns)           (to destroy)                           |");
				System.out.println("|    IS       12          1          8          3         1/2       0             |");
				System.out.println("|    I         8          3         12          4          1        0             |");
				System.out.println("|    II       12          2         12          4          1       1/2            |");
				System.out.println("|    III      12         25         12          4          1        1             |");
				System.out.println("|    IV        8          3         24          6          2        0             |");
				System.out.println("|    I        12          2         24          6          2       1/2            |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  DRONE RACKS: Unless otherwise stated all drone racks are Standard \"A\"          |");
				System.out.println("|                                                                                 |");
				System.out.println("|  Rule: Type:         Capacity      Rate of Fire       Restrictions              |");
				System.out.println("|             (Each SSD box)                                                      |");
				System.out.println("|  FD3.1  \"A\":            4           1 per turn            -                     |");
				System.out.println("|  FD3.2  \"B\":            6           1 per turn            -                     |");
				System.out.println("|  FD3.3  \"C\":            4           1 per turn     12 impulses apart            |");
				System.out.println("|  FD3.4  \"D\":                                                                    |");
				System.out.println("|  FD3.5  \"E\":       8 (Type-IS)      4 per turn      8 impulses apart            |");
				System.out.println("|  FD3.6  \"F\":            4           1 per turn            -                     |");
				System.out.println("|  FD3.7  \"G\":                                                                    |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("C")) {
				System.out.println("|=================================================================================|");
				System.out.println("|             CHANGING SPEED (ACCELERATION/DECELERATION) (C2.2/C12.0)             |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  Acceleration: x2 or +10 of previous, whichever is greater                      |");
				System.out.println("|                                                                                 |");
				System.out.println("|  Deceleration: ÷2 or -4 of previous, whichever is greater                       |");
				System.out.println("|                                                                                 |");
				System.out.println("|  Change of speed may happen 8 impulses apart,                                   |");
				System.out.println("|       on or after impulse 4, but on or before impulse 28.                       |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("R")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                         REPAIR SYSTEMS   (G17.0 / Annex #9)                     |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   - 1 repair point = 1 unit of power from any source (except reserve power)     |");
				System.out.println("|   - No more than 5 repair pts can be applied to any 1 system per turn           |");
				System.out.println("|   - Multiple systems can be in repaired simultaneously                          |");
				System.out.println("|   - Unused repair points cannot be saved                                        |");
				System.out.println("|   - Bases & FRDs can repair themselves during a scenario, but at 4x the cost    |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   ADD                    3  |  Hull               1  |  Probe               3   |");
				System.out.println("|   APR                    4  |  Impulse            5  |  Repair Box          6   |");
				System.out.println("|   Battery                2  |  Lab                5  |  Sensor             10   |");
				System.out.println("|   Bridge (Any Control)   6  |  Mine Rack          4  |  Scanner            10   |");
				System.out.println("|   Cargo                  1  |  PA Panel           5  |  Shield              2   |");
				System.out.println("|   Damage Control         3  |  Phaser-I           5  |  Shuttle Bay         2   |");
				System.out.println("|   Displacement Device   25  |        -II          4  |  Stasis Field Gen   20   |");
				System.out.println("|   Disruptor: range 40   10  |        -III         2  |  Special Sensors    15   |");
				System.out.println("|              range 30    8  |        -IV         10  |  Tractor Beam        3   |");
				System.out.println("|              range 22    7  |        -G           6  |  Tractor-Repulsor    5   |");
				System.out.println("|              range 15    5  |  Photon Torpedo     8  |  Transporter         3   |");
				System.out.println("|              range 10    4  |  Plasma-F           5  |  Ubitron Interface   4   |");
				System.out.println("|   Drone Rack             3  |        -G          10  |  Warp Engine        10   |");
				System.out.println("|   Expanding Field Gen   15  |        -R          20  |  Warp Reactor        6   |");
				System.out.println("|   Fusion Beam            6  |        -S          15  |  Web                 6   |");
				System.out.println("|   Hellbore              15  |  Plasmatic Pulsar  15  |  Web Caster         15   |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   Damage Point on Fighter or Shuttle  1    damage Point on SWAC   2         3   |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("B")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                                 BATTERIES (H7.0)                                |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - Batteries are assumed to be charged at the beginning of a scenario           |");
				System.out.println("|  - Must be recharged after being discharged                                     |");
				System.out.println("|  - Batteries destroyed in combet are assumed to be those previously discharged  |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("S")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                               SHIELD COSTS (D3.0)                               |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|                                     MINIMUM         FULL                        |");
				System.out.println("|  COST OF OPERATion:              (5 SSD boxes)   (All Avail)                    |");
				System.out.println("|    Size Class 1 (Starbases)          2 pts     +    5 pts                       |");
				System.out.println("|    Size Class 2 (Dreadnoughts)       1 pt      +    3 pts                       |");
				System.out.println("|    Size Class 3 (Cruisers)           1 pt      +    1 pt                        |");
				System.out.println("|    Size Class 4 (Destroyers)                 1 pt                               |");
				System.out.println("|    Size Class 5 (P/F's)                      1 pt                               |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  GENERAL SHIELD REINFORCEMENT (GSR):                                            |");
				System.out.println("|    Energy supplied / 2 = GSR available (round down)                             |");
				System.out.println("|    Reinforce all shields and eliminated before Specific Reinforcement           |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  SPECIFIC SHIELD REINFORCEMENT:                                                 |");
				System.out.println("|    Energy supplied \"adds\" to the number of SSD boxes for a specific shield      |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  NOTE: A shield that is down cannot be reinforced specifically, but GSR would   |");
				System.out.println("|    still block fire coming from that direction.                                 |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("H")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                            HIGH ENERGY TURNS (C6.0)                             |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - Cost = 5 * movement cost                                                     |");
				System.out.println("|  - Defined as a \"snap turn\" to face any adjacent hex regardless of turn mode    |");
				System.out.println("|  - Does not affect acceleration                                                 |");
				System.out.println("|  - Cannot be preformed on Impulse 1                                             |");
				System.out.println("|  - Seeking weapons and fighters may perform 1 HET per turn, never breakdown     |");
				System.out.println("|  - Immediately following a HET, roll 1d6 to see if breakdown occurs. (First     |");
				System.out.println("|        HET of turn for each player, roll 1d6-2.)                                |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - BREAKDOWN (C6.54)                                                            |");
				System.out.println("|      - On the first HET of the SCENARIO, 2 is subtracted from the die roll      |");
				System.out.println("|      - If breakdown occurs:                                                     |");
				System.out.println("|          - Immediate stop                                                       |");
				System.out.println("|          - Cannot move for 16 impulses (post-breakdown period)                  |");
				System.out.println("|          - Roll 1d6 and face ship that direction                                |");
				System.out.println("|          - All movement energy is lost                                          |");
				System.out.println("|          - Destroyed: 1/5 of warp boxes (distribute as evenly as possible)      |");
				System.out.println("|          - Killed: 1/3 crew units & 1/4 borading parties (1/4 enemy BP too)     |");
				System.out.println("|          - 2 internal hits via DAC                                              |");
				System.out.println("|          - All repairs aborted; incomplete repairs lost                         |");
				System.out.println("|          - Breakdown rating reduced by 1                                        |");
				System.out.println("|          - Stasis fields, Expanding Spheres, Tractor Beams released             |");
				System.out.println("|          - For 8 Impulses, or the rest of the turn (whichever is less):         |");
				System.out.println("|              - No weapons may be fired (inc. seeking wepaons)                   |");
				System.out.println("|              - No shuttles/fighters may be launched/recovered                   |");
				System.out.println("|              - No tractor beams/transporters may be used                        |");
				System.out.println("|      - Ship may resume \"normal\" operations after the 16 impulse post-           |");
				System.out.println("|             breakdown peroid has ended                                          |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("D")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                                DAMAGE CONTROL:                                  |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - Energy may be allocated up to the highest Damage Control number.             |");
				System.out.println("|  - For every 2 energy units allocated, 1 shield box may be restored:            |");
				System.out.println("|       - Must be specificied in advance, and already be damaged                  |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - All ships can begin repair at beginning of turn, at no cost, system(s) equal |");
				System.out.println("|       to its Damage Control rating, to repair systems at the end of the turn.   |");
				System.out.println("|  - Repair points can be collected over multiple turns                           |");
				System.out.println("|  - The system to be repaired must already be destroyed.                         |");
				System.out.println("|  - Repair points CANNOT be transferred to repair another system.                |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   ADD                   3  |  Lab                5  |  Probe               3    |");
				System.out.println("|   APR                   4  |  Mine Rack          4  |  Repair Box          6    |");
				System.out.println("|   Battery               2  |  PA Panel           5  |  Sensor             10    |");
				System.out.println("|   Bridge (Any Control)  6  |  Phaser-I           5  |  Scanner            10    |");
				System.out.println("|   Cargo                 1  |  Phaser-II          4  |  Shield              2    |");
				System.out.println("|   Damage Control        3  |  Phaser-III         2  |  Shuttle Bay         2    |");
				System.out.println("|   Displacement Device  25  |  Phaser-IV         10  |  Stasis Field Gen   20    |");
				System.out.println("|   Disruptor             8  |  Phaser-G           6  |  Special Sensors    15    |");
				System.out.println("|   Drone Rack            3  |  Photon Torpedo     8  |  Tractor Beam        3    |");
				System.out.println("|   Expanding Field Gen  15  |  Plasma-F           5  |  Transporter         3    |");
				System.out.println("|   Fusion Beam           6  |  Plasma-G          10  |  Ubitron Interface   4    |");
				System.out.println("|   Hellbore             15  |  Plasma-R          20  |  Warp Engine        10    |");
				System.out.println("|   Hull                  1  |  Plasma-S          15  |  Web                 6    |");
				System.out.println("|   Impulse               5  |  Plasmatic Pulsar  15  |  Web Caster         15    |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("P")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                                 PROBES (G5.0):                                  |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  Each SSD box is a probe launcher and contains 5 probes each                    |");
				System.out.println("|  Each launchers can can launch 1 probe at a time                                |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  FOR INFORMATION: Automatic 20 points of information gained per probe           |");
				System.out.println("|                   Maximum range = 5 hexes                                       |");
				System.out.println("|                   Cost = 1+1 (hold cost = 1)                                    |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  AS A WEAPON: Only under extreme circumstances (G5.35)                          |");
				System.out.println("|               Warhead strength of 8                                             |");
				System.out.println("|               Cost = 2+2 (no holding over)                                      |");
				System.out.println("|               Success only if 1d6 >= distance when fired                        |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("T")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                            TACTICAL MANEUVERS (C5.0):                           |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  Defined as making a 60 degree turn in place.                                   |");
				System.out.println("|  Performed on any impulse after the 1st impulse of the turn.                    |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  Sublight Tactical Maneuver:                                                    |");
				System.out.println("|      Only 1 turn of this kind per turn.                                         |");
				System.out.println("|      Cost is 1 energy from impulse only.                                        |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  Warp Tactical Maneuver:                                                        |");
				System.out.println("|      Only 4 turn of this kind per turn.                                         |");
				System.out.println("|      Cost is the same as movement cost, per maneuver.                           |");
				System.out.println("|      Enter the number of manuevers as \"speed\" + 1 for Impulse Procedure         |");
				System.out.println("|          purposes                                                               |");
				System.out.println("|      At the scheduled time of movement/maneuver, the ship \"earns\" a             |");
				System.out.println("|          maneuever point.                                                       |");
				System.out.println("|      These points must be used before the next instance occurs in the           |");
				System.out.println("|          Impulse Prodecure, or the maneuver is lost.                            |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("L")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                               LIFE SUPPORT (B3.3):                              |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  LIFE SUPPORT:                                                                  |");
				System.out.println("|     Energy must be allocated every turn according to size class:                |");
				System.out.println("|         Size Class      1        2        3        4        5        Emer       |");
				System.out.println("|         Energy Cost     3      1 1/2      1       1/2       0         0         |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  EMERGENCY LIFE SUPPORT:                                                        |");
				System.out.println("|      May be used by ships that are crippled:                                    |");
				System.out.println("|          90% or more warp engines destroyed                                     |");
				System.out.println("|          50% or more of interior SSD boxes destroyed                            |");
				System.out.println("|          Any Excess Damage taken                                                |");
				System.out.println("|          All control boxes destroyed                                            |");
				System.out.println("|          All weapons destroyed                                                  |");
				System.out.println("|      Only 1 of these conditions need to be met for a ship to be considered      |");
				System.out.println("|          crippled                                                               |");
				System.out.println("|=================================================================================|");
				
			} else if (userInput2.equalsIgnoreCase("")) {
				// break
				cont = false;
			}
			if (!(userInput2.equalsIgnoreCase(""))) {
				System.out.println("|  [W]eapons   [E]nergy Alloc    [H]ET      [D]amage Control      [A]DD & Drone   |");
				System.out.println("|  [S]hields   [R]epair System   [P]robes   [T]actical Maneuvers  [L]ife Support  |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|                            [RETURN] to the Main Menu                            |");
				System.out.println("|=================================================================================|");
				System.out.println();
			}
		}
	}

	public static void PreloadScenario() {
		System.out.println();
		System.out.println("|=================================================================================|");
		System.out.println("|                                  PRELOAD MENU:                                  |");
		System.out.println("|---------------------------------------------------------------------------------|");
		System.out.println("|  Monster Senarios:                              Other Ships:                    |");
		System.out.println("|      1. The Planet Crusher       (SM1.0)           Fed-CC           Planet      |");
		System.out.println("|      2. The Space Amoeba         (SM2.0)           Fed-CC                       |");
		System.out.println("|      3. The Moray Eel of Space   (SM3.0)           Fed-CC                       |");
		System.out.println("|      4. The Cosmic Cloud         (SM4.0)           Fed-CC                       |");
		System.out.println("|      5. The Sunsnake             (SM5.0)           Fed-CC                       |");
		System.out.println("|      6. The Mind Monster         (SM6.0)           Fed-CC           Planet      |");
		System.out.println("|      7. The Space Dragon         (SM7.0)           Fed-CC                       |");
		System.out.println("|      8. The Combining of Arastoz (CL#2)            Fed-CC                       |");
		System.out.println("|      9.                                                                         |");
		System.out.println("|     10.                                                                         |");
		System.out.println("|                                                                                 |");
		System.out.println("|  2-PLAYER:                                                                      |");
		System.out.println("|     21.                                                                         |");
		System.out.println("|     22.                                                                         |");
		System.out.println("|     23.                                                                         |");
		System.out.println("|     24.                                                                         |");
		System.out.println("|     25.                                                                         |");
		System.out.println("|     26.                                                                         |");
		System.out.println("|     27.                                                                         |");
		System.out.println("|     28.                                                                         |");
		System.out.println("|     29.                                                                         |");
		System.out.println("|     30.                                                                         |");
//		if (Driver.TESTING) {
			System.out.println("|                                                                                 |");
			System.out.println("|  RANDOM SHIPS FOR TESTING:                                                      |");
			System.out.println("|    101.  2 Federation, 2 Kligon, 2 Monsters                                     |");
			System.out.println("|    102.  1 Federation, 1 Gorn, Civilian Freighter                               |");
			System.out.println("|    103.  1 Federation, 1 Klingon, 1 Hydran                                      |");
			System.out.println("|    104.  1 Romulan, 1 Tholian, Civilian Freighter                               |");
			System.out.println("|    105.  5 Random Monsters                                                      |");
			System.out.println("|=================================================================================|");
//		}
		
		boolean scenarioLoaded = false; 
		
		while (scenarioLoaded == false) {
			System.out.print("What scenario to load? [RETURN to cancel] ");
			int scenario = getNumber(1,200);
		
			if (scenario == 1) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 1;
				Driver.currentGameYard.numShips = 0;
				
				InstallSpecificShip("Monster", "Planet Crusher");
				currentGameYard.list[0].speed = 6;
				currentGameYard.list[0].name = "Planet Crusher";
				
				InstallSpecificShip("Other", "Planet");
				currentGameYard.list[1].speed = 0;
				currentGameYard.list[1].name = "Sheboygen III";
				
				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 2) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 2;
				labResearchRequired = 400;
				Driver.currentGameYard.numShips = 0;
				
				InstallSpecificShip("Monster", "Space Amoeba");
				currentGameYard.list[0].speed = 4;
				
				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 3) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 3;
				labResearchRequired = 0;
				Driver.currentGameYard.numShips = 0;
				
				InstallSpecificShip("Monster", "Moray Eel");
				currentGameYard.list[0].speed = 6;

				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;

			} else if (scenario == 4) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 4;
				labResearchRequired = 400;
				Driver.currentGameYard.numShips = 0;

				InstallSpecificShip("Monster", "Cosmic Cloud");
				currentGameYard.list[0].speed = 4;

				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;

			} else if (scenario == 5) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 5;
				labResearchRequired = 400;

				Driver.currentGameYard.numShips = 0;

				InstallSpecificShip("Monster", "Sunsnake");
				currentGameYard.list[0].speed = 3;

				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;

			} else if (scenario == 6) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 6;
				Driver.currentGameYard.numShips = 0;

				labResearchRequired = 400;

				InstallSpecificShip("Monster", "Mind Monster");
				currentGameYard.list[0].speed = 6;

				InstallSpecificShip("Other", "Planet");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				currentGameYard.list[1].speed = 0;
				currentGameYard.list[1].name = "Libraria IV";
				currentGameYard.list[1].ssd[24].remaining = 75;

				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;

			} else if (scenario == 8) {
				ZeroOutMonsterAndLabResearchValues();
				MonsterScenario = 8;
				Driver.currentGameYard.numShips = 0;

				labResearchRequired = 0;

				int firstArastozNum = FindMonsterLocation("Monster", "Arastoz 1x");
				currentGameYard.addShipToShipyard(defaultYard.list[firstArastozNum]);					//  Arastoz 1x
				currentGameYard.list[0].speed = 14;
				currentGameYard.list[0].name = "Arastoz A";
				
				currentGameYard.addShipToShipyard(defaultYard.list[firstArastozNum + 1]);				//  Arastoz 1x
				currentGameYard.list[1].speed = 14;
				currentGameYard.list[1].name = "Arastoz B";
				
				currentGameYard.addShipToShipyard(defaultYard.list[firstArastozNum + 2]);				//  Arastoz 1x
				currentGameYard.list[2].speed = 14;
				currentGameYard.list[2].name = "Arastoz C";
				
				currentGameYard.addShipToShipyard(defaultYard.list[firstArastozNum + 3]);				//  Arastoz 1x
				currentGameYard.list[3].speed = 14;
				currentGameYard.list[3].name = "Arastoz D";
				
				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[4].speed = 6;		//  Speed coming into game

				InstallSpecificShip("Civilian", "F-L");
				currentGameYard.list[5].speed = 6;		//  Speed coming into game

//				InstallSpecificShip("Lyran", "CA");
//				currentGameYard.list[6].speed = 6;		//  Speed coming into game
				
			} else if (scenario == 101) {
				ZeroOutMonsterAndLabResearchValues();
				Driver.currentGameYard.numShips = 0;

				InstallSpecificShip("Federation", "DN");
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Klingon", "B10");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Klingon", "D7C");
				currentGameYard.list[3].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				int firstMonsterNum = FindMonsterLocation("Monster", "Planet Crusher");
				int randMonster = DamageAllocation.rollDice(1, 7);
				currentGameYard.addShipToShipyard(defaultYard.list[firstMonsterNum + randMonster]);
				currentGameYard.list[4].speed = DamageAllocation.rollDice(1, 10) + 1;
					
				randMonster = DamageAllocation.rollDice(1, 7);
				currentGameYard.addShipToShipyard(defaultYard.list[firstMonsterNum + randMonster]);
				currentGameYard.list[5].speed = DamageAllocation.rollDice(1, 10) + 1;
												
			} else if (scenario == 102) {
				ZeroOutMonsterAndLabResearchValues();
				Driver.currentGameYard.numShips = 0;

				InstallSpecificShip("Federation", "NCL");
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Gorn", "CC");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Civilian", "F-L");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 5) + 1;
				
			} else if (scenario == 103) {
				ZeroOutMonsterAndLabResearchValues();
				Driver.currentGameYard.numShips = 0;

				InstallSpecificShip("Federation", "CC");
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Klingon", "D7C");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Hydran", "CVA");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 104) {
				ZeroOutMonsterAndLabResearchValues();
				Driver.currentGameYard.numShips = 0;
				
				InstallSpecificShip("Tholian", "DD");
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Romulan", "SpH-A");
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				InstallSpecificShip("Civilian", "F-L");
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 5) + 1;
				
			} else if (scenario == 105) {
				ZeroOutMonsterAndLabResearchValues();
				Driver.currentGameYard.numShips = 0;
				int firstMonsterNum = FindMonsterLocation("Monster", "Planet Crusher");
				for (int i = 0; i <= 4; i++) {
					int randMonster = DamageAllocation.rollDice(1, 7);
					currentGameYard.addShipToShipyard(defaultYard.list[firstMonsterNum + randMonster]);
					currentGameYard.list[i].speed = DamageAllocation.rollDice(1, 10) + 1;
				}
			} else if (scenario == -1) {
				scenarioLoaded = false;
			}
			
			if (scenario > 0) {
				TurnNumber = 0;
				scenarioLoaded = true;
			}
		}

		System.out.println();
		ShipSetup.PrintCurrentThingsInGame("SHIP MONSTER OTHER", "SPEED");
		System.out.println();
	}
	
	public static void InstallSpecificShip(String race, String shipType) {
		for (int i = 0; i <= defaultYard.numShips-1; i++) {
			if (defaultYard.list[i].race == race && defaultYard.list[i].shipType == shipType) {
				currentGameYard.addShipToShipyard(defaultYard.list[i]);
			}
		}
	}

	public static int FindMonsterLocation(String race, String shipType) {  // FInd Monster location # in shipyard
		int firstMonsterNum = 0;
		for (int i = 0; i <= defaultYard.numShips-1; i++) {
			if (defaultYard.list[i].race == race & defaultYard.list[i].shipType == shipType) {
				firstMonsterNum = i; 
				break;
			}
		}
		return firstMonsterNum;
	}
	
	// GET LETTER AS INPUT THAT IS IN PASSED STRING
	public static String getInput(String word) {
//		Scanner keyboard = new Scanner(System.in);

//		boolean cont = true;

		String inputLetter = "";
		int location = -2;
		while (location < 0) {
			inputLetter = keyboard.nextLine().toUpperCase();
			location = word.indexOf(inputLetter); // Getting position of user's input of the "word" string passed through
			if (inputLetter.equalsIgnoreCase("AA") || inputLetter.equalsIgnoreCase("AAA") || inputLetter.equalsIgnoreCase("AAAA")) {
				location = 0;
			} else if (location == -1) { // location = -1 if character is not found in "word" string
				for (int d = 0; d <= word.length() - 1; d++) {
					System.out.print("[" + word.charAt(d) + "]"); // Remind user what letter inputs are being looked for
				}
			}
		}
		return inputLetter;
	}

	// GET LETTER AS INPUT THAT IS IN PASSED STRING
	public static String getInputNoCancel(String word) {
//		Scanner keyboard = new Scanner(System.in);

//		boolean cont = true;

		String inputLetter = "";
		int location = -1;
		while (location < 0 || inputLetter.contentEquals("")) {
			inputLetter = keyboard.nextLine().toUpperCase();
			location = word.indexOf(inputLetter); // Getting position of user's input of the "word" string passed through
			if (inputLetter.equalsIgnoreCase("AA") || inputLetter.equalsIgnoreCase("AAA") || inputLetter.equalsIgnoreCase("AAAA")) {
				location = 0;
			} else if (location == -1 || inputLetter.contentEquals("")) { // location = -1 if character is not found in "word" string
				for (int d = 0; d <= word.length() - 1; d++) {
					System.out.print("[" + word.charAt(d) + "]"); // Remind user what letter inputs are being looked for
				}
			}
		}
		return inputLetter;
	}

	// GET NUMBER AS INPUT THAT IS BETWEEN PASSED SMALL/BIG
	public static int getNumber(int small, int big) {
//		Scanner keyboard = new Scanner(System.in);

		boolean cont = true;
		int input = 0;
		String userInput = "";

		while (cont) {
			userInput = keyboard.nextLine().toUpperCase();
			if (userInput.length() > 0) {
				try {
					input = Integer.parseInt(userInput);
				} catch (NumberFormatException e) {
					input = -1;
				}

				if (input >= small && input <= big) {
					cont = false;
				} else {
					System.out.print("[" + (small) + "-" + big + "]"); // Remind user what number range is being looked
																		// for
					input = 0; // This line and
					// keyboard.nextLine(); // this line prevent a very bad infinite loop
				}
			} else {
				input = -1; // user Input is "", returns -1
				cont = false;
			}
		}
		return input;
	}

//	public static double getNumberDoubleNoCancel(double small, double big) {
//		double input = -1.0;
//		String userInput = "";
//
//		while (input == -1.0) {
//			userInput = keyboard.nextLine().toUpperCase();
//			if (userInput.length() >= 0) {
//
//				if (!userInput.contains(".")) {
//					try {
//						input = Integer.parseInt(userInput);
//					} catch (NumberFormatException e) {
//						input = -1;
//					}
//				} else {
//					try {
//						int decimalLocation = userInput.indexOf(".");
//						int integerPart = Integer.parseInt(userInput.substring(0, decimalLocation));
//						int decimalPart = Integer.parseInt(userInput.substring(decimalLocation, userInput.length()));
//						input = (double) integerPart + ( (double) decimalPart / 10);
//					} catch (NumberFormatException e) {
//						input = -1;
//					}
//				}
//				
//				if (input < small || input > big) {
//					System.out.print("[" + (small) + "-" + big + "]"); // Remind user what number range is being looked
//					input = -1;
//				}
//			}
//		}
//		return input;
//	}
	
	public static double getNumberDoubleNoCancel(double small, double big) {
		double input = -1.0;
		String userInput = "";

		while (input == -1.0) {
			userInput = keyboard.nextLine().toUpperCase();
			if (userInput.length() >= 0) {
				try {
					input = Double.parseDouble(userInput);
				} catch (NumberFormatException e) {
					input = -1.0;
				}

				double decimalPart = (double)Math.round((input - (int) input) * 10) / 10; 
				
				if (!(decimalPart == 0 || decimalPart == 0.5)) {
					input = -1.0;
				}
				
				if (input < small || input > big) {
					System.out.print("[" + (small) + "-" + big + "]"); // Remind user what number range is being looked
					input = -1.0;
				}
			}
		}
		return input;
	}

	public static int getNumberNoCancel(int small, int big) {
		int input = -1;
		String userInput = "";

		while (input == -1) {
			userInput = keyboard.nextLine().toUpperCase();
			if (userInput.length() >= 0) {
				try {
					input = Integer.parseInt(userInput);
				} catch (NumberFormatException e) {
					input = -1;
				}

				if (input < small || input > big) {
					System.out.print("[" + (small) + "-" + big + "]"); // Remind user what number range is being looked
					input = -1;
				}
			}
		}
		return input;
	}

}
