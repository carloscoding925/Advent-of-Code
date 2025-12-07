import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySix {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySix.txt"));
            int inputLength = lines.size();

            List<String[]> nums = new ArrayList<>();
            for (int i = 0; i < inputLength - 1; i++) {
                String[] lineValues = lines.get(i).trim().split("\\s+");
                nums.add(lineValues);
            }

            String operationString = lines.getLast();
            String[] operations = operationString.trim().split("\\s+");

            for (int i = 0; i < nums.size(); i++) {
                System.out.println(Arrays.toString(nums.get(i)));
            }
            System.out.println(Arrays.toString(operations));
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
