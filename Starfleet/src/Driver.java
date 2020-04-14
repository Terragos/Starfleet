import java.util.Scanner;

public class Driver {

	/* MAIN Method */
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		
		while(cont) {
			System.out.println("|==================================|");
			System.out.println("|    STAR FLEET BATTLES UTILITY    |");
			System.out.println("|==================================|");
			System.out.println("|    What would you like to do?    |");
			System.out.println("|==================================|");
			System.out.println("| [M]ovement Impulse Calculations  |");
			System.out.println("| [W]eapon Damage Calculations     |");
			System.out.println("| [D]amage Allocation Calculations |");
			System.out.println("|==================================|");
			System.out.println("|              [Q]uit              |");
			System.out.println("|==================================|");
			
			String input = keyboard.nextLine();

			//  IS THERE SOMEWAY TO CLEAR THE SCREEN?
			//  TO START IN THE TOP LEFT CORNER?

			if(input.equalsIgnoreCase("M")) {
				PhaseCalculation.PhaseCalc();
			} else if(input.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam();
			} else if(input.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc();
			} else if(input.equalsIgnoreCase("Q")) {
				System.out.println("Exiting Program...");
				break;
			} else {
				continue;
			}
			
			System.out.println();
			System.out.println();
		}
	}

}
