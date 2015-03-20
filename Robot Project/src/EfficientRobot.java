/**
 * EfficientRobot
 * @author Andrea Gonzales, Stephanie Kong, Yasmin Riaz
 * @version 3/19/15
 */

/**
 *A subclass of Robot that finds the most efficient path and uses it
 */
public class EfficientRobot extends Robot{
	
	private boolean[][] mazeGrid;
	
    /**
     * Creates a new EfficientRobot with the given maze.
     * @param theMaze the maze to traverse 
     * @pre maze is not null
     * Overrides constructor, sets random initial direction. 
     */
	public EfficientRobot(Maze theMaze) 
	{
		super(theMaze);
		
		mazeGrid = getMaze().getMazeGrid();
		analyzeMaze();
	}
	
	/**
	 * calls the fillToIntersection method on each coordinate in the maze
	 */
	private void analyzeMaze() {
		for (int x = 0; x < mazeGrid.length; x++) {
			System.out.println();
			for (int y = 0; y < mazeGrid[0].length; y++) {
				fillToIntersection(new Location(y,x));
			}
		}
		analysisTest();
	}
	
	/**
	 * Fills maze from a found deadEnd point to the nearest intersection
	 * @pre location is within the bounds of the initial maze wall
	 * @param loc
	 */
	private void fillToIntersection(Location loc) {
		Direction point = null;

		int wallCount = 0;
		for(Direction dir: Direction.values()) {
			Location adjacentLocation = loc.getAdjacentLocationTowards(dir);
			if (getMaze().isValid(adjacentLocation) 
					&& mazeGrid[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()]) {
				wallCount++;
			} else {
				point = dir;
			}
		}
		
		if (wallCount == 3) {
			mazeGrid[loc.getYCoordinate()][loc.getXCoordinate()] = true;
			fillToIntersection(loc.getAdjacentLocationTowards(point));
		}
	}
	
	/**
	 * FOR TESTING
	 * @return returns maze grid copy with filled-in dead ends as "*"s
	 */
	private void analysisTest() {
		System.out.println("TESTED ANALYSIS OF MAZE: ");
		for (int x = 0; x < mazeGrid.length; x++) {
			System.out.println();
			for (int y = 0; y < mazeGrid[0].length; y++) {
				if (mazeGrid[x][y]) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}				
			}
		}
	}
	
	/** moves the robot based on the false values in boolean[][] mazeGrid
	 * marks locations passed by the robot as true
	 * @pre the robotPath consists of locations adjacent to each other
	 */
	public void move() {
		Location adjacentLocation = getCurrentLocation().getAdjacentLocationTowards(getFacingDirection());
		
		if (!mazeGrid[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()]) {
			setCurrentLocation(adjacentLocation);
			mazeGrid[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()] = true; //so it does not go backwards
		} else {
			for (Direction dir: Direction.values()) {
				adjacentLocation = getCurrentLocation().getAdjacentLocationTowards(dir);
				
				if (!mazeGrid[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()]) {
					setFacingDirection(dir);
					setCurrentLocation(adjacentLocation);
					mazeGrid[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()] = true;
				}
			}
		}
	}
}