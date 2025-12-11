import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEleven {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayEleven.txt"));

            Map<String, List<String>> graph = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(": ");
                String machine = parts[0];
                String[] outputs = parts[1].split(" ");

                graph.put(machine, Arrays.asList(outputs));
            }
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {

    }
}