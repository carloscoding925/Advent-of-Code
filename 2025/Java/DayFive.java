import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFive {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayFive.txt"));

            List<String> ranges = new ArrayList<>();
            List<Long> ids = new ArrayList<>();

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
                    ids.add(Long.parseLong(line));
                }
            }

            int freshIngredients = 0;

            for (long id : ids) {
                boolean isFresh = false;

                for (String range : ranges) {
                    String[] parts = range.split("-");
                    long low = Long.parseLong(parts[0]);
                    long high = Long.parseLong(parts[1]);

                    if (isFresh) {
                        continue;
                    }

                    if (id <= high && id >= low) {
                        isFresh = true;
                    }
                }

                if (isFresh) {
                    freshIngredients++;
                }
            }

            System.out.println("Part 1 Count: " + freshIngredients);
            // Should be: 635
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayFive.txt"));
            long totalIds = 0;

            for (String line : lines) {
                if (line.isBlank()) {
                    break;
                }

                
            }

            System.out.println("Part 2 Count: " + totalIds);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
