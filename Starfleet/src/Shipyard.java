
public class Shipyard {

	public static int numShipyardShips = 0;
//	public static Starship[] list;
//	
//	public Shipyard() {
//		numShipyardShips = 0;
//		list = new Starship[50];
//	}
//	
//	public void addShipToShipyard(Starship ship) {
//		list[numShipyardShips] = ship;
//	}
	
	public static void GotoShipyard (int pass) {
		String shipyard[][] = {{"Race", "Type", "CU", "BP", "BPV", "BD", "MC", "SS", "SC", "TM", "RN", "YiS"},
							   {"Federation", "DN", "50", "14", "180", "3-6", "1.5", "4", "2", "E", "2", "168"},
							   {"Federation", "DN+", "52", "14", "205", "3-6", "1.5", "4", "2", "E", "17", "175"},
							   {"Federation", "CX", "46", "14", "268", "5-6", "1", "4", "2", "D", "52", "-"},
							   {"Federation", "CC", "45", "10", "137", "5-6", "1", "3", "3", "D", "3", "83"},
							   {"Federation", "CC+", "45", "10", "145", "5-6", "1", "3", "3", "D", "3", "165"},
							   {"Federation", "CA", "43", "10", "125", "5-6", "1", "3", "3", "D", "4", "65"},
							   {"Federation", "CA+", "43", "10", "137", "5-6", "1", "3", "3", "D", "4", "165"},
							   {"Federation", "CVA", "49", "10", "172/150", "4-6", "1", "2+4", "2", "D", "13", "171"},
							   {"Federation", "CVA+", "49", "10", "182/160", "4-6", "1", "2+4", "2", "D", "13", "175"},
							   {"Federation", "CVS", "46", "10", "142", "5-6", "1", "2+4", "3", "D", "29", "168"},
							   {"Federation", "GS", "45", "12", "140/120", "5-6", "1", "2", "3", "D", "16", "72"},
							   {"Federation", "GS+", "45", "12", "150/130", "5-6", "1", "2", "3", "D", "16", "175"},
							   {"Federation", "GS(CVL)", "44", "6", "133", "5-6", "1", "1+2", "3", "D", "16", "170"},
							   {"Federation", "GS(CVL+", "44", "6", "143", "5-6", "1", "1+2", "3", "D", "16", "175"},
							   {"Federation", "CL", "37", "8", "93", "4-6", "0.75", "2", "3", "C", "5", "63"},
							   {"Federation", "CL+", "37", "8", "101", "4-6", "0.75", "2", "3", "C", "5", "165"},
							   {"Federation", "CLS", "40", "10", "105/90", "4-6", "0.75", "3", "3", "C", "N6", "125"},
							   {"Federation", "ECL", "40", "6", "90", "4-6", "0.75", "4", "3", "C", "15", "171"},
							   {"Federation", "NCL", "36", "8", "116", "4-6", "0.67", "2", "3", "C", "18", "170"},
							   {"Federation", "NCL+", "36", "8", "120", "4-6", "0.67", "2", "3", "C", "18", "175"},
							   {"Federation", "NSC", "32", "8", "120/100", "4-6", "0.67", "2", "3", "C", "19", "176"},
							   {"Federation", "NSC+", "32", "8", "124/104", "4-6", "0.67", "2", "3", "C", "19", "180"},
							   {"Federation", "NEC", "38", "8", "120", "4-6", "0.67", "2", "3", "C", "20", "184"},
							   {"Federation", "DD", "20", "6", "94", "3-6", "0.5", "1", "4", "C", "6", "65"},
							   {"Federation", "DD+", "20", "6", "106", "3-6", "0.5", "1", "4", "C", "6", "165"},
							   {"Federation", "DDL", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "27", "160"},
							   {"Federation", "DDG", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "28", "155"},
							   {"Federation", "DE", "22", "4", "92", "3-6", "0.5", "3", "4", "C", "14", "171"},
							   {"Federation", "DEA", "22", "4", "98", "3-6", "0.5", "3", "4", "C", "23", "176"},
							   {"Federation", "SC", "19", "6", "120/100", "3-6", "0.5", "1", "4", "C", "7", "65"},
							   {"Federation", "SC+", "19", "6", "150/120", "3-6", "0.5", "1", "4", "C", "7", "165"},
							   {"Federation", "MS(CL)", "30", "6", "94/80", "4-6", "0.75", "2", "3", "C", "21", "158"},
							   {"Federation", "NMS", "30", "8", "116/90", "4-6", "0.67", "2", "3", "C", "30", "175"},
							   {"Federation", "NMS+", "30", "8", "120/94", "4-6", "0.67", "2", "3", "C", "30", "180"},
							   {"Federation", "FF", "16", "6", "71", "5-6", "0.33", "1", "4", "B", "25", "65"},
							   {"Federation", "FFG", "16", "6", "75", "5-6", "0.33", "1", "4", "B", "26", "160"},
							   {"Federation", "Tug", "22", "2", "88/60", "2-6", "*", "1", "3", "*", "8", "72"},
							   {"Federation", "Tug+", "22", "2", "96/68", "2-6", "*", "1", "3", "*", "8", "165"},
							   {"Federation", "BT", "30", "10", "168", "2-6", "1.5", "1", "2", "E", "10", "115"},
							   {"Federation", "BT+", "30", "10", "184", "2-6", "1.5", "1", "2", "E", "10", "165"},
							   {"Federation", "CVT", "42", "6", "148/90", "2-6", "*", "2+4", "2", "E", "22", "172"},
							   {"Federation", "P-CV", "20", "4", "60/30", "-", "*", "1+4", "4*", "-", "22", "172"},
							   {"Federation", "P-SL", "4+30", "2", "48/20", "-", "*", "-", "4*", "-", "9", "72"},
							   {"Federation", "P-SL+", "4+30", "2", "56/28", "-", "*", "-", "4*", "-", "9", "165"},
							   {"Federation", "BP", "8", "8", "88/45", "-", "*", "-", "4*", "-", "10", "115"},
							   {"Federation", "BP+", "8", "8", "96/53", "-", "*", "-", "-", "-", "10", "165"},
							   {"Federation", "P-CP", "0", "0", "14/10", "-", "*", "-", "4", "-", "11", "72"},
							   {"Federation", "Pol", "6", "2", "40", "6", "0.33", "1", "4", "A", "12", "72"},
							   {"Federation", "Pol+", "6", "2", "48", "6", "0.33", "1", "4", "A", "12", "165"},
							   {"Federation", "PolCVE", "12", "2", "60", "5-6", "0.5", "1+2", "4", "B", "24", "176"},
							   {"Federation", "DN Scr", "30", "8", "90", "2-6", "0.5", "-", "4*", "C", "-", "168"},
							   {"Federation", "CA Scr", "20", "5", "60/20", "-", "*", "-", "4*", "-", "-", "65"},
							   {"Federation", "S-Qship", "6", "4", "40", "2-6", "0.33", "-", "4", "B", "-", "74"},
							   {"Federation", "L-Qship", "12", "8", "81", "2-6", "0.5", "-", "4", "B", "-", "74"},
							   {"Klingon", "B-10", "81", "32", "316", "2-6", "2", "2+2", "2", "E", "17", "-"},
							   {"Klingon", "C-9B", "62", "24", "210", "3-6", "1.5", "2", "2", "D", "2", "175"},
							   {"Klingon", "C-9A", "62", "24", "221", "3-6", "1.5", "2", "2", "D", "2", "180"},
							   {"Romulan", "DN ", "62", "22", "203", "3-6", "1.5", "2", "2", "D", "2", "170"},
							   {"Romulan", "BC", "52", "20", "142", "4-6", "1", "2", "3", "C", "3", "170"},
							   {"Romulan", "CA", "42", "12", "133", "5-6", "1", "1", "3", "C", "4", "68"},
							   {"Gorn", "DN", "62", "24", "220", "4-6", "1.5", "4", "2", "E", "11", "171"},
							   {"Gorn", "DN+", "64", "26", "230", "4-6", "1.5", "4", "2", "E", "11", "173"},
							   {"Gorn", "DNF", "66", "30", "244", "4-6", "1.5", "4", "2", "E", "11", "175"},
							   {"Gorn", "CX", "48", "18", "260", "5-6", "1", "3", "2", "D", "S2", "-"},
							   {"Gorn", "CC", "50", "20", "175", "5-6", "1", "3", "3", "D", "18", "175"},
							   {"Kzinti", "SSCS", "70", "30", "245", "4-6", "1.5", "3+6", "2", "E", "24", "-"},
							   {"Kzinti", "SCS", "65", "24", "215", "4-6", "1.5", "3+3", "2", "E", "11", "181"},
							   {"Kzinti", "CVA", "65", "20", "215", "4-6", "1.5", "2+6", "2", "E", "25", "173"},
							   {"Kzinti", "CV", "50", "20", "147", "5-6", "1", "3+3", "3", "E", "6", "166"},
							   {"Tholian", "D", "45", "14", "175", "4-6", "1", "2", "2", "C", "5", "169"},
							   {"Tholian", "CX", "36", "10", "215", "5-6", "0.75", "1", "2", "B", "S2", "-"},
							   {"Tholian", "CX", "34", "10", "120", "4-6", "0.75", "1", "3", "B", "6", "147"},
							   {"Tholian", "CVA", "40", "8", "141", "4-6", "0.75", "1+4", "3", "B", "9", "173"},
							   {"Tholian", "DD", "18", "8", "80", "5-6", "0.5", "-", "4", "A", "4", "115"}};
		
		numShipyardShips = 74;

		boolean cont = true; 
		while (cont) {
			String userRace = "";
			System.out.println();
			System.out.println();
			System.out.println("|==========================================================================|");
			System.out.println("|                              THE SHIPYARD                                |");
			System.out.println("|==========================================================================|");
			System.out.println("|    Races Available in the Shipyard:               [A]ll                  |");
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
	
			userRace = Driver.getInput("AFKGRZT");
			
			String choices = "AFKGRZT";					//  Only do this if non-RETURN / If RETURN then don't do anything and return
			if (userRace.equalsIgnoreCase("A") || userRace.equalsIgnoreCase("F") || userRace.equalsIgnoreCase("K") || userRace.equalsIgnoreCase("G") || userRace.equalsIgnoreCase("R") || userRace.equalsIgnoreCase("Z") || userRace.equalsIgnoreCase("T")) {    
				System.out.println();
				System.out.println("ShpYrd\tRace\tShip\tCrew\tBrdg\tBPV\tBreak\tMove\tSpare\tSize\tTurn\tRule\tYear in");
				System.out.println("Number\t\tType\tUnits\tParties\t\tDown\tCost\tShtls\tClass\tMode\tNumber\tService");
				System.out.println("-------------------------------------------------------------------------------------------------------------");
		
				String race = getRace(userRace);
				
				printShips(race, shipyard);
				
				System.out.println();
			
				int whichShip = -1;
				while (whichShip < 0) {
					System.out.println("Which ship to add to the game?  [0 to cancel]");
			
					whichShip = Driver.getNumber(0,numShipyardShips);
				
					if (whichShip > 0) {
						Starship newShip = new Starship();
						newShip.name = shipyard[whichShip][0].substring(0,3) + "-" + shipyard[whichShip][1];
						newShip.turnMode = shipyard[whichShip][9];
						newShip.speed = 0;
						Driver.starships[Driver.numShips] = newShip;
						Driver.numShips++;
						whichShip = -1;
					} else {
						whichShip = 0;
					}
				}
			}
			
			if (userRace.contentEquals("")) {
				cont = false;
			}
		}
	}
	
	public static void printShips (String r, String [][] shipInTheYard) {

		if (r.equalsIgnoreCase("A")) {
			for (int x = 1; x <= numShipyardShips; x++) {
				if (x < 10) {
					System.out.print("  " + x + ")" + "\t");
				} else {
					System.out.print(" " + x + ")" + "\t");
				}
				System.out.print(shipInTheYard[x][0].substring(0,3) + "\t");
				for (int y = 1; y <=11; y++) {
					System.out.print(shipInTheYard[x][y] + "\t");
				}
				System.out.println();
			}
		} else {
			for (int x = 1; x <= numShipyardShips; x++) {
				if (shipInTheYard[x][0].contentEquals(r)) {
					if (x < 10) {
						System.out.print(" " + x + ")" + "\t");
					} else {
						System.out.print(x + ")" + "\t");
					}
					System.out.print(shipInTheYard[x][0].substring(0,3) + "\t");
					for (int y = 1; y <=11; y++) {
						System.out.print(shipInTheYard[x][y] + "\t");
					}
					System.out.println();
				}
			}
		}
		
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
