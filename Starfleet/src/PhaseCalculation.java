import java.util.Random;
import java.util.Scanner;

public class PhaseCalculation {

	public static void PhaseCalc() {
		int numImpulses = 0;
		
		// Die Roll is equal to the die roll that was printed.
		// Do not need to use but we do have access to it here.
		// int dieRoll = rollDice(1, 36, true);
		
		/* Making an Object of type Starship */
		StarShip s1 = new StarShip();
		System.out.print("Ship 1 Name: ");
		Scanner scan1 = new Scanner(System.in);
		String sn1 = scan1.next();
		s1.name = sn1;
		System.out.print("Ship 1 Speed:");
		Scanner scan2 = new Scanner(System.in);
		int i1 = scan2.nextInt();
		s1.speed = i1;
	
		StarShip s2 = new StarShip();
		System.out.print("Ship 2 Name: ");
		Scanner scan3 = new Scanner(System.in);
		String sn2 = scan3.next();
		s2.name = sn2;
		System.out.print("Ship 3 Speed:");
		Scanner scan4 = new Scanner(System.in);
		int i2 = scan4.nextInt();
		s2.speed = i2;
	
		StarShip s3 = new StarShip();
		System.out.print("Ship 3 Name: ");
		Scanner scan5 = new Scanner(System.in);
		String sn3 = scan5.next();
		s3.name = sn3;
		System.out.print("Ship 3 Speed:");
		Scanner scan6 = new Scanner(System.in);
		int i3 = scan6.nextInt();
		s3.speed = i3;
	
		if (s1.speed>numImpulses) {
			numImpulses = s1.speed;
		}
	
		if (s2.speed>numImpulses) {
			numImpulses = s2.speed;
		}
	
		if (s3.speed>numImpulses) {
			numImpulses = s3.speed;
		}
		
		s1.spi = ((double)s1.speed / numImpulses) + 0.000001;  // + 0.000001 fudge factor
		s2.spi = ((double)s2.speed / numImpulses) + 0.000001;
		s3.spi = ((double)s3.speed / numImpulses) + 0.000001;
	
		System.out.println("================================================");
		System.out.println("                   Ship Speeds");
		System.out.println("------------------------------------------------");
		System.out.println("              " + s1.name + "    " + s2.name + "    " + s3.name);
		System.out.print("                " + s1.speed);
		for (int x = 1; x<=s1.name.length()+2; x++) {
			System.out.print(" ");
		}
		System.out.print(s2.speed);
		for (int x = 1; x<=s2.name.length()+3; x++) {
			System.out.print(" ");
		}
		System.out.println(s3.speed);
		System.out.println("================================================");
		System.out.println("          Press RETURN for next Impulse");		
		System.out.println("================================================");
		System.out.println();
	
		for(int i = 1; i<=numImpulses; i++)
		{
			if (i < 10) {
			System.out.print("Impulse 0" + i + ":");
			} else {
				System.out.print("Impulse " + i + ":");
			}
	
			s1.distrv = ((s1.distrv * 10) + (s1.spi * 10)) / 10;
			s2.distrv = ((s2.distrv * 10) + (s2.spi * 10)) / 10;
			s3.distrv = ((s3.distrv * 10) + (s3.spi * 10)) / 10;
		
			int x1 = (int)s1.distrv;
		
			if ((((s1.distrv * 10) - (s1.spi * 10)) / 10) < x1) {
				System.out.print("   " + s1.name);
				} else {
					System.out.print("    ");
					for (int x = 1; x<=s1.name.length()-1; x++) {
					System.out.print(" ");
				}
			}
		
			int x2 = (int)s2.distrv;
		
			if ((((s2.distrv * 10) - (s2.spi * 10)) / 10) < x2) {
				System.out.print("    " + s2.name);
				} else {
					System.out.print("     ");
					for (int x = 1; x<=s2.name.length()-1; x++) {
					System.out.print(" ");
				}
			}
		
			int x3 = (int)s3.distrv;
		
			if ((((s3.distrv * 10) - (s3.spi * 10)) / 10) < x3) {
				System.out.print("    " + s3.name);
				} else {
					System.out.print("    ");
					for (int x = 1; x<=s3.name.length()-1; x++) {
					System.out.print(" ");
				}
			}
	
			//  Print distance so far to check
			//  System.out.print(s1.distrv + "    " + s2.distrv + "    " + s3.distrv);
			
			//  System.out.println();
			//  System.out.println();
	
			//  Need "WAIT FOR SPACEBAR" CODE
			//  that does NOT type letters on the screen
			//  or  show hard returns (causes skipped lines)
			
			waitForSpace();
			
			//  Scanner console = new Scanner(System.in);
			//  String guess = console.next();
			
			//  try {
			//  	System.in.read();
			//  } catch (Exception e){}
		
		}  
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
		
		//Hello World
		
		if(numOfDice == 0) {
			System.out.println("Please use more than zero dice rolls.");
		}
		else if(print == true && numOfDice == 1) {
			System.out.println("Die Roll: " + totalDieRoll);
		}
		else if(print == true){
			System.out.println("Total Dice Roll: " + totalDieRoll);
		}
		else {
			//Nothing since print is false
		}
		
		return totalDieRoll;
	}
	
	/* Wait for Space */
	public static void waitForSpace () {
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
