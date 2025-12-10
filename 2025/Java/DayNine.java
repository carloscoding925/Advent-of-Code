import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayNine {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayNine.txt"));

            List<long[]> coordinates = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                long col = Long.parseLong(parts[0]);
                long row = Long.parseLong(parts[1]);
                coordinates.add(new long[]{col, row});
            }

            long maxRectangle = 0;
            for (int i = 0; i < coordinates.size(); i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    long[] corner = coordinates.get(i);
                    long[] otherCorner = coordinates.get(j);

                    long width = Math.abs(corner[0] - otherCorner[0]) + 1;
                    long height = Math.abs(corner[1] - otherCorner[1]) + 1;
                    long area = width * height;

                    maxRectangle = Math.max(maxRectangle, area);
                }
            }

            System.out.println("Part 1 Output: " + maxRectangle);
            // Should be: 4764078684
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
