/**
 * The RightHandRuleRobot is a subclass of the Robot class.
 * This robot moves along the walls with its right hand always touching, to find a way out of the maze. 
 * @author Stephanie Kong, Andrea Gonzales, Yasmin Riaz
 */
public class RightHandRuleRobot extends Robot
{
    /**
     * Constructor for objects of the RightHandRuleRobot class
     */
    public RightHandRuleRobot(Maze theMaze)
    {
        super(theMaze);
    }
    /**
     * This method moves the robot one step at a time with its right hand always against a wall by calling the setCurrentLocation and setFacingDirection methods. 
     */
    public void move()
    {
        Location nextPos = getCurrentLocation();
        Location frontPos = getCurrentLocation().getAdjacentLocationTowards(getFacingDirection());
        Location rightPos = getCurrentLocation().getAdjacentLocationTowards(rightHand());
        if (getMaze().isValid(rightPos) && !getMaze().isWall(rightPos))
        {
            setFacingDirection(rightHand());
            nextPos = rightPos;
        }
        else if (getMaze().isValid(frontPos) && !getMaze().isWall(frontPos))
        {
            nextPos = frontPos;
        }
       else 
        {
            setFacingDirection(leftHand());
        }
        setCurrentLocation(nextPos);
    }
    /**
     * Returns the left hand direction of the robot. 
     */
    public Direction leftHand()
    {
        if (getFacingDirection() == Direction.NORTH)
        {
            return Direction.WEST;
        }
        if (getFacingDirection() == Direction.WEST)
                {
            return Direction.SOUTH;
        }
        if (getFacingDirection() == Direction.SOUTH)
        {
            return Direction.EAST;
        }
        if (getFacingDirection() == Direction.EAST)
        {
            return Direction.NORTH;
        } 
        else 
        {
            return Direction.SOUTH;
        }
    }
    /**
     * Returns the right hand direction of the robot.
     */
    public Direction rightHand()
    {   if (getFacingDirection() == Direction.NORTH)
        {
            return Direction.EAST;
        }
        if (getFacingDirection() == Direction.EAST)
        {
            return Direction.SOUTH;
        }
        if (getFacingDirection() == Direction.SOUTH)
        {
            return Direction.WEST;
        }
        if (getFacingDirection() == Direction.WEST)
        {
            return Direction.NORTH;
        }
        else
        {
            return Direction.NORTH;
        }
    }
}
