import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a Composite District object
 * @author Ladd Jones
 * @version 100.0 Dec. 4 2015
 */
public class CompositeDistrict extends District {

    private String nameOfDistrict;
    private List<District> listofDistWithIn;

    /**
     * Instantiates a CompositeDistrict object
     * @param nameOfDistrict the name of the Composite District
     * @param listofDistWithIn the list of the districts with the Composite
     * District
     */
    public CompositeDistrict(String nameOfDistrict, List<District>
        listofDistWithIn) {
        this.nameOfDistrict = nameOfDistrict;
        this.listofDistWithIn = listofDistWithIn;
    }

    /**
     * getter method for name
     * @return nameOfDistrict the name of the district
     */
    public String getName() {
        return nameOfDistrict;
    }

    /**
     * calculates the number of voters in a compostie district
     * @return size the number of voters in a compostie district
     */
    public int getVotersInDist() {
        int size = 0;
        for (District element : listofDistWithIn) {
            size += element.getVotersInDist();
        }
        return size;
    }

    /**
     * gets the winner of the district
     * @return maxKey the name of the winner
     */
    public String getWinner() {
        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        for (District element : listofDistWithIn) {
            if ((tempMap.size() > 0)
                && tempMap.containsKey(element.getWinner())) {
                int value = tempMap.get(element.getWinner());
                tempMap.put(element.getWinner(),
                    (value + element.getVotersInDist()));
            } else {
                tempMap.put(element.getWinner(), element.getVotersInDist());
            }
        }
        int maxValue = 0;
        String maxKey = "";
        for (Map.Entry<String, Integer> element : tempMap.entrySet()) {
            if (element.getValue() > maxValue) {
                maxValue = element.getValue();
                maxKey = element.getKey(); //getName();
            }
        }
        return maxKey;
    }

    /**
     * prints the winner of the district and the number of voters in the
     * @return getWinner() getVotersInDist gets the winner, gets the number
     * of people in district
     */
    public String toString() {
        return getWinner() + " - " + getVotersInDist();

    }
}
