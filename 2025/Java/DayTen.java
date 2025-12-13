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

        Using Gauss-Jordan Elimination to get RREF, then backtrack free variables
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

        // Gauss-Jordan elimination to get RREF
        int[] pivotCols = gaussJordanElimination(matrix, numIndices, numButtons);

        // Identify free variables (non-pivot columns)
        List<Integer> freeVars = new ArrayList<>();
        for (int col = 0; col < numButtons; col++) {
            boolean isPivot = false;
            for (int pivotCol : pivotCols) {
                if (pivotCol == col) {
                    isPivot = true;
                    break;
                }
            }

            if (!isPivot) {
                freeVars.add(col);
            }
        }

        // If no free variables, compute the unique solution
        if (freeVars.isEmpty()) {
            int totalPresses = 0;
            for (int row = 0; row < numIndices; row++) {
                int pivotCol = pivotCols[row];

                if (pivotCol >= 0) {
                    totalPresses += (int) Math.round(matrix[row][numButtons]);
                }
            }
            return totalPresses;
        }

        // Backtrack over free variables to find minimum solution
        return backtrackFreeVariables(matrix, pivotCols, freeVars, numButtons, numIndices);
    }

    private static int[] gaussJordanElimination(double[][] matrix, int numRows, int numCols) {
        int[] pivotCols = new int[numRows];
        for (int i = 0; i < numRows; i++) {
            pivotCols[i] = -1;
        }

        int currentRow = 0;

        for (int col = 0; col < numCols && currentRow < numRows; col++) {
            // Find pivot
            int maxRow = currentRow;
            for (int row = currentRow + 1; row < numRows; row++) {
                if (Math.abs(matrix[row][col]) > Math.abs(matrix[maxRow][col])) {
                    maxRow = row;
                }
            }

            if (Math.abs(matrix[maxRow][col]) < ep) {
                continue; // No pivot in this column
            }

            // Swap rows
            double[] temp = matrix[currentRow];
            matrix[currentRow] = matrix[maxRow];
            matrix[maxRow] = temp;

            pivotCols[currentRow] = col;

            // Scale pivot row to make pivot = 1
            double pivot = matrix[currentRow][col];
            for (int j = 0; j <= numCols; j++) {
                matrix[currentRow][j] /= pivot;
            }

            // Eliminate all other entries in this column (both above and below)
            for (int row = 0; row < numRows; row++) {
                if (row != currentRow && Math.abs(matrix[row][col]) > ep) {
                    double factor = matrix[row][col];
                    for (int j = 0; j <= numCols; j++) {
                        matrix[row][j] -= factor * matrix[currentRow][j];
                    }
                }
            }

            currentRow++;
        }

        return pivotCols;
    }

    private static int backtrackFreeVariables(
        double[][] matrix, int[] pivotCols, List<Integer> freeVars, int numButtons, int numRows
    ) {
        int minPresses = Integer.MAX_VALUE;

        // Determine reasonable bounds for free variables based on target values
        int maxFreeVarValue = 0;
        for (int i = 0; i < numRows; i++) {
            maxFreeVarValue = Math.max(maxFreeVarValue, (int) Math.round(Math.abs(matrix[i][numButtons])));
        }
        maxFreeVarValue = Math.min(maxFreeVarValue * 2, 300); // Cap at reasonable value

        // Try all combinations of free variable values
        int[] freeVarValues = new int[freeVars.size()];
        minPresses = tryFreeVarCombinations(matrix, pivotCols, freeVars, freeVarValues,0, numButtons, numRows, maxFreeVarValue, minPresses);

        return minPresses;
    }

    private static int tryFreeVarCombinations(
        double[][] matrix, int[] pivotCols, List<Integer> freeVars, int[] freeVarValues, int index, int numButtons, int numRows, int maxValue, int currentMin
    ) {
        if (index == freeVars.size()) {
            // Compute solution for this combination of free variables
            double[] solution = new double[numButtons];

            // Set free variables
            for (int i = 0; i < freeVars.size(); i++) {
                solution[freeVars.get(i)] = freeVarValues[i];
            }

            // Compute basic variables from RREF
            for (int row = 0; row < numRows; row++) {
                int pivotCol = pivotCols[row];
                if (pivotCol >= 0) {
                    double value = matrix[row][numButtons];

                    // Subtract contributions from free variables
                    for (int col = 0; col < numButtons; col++) {
                        if (col != pivotCol && Math.abs(matrix[row][col]) > ep) {
                            value -= matrix[row][col] * solution[col];
                        }
                    }

                    solution[pivotCol] = value;
                }
            }

            // Check if all values are non-negative integers (or very close)
            boolean valid = true;
            int totalPresses = 0;
            for (int i = 0; i < numButtons; i++) {
                int rounded = (int) Math.round(solution[i]);
                if (Math.abs(solution[i] - rounded) > ep || rounded < 0) {
                    valid = false;
                    break;
                }
                totalPresses += rounded;
            }

            if (valid && totalPresses < currentMin) {
                return totalPresses;
            }

            return currentMin;
        }

        // Try different values for this free variable
        int min = currentMin;
        
        for (int value = 0; value <= maxValue; value++) {
            freeVarValues[index] = value;
            min = Math.min(min, tryFreeVarCombinations(matrix, pivotCols, freeVars, freeVarValues, index + 1, numButtons, numRows, maxValue, min));

            // Early termination if we found 0
            if (min == 0) {
                return 0;
            }
        }

        return min;
    }
}
