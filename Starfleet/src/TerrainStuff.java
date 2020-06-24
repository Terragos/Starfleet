
public class TerrainStuff {
	
	public static boolean Terrain = false;
	public static String TerrainTypeList = "";

	public static double GravityWaveStrength = 0.0;
	public static int GravityWaveDamage = 0;
	public static double GravityWaveStrengthStart = 0.0;
	public static int GravityWaveTurnNumberStart = 0;
	public static int GravityWaveTurnInterval = 0;
	
	public static int PulsarDamage = 0;
	public static int PulsarBaseStrength = 0;
	public static int PulsarTurnNumberStart = 1;
	public static int PulsarTurnInterval = 0;
	public static int PulsarRandomImpulse = 0;

	public static int DustCloudIntensity;
	
	public static int NebulaeECM = 9;
	public static int NebulaeIntensity = 0;
	
	public static boolean EmptyLinePrinted = false;
	
	public static void AddTerrain() {
		String terrainInput = "";
		boolean cont = true;
		
		System.out.println();
		System.out.println("|=================================================================================|");
		System.out.println("|                                 TERRAIN TYPES                                   |");
		System.out.println("|=================================================================================|");
		System.out.println("|   [A]steroids                           [B]lack Hole                            |");
		System.out.println("|   [N]ebulae                             [V]ariable Pulsar                       |");
		System.out.println("|   [S]unspots                            [I]on Storms                            |");
		System.out.println("|   [R]adiation Zone                      [D]ust Clouds                           |");
		System.out.println("|   [W]YN Radiation Zone                  [P]lanetary Rings                       |");
		System.out.println("|   [G]ravity Wave                                                                |");
		System.out.println("|=================================================================================|");
		System.out.println("|                        Type Letter Again to Toggle Off                          |");
		System.out.println("|=================================================================================|");
		
		while (cont) {
			System.out.println();
			System.out.print("What Terrain would you like to add to the map? [RETURN to cancel] ");
			terrainInput = Driver.getInput("ABNVSIRDWPG");
			if (terrainInput.equalsIgnoreCase("A")) {
				if (TerrainTypeList.contains("A")) {
					System.out.print("Terrain already includes Asteroids.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "A";
				}
			
			} else if (terrainInput.equalsIgnoreCase("B")) {
				if (TerrainTypeList.contains("B")) {
					System.out.print("Terrain currently includes Black Hole.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "B";
				}

			} else if (terrainInput.equalsIgnoreCase("N")) {
				if (TerrainTypeList.contains("N")) {
					System.out.print("Terrain currently includes Nebulae.  Remove? ");
					RemoveTerrain(terrainInput);
					NebulaeIntensity = 0;
				} else {
					TerrainTypeList = TerrainTypeList + "N";
					System.out.print("Intensity of Nebula: (% chance to be randomly moved each impulse, 10=normal)? ");
					int intensityInput = Driver.getNumberNoCancel(1,100);
					NebulaeIntensity = intensityInput;
					System.out.println("Reminder:  Shields can ONLY be operated at minimum.");
				}
				
			} else if (terrainInput.equalsIgnoreCase("V")) {
				if (TerrainTypeList.contains("V")) {
					System.out.print("Terrain currently includes Variable Pulsar.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "V";
					System.out.println("Variable Pulsar will burst on turn 1, and randomly burst within the next 3 turns, etc...");
				}
				
			} else if (terrainInput.equalsIgnoreCase("S")) {
				if (TerrainTypeList.contains("S")) {
					System.out.print("Terrain currently includes Sunspots.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "S";
				}
				
			} else if (terrainInput.equalsIgnoreCase("I")) {
				if (TerrainTypeList.contains("I")) {
					System.out.print("Terrain currently includes Ion Storms.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "I";
				}
				
			} else if (terrainInput.equalsIgnoreCase("R")) {
				if (TerrainTypeList.contains("R")) {
					System.out.print("Terrain currently includes Radiation Zone.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "R";
				}
				
			} else if (terrainInput.equalsIgnoreCase("D")) {
				if (TerrainTypeList.contains("D")) {
					System.out.print("Terrain currently includes Dust Clouds.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "D";
					System.out.print("Intensity of Dust Cloud: [N]ormal or [D]ouble? ");
					String intensityInput = Driver.getInput("ND");
					if (intensityInput.equalsIgnoreCase("D")) {
						DustCloudIntensity = 2;
					} else {
						DustCloudIntensity = 1;
					}
					
					if (Driver.TESTING) {
						System.out.println("DustCloudIntensity: " + DustCloudIntensity);
					}
					
				}
				
			} else if (terrainInput.equalsIgnoreCase("W")) {
				if (TerrainTypeList.contains("W")) {
					System.out.print("Terrain currently includes WYN Radiation Zone.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "W";
				}
				
			} else if (terrainInput.equalsIgnoreCase("P")) {
				if (TerrainTypeList.contains("P")) {
					System.out.print("Terrain currently includes Planetary Rings.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "P";
				}
				
			} else if (terrainInput.equalsIgnoreCase("G")) {
				if (TerrainTypeList.contains("G")) {
					System.out.print("Terrain currently includes Gravity Waves.  Remove? ");
					RemoveTerrain(terrainInput);
				} else {
					TerrainTypeList = TerrainTypeList + "G";
					System.out.print("Starting strength of Gravity Wave? [10-100] ");
					GravityWaveStrength = Driver.getNumberDoubleNoCancel(20, 100);
					GravityWaveStrengthStart = GravityWaveStrength;
					System.out.print("Start Gravity Wave on turn: ");
					GravityWaveTurnNumberStart = Driver.getNumberNoCancel(1, 10);
					System.out.print("Gravity Wave interval (0 = once only on above turn): ");
					GravityWaveTurnInterval = Driver.getNumberNoCancel(0, 10);
				}
				
			} else if (terrainInput.equalsIgnoreCase("")) {
				cont = false;
			}
			System.out.println();
			PrintTerrain();
		}
		
		if (TerrainTypeList.length() > 0) {
			Terrain = true;
		} else {
			Terrain = false;
		}
	}
	
	
	
	
	
	
	public static void RemoveTerrain(String terrainInput) {
		String yesOrNo = Driver.getInput("YN");
		if (yesOrNo.equalsIgnoreCase("Y")) {
			int terrainLocationToRemove = 0;
			terrainLocationToRemove = TerrainTypeList.indexOf(terrainInput);
			TerrainTypeList = TerrainTypeList.substring(0, terrainLocationToRemove) + TerrainTypeList.substring(terrainLocationToRemove + 1, TerrainTypeList.length());	
		}
	}
	
	
	
	
	
	public static void PrintTerrain() {
		
		System.out.print("Current Terrain List: ");
		
		for (int i = 0; i <= TerrainTypeList.length(); i++) {
			
			if (i == TerrainTypeList.indexOf("A")) {
				System.out.print("Asteroids");
			} else if (i == TerrainTypeList.indexOf("B")) {
				System.out.print("Black Hole");
			} else if (i == TerrainTypeList.indexOf("N")) {
				System.out.print("Nebulae");
			} else if (i == TerrainTypeList.indexOf("V")) {
				System.out.print("Variable Pulsar");
			} else if (i == TerrainTypeList.indexOf("S")) {
				System.out.print("Sunspots");
			} else if (i == TerrainTypeList.indexOf("I")) {
				System.out.print("Ion Storms");
			} else if (i == TerrainTypeList.indexOf("R")) {
				System.out.print("Radiation Zone");
			} else if (i == TerrainTypeList.indexOf("D")) {
				System.out.print("Dust Clouds");
			} else if (i == TerrainTypeList.indexOf("W")) {
				System.out.print("WYN Radiation Zone");
			} else if (i == TerrainTypeList.indexOf("P")) {
				System.out.print("Planetary Rings");
			} else if (i == TerrainTypeList.indexOf("G")) {
				System.out.print("Gravity Waves");
			}
			if (i < TerrainTypeList.length()-1) {
				System.out.print(", ");
			}
		}

		System.out.println();
	}
	
	
	
	
	
	public static void TerrainDamage() {
		
		int asteroidDamage[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32},   //  speed
				  				  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //  die roll 1
				  				  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5},   //  die roll 2
				  				  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,10,10,10,10,10,10,10},   //  die roll 3
				  				  {0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,15,15,15,15,15,15,15},   //  die roll 4
				  				  {0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6,10,10,10,10,10,10,10,10,10,10,10,20,20,20,20,20,20,20},   //  die roll 5
				  				  {0, 0, 0, 0, 0, 0, 0,10,10,10,10,10,10,10,10,15,15,15,15,15,15,15,15,15,15,15,30,30,30,30,30,30,30}};  //  die roll 6
		
		boolean cont = true;
		String terrainInput = "";
		
		PrintTerrain();

		while (cont) {
			System.out.print("What Terrain Damage needs to be calculated (" + TerrainTypeList + ") [RETURN to cancel]? ");
			terrainInput = Driver.getInput(TerrainTypeList);

			if (terrainInput.equalsIgnoreCase("A")) {
				int print = ShipSetup.PrintCurrentThingsInGame("SHIP SHUTTLE FIGHTER", "");
				System.out.print("Which ship entered an Asteroid hex? [RETURN to cancel] ");
				int shipNumAsteroid = -5;
				shipNumAsteroid = ShipSetup.GetAdjustedInput(print, "SHIP SHUTTLE FIGHTER", "");
				System.out.println("shipNumAsteroid: " + shipNumAsteroid);
				if (shipNumAsteroid != -1) {
					System.out.print("How many Asteroid hexes? ");
					int numAsteroids = Driver.getNumber(1, 10);
					if (numAsteroids > 0) {
						int damage = 0;
						for (int i = 1; i <= numAsteroids; i++) {
							int die = DamageAllocation.rollDice(1, 6);
							damage = damage + asteroidDamage[die][Driver.currentGameYard.list[shipNumAsteroid].speed];
						}
						System.out.println(Driver.currentGameYard.list[shipNumAsteroid].name + " (speed " + Driver.currentGameYard.list[shipNumAsteroid].speed + "): " + damage + " pts of damage.");
						DamageAllocation.DamageAlloc(shipNumAsteroid, damage);
					}
				}
				
			} else if (terrainInput.equalsIgnoreCase("P")) {
				int print = ShipSetup.PrintCurrentThingsInGame("SHIP SHUTTLE FIGHTER", "");
				System.out.print("Which ship entered a Planetary Ring hex? [RETURN to cancel] ");
				int shipNumRings = -5;
				shipNumRings = ShipSetup.GetAdjustedInput(print, "SHIP SHUTTLE FIGHTER", "");
				System.out.println("shipNumRings: " + shipNumRings);
				if (shipNumRings != -1) {
					System.out.print("How many Planetary Ring hexes? ");
					int numRings = Driver.getNumber(1, 10);
					if (numRings > 0) {
						int damage = 0;
						for (int i = 1; i <= numRings; i++) {
							int die = DamageAllocation.rollDice(1, 6);
							damage = damage + asteroidDamage[die][Driver.currentGameYard.list[shipNumRings].speed] / 2;
						}
						System.out.println(Driver.currentGameYard.list[shipNumRings].name + " (speed " + Driver.currentGameYard.list[shipNumRings].speed + "): " + damage + " pts of damage.");
						DamageAllocation.DamageAlloc(shipNumRings, damage);
					}
				}
					
			} else if (terrainInput.equalsIgnoreCase("G")) {
				int print = ShipSetup.PrintCurrentThingsInGame("SHIP SHUTTLE FIGHTER", "");
				System.out.print("Which ship damaged by Gravity Wave? [RETURN to cancel] ");
				int shipNumGWDamage = -5;
				shipNumGWDamage = ShipSetup.GetAdjustedInput(print, "SHIP SHUTTLE FIGHTER", "");
				System.out.println("shipNumGWDamage: " + shipNumGWDamage);
				if (shipNumGWDamage != -1) {
					System.out.print("Gravity Wave Damage: " + GravityWaveDamage);
					DamageAllocation.DamageAlloc(shipNumGWDamage, GravityWaveDamage);
				}

			} else if (terrainInput.equalsIgnoreCase("")) {
				cont = false;

			}
		} //end while

	}
	
	
	
	
	
	public static void PrintEmptyLineOrNot() {
		if (!EmptyLinePrinted) {
			System.out.println();
			EmptyLinePrinted = true;
		}
		
	}
	
	
	
	
		
	public static void CheckForBlackHoleMovement(int impulse) {

		if (TerrainTypeList.contains("B")) {
			if (impulse == 32) {
				PrintEmptyLineOrNot();
				System.out.println("BLACK HOLE:");
				System.out.println("\tEverything within 30 hexes moves 1 hex closer.");

			} else if (impulse == 11 || impulse == 22) {
				PrintEmptyLineOrNot();
				System.out.println("BLACK HOLE:");
				System.out.println("\tEverything within 20 hexes moves 1 hex closer.");
				
			} else if (impulse == 5 || impulse == 16 || impulse == 27) {
				PrintEmptyLineOrNot();
				System.out.println("BLACK HOLE:");
				System.out.println("\tEverything within 10 hexes moves 1 hex closer.");
				
			} else if (impulse == 2 || impulse == 8 || impulse == 13 || impulse == 19 || impulse == 24 || impulse == 29) {
				PrintEmptyLineOrNot();
				System.out.println("BLACK HOLE:");
				System.out.println("\tEverything within 5 hexes moves 1 hex closer.");
				
			} else {
				PrintEmptyLineOrNot();
				System.out.println("BLACK HOLE:");
				System.out.println("\tEverything within 2 hexes moves 1 hex closer.");

			}
		}
	}

	
	
	
	
	
	public static void CheckForGravityWave(int impulse) {
		
		if (TerrainTypeList.contains("G") && Driver.TurnNumber == GravityWaveTurnNumberStart) {
			
			if (impulse < 22) {
				PrintEmptyLineOrNot();
				System.out.println("GRAVITY WAVE moves:");
				System.out.println("\tStrength is now " + (int) GravityWaveStrength + ".  Colliding ships turned 60° facing parallel to wave.  (Terrai[N] Damage)");
				GravityWaveDamage = (int) GravityWaveStrength;
				GravityWaveStrength = GravityWaveStrength * 0.9;
			} else if (impulse > 21) {
				double base = 33.0 - impulse;
				GravityWaveDamage = (int) GravityWaveStrength;
				GravityWaveStrength = base * GravityWaveStrengthStart / 100;
				PrintEmptyLineOrNot();
				System.out.println("GRAVITY WAVE moves:");
				System.out.println("\tStrength is now " + (int) GravityWaveStrength + ".  Colliding ships turned 60° facing parallel to wave.  (Terrai[N] Damage)");
							}
			if (impulse == 32) {
				GravityWaveTurnNumberStart = GravityWaveTurnNumberStart + GravityWaveTurnInterval;
				GravityWaveStrength = GravityWaveStrengthStart; 
			}
		}
	}

	
	
	
	
	public static void NebulaeRandomMovement(int impulse) {
		
//		change rules - instead of always impulses 5, 15 and 26 - 10% chance (average 3 times per turn)
		
//		if (impulse == 5 || impulse == 15 || impulse == 26) {
//			PrintEmptyLineOrNot();
//			System.out.println("Nebulae Randomizer: ");
//			for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
//				int direction = DamageAllocation.rollDice(1, 6);
//				int facing = DamageAllocation.rollDice(1, 3);
//				System.out.print("\t" + Driver.currentGameYard.list[i].name + " moves 1 hex in direction " + direction);
//				if (Driver.currentGameYard.list[i].name != "TORP" && facing == 1) {
//					System.out.print(", rotated 60 CW");
//				} else if (Driver.currentGameYard.list[i].name != "TORP" && facing == 2) {
//					System.out.print(", rotated 60 CCW");
//				} else if (Driver.currentGameYard.list[i].name != "TORP" && facing == 3) {
//					System.out.print(", same facing");
//				}
//				System.out.println();
//			}
//		}

		int count = 0;
		for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
			int moveShip = DamageAllocation.rollDice(1, 100);
			if (moveShip <= NebulaeIntensity) {
				count++;
				if (count == 1) {
					PrintEmptyLineOrNot();
					System.out.println("NEBULAE Randomizer: ");
				}
				int direction = DamageAllocation.rollDice(1, 6);
				int facing = DamageAllocation.rollDice(1, 3);
				System.out.print("\t" + Driver.currentGameYard.list[i].name + " moves 1 hex in direction " + direction);
				if (Driver.currentGameYard.list[i].name != "TORP" && facing == 1) {
					System.out.print(", rotated 60° CW.");
				} else if (Driver.currentGameYard.list[i].name != "TORP" && facing == 2) {
					System.out.print(", rotated 60° CCW.");
				} else if (Driver.currentGameYard.list[i].name != "TORP" && facing == 3) {
					System.out.print(", same facing.");
				}
				System.out.println();
				
			}
		}
	}

	
	
	
	
	public static void DustCloudDamge(int impulse) {
		int dustCloud[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32},   //  speed
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},   //  impulse 5
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},   //  impulse 10
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},   //  impulse 15
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},   //  impulse 20
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},   //  impulse 25
				  			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};  //  impulse 30

		int count = 0;
		if (TerrainTypeList.contains("D")) {
			double impulseDouble = impulse;
			if (impulseDouble / 5 == impulse / 5) {
				int impulseMultiple = impulse / 5;
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					if (Driver.currentGameYard.list[i].kindOfShip != Starship.Ship.TORPEDO) {
						int speed = Driver.currentGameYard.list[i].speed;
						String name = Driver.currentGameYard.list[i].name;
						if (dustCloud[impulseMultiple][speed] == 1) {
							count++;
							if (count == 1) {
								PrintEmptyLineOrNot();
								System.out.println("DUST CLOUD:");
								System.out.print("\tDamage (" + DustCloudIntensity + "): " + name);
							} else {
								System.out.print(", " + name);
							}
						}
					}
				}
				if (count == 0) {
					PrintEmptyLineOrNot();
					System.out.println("DUST CLOUD: No damage");
				} else {
					System.out.println();
				}
			}
		}
	}

	
	
	
	
	public static void PulsarDamage(int impulse) {

		int count = 0;
		if(TerrainTypeList.contains("V") && Driver.TurnNumber == PulsarTurnNumberStart) {

			if(PulsarRandomImpulse == 0) {
				PulsarRandomImpulse = DamageAllocation.rollDice(1, 31) + 1;
				PulsarBaseStrength = DamageAllocation.rollDice(1, 51) + 9;
				if (Driver.TESTING) {
					System.out.println("PulsarRandomImpulse: " + PulsarRandomImpulse + "   PulsarBaseStrength: " + PulsarBaseStrength);
				}
			}
			
			if(impulse == PulsarRandomImpulse) {
				PrintEmptyLineOrNot();
//				System.out.println("VARIABLE PULSAR: " + PulsarBaseStrength + " dmg (0-5 hexes), " + (int) (PulsarBaseStrength*0.75) + " dmg (6-10 hexes), " + (int) (PulsarBaseStrength/2) + " dmg (11-20 hexes), " + (int) (PulsarBaseStrength/4) + " dmg (21-50 hexes), 0 dmg (50+ hexes)");
				System.out.println("VARIABLE PULSAR:");
				System.out.println("\tBase Strength is " + PulsarBaseStrength + ".");
				
				for (int i = 0; i < Driver.currentGameYard.numShips; i++) {
					System.out.println();
					if(Driver.currentGameYard.list[i].name != "TORP") {
						System.out.print(Driver.currentGameYard.list[i].name + " - Distance from Pulsar? ");
						int distanceToPulsar = Driver.getNumberNoCancel(0, 60);
						
						int modifiedPulsarStrength = 0;
						
						modifiedPulsarStrength = (int) (PulsarBaseStrength * (Math.round(77.16 * (Math.pow(1.08, (-0.8*distanceToPulsar + 2))) + 10)) / 100);
						if (Driver.TESTING) {
							System.out.println("PulsarBaseStrength: " + PulsarBaseStrength);
							System.out.println("modifiedPulsarStrength: " + modifiedPulsarStrength);
							System.out.println("(Math.round(77.16 * (Math.pow(1.08, (-0.8*distanceToPulsar + 2))) + 10)) / 100): " + (Math.round(77.16 * (Math.pow(1.08, (-0.8*distanceToPulsar + 2))) + 10)) / 100);
						}
						
//						if (distanceToPulsar >= 0 && distanceToPulsar <= 5) {
//							modifiedPulsarStrength = PulsarBaseStrength;
//						} else if (distanceToPulsar >= 6 && distanceToPulsar <= 10) {
//							modifiedPulsarStrength = (int) (PulsarBaseStrength * 0.75);
//						} else if (distanceToPulsar >= 11 && distanceToPulsar <= 20) {
//							modifiedPulsarStrength = (int) (PulsarBaseStrength / 2);
//						} else if (distanceToPulsar >= 21 && distanceToPulsar <= 50) {
//							modifiedPulsarStrength = (int) (PulsarBaseStrength / 4);
//						} else if (distanceToPulsar >= 51) {
//							modifiedPulsarStrength = 0;
//						}
						
						if (modifiedPulsarStrength > 0) {
							if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.MONSTER) {
								MonsterStuff.MonsterDamageFromShip(i, modifiedPulsarStrength);
							} else if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.PLANET) {
								MonsterStuff.DamageToPlanet(i, modifiedPulsarStrength);
							} else if (Driver.currentGameYard.list[i].kindOfShip == Starship.Ship.STARSHIP) {
								DamageAllocation.DamageAlloc(i, modifiedPulsarStrength);
							}
						} else {
							System.out.println("NO damage from Variable Pulsar.");
							System.out.println();
						}
					}
				}
				PulsarTurnInterval = DamageAllocation.rollDice(1, 3);
				PulsarTurnNumberStart = PulsarTurnNumberStart + PulsarTurnInterval;
				PulsarRandomImpulse = 0;

				if (Driver.TESTING) {
					System.out.println("PulsarTurnInterval: " + PulsarTurnInterval);
				}

				System.out.println();
				PhaseCalculation.PrintImpulseHeader();
			}
		}
	}

	
}
