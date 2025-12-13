import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayTwelve {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTwelve.txt"));

            List<int[][]> presents = new ArrayList<>();
            int i = 0;

            while (i < lines.size() && !lines.get(i).contains("x")) {
                String line = lines.get(i).trim();

                if (line.matches("\\d+:")) {
                    List<int[]> cells = new ArrayList<>();

                    for (int row = 0; row < 3; row++) {
                        i++;
                        if (i < lines.size()) {
                            String gridLine = lines.get(i);

                            for (int col = 0; col < Math.min(3, gridLine.length()); col++) {
                                if (gridLine.charAt(col) == '#') {
                                    cells.add(new int[]{row, col});
                                }
                            }
                        }
                    }

                    int[][] presentArray = new int[cells.size()][2];
                    for (int j = 0; j < cells.size(); j++) {
                        presentArray[j] = cells.get(j);
                    }
                    presents.add(presentArray);
                }
                i++;
            }

            int count = 0;
            while (i < lines.size()) {
                String line = lines.get(i).trim();

                if (line.contains("x") && line.contains(":")) {
                    String[] parts = line.split(":");
                    String[] dims = parts[0].trim().split("x");
                    int width = Integer.parseInt(dims[0]);
                    int height = Integer.parseInt(dims[1]);

                    String[] countsString = parts[1].trim().split("\\s+");
                    int[] counts = new int[countsString.length];

                    for (int j = 0; j < countsString.length; j++) {
                        counts[j] = Integer.parseInt(countsString[j]);
                    }

                    List<int[][]> toPlace = new ArrayList<>();
                    for (int j = 0; j < counts.length; j++) {
                        for (int k = 0; k < counts[j]; k++) {
                            if (j < presents.size()) {
                                toPlace.add(presents.get(j));
                            }
                        }
                    }

                    boolean[][] grid = new boolean[height][width];
                    boolean success = canPlaceAll(grid, toPlace, 0);

                    if (success) {
                        count++;
                    }
                }

                i++;
            }

            System.out.println("Part 1 Count: " + count);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    static boolean canPlaceAll(boolean[][] grid, List<int[][]> presents, int index) {
        if (index == presents.size()) {
            return true;
        }

        int[][] present = presents.get(index);
        int height = grid.length;
        int width = grid[0].length;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (canPlace(grid, present, row, col)) {
                    place(grid, present, row, col, true);

                    if (canPlaceAll(grid, presents, index + 1)) {
                        return true;
                    }
                    place(grid, present, row, col, false);
                }
            }
        }

        return false;
    }

    static boolean canPlace(boolean[][] grid, int[][] present, int startRow, int startCol) {
        for (int[] cell : present) {
            int row = startRow + cell[0];
            int col = startCol + cell[1];

            if (row >= grid.length || col >= grid[0].length || grid[row][col]) {
                return false;
            }
        }

        return true;
    }

    static void place(boolean[][] grid, int[][] present, int startRow, int startCol, boolean mark) {
        for (int[] cell : present) {
            int row = startRow + cell[0];
            int col = startCol + cell[1];

            grid[row][col] = mark;
        }
    }

    private static void solvePartTwo() {

    }
}
