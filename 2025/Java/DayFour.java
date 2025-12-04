import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayFour {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayFour.txt"));
            int reachable = 0;

            for (int row = 0; row < lines.size(); row++) {
                for (int col = 0; col < lines.get(row).length(); col++) {
                    if (lines.get(row).charAt(col) == '@') {
                        int rollCount = 0;

                        // TOP
                        if (row - 1 >= 0 && lines.get(row - 1).charAt(col) == '@') {
                            rollCount++;
                        }

                        // BOTTOM
                        if (row + 1 < lines.size() && lines.get(row + 1).charAt(col) == '@') {
                            rollCount++;
                        }

                        // LEFT
                        if (col - 1 >= 0 && lines.get(row).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // RIGHT
                        if (col + 1 < lines.get(row).length() && lines.get(row).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        // TOP LEFT
                        if (row - 1 >= 0 && col - 1 >= 0 && lines.get(row - 1).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // TOP RIGHT
                        if (row - 1 >= 0 && col + 1 < lines.get(row - 1).length() &&
                            lines.get(row - 1).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        // BOTTOM LEFT
                        if (row + 1 < lines.size() && col - 1 >= 0 &&
                            lines.get(row + 1).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // BOTTOM RIGHT
                        if (row + 1 < lines.size() && col + 1 < lines.get(row + 1).length() &&
                            lines.get(row + 1).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        if (rollCount <= 3) {
                            reachable++;
                        }
                    }
                }
            }

            System.out.println("Part 1 Count: " + reachable);
            // Should be: 1505
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayFour.txt"));
            
            // Converting to char 2D array for easier manipulation
            char[][] rollGrid = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                rollGrid[i] = lines.get(i).toCharArray();
            }

            // Keep track of all rolls removed + if we managed to remove any
            int reachable = 0;
            boolean removed = true;

            for (int row = 0; row < lines.size(); row++) {
                for (int col = 0; col < lines.get(row).length(); col++) {
                    if (lines.get(row).charAt(col) == '@') {
                        int rollCount = 0;

                        // TOP
                        if (row - 1 >= 0 && lines.get(row - 1).charAt(col) == '@') {
                            rollCount++;
                        }

                        // BOTTOM
                        if (row + 1 < lines.size() && lines.get(row + 1).charAt(col) == '@') {
                            rollCount++;
                        }

                        // LEFT
                        if (col - 1 >= 0 && lines.get(row).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // RIGHT
                        if (col + 1 < lines.get(row).length() && lines.get(row).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        // TOP LEFT
                        if (row - 1 >= 0 && col - 1 >= 0 && lines.get(row - 1).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // TOP RIGHT
                        if (row - 1 >= 0 && col + 1 < lines.get(row - 1).length() &&
                            lines.get(row - 1).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        // BOTTOM LEFT
                        if (row + 1 < lines.size() && col - 1 >= 0 &&
                            lines.get(row + 1).charAt(col - 1) == '@') {
                            rollCount++;
                        }

                        // BOTTOM RIGHT
                        if (row + 1 < lines.size() && col + 1 < lines.get(row + 1).length() &&
                            lines.get(row + 1).charAt(col + 1) == '@') {
                            rollCount++;
                        }

                        if (rollCount <= 3) {
                            reachable++;
                        }
                    }
                }
            }

            System.out.println("Part 2 Count: " + reachable);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    // same as part 1, modified to use in a char grid
    private static int countNeighboringRolls(char[][] grid, int row, int col) {
        int count = 0;

        // TOP
        if (row - 1 >= 0 && grid[row - 1][col] == '@') {
            count++;
        }

        // BOTTOM
        if (row + 1 < grid.length && grid[row + 1][col] == '@') {
            count++;
        }

        // LEFT
        if (col - 1 >= 0 && grid[row][col - 1] == '@') {
            count++;
        }

        // RIGHT
        if (col + 1 < grid[row].length && grid[row][col + 1] == '@') {
            count++;
        }

        // TOP LEFT
        if (row - 1 >= 0 && col - 1 >= 0 && grid[row - 1][col - 1] == '@') {
            count++;
        }

        // TOP RIGHT
        if (row - 1 >= 0 && col + 1 < grid[row - 1].length && grid[row - 1][col + 1] == '@') {
            count++;
        }

        // BOTTOM LEFT
        if (row + 1 < grid.length && col - 1 >= 0 && grid[row + 1][col - 1] == '@') {
            count++;
        }

        // BOTTOM RIGHT
        if (row + 1 < grid.length && col + 1 < grid[row + 1].length && grid[row + 1][col + 1] == '@') {
            count++;
        }

        return count;
    }
}
