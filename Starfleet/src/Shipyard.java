
public class Shipyard {

	public static int numberOfShipyardsMadeThusFar = 0;
	
	public int numShips = 0;
	public int numStarships = 0;
	public Starship[] list;
	public String name;
	
	public static boolean showOnlySSDs = true;
	public static boolean showRefitText = false;
	
	public Shipyard() {
		numberOfShipyardsMadeThusFar++;
		numShips = 0;
		numStarships = 0;
		list = new Starship[500];
	}
	
	public Shipyard(String name) {
		numberOfShipyardsMadeThusFar++;
		numShips = 0;
		numStarships = 0;
		list = new Starship[500];
		this.name = name;
	}
	
	public void addShipToShipyard(Starship ship) {
		
		this.list[numShips] = new Starship(ship);
		numShips++;
		if(ship.kindOfShip == Starship.Ship.STARSHIP) 
			numStarships++;
	}
	
	public void removeShipFromShipyard(int remover) {
		System.out.println();
		if(this.list[remover-1].kindOfShip == Starship.Ship.STARSHIP) {
			numStarships--;
		}
		for (int n = remover; n <= Driver.currentGameYard.numShips; n++) {	
			this.list[n-1] = this.list[n];
		}
		this.numShips--;
	}
	
	public static Shipyard setupDefaultShipyard() {	
		Shipyard defaultYard = new Shipyard("default Yard");

//		se = new int[]{};
//		sc = new int[]{};
//		da = new int[]{};
//		ship.setupSSD(B, F, E, A, P, T, C, L, R, I, A, B, TS,TC,P, S, D, L, C, FH,AF,DA,SE,SC,EX,sensNums, scanNums, damConNums);

		//  FEDERATION  =============================================================================================
		
		Starship ship = new Starship("Federation", "DN", "50", "14", "180", "3-6", "1.5", "4", "2", "E", "R2.2", "168");
		int[] se = new int[]{6, 6, 6, 6, 5, 4, 3, 2, 1, 0};
		int[] sc = new int[]{0, 0, 0, 0, 1, 2, 3, 4, 5, 9};
		int[] da = new int[]{6, 6, 4, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 10, 4, 15, 15, 15, 6, 2, 5, 4, 2, 1, 6, 0, 10, 0, 18, 6, 9, 10, 10, 12, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		// Shipyard with 1 ship in it.
		
		ship = new Starship("Federation", "DN+", "52", "14", "205", "3-6", "1.5", "4", "2", "E", "R2.17", "175");
		se = new int[]{6, 6, 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 0, 1, 2, 3, 4, 5, 9};
		da = new int[]{6, 6, 4, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 12, 6, 15, 15, 15, 6, 4, 5, 6, 3, 1, 8, 1, 10, 0, 22, 6, 9, 10, 10, 12, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		// Shipyard with 2 ships in it.

		// Xse Track  ()
		// Xsc Track ()
		// Xda Track  ()

		ship = new Starship("Federation", "CX", "46", "14", "268", "5-6", "1", "4", "2", "D", "R2.52", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CC", "45", "10", "137", "5-6", "1", "3", "3", "D", "R2.3", "83");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 8, 4, 0, 15, 15, 4, 2, 4, 2, 2, 1, 4, 0, 8, 0, 12, 4, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CC+", "45", "10", "145", "5-6", "1", "3", "3", "D", "R2.3", "165");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 10, 4, 0, 15, 15, 4, 2, 4, 2, 2, 1, 4, 1, 8, 0, 12, 4, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CA", "43", "10", "125", "5-6", "1", "3", "3", "D", "R2.4", "65");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 2, 2, 6, 4, 0, 15, 15, 4, 0, 4, 2, 2, 1, 4, 0, 8, 0, 12, 4, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CA+", "43", "10", "137", "5-6", "1", "3", "3", "D", "R2.4", "165");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 2, 2, 8, 4, 0, 15, 15, 4, 2, 4, 2, 2, 1, 4, 1, 8, 0, 12, 4, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CVA", "49", "10", "172/150", "4-6", "1", "2+4", "2", "D", "R2.13", "171");
		se = new int[]{6, 6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 3, 5, 9};
		da = new int[]{6, 4, 4, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 10, 14, 0, 18, 18, 4, 5, 5, 4, 3, 1, 28, 2, 4, 0, 14, 6, 6, 7, 7, 7, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CVA+", "49", "10", "182/160", "4-6", "1", "2+4", "2", "D", "R2.13", "175");
		se = new int[]{6, 6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 3, 5, 9};
		da = new int[]{6, 4, 4, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 10, 14, 0, 18, 18, 4, 5, 5, 4, 3, 1, 28, 4, 4, 0, 14, 6, 6, 7, 7, 7, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "CVS", "46", "10", "142", "5-6", "1", "2+4", "3", "D", "R2.29", "168");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "GSC", "45", "12", "140/120", "5-6", "1", "2", "3", "D", "R2.16", "72");
		se = new int[]{6, 6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 3, 5, 9};
		da = new int[]{6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 2, 2, 12, 2, 0, 15, 15, 4, 2, 6, 7, 2, 2, 8, 1, 10, 10, 12, 4, 7, 7, 7, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "GSC+", "45", "12", "150/130", "5-6", "1", "2", "3", "D", "R2.16", "175");
		se = new int[]{6, 6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 3, 5, 9};
		da = new int[]{6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 2, 2, 12, 2, 0, 15, 15, 4, 2, 6, 7, 2, 2, 8, 3, 10, 10, 12, 4, 7, 7, 7, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "GS(CVL)", "44", "6", "133", "5-6", "1", "1+2", "3", "D", "R2.16", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "GS(CVL+)", "44", "6", "143", "5-6", "1", "1+2", "3", "D", "R2.16", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CL", "37", "8", "93", "4-6", "0.75", "2", "3", "C", "5", "R2.63");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CL+", "37", "8", "101", "4-6", "0.75", "2", "3", "C", "R2.5", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CLS", "40", "10", "105/90", "4-6", "0.75", "3", "3", "C", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "ECL", "40", "6", "90", "4-6", "0.75", "4", "3", "C", "R2.15", "171");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "NCL", "36", "8", "116", "4-6", "0.67", "2", "3", "C", "R2.18", "170");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 6, 4, 0, 12, 12, 4, 4, 2, 2, 2, 1, 4, 1, 4, 0, 5, 5, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "NCL+", "36", "8", "120", "4-6", "0.67", "2", "3", "C", "R2.18", "175");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 8, 4, 0, 12, 12, 4, 4, 2, 2, 2, 1, 4, 1, 4, 0, 5, 5, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "NSC", "32", "8", "120/100", "4-6", "0.67", "2", "3", "C", "R2.19", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "NSC+", "32", "8", "124/104", "4-6", "0.67", "2", "3", "C", "R2.19", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "NEC", "38", "8", "120", "4-6", "0.67", "2", "3", "C", "R2.20", "184");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "DD", "20", "6", "94", "3-6", "0.5", "1", "4", "C", "R2.6", "65");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 6, 4, 15, 0, 0, 4, 0, 2, 2, 2, 1, 2, 0, 8, 0, 5, 5, 4, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "DD+", "20", "6", "106", "3-6", "0.5", "1", "4", "C", "R2.6", "165");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 8, 4, 15, 0, 0, 4, 2, 2, 2, 2, 1, 2, 1, 8, 0, 5, 5, 4, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "DDL", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "R2.27", "160");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "DDG", "22", "6", "94", "3-6", "0.5", "1", "4", "C", "R2.28", "155");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "DE", "22", "4", "92", "3-6", "0.5", "3", "4", "C", "R2.14", "171");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "DEA", "22", "4", "98", "3-6", "0.5", "3", "4", "C", "R2.23", "176");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "SC", "19", "6", "120/100", "3-6", "0.5", "1", "4", "C", "R2.7", "65");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 2, 0, 15, 0, 0, 4, 0, 2, 2, 2, 1, 2, 0, 8, 0, 5, 5, 4, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Federation", "SC+", "19", "6", "150/120", "3-6", "0.5", "1", "4", "C", "R2.7", "165");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 4, 0, 15,0, 0, 4, 2, 2, 2, 2, 1, 2, 1, 8, 0, 5, 5, 4, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "MS(CL)", "30", "6", "94/80", "4-6", "0.75", "2", "3", "C", "R2.21", "158");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "NMS", "30", "8", "116/90", "4-6", "0.67", "2", "3", "C", "R2.30", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "NMS+", "30", "8", "120/94", "4-6", "0.67", "2", "3", "C", "R2.30", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "FF", "16", "6", "71", "5-6", "0.33", "1", "4", "B", "R2.25", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "FFG", "16", "6", "75", "5-6", "0.33", "1", "4", "B", "R2.26", "160");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "Tug", "22", "2", "88/60", "2-6", "*", "1", "3", "*", "R2.8", "72");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "Tug+", "22", "2", "96/68", "2-6", "*", "1", "3", "*", "R2.8", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "BT", "30", "10", "168", "2-6", "1.5", "1", "2", "E", "R2.10", "115");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "BT+", "30", "10", "184", "2-6", "1.5", "1", "2", "E", "R2.10", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CVT", "42", "6", "148/90", "2-6", "*", "2+4", "2", "E", "R2.22", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "P-CV", "20", "4", "60/30", "-", "*", "1+4", "4*", "-", "R2.22", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "P-SL", "4+30", "2", "48/20", "-", "*", "-", "4*", "-", "R2.9", "72");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "P-SL+", "4+30", "2", "56/28", "-", "*", "-", "4*", "-", "R2.9", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "BP", "8", "8", "88/45", "-", "*", "-", "4*", "-", "R2.0", "115");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "BP+", "8", "8", "96/53", "-", "*", "-", "-", "-", "R2.10", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "P-CP", "0", "0", "14/10", "-", "*", "-", "4", "-", "R2.11", "72");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "Pol", "6", "2", "40", "6", "0.33", "1", "4", "A", "R2.12", "72");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "Pol+", "6", "2", "48", "6", "0.33", "1", "4", "A", "R2.12", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "PolCVE", "12", "2", "60", "5-6", "0.5", "1+2", "4", "B", "R2.24", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "DN Scr", "30", "8", "90", "2-6", "0.5", "-", "4*", "C", "-", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "CA Scr", "20", "5", "60/20", "-", "*", "-", "4*", "-", "-", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "S-Qshp", "6", "4", "40", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Federation", "L-Qshp", "12", "8", "81", "2-6", "0.5", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);

		//  KLINGON  =============================================================================================

		ship = new Starship("Klingon", "B10", "81", "32", "316", "2-6", "2", "2+2", "2", "E", "R3.17", "-");
		se = new int[]{6, 6, 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 4, 5, 6, 9};
		da = new int[]{8, 8, 6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(5, 5, 2, 2, 21, 12, 30, 15, 15, 13, 6, 12, 12, 5, 1, 12, 10, 6, 0, 15, 36, 10, 10, 10, 10, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "C9B", "62", "24", "210", "3-6", "1.5", "2", "2", "D", "R3.2", "175");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "C9A", "62", "24", "221", "3-6", "1.5", "2", "2", "D", "R3.2", "180");
		se = new int[]{6, 6, 6, 5, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 4, 2, 2, 12, 7, 15, 15, 15, 8, 4, 8, 8, 3, 1, 4, 4, 6, 0, 12, 20, 8, 8, 8, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "C9", "62", "24", "205", "3-6", "1.5", "2", "2", "D", "R3.2", "168");
		se = new int[]{6, 6, 6, 5, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 4, 2, 2, 12, 6, 15, 15, 15, 8, 4, 8, 8, 3, 1, 4, 4, 6, 2, 12, 20, 8, 8, 8, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "C8", "60", "24", "211", "3-6", "1.5", "2", "2", "D", "R3.3", "168");
		se = new int[]{6, 6, 6, 5, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 4, 2, 2, 16, 6, 15, 15, 15, 8, 4, 8, 8, 3, 1, 4, 6, 6, 0, 12, 20, 8, 8, 8, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "C8B", "60", "24", "217", "3-6", "1.5", "2", "2", "D", "R3.3", "175");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "C8V", "66", "20", "235/200", "3-6", "1.5", "2+6", "2", "D", "R3.28", "174");
		se = new int[]{6, 6, 6, 5, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 4, 2, 2, 16, 4, 15, 15, 15, 8, 5, 8, 8, 8, 1, 31, 4, 6, 0, 12, 20, 8, 8, 8, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "DX", "46", "20", "267", "5-6", "1", "2", "2", "B", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "D7", "45", "14", "117", "5-6", "1", "1", "3", "B", "R3.4", "83");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 1, 2, 9, 4, 0, 15, 15, 5, 2, 3, 5, 3, 1, 2, 2, 4, 0, 4, 7, 6, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "D7A", "45", "14", "127", "5-6", "1", "1", "3", "B", "R3.8", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D7B", "45", "14", "125", "5-6", "1", "1", "3", "B", "R3.4", "165");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "D7C", "47", "16", "139", "5-6", "1", "2", "3", "B", "R3.31", "123");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 1, 2, 9, 4, 0, 15, 15, 5, 4, 3, 7, 5, 1, 2, 4, 4, 0, 4, 6, 6, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "D6", "44", "14", "113", "5-6", "1", "1", "3", "B", "R3.5", "62");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 1, 2, 7, 4, 0, 15, 15, 5, 2, 3, 5, 3, 1, 2, 2, 4, 0, 4, 7, 6, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "D6B", "44", "14", "124", "5-6", "1", "1", "3", "B", "R3.5", "165");
		ship.setupSSD(2, 2, 1, 2, 7, 4, 0, 15, 15, 5, 2, 3, 5, 3, 1, 2, 2, 4, 0, 4, 7, 6, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "D6CV", "45", "8", "106", "5-6", "1", "1+2", "3", "B", "R3.21", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6CVB", "45", "8", "114", "5-6", "1", "1+2", "3", "B", "R3.21", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6M", "44", "6", "125", "5-6", "1", "1", "3", "B", "R3.33", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6MB", "44", "6", "131", "5-6", "1", "1", "3", "B", "R3.33", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6D", "46", "6", "113", "5-6", "1", "1", "3", "B", "R3.32", "135");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6DB", "46", "6", "117", "5-6", "1", "1", "3", "B", "R3.32", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6PFT", "44", "8", "109", "5-6", "1", "1", "3", "B", "R3.22", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D6PFB", "44", "8", "117", "5-6", "1", "1", "3", "B", "R3.22", "184");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D5", "40", "8", "110", "5-6", "0.67", "1", "3", "B", "R3.23", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D5A", "40", "8", "118", "5-6", "0.67", "1", "3", "B", "R3.24", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "AD5", "40", "8", "120", "5-6", "0.67", "1", "3", "B", "R3.29", "176");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "F5L", "26", "12", "94", "4-6", "0.5", "1", "4", "A", "R3.34", "90");
		se = new int[]{6, 5, 3, 0};
		sc = new int[]{0, 1, 3, 9};
		da = new int[]{4, 2, 2, 0};
		ship.setupSSD(1, 2, 1, 1, 5, 2, 0, 8, 8, 3, 3, 2, 2, 1, 1, 2, 3, 2, 0, 2, 5, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "F5", "22", "8", "71", "4-6", "0.5", "-", "4", "A", "R3.6", "65");
		se = new int[]{6, 5, 3, 0};
		sc = new int[]{0, 1, 3, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 2, 1, 1, 5, 2, 0, 8, 8, 3, 1, 2, 2, 1, 1, 1, 1, 2, 0, 2, 5, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "F5B", "22", "8", "75", "4-6", "0.5", "-", "4", "A", "R3.6", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F5D", "22", "6", "90", "4-6", "0.5", "-", "4", "A", "R3.35", "79");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F5S", "20", "6", "80/60", "4-6", "0.5", "-", "4", "A", "R3.20", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F5SB", "20", "6", "85/64", "4-6", "0.5", "-", "4", "A", "R3.20", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F5M", "20", "6", "75/60", "4-6", "0.5", "-", "4", "A", "R3.27", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F5MB", "20", "6", "77/64", "4-6", "0.5", "-", "4", "A", "R3.27", "175");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "F5CVL", "24", "6", "90/70", "4-6", "0.5", "1+2", "4", "A", "R3.30", "167");
		se = new int[]{6, 5, 3, 0};
		sc = new int[]{0, 1, 3, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 2, 1, 1, 5, 2, 0, 8, 8, 3, 0, 1, 1, 2, 1, 10, 1, 2, 0, 2, 4, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Klingon", "E4", "14", "6", "55", "4-6", "0.33", "-", "4", "A", "R3.7", "65");
		se = new int[]{6, 5, 3, 0};
		sc = new int[]{0, 1, 3, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 1, 1, 1, 5, 2, 0, 8, 8, 2, 0, 1, 1, 1, 0, 1, 1, 2, 0, 2, 4, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Klingon", "E4B", "14", "6", "59", "4-6", "0.33", "-", "4", "A", "R3.7", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E4A", "14", "6", "60", "4-6", "0.33", "-", "4", "A", "R3.25", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E3", "12", "5", "42", "4-6", "0.33", "-", "4", "A", "R3.18", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E3A", "12", "5", "48", "4-6", "0.33", "-", "4", "A", "R3.26", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "G2", "10", "4", "46", "5-6", "0.33", "-", "4", "A", "R3.19", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "G1 PF", "3", "1", "20/38", "6", "0.2", "-", "5", "AA", "R3.81", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "Tug-A", "20", "7", "125/110", "3-6", "1", "1", "3", "*", "R3.9", "119");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "Tug-B", "18", "3", "106/70", "3-6", "1", "1", "3", "*", "R3.10", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "CVT", "40", "13", "158/139", "3-6", "1", "1+4", "3", "E", "R3.16", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-C1", "0", "0", "14/10", "-", "*", "-", "-", "-", "R3.11", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-P2", "3", "1", "28/15", "-", "*", "-", "-", "-", "R3.12", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-T3", "3+20", "40", "42/30", "-", "*", "-", "4*", "-", "R3.13", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-B4", "10", "6", "31", "-", "*", "1", "-", "-", "R3.14", "119");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-H5", "10", "3", "14/12", "-", "*", "0+2", "-", "-", "R3.15", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-PF6", "10", "2", "16", "-", "*", "-", "-", "-", "R3.36", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "B-Bm", "18", "8", "125", "2-6", "1", "-", "3*", "C", "-", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "C-Bm", "12", "6", "75", "2-6", "0.5", "-", "4*", "C", "-", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-Bm", "9", "4", "58/30", "-", "*", "-", "4*", "-", "-", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-Bm", "6", "3", "35/20", "-", "*", "-", "4*", "-", "-", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "S-Qshp", "5", "5", "41", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "L-Qshp", "10", "10", "83", "2-6", "0.5", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);

		//  ROMULAN  =============================================================================================

		ship = new Starship("Romulan", "Condor", "60", "20", "224*", "5-6", "1.5", "2", "2", "E", "R4.6", "170");
		se = new int[]{6, 6, 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 0, 1, 3, 6, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 0, 1, 3, 9, 3, 0, 24, 24, 5, 7, 6, 6, 4, 1, 5, 0, 6, 0, 12, 16, 8, 10, 8, 10, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "KRX", "42", "16", "275*", "5-6", "1", "1", "2", "B", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KRC", "48", "16", "170*", "5-6", "1", "1", "3", "B", "R4.32", "165");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "KR", "40", "10", "132*", "5-6", "1", "1", "3", "B", "R4.4", "160");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 7, 2, 0, 15, 15, 5, 2, 3, 5, 3, 1, 4, 0, 4, 0, 5, 8, 6, 6, 6, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "KRB", "40", "10", "154*", "5-6", "1", "1", "3", "B", "R4.35", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KRT", "20", "6", "140/128*", "3-6", "1", "1", "3", "*", "R4.33", "162");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KRM", "44", "6", "142*", "5-6", "1", "1", "3", "B", "R4.36", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KRMB", "44", "6", "146*", "5-6", "1", "1", "3", "B", "R4.36", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SupH", "45", "15", "172*", "5-6", "1", "1+2", "3", "C", "R4.34", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "FireH", "40", "12", "140*", "5-6", "1", "1", "3", "C", "R4.38", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-A", "36", "10", "125*", "5-6", "0.67", "1", "3", "B", "R4.14", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-B", "38", "8", "125/110*", "5-6", "0.67", "1+2", "3", "B", "R4.15", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-Bv", "42", "8", "131/120*", "5-6", "0.67", "1+4", "3", "B", "R4.29", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-C", "10", "10", "130/114*", "5-6", "0.67", "1", "3", "B", "R4.16", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-D", "32", "6", "110*", "5-6", "0.67", "1", "3", "B", "R4.17", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-E", "38", "6", "113*", "5-6", "0.67", "1", "3", "B", "R4.18", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-F", "32", "6", "120*", "5-6", "0.67", "1", "3", "B", "R4.19", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-G", "24+40", "5+80", "143/110*", "5-6", "0.67", "1", "3", "B", "R4.2", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SpH-H", "34", "8", "124/100*", "5-6", "0.67", "1", "3", "B", "R4.31", "168");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "WE", "20", "5", "100*", "5-6", "1", "-", "3", "D", "R4.3", "162");
		se = new int[]{6, 6, 5, 4, 2, 0};
		sc = new int[]{0, 0, 0, 3, 6, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 0, 0, 4, 1, 0, 10, 10, 6, 0, 6, 1, 0, 1, 2, 0, 2, 0, 3, 3, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "SE", "18", "4", "110/80*", "5-6", "1", "-", "3", "D", "R4.12", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "FrtE", "16", "2", "90/50*", "5-6", "1", "-", "3", "D", "R4.3", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "F-Pal", "-", "-", "10", "-", "*", "-", "-", "-", "R4.3", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "WH", "20", "5", "87/60*", "5-6", "0.5", "1+1", "4", "D", "R4.7", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CH", "21", "4", "90/55*", "5-6", "0.5", "1", "4", "D", "R4.13", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "MS", "14", "5", "72/40*", "4-6", "0.5", "1", "4", "-", "R4.8", "164");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "MA", "12", "2", "88*", "4-6", "1", "-", "3", "-", "R4.9", "164");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "WB+", "15", "5", "60*", "5-6", "*", "-", "3", "A", "R4.2", "158");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "WB", "15", "5", "45*", "5-6", "*", "-", "3", "A", "R4.2", "33");
		se = new int[]{6, 6, 5, 4, 2, 0};
		sc = new int[]{0, 0, 0, 3, 6, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 0, 0, 0, 1, 0, 0, 0, 6, 0, 6, 0, 0, 1, 2, 0, 2, 0, 3, 3, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "SkyH-A", "22", "8", "102*", "6", "0.5", "-", "4", "A", "R4.21", "168");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 8, 2, 0, 8, 8, 2, 3, 6, 2, 4, 1, 4, 0, 2, 0, 2, 4, 4, 5, 5, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "SkyH-B", "24", "6", "92*", "6", "0.5", "0+2", "4", "A", "R4.22", "168");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1,   6, 2,   0, 8, 8, 2, 3, 2,   2, 4,   1, 2, 0,   2, 5, 2, 9,   4, 5, 5, 4, se, sc, da);		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-D", "20", "6", "95*", "6", "0.5", "2", "4", "A", "R4.24", "168");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-E", "22", "8", "105*", "6", "0.5", "1", "4", "A", "R4.25", "176");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1,   8, 2,   0, 8, 8, 2, 3, 2,   2, 4,   1, 4, 4,   2, 0, 2, 4,   4, 5, 5, 4, se, sc, da);		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-F", "22", "8", "110/90*", "6", "0.5", "1", "4", "A", "R4.26", "168");
		se = new int[]{6, 6, 6, 6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1,   6, 2,   0, 8, 8, 2, 3, 2,   2, 4,   1, 2, 0,   6, 0, 2, 4,   8, 5, 5, 4, se, sc, da);		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-G", "20+20", "4+40", "107/92*", "6", "0.5", "2", "4", "A", "R4.27", "168");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1,   6, 2,   0, 8, 8, 2, 3, 2,   4, 4,   1, 4, 0,   2, 0, 4, 6,   4, 5, 5, 4, se, sc, da);		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "SkyH-H", "18", "6", "93/85*", "6", "0.5", "1", "4", "A", "R4.28", "168");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1,   6, 2,   0, 8, 8, 2, 3, 2,   2, 4,   1, 2, 0,   2, 8, 2, 4,   4, 5, 5, 4, se, sc, da);		ship = new Starship("Romulan", "SkyH-C", "24", "6", "93*", "6", "0.5", "-", "4", "A", "R4.23", "181");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Romulan", "KF5R", "20", "5", "78*", "4-6", "0.5", "-", "4", "A", "R4.5", "1060");
		se = new int[]{6, 5, 3, 0};
		sc = new int[]{0, 1, 3, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 0, 1, 1, 5, 1, 0, 8, 8, 3, 1, 2, 2, 1, 1, 2, 0, 2, 0, 3, 6, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Romulan", "KF5RB", "20", "5", "90*", "4-6", "0.5", "-", "4", "A", "R4.5", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KF5RS", "18", "4", "85/60*", "4-6", "0.5", "-", "4", "A", "R4.11", "164");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KF5RSB", "18", "4", "90/65*", "4-6", "0.5", "-", "4", "A", "R4.11", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KE4R", "13", "4", "60*", "4-6", "0.33", "-", "4", "A", "R4.1", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "KE4RB", "13", "4", "68*", "4-6", "0.33", "-", "4", "A", "R4.1", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Cent PF", "3", "1", "20/44*", "6", "0.2", "-", "5", "AA", "R4.81", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "S-Qshp", "5", "4", "40", "2-6", "0.33", "-", "4", "B", "-", "161");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "L-Qshp", "10", "8", "80", "2-6", "0.5", "-", "4", "B", "-", "161");
		defaultYard.addShipToShipyard(ship);

		//  KZINTI  =============================================================================================

		ship = new Starship("Kzinti", "SSCS", "70", "30", "245", "4-6", "1.5", "3+6", "2", "E", "R5.24", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "SCS", "65", "24", "215", "4-6", "1.5", "3+3", "2", "E", "R5.11", "181");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Kzinti", "CVA", "65", "20", "215", "4-6", "1.5", "2+6", "2", "E", "R5.25", "173");
		se = new int[]{6, 6, 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 0, 1, 2, 3, 4, 5, 9};
		da = new int[]{6, 6, 4, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 3, 2, 3, 20, 4, 15, 15, 15, 7, 4, 7, 7, 8, 1, 28, 8, 9, 0, 7, 18, 9, 10, 10, 12, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Kzinti", "CV", "50", "20", "147", "5-6", "1", "3+3", "3", "E", "R5.6", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVS", "50", "20", "169", "5-6", "1", "3+3", "3", "E", "R5.7", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVL", "40", "15", "117", "5-6", "1", "2+2", "3", "C", "R5.9", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVE", "30", "10", "89", "5-6", "0.67", "1+2", "3", "B", "R5.10", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CSX", "41", "18", "264", "5-6", "1", "3", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Kzinti", "CC", "44", "20", "150", "5-6", "1", "2", "3", "C", "R5.4", "107");
		se = new int[]{6, 6, 6, 4, 1, 0};
		sc = new int[]{0, 1, 2, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 0, 1, 3, 12, 4, 10, 10, 10, 3, 5, 5, 5, 2, 1, 2, 4, 6, 0, 5, 12, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Kzinti", "BC", "40", "16", "138", "5-6", "1", "2", "3", "C", "R5.3", "160");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CS", "40", "16", "116", "5-6", "1", "2", "3", "C", "R5.2", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CM", "33", "12", "110", "5-6", "0.67", "1", "3", "B", "R5.19", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CL", "30", "10", "84", "5-6", "0.67", "1", "3", "B", "R5.5", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "FF", "20", "6", "62", "5-6", "0.33", "1", "4", "A", "R5.8", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "AFF", "20", "6", "70", "5-6", "0.33", "1", "4", "A", "R5.20", "177");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "DF", "21", "4", "74", "5-6", "0.33", "1", "4", "A", "R5.23", "104");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "SF", "18", "4", "90/55", "5-6", "0.33", "1", "4", "A", "R5.18", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "PFT", "30", "8", "75/65", "5-6", "0.5", "1", "3", "B", "R5.22", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MS", "18", "2", "70/45", "5-6", "0.33", "1", "4", "A", "R5.21", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Tug", "28", "8", "114/90", "4-6", "1", "2", "3", "*", "R5.12", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-C1", "0", "0", "14/10", "-", "*", "-", "-", "-", "R5.13", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-H2", "11", "4", "19/12", "-", "*", "0+2", "-", "-", "R5.14", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-B3", "12", "8", "37", "-", "*", "-", "-", "-", "R5.15", "121");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-SD4", "6", "6", "24/20", "-", "*", "-", "-", "-", "R5.16", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-T5", "3+20", "40", "31/20", "-", "*", "-", "4*", "-", "R5.17", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-PF6", "12", "4", "20/12", "-", "*", "-", "-", "-", "R5.26", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Needle", "3", "1", "20/37", "6", "0.2", "-", "5", "AA", "R5.81", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Fi-Con", "3", "1", "30", "6", "0.2", "-", "5", "AA", "R5.83", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-ABJ", "3", "1", "30/37", "6", "0.2", "-", "5", "AA", "R5.82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-C-H", "3", "1", "30", "6", "0.2", "-", "5", "AA", "R5.82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-E", "3", "1", "100/50", "6", "0.2", "-", "5", "AA", "R5.82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "S-Qshp", "6", "6", "30", "2-6", "0.33", "-", "4", "-", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "L-Qshp", "12", "12", "62", "2-6", "0.5", "-", "4", "-", "-", "74");
		defaultYard.addShipToShipyard(ship);

		//  GORN  =============================================================================================

		ship = new Starship("Gorn", "DN", "62", "24", "220", "4-6", "1.5", "4", "2", "E", "R6.11", "171");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DN+", "64", "26", "230", "4-6", "1.5", "4", "2", "E", "R6.11", "173");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DNF", "66", "30", "244", "4-6", "1.5", "4", "2", "E", "R6.11", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CX", "48", "18", "260", "5-6", "1", "3", "2", "D", "S2", "-");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Gorn", "CC", "50", "20", "175", "5-6", "1", "3", "3", "D", "R6.18", "175");
		se = new int[]{6, 6, 5, 3, 2, 0};
		sc = new int[]{0, 0, 1, 2, 4, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 2, 2, 10, 4, 0, 16, 16, 4, 2, 4, 3, 2, 1, 6, 0, 4, 0, 4, 12, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Gorn", "BC", "48", "16", "171", "5-6", "1", "3", "3", "D", "R6.19", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CA", "46", "16", "141", "5-6", "1", "3", "3", "D", "R6.2", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CA+", "46", "16", "157", "5-6", "1", "3", "3", "D", "R6.2", "170");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Gorn", "CV", "36", "8", "120", "4-6", "0.67", "2+4", "3", "D", "R6.16", "175");
		se = new int[]{6, 6, 5, 3, 2, 0};
		sc = new int[]{0, 0, 1, 2, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 3, 4, 2, 0, 12, 12, 4, 3, 2, 2, 5, 1, 14, 0, 2, 0, 4, 6, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Gorn", "CL", "32", "8", "107", "4-6", "0.67", "2", "3", "D", "R6.3", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CL+", "32", "8", "123", "4-6", "0.67", "2", "3", "D", "R6.3", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CLF", "32", "8", "137", "4-6", "0.67", "2", "3", "D", "R6.3", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "LSC", "30", "8", "100/80", "4-6", "0.67", "2", "3", "D", "R6.10", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "HDD", "32", "12", "105", "5-6", "0.67", "1", "3", "C", "R6.12", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "BDD", "24", "8", "85", "5-6", "0.5", "1", "4", "B", "N6", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DD", "20", "6", "68", "4-6", "0.5", "1", "4", "C", "R6.4", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DD+", "20", "6", "76", "4-6", "0.5", "1", "4", "C", "R6.4", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DDF", "20", "6", "90", "4-6", "0.5", "1", "4", "C", "R6.4", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "SC", "20", "6", "80/55", "4-6", "0.5", "1", "4", "C", "R6.13", "75");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "SCF", "20", "6", "84/70", "4-6", "0.5", "1", "4", "C", "R6.13", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "PFT", "20", "4", "70/55", "4-6", "0.5", "1", "4", "C", "R6.14", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "PFTF", "20", "4", "84/70", "4-6", "0.5", "1", "4", "C", "R6.14", "183");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "MS", "20", "4", "70/55", "4-6", "0.5", "1", "4", "C", "R6.15", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "MSF", "20", "4", "84/70", "4-6", "0.5", "1", "4", "C", "R6.15", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "Tug", "23", "4", "96/44", "2-6", "*", "2", "3", "*", "R6.5", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "TugF", "23", "4", "110/58", "2-6", "*", "2", "3", "*", "R6.5", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-C", "0", "0", "15/10", "-", "*", "-", "-", "-", "R6.6", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-T", "2+40", "80", "60/30", "-", "*", "2", "4*", "-", "R6.7", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-SL", "2+40", "6", "40/20", "-", "*", "1", "4*", "-", "R6.9", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-M", "17", "6", "45/96", "-", "*", "-", "-", "-", "R6.8", "121");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-M+", "20", "8", "60/120", "-", "*", "-", "-", "-", "R6.8", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "Pter PF", "3", "1", "20/40", "6", "0.2", "-", "5", "AA", "R6.81", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "S-Qshp", "6", "5", "35", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "L-Qshp", "12", "10", "80", "2-6", "0.5", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);

		//  THOLIAN  =============================================================================================

		ship = new Starship("Tholian", "D", "45", "14", "175", "4-6", "1", "2", "2", "C", "R7.5", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "CX", "36", "10", "215", "5-6", "0.75", "1", "2", "B", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "C", "34", "10", "120", "4-6", "0.75", "1", "3", "B", "R7.6", "147");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Tholian", "CVA", "40", "8", "141", "4-6", "0.75", "1+4", "3", "B", "R7.9", "173");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 0, 1, 2, 10, 0, 0, 12, 12, 4, 3, 3, 2, 6, 1, 4, 0, 4, 0, 7, 7, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Tholian", "DD", "18", "8", "80", "5-6", "0.5", "-", "4", "A", "R7.4", "115");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(4, 0, 1, 1, 6, 2, 0, 8, 8, 3, 0, 2, 1, 2, 1, 2, 0, 2, 0, 5, 0, 4, 5, 5, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Tholian", "PC+", "14", "6", "65", "5-6", "0.5", "1", "4", "A", "R7.3", "98");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "PC", "12", "5", "59", "5-6", "0.5", "1", "4", "A", "R7.2", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "SC", "12", "4", "90/50", "5-6", "0.5", "1", "4", "A", "R7.12", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "MS", "12", "4", "60/50", "5-6", "0.5", "1", "4", "A", "R7.13", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "CPC", "12", "5", "55/50", "5-6", "0.5", "1", "4", "A", "R7.11", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "C-Pk", "0", "0", "6", "-", "*", "-", "-", "-", "R7.11", "85");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "BW", "20", "6", "65", "5-6", "0.5", "1+2", "4", "A", "R7.7", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "PFT", "20", "6", "70", "5-6", "0.5", "1", "4", "A", "R7.8", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "WT", "10", "5", "70/30", "3-6", "0.33", "1", "4", "B", "R7.10", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "Arac PF", "3", "1", "20/38", "6", "0.2", "-", "5", "AA", "R7.81", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "S-Qshp", "5", "4", "41", "2-6", "0.33", "-", "4", "B", "-", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "L-Qshp", "10", "8", "83", "2-6", "0.5", "-", "4", "B", "-", "150");
		defaultYard.addShipToShipyard(ship);

		//  ORION  =============================================================================================

		ship = new Starship("Orion", "CA", "30", "16", "127", "5-6", "1", "2", "3", "B", "R8.3", "132");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "CRX", "22", "14", "180", "6", "0.067", "2", "2", "A", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "CR", "20", "12", "86", "6", "0.067", "2", "3", "A", "R8.2", "117");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "Sal", "36", "20", "112/90", "4-6", "0.067", "2", "3", "C", "R8.4", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "CVL", "38", "18", "130/90", "4-6", "0.067", "2+2", "3", "C", "R8.6", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "PFT", "36", "18", "130/90", "3-6", "0.067", "2", "3", "C", "R8.9", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "Slv", "12", "8", "83/60", "3-6", "0.25", "1", "4", "D", "R8.5", "117");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "LR", "12", "8", "68", "6", "0.33", "1", "4", "AA", "R8.7", "117");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "DR", "12", "8", "68", "6", "0.33", "1", "4", "AA", "R8.8", "117");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "Buncr", "3", "1", "20/40", "6", "0.2", "-", "5", "A", "R8.81", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "BS", "40", "10", "200/75", "-", "*", "2", "3", "-", "-", "117");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Orion", "BATS", "80", "30", "500/125", "-", "*", "4", "2", "-", "-", "165");
		defaultYard.addShipToShipyard(ship);


		//  HYDRAN  =============================================================================================

		ship = new Starship("Hydran", "DN", "54", "18", "170", "4-6", "1.5", "3+4", "2", "D", "R9.4", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "XR", "37", "14", "220", "5-6", "1", "2+3", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "XD", "38", "14", "250", "5-6", "1", "2+1", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Hydran", "CVA", "40", "10", "140/105", "5-6", "1", "2+6", "3", "C", "R9.15", "171");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 0};
		ship.setupSSD(2, 0, 2, 2, 8, 0, 10, 10, 10, 5, 3, 2, 2, 4, 1, 25, 0, 2, 0, 12, 9, 5, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Hydran", "CC(LM)", "40", "18", "138", "5-6", "1", "2+3", "3", "C", "R9.19", "160");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 0};
		ship.setupSSD(2, 3, 2, 2, 6, 4, 10, 10, 10, 4, 6, 4, 2, 4, 1, 9, 2, 4, 0, 12, 9, 5, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Hydran", "CA(R)", "35", "12", "93", "5-6", "1", "2+3", "3", "C", "R9.2", "134");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "CA(D)", "36", "14", "130", "5-6", "1", "2+1", "3", "C", "R9.8", "158");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "CL(H)", "30", "10", "83", "5-6", "0.67", "1+2", "3", "B", "R9.10", "171");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "CL(T)", "31", "12", "112", "5-6", "0.67", "1", "3", "B", "R9.11", "171");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "CV(U)", "26", "6", "108/85", "6", "0.5", "2+4", "4", "B", "R9.17", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "DD(L)", "22", "8", "67", "6", "0.5", "1+2", "4", "B", "R9.3", "134");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "DD(K)", "23", "10", "90", "6", "0.5", "1", "4", "B", "R9.9", "158");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "DE(AL)", "22", "8", "90", "6", "0.5", "1+2", "4", "B", "R9.16", "177");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "MS", "20", "4", "75/65", "6", "0.5", "2", "4", "B", "R9.14", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "PFT", "24", "6", "78/40", "3-6", "0.67", "1", "3", "D", "R9.12", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Sc", "12", "4", "60/26", "6", "0.33", "1", "4", "A", "R9.5", "134");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Hnt", "10", "6", "48", "6", "0.33", "1", "4", "A", "R9.6", "134");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "A-Hnt", "10", "4", "54", "6", "0.33", "1", "4", "A", "R9.13", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Cuirs", "10", "6", "65", "6", "0.33", "1", "4", "A", "R9.7", "158");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Tug", "22", "6", "110/70", "4-6", "1", "2", "3", "C", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-Cargo", "-", "-", "14/10", "-", "*", "-", "-", "-", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-FS", "10", "6", "50/90", "-", "*", "-", "-", "-", "N6", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-TT", "4+40", "80", "30/20", "-", "*", "1", "-", "-", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-SD", "10", "4", "25", "-", "*", "-", "-", "-", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-Com", "10", "6", "40", "-", "*", "-", "-", "-", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-Fconv", "2", "-", "15/10", "-", "*", "-", "-", "-", "N6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-CV", "10", "4", "22", "-", "*", "-", "-", "-", "R9.27", "155");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Pal-PFT", "12", "4", "24", "-", "*", "-", "-", "-", "R9.28", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Harrier", "3", "1", "20/37", "6", "0.2", "-", "5", "AA", "R9.81", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "Hellion", "3", "1", "24/42", "6", "0.2", "-", "5", "AA", "R9.82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "S-Qshp", "5", "5", "25", "2-6", "0.33", "-", "4", "D", "-", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Hydran", "L-Qshp", "10", "10", "55", "2-6", "0.5", "-", "4", "D", "-", "140");
		defaultYard.addShipToShipyard(ship);

		//  LYRAN  =============================================================================================

		ship = new Starship("Lyran", "DN", "62", "22", "203", "3-6", "1.5", "2", "2", "D", "R11.2", "170");
		se = new int[]{6, 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 4, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(4, 3, 2, 3, 14, 6, 15, 15, 15, 6, 4, 6, 6, 6, 2, 4, 4, 6, 0, 16, 12, 8, 9, 9, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "BC", "52", "20", "142", "4-6", "1", "2", "3", "C", "R11.3", "170");
		se = new int[]{ 6, 6, 5, 4, 3, 2, 1, 0};
		sc = new int[]{ 0, 0, 1, 2, 3, 4, 5, 9};
		da = new int[]{ 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 2, 1, 1, 12, 4, 10, 10, 10, 4, 2, 2, 4, 4, 1, 4, 4, 4, 0, 12, 10, 7, 8, 8, 7, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "CA", "42", "12", "133", "5-6", "1", "1", "3", "C", "R11.4", "68");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 1, 1, 3, 10, 4, 0, 15, 15, 3, 4, 4, 4, 4, 1, 2, 2, 4, 0, 8, 8, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "CX", "45", "12", "272", "5-6", "1", "1", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Lyran", "CV", "44", "10", "135", "5-6", "1", "2+4", "3", "C", "R11.12", "172");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 1, 1, 3, 10, 4, 0, 15, 15, 3, 4, 4, 4, 2, 1, 16, 2, 4, 0, 4, 4, 6, 6, 6, 6, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "CW", "34", "10", "115", "5-6", "0.67", "1", "3", "B", "N5", "165");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "CL", "34", "9", "92", "5-6", "0.67", "1", "3", "C", "R11.5", "68");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 8, 2, 0, 10, 10, 3, 2, 2, 2, 2, 1, 2, 2, 2, 0, 6, 6, 5, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "DD", "26", "6", "79", "6", "0.5", "1", "4", "B", "R11.6", "68");
		se = new int[]{6, 6, 4, 2, 0};
		sc = new int[]{0, 0, 1, 5, 9};
		da = new int[]{4, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 8, 2, 0, 8, 8, 2, 2, 2, 2, 2, 1, 2, 1, 2, 0, 5, 5, 4, 5, 5, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "SC", "25", "6", "100/60", "6", "0.5", "1", "4", "B", "R11.9", "88");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "MS", "22", "4", "80/60", "6", "0.5", "1", "4", "B", "R11.8", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "PFT", "28", "4", "80/50", "6", "0.5", "1", "4", "B", "R11.10", "178");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Lyran", "FF", "18", "4", "63", "6", "0.33", "1", "4", "A", "R11.7", "68");
		se = new int[]{6, 4, 2, 0};
		sc = new int[]{0, 1, 5, 9};
		da = new int[]{2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 6, 2, 0, 6, 6, 2, 1, 1, 1, 2, 1, 2, 1, 1, 0, 4, 4, 3, 4, 4, 3, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Lyran", "AF", "18", "4", "70", "6", "0.33", "1", "4", "A", "R11.11", "177");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Tug-P", "34", "6", "121/102", "3-6", "1", "-", "3", "D", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Tug-C", "40", "10", "138/124", "3-6", "1", "1", "3", "D", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Pal-Bt", "20", "12", "50/60", "-", "*", "-", "-", "-", "N6", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Pal-C", "-", "-", "28/20", "-", "*", "-", "-", "-", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Pal-TT", "4+40", "80", "50/30", "-", "*", "-", "4*", "-", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Pal-PFT", "20", "4", "40/30", "-", "*", "-", "-", "-", "N6", "178");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "PFT", "3", "1", "20/37", "6", "0.2", "-", "5", "AA", "R11.81", "178");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "Fi-Con", "3", "1", "20/25", "6", "0.2", "-", "5", "AA", "R11.82", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "S-Qshp", "5", "5", "41", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Lyran", "L-Qshp", "10", "10", "83", "2-6", "0.5", "-", "4", "B", "-", "74");

		//  WYN =============================================================================================

		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "Orn CR", "19", "10", "86", "6", "0.67", "2", "3", "A", "R12.2", "160");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "Ltr DD", "24", "4", "93", "6", "0.5", "1", "4", "B", "R12.3", "136");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "Kz FF", "22", "4", "90", "5-6", "0.33", "2", "4", "A", "R12.4", "136");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "Kl G2", "10", "4", "54", "5-6", "0.33", "-", "4", "A", "R12.5", "136");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "WYN AxC", "8", "4", "65", "3-6", "0.33", "-", "4", "C", "R12.6", "140");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("WYN", "WYN AxCV", "8", "2", "75/50", "3-6", "0.33", "0+2", "4", "C", "R12.7", "170");
		defaultYard.addShipToShipyard(ship);

		//  ANDROMEDAN  =============================================================================================

		ship = new Starship("Andromedan", "Dom", "38", "20", "450", "5-6", "1.5", "-", "2", "D", "R13.2", "184");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Andromedan", "Intruder", "24", "10", "250", "6", "1", "-", "3", "C", "R13.3", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Andromedan", "Cobra", "14", "8", "90", "6", "0.5", "-", "4", "A", "R13.4", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Andromedan", "Term", "10", "4", "110", "6", "0.5", "-", "4", "A", "R13.6", "184");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Andromedan", "Cour", "10", "4", "70", "6", "0.33", "-", "4", "A", "R13.5", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Andromedan", "E-Mod", "0", "0", "40", "-", "*", "-", "4", "-", "R13.7", "184");
		defaultYard.addShipToShipyard(ship);
		
		//  FRAX  =============================================================================================
		
		ship = new Starship("Frax", "BB", "65", "26", "350", "3-6", "2", "2+2", "2", "E", "R91.08", "184");
		se = new int[]{6, 6, 6, 6, 5, 4, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{8, 8, 6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 2, 1, 2, 19, 9, 30, 15, 15, 6, 12, 12, 10, 4, 1, 12, 10, 10, 0, 17, 16, 10, 9, 8, 12, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Frax", "DN", "55", "20", "210", "4-6", "105", "2", "2", "D", "R91.02", "167");
		se = new int[]{6, 6, 6, 5, 4, 3, 1, 0};
		sc = new int[]{0, 0, 0, 1, 2, 3, 5, 9};
		da = new int[]{6, 6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(3, 2, 1, 2, 17, 6, 15, 15, 15, 6, 6, 8, 6, 4, 1, 4, 4, 8, 0, 15, 15, 8, 8, 8, 8, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "CC", "45", "14", "153", "5-6", "1", "2", "3", "C", "R91.09", "143");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 1, 1, 2, 8, 4, 0, 15, 15, 6, 3, 5, 4, 2, 1, 4, 4, 4, 0, 7, 7, 6, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "CA", "45", "14", "140", "5-6", "1", "2", "3", "C", "R91.03", "122");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 8, 4, 0, 12, 12, 4, 2, 3, 2, 2, 1, 4, 2, 3, 0, 5, 5, 5, 5, 5, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "CV", "45", "14", "140/100", "5-6", "1", "2+4", "3", "C", "R91.18", "167");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 6, 0, 0, 15, 15, 4, 3, 4, 3, 4, 1, 28, 4, 2, 0, 5, 5, 6, 6, 6, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "CW", "40", "10", "120", "5-6", "0.67", "2", "3", "B", "R91.04", "168");
		se = new int[]{6, 5, 3, 1, 0};
		sc = new int[]{0, 1, 3, 5, 9};
		da = new int[]{4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 2, 8, 4, 0, 12, 12, 4, 2, 3, 2, 2, 1, 4, 2, 3, 0, 5, 5, 5, 5, 5, 5, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "CWV", "44", "10", "120/100", "5-6", "0.67", "2+4", "3", "B", "R91.05", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "PFT", "40", "10", "120/80", "5-6", "0.67", "2", "3", "B", "R91.06", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "CWE", "40", "10", "140", "5-6", "0.67", "2", "3", "B", "R91.07", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "CWA", "40", "10", "152", "5-6", "0.67", "2", "3", "B", "R91.07A", "175");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "DW", "28", "8", "90", "5-6", "0.5", "1", "4", "B", "R91.10", "135");
		se = new int[]{6, 5, 1, 0};
		sc = new int[]{0, 1, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 0, 1, 1, 7, 3, 0, 9, 9, 2, 2, 2, 2, 2, 1, 2, 2, 2, 0, 4, 4, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "DWD", "28", "8", "93", "5-6", "0.5", "1", "4", "B", "R91.11", "137");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "DWS", "28", "8", "100/80", "5-6", "0.5", "1", "4", "B", "R91.12", "138");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "DWM", "28", "8", "100/80", "5-6", "0.5", "1", "4", "B", "R91.13", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "DWE", "28", "8", "97", "5-6", "0.5", "1", "4", "B", "R91.14", "167");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "DWA", "28", "8", "102", "5-6", "0.5", "1", "4", "B", "R91.15", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "DWC", "32", "28", "109/79", "5-6", "0.5", "1", "4", "B", "R91.17", "155");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "MDW", "28", "8", "93", "5-6", "0.5", "1", "4", "B", "R91.16", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "Tug", "45", "10", "140/80", "3-6", "*", "2", "3", "*", "R91.19", "124");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "FF", "20", "6", "75", "5-6", "0.33", "1", "4", "A", "R91.20", "121");
		se = new int[]{6, 5, 1, 0};
		sc = new int[]{0, 1, 5, 9};
		da = new int[]{2, 2, 2, 0};
		ship.setupSSD(1, 0, 1, 1, 5, 2, 0, 6, 6, 2, 2, 2, 2, 1, 1, 2, 2, 2, 0, 4, 3, 4, 4, 4, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Frax", "SFF", "10", "4", "60", "6", "0.33", "-", "4", "A", "R91.21", "121");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "SDD", "14", "6", "80", "5-6", "0.5", "-", "4", "B", "R91.22", "135");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "SCW", "19", "10", "110", "5-6", "0.67", "1", "3", "B", "R91.23", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "SCL", "19", "10", "110", "5-6", "0.67", "1", "3", "B", "R91.25", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "SCG", "28", "12", "150", "5-6", "1", "1", "3", "C", "R91.24", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "PF", "3", "1", "20/30", "6", "0.2", "-", "5", "AA", "R91.PF1", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Frax", "PFD", "3", "1", "20/30", "6", "0.2", "-", "5", "AA", "R91.PF2", "179");
		defaultYard.addShipToShipyard(ship);

		
		//  CIVILIAN  =============================================================================================

		ship = new Starship("Civilian", "F-L", "2", "-", "61/18", "1-6", "0.5", "-", "4", "B", "R1.6", "74");
		se = new int[]{6, 3, 0};
		sc = new int[]{0, 2, 9};
		da = new int[]{2, 2, 0};
		ship.setupSSD(1, 0, 1, 1, 2, 0, 0, 4, 4, 2, 1, 1, 1, 0, 0, 1, 0, 1, 50, 3, 3, 3, 3, 3, 4, se, sc, da);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Civilian", "F-S", "1", "-", "26/12", "1-6", "0.33", "-", "4", "B", "R1.5", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Civilian", "Armd Pr", "4", "2", "75/20", "3-6", "0.2", "-", "4", "C", "R1.8", "80");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Civilian", "Fed Ex", "3", "1", "70/28", "3-6", "0.1", "-", "4", "AA", "R1.11", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Civilian", "Free Trd", "3", "2", "70/22", "4-6", "0.5", "1", "4", "C", "R1.9", "80");
		defaultYard.addShipToShipyard(ship);

		//  ALL FLEETS  =============================================================================================

		ship = new Starship("All Fleets", "StrBase", "250", "50", "600", "-", "*", "6", "1", "-", "R1.1", "75");
		se = new int[]{6, 6, 6, 6, 5, 5, 4, 4, 3, 2, 1, 0};
		sc = new int[]{0, 0, 0, 0, 1, 1, 2, 3, 3, 5, 7, 9};
		da = new int[]{12, 12, 12, 10, 10, 10, 8, 8, 8, 6, 6, 4, 4, 2, 2, 0};
		ship.setupSSD(6, 4, 2, 10, 12, 12, 12, 12, 12, 0, 40, 44, 22, 12, 4, 12, 0, 20, 56, 38, 38, 16, 12, 12, 16, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("All Fleets", "BatlStn", "100", "24", "200", "-", "*", "4", "2", "-", "R1.2", "160");
		se = new int[]{6, 6, 5, 3, 1, 0};
		sc = new int[]{0, 0, 1, 3, 5, 9};
		da = new int[]{6, 4, 4, 2, 2, 2, 0};
		ship.setupSSD(2, 0, 1, 1, 12, 2, 9, 0, 0, 0, 24, 6, 4, 8, 0, 16, 0, 4, 16, 10, 9, 7, 6, 6, 7, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("All Fleets", "BS", "60", "12", "120", "-", "*", "2", "3", "-", "R1.3", "75");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "FBay", "7", "-", "10", "-", "*", "0+2", "-", "-", "R1.4", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "PFBay", "8", "-", "12", "-", "*", "-", "-", "-", "R1.17", "180");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("All Fleets", "FRD", "80", "12", "200/50", "-", "*", "2", "2", "-", "R1.10", "75");
		se = new int[]{6, 0};
		sc = new int[]{0, 9};
		da = new int[]{6, 6, 6, 6, 4, 4, 4, 2, 0};
		ship.setupSSD(2, 2, 0, 2, 6, 0, 0, 0, 0, 2, 36, 2, 2, 16, 0, 2, 0, 0, 80, 10, 10, 9, 2, 2, 4, se, sc, da);
//		ship.setupSSD(B, F, E, A, P, T, C, L, R, I, A, B, TS,TC,P, S, D, L, C, FH,AF,DA,SE,SC,EX,sensNums, scanNums, damConNums);
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("All Fleets", "Cargo PF", "3", "1", "20", "6", "0.2", "-", "5", "AA", "R1.82", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "Scout PF", "3", "1", "100/50", "6", "0.2", "-", "5", "AA", "R1.81", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "S-AxCV", "10", "2", "75/50", "3-6", "0.33", "0+2", "4", "B", "R1.13", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "L-AxCV", "20", "4", "120/80", "3-6", "0.67", "2+4", "3", "B", "R1.13", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "DefSat", "0", "0", "20", "-", "*", "-", "7", "-", "R1.12", "75");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("All Fleets", "GBDP", "0", "0", "7", "-", "*", "-", "-", "-", "R1.14", "75");
		defaultYard.addShipToShipyard(ship);

		//  MONSTERS  =============================================================================================

		ship = new Starship("Monster", "Planet Crusher", "-", "-", "-", "-", "-", "-", "-", "0", "SM1.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Space Amoeba", "-", "-", "-", "-", "-", "-", "-", "-", "SM2.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Moray Eel", "-", "-", "-", "-", "-", "-", "-", "-", "SM3.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 399, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Cosmic Cloud", "-", "-", "-", "-", "-", "-", "-", "-", "SM4.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Sunsnake", "-", "-", "-", "-", "-", "-", "-", "-", "SM5.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Mind Monster", "-", "-", "-", "-", "-", "-", "-", "-", "SM6.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 1x", "-", "-", "-", "-", "-", "-", "1", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 1x", "-", "-", "-", "-", "-", "-", "1", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 1x", "-", "-", "-", "-", "-", "-", "1", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 1x", "-", "-", "-", "-", "-", "-", "1", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 2x", "-", "-", "-", "-", "-", "-", "2", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 4x", "-", "-", "-", "-", "-", "-", "3", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 399, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Arastoz 8x", "-", "-", "-", "-", "-", "-", "4", "-", "SL49.0", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 799, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Monster", "Space Dragon Young", "-", "-", "-", "-", "-", "-", "-", "-", "SM7.0", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Monster", "Space Dragon Adult", "-", "-", "-", "-", "-", "-", "-", "-", "SM7.0", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Monster", "Space Dragon Old", "-", "-", "-", "-", "-", "-", "-", "-", "SM7.0", "-");
		defaultYard.addShipToShipyard(ship);
		
		ship = new Starship("Monster", "Space Dragon Ancient", "-", "-", "-", "-", "-", "-", "-", "-", "SM7.0", "-");
		defaultYard.addShipToShipyard(ship);
		
		//  OTHER  =============================================================================================

		ship = new Starship("Other", "Planet", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Other", "Comet", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-");
		se = new int[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
		sc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		da = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ship.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 199, se, sc, da);
		defaultYard.addShipToShipyard(ship);

		
	    return  defaultYard;
	}
	
	public void displayShipyardMenu(int pass) {

		boolean isMonster = true;
		
		boolean cont = true; 
		boolean cont2 = true;
		
		while (cont) {
			String userRace = "";
			
			System.out.println();
			System.out.println();
			System.out.println("|===========================================================================================================|");
			System.out.println("|                                              THE SHIPYARD                                                 |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------|");
			if (showOnlySSDs == true) {
				System.out.println("|                                       Show ONLY ships with SSDs                                           |");
			} else {
				System.out.println("|                                 Show ALL ships (with AND without SSDs)                                    |");
			}
			System.out.println("|===========================================================================================================|");
			System.out.println("|   Races Available in the Shipyard:                                                   [*] Show SSD Toggle  |");
			System.out.println("|       [F]ederation       [L]yran            Fra[X]              [ ]                  [+] Show Refit Text  |");
			System.out.println("|       [K]lingon          [G]orn             [W]YN               [ ]                                       |");
			System.out.println("|       [R]omulan          [T]holian          [I]SC               [ ]                  [M]onsters           |");
			System.out.println("|       K[Z]inti           [H]ydran           [S]eltorian         [C]ivilian Ships     Oth[E]r              |");
			System.out.println("|       [O]rion            [A]ndromedan       [ ]                 [Q] All Fleets                            |");
			System.out.println("|===========================================================================================================|");

			if (pass == -1) {
				System.out.println("|   Display ships from what race?                                    RETURN to go back to Main Menu         |");
			}
			if (pass == 1) {
				System.out.println("|   Display ships from what race?                             RETURN to go back to Modify Ships Menu        |");
			}
			System.out.println("|===========================================================================================================|");
	
			userRace = Driver.getInput("FKRZOGTHAWLSXIMCQ*+");
			
			if (userRace.contentEquals("*")) {
				showOnlySSDs = !showOnlySSDs;
			} else if (userRace.contentEquals("+")) {
				showRefitText = !showRefitText;
			} else {
				if (userRace.contentEquals("")) {
					cont = false;
				} else {
					System.out.println();
			
					String race = getRace(userRace);
					
					int firstOfRace = this.printShipyardBasedOnRace(race); // fed == 0
					// Not the best way of doing this, fix later
	
					int count = this.numberOfShipsWithRace(race);

					if (showRefitText) {
						DisplayRaceRefits(userRace);
					}
					
					int whichShip = 0;
					cont2 = true;
					while (cont2) {
						if (userRace.contentEquals("M")) {
							System.out.print("Which monster to add to the game?  [RETURN to cancel]");
						} else {
							System.out.print("Which ship to add to the game?  [RETURN to cancel]");
						}
				
						whichShip = Driver.getNumber(1, count);
						if (whichShip == -1) {
							cont2 = false;
						} else {
							int adjusted = whichShip + firstOfRace - 1;
							
							if (list[adjusted].hasSSD == false) {
								System.out.print("You are about to add a ship to the game WIHTOUT an assigned SSD.  Proceed?");
								String yesOrNo = Driver.getInput("YN");
								if (yesOrNo.equalsIgnoreCase("N")) {
									cont2 = false;
									System.out.println();
								}
							}
							
							if (cont2 == true) {
								if (userRace.contentEquals("M")) {
									Starship copiedShip = list[adjusted];
									copiedShip.name = list[adjusted].shipType;
									copiedShip.turnMode = list[adjusted].turnMode;
									if (whichShip == 1 || whichShip == 3 || whichShip == 6) {  // Planet Crusher, Moray Eel of Space, Mind Monster
										copiedShip.speed = 6;
									} else if (whichShip == 2 || whichShip == 4) {  // Space Amoeba, Cosmic Cloud
										copiedShip.speed = 4;
									} else if (whichShip == 5) {  // Sunsnake
										copiedShip.speed = 3;
									} else if (whichShip == 7) {  // Arastoz 1 piece (single)
										copiedShip.speed = 14;
									} else if (whichShip == 8) {  // Arastoz 2 pieces joined
										copiedShip.speed = 12;
									} else if (whichShip == 9) {  // Arastoz 3 pieces joined
										copiedShip.speed = 10;
									} else if (whichShip == 10) { // Arastoz 4 pieces joined
										copiedShip.speed = 8;
									}
									System.out.println("Monster Speed is: " + copiedShip.speed);
									System.out.println();
									Driver.currentGameYard.addShipToShipyard(copiedShip);
								} else {
									Starship copiedShip = list[adjusted];
									copiedShip.name = (list[adjusted].race).substring(0,3) + "-" + (list[adjusted].shipType);
									copiedShip.turnMode = list[adjusted].turnMode;
									
//									System.out.print("Ship Speed: ");
//									int shipSpeed = Driver.getNumberNoCancel(0, 32);
//									System.out.println();
//									
//									copiedShip.speed = shipSpeed;
									Driver.currentGameYard.addShipToShipyard(copiedShip);
								}
							}
							cont2 = true;
						}
					}
				}
			}
		}
	}
	
	public static void DisplayRaceRefits(String race) {
		if (race.contentEquals("F")) {
			System.out.println("                                            \"+\" REFITS:   (R2.70)");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("CA/DD/SC: +1 Type-G drone rack, +2 APR boxes, +2 Phaser-III (360°)                          <--standard refit");
			System.out.println("CC/CL*:   +1 Type-G drone rack, +2 Phaser-III (360°)                (* Also Tug/Pol/Battle Pod/Starliner Pod)");
			System.out.println("CV/GS:    +1 Type-G drone rack");
			System.out.println("NCL:      +2 Phaser-III (360 degree)");
			System.out.println("DN:       +2 photon (FA), +2 APR, +1 tractor, +2 transporter, +1 ADD, +4 F Hull, +2 shuttles");
			System.out.println("FFG:      +1 APR, +1 Type-G drone rack");
		} else if (race.contentEquals("K")) {
			System.out.println("                                              \"B\" REFITS:   (R3.70)");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("F5/E4:    +1 ADD, +ability to launch 1 drone per rack per turn, shield 3-4-5 inc. to 6-1-2  <--standard refit");
			System.out.println("C8/C9/D7: Standard refit, +DERFACS");
			System.out.println("D6:       Standard refit, +DERFACS, disruptor range inc. to 30 ");
			System.out.println();
			System.out.println("All variants (e.g. D6PFT, F5M) received the same refit as the standard type unless otherwise noted  ");
		} else if (race.contentEquals("R")) {
			System.out.println("                                              \"B\" REFITS:   (R4.70)");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("Convert fixed Type-G plasma torpedoes to sviwel (LP/RP) Type-S plasma torpedoes");
			System.out.println("Strengthen #3, #4 and #5 shields to equal #2 shield");
			System.out.println();
			System.out.println("KF5R:      Standard, +Convert 3 Ph-II to 2 Ph-I");
			System.out.println("KR Types:  Standard, +Convert 2 waist Ph-II to 1 Ph-I to 1 Ph-III");
		} else if (race.contentEquals("Z")) {
			System.out.println("                                               FLEET REFITS:   (R5.70)");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("C-14 (CS Class ships):  a) +1 disruptor (both sides) and upgrade firing arc to FA+L or FA+R");
			System.out.println("                        b) Upgrade firing arc of front left/right Ph-I to FA+L/FA+R");
			System.out.println("                        c) Upgrade each warp engine to 10 boxes");
			System.out.println("                        d) +DERFACS");
			System.out.println("C-12 (CL/CVL):       All C-14 refits, +2 APR boxes, +2 drone racks");
			System.out.println("C-10 (FF/AF/DF/SF):  +2 drone racks, increase shield boxes FF, AF, DF ");
			System.out.println("C-8 (MS/DF/AF):      Increase shield boxes FF, AF, DF ");
		}
		System.out.println("=============================================================================================================");
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

		String hasSSD = "";
		System.out.println("ShpYrd\tRace\tShip\tCrew\tBrdg\tBPV\tBreak\tMove\tSpare\tSize\tTurn\tRule\tYear in");
		System.out.println("Number\t\tType\tUnits\tParties\t\tDown\tCost\tShtls\tClass\tMode\tNumber\tService");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		int num = 1;
		int firstOfRace = -1;
		for (int i = 0; i < this.numShips; i++) {
			if (this.list[i].race.equals(race)) {
				if (this.list[i].hasSSD == true) {
					hasSSD = " *";
				}

				if (showOnlySSDs == true) {		// Do this if SSD Toggle is ON (TRUE)
					if (num < 10) {
						if (hasSSD == " *") {
							System.out.print(" " + num + ")" + hasSSD + "\t" + this.list[i].toString());
							System.out.println();
						}
						if(firstOfRace == -1) {
							firstOfRace = i;
						}
					} else {
						if (hasSSD == " *") {
							System.out.print(num + ")" + hasSSD  + "\t" + this.list[i].toString());
							System.out.println();
						}
					}
					num++;
				} else {						// Do this if SSD Toggle is OFF (FALSE)
					if (num < 10) {
						System.out.print(" " + num + ")" + hasSSD + "\t" + this.list[i].toString());
						System.out.println();
						if(firstOfRace == -1) {
							firstOfRace = i;
						}
					} else {
						System.out.print(num + ")" + hasSSD  + "\t" + this.list[i].toString());
						System.out.println();
					}
					num++;
				}
			}
			hasSSD = "";
		}		
		
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		
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
		} else if (r.equalsIgnoreCase("O")) {
			race = "Orion";
		} else if (r.equalsIgnoreCase("H")) {
			race = "Hydran";
		} else if (r.equalsIgnoreCase("A")) {
			race = "Andromedan";
		} else if (r.equalsIgnoreCase("W")) {
			race = "WYN";
		} else if (r.equalsIgnoreCase("L")) {
			race = "Lyran";
		} else if (r.equalsIgnoreCase("X")) {
			race = "Frax";
		} else if (r.equalsIgnoreCase("S")) {
			race = "Seltorian";
		} else if (r.equalsIgnoreCase("I")) {
			race = "ISC";
		} else if (r.equalsIgnoreCase("C")) {
			race = "Civilian";
		} else if (r.equalsIgnoreCase("Q")) {
			race = "All Fleets";
		} else if (r.equalsIgnoreCase("M")) {
			race = "Monster";
		} else if (r.equalsIgnoreCase("O")) {
			race = "Other";
		}
		return race;
	}
}
