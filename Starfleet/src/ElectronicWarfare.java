
public class ElectronicWarfare {

	public static double GetEWshift(int shipNumFiring, int shipNumTarget) {
		int asteroidECM = 0;
		if(TerrainStuff.TerrainTypeList.contains("A")) {
			System.out.print("How many Asteroid hexes are being fired through (+ECM modifier for target)? ");
			asteroidECM = Driver.getNumberNoCancel(1,100);
		}
		
		int nebluaeECM = 0;
		if(TerrainStuff.TerrainTypeList.contains("N")) {
			System.out.println("Nebulae +ECM modifier for target)");
			nebluaeECM = 9;
		}
		
		int cloakECM = 0;
		if(Driver.currentGameYard.list[shipNumTarget].cloakOn) {
			System.out.println("Targetted ship is CLOAKED. (+ECM modifier for target)");
			cloakECM = 5;
		}

		Starship currentShip = Driver.currentGameYard.list[shipNumFiring];
		Starship targetShip = Driver.currentGameYard.list[shipNumTarget];
		
		int terrainECM = asteroidECM + cloakECM + nebluaeECM;
		
		Driver.electronicWarfareNet = Math.sqrt(targetShip.ECM + terrainECM - currentShip.ECCM);
		if (Driver.electronicWarfareNet < 0) {
			Driver.electronicWarfareNet = 0;
		}
		
		if (Driver.TESTING) {
			System.out.println("ECCM + terrain - ECM: " + Driver.electronicWarfareNet);
		}
		
		return Driver.electronicWarfareNet;
	}
	
	
	
	
	
	public static int[] GetEWadjustment(int die, int dist, int maxDist) {
		int EW[] = {0,0};
		
		if (Driver.TESTING) {
			System.out.println();
			System.out.println("   die: " + die + "\t   dist: " + dist);
			System.out.println("ECCM-ECM: " + Driver.electronicWarfareNet);
		}

//		Driver.electronicWarfareNet = GetEWshift(shipNumFiring, shipNumTarget);

		int newDie = die + (int) Driver.electronicWarfareNet;
		int newDist = dist;
		if (newDie > 6) {
			newDist = newDist + newDie - 6;
			if (newDist > maxDist) {
				newDist = maxDist;
			}
			newDie = 6;
		}
		
		EW[0] = newDie;
		EW[1] = newDist;

		if (Driver.TESTING) {
			System.out.println("newDie: " + newDie + "\tnewDist: " + newDist);
		}
		return EW;
	}
	
}
