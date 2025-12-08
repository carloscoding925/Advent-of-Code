import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
            int numsSize = nums.size();

            String operationString = lines.getLast();
            String[] operations = operationString.trim().split("\\s+");
            long total = 0;

            for (int j = 0; j < operations.length; j++) {
                long result = 0;

                if (operations[j].equals("*")) {
                    result = Integer.parseInt(nums.get(0)[j]);

                    for (int k = 1; k < numsSize; k++) {
                        result = result * Integer.parseInt(nums.get(k)[j]);
                    }
                }
                else {
                    result = Integer.parseInt(nums.get(0)[j]);

                    for (int k = 1; k < numsSize; k++) {
                        result = result + Integer.parseInt(nums.get(k)[j]);
                    }
                }

                total = total + result;
            }

            System.out.println("Part 1 Total: " + total);
            // Should be: 5361735137219
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySix.txt"));
            int inputLength = lines.size();

            List<String> nums = new ArrayList<>();
            for (int i = 0; i < inputLength - 1; i++) {
                nums.add(lines.get(i));
            }

            String operationString = lines.getLast();
            int maxLength = operationString.length();
            long total = 0;

            System.out.println("Part 2 Total: " + total);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
