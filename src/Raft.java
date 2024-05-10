import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Raft {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int numberOfGoats = scanner.nextInt();
        int maxCourses = scanner.nextInt();
        List<Integer> weightsOfGoats = new ArrayList<>();
        for (int i = 0; i < numberOfGoats; i++) {
            int inputedWeight = scanner.nextInt();
            weightsOfGoats.add(inputedWeight);
        }
        Collections.sort(weightsOfGoats, Collections.reverseOrder());

        int minCapacity = Math.max(setFirstMinCapacity(weightsOfGoats),
                setEstimatedMinCapacity(weightsOfGoats, maxCourses));

        System.out.println(calculateResult(weightsOfGoats, maxCourses, minCapacity));

        scanner.close();
    }

    // Setting first minimal capacity of the raft, which is the biggest weight in the List
    public static int setFirstMinCapacity(List<Integer> weightsOfGoats) {
        int maxWeight = Collections.max(weightsOfGoats);
        return maxWeight;
    }

    // Finding some estimated capacity ( all weights combined / max number of courses)
    public static int setEstimatedMinCapacity(List<Integer> weightsOfGoats, int maxCourses) {
        int sum = 0;
        for (Integer together : weightsOfGoats) {
            sum += together;
        }
        int estimated = sum / maxCourses;
        return estimated;
    }

    // Removing all weights which have been used 
    public static void updateList(List<Integer> weightsOfGoats, List<Integer> elementsToRemove) {
        weightsOfGoats.remove(Collections.max(weightsOfGoats));
        for (Integer element : elementsToRemove) {
            weightsOfGoats.remove(element);
        }
        Collections.sort(weightsOfGoats, Collections.reverseOrder());

    }

    // Finding minimal capacity of the raft for inputed max ammount of courses using the given strategy
    public static int calculateResult(List<Integer> weightsOfGoats, int maxCourses, int minCapacity) {
        int i = 0;
        List<Integer> elementsToRemove = new ArrayList<>();
        while (i < maxCourses && weightsOfGoats.isEmpty() == false) {
            int sum = 0;
            outerLoop: for (int j = 0, k = 1; j + k < weightsOfGoats.size(); k++) {
                int currentWeight = weightsOfGoats.get(j);
                int nextWeight = weightsOfGoats.get(j + k);
                if (currentWeight + nextWeight <= minCapacity) {
                    sum = currentWeight + nextWeight;
                    elementsToRemove.add(nextWeight);
                    for (int x = 1; k + x < weightsOfGoats.size(); x++) {
                        int nextNextWeight = weightsOfGoats.get(j + k + x);
                        if (sum + nextNextWeight <= minCapacity) {
                            sum += nextNextWeight;
                            elementsToRemove.add(nextNextWeight);
                        }
                    }
                    break outerLoop;
                }

            }

            updateList(weightsOfGoats, elementsToRemove);

            if (weightsOfGoats.isEmpty() == true) {
                break;
            }

            if (i == maxCourses - 1) {
                minCapacity = sum + Collections.max(weightsOfGoats);

            }

            i++;

        }

        return minCapacity;
    }
}
