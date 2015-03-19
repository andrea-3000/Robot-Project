import java.util.Random;


public class RandomRobot extends Robot{

	public RandomRobot(Maze theMaze) {
		super(theMaze);
	}

	public void move() {
		Location inFront = getCurrentLocation().getAdjacentLocationTowards(getFacingDirection());
		if (getMaze().isWall(inFront) || (inFront.equals(getMaze().getMazeEntrance())))
		{
			setRandomDirection();
		} else {
			setCurrentLocation(inFront);
		}
	}
	
	public void setRandomDirection() {
		Random r = new Random();
		int newInt = r.nextInt(4);
		switch (newInt) {
			case 0:
				setFacingDirection(Direction.NORTH);
				break;
			case 1:
				setFacingDirection(Direction.SOUTH);
				break;
			case 2:
				setFacingDirection(Direction.EAST);
				break;
			case 3:
				setFacingDirection(Direction.WEST);
				break;
		}
	}

}
