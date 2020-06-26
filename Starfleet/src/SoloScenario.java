
public class SoloScenario {

	public static int RiftInvaderNum = 2;
	public static int NumberImpulsesSoFar = 0;
	
	public static void SoloScenarios() {
		if (Driver.SoloScenario == 1) {
			if (Driver.TurnNumber == 1) {
				if (NumberImpulsesSoFar <= 16) {
					//  System.out.println("Do nothing");
				}
			} else {
				RiftInvaders();
			}
		
		} else if (Driver.SoloScenario == 2) {
			//  Solo Scenario Info

		} else if (Driver.SoloScenario == 3) {
			//  Solo Scenario Info
			
		} else if (Driver.SoloScenario == 4) {
			//  Solo Scenario Info
			
		} else if (Driver.SoloScenario == 5) {
			//  Solo Scenario Info
			
		}
	}

	public static void RiftInvaders() {
		int invaderChance = DamageAllocation.rollDice(2, 6);
		if (NumberImpulsesSoFar > 32) {			//  Enemy ship shows up at least every 32 impulses
			invaderChance = 2;
			NumberImpulsesSoFar = 0;			//  Reset counter
		}
		int typeChance = DamageAllocation.rollDice(1, 6);
		int entryPositionChance = DamageAllocation.rollDice(1, 6);
		int facingChance = DamageAllocation.rollDice(1, 6) + 64;
		char facingLetter = (char) (facingChance);
		
		if (invaderChance == 2) {
			
			RiftInvaderNum++;
			
			if (typeChance >= 1 & typeChance <= 3) {
				Driver.InstallSpecificShip("Rift Invader", "50");
				int totalShips = Driver.currentGameYard.numShips - 1;
				Driver.currentGameYard.list[totalShips].speed = 20;
				Driver.currentGameYard.list[totalShips].spi = 20/32.0;
				Driver.currentGameYard.list[totalShips].distrv = 0;
				Driver.currentGameYard.list[totalShips].name = Driver.currentGameYard.list[totalShips].name + " " + (char) (64 + RiftInvaderNum);
				System.out.println();
				System.out.println("New Rift Invader (50) arriving from rift section " + entryPositionChance + ", facing " + facingLetter + ".");
				System.out.println();

			} else if (typeChance == 4 || typeChance == 5) {
				Driver.InstallSpecificShip("Rift Invader", "100");
				int totalShips = Driver.currentGameYard.numShips - 1;
				Driver.currentGameYard.list[Driver.currentGameYard.numShips-1].speed = 18;
				Driver.currentGameYard.list[Driver.currentGameYard.numShips-1].spi = 18/32.0;
				Driver.currentGameYard.list[totalShips].distrv = 0;
				Driver.currentGameYard.list[totalShips].name = Driver.currentGameYard.list[totalShips].name + " " + (char) (64 + RiftInvaderNum);
				System.out.println();
				System.out.println("New Rift Invader (100) arriving from rift section " + entryPositionChance + ", facing " + facingLetter + ".");
				System.out.println();
				
			} else if (typeChance == 6) {
				Driver.InstallSpecificShip("Rift Invader", "150");
				int totalShips = Driver.currentGameYard.numShips - 1;
				Driver.currentGameYard.list[Driver.currentGameYard.numShips-1].speed = 16;
				Driver.currentGameYard.list[Driver.currentGameYard.numShips-1].spi = 16/32.0;
				Driver.currentGameYard.list[totalShips].distrv = 0;
				Driver.currentGameYard.list[totalShips].name = Driver.currentGameYard.list[totalShips].name + " " + (char) (64 + RiftInvaderNum);
				System.out.println();
				System.out.println("New Rift Invader (150) arriving from rift section " + entryPositionChance + ", facing " + facingLetter + ".");
				System.out.println();
				
			}
			
		}

	}

}
