import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class OutputToTextFile {

	public static void AddShipManually() throws IOException {

		System.out.print("How many Sensor boxes? ");
		int sensorNumBoxes = Driver.getNumber(1,10);
		System.out.print("How many Scanner boxes? ");
		int scannerNumBoxes = Driver.getNumber(1,10);
		System.out.print("How many Damage Control boxes? ");
		int damConNumBoxes = Driver.getNumber(1,10);
		String format = String.valueOf(sensorNumBoxes) + String.valueOf(scannerNumBoxes) + String.valueOf(damConNumBoxes); 

//		System.out.println("format: " + format);
		
		if (format.equalsIgnoreCase("333")) {
			Driver.InstallSpecificShip("Blank", "333");			
		} else if (format.equalsIgnoreCase("444")) {
			Driver.InstallSpecificShip("Blank", "444");
		} else if (format.equalsIgnoreCase("443")) {
			Driver.InstallSpecificShip("Blank", "443");
		} else if (format.equalsIgnoreCase("555")) {
			Driver.InstallSpecificShip("Blank", "555");
		} else if (format.equalsIgnoreCase("554")) {
			Driver.InstallSpecificShip("Blank", "554");
		} else if (format.equalsIgnoreCase("666")) {
			Driver.InstallSpecificShip("Blank", "666");
		} else if (format.equalsIgnoreCase("667")) {
			Driver.InstallSpecificShip("Blank", "667");
		} else if (format.equalsIgnoreCase("665")) {
			Driver.InstallSpecificShip("Blank", "665");
		} else if (format.equalsIgnoreCase("777")) {
			Driver.InstallSpecificShip("Blank", "777");
		} else if (format.equalsIgnoreCase("776")) {
			Driver.InstallSpecificShip("Blank", "776");
		} else if (format.equalsIgnoreCase("888")) {
			Driver.InstallSpecificShip("Blank", "888");
		} else if (format.equalsIgnoreCase("887")) {
			Driver.InstallSpecificShip("Blank", "887");
		} else if (format.equalsIgnoreCase("999")) {
			Driver.InstallSpecificShip("Blank", "999");
		} else if (format.equalsIgnoreCase("998")) {
			Driver.InstallSpecificShip("Blank", "998");
		} else if (format.equalsIgnoreCase("101010")) {
			Driver.InstallSpecificShip("Blank", "101010");
		} else if (format.equalsIgnoreCase("10109")) {
			Driver.InstallSpecificShip("Blank", "10109");
		}
		
//		System.out.println("currentGameYard.numShips: " + Driver.currentGameYard.numShips);
		int newShipNum = Driver.currentGameYard.numShips - 1;
//		System.out.println("newShipNum: " + newShipNum);
		
		Starship currentShip = Driver.currentGameYard.list[newShipNum];

		System.out.print("Race: ");
		currentShip.race = Driver.keyboard.nextLine();
		currentShip.race = PhaseCalculation.capFirstLetter(currentShip.race.toLowerCase());
		System.out.print("Ship Type: ");
		currentShip.shipType = Driver.keyboard.nextLine().toUpperCase();
		currentShip.name = currentShip.race.substring(0, 1).toUpperCase() + currentShip.race.substring(1, 3) + "-" + currentShip.shipType;
		System.out.println("Ship Name: " + currentShip.name);
		System.out.print("Crew Units: ");
		currentShip.crewUnits = Driver.keyboard.nextLine();
		System.out.print("Boarding Parties: ");
		currentShip.boardingParties = Driver.keyboard.nextLine();
		System.out.print("BPV: ");
		currentShip.BPV = Driver.keyboard.nextLine();
		System.out.print("Break Down: ");
		currentShip.breakDown = Driver.keyboard.nextLine();
		System.out.print("Move Cost: ");
		currentShip.moveCost = Driver.keyboard.nextLine();
		System.out.print("Spare Shuttles: ");
		currentShip.spareShuttles = Driver.keyboard.nextLine();
		System.out.print("Size Class: ");
		currentShip.sizeClass = Driver.keyboard.nextLine();
		System.out.print("Turn Mode: ");
		currentShip.turnMode = Driver.keyboard.nextLine().toUpperCase();
		System.out.print("Rule Number: ");
		currentShip.ruleNum = Driver.keyboard.nextLine().toUpperCase();
		System.out.print("Year in Service: ");
		currentShip.yearInService = Driver.keyboard.nextLine();
//		System.out.print("Dock Points:");
//		System.out.print("Explosion Strength:");

		System.out.println();
		System.out.println("Indicate how many boxes exist on the " + currentShip.name + " SSD for the following systems:");
		System.out.println();
		System.out.println("SYSTEM NAME");
				
		for (int numPart = 0; numPart <= 20; numPart++) {  //  Allow changes to all systems except Damage Control, Sensor, Scanner, Excess Damage
			if (currentShip.ssd[numPart].name == "Flag Bridge") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Flag Bridge = Security, Web, Displacement Device");
				System.out.println("---------------------------------------------------------");
			} else if (currentShip.ssd[numPart].name == "Torpedo") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Torpedo = Photon Torpedo, Disruptor Bolt, Plasma Torpedo,");
				System.out.println("          SFG, Fusion Beam, Tractor-Repulsor Beam");
				System.out.println("---------------------------------------------------------");
			} else if (currentShip.ssd[numPart].name == "Drone") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Drone = ADD, ESG, Hellbore, Plasmatic Pulsars,");
				System.out.println("        Power Absorbers");
				System.out.println("---------------------------------------------------------");
			} else if (currentShip.ssd[numPart].name == "Shuttle") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Shuttle = Fighter, Mine Andormedan Hangar");
				System.out.println("---------------------------------------------------------");
			} else if (currentShip.ssd[numPart].name == "Cargo") {
				System.out.println("---------------------------------------------------------");
				System.out.println("Cargo = Repair, Mine Rack");
				System.out.println("---------------------------------------------------------");
			} else if (currentShip.ssd[numPart].name.contains("Hull")) {
				System.out.println("---------------------------------------------------------");
				System.out.println("Front/Aft Hull = Repair");
				System.out.println("---------------------------------------------------------");
			}
			System.out.print(currentShip.ssd[numPart].name + ": ");
			currentShip.ssd[numPart].numOfThisPart = Driver.getNumberNoCancel(0, 100);
			currentShip.ssd[numPart].remaining = currentShip.ssd[numPart].numOfThisPart;
		}

		currentShip.ssd[21].numOfThisPart = damConNumBoxes;
		System.out.println("Damage Control: " + damConNumBoxes);
		
		currentShip.ssd[22].numOfThisPart = sensorNumBoxes;
		System.out.println("Sensor: " + sensorNumBoxes);

		currentShip.ssd[23].numOfThisPart = scannerNumBoxes;
		System.out.println("Scanner: " + scannerNumBoxes);
		
		System.out.print(currentShip.ssd[24].name + ": ");
		currentShip.ssd[24].numOfThisPart = Driver.getNumberNoCancel(0, 100);
		currentShip.ssd[24].remaining = currentShip.ssd[24].numOfThisPart;		
		
		GetDamConSensorScannerNumbers ("Sensor", newShipNum);

		GetDamConSensorScannerNumbers ("Scanner", newShipNum);

		GetDamConSensorScannerNumbers ("Damage Control", newShipNum);
		
		boolean cont = true;
		while(cont) {
			System.out.println("=======================================================================");
			System.out.println("Race: " + currentShip.race);
			System.out.println("Ship Type: " + currentShip.shipType);
			System.out.println("Ship Name: " + currentShip.name);
			System.out.println("Crew Units: " + currentShip.crewUnits);
			System.out.println("Boarding Parties: " + currentShip.boardingParties);
			System.out.println("BPV: " + currentShip.BPV);
			System.out.println("Break Down: " + currentShip.breakDown);
			System.out.println("Move Cost: " + currentShip.moveCost);
			System.out.println("Spare Shuttles: " + currentShip.spareShuttles);
			System.out.println("Size Class: " + currentShip.sizeClass);
			System.out.println("Turn Mode: " + currentShip.turnMode);
			System.out.println("Rule Number: " + currentShip.ruleNum);
			System.out.println("Year in Service: " + currentShip.yearInService);
			System.out.println();
			for (int numPart = 0; numPart <= 24; numPart++) {
				String extraSpace = ShipSetup.getExtraSpaces(numPart, 2);
				System.out.print(extraSpace + (numPart) + ") " + currentShip.ssd[numPart].name + ": " + currentShip.ssd[numPart].numOfThisPart);
				
				if (numPart == 21) {
					System.out.print(" [");
					for (int i = 0; i < currentShip.ssd[21].numOfThisPart; i++) {		// Scanner Numbers
						System.out.print(currentShip.damConNums[i]);
						if (i < currentShip.ssd[21].numOfThisPart - 1) {
							System.out.print(" ");
						}
					}
					System.out.print("]");
				} else if (numPart == 22) {
					System.out.print(" [");
					for (int i = 0; i < currentShip.ssd[22].numOfThisPart; i++) {		// Scanner Numbers
						System.out.print(currentShip.sensorNums[i]);
						if (i < currentShip.ssd[22].numOfThisPart - 1) {
							System.out.print(" ");
						}
					}
					System.out.print("]");
				} else if (numPart == 23) {
					System.out.print(" [");
					for (int i = 0; i < currentShip.ssd[23].numOfThisPart; i++) {		// Scanner Numbers
						System.out.print(currentShip.scannerNums[i]);
						if (i < currentShip.ssd[23].numOfThisPart - 1) {
							System.out.print(" ");
						}
					}
					System.out.print("]");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.print("Is all the above information correct?");
			String correct = Driver.getInputNoCancel("YN");
			
			if (correct.equalsIgnoreCase("N")) {
				System.out.print("What is incorrect: Ship [I]nfo or [S]SD System Number?");
				String whatIsIncorrect = Driver.getInputNoCancel("IS");
				if (whatIsIncorrect.equalsIgnoreCase("S")) {
					ChangeShipSSDSystems(newShipNum);
				} else {
					ChangeShipInfo(newShipNum);
				}
			} else {
				cont = false;
			}
		}
				
		System.out.println();
		ShipSetup.PrintCurrentThingsInGame("Ship", "");
		System.out.println();
		
		SendToTextFile(newShipNum);
		
	}
	
	
	
	
	
	
	public static void GetDamConSensorScannerNumbers(String unit, int newShipNum) {
		System.out.println();
		System.out.println("Enter " + unit + " Numbers:");
		
		if (unit.equalsIgnoreCase("Damage Control")) {
//			System.out.println("Driver.currentGameYard.list[newShipNum].ssd[21].numOfThisPart: " + Driver.currentGameYard.list[newShipNum].ssd[21].numOfThisPart);
			for (int i = 0; i < Driver.currentGameYard.list[newShipNum].ssd[21].numOfThisPart; i++) {
				System.out.print("Box " + (i+1) + ": ");
				int damConInput = -1;
				while(damConInput < 0) {
					damConInput = Driver.getNumber(0,20);
				}
				Driver.currentGameYard.list[newShipNum].damConNums[i] = damConInput;
			}
			
		} else if (unit.equalsIgnoreCase("Sensor")) {
//			System.out.println("Driver.currentGameYard.list[newShipNum].ssd[22].numOfThisPart: " + Driver.currentGameYard.list[newShipNum].ssd[22].numOfThisPart);
			for (int i = 0; i < Driver.currentGameYard.list[newShipNum].ssd[22].numOfThisPart; i++) {
				System.out.print("Box " + (i+1) + ": ");
				int sensorInput = -1;
				while(sensorInput < 0) {
					sensorInput = Driver.getNumber(0,20);
				}
				Driver.currentGameYard.list[newShipNum].sensorNums[i] = sensorInput;
			}
			
		} else if (unit.equalsIgnoreCase("Scanner")) {
//			System.out.println("Driver.currentGameYard.list[newShipNum].ssd[23].numOfThisPart: " + Driver.currentGameYard.list[newShipNum].ssd[23].numOfThisPart);
			for (int i = 0; i < Driver.currentGameYard.list[newShipNum].ssd[23].numOfThisPart; i++) {
				System.out.print("Box " + (i+1) + ": ");
				int scannerInput = -1;
				while(scannerInput < 0) {
					scannerInput = Driver.getNumber(0,20);
				}
				Driver.currentGameYard.list[newShipNum].scannerNums[i] = scannerInput;
			}
			
		}

	}
	
	
	
	
	
	
	public static void ChangeShipSSDSystems(int shipNumInput) {
		int systemNumToChange = 0;
		int newAmount = 0;
		
		while (systemNumToChange != -1) {
			System.out.println();
			System.out.print("System number to change [RETURN to cancel]: ");
			systemNumToChange = Driver.getNumber(0, 24);
			if (systemNumToChange != -1) {
				if ((systemNumToChange >= 0 && systemNumToChange <= 20) || systemNumToChange == 24) {
					System.out.print("New number for " + Driver.currentGameYard.list[shipNumInput].ssd[systemNumToChange].name.toUpperCase() + " SSD boxes: ");
					newAmount = Driver.getNumber(0, 24);
					Driver.currentGameYard.list[shipNumInput].ssd[systemNumToChange].numOfThisPart = newAmount;
					Driver.currentGameYard.list[shipNumInput].ssd[systemNumToChange].remaining = newAmount;
				} else if (systemNumToChange == 21) {
					GetDamConSensorScannerNumbers ("Damage Control", shipNumInput);
				} else if (systemNumToChange == 22) {
					GetDamConSensorScannerNumbers ("Sensor", shipNumInput);
				} else if (systemNumToChange == 23) {
					GetDamConSensorScannerNumbers ("Scanner", shipNumInput);
					
				}
			}
		}
	}
	
	
	
	
	
	
	public static void ChangeShipInfo(int newShipNum) {

		Starship currentShip = Driver.currentGameYard.list[newShipNum];
		
		System.out.print("Race (" + currentShip.race + "): ");
		String raceInput = Driver.keyboard.nextLine();
		if (raceInput.length() != 0) {
			currentShip.race = raceInput;
		}
		
		System.out.print("Ship Type (" + currentShip.shipType + "): ");
		String typeInput = Driver.keyboard.nextLine().toUpperCase();
		if (typeInput.length() != 0) {
			currentShip.shipType = typeInput;
		}

		currentShip.name = currentShip.race.substring(0, 1).toUpperCase() + currentShip.race.substring(1, 3) + "-" + currentShip.shipType;
		System.out.println("Ship Name = " + currentShip.name);

		System.out.print("Crew Units (" + currentShip.crewUnits + "): ");
		String crewInput = Driver.keyboard.nextLine();
		if (crewInput.length() != 0) {
			currentShip.crewUnits = crewInput;
		}

		System.out.print("Boarding Parties (" + currentShip.boardingParties + "): ");
		String boardingInput = Driver.keyboard.nextLine();
		if (boardingInput.length() != 0) {
			currentShip.boardingParties = boardingInput;
		}

		System.out.print("BPV (" + currentShip.BPV + "): ");
		String bpvInput = Driver.keyboard.nextLine();
		if (bpvInput.length() != 0) {
			currentShip.BPV = bpvInput;
		}

		System.out.print("Break Down (" + currentShip.breakDown + "): ");
		String breakInput = Driver.keyboard.nextLine();
		if (breakInput.length() != 0) {
			currentShip.breakDown = breakInput;
		}

		System.out.print("Move Cost (" + currentShip.moveCost + "): ");
		String moveInput = Driver.keyboard.nextLine();
		if (moveInput.length() != 0) {
			currentShip.moveCost = moveInput;
		}

		System.out.print("Spare Shuttles (" + currentShip.spareShuttles + "): ");
		String shuttleInput = Driver.keyboard.nextLine();
		if (shuttleInput.length() != 0) {
			currentShip.spareShuttles = shuttleInput;
		}

		System.out.print("Size Class (" + currentShip.sizeClass + "): ");
		String sizeInput = Driver.keyboard.nextLine();
		if (sizeInput.length() != 0) {
			currentShip.sizeClass = sizeInput;
		}
		
		System.out.print("Turn Mode (" + currentShip.turnMode + "): ");
		String turnInput = Driver.keyboard.nextLine().toUpperCase();
		if (turnInput.length() != 0) {
			currentShip.turnMode = turnInput;
		}
		
		System.out.print("Rule Number (" + currentShip.ruleNum + "): ");
		String ruleInput = Driver.keyboard.nextLine();
		if (ruleInput.length() != 0) {
			currentShip.ruleNum = ruleInput;
		}
		
		System.out.print("Year in Service (" + currentShip.yearInService + "): ");
		String yearInput = Driver.keyboard.nextLine();
		if (yearInput.length() != 0) {
			currentShip.yearInService = yearInput;
		}
		
//		System.out.print("Dock Points:");
//		System.out.print("Explosion Strength:");
		
	}
	
	
	
	
	
	
    public static void SendToTextFile(int newShipNum) throws IOException {

		Starship currentShip = Driver.currentGameYard.list[newShipNum];
		
		File file = new File("./AddedShips.txt");
        Scanner scan = new Scanner(System.in);
        
        FileWriter writer = new FileWriter(file, true);

        writer.write("ship = new Starship(");
        writer.write("\"" + currentShip.race + "\", ");
        writer.write("\"" + currentShip.shipType + "\", ");
        writer.write("\"" + currentShip.crewUnits + "\", ");
        writer.write("\"" + currentShip.boardingParties + "\", ");
        writer.write("\"" + currentShip.BPV + "\", ");
        writer.write("\"" + currentShip.breakDown + "\", ");
        writer.write("\"" + currentShip.moveCost + "\", ");
        writer.write("\"" + currentShip.spareShuttles + "\", ");
        writer.write("\"" + currentShip.sizeClass + "\", ");
        writer.write("\"" + currentShip.turnMode + "\", ");
        writer.write("\"" + currentShip.ruleNum + "\", ");
        writer.write("\"" + currentShip.yearInService + "\"");
        writer.write(");");
        
        writer.write(System.getProperty("line.separator"));
        
        writer.write("se = new int[]{");
        for (int i = 0; i < currentShip.ssd[22].numOfThisPart; i++) {
        	writer.write(String.valueOf(currentShip.sensorNums[i]));
        	if (i <= currentShip.ssd[22].numOfThisPart-2) {
        		writer.write(", ");
        	}
        }
        writer.write("};");
        
        writer.write(System.getProperty("line.separator"));
        
        writer.write("sc = new int[]{");
        for (int i = 0; i < currentShip.ssd[23].numOfThisPart; i++) {
        	writer.write(String.valueOf(currentShip.scannerNums[i]));
        	if (i <= currentShip.ssd[23].numOfThisPart-2) {
        		writer.write(", ");
        	}
        }
        writer.write("};");
        
        writer.write(System.getProperty("line.separator"));
        
        writer.write("da = new int[]{");
		for (int i = 0; i < currentShip.ssd[21].numOfThisPart; i++) {
			writer.write(String.valueOf(currentShip.damConNums[i]));
			if (i <= currentShip.ssd[21].numOfThisPart-2) {
				writer.write(", ");
			}
		}
        writer.write("};");
        
		writer.write(System.getProperty("line.separator"));
        
		writer.write("ship.setupSSD(");
        for (int i = 0; i <= 24; i++) {
        	writer.write(String.valueOf(currentShip.ssd[i].numOfThisPart));
        	if (i <= 23) {
        		writer.write(", ");
        	}
        }
        writer.write(", se, sc, da);");
        
        writer.write(System.getProperty("line.separator"));

        writer.write("defaultYard.addShipToShipyard(ship);");
        
        writer.write(System.getProperty("line.separator"));
        writer.write(System.getProperty("line.separator"));
        
        writer.close();
    }
    
    
    
    
    
    
//    public static void SendToTextFileOld(int newShipNum) {
//
//    	Starship currentShip = Driver.currentGameYard.list[newShipNum];
//
//        try
//        {
//            // Save original out stream.
//            PrintStream originalOut = System.out;
////            // Save original err stream.
////            PrintStream originalErr = System.err;
//
//            // Create a new file output stream.
//            PrintStream fileOut = new PrintStream("./out.txt");
////            // Create a new file error stream. 
////            PrintStream fileErr = new PrintStream("./err.txt");
//
//            // Redirect standard out to file.
//            System.setOut(fileOut);
////            // Redirect standard err to file.
////            System.setErr(fileErr);
//
//            // Wrapped Scanner to get user input.
//            Scanner scanner = new Scanner(System.in);
//
//            fileOut.print("\"" + currentShip.race + "\", ");
//            fileOut.print("\"" + currentShip.shipType + "\", ");
//            fileOut.print("\"" + currentShip.name + "\", ");
//            fileOut.print("\"" + currentShip.crewUnits + "\", ");
//            fileOut.print("\"" + currentShip.boardingParties + "\", ");
//            fileOut.print("\"" + currentShip.BPV + "\", ");
//            fileOut.print("\"" + currentShip.breakDown + "\", ");
//            fileOut.print("\"" + currentShip.moveCost + "\", ");
//            fileOut.print("\"" + currentShip.spareShuttles + "\", ");
//            fileOut.print("\"" + currentShip.sizeClass + "\", ");
//            fileOut.print("\"" + currentShip.turnMode + "\", ");
//            fileOut.print("\"" + currentShip.ruleNum + "\", ");
//            fileOut.print("\"" + currentShip.yearInService + "\"");
//            fileOut.println();
//            
//            for (int i = 0; i < currentShip.ssd[21].numOfThisPart; i++) {
//            	fileOut.print(currentShip.damConNums[i]);
//            	if (i <= currentShip.ssd[21].numOfThisPart-2) {
//            		fileOut.print(", ");
//            	}
//            }
//            fileOut.println();
//            
//            for (int i = 0; i < currentShip.ssd[22].numOfThisPart; i++) {
//            	fileOut.print(currentShip.sensorNums[i]);
//            	if (i <= currentShip.ssd[22].numOfThisPart-2) {
//            		fileOut.print(", ");
//            	}
//            }
//            fileOut.println();
//            
//            for (int i = 0; i < currentShip.ssd[23].numOfThisPart; i++) {
//            	fileOut.print(currentShip.scannerNums[i]);
//            	if (i <= currentShip.ssd[23].numOfThisPart-2) {
//            		fileOut.print(", ");
//            	}
//            }
//            fileOut.println();
//            
//            for (int i = 0; i <= 24; i++) {
//            	fileOut.print(currentShip.ssd[i].numOfThisPart);
//            	if (i <= 23) {
//            		fileOut.print(", ");
//            	}
//            }
//            fileOut.println();
//            
//            System.setOut(originalOut);
//
//        } catch(FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        
//    }
}
