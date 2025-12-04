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
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}
