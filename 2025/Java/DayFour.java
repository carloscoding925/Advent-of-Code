import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
            List<String> copy = new ArrayList<>(lines);
            
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

            System.out.println("Part 2 Count: " + reachable);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
