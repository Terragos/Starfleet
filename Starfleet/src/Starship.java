
public class Starship {

	enum Ship { TORPEDO, DRONE, SHUTTLE, FIGHTER, STARSHIP, MONSTER }
	/*
	enum Race { FEDERATION, KLINGON, ROMULAN, KZINTI, GORN, THOLIAN }
	enum Type { DNPLUS, DN, CA, CX, CL, TUG }
	*/
	
	public static String[] partNames = {"Bridge", "Flag Bridge", "Emergency Bridge", "Aux Control", "Phaser", "Torpedo", "Center Warp Engine", 
			"Left Warp Engine", "Right Warp Engine", "Impulse", "APR", "Battery", "Transporter", "Tractor Beam", "Probe", 
			"Shuttle", "Drone", "Lab", "Cargo", "Front Hull", "Aft Hull", "Damage Control", "Sensor", "Scanner", "Excess Damage"};         
	
	//  Starship class
	public String race;
	public String shipType;
	public String crewUnits;
	public String boardingParties;
	public String BPV;
	public String breakDown;
	public String moveCost;
	public String spareShuttles;
	public String sizeClass;
	public String name;      // name
	public String turnMode;  // turn mode letter - converted to # of hexes to move forward before turning during Impulse Procedure
	public String ruleNum;
	public String yearInService;
	public int speed;        // hexes moved for entire turn
	public double spi;       // speed per impulse
	public double distrv;    // distance traveled 
	public Ship kindOfShip;
	public Part[] ssd;

	/* Default Constructor */
	public Starship() {
		
	}
	
	/* Non Regular Starship Constructor */
	public Starship(Ship kind, int speed, String name) {
		if(kind == Ship.TORPEDO) { 
			this.name = name;
			this.turnMode = "X";
			this.speed = speed;
			this.spi = speed/32.0;
			this.distrv = 0;
			this.breakDown = "-";
			this.kindOfShip = kind;
		} else if(kind == Ship.DRONE) {
			this.name = name;
			this.speed = speed;
			this.spi = speed/32.0;
			this.distrv = 0;
			this.turnMode = "X";
			this.breakDown = "-";
			this.kindOfShip = kind;
			this.ssd = new Part[25];
			this.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5);
			// Maybe make a drone class
		} else if(kind == Ship.SHUTTLE) {
			this.name = name;
			this.speed = speed;
			this.spi = speed/32.0;
			this.distrv = 0;
			this.turnMode = "Y";
			this.breakDown = "*";
			this.kindOfShip = kind;
			this.ssd = new Part[25];
			this.setupSSD(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8);
		} else if(kind == Ship.FIGHTER) {
			this.name = name;
			this.speed = speed;
			this.spi = speed/32.0;
			this.distrv = 0;
			this.turnMode = "Y";
			this.breakDown = "*";
			this.kindOfShip = kind;	
		}
	}
	
	public Starship(String race, String type, String crewUnits, String boardingParties, String BPV, 
			String breakDown, String moveCost, String spareShuttles, String sizeClass, 
			String turnMode, String ruleNum, String yearInService) {
		this.race = race;
		this.shipType = type;
		this.crewUnits = crewUnits;
		this.boardingParties = boardingParties;
		this.BPV = BPV;
		this.breakDown = breakDown;
		this.moveCost = moveCost;
		this.spareShuttles = spareShuttles;
		this.sizeClass = sizeClass;
		this.name = name;
		this.turnMode = turnMode;
		this.ruleNum = ruleNum;
		this.yearInService = yearInService;
		this.name = race.substring(0,3) + "-" + type;
		if(this.name.length() > 10)
			this.name = this.name.substring(0, 10);
		this.speed = 0;
		this.spi = 0.0;
		this.distrv = 0.0;
		this.kindOfShip = Ship.STARSHIP;
		this.ssd = new Part[25];
	}
	
	public Starship(String race, String type, String crewUnits, String boardingParties, String BPV, 
			String breakDown, String moveCost, String spareShuttles, String sizeClass, 
			String turnMode, String ruleNum, String yearInService, int speed, double spi, double distrv) {
		this.race = race;
		this.shipType = type;
		this.crewUnits = crewUnits;
		this.boardingParties = boardingParties;
		this.BPV = BPV;
		this.breakDown = breakDown;
		this.moveCost = moveCost;
		this.spareShuttles = spareShuttles;
		this.sizeClass = sizeClass;
		this.turnMode = turnMode;
		this.ruleNum = ruleNum;
		this.yearInService = yearInService;
		this.speed = speed;
		this.spi = spi;
		this.distrv = distrv;
		this.name = race.substring(0,3) + "-" + type;
		this.kindOfShip = Ship.STARSHIP;
		this.ssd = new Part[25];
	}
	
	public String toString() {
		return this.race.substring(0,3).toUpperCase() + "\t" + this.shipType + "\t" + this.crewUnits + "\t" + this.boardingParties + "\t"
				+ this.BPV + "\t" + this.breakDown + "\t" + this.moveCost + "\t" + this.spareShuttles + "\t" + this.sizeClass + "\t" 
				+ this.turnMode + "\t" + this.ruleNum + "\t" + this.yearInService;
	}
	

	public void setupSSD(int bridge, int flagBridge, int emerBridge, int aux, int phaser, int torpedo, int centerWarp, 
			int leftWarp, int rightWarp, int impulse, int apr, int battery, int trans, int tractor, int probe, 
			int shuttle, int drone, int lab, int cargo, int fHull, int aHull, int damageCon, int sensor, int scanner, int excessDam) {
		
		int[] ints = { bridge, flagBridge, emerBridge, aux, phaser, torpedo, centerWarp, 
				leftWarp, rightWarp, impulse, apr, battery, trans, tractor, probe, 
				shuttle, drone, lab, cargo, fHull, aHull, damageCon, sensor, scanner, (excessDam+1) };
		
		Starship ship = new Starship();
		
		for(int i = 0; i < partNames.length; i++) {
			this.ssd[i] = new Part(ints[i], partNames[i]);
		}
	}
}












