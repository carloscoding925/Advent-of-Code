import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

            Set<String> visited = new HashSet<>();
            int count = 0;

            System.out.println("Part one count: " + count);
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static int graphDfs(String curr, Map<String, List<String>> graph, Set<String> visited) {
        return 0;
    }

    private static void solvePartTwo() {

    }
}