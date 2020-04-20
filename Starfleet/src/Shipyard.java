
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
		ship = new Starship("Federation", "GS(CVL+)", "44", "6", "143", "5-6", "1", "1+2", "3", "D", "16", "175");
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
		ship = new Starship("Klingon", "C-9", "62", "24", "205", "3-6", "1.5", "2", "2", "D", "2", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "C-8", "60", "24", "211", "3-6", "1.5", "2", "2", "D", "3", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "C-8B", "60", "24", "217", "3-6", "1.5", "2", "2", "D", "3", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "C-8V", "66", "20", "235/200", "3-6", "1.5", "2+6", "2", "D", "28", "174");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "DX", "46", "20", "267", "5-6", "1", "2", "2", "B", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-7", "45", "14", "117", "5-6", "1", "1", "3", "B", "4", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-7A", "45", "14", "127", "5-6", "1", "1", "3", "B", "8", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-7B", "45", "14", "125", "5-6", "1", "1", "3", "B", "4", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-7C", "47", "16", "139", "5-6", "1", "2", "3", "B", "31", "123");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6", "44", "14", "113", "5-6", "1", "1", "3", "B", "5", "62");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6B", "44", "14", "124", "5-6", "1", "1", "3", "B", "5", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6CV", "45", "8", "106", "5-6", "1", "1+2", "3", "B", "21", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6CVB", "45", "8", "114", "5-6", "1", "1+2", "3", "B", "21", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6M", "44", "6", "125", "5-6", "1", "1", "3", "B", "33", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6MB", "44", "6", "131", "5-6", "1", "1", "3", "B", "33", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6D", "46", "6", "113", "5-6", "1", "1", "3", "B", "32", "135");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6DB", "46", "6", "117", "5-6", "1", "1", "3", "B", "32", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6PFT", "44", "8", "109", "5-6", "1", "1", "3", "B", "22", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-6PFB", "44", "8", "117", "5-6", "1", "1", "3", "B", "22", "184");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-5", "40", "8", "110", "5-6", "0.67", "1", "3", "B", "23", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "D-5A", "40", "8", "118", "5-6", "0.67", "1", "3", "B", "24", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "AD-5", "40", "8", "120", "5-6", "0.67", "1", "3", "B", "29", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5L", "26", "12", "94", "4-6", "0.5", "1", "4", "A", "34", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5", "22", "8", "71", "4-6", "0.5", "-", "4", "A", "6", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5B", "22", "8", "75", "4-6", "0.5", "-", "4", "A", "6", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5D", "22", "6", "90", "4-6", "0.5", "-", "4", "A", "35", "79");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5S", "20", "6", "80/60", "4-6", "0.5", "-", "4", "A", "20", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5SB", "20", "6", "85/64", "4-6", "0.5", "-", "4", "A", "20", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5M", "20", "6", "75/60", "4-6", "0.5", "-", "4", "A", "27", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5MB", "20", "6", "77/64", "4-6", "0.5", "-", "4", "A", "27", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "F-5CVL", "24", "6", "90/70", "4-6", "0.5", "1+2", "4", "A", "30", "167");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E-4", "14", "6", "55", "4-6", "0.33", "-", "4", "A", "7", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E-4B", "14", "6", "59", "4-6", "0.33", "-", "4", "A", "7", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E-4A", "14", "6", "60", "4-6", "0.33", "-", "4", "A", "25", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E-3", "12", "5", "42", "4-6", "0.33", "-", "4", "A", "18", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "E-3A", "12", "5", "48", "4-6", "0.33", "-", "4", "A", "26", "176");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "G-2", "10", "4", "46", "5-6", "0.33", "-", "4", "A", "19", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "G-1 PF", "3", "1", "20/38", "6", "0.2", "-", "5", "AA", "81", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "Tug-A", "20", "7", "125/110", "3-6", "1", "1", "3", "*", "9", "119");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "Tug-B", "18", "3", "106/70", "3-6", "1", "1", "3", "*", "10", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "CVT", "40", "13", "158/139", "3-6", "1", "1+4", "3", "E", "16", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-C1", "0", "0", "14/10", "-", "*", "-", "-", "-", "11", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-P2", "3", "1", "28/15", "-", "*", "-", "-", "-", "12", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-T3", "3+20", "40", "42/30", "-", "*", "-", "4*", "-", "13", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-B4", "10", "6", "31", "-", "*", "1", "-", "-", "14", "119");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-H5", "10", "3", "14/12", "-", "*", "0+2", "-", "-", "15", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Klingon", "P-PF6", "10", "2", "16", "-", "*", "-", "-", "-", "36", "180");
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

		ship = new Starship("Romulan", "DN ", "62", "22", "203", "3-6", "1.5", "2", "2", "D", "2", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "BC", "52", "20", "142", "4-6", "1", "2", "3", "C", "3", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CA", "42", "12", "133", "5-6", "1", "1", "3", "C", "4", "68");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CX", "45", "12", "272", "5-6", "1", "1", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CV", "44", "10", "135", "5-6", "1", "2+4", "3", "C", "12", "172");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CW", "34", "10", "115", "5-6", "0.67", "1", "3", "B", "N5", "165");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "CL", "34", "9", "92", "5-6", "0.67", "1", "3", "C", "5", "68");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "DD", "26", "6", "79", "6", "0.5", "1", "4", "B", "6", "68");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "SC", "25", "6", "100/60", "6", "0.5", "1", "4", "B", "9", "88");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "MS", "22", "4", "80/60", "6", "0.5", "1", "4", "B", "8", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "PFT", "28", "4", "80/50", "6", "0.5", "1", "4", "B", "10", "178");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "FF", "18", "4", "63", "6", "0.33", "1", "4", "A", "7", "68");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "AF", "18", "4", "70", "6", "0.33", "1", "4", "A", "11", "177");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Tug-P", "34", "6", "121/102", "3-6", "1", "-", "3", "D", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Tug-C", "40", "10", "138/124", "3-6", "1", "1", "3", "D", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Pal-Bt", "20", "12", "50/60", "-", "*", "-", "-", "-", "N6", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Pal-C", "-", "-", "28/20", "-", "*", "-", "-", "-", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Pal-TT", "4+40", "80", "50/30", "-", "*", "-", "4*", "-", "N6", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Pal-PFT", "20", "4", "40/30", "-", "*", "-", "-", "-", "N6", "178");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "PF", "3", "1", "20/37", "6", "0.2", "-", "5", "AA", "81", "178");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "Fi-Con", "3", "1", "20/25", "6", "0.2", "-", "5", "AA", "82", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "S-Qshp", "5", "5", "41", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Romulan", "L-Qshp", "10", "10", "83", "2-6", "0.5", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Kzinti", "SSCS", "70", "30", "245", "4-6", "1.5", "3+6", "2", "E", "24", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "SCS", "65", "24", "215", "4-6", "1.5", "3+3", "2", "E", "11", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVA", "65", "20", "215", "4-6", "1.5", "2+6", "2", "E", "25", "173");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CV", "50", "20", "147", "5-6", "1", "3+3", "3", "E", "6", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVS", "50", "20", "169", "5-6", "1", "3+3", "3", "E", "7", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVL", "40", "15", "117", "5-6", "1", "2+2", "3", "C", "9", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CVE", "30", "10", "89", "5-6", "0.67", "1+2", "3", "B", "10", "166");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CSX", "41", "18", "264", "5-6", "1", "3", "2", "C", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CC", "44", "20", "150", "5-6", "1", "2", "3", "C", "4", "107");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "BC", "40", "16", "138", "5-6", "1", "2", "3", "C", "3", "160");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CS", "40", "16", "116", "5-6", "1", "2", "3", "C", "2", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CM", "33", "12", "110", "5-6", "0.67", "1", "3", "B", "19", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "CL", "30", "10", "84", "5-6", "0.67", "1", "3", "B", "5", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "FF", "20", "6", "62", "5-6", "0.33", "1", "4", "A", "8", "65");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "AFF", "20", "6", "70", "5-6", "0.33", "1", "4", "A", "20", "177");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "DF", "21", "4", "74", "5-6", "0.33", "1", "4", "A", "23", "104");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "SF", "18", "4", "90/55", "5-6", "0.33", "1", "4", "A", "18", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "PFT", "30", "8", "75/65", "5-6", "0.5", "1", "3", "B", "22", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MS", "18", "2", "70/45", "5-6", "0.33", "1", "4", "A", "21", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Tug", "28", "8", "114/90", "4-6", "1", "2", "3", "*", "12", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-C1", "0", "0", "14/10", "-", "*", "-", "-", "-", "13", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-H2", "11", "4", "19/12", "-", "*", "0+2", "-", "-", "14", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-B3", "12", "8", "37", "-", "*", "-", "-", "-", "15", "121");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-SD4", "6", "6", "24/20", "-", "*", "-", "-", "-", "16", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-T5", "3+20", "40", "31/20", "-", "*", "-", "4*", "-", "17", "95");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "P-PF6", "12", "4", "20/12", "-", "*", "-", "-", "-", "26", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Needle", "3", "1", "20/37", "6", "0.2", "-", "5", "AA", "81", "179");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "Fi-Con", "3", "1", "30", "6", "0.2", "-", "5", "AA", "83", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-ABJ", "3", "1", "30/37", "6", "0.2", "-", "5", "AA", "82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-C-H", "3", "1", "30", "6", "0.2", "-", "5", "AA", "82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "MRN-E", "3", "1", "100/50", "6", "0.2", "-", "5", "AA", "82", "180");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "S-Qshp", "6", "6", "30", "2-6", "0.33", "-", "4", "-", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Kzinti", "L-Qshp", "12", "12", "62", "2-6", "0.5", "-", "4", "-", "-", "74");
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
		ship = new Starship("Gorn", "BC", "48", "16", "171", "5-6", "1", "3", "3", "D", "19", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CA", "46", "16", "141", "5-6", "1", "3", "3", "D", "2", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CA+", "46", "16", "157", "5-6", "1", "3", "3", "D", "2", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CV", "36", "8", "120", "4-6", "0.67", "2+4", "3", "D", "16", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CL", "32", "8", "107", "4-6", "0.67", "2", "3", "D", "3", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CL+", "32", "8", "123", "4-6", "0.67", "2", "3", "D", "3", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "CLF", "32", "8", "137", "4-6", "0.67", "2", "3", "D", "3", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "LSC", "30", "8", "100/80", "4-6", "0.67", "2", "3", "D", "10", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "HDD", "32", "12", "105", "5-6", "0.67", "1", "3", "C", "12", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "BDD", "24", "8", "85", "5-6", "0.5", "1", "4", "B", "N6", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DD", "20", "6", "68", "4-6", "0.5", "1", "4", "C", "4", "69");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DD+", "20", "6", "76", "4-6", "0.5", "1", "4", "C", "4", "170");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "DDF", "20", "6", "90", "4-6", "0.5", "1", "4", "C", "4", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "SC", "20", "6", "80/55", "4-6", "0.5", "1", "4", "C", "13", "75");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "SCF", "20", "6", "84/70", "4-6", "0.5", "1", "4", "C", "13", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "PFT", "20", "4", "70/55", "4-6", "0.5", "1", "4", "C", "14", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "PFTF", "20", "4", "84/70", "4-6", "0.5", "1", "4", "C", "14", "183");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "MS", "20", "4", "70/55", "4-6", "0.5", "1", "4", "C", "15", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "MSF", "20", "4", "84/70", "4-6", "0.5", "1", "4", "C", "15", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "Tug", "23", "4", "96/44", "2-6", "*", "2", "3", "*", "5", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "TugF", "23", "4", "110/58", "2-6", "*", "2", "3", "*", "5", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-C", "0", "0", "15/10", "-", "*", "-", "-", "-", "6", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-T", "2+40", "80", "60/30", "-", "*", "2", "4*", "-", "7", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-SL", "2+40", "6", "40/20", "-", "*", "1", "4*", "-", "9", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-M", "17", "6", "45/96", "-", "*", "-", "-", "-", "8", "121");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "P-M+", "20", "8", "60/120", "-", "*", "-", "-", "-", "8", "175");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "Pter PF", "3", "1", "20/40", "6", "0.2", "-", "5", "AA", "81", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "S-Qshp", "6", "5", "35", "2-6", "0.33", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Gorn", "L-Qshp", "12", "10", "80", "2-6", "0.5", "-", "4", "B", "-", "74");
		defaultYard.addShipToShipyard(ship);

		ship = new Starship("Tholian", "D", "45", "14", "175", "4-6", "1", "2", "2", "C", "5", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "CX", "36", "10", "215", "5-6", "0.75", "1", "2", "B", "S2", "-");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "C", "34", "10", "120", "4-6", "0.75", "1", "3", "B", "6", "147");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "CVA", "40", "8", "141", "4-6", "0.75", "1+4", "3", "B", "9", "173");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "DD", "18", "8", "80", "5-6", "0.5", "-", "4", "A", "4", "115");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "PC+", "14", "6", "65", "5-6", "0.5", "1", "4", "A", "3", "98");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "PC", "12", "5", "59", "5-6", "0.5", "1", "4", "A", "2", "83");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "SC", "12", "4", "90/50", "5-6", "0.5", "1", "4", "A", "12", "125");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "MS", "12", "4", "60/50", "5-6", "0.5", "1", "4", "A", "13", "168");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "CPC", "12", "5", "55/50", "5-6", "0.5", "1", "4", "A", "11", "90");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "C-Pk", "0", "0", "6", "-", "*", "-", "-", "-", "11", "85");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "BW", "20", "6", "65", "5-6", "0.5", "1+2", "4", "A", "7", "169");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "PFT", "20", "6", "70", "5-6", "0.5", "1", "4", "A", "8", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "WT", "10", "5", "70/30", "3-6", "0.33", "1", "4", "B", "10", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "Arac PF", "3", "1", "20/38", "6", "0.2", "-", "5", "AA", "81", "181");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "S-Qshp", "5", "4", "41", "2-6", "0.33", "-", "4", "B", "-", "150");
		defaultYard.addShipToShipyard(ship);
		ship = new Starship("Tholian", "L-Qshp", "10", "8", "83", "2-6", "0.5", "-", "4", "B", "-", "150");
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
					System.out.print("Which ship to add to the game?  [0 to cancel]");
			
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
