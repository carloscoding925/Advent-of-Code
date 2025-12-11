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

        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
