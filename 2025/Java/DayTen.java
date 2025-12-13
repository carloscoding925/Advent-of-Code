import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DayTen {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTen.txt"));

            List<boolean[]> indicators = new ArrayList<>();
            List<String[]> buttons = new ArrayList<>();

            for (String line : lines) {
                // Create Indicator Diagram and Append to List
                int startBracket = line.indexOf('[');
                int endBracket = line.indexOf(']');
                String substring = line.substring(startBracket + 1, endBracket);
                boolean[] indicator = new boolean[substring.length()];

                for (int i = 0; i < substring.length(); i++) {
                    indicator[i] = substring.charAt(i) == '#';
                }
                indicators.add(indicator);

                int curlyStart = line.indexOf('{');
                String parenthesisSubstring = line.substring(endBracket + 1, curlyStart);
                String[] parenthesisArray = parenthesisSubstring.trim().split(" ");
                buttons.add(parenthesisArray);
            }

            int total = 0;
            for (int i = 0; i < lines.size(); i++) {
                boolean[] targetIndicator = indicators.get(i);
                String[] correlatedButtons = buttons.get(i);

                List<int[]> integerButtons = new ArrayList<>();
                for (String button : correlatedButtons) {
                    button = button.replace("(", "").replace(")", "").trim();

                    String[] parts = button.split(",");
                    int[] indices = new int[parts.length];
                    for (int j = 0; j < parts.length; j++) {
                        indices[j] = Integer.parseInt(parts[j].trim());
                    }

                    integerButtons.add(indices);
                }

                int minPresses = findMinPresses(targetIndicator, integerButtons);
                total = total + minPresses;
            }

            System.out.println("Part 1 Total: " + total);
            // Should be: 509
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static int findMinPresses(boolean[] target, List<int[]> buttons) {
        boolean[] start = new boolean[target.length];

        if (areEqual(start, target)) {
            return 0;
        }

        Queue<boolean[]> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();

        queue.offer(start);
        visited.put(arrayAsString(start), 0);

        while (!queue.isEmpty()) {
            boolean[] current = queue.poll();
            int presses = visited.get(arrayAsString(current));

            for (int[] button : buttons) {
                boolean[] next = current.clone();

                for (int index : button) {
                    next[index] = !next[index];
                }

                if (areEqual(next, target)) {
                    return presses + 1;
                }

                String nextString = arrayAsString(next);
                if (!visited.containsKey(nextString)) {
                    visited.put(nextString, presses + 1);
                    queue.offer(next);
                }
            }
        }

        return -1; 
    }

    private static String arrayAsString(boolean[] array) {
        StringBuilder builder = new StringBuilder();

        for (boolean bool : array) {
            builder.append(bool ? '#' : '.');
        }

        return builder.toString();
    }

    private static boolean areEqual(boolean[] a, boolean[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTen.txt"));

            List<int[]> targetJoltages = new ArrayList<>();
            List<List<int[]>> buttonsPerLine = new ArrayList<>();

            for (String line : lines) {
                int endBracket = line.indexOf(']');
                int startBrace = line.indexOf('{');
                String buttonsSubstring = line.substring(endBracket + 1, startBrace).trim();
                String[] buttonStrings = buttonsSubstring.split(" ");

                List<int[]> lineButtons = new ArrayList<>();
                for (String button : buttonStrings) {
                    button = button.replace("(", "").replace(")", "").trim();

                    String[] parts = button.split(",");
                    int[] indices = new int[parts.length];

                    for (int j = 0; j < parts.length; j++) {
                        indices[j] = Integer.parseInt(parts[j].trim());
                    }

                    lineButtons.add(indices);
                }
                buttonsPerLine.add(lineButtons);

                int endBrace = line.indexOf('}');
                String joltageSubstring = line.substring(startBrace + 1, endBrace).trim();
                String[] joltageParts = joltageSubstring.split(",");
                int[] joltageIndices = new int[joltageParts.length];

                for (int k = 0; k < joltageParts.length; k++) {
                    joltageIndices[k] = Integer.parseInt(joltageParts[k].trim());
                }
                targetJoltages.add(joltageIndices);
            }

            int total = 0;
            for (int n = 0; n < lines.size(); n++) {
                int[] target = targetJoltages.get(n);
                List<int[]> buttons = buttonsPerLine.get(n);

                int minPresses = solveLinearEquation(target, buttons);
                total = total + minPresses;
            }

            System.out.println("Part 2 Total: " + total);
            // Should be: 20862
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    /*
        Part 2 uses MATH to figure out the answer -
        Linear Algebra approach asks us to solve a matrix 

        Consider (2,3,4) (1,2,4) (0,1,3) (0) (2,3) {16,26,13,28,24}
        Solve 5 buttons & 5 variables so we can solve the following matrix:

        Index 0:          b2 + b3       = 16
        Index 1:      b1 + b2           = 26
        Index 2: b0 + b1      + b4      = 13
        Index 3: b0      + b2      + b4 = 28
        Index 4: b0 + b1                = 24

        Using Gaussian Elimination
        [0]   0   0   1   1   0  |  16
        [1]   0   1   1   0   0  |  26
        [2]   1   1   0   0   1  |  13
        [3]   0  -1   1   0   0  |  15  (row3 - row2)
        [4]   0   0   0   0  -1  |  11  (row4 - row2)
    */

    
    private static final double ep = 1e-10;

    private static int solveLinearEquation(int[] target, List<int[]> buttons) {
        int numIndices = target.length;
        int numButtons = buttons.size();

        double[][] matrix = new double[numIndices][numButtons + 1];

        // Build our coefficient matrix
        for (int buttonIndex = 0; buttonIndex < numButtons; buttonIndex++) {
            int[] button = buttons.get(buttonIndex);
            for (int affectedIndex : button) {
                matrix[affectedIndex][buttonIndex] = 1;
            }
        }

        // Add target values (the stuff inside the {})
        for (int i = 0; i < numIndices; i++) {
            matrix[i][numButtons] = target[i];
        }

        for (int col = 0; col < Math.min(numIndices, numButtons); col++) {
            int maxRow = col;

            // Search for our pivot index in the matrix
            for (int row = col + 1; row < numIndices; row++) {
                if (Math.abs(matrix[row][col]) > Math.abs(matrix[maxRow][col])) {
                    maxRow = row;
                }
            }

            // Perform row swaps with the row with the largest pivot
            double[] temp = matrix[col];
            matrix[col] = matrix[maxRow];
            matrix[maxRow] = temp;

            if (Math.abs(matrix[col][col]) < ep) {
                continue;
            }

            // Form our upper triangular matrix
            for (int row = col + 1; row < numIndices; row++) {
                double factor = matrix[row][col] / matrix[col][col];
                
                for (int j = col; j <= numButtons; j++) {
                    matrix[row][j] = matrix[row][j] - factor * matrix[col][j];
                }
            }
        }

        // Solve for each matrix variable backwards
        double[] solution = new double[numButtons];
        for (int i = Math.min(numIndices, numButtons) - 1; i >= 0; i--) {
            double sum = matrix[i][numButtons];

            for (int j = i + 1; j < numButtons; j++) {
                sum = sum - matrix[i][j] * solution[j];
            }

            if (Math.abs(matrix[i][i]) > ep) {
                solution[i] = sum / matrix[i][i];
            }
        }

        int totalPresses = 0;
        for (double presses : solution) {
            totalPresses = totalPresses + (int) Math.round(presses);
        }

        return totalPresses;
    }
}
