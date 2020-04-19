
public class Shipyard {

	public static int numberOfShipyardMadeThusFar = 0;
	
	public int numShips = 0;
	public Starship[] list;
	public String name;
	
	
	public Shipyard() {
		numberOfShipyardMadeThusFar++;
		numShips = 0;
		list = new Starship[500];
	}
	
	public Shipyard(String name) {
		numberOfShipyardMadeThusFar++;
		numShips = 0;
		list = new Starship[500];
		this.name = name;
	}
	
	public void addShipToShipyard(Starship ship) {
		this.list[numShips] = ship;
		numShips++;
	}
	
	public void removeShipFromShipyard(int remover) {
		for (int n = remover; n <= Driver.currentGameYard.numShips; n++) {	
			this.list[n-1] = this.list[n];
		}
		this.numShips--;
	}
	
	public static Shipyard setupDefaultShipyard() 
	{
		
		Shipyard defaultYard = new Shipyard("default Yard");
		
		Starship ship = new Starship("Federation", "DN", "50", "14", "180", "3-6", "1.5", "4", "2", "E", "2", "168");
		defaultYard.addShipToShipyard(ship);
		
		// Shipyard with 1 ship in it.
		
		ship = new Starship("Federation", "DN+", "50", "14", "180", "3-6", "1.5", "4", "2", "E", "2", "168");
		defaultYard.addShipToShipyard(ship);
		
		// Shipyard with 2 ships in it.

		ship = new Starship("Federation", "CX", "46", "14", "268", "5-6", "1", "4", "2", "D", "52", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CC", "45", "10", "137", "5-6", "1", "3", "3", "D", "3", "83"); 
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CC+", "45", "10", "145", "5-6", "1", "3", "3", "D", "3", "165"); 
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CA", "43", "10", "125", "5-6", "1", "3", "3", "D", "4", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CA+", "43", "10", "137", "5-6", "1", "3", "3", "D", "4", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CVA", "49", "10", "172/150", "4-6", "1", "2+4", "2", "D", "13", "171");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CVA+", "49", "10", "182/160", "4-6", "1", "2+4", "2", "D", "13", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CVS", "46", "10", "142", "5-6", "1", "2+4", "3", "D", "29", "168");
		defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "GS", "45", "12", "140/120", "5-6", "1", "2", "3", "D", "16", "72");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "GS+", "45", "12", "150/130", "5-6", "1", "2", "3", "D", "16", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "GS(CVL)", "44", "6", "133", "5-6", "1", "1+2", "3", "D", "16", "170");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "GS(CVL+", "44", "6", "143", "5-6", "1", "1+2", "3", "D", "16", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "CL", "37", "8", "93", "4-6", "0.75", "2", "3", "C", "5", "63");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "CL+", "37", "8", "101", "4-6", "0.75", "2", "3", "C", "5", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "CLS", "40", "10", "105/90", "4-6", "0.75", "3", "3", "C", "N6", "125");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "ECL", "40", "6", "90", "4-6", "0.75", "4", "3", "C", "15", "171");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NCL", "36", "8", "116", "4-6", "0.67", "2", "3", "C", "18", "170");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NCL+", "36", "8", "120", "4-6", "0.67", "2", "3", "C", "18", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NSC", "32", "8", "120/100", "4-6", "0.67", "2", "3", "C", "19", "176");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NSC+", "32", "8", "124/104", "4-6", "0.67", "2", "3", "C", "19", "180");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NEC", "38", "8", "120", "4-6", "0.67", "2", "3", "C", "20", "184");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "DD", "20", "6", "94", "3-6", "0.5", "1", "4", "C", "6", "65");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "DD+", "20", "6", "106", "3-6", "0.5", "1", "4", "C", "6", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "DDL", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "27", "160");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "DDG", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "28", "155");
	    defaultYard.addShipToShipyard(ship);
 	    ship = new Starship("Federation", "DE", "22", "4", "92", "3-6", "0.5", "3", "4", "C", "14", "171");
 	    defaultYard.addShipToShipyard(ship);
 	    ship = new Starship("Federation", "DEA", "22", "4", "98", "3-6", "0.5", "3", "4", "C", "23", "176");
 	    defaultYard.addShipToShipyard(ship);
 	    ship = new Starship("Federation", "SC", "19", "6", "120/100", "3-6", "0.5", "1", "4", "C", "7", "65");
 	    defaultYard.addShipToShipyard(ship);
 	    ship = new Starship("Federation", "SC+", "19", "6", "150/120", "3-6", "0.5", "1", "4", "C", "7", "165");
 	    defaultYard.addShipToShipyard(ship);
 	    ship = new Starship("Federation", "MS(CL)", "30", "6", "94/80", "4-6", "0.75", "2", "3", "C", "21", "158");
 	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NMS", "30", "8", "116/90", "4-6", "0.67", "2", "3", "C", "30", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "NMS+", "30", "8", "120/94", "4-6", "0.67", "2", "3", "C", "30", "180");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "FF", "16", "6", "71", "5-6", "0.33", "1", "4", "B", "25", "65");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "FFG", "16", "6", "75", "5-6", "0.33", "1", "4", "B", "26", "160");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "Tug", "22", "2", "88/60", "2-6", "*", "1", "3", "*", "8", "72");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "Tug+", "22", "2", "96/68", "2-6", "*", "1", "3", "*", "8", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "BT", "30", "10", "168", "2-6", "1.5", "1", "2", "E", "10", "115");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "BT+", "30", "10", "184", "2-6", "1.5", "1", "2", "E", "10", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "CVT", "42", "6", "148/90", "2-6", "*", "2+4", "2", "E", "22", "172");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "P-CV", "20", "4", "60/30", "-", "*", "1+4", "4*", "-", "22", "172");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "P-SL", "4+30", "2", "48/20", "-", "*", "-", "4*", "-", "9", "72");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "P-SL+", "4+30", "2", "56/28", "-", "*", "-", "4*", "-", "9", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "BP", "8", "8", "88/45", "-", "*", "-", "4*", "-", "10", "115");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "BP+", "8", "8", "96/53", "-", "*", "-", "-", "-", "10", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "P-CP", "0", "0", "14/10", "-", "*", "-", "4", "-", "11", "72");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "Pol", "6", "2", "40", "6", "0.33", "1", "4", "A", "12", "72");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "Pol+", "6", "2", "48", "6", "0.33", "1", "4", "A", "12", "165");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "PolCVE", "12", "2", "60", "5-6", "0.5", "1+2", "4", "B", "24", "176");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "DN Scr", "30", "8", "90", "2-6", "0.5", "-", "4*", "C", "-", "168");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "CA Scr", "20", "5", "60/20", "-", "*", "-", "4*", "-", "-", "65");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "S-Qshp", "6", "4", "40", "2-6", "0.33", "-", "4", "B", "-", "74");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Federation", "L-Qshp", "12", "8", "81", "2-6", "0.5", "-", "4", "B", "-", "74");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Klingon", "B-10", "81", "32", "316", "2-6", "2", "2+2", "2", "E", "17", "-");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Klingon", "C-9B", "62", "24", "210", "3-6", "1.5", "2", "2", "D", "2", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Klingon", "C-9A", "62", "24", "221", "3-6", "1.5", "2", "2", "D", "2", "180");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Romulan", "DN ", "62", "22", "203", "3-6", "1.5", "2", "2", "D", "2", "170");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Romulan", "BC", "52", "20", "142", "4-6", "1", "2", "3", "C", "3", "170");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Romulan", "CA", "42", "12", "133", "5-6", "1", "1", "3", "C", "4", "68");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Gorn", "DN", "62", "24", "220", "4-6", "1.5", "4", "2", "E", "11", "171");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Gorn", "DN+", "64", "26", "230", "4-6", "1.5", "4", "2", "E", "11", "173");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Gorn", "DNF", "66", "30", "244", "4-6", "1.5", "4", "2", "E", "11", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Gorn", "CX", "48", "18", "260", "5-6", "1", "3", "2", "D", "S2", "-");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Gorn", "CC", "50", "20", "175", "5-6", "1", "3", "3", "D", "18", "175");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Kzinti", "SSCS", "70", "30", "245", "4-6", "1.5", "3+6", "2", "E", "24", "-");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Kzinti", "SCS", "65", "24", "215", "4-6", "1.5", "3+3", "2", "E", "11", "181");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Kzinti", "CVA", "65", "20", "215", "4-6", "1.5", "2+6", "2", "E", "25", "173");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Kzinti", "CV", "50", "20", "147", "5-6", "1", "3+3", "3", "E", "6", "166");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Tholian", "D", "45", "14", "175", "4-6", "1", "2", "2", "C", "5", "169");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Tholian", "CX", "36", "10", "215", "5-6", "0.75", "1", "2", "B", "S2", "-");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Tholian", "CX", "34", "10", "120", "4-6", "0.75", "1", "3", "B", "6", "147");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Tholian", "CVA", "40", "8", "141", "4-6", "0.75", "1+4", "3", "B", "9", "173");
	    defaultYard.addShipToShipyard(ship);
	    ship = new Starship("Tholian", "DD", "18", "8", "80", "5-6", "0.5", "-", "4", "A", "4", "115");
	    defaultYard.addShipToShipyard(ship);
	    
	    return  defaultYard;
	}
	
	public void displayShipyardMenu(int pass) {

		boolean cont = true; 
		while (cont) {
			String userRace = "";
			
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                              THE SHIPYARD                                |");
			System.out.println("|==========================================================================|");
			System.out.println("|    Races Available in the Shipyard:                                      |");
			System.out.println("|         [F]ederation          [K]lingon           [G]orn                 |");
			System.out.println("|         [R]omulan             K[z]inti            [T]holian              |");
			System.out.println("|==========================================================================|");
			
			if (pass == -1) {
				System.out.println("|  Display ships from what race?          RETURN to go back to Main Menu   |");
			}
			if (pass == 1) {
				System.out.println("|  Display ships from what race?   RETURN to go back to Modify Ships Menu  |");
			}
			System.out.println("|==========================================================================|");
	
			userRace = Driver.getInput("FKGRZT");
			
			String choices = "FKGRZT";					//  Only do this if non-RETURN / If RETURN then don't do anything and return
			
			if (userRace.contentEquals("")) {
				cont = false;
			} else { //if (userRace.equalsIgnoreCase("A") || userRace.equalsIgnoreCase("F") || userRace.equalsIgnoreCase("K") || userRace.equalsIgnoreCase("G") || userRace.equalsIgnoreCase("R") || userRace.equalsIgnoreCase("Z") || userRace.equalsIgnoreCase("T")) {    
				System.out.println();
		
				String race = getRace(userRace);
				
				int firstOfRace = this.printShipyardBasedOnRace(race); // fed == 0
				// Not the best way of doing this, fix later

				int count = this.numberOfShipsWithRace(race);
				
				int whichShip = -1;
				while (whichShip < 0) {
					System.out.println("Which ship to add to the game?  [0 to cancel]");
			
					whichShip = Driver.getNumber(0, count);
					int adjusted = whichShip + firstOfRace - 1;
				
					if (whichShip > 0) {
						Starship newShip = new Starship();
						newShip.name = (list[adjusted].race).substring(0,3) + "-" + (list[adjusted].shipType);
						newShip.turnMode = list[adjusted].turnMode;
						
						System.out.print("Ship Speed: ");
						int shipSpeed = Driver.getNumber(-1, 32);
						
						newShip.speed = shipSpeed;
						Driver.currentGameYard.addShipToShipyard(newShip);
						whichShip = -1;
					} else {
						whichShip = 0;
					}
				}
			}
		}
	}
	
	public int numberOfShipsWithRace(String race) {
		int count = 0;
		for (int i = 0; i < this.numShips; i++) {
			if (this.list[i].race.equals(race)) {
				count++;
			}
		}	
		return count;
	}
	
	public int printShipyardBasedOnRace (String race) {

		System.out.println("ShpYrd\tRace\tShip\tCrew\tBrdg\tBPV\tBreak\tMove\tSpare\tSize\tTurn\tRule\tYear in");
		System.out.println("Number\t\tType\tUnits\tParties\t\tDown\tCost\tShtls\tClass\tMode\tNumber\tService");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		int num = 1;
		int firstOfRace = -1;
		for (int i = 0; i < this.numShips; i++) {
			if (this.list[i].race.equals(race)) {
				
				if (num < 10) {
					System.out.print("0" + num + ")" + "\t");
					if(firstOfRace == -1) {
						firstOfRace = i;
					}
				} else {
					System.out.print(num + ")" + "\t");
				}
				num++;
				
				System.out.print(this.list[i].toString());
				System.out.println();
			}
		}		
		
		System.out.println();
		
		System.out.println("First of Race: " + firstOfRace);
		return firstOfRace;
	}
	
	public static String getRace(String r) {
		String race = "";
		if (r.equalsIgnoreCase("F")) {
			race = "Federation";
		} else if (r.equalsIgnoreCase("K")) {
			race = "Klingon";
		} else if (r.equalsIgnoreCase("G")) {
			race = "Gorn";
		} else if (r.equalsIgnoreCase("R")) {
			race = "Romulan";
		} else if (r.equalsIgnoreCase("Z")) {
			race = "Kzinti";
		} else if (r.equalsIgnoreCase("T")) {
			race = "Tholian";
		}
		return race;
	}
}
