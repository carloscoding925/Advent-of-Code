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
            
            List<long[]> ranges = new ArrayList<>();
            for (String line : lines) {
                if (line.isBlank()) {
                    break;
                }

                String[] parts = line.split("-");
                long start = Long.parseLong(parts[0]);
                long end = Long.parseLong(parts[1]);
                ranges.add(new long[]{start, end});
            }

            ranges.sort((a, b) -> Long.compare(a[0], b[0]));

            long totalIds = 0;
            long currStart = ranges.get(0)[0];
            long currEnd = ranges.get(0)[1];

            for (int i = 1; i < ranges.size(); i++) {
                long nextStart = ranges.get(i)[0];
                long nextEnd = ranges.get(i)[1];

                if (nextStart <= currEnd + 1) {
                    currEnd = Math.max(currEnd, nextEnd);
                }
                else {
                    totalIds = totalIds + (currEnd - currStart + 1);
                    currStart = nextStart;
                    currEnd = nextEnd;
                }
            }

            System.out.println("Part 2 Count: " + totalIds);
            // Should be: 363407946183085
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
