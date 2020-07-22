import java.util.Random;

public class RandomMaze {
	public static int maze[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public static void CreateMaze() {
		int pathNum = 1;
		boolean done = false;
		boolean valid1 = false;
		boolean valid2 = false;
		int directionRoll = 0;
		int rollCount = 0;
		int forceDirectionCount = 0;
		int tryNum = 0;
		
		while (done == false) {
			ZeroOutMaze();
			tryNum++;
			System.out.println();
			System.out.print("Try #" + tryNum);
						
			int currentX = 1;
			int currentY = rollDice(1,20);
			maze[currentX][currentY] = 1;
			
//			System.out.println("currentX: " + currentX);
//			System.out.println("currentY: " + currentY);
//			System.out.println("maze: " + maze[currentX][currentY]);
			
			for (int i = 1; i <= 300; i++) {
				
				valid1 = false;
				valid2 = false;
				rollCount = 0;
				
				while (valid1 == false || valid2 == false) {
					rollCount++;
					if (rollCount > 100) {
						System.out.print("\tGot stuck.");
						pathNum = 1;
						i = 301;
//						String waitForReturn = Driver.getInput("");
						break;
					}
					directionRoll = rollDice(1,4);
					
					if (currentY == 1 || currentY == 20) {
						if (forceDirectionCount == 0) {
							directionRoll = 2;
							forceDirectionCount++;
						}
					}
					
					valid1 = Check1Away(directionRoll, currentX, currentY);
					valid2 = Check2Away(directionRoll, currentX, currentY);
				}
				
				if (rollCount < 100) {
//					System.out.println("Path Number: " + pathNum + "  DirectionRoll: " + directionRoll);
					pathNum++;
					
					if (directionRoll == 1) {
						currentY++;
					} else if (directionRoll == 2) {
						currentX++;
					} else if (directionRoll == 3) {
						currentY--;
					} else if (directionRoll == 4) {
						currentX--;
					}
					
					maze[currentX][currentY] = 1;
					
					
					if (currentX == 20) {
						if (pathNum > 75) {
							System.out.print("\tFinished!  Maze has " + pathNum + " sections.");
							System.out.println();
							PrintMaze();
							System.out.println();
							done = true;
							i = 301;
							pathNum = 1;
							String waitForReturn = Driver.getInput("");
						} else {
							System.out.print("\tPath under 75 sections.");
							i = 301;
							pathNum = 1;
//							String waitForReturn = Driver.getInput("");
						}
					}
					
//					if (i/10 == ((double) i) / 10.0) {
//						String waitForReturn = Driver.getInput("");
//					}
				}
			}
		}
	}	

	
	
	
	
	public static boolean Check1Away(int directionRoll, int currentX, int currentY) {
		boolean valid = true; 
		
		if (directionRoll == 1) {
			
			if (currentY + 1 >= 21 || maze[currentX][currentY + 1] == 1) {
				valid = false;
			} else {
				valid = true;
			}
			
		} else if (directionRoll == 2) {
			if (currentX + 1 >= 21 || maze[currentX + 1][currentY] == 1) {
				valid = false;
			} else {
				valid = true;
			}
			
		} else if (directionRoll == 3) {
			if (currentY - 1 <= 0 || maze[currentX][currentY - 1] == 1) {
				valid = false;
			} else {
				valid = true;
			}
			
		} else if (directionRoll == 4) {
			if (currentX - 1 <= 0 || maze[currentX - 1][currentY] == 1) {
				valid = false;
			} else {
				valid = true;
			}
		}

		return valid;
	}	
	
	
	
	
	public static boolean Check2Away(int directionRoll, int currentX, int currentY) {
		boolean valid = true; 
		
		if (directionRoll == 1) {
			if (currentY + 2 <= 21) {
				if (maze[currentX][currentY + 2] == 1 || maze[currentX - 1][currentY + 2] == 1 || maze[currentX + 1][currentY + 2] == 1 || maze[currentX - 1][currentY + 1] == 1 || maze[currentX + 1][currentY + 1] == 1) {
					valid = false;
				} else {
					valid = true;
				}
			}
			
		} else if (directionRoll == 2) {
			if (currentX + 2 <= 21) {
				if (maze[currentX + 2][currentY] == 1 || maze[currentX + 2][currentY - 1] == 1 || maze[currentX + 2][currentY + 1] == 1 || maze[currentX + 1][currentY - 1] == 1 || maze[currentX + 1][currentY + 1] == 1) {
					valid = false;
				} else {
					valid = true;
				}
			}
			
		} else if (directionRoll == 3) {
			if (currentY - 2 >= 0) {
				if (maze[currentX][currentY - 2] == 1 || maze[currentX - 1][currentY - 2] == 1 || maze[currentX + 1][currentY - 2] == 1 || maze[currentX - 1][currentY - 1] == 1 || maze[currentX + 1][currentY - 1] == 1) {
					valid = false;
				} else {
					valid = true;
				}
			}
			
		} else if (directionRoll == 4) {
			if (currentX - 2 >= 0) {
				if (maze[currentX - 2][currentY] == 1 || maze[currentX - 2][currentY - 1] == 1 || maze[currentX - 2][currentY + 1] == 1 || maze[currentX - 1][currentY - 1] == 1 || maze[currentX - 1][currentY + 1] == 1) {
					valid = false;
				} else {
					valid = true;
				}
			}
		}

		return valid;
	}
	
	
	
	
	public static void PrintMaze() {
		for (int y = 20; y >= 1; y--) {
			for (int x = 1; x <= 20; x++) {
				if (maze[x][y] == 0) {
					System.out.print(". ");
				} else if (maze[x][y] == 1) {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	
	public static void ZeroOutMaze() {
		for (int y = 21; y >= 0; y--) {
			for (int x = 0; x <= 21; x++) {
				maze[x][y] = 0;
			}
		}
	}
	
	
	
	
	
	public static int rollDice(int numOfDice, int numOfSides) {
		Random randomGenerator = new Random();
		int dieRoll;
		int totalDieRoll = 0;
		
		for(int i = 0; i < numOfDice; i++) {
			dieRoll = randomGenerator.nextInt(numOfSides) + 1;
			totalDieRoll += dieRoll;
		}
		
		
		if (Driver.TESTING) {
			System.out.println(numOfDice + "d" + numOfSides + ": " + totalDieRoll);
		}
		return totalDieRoll;
	}
}
