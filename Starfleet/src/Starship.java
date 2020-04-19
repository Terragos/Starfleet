
public class Starship {

	enum Race { FEDERATION, KLINGON, ROMULAN, KZINTI, GORN, THOLIAN }
	enum Type { DN, CA, CX, CL, TUG }
	
	//  Starship class
	public Race race;
	public Type shipType;
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

	public Starship() {
		
	}
	
	public Starship(Race race, Type type, String crewUnits, String boardingParties, String BPV, 
			String breakDown, String moveCost, String spareShuttles, String sizeClass, String name, 
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
		this.name = name;
		this.ruleNum = ruleNum;
		this.turnMode = turnMode;
		this.yearInService = yearInService;
		this.speed = speed;
		this.spi = spi;
		this.distrv = distrv;
	}
	
	public Starship(Race race, Type type, String crewUnits, String boardingParties, String BPV, 
			String breakDown, String moveCost, String spareShuttles, String sizeClass, String name, 
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
		this.ruleNum = ruleNum;
		this.turnMode = turnMode;
		this.yearInService = yearInService;
	}
	
}
