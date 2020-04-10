import java.util.Random;
import java.util.Scanner;

public class Driver {

	/* MAIN Method */
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		boolean cont = true;
		
		while(cont) {
			System.out.println("What would you like to do?");
			System.out.println("M for Movement Calculations");
			System.out.println("W for Weapon Calculations");
			System.out.println("D for Damage Calculations");
			System.out.println("Q for Quit");
			
			String input = keyboard.nextLine();
			if(input.equalsIgnoreCase("M")) {
				PhaseCalculation.PhaseCalc();
			} else if(input.equalsIgnoreCase("W")) {
				WeaponsDamage.WeaponsDam();
			} else if(input.equalsIgnoreCase("D")) {
				DamageAllocation.DamageAlloc();
			} else if(input.equalsIgnoreCase("Q")) {
				break;
			} else {
				System.out.println("Invalid Entry");
				continue;
			}
			
			System.out.println();
			System.out.println();
		}
	}

}
