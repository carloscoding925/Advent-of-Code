import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DaySeven {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySeven.txt"));

            char[][] diagram = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                diagram[i] = lines.get(i).toCharArray();
            }

            char[] beamArray = new char[lines.get(0).length()];
            int count = 0;

            for (int i = 0; i < diagram.length; i++) {
                for (int j = 0; j < diagram[0].length; j++) {
                    if (diagram[i][j] == 'S') {
                        beamArray[j] = 'X';
                    }

                    if (diagram[i][j] == '^' && beamArray[j] == 'X') {
                        if (j - 1 >= 0) {
                            beamArray[j - 1] = 'X';
                        }

                        if (j + 1 < beamArray.length) {
                            beamArray[j + 1] = 'X';
                        }

                        beamArray[j] = '.';
                        count++;
                    }
                }
            }

            System.out.println("Part 1 Count: " + count);
            // Should be: 1550
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySeven.txt"));

            char[][] diagram = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                diagram[i] = lines.get(i).toCharArray();
            }

            // Find starting position ('S' Character)
            int startingPos = -1;
            for (int j = 0; j < diagram[0].length; j++) {
                if (diagram[0][j] == 'S') {
                    startingPos = j;
                    break;
                }
            }

            // Only one way for the beam to split at first (initial state)
            long[] paths = new long[diagram[0].length];
            paths[startingPos] = 1;

            // Double for loop - first one creates a new paths array at every row
            // second one calculates new paths when we run into a '^' character AND a previous path exists
            for (int row = 0; row < diagram.length; row++) {
                long nextPaths[] = new long[diagram[0].length];

                for (int col = 0; col < diagram[0].length; col++) {
                    if (paths[col] > 0) {
                        if (diagram[row][col] == '^') {
                            if (col - 1 >= 0) {
                                nextPaths[col - 1] = nextPaths[col - 1] + paths[col];
                            }
                            
                            if (col + 1 < diagram[0].length) {
                                nextPaths[col + 1] = nextPaths[col + 1] + paths[col];
                            }
                        }
                        else {
                            nextPaths[col] = nextPaths[col] + paths[col];
                        }
                    }
                }

                paths = nextPaths;
            }

            long totalPaths = 0;
            for (long count : paths) {
                totalPaths = totalPaths + count;
            }

            System.out.println("Part 2 Count: " + totalPaths);
            // Should be: 9897897326778
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
