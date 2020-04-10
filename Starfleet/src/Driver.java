import java.util.Random;
import java.util.Scanner;

public class Driver {

	/* MAIN Method */
	public static void main(String[] args) 
	{
		int numImpulses=0;
		
		// Die Roll is equal to the die roll that was printed.
		// Do not need to use but we do have access to it here.
		// int dieRoll = rollDice(1, 36, true);
		
		/* Making an Object of type Starship */
		StarShip s1 = new StarShip();
		s1.name = "FED-CV";
		s1.speed = 2;

		StarShip s2 = new StarShip();
		s2.name = "KLI-D7";
		s2.speed = 5;

		StarShip s3 = new StarShip();
		s3.name = "GOR-CA";
		s3.speed = 24;

		if (s1.speed>numImpulses) {
			numImpulses = s1.speed;
		}

		if (s2.speed>numImpulses) {
			numImpulses = s2.speed;
		}

		if (s3.speed>numImpulses) {
			numImpulses = s3.speed;
		}
		
		s1.spi = (double)s1.speed / numImpulses;
		s2.spi = (double)s2.speed / numImpulses;
		s3.spi = (double)s3.speed / numImpulses;

		System.out.println("================================================");
		System.out.println("Ship Speeds");
		System.out.println(s1.name + " = " + s1.speed + "      " + s2.name + " = " + s2.speed + "      "+ s3.name + " = " + s3.speed);
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
					System.out.print("         ");
			}
		
			int x2 = (int)s2.distrv;
		
			if ((((s2.distrv * 10) - (s2.spi * 10)) / 10) < x2) {
				System.out.print("    " + s2.name);
				} else {
					System.out.print("          ");
			}
		
			int x3 = (int)s3.distrv;
		
			if ((((s3.distrv * 10) - (s3.spi * 10)) / 10) < x3) {
				System.out.print("    " + s3.name);
				} else {
					System.out.print("          ");
			}
	
			System.out.println();
			System.out.println();
	
			//  Need "WAIT FOR SPACEBAR" CODE
			//  that does NOT type letters on the screen
			//  or  show hard returns (causes skipped lines)
			
			
			// Scanner console = new Scanner(System.in);
			// String guess = console.next();
			
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
	
	public static void waitForSpace() {
		
		Scanner keyboard = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Waiting for space...");

	    boolean notSpace = true;
	    
	    while(notSpace) {
		    String inputString = keyboard.nextLine();  // Read user input
		    
		    if(inputString.contentEquals(" ")) {
		    	notSpace = false;
		    	System.out.println("Space was pressed!");
		    }
	    }
	   
		  
	}
	

}
