import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a driver class that tests and runs the election
 * @author Ladd Jones
 * @version 100.0 Dec. 4 2015
 */
public class Tester {
    private static Map<String, Voter> mapOfVoters =
        new HashMap<String, Voter>();
    private static Map<String, District> mapOfLocalDistricts =
        new HashMap<String, District>();
    private static Map<String, District> mapOfCompositeDistricts =
        new HashMap<String, District>();
    private static List<Integer> listToSort = new ArrayList<Integer>();
    /**
     * the main method parses the files and puts the data in maps, the files
     * must be formatted in the same way that the test files were with composite
     * districts inside of composite districts lowed down in
     * CompositeDistrict.txt
     * @param args : files entered-voter.txt, local_districts.txt, districts.txt
     * @throws IOException in the case that there is a file reading misfunction
     */
    public static void main(String[] args) throws IOException {
        //System.out.println("Enter the file with your list of voters and
        //their votes");
        String theirVoterInput = args[0];
        Scanner scan1 = new Scanner(new File(theirVoterInput));

        while (scan1.hasNextLine()) {
            String line = scan1.nextLine();
            String[] firstSplit = line.split(":");
            String voterName = firstSplit[0];
            String[] votes = firstSplit[1].split(",");
            String firstVote = votes[0];
            String secondVote = votes[1];
            String thirdVote = votes[2];
            Voter voter = new Voter(voterName, firstVote, secondVote,
                thirdVote);
            mapOfVoters.put(voterName, voter);

        }
        String theirLocalDist = args[1];
        Scanner scan2 = new Scanner(new File(theirLocalDist));
        while (scan2.hasNextLine()) {
            String line2 = scan2.nextLine();
            String[] firstSplit2 = line2.split(":");
            String localdistrictName = firstSplit2[0].trim();
            String[] secondPart = firstSplit2[1].split(",");
            ArrayList<Voter> votersofDistrict = new ArrayList<Voter>();
            for (int i = 0; i < secondPart.length; i++) {
                if (mapOfVoters.containsKey(secondPart[i].trim())) {
                    votersofDistrict.add(mapOfVoters.get(secondPart[i]
                        .trim()));
                }
            }
            LocalDistrict oneDistrict = new LocalDistrict(localdistrictName,
                votersofDistrict);
            mapOfLocalDistricts.put(localdistrictName, oneDistrict);
        }
        String theirDist2 = args[2];
        Scanner scan3 = new Scanner(new File(theirDist2));
        scan3.nextLine();
        while (scan3.hasNextLine()) {
            String line3 = scan3.nextLine();
            String[] firstSplit2 = line3.split(":");
            String compositeDistrictName = firstSplit2[0].trim();
            String[] secondPart = firstSplit2[1].split(",");
            List<District> allSubDistricts = new ArrayList<District>();
            for (int i = 0; i < secondPart.length; i++) {
                if (mapOfLocalDistricts.get(secondPart[i].trim()) != null) {
                    allSubDistricts.add(mapOfLocalDistricts
                        .get(secondPart[i].trim()));
                } else {
                    allSubDistricts.add(mapOfCompositeDistricts
                        .get(secondPart[i].trim()));
                }
            }
            CompositeDistrict newCompositeDist =
                new CompositeDistrict(compositeDistrictName,
                allSubDistricts);
            mapOfCompositeDistricts.put(compositeDistrictName,
                newCompositeDist);
        }

        System.out.println("---------Here is a list of voters and the "
            + "candidate they voted for------------");
        System.out.println(mapOfVoters.values());
        System.out.println("");
        System.out.println("-------Here is a list of local districts, "
            + "followed by the winner from each and the number of votes "
            + "they recieved------------");
        System.out.println(mapOfLocalDistricts);
        System.out.println("");
        System.out.println("------Here is a list of composite districts, "
            + "followed by the winner from each and the size of the "
            + "district---------------");
        System.out.println(mapOfCompositeDistricts);
        System.out.println("");
        System.out.println("------Here is a sorted list of the number of "
            + "votes from each local district---------------");
        System.out.println(mergeSort(putSizeInAL()));
        System.out.println("");
        System.out.println("********** Final WinningCandidate **********");
        System.out.println(getFinalWinner());


    }

    /**
     * gets the final winner from the districts
     * @return winner the final winner
     */
    public static String getFinalWinner() {
        int maxVoters = 0;
        String winner = "";
        for (Map.Entry<String, District> element
            : mapOfCompositeDistricts.entrySet()) {
            if (element.getValue().getVotersInDist() > maxVoters) {
                maxVoters = element.getValue().getVotersInDist();
                winner = element.getValue().getWinner();
            }
        }
        return winner;
    }

    /**
     * Puts the number of voters in each local distric into an arrayList
     * @return listToSort the list that you will sort in mergeSort
     */
    public static List<Integer> putSizeInAL() {
        for (Map.Entry<String, District> element
            : mapOfLocalDistricts.entrySet()) {
            listToSort.add(element.getValue().getVotersInDist());
        }
        return listToSort;
    }

    /**
     * Sorts a list of numbers form lowest to highest
     * @param listToSplit the list that you want to mergeSort
     * @return sortedArray the sorted arrayList
     */
    public static List<Integer> mergeSort(List<Integer> listToSplit) {
        if (listToSplit.size() == 1) {
            return listToSplit;
        }
        List<Integer> firstHalf = listToSplit.subList(0,
            listToSplit.size() / 2);
        List<Integer> secondHalf = listToSplit.subList(listToSplit.size() / 2,
            listToSplit.size());
        List<Integer> firstMrg = mergeSort(firstHalf);
        List<Integer> secondMrg = mergeSort(secondHalf);
        List<Integer> sortedArray = new ArrayList<Integer>();
        int pointer1 = 0;
        int pointer2 = 0;
        while (pointer1 < firstMrg.size() && pointer2 < secondMrg.size()) {
            if (firstMrg.get(pointer1) < secondMrg.get(pointer2)) {
                sortedArray.add(firstMrg.get(pointer1));
                pointer1++;
            } else {
                sortedArray.add(secondMrg.get(pointer2));
                pointer2++;
            }
        }
        while (pointer1 < firstMrg.size()) {
            sortedArray.add(firstMrg.get(pointer1));
            pointer1++;
        }
        while (pointer2 < secondMrg.size()) {
            sortedArray.add(secondMrg.get(pointer2));
            pointer2++;
        }
        return sortedArray;
    }
}
