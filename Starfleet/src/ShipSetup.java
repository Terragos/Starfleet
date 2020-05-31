import java.util.Scanner;

public class ShipSetup {

	public static void SortCurrentShipyard() {			// Sort ships by speed, fastest first
		Starship temp = new Starship();
		Driver.currentGameYard.list[Driver.currentGameYard.numShips] = temp;

		for (int x = 0; x <= Driver.currentGameYard.numShips-1; x++) {
			for (int y = 0; y <= Driver.currentGameYard.numShips-2; y++) {
				if (Driver.currentGameYard.list[y].speed < Driver.currentGameYard.list[y+1].speed) {
					
					Driver.currentGameYard.list[49] = Driver.currentGameYard.list[y];
					Driver.currentGameYard.list[y] = Driver.currentGameYard.list[y+1];
					Driver.currentGameYard.list[y+1] = Driver.currentGameYard.list[49];
				}
			}
		}
	}
	
	public static int GetAdjustedInput(int printSpecific, String whatToPrint, String whatAspect1) {
		int inputNum = Driver.getNumber(0, printSpecific);
		
		whatToPrint = whatToPrint.toUpperCase();
		
		int k = 0;
		for(int i = 0; i < Driver.currentGameYard.numShips; i++) {
			
			if (whatToPrint.contains("SHIP") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
				if(k + 1 == inputNum) {
					return i;
				}else {
					k++;
				}
			}
			
			if (whatToPrint.contains("MONSTER") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) { 
				if(k + 1 == inputNum) {
					return i;
				}else {
					k++;
				}
			}
			
			if (whatToPrint.contains("OTHER") && Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.PLANET) { 
				if(k + 1 == inputNum) { 
					return i;
				} else {
					k++;
				}
			}			
		}
		return -1;
	}
	
	public static int PrintCurrentThingsInGame(String whatToPrint, String whatAspect1 /*, String whatAspect2 */) { 

		whatToPrint = whatToPrint.toUpperCase();
		whatAspect1 = whatAspect1.toUpperCase();
		if (whatAspect1.contains("EW")) {
			whatAspect1 = "ECM / ECCM";
		}
//		whatAspect2 = whatAspect2.toUpperCase();
		
		ShipSetup.SortCurrentShipyard();

		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		System.out.println("     NAME\t\t" + whatAspect1);
//		System.out.println("     Name\t" + whatAspect1 + "\t" + whatAspect2);
		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		
		int printNum = 0;
		
		for (int i = 1; i <= Driver.currentGameYard.numShips; i++) {
		
			Starship ship = Driver.currentGameYard.list[i-1];
			
			String extraSpace = getExtraSpaces(i, 2);
			String extraTab = "";
			if (ship.name.length() < 11 ) {
				extraTab = "\t";
			}
			
			if (whatToPrint.contains("SHIP")) {
				if (ship.kindOfShip == Starship.Ship.STARSHIP) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
						if (ship.speed == 0) {
							System.out.print("\t  <--- SPEED IS ZERO!");
						}
					
					} else if (whatAspect1.contains("BPV")) {
						System.out.print("\t" + ship.BPV);

					} else if (whatAspect1.contains("ECM")) {
						System.out.print("\t  " + (int) ship.ECM + " / " + (int) ship.ECCM);
					}
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("MONSTER")) {
				if (ship.kindOfShip == Starship.Ship.MONSTER) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
						if (ship.speed == 0) {
							System.out.print("\t  <--- SPEED IS ZERO!");
						}

					} else if (whatAspect1.contains("HEALTH")) {
						System.out.print("\t" + ship.ssd[24].remaining);

					} else if (whatAspect1.contains("ECM")) {
						System.out.print("\t  " + (int) ship.ECM + " / " + (int) ship.ECCM);
					}
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("OTHER")) {
				if (ship.kindOfShip == Starship.Ship.PLANET) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);

					} else if (whatAspect1.contains("HEALTH"))
						System.out.print("\t" + ship.ssd[24].remaining);
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("TORPEDO")) {
				if (ship.kindOfShip == Starship.Ship.TORPEDO) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);
					}
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("DRONE")) {
				if (ship.kindOfShip == Starship.Ship.DRONE) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);

					} else if (whatAspect1.contains("ECM")) {
						System.out.print("\t  " + (int) ship.ECM + " / " + (int) ship.ECCM);

					}
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("SHUTTLE")) {
				if (ship.kindOfShip == Starship.Ship.SHUTTLE) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);

					} else if (whatAspect1.contains("ECM")) {
						System.out.print("\t  " + (int) ship.ECM + " / " + (int) ship.ECCM);
					
					}
					
					System.out.println();
				}
			}
			
			if (whatToPrint.contains("FIGHTER")) {
				if (ship.kindOfShip == Starship.Ship.FIGHTER) {
					System.out.print(extraSpace + (++printNum) + ")  " + ship.name + extraTab);
//					System.out.println("ship.kindOfShip: " + ship.kindOfShip);
					
					if (whatAspect1.contains("SPEED")) {
						System.out.print("\t" + ship.speed);

					} else if (whatAspect1.contains("ECM")) {
						System.out.print("\t  " + (int) ship.ECM + " / " + (int) ship.ECCM);

					}
					
					System.out.println();
				}
			}
		}
		
		if (whatAspect1 == "") {
			System.out.print("-------------------");
		} else {
			System.out.print("--------------------------------");
		}
		System.out.println();
		return printNum;
	}
	
	public static String getExtraSpaces (int num, int maxDigits) {

		String extraSpace = "";
		int digitCount = 0;
		
        if (num == 0) {
        	digitCount = 1;
        } else {
        	while(num != 0) {
        		num /= 10;
        		++digitCount;
        	}
        }
        for (int i = 1; i <= maxDigits-digitCount; i++) {
        	extraSpace = extraSpace + " ";
        }
        return extraSpace;
    } 
}
