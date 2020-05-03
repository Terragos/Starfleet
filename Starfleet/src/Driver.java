import java.util.Scanner;

//TODO, NEED CODE TO EMPTY OUT KEYBOARD BUFFER  ???  In Impulse procedure after toggling TORPEDO, maybe ???  (there is an extra space)
//TODO, IS it possible to change the name of "PhaseCalculation" to "ImpulseCalculation"?  Everywhere "Phase" --> "Impulse" ???

public class Driver {

	public final static boolean TESTING = false;
	public static int labResearchAquired = 0;
	public static int MonsterScenario = 0;
	public static int numImpulses = 0;
	public static Shipyard currentGameYard = new Shipyard("Current Game Shipyard");
	public static Shipyard defaultYard = Shipyard.setupDefaultShipyard();
	public static Scanner keyboard = new Scanner(System.in);

	public static String[] labResearchRaceNames = {"Federation", "Klingon", "Romulan", "Kzinti", "Gorn", "Tholian", "Orion", "Hydran", "Lyran", "WYN", "Andromedan", "Frax", "Seltorian"};
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
		
		System.out.println();
		System.out.print("Would you like to add ships from the [S]hipyard or [P]reload a scenario? [RETURN = Main Menu] ");
//		System.out.print("Would you like to add ships [M]anually or from the [S]hipyard or [P]reload a scenario? [RETURN = Main Menu] ");

		String userInput2 = Driver.getInput("SP");

		if (userInput2.equalsIgnoreCase("M")) {
			boolean cont2 = true;
			while (cont2) {
				Starship star = new Starship();
				System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Name     : ");

				String nameInput = keyboard.nextLine();

				if (nameInput.contentEquals("")) {
					// break;
					cont2 = false;
				} else {
					if (nameInput.length() > 10)
						nameInput = nameInput.substring(0, 10);
					star.name = nameInput;

					System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Speed    : ");
					int speedInput = Driver.getNumberNoCancel(0, 32);
					star.speed = speedInput;

					System.out.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Turn Mode: ");
					String turnModeInput = Driver.getInput("ABCDEF-");
					star.turnMode = turnModeInput.toUpperCase();

					System.out
							.print("Ship " + (Driver.currentGameYard.numShips + 1) + " Break Down [?-6] ('-' = n/a): ");
					String breakDownString = "-";
					String breakDownInput = Driver.getInput("-123456");
					if (breakDownInput == "-") {
						breakDownString = "-";
					} else {
						breakDownString = breakDownInput.concat("-6");
					}
					star.breakDown = breakDownString;
					System.out.println();

					Driver.currentGameYard.addShipToShipyard(star);
				}
			}
		} else if (userInput2.equalsIgnoreCase("S")) {
			Driver.defaultYard.displayShipyardMenu(1);
		} else if (userInput2.equalsIgnoreCase("P")) {
			PreloadScenario();
		}

		cont = true;
		while (cont) {
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                             SFB MAIN MENU                                |");
			System.out.println("|==========================================================================|");
			System.out.println("|            [M]odify, Add, Remove or View Ship Name & Stats               |");
			System.out.println("|            [I]mpulse Movement Procedure                                  |");
			System.out.println("|            [W]eapon Damage Procedure                                     |");
			System.out.println("|            [D]amage Allocation Procedure                                 |");
			System.out.println("|            [S]hipyard                                                    |");
			System.out.println("|            [C]hange SSD Numbers (because of Program Crash)               |");
			System.out.println("|            [P]reload Scenario                                            |");
			System.out.println("|            [R]ules to Remember                                           |");
			System.out.println("|            [Z] = Monster Scenario Lab & Damage Check                     |");
			System.out.println("|==========================================================================|");
			System.out.println("|                                [Q]uit                                    |");
			System.out.println("|==========================================================================|");

			String userInput = getInput("MIWDQSCRZ");
			String userInput3 = "";

			int damageTotal = 0;
			if (userInput.equalsIgnoreCase("M")) {
				ShipSetup.ShipSetupOrModify("N"); // Pass "N" to NOT go on to Impulse Procedure ("Y" to go to...)
			} else if (userInput.equalsIgnoreCase("I")) {
				if (currentGameYard.numShips > 0) {
					PhaseCalculation.PhaseCalc();
				} else {
					System.out.print(
							"You have no ships assigned to the current game.  Would you like to add some ships?");
					userInput3 = getInput("YN");
					if (userInput3.equalsIgnoreCase("Y")) {
						ShipSetup.ShipSetupOrModify("N");
					}
				}
				System.out.println();
			} else if (userInput.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam(-1);
			} else if (userInput.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc(-1);
			} else if (userInput.equalsIgnoreCase("S")) {
				defaultYard.displayShipyardMenu(-1);
			} else if (userInput.equalsIgnoreCase("C")) {
				// Allow user to ADD ships and tell how many systems left of each type
				ModifyShipSystems();
			} else if (userInput.equalsIgnoreCase("P")) {
				PreloadScenario();
			} else if (userInput.equalsIgnoreCase("R")) {
				RulesToRemember();
			} else if (userInput.equalsIgnoreCase("Z")) {
				MonsterStuff.MonsterScenarioCheck();
			} else if (userInput.equalsIgnoreCase("Q")) {
				System.out.println("Exiting Program...");
				break;
			}

			System.out.println();
			System.out.println();
		}
	}

	public static void ModifyShipSystems() {
//		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;

		while (cont) {
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                    SHIP SSD CHANGE/MODIFICATION MENU                     |");

			if (Driver.currentGameYard.numShips > 0) {
				System.out.println("|==========================================================================|");
				System.out.println("|                  Current ship, object and monster list:                  |");
				System.out.println("|==========================================================================|");
				System.out.println();

				ShipSetup.SortCurrentShipyard();
				ShipSetup.PrintCurrentShipsInGame();

				System.out.println();
			}

			System.out.println("|==========================================================================|");
			System.out.println("|       [A]dd Ship from Shipyard   [M]odify Systems   [R]emove Ship        |");
			System.out.println("|                      RETURN to return to Main Menu                       |");
			System.out.println("|==========================================================================|");

			String userInput = Driver.getInput("AMR");

			if (userInput.contentEquals("")) {
				// break;
				cont = false;

			} else if (userInput.equalsIgnoreCase("A")) {

				Driver.defaultYard.displayShipyardMenu(1);

			} else if (userInput.equalsIgnoreCase("M")) {

				boolean cont2 = true;
				while (cont2) {

					int dummyArray[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					System.out.print("Modify systems of which ship? [RETURN to cancel]");

					int shipNumInput = Driver.getNumber(1, Driver.currentGameYard.numShips);

					if (shipNumInput == -1) {
						cont2 = false;
						// break;
					} else {
//						System.out.println();
//						System.out.println("Alternate Parts on SSDs include (Annex #7E):");
//						System.out.println("\tFlag Bridge = Security, Web, Displacement Device");
//						System.out.println(
//								"\tTorpedo = Photon Torpedo, Disruptor Bolt, Plasma Torpedo, SFG, Fusion Beam, Tractor-Repulsor Beam");
//						System.out.println("\tDrone = ADD, ESG, Hellbore, Plasmatic Pulsars, Power Absorbers");
//						System.out.println("\tShuttle = Fighter, Mine Andormedan Hangar");
//						System.out.println("\tCargo = Repair, Mine");
//						System.out.println("\tFront/Aft Hull = Repair");
//						System.out.println();
						System.out.println(
								"Please indicate how many boxes are left on the SSD \nfor the following systems (RETURN to cancel):");

						int cancel = -1;
						for (int numPart = 0; numPart <= 24; numPart++) {
							if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name == "Flag Bridge") {
								System.out.println("Flag Bridge = Security, Web, Displacement Device");
							} else if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name == "Torpedo") {
								System.out.println("Torpedo = Photon Torpedo, Disruptor Bolt, Plasma Torpedo, SFG, Fusion Beam, Tractor-Repulsor Beam");
							} else if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name == "Drone") {
								System.out.println("Drone = ADD, ESG, Hellbore, Plasmatic Pulsars, Power Absorbers");
							} else if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name == "Shuttle") {
								System.out.println("Shuttle = Fighter, Mine Andormedan Hangar");
							} else if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name == "Cargo") {
								System.out.println("Cargo = Repair, Mine Rack");
							} else if (((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name.contains("Hull")) {
								System.out.println("Front/Aft Hull = Repair");
							}
							System.out.print( "\t" + ((Starship) currentGameYard.list[shipNumInput - 1]).ssd[numPart].name + ": ");
							dummyArray[numPart] = getNumber(0, 100);
							if (dummyArray[numPart] == -1) {
								System.out.println("Modification cancelled");
								cancel = -1;
								break;
							}
						}
						if (cancel == 0) {
							for (int numPart = 0; numPart <= 24; numPart++) {
								((Starship)currentGameYard.list[shipNumInput - 1]).ssd[numPart].numOfThisPart = dummyArray[numPart];
							}
						}
						System.out.println();
						ShipSetup.PrintCurrentShipsInGame();
						System.out.println();
					}
				}

			} else if (userInput.equalsIgnoreCase("R")) {

				System.out.print("Remove which ship? [RETURN to cancel] ");
				int removeInput = -1;

				removeInput = Driver.getNumber(1, Driver.currentGameYard.numShips); // Get a new input

				if (removeInput > 0) {
					System.out.print("Are you sure you want to remove: " + Driver.currentGameYard.list[removeInput-1].name);
					String yesOrNo = Driver.getInput("YN");
					if (yesOrNo.contentEquals("Y")) {
						Driver.currentGameYard.removeShipFromShipyard(removeInput);
					}
				}
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
		System.out.println("|=================================================================================|");
		System.out.println("|                         RETURN to return to Main Menu                           |");
		System.out.println("|=================================================================================|");

		System.out.print("What rule would you like to be reminded of? ");

		while (cont) {
			String userInput2 = Driver.getInput("RAWBSHDPTL");

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
				System.out.println("| E3.0   Disrupter Bolt:         1   Any Src             -                        |");
				System.out.println("|          Overloaded           1+1  Any Src             -                        |");
				System.out.println("| E4.0   Photon Torpedo:        2+2  Warp Only           1/box/Any source         |");
				System.out.println("|          Proximity            2+2  Warp Only                                    |");
				System.out.println("|          Overloaded         2+2+(1-4 extra) Warp Only  2/box/Any source         |");
				System.out.println("| E7.0   Fusion Beam             2   Any Src             -                        |");
				System.out.println("| E8.0   Mauler                                                                   |");
				System.out.println("| E9.0   Tractor-Repulsor Beam  3+3  Any Src             1      Sub. Tractor Beam |");
				System.out.println("| E10.0  Hellbore               3+3  Any Src             3      Surrounding Damage|");
				System.out.println("|          Overloaded           3+6  Any Src             -                        |");
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
			} else if (userInput2.equalsIgnoreCase("R")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                         REPAIR SYSTEMS   (G17.0 / Annex #9)                     |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   - 1 repair point = 1 unit of power from any source (except reserve power)     |");
				System.out.println("|   - No more than 5 repair pts can be applied to any 1 system per turn           |");
				System.out.println("|   - Multiple systems can be in repair simultaneously                            |");
				System.out.println("|   - Unused repair points cannot be saved                                        |");
				System.out.println("|   - Bases & FRDs can repair themselves during a scenario, but at 4x the cost    |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|   ADD                    3  |  Hull               1  |  Probe               3   |");
				System.out.println("|   APR                    4  |  Impulse            5  |  Repair Box          6   |");
				System.out.println("|   Battery                2  |  Lab                5  |  Sensor             10   |");
				System.out.println("|   Bridge (Any Control)   6  |  Mine Rack          4  |  Scanner            10   |");
				System.out.println("|   Cargo                  1  |  PA Panel           5  |  Shield              2   |");
				System.out.println("|   Damage Control         3  |  Phaser-I           5  |  Shuttle Bay         2   |");
				System.out.println("|   Displacement Device   25  |  Phaser-II          4  |  Stasis Field Gen   20   |");
				System.out.println("|   Disruptor: range 40    8  |  Phaser-III         2  |  Special Sensors    15   |");
				System.out.println("|   Disruptor: range 30    3  |  Phaser-IV         10  |  Tractor Beam        3   |");
				System.out.println("|   Disruptor: range 22   15  |  Phaser-G           6  |  Transporter         3   |");
				System.out.println("|   Disruptor: range 15    6  |  Photon Torpedo     8  |  Ubitron Interface   4   |");
				System.out.println("|   Disruptor: range 10   15  |  Plasma-F           5  |  Warp Engine        10   |");
				System.out.println("|   Drone Rack             3  |  Plasma-G          10  |  Warp Reactor        6   |");
				System.out.println("|   Expanding Field Gen   15  |  Plasma-R          20  |  Web                 6   |");
				System.out.println("|   Fusion Beam            6  |  Plasma-S          15  |  Web Caster         15   |");
				System.out.println("|   Hellbore              15  |  Plasmatic Pulsar  15  |                          |");
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
				System.out.println("|    Energy supplied \"adds\" to the number of SSD boxes for a specific shield    |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  NOTE: A shield that is down cannot be reinforced specifically, but GSR would   |");
				System.out.println("|    still block fire coming from that direction.                                 |");
				System.out.println("|=================================================================================|");
			} else if (userInput2.equalsIgnoreCase("H")) {
				System.out.println("|=================================================================================|");
				System.out.println("|                            HIGH ENERGY TURNS (C6.0)                             |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - Cost = 5 * movement cost                                                     |");
				System.out.println("|  - Defined as a \"snap turn\" to face any adjacent hex regardless of turn mode  |");
				System.out.println("|  - Does not affect acceleration                                                 |");
				System.out.println("|  - Cannot be preformed on Impulse 1                                             |");
				System.out.println("|  - Seeking weapons and fighters may perform 1 HET per turn, never breakdown     |");
				System.out.println("|  - Immediately following a HET, roll 1 die to see if breakdown occurs           |");
				System.out.println("|---------------------------------------------------------------------------------|");
				System.out.println("|  - BREAKDOWN (C6.54)                                                            |");
				System.out.println("|      - On the first HET of a turn, 2 is subtracted from the die roll            |");
				System.out.println("|      - If breakdown occurs:                                                     |");
				System.out.println("|          - Immediate stop                                                       |");
				System.out.println("|          - Destroyed: 1/5 of warp boxes & 1/3 crew units (round down)           |");
				System.out.println("|          - 2 internal hits via DAC                                              |");
				System.out.println("|          - Breakdown rating reduced by 1                                        |");
				System.out.println("|          - Stasis fields, Expanding Spheres, Tractor Beams released             |");
				System.out.println("|          - For 8 Impulses, or the rest of the turn (whichever is less):         |");
				System.out.println("|              - No weapons may be fired (inc. seeking wepaons)                   |");
				System.out.println("|              - No shuttles/fighters may be launched/recovered                   |");
				System.out.println("|              - No tractor beams/transporters may be used                        |");
				System.out.println("|      - Ship may resume \"normal\" operations at the beginning of the next turn    |");
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
		System.out.println("|      3. The Moray Eel of Space   (SM3.0)                                        |");
		System.out.println("|      4. The Cosmic Cloud         (SM4.0)                                        |");
		System.out.println("|      5. The Sunsnake             (SM5.0)                                        |");
		System.out.println("|      6. The Mind Monster         (SM6.0)                                        |");
		System.out.println("|      7. The Space Dragon         (SM7.0)                                        |");
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
			System.out.println("|    101.  2 Federation, 2 Kligon, 2 Random Monsters                              |");
			System.out.println("|    102.  1 Federation, 1 Gorn, Civilian Freighter                               |");
			System.out.println("|    103.  1 Federation, 1 Klingon, 1 Hydran                                      |");
			System.out.println("|    104.  1 Romulan, 1 Tholian, Civilian Freighter                               |");
			System.out.println("|    105.  5 Random Monsters                                                      |");
			System.out.println("|=================================================================================|");
//		}
		
		boolean scenarioLoaded = false; 
		
		while (scenarioLoaded == false) {
			System.out.print("What scenario to load? ");
			int scenario = getNumber(1,200);
		
			if (scenario == 1) {
				MonsterScenario = 1;
				scenarioLoaded = true;

				currentGameYard.addShipToShipyard(defaultYard.list[337]);				//  Planet Crusher
				currentGameYard.list[0].speed = 6;
				
				currentGameYard.addShipToShipyard(defaultYard.list[354]);				//  Planet
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				currentGameYard.list[1].speed = 0;
				currentGameYard.list[1].name = "Sheboygen III";
				
				currentGameYard.addShipToShipyard(defaultYard.list[3]);					//  Federation CC
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 2) {
				MonsterScenario = 2;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[338]);				//  Space Amoeba
				currentGameYard.list[0].speed = 4;
				
				currentGameYard.addShipToShipyard(defaultYard.list[3]);					//  Federation CC
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 3) {
				MonsterScenario = 3;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[339]);				//  Moray Eel of Space
				currentGameYard.list[0].speed = 6;
				
			} else if (scenario == 4) {
				MonsterScenario = 4;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[340]);				//  Cosmic CLoud
				currentGameYard.list[0].speed = 4;
				
			} else if (scenario == 5) {
				MonsterScenario = 5;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[341]);				//  Sunsnake
				currentGameYard.list[0].speed = 3;
				
			} else if (scenario == 6) {
				MonsterScenario = 6;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[342]);				//  Mind Monster
				currentGameYard.list[0].speed = 6;

				currentGameYard.addShipToShipyard(defaultYard.list[354]);				//  Planet
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				currentGameYard.list[1].speed = 0;
				currentGameYard.list[1].name = "Libraria IV";
				currentGameYard.list[1].ssd[24].remaining = 75;

			} else if (scenario == 8) {
				MonsterScenario = 8;
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[347]);				//  Arastoz 1x
				currentGameYard.list[0].speed = 14;
				currentGameYard.list[0].name = "Arastoz A";
				
				currentGameYard.addShipToShipyard(defaultYard.list[348]);				//  Arastoz 1x
				currentGameYard.list[1].speed = 14;
				currentGameYard.list[1].name = "Arastoz B";
				
				currentGameYard.addShipToShipyard(defaultYard.list[349]);				//  Arastoz 1x
				currentGameYard.list[2].speed = 14;
				currentGameYard.list[2].name = "Arastoz C";
				
				currentGameYard.addShipToShipyard(defaultYard.list[350]);				//  Arastoz 1x
				currentGameYard.list[3].speed = 14;
				currentGameYard.list[3].name = "Arastoz D";
				
				currentGameYard.addShipToShipyard(defaultYard.list[3]);					//  Federation CC
				currentGameYard.list[4].speed = DamageAllocation.rollDice(1, 5) + 1;
				
			} else if (scenario == 101) {
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[0]);					//  Federation DN
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[3]);					//  Federation CC
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[54]);				//  Klingon B10
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[65]);				//  Klingon D7C
				currentGameYard.list[3].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[336 + DamageAllocation.rollDice(1, 6)]);				//  Random Monster
				currentGameYard.list[4].speed = DamageAllocation.rollDice(1, 6);
				
				currentGameYard.addShipToShipyard(defaultYard.list[336 + DamageAllocation.rollDice(1, 6)]);				//  Random Monster
				currentGameYard.list[5].speed = DamageAllocation.rollDice(1, 6);
				
			} else if (scenario == 102) {
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[18]);				//  Federation NCL
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[194]);				//  Gorn CC
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[320]);				//  Civilian Freighter Large
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 5) + 1;
				
			} else if (scenario == 103) {
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[3]);					//  Federation CC
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[65]);				//  Klingon D7C
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[256]);				//  Hydran CVA
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 10) + 1;
				
			} else if (scenario == 104) {
				scenarioLoaded = true;
				currentGameYard.addShipToShipyard(defaultYard.list[228]);				//  Tholian DD
				currentGameYard.list[0].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[121]);				//  Romulan SpH-A
				currentGameYard.list[1].speed = DamageAllocation.rollDice(1, 10) + 1;
				
				currentGameYard.addShipToShipyard(defaultYard.list[320]);				//  Civilian Freighter Large
				currentGameYard.list[2].speed = DamageAllocation.rollDice(1, 5) + 1;
			} else if (scenario == 105) {
				scenarioLoaded = true;
				int numMonsters = DamageAllocation.rollDice(1, 3)+2;
				for (int i = 0; i <= numMonsters-1; i++) {
					int randMonster = DamageAllocation.rollDice(1, 7);
					if (randMonster > 6) {
						randMonster = randMonster + 4;
					}
					currentGameYard.addShipToShipyard(defaultYard.list[336 + randMonster]);				//  Tholian DD
					currentGameYard.list[i].speed = DamageAllocation.rollDice(1, 10) + 1;
					
				}
			}
		}

		System.out.println();
		ShipSetup.SortCurrentShipyard();
		ShipSetup.PrintCurrentShipsInGame();
		System.out.println();
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

	public static int getNumberNoCancel(int small, int big) {
//		Scanner keyboard = new Scanner(System.in);

//		boolean cont = true;
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
																		// for
					// This line and
					// keyboard.nextLine(); // this line prevent a very bad infinite loop
			}
//			else {
//				input = -1; // user Input is "", returns -1
//				cont = false;
//			}
		}
		return input;
	}

}
