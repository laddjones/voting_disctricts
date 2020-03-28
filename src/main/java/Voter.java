/**
 * Represents a Voter object
 * @author Ladd Jones
 * @version 100.0 Dec. 4 2015
 */
public class Voter {
    private String name;
    private String vote1;
    private String vote2;
    private String vote3;

    /**
     * Instantiates a Voter object
     * @param name the name of the voter
     * @param vote1 the candidate voted for first by this person
     * @param vote2 the candidate voted for second by this person
     * @param vote3 the candidate voted for third by this person
     */
    public Voter(String name, String vote1, String vote2, String vote3) {
        this.name = name;
        this.vote1 = vote1;
        this.vote2 = vote2;
        this.vote3 = vote3;
    }

    /**
     * Gets the name
     * @return name the name of the voter
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the first vote
     * @return vote1 the first vote
     */
    public String getVote1() {
        return vote1;
    }

    /**
     * Gets the second vote
     * @return vote2 the second vote
     */
    public String getVote2() {
        return vote2;
    }

    /**
     * Gets the third vote
     * @return vote3 the third vote
     */
    public String getVote3() {
        return vote3;
    }

    /**
     * return the name and all the votes
     * @return name the nam e
     * @return vote2 the first vote
     * @return vote2 the second vote
     * @return vote3 the third vote
     */
    @Override
    public String toString() {
        return name + " " + vote1 + " " + vote2 + " " + vote3 + " ";
    }

}
