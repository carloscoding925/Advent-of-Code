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

            int startingPos = -1;
            for (int j = 0; j < diagram[0].length; j++) {
                if (diagram[0][j] == 'S') {
                    startingPos = j;
                    break;
                }
            }

            int count = countPathsDFS(diagram, 0, startingPos);
            System.out.println("Part 2 Count: " + count);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static int countPathsDFS(char[][] diagram, int row, int col) {
        if (row >= diagram.length) {
            return 1;
        }

        if (col < 0 || col >= diagram[0].length) {
            return 0;
        }

        if (diagram[row][col] == '^') {
            int left = countPathsDFS(diagram, row + 1, col - 1);
            int right = countPathsDFS(diagram, row + 1, col + 1);
            return left + right;
        }
        else {
            return countPathsDFS(diagram, row + 1, col);
        }
    }
}
