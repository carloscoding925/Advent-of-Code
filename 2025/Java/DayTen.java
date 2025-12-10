import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            for (int i = 0; i < indicators.size(); i++) {
                System.out.println(Arrays.toString(indicators.get(i)));
            }

            for (int j = 0; j < buttons.size(); j++) {
                System.out.println(Arrays.toString(buttons.get(j)));
            }
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
