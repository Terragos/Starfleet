
public class Starship {
	
	public static int numShips = 0;
	public static Starship[] starships;

	//  Starship class
	public String name;      // name
	public String turnMode;  // turn mode letter - converted to # of hexes to move forward before turning during Impulse Procedure
	public int speed;        // hexes moved for entire turn
	public double spi;       // speed per impulse
	public double distrv;    // distance traveled 

}
