import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFive {
    public static void main() {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayFive.txt"));

            List<String> ranges = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();

            boolean hitDelimiter = false;

            for (String line : lines) {
                if (line.isBlank()) {
                    hitDelimiter = true;
                    continue;
                }

                if (!hitDelimiter) {
                    ranges.add(line);
                }
                else {
                    ids.add(Integer.parseInt(line));
                }
            }

            System.out.println(ranges.toString() + " " + ids.toString());
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
