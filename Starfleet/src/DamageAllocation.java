import java.util.Scanner;
import java.util.Random;

public class DamageAllocation {

	public static Scanner keyboard = new Scanner(System.in);
	
	public static String[][] systemName = {
		{"", "", "", "", "", "", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", "", "", "", "", "", ""},
		{"Bridge", "Flag Bridge", "Sensor", "Damage Control", "Aft Hull", "Left Warp Engine", "Transporter", "Tractor Beam", "Shuttle", "Lab", "Front Hull", "Right Warp Engine", "Excess Damage"},
		{"Drone", "Phaser", "Impulse", "Left Warp Engine", "Right Warp Engine", "Aft Hull", "Shuttle", "Damage Control", "Center Warp Engine", "Lab", "Battery", "Phaser", "Excess Damage"},
		{"Phaser", "Transporter", "Right Warp Engine", "Impulse", "Front Hull", "Aft Hull", "Left Warp Engine", "APR", "Lab", "Transporter", "Probe", "Center Warp Engine", "Excess Damage"},
		{"Right Warp Engine", "Aft Hull", "Cargo", "Battery", "Shuttle", "Torpedo", "Left Warp Engine", "Impulse", "Right Warp Engine", "Tractor Beam", "Probe", "Any Weapon", "Excess Damage"},
		{"Front Hull", "Impulse", "Lab", "Left Warp Engine", "Sensor", "Tractor Beam", "Shuttle", "Right Warp Engine", "Phaser", "Transporter", "Battery", "Any Weapon", "Excess Damage"},
		{"Cargo", "Front Hull", "Battery", "Center Warp Engine", "Shuttle", "APR", "Lab", "Phaser", "Any Warp Engine", "Probe", "Aft Hull", "Any Weapon", "Excess Damage"},
		{"Aft Hull", "APR", "Shuttle", "Right Warp Engine", "Scanner", "Tractor Beam", "Lab", "Left Warp Engine", "Phaser", "Transporter", "Battery", "Any Weapon", "Excess Damage"},
		{"Left Warp Engine", "Front Hull", "Cargo", "Battery", "Lab", "Drone", "Right Warp Engine", "Impulse", "Left Warp Engine", "Tractor Beam", "Probe", "Any Weapon", "Excess Damage"},
		{"Phaser", "Tractor Beam", "Left Warp Engine", "Impulse", "Aft Hull", "Front Hull", "Right Warp Engine", "APR", "Lab", "Transporter", "Probe", "Center Warp Engine", "Excess Damage"},
		{"Torpedo", "Phaser", "Impulse", "Right Warp Engine", "Left Warp Engine", "Front Hull", "Tractor Beam", "Damage Control", "Center Warp Engine", "Lab", "Battery", "Phaser", "Excess Damage"},
		{"Aux Control", "Emergency Bridge", "Scanner", "Probe", "Front Hull", "Right Warp Engine", "Transporter", "Shuttle", "Tractor Beam", "Lab", "Aft Hull", "Left Warp Engine", "Excess Damage" } 
	};
	
	public static int[][] special = { 
		{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
		{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
		{1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	public static int[] hits;
		
	public static Starship currentShip;
	
	public static void DamageAlloc(int num) {   //  -1 coming from Main Menu Method / >0 coming from Weapons Damage Method
		
		if(num == 0) {
			System.out.println("No damage to allocate.");
			return;
		}
		
		int[] array = { -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		hits = array;
		
		System.out.println();
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|                     DAMAGE ALLOCATION PROCEDURE                          |");
		System.out.println("|==========================================================================|");
		
		
		System.out.println();
		int print = ShipSetup.PrintCurrentThingsInGame("SHIP", "");
		System.out.println();
		System.out.print("Deal damage to which ship? [RETURN to cancel] ");
		int input = -5;
		input = ShipSetup.GetAdjustedInput(print, "SHIP", "");
		
		if(input == -1) {
			return;
		}
		
//		System.out.println("input: " + input);
//		System.out.println("ship being damaged: " + Driver.currentGameYard.list[input].name);
		
		currentShip = Driver.currentGameYard.list[input];
		
		if(currentShip.race == "Monster") {				//  If defending object is a monster then pass through damage to "monster damage" method
			System.out.println();
			System.out.println("***  In the future hit [M] to deal damage to a monster from a ship.  ***");
			System.out.println();
			MonsterStuff.MonsterDamageFromShip(num);  
			return;
		}
		
		int totalDamage = 0;
		
		if (num == -1) {
			System.out.print("Number of Damage Points to Allocate: ");
			int damage = (Driver.getNumber(1, 1000));
			if (damage == -1) {
				return;
			}
			totalDamage = damage;
		} else {
			totalDamage = num;
		}
		
		int general = 0;
		int specific = 0;
		int shieldDamage = 0;
		System.out.println();
		System.out.println("\t(GSR = energy allocated / 2)");				//  Apply Damage to 3 shield types first
		System.out.print("\tGeneral Shield Reinforcement  [RETURN = 0]: ");
		general = Driver.getNumber(0, 100);
		if (general == -1) {
			general = 0;
		}
		System.out.print("\tSpecific Shield Reinforcement [RETURN = 0]: ");
		specific = Driver.getNumber(0, 100);
		if (specific == -1) {
			specific = 0;
		}
		System.out.print("\tShield Damage                 [RETURN = 0]: ");
		shieldDamage = Driver.getNumber(0, 100);
		if (shieldDamage == -1) {
			shieldDamage = 0;
		}
		int allShieldDamage = general + specific + shieldDamage;
		totalDamage = totalDamage - allShieldDamage;
		System.out.println();
		System.out.println("\tTOTAL DAMAGE after accounting for shields: " + totalDamage);
		System.out.println();
		System.out.print("\tArmor Damage                  [RETURN = 0]: ");
		int armorDamage = Driver.getNumber(0, 100);
		if (armorDamage == -1) {
			armorDamage = 0;
		}
		System.out.println();
		totalDamage = totalDamage - armorDamage;
		System.out.print("\tTOTAL DAMAGE after accounting for armor : " + totalDamage);
		System.out.println();
		
		if (armorDamage + allShieldDamage >= 20) { 
			RollForCriticalHit();
		}
		
		if (totalDamage <= 0) {
			return;
		} //  Quit and Go to Damage Allocation with total damage			
		
		int die = 0; 
		
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|            Press RETURN to acknowledge that system destroyed             |");
		System.out.println("|     Press \"N\" if NO system of that type is available to be destroyed     |");
		System.out.println("|--------------------------------------------------------------------------|");

		String extraSpace = ShipSetup.getExtraSpaces(totalDamage, 3);

		String textToCenter = "Allocating " + totalDamage + " point(s) of damage";
		String spacedText = spacesToCenter(textToCenter, 74);
		System.out.println(spacedText);
		System.out.println("|==========================================================================|");
		System.out.println();
		if(Driver.TESTING) {
			System.out.println("\tDamage\tRow/\tSystem");
			System.out.println("\tCount\t   Col\tName");
		} else {
			System.out.println("\tDamage\tSystem");
			System.out.println("\tCount\tName");
		}
		System.out.println("     ================================");
		
		boolean damageDealt = true;
		
		for(int damageCount = 1; damageCount <= totalDamage;) {
			
			if(damageDealt == true) {
				die = DamageAllocation.rollDice(2, 6);
				extraSpace = ShipSetup.getExtraSpaces(damageCount, 3);
				if(Driver.TESTING) {
					System.out.print("\t  " + extraSpace + damageCount + "\t" + die + "/");
				} else {
					System.out.print("\t  " + extraSpace + damageCount);
				}
			}
			
			damageDealt = false;
			
			int col = hits[die]; // Starts at 0
			if(Driver.TESTING)
				System.out.print(col);
			
			String searchVal = "";
			if(systemName[die][col].equals("Any Warp Engine")) {				
				if(currentShip.ssd[7].remaining > 0 && currentShip.ssd[6].remaining > 0 && currentShip.ssd[8].remaining > 0) {
					int superCoolNumber = DamageAllocation.rollDice(1,3);
					if (superCoolNumber == 1) {
						searchVal = "Left Warp Engine";
					} else if (superCoolNumber == 2) {
						searchVal = "Center Warp Engine";
					} else {
						searchVal = "Right Warp Engine";
					}
				}else if(currentShip.ssd[7].remaining > 0) {
					searchVal = "Left Warp Engine";
				}else if(currentShip.ssd[6].remaining > 0){
					searchVal = "Center Warp Engine";
				}else{
					searchVal = "Right Warp Engine";
				}
			}else if(systemName[die][col].equals("Any Weapon")) {				
				if(currentShip.ssd[4].remaining > 0 && currentShip.ssd[5].remaining > 0) {
					if(DamageAllocation.rollDice(1,2) == 1) {
						searchVal = "Phaser";
					}else {
						searchVal = "Torpedo";
					}
				}else if(currentShip.ssd[4].remaining > 0) {
					searchVal = "Phaser";
				}else{
					searchVal = "Torpedo";
				}
			}else {
				searchVal = systemName[die][col];
			}
			
			int number = -1;
			for(int i = 0; i < Starship.partNames.length && number == -1; i++) {
				if(Starship.partNames[i].equals(searchVal)){
					number = i;
				}
			}
			
			if(currentShip.ssd[number].remaining >= 1) { // IF REMAINING
				
				System.out.print("\t" + searchVal + " (" + currentShip.ssd[number].numOfThisPart + "/" + currentShip.ssd[number].remaining + ")\t");
				currentShip.ssd[number].remaining--;
				
				if(currentShip.ssd[number].remaining == 0) {
					if(currentShip.ssd[number].name.equals("Excess Damage")) {
						System.out.print(" -- " + currentShip.name + " has been DESTROYED!! -- ");
						System.out.println();
						Driver.currentGameYard.removeShipFromShipyard(input+1);
						return;
					}else {
						System.out.print(" -- Final " + Starship.partNames[number] + " destroyed. -- ");
					}
					hits[die]++;
				} else if (special[die][col] == 1) {
					hits[die]++;
				}
				
				damageDealt = true;
				
			} else if (currentShip.ssd[number].remaining == 0) { // ELSE IF NONE REMAINING
				
				if(Driver.TESTING) {
					System.out.print(" -- No " + Starship.partNames[number] + " available. -- ");
				}
				hits[die]++;
				damageDealt = false;	
			}

			if(damageDealt == true) {
				if(Driver.TESTING) {
					System.out.println();
					for (int i = 0; i <= 24; i++) {
						System.out.print(currentShip.ssd[i].name.charAt(0) + ":" + currentShip.ssd[i].remaining + " ");
					}
				}
				damageCount++;
				String input2 = keyboard.nextLine();

				if(input2.equalsIgnoreCase("N")) {		//  In case SSD is wrong or computer crashes, allow to user hit "N" to
					currentShip.ssd[number].remaining = 0;		//  to user hit "N" to "destroyed" all of those systems with that name.
					damageCount--;
				}
				if (damageCount + armorDamage + allShieldDamage == 20) { 
					RollForCriticalHit();
				}
			}
			
		}
			
		System.out.println();
		System.out.println("Damage Allocation Finished.  [C]ontinue");
		String userInput = Driver.getInputNoCancel("C");

		if (num == -1) {
			System.out.println("Press RETURN to return to Main Menu");
		} else {
			System.out.println("Press RETURN to return to Weapons Damage");
		}
		waitForReturn();
	}
	
	public static void RollForCriticalHit () {
		int die = DamageAllocation.rollDice(2, 6);
		System.out.println();
		System.out.println("===========================================================================");
		if (die == 2) {
			System.out.println("Critical Hit!");
			System.out.println("Weapons tracking systems knocked out for an unkown number of turns.");
			System.out.println("All current lock-on are lost.");
			System.out.println("No weapons can be fired/launched until system is repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		} else if (die == 3) {
			System.out.println("Critical Hit!");
			System.out.println("Transporter failure.  Transporters cannot be used until repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		} else if (die == 4) {
			System.out.println("Critical Hit!");
			System.out.println("Power failure in the labs.  Labs cannot be used until repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		} else if (die >= 5 && die <= 9) {
			System.out.println("No critical hit!");

		} else if (die == 10) {
			System.out.println("Critical Hit!");
			System.out.println("Shuttle launch controls jammed.  No Shuttles can be launched/recovered until repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		} else if (die == 11) {
			System.out.println("Critical Hit!");
			System.out.println("Tractor beam breakdown.  Tractor beams cannot be used until repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		} else if (die == 12) {
			System.out.println("Critical Hit!");
			System.out.println("Warp engine controls are damaged, and the ship cannot use warp energy for movement.");
			System.out.println("1/2 of the output of the warp engines can be used for other purposes.");
			System.out.println("The other 1/2 of the power output cannot be used until repaired.");
			System.out.println();
			System.out.println("Roll one d6 at end of turn (one time only).  A roll of \"6\" always fails.");
			System.out.println("If die roll is less than highest Damage Control number, then the weapons trcking systems are repaired.");
			
		}
		System.out.println("============================================================================");
		System.out.println();
	}
	
	// ROLL DICE METHOD
	public static int rollDice(int numOfDice, int numOfSides) {
		Random randomGenerator = new Random();
		int dieRoll;
		int totalDieRoll = 0;
		
		for(int i = 0; i < numOfDice; i++) {
			dieRoll = randomGenerator.nextInt(numOfSides) + 1;
			totalDieRoll += dieRoll;
		}
		
		
		if (Driver.TESTING) {
			System.out.println(numOfDice + "d" + numOfSides + ": " + totalDieRoll);
		}
		return totalDieRoll;
	}

	// WAIT FOR RETURN
	public static void waitForReturn () {
		Scanner keyboard = new Scanner(System.in);  //  Create a Scanner object
		boolean notSpace = true;
		while (notSpace) {
			String inputString = keyboard.nextLine();  //  Read user input
			if (inputString.contentEquals("")) {
				notSpace = false;
			}
		}
	}
	
	public static String spacesToCenter(String textToCenter, int spaceWidth) {
		String returnString = "";
		String leftSideSpaces = "";
		String rightSideSpaces = "";
		int numSpaces = 0;
		spaceWidth = spaceWidth - textToCenter.length();
		int numLeftSpaces = spaceWidth/2;
		int numRightSpaces = spaceWidth - numLeftSpaces;
		
		for (int i = 1; i <= numLeftSpaces; i++) {
			leftSideSpaces = leftSideSpaces + " ";
			rightSideSpaces = rightSideSpaces + " ";
		}
		if (numLeftSpaces != numRightSpaces) {
			rightSideSpaces = rightSideSpaces + " ";
		}
		returnString = "|" + leftSideSpaces + textToCenter + rightSideSpaces + "|"; 
		return returnString;
	}
	
}
