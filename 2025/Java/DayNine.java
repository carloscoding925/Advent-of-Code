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

            List<int[]> coordinates = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                coordinates.add(new int[]{row, col});
            }

            long maxRectangle = 0;
            for (int i = 0; i < coordinates.size(); i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    int[] corner = coordinates.get(i);
                    int[] otherCorner = coordinates.get(j);

                    int width = Math.abs(corner[0] - otherCorner[0]) + 1;
                    int height = Math.abs(corner[1] - otherCorner[1]) + 1;
                    long area = width * height;

                    maxRectangle = Math.max(maxRectangle, area);
                }
            }

            System.out.println("Part 1 Output: " + maxRectangle);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
