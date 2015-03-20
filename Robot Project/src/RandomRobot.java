import java.util.Random;

/**
 * The RRandomRobot is a subclass of the Robot class.
 * This robot moves along the walls with its right hand always touching, to find a way out of the maze. 
 *@author Andrea Gonzales, Stephanie Kong, Yasmin Riaz
 */
public class RandomRobot extends Robot{

	public RandomRobot(Maze theMaze) {
		super(theMaze);
	}

	/**
	 * steps the robot in a direction. If it hits a wall or intersection, it selects an available direction
	 */
	public void move() {
		Location adjacent = getCurrentLocation().getAdjacentLocationTowards(getFacingDirection());
		if (inMazeisWallisEntrance(adjacent)) { //if it hits a wall
			Direction random = getRandomDirection();
			adjacent = getCurrentLocation().getAdjacentLocationTowards(random);
			
			if (!inMazeisWallisEntrance(adjacent)) {
				setCurrentLocation(adjacent);
				setFacingDirection(random);
			}
			
		} else {
			setCurrentLocation(adjacent);
		}
	}
	
	/**
	 * 
	 * @param loc
	 * @return if the given location isValid() and is a wall or the maze entrance
	 */
	public boolean inMazeisWallisEntrance(Location loc) {
		if (getMaze().isValid(loc) && getMaze().isWall(loc) || loc.equals(getMaze().getMazeEntrance())) { //if it hits a wall
			return true;			
		} else {
			return false;			
		}
	}
	
	/**
	 * 
	 * @param loc
	 * @return true if the given location is an intersection of the maze
	 */
	public boolean isIntersection(Location loc) {
		int wallCount = 0;
		for(Direction dir: Direction.values()) {
			Location adjacentLocation = loc.getAdjacentLocationTowards(dir);
			if (getMaze().isValid(adjacentLocation) 
					&& getMaze().getMazeGrid()[adjacentLocation.getYCoordinate()][adjacentLocation.getXCoordinate()]) {
				wallCount++;
			}
		}
		
		if (wallCount < 2) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return random direction, defaults to North
	 */
	public Direction getRandomDirection() {
		Random r = new Random();
		int newInt = r.nextInt(4);
		switch (newInt) {
			case 0:
				return Direction.NORTH;
			case 1:
				return Direction.EAST;
			case 2:
				return Direction.SOUTH;
			case 3:
				return Direction.WEST;
			default: 
				return Direction.NORTH;
		}
	}
}
