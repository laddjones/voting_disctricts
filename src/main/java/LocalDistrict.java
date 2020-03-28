import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a Local District object
 * @author Ladd Jones
 * @version 100.0 Dec. 4 2015
 */
public class LocalDistrict extends District {
    private String name;
    private List<Voter> voters;

    private Map<String, Integer> voteCount = new HashMap<String, Integer>();

    /**
     * Instantiates a local district object
     * @param name the name of the local district
     * @param voters an arraylist of the voters
     */
    public LocalDistrict(String name, ArrayList<Voter> voters) {
        this.name = name;
        this.voters = voters;
    }

    /**
     * gets the number of voteres in the district
     * @return voters.size() gets the size
     */
    public int getVotersInDist() {
        return voters.size();
    }

    /**
     * gets the name of the district
     * @return name gets the name
     */
    public String getName() {
        return name;
    }

    /**
     * returns a map of the vote count
     * @return voteCount a map of the vote count comprised of a string name and
     * an integer representation of the votes they got
     */
    public Map<String, Integer> getVoteCount() {
        for (Voter element: voters) {
            if ((voteCount.size() > 0)
                && voteCount.containsKey(element.getVote1())) {
                voteCount.put(element.getVote1(),
                    voteCount.get(element.getVote1()) + 3);
            } else {
                voteCount.put(element.getVote1(), 3);
            }
            if ((voteCount.size() > 0)
                && voteCount.containsKey(element.getVote2())) {
                voteCount.put(element.getVote2(),
                    voteCount.get(element.getVote2()) + 2);
            } else {
                voteCount.put(element.getVote2(), 2);
            }
            if ((voteCount.size() > 0)
                && voteCount.containsKey(element.getVote3())) {
                voteCount.put(element.getVote3(),
                    voteCount.get(element.getVote3()) + 1);
            } else {
                voteCount.put(element.getVote3(), 1);
            }
        }
        return voteCount;
    }

    /**
     * gets the name of the winner of the district
     * @return maxKey gets the name of the winner
     */
    public String getWinner() {
        getVoteCount();
        int maxValue = 0;
        String maxKey = "";
        for (Map.Entry<String, Integer> element : voteCount.entrySet()) {
            if (element.getValue() > maxValue) {
                maxValue = element.getValue();
                maxKey = element.getKey();
            }
        }
        return maxKey;
    }

    /**
     * prints the winner and the number of voters in the district
     * @return getWinner() getVotersInDist() gets the winner, the number of
     * voters in the district
     */
    public String toString() {
        return getWinner() + " - " + getVotersInDist();
    }
}
