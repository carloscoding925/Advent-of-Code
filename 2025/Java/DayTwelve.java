import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            List<List<int[][]>> allRotations = new ArrayList<>();
            for (int[][] present : presents) {
                allRotations.add(getUniqueRotations(present));
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

                    List<List<int[][]>> toPlace = new ArrayList<>();
                    for (int j = 0; j < counts.length; j++) {
                        for (int k = 0; k < counts[j]; k++) {
                            if (j < allRotations.size()) {
                                toPlace.add(allRotations.get(j));
                            }
                        }
                    }

                    int totalCells = toPlace.size() * 7;
                    int gridArea = width * height;

                    boolean success = false;
                    if (totalCells <= gridArea) {
                        boolean[][] grid = new boolean[height][width];
                        success = canPlaceAll(grid, toPlace, 0);
                    }

                    if (success) {
                        count++;
                    }
                }

                i++;
            }

            System.out.println("Part 1 Count: " + count);
            // Should be: 476
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    static List<int[][]> getUniqueRotations(int[][] present) {
        List<int[][]> rotations = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int r = 0; r < 4; r++) {
            int[][] rotated = rotate(present, r);
            String signature = getSignature(rotated);

            if (!seen.contains(signature)) {
                seen.add(signature);
                rotations.add(rotated);
            }
        }

        return rotations;
    }

    static String getSignature(int[][] present) {
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        for (int[] cell : present) {
            minRow = Math.min(minRow, cell[0]);
            minCol = Math.min(minCol, cell[1]);
        }

        List<String> coords = new ArrayList<>();
        for (int[] cell : present) {
            coords.add((cell[0] - minRow) + "," + (cell[1] - minCol));
        }
        Collections.sort(coords);
        return String.join(";", coords);
    }

    static boolean canPlaceAll(boolean[][] grid, List<List<int[][]>> presents, int index) {
        if (index == presents.size()) {
            return true;
        }

        int height = grid.length;
        int width = grid[0].length;
        List<int[][]> rotations = presents.get(index);

        for (int[][] rotation : rotations) {
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (canPlace(grid, rotation, row, col)) {
                        place(grid, rotation, row, col, true);

                        if (canPlaceAll(grid, presents, index + 1)) {
                            return true;
                        }

                        place(grid, rotation, row, col, false);
                    }
                }
            }
        }

        return false;
    }

    static int[][] rotate(int[][] present, int times) {
        int[][] result = present;

        for (int t = 0; t < times; t++) {
            int[][] temp = new int[result.length][2];

            int maxRow = 0;
            int maxCol = 0;

            for (int[] cell : result) {
                maxRow = Math.max(maxRow, cell[0]);
                maxCol = Math.max(maxCol, cell[1]);
            }

            for (int i = 0; i < result.length; i++) {
                int r = result[i][0];
                int c = result[i][1];

                temp[i][0] = c;
                temp[i][1] = maxRow - r;
            }

            result = temp;
        }

        return result;
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
