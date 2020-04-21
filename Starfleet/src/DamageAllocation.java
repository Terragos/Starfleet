import java.util.Scanner;
import java.util.Random;

public class DamageAllocation {

	public static void DamageAlloc(int num) {   //  -1 coming from Main Menu Method / >0 coming from Weapons Damage Method
		
		System.out.println();
		System.out.println();
		System.out.println("|==========================================================================|");
		System.out.println("|                     DAMAGE ALLOCATION PROCEDURE                          |");
		System.out.println("|==========================================================================|");
		
		int totalDamage = 0;
		
		if (num == -1) {
			System.out.print(" Number of Damage Points to Allocate: ");
			int damage = (Driver.getNumber(1, 1000));
			totalDamage = damage;
		} else {
			totalDamage = num;
		}
		
		int general = 0;
		int specific = 0;
		int shieldDamage = 0;
		System.out.println();
		System.out.println("\t(GSR = energy allocated / 2)");				//  Apply Damage to 3 shield types first
		System.out.print("\tGeneral Shield Reinforcement : ");
		general = Driver.getNumber(0, 100);
		System.out.print("\tSpecific Shield Reinforcement: ");
		specific = Driver.getNumber(0, 100);
		System.out.print("\tShield Damage                : ");
		shieldDamage = Driver.getNumber(0, 100);
		totalDamage = totalDamage - general - specific - shieldDamage;
		System.out.println();
		System.out.print("\tTOTAL DAMAGE after accounting for shields: " + totalDamage);
		System.out.println();
		
		if (totalDamage > 0) {    										//  Quit and Go to Damage Allocation with total damage
			String systemName[] = { "Bridge", "Flag Bridge", "Sensor", "Damage Control", "Aft Hull", "Left Warp Engine", "Transporter", "Tractor Beam", "Shuttle", "Lab", "Front Hull", "Right Warp Engine", "Excess Damage",
								    "Drone", "Phaser", "Impulse", "Left Warp Engine", "Right Warp Engine", "Aft Hull", "Shuttle", "Damage Control", "Center Warp Engine", "Lab", "Battery", "Phaser", "Excess Damage",
								    "Phaser", "Transporter", "Right Warp Engine", "Impulse", "Front Hull", "Aft Hull", "Left Warp Engine", "APR", "Lab", "Transporter", "Probe", "Center Warp Engine", "Excess Damage",
								    "Right Warp Engine", "Aft Hull", "Cargo", "Battery", "Shuttle", "Torpedo", "Left Warp Engine", "Impulse", "Right Warp Engine", "Tractor Beam", "Probe", "Any Weapon", "Excess Damage",
								    "Front Hull", "Impulse", "Lab", "Left Warp Engine", "Sensor", "Tractor Beam", "Shuttle", "Right Warp Engine", "Phaser", "Transporter", "Battery", "Any Weapon", "Excess Damage",
								    "Cargo", "Front Hull", "Battery", "Center Warp Engine", "Shuttle", "APR", "Lab", "Phaser", "Any Warp Engine", "Probe", "Aft Hull", "Any Weapon", "Excess Damage",
								    "Aft Hull", "APR", "Shuttle", "Right Warp Engine", "Scanner", "Tractor Beam", "Lab", "Left Warp Engine", "Phaser", "Transporter", "Battery", "Any Weapon", "Excess Damage",
								    "Left Warp Engine", "Front Hull", "Cargo", "Battery", "Lab", "Drone", "Right Warp Engine", "Impulse", "Left Warp Engine", "Tractor Beam", "Probe", "Any Weapon", "Excess Damag",
								    "Phaser", "Tractor Beam", "Left Warp Engine", "Impulse", "Aft Hull", "Front Hull", "Right Warp Engine", "APR", "Lab", "Transporter", "Probe", "Center Warp Engine", "Excess Damage",
								    "Torpedo", "Phaser", "Impulse", "Right Warp Engine", "Left Warp Engine", "Front Hull", "Tractor Beam", "Damage Control", "Center Warp Engine", "Lab", "Battery", "Phaser", "Excess Damage",
								    "Aux Control", "Emergency Bridge", "Scanner", "Probe", "Front Hull", "Right Warp Engine", "Transporter", "Shuttle", "Tractor Beam", "Lab", "Aft Hull", "Left Warp Engine", "Excess Damage" };
			
			int special[] = { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
						      1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
						      1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						      1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
						      0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
						      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						      0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
						      1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
						      1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						      1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
						      1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
		
			int chartspot = ((rollDice(1, 11) - 1) * 13);
			
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
			System.out.println("\tDamage\tSystem Name");
			System.out.println("\tCount");
			System.out.println("     ================================");
			
			for(int damageCount = 1; damageCount <= totalDamage; damageCount++) {
				
				extraSpace = ShipSetup.getExtraSpaces(damageCount, 3);
//				String extraSpace2 = "";
//				if (damageCount < 10) {
//					extraSpace2 = " ";
//				}
				if (chartspot <= 142) {
					System.out.print("\t " + extraSpace + damageCount + "\t" + systemName[chartspot]);
	
					Scanner keyboard = new Scanner(System.in);
					boolean cont = true;
					
					while(cont) {
						String input = keyboard.nextLine();
						if(input.equalsIgnoreCase("")) {
							// System destroyed - loop advances
							if (special[chartspot] == 1) {
								chartspot++;
							}
							break;
						} else if(input.equalsIgnoreCase("N")) {
							// System NOT destroyed - loop does NOT advance, get next system
							damageCount--;
							chartspot++;
							break;
						} else {}
					}
				} else {
					System.out.print("\t" + extraSpace + damageCount + "\t" + systemName[chartspot-143]);
	
					Scanner keyboard = new Scanner(System.in);
					boolean cont = true;
					
					while(cont) {
						String input = keyboard.nextLine();
						if(input.equalsIgnoreCase("")) {
							// System destroyed - loop advances
							if (special[chartspot-143] == 1) {
								chartspot++;
							}
							break;
						} else if(input.equalsIgnoreCase("N")) {
							// System NOT destroyed - loop does NOT advance, get next system
							damageCount--;
							chartspot++;
							break;
						} else {}
					}
				}
			}
			System.out.println();
			if (num == -1) {
				System.out.println("Press RETURN to return to Main Menu");
			} else {
				System.out.println("Press RETURN to return to Weapons Damage");
			}
			waitForReturn();
		}
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
