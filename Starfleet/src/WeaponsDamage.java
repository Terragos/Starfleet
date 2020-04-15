import java.util.Scanner;

public class WeaponsDamage {
	

	
	public static void WeaponsDam() {

		int totalDamage = 0;
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		while(cont) {
			if (totalDamage == 0) {
				System.out.println("|=================================================================|");
				System.out.println("|                   WEAPONS DAMAGE CALCULATIONS                   |");
				System.out.println("|=================================================================|");
				System.out.println("|  Type [1] Phaser   [P]hoton Torpedo    [D]isruptor Bolt         |");
				System.out.println("|  Type [2] Phaser   P[L]asma Torpedo    [T]ractor-Repulsor Beam  |");
				System.out.println("|  Type [3] Phaser   [F]usion Beam       [E]xpanding Sphere       |");
				System.out.println("|  Type [4] Phaser   [O]verloaded Fusion Beam                     |");
				System.out.println("|=================================================================|");
				System.out.println("|                 [Q]uit and go Damage Allocation                 |");
				System.out.println("|                      [R]eturn to Main Menu                      |");
				System.out.println("|=================================================================|");
			}
		
			System.out.println();
			System.out.print("Weapon:   ");
			String weaponInput = keyboard.next();
			
			
			if(weaponInput.equalsIgnoreCase("Q")) {
				DamageAllocation.DamageAlloc(totalDamage);
				totalDamage = 0;
				continue;
				//  Quit and Go to Damage Allocation with total damage
				//  Pass through total damage number
			} else if(weaponInput.equalsIgnoreCase("R")) {
				//  Quit and Return to Main Menu
				System.out.println("Exiting Program...");
				break;
			}
		
			System.out.print("Number:   ");
			int numberInput = keyboard.nextInt();
			System.out.print("Distance: ");
			int distanceInput = keyboard.nextInt();
			System.out.println();

			if(weaponInput.equalsIgnoreCase("1")) {
				totalDamage = type1Phaser(numberInput, distanceInput, totalDamage);		
				// use totalDamage
			} else if(weaponInput.equalsIgnoreCase("2")) {
				//  Type 2 Phaser
			} else if(weaponInput.equalsIgnoreCase("3")) {
				//  Type 3 Phaser
			} else if(weaponInput.equalsIgnoreCase("4")) {
				//  Type 4 Phaser
			} else if(weaponInput.equalsIgnoreCase("P")) {
				//  Photon Torpedo
			} else if(weaponInput.equalsIgnoreCase("L")) {
				//  Plasma Torpedo
			} else if(weaponInput.equalsIgnoreCase("F")) {
				//  Fusion Beam
			} else if(weaponInput.equalsIgnoreCase("O")) {
				//  Overloaded Fusion Beam
			} else if(weaponInput.equalsIgnoreCase("D")) {
				//  Disruptor Bolt
			} else if(weaponInput.equalsIgnoreCase("T")) {
				//  Tractor-Repulsor Beam
			} else if(weaponInput.equalsIgnoreCase("E")) {
				//  Expanding Sphere
			} else {
				System.out.println("Invalid entry. Try again.");
				continue;
			}
			
			System.out.println("Total Damage: " + totalDamage);
			System.out.println();
		}
	}
	
	public static int type1Phaser(int num, int dist, int total) {
		int intPhaser1[][] = {{0,1,2,3,4,5,6,9,16,26,51,76},
							  {9,8,7,6,5,5,4,3, 2, 1, 1, 0},
							  {8,7,6,5,5,4,3,2, 1, 1, 0, 0},
							  {7,5,5,4,4,4,3,1, 0, 0, 0, 0},
							  {6,4,4,4,4,3,2,0, 0, 0, 0, 0},
							  {5,4,4,4,3,3,1,0, 0, 0, 0, 0},
							  {4,4,3,3,2,2,0,0, 0, 0, 0, 0}};
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
		} else if (dist > 75) {
			dist = 11;			
		}
		int startTotal = total;
		for(int i = 0; i < num; i++) {
			int die = DamageAllocation.rollDice(1,6);
			int damage = intPhaser1[die][dist];
			total += damage;
		}
		System.out.println("Volley Damage: " + (total-startTotal));
		return total;
	}
}
