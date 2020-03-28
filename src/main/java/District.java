/**
 * Represents a abstract District class
 * @author Ladd Jones
 * @version 100.0 Dec. 4 2015
 */
public abstract class District {

    /**
     * Gets the winner
     * @return String the winner
     */
    public abstract String getWinner();

    /**
     * Gets the number of voters in a District
     * @return int containing the number of voters in the district
     */
    public abstract int getVotersInDist();

    /**
     * Gets the name of the District
     * @return String the name
     */
    public abstract String getName();

    /**
     * Overrides the toString
     * @return String the override toString
     */
    public abstract String toString();
}
