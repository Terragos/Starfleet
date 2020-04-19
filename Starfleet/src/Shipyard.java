
public class Shipyard {

	public static int numShips = 0;
	public static Starship[] list;
	
	public Shipyard() {
		numShips = 0;
		list = new Starship[50];
	}
	
	public void addShipToShipyard(Starship ship) {
		list[numShips] = ship;
	}
	
}
