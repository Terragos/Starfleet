import java.util.Scanner;
import java.util.Random;

public class DamageAllocation {

	public static void DamageAlloc() {
		
		System.out.println("================================================================");
		System.out.println("               DAMAGE ALLOCATION CALCULATIONS");
		System.out.println("================================================================");
		
		System.out.print("Number of Damage Points to Allocate: ");
		Scanner damage = new Scanner(System.in);
		int totalDamage = damage.nextInt();
		
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
	
		int dieRoll = rollDice(1, 143, true) - 1;
		
		System.out.println("");
		System.out.println("Starting Chart Box (Random number 0-142): " + (dieRoll));
		System.out.println("================================================================");
		System.out.println("Press RETURN to acknowledge that system destroyed");
		System.out.println("Press \"N\" if NO system of that type is available to be destroyed");
		System.out.println("================================================================");
		System.out.println("Damage\tSystem Name");
		System.out.println("Count");
		System.out.println("================================================================");
		
		int chartspot = dieRoll;

		for(int s = 1; s<=totalDamage; s++)
		{
			if (chartspot <= 142) {
				// System.out.print(s + "\t" + dieRoll + "\t" + chartspot + "\t" + special[chartspot] + "\t" + systemName[chartspot]);
				System.out.print(s + "\t" + systemName[chartspot]);

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
						s--;
						chartspot++;
						break;
					} else {}
				}
			} else {
				// System.out.print(s + "\t" + dieRoll + "\t" + (chartspot-143) + "\t" + special[chartspot-143] + "\t" + systemName[chartspot-143]);
				System.out.print(s + "\t" + systemName[chartspot-143]);

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
						s--;
						chartspot++;
						break;
					} else {}
				}
			}
		}
		
		waitForReturn();
	}
	/* rollDice */
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
		
		return totalDieRoll;
	}

	/* Wait for Return */
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
	
}
