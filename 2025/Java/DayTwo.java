import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayTwo {
    public static void main(String[] args) {
        solvePartOne();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTwo.txt"));

            List<String> allRanges = new ArrayList<>();
            for (String line : lines) {
                String[] ranges = line.split(",");

                for (String range : ranges) {
                    if (!range.trim().isEmpty()) {
                        allRanges.add(range.trim());
                    }
                }
            }

            long sum = 0;
            for (String range : allRanges) {
                String[] ids = range.split("-");
                long left = Long.parseLong(ids[0]);
                long right = Long.parseLong(ids[1]);

                while (left <= right) {
                    String idString = String.valueOf(left);
                    int idLength = idString.length();

                    if (idLength == 1 || (idLength % 2 != 0)) {
                        left++;
                        continue;
                    }

                    int start = 0;
                    int halfLength = idLength / 2;
                    boolean isSymmetric = true;

                    while (halfLength < idLength) {
                        if (idString.charAt(start) != idString.charAt(halfLength)) {
                            isSymmetric = false;
                            break;
                        }
                        
                        start++;
                        halfLength++;
                    }

                    if (isSymmetric) {
                        sum = sum + left;
                    }

                    left++;
                }
            }

            System.out.println("Total Sum: " + sum);
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
