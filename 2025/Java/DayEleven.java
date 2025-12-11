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
            int count = graphDfs("you", graph, visited);

            System.out.println("Part one count: " + count);
            // Should be: 477
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static int graphDfs(String curr, Map<String, List<String>> graph, Set<String> visited) {
        if (curr.equals("out")) {
            return 1;
        }

        if (visited.contains(curr)) {
            return 0;
        }

        if (!graph.containsKey(curr)) {
            return 0;
        }

        visited.add(curr);

        int total = 0;
        for (String neighbor : graph.get(curr)) {
            total = total + graphDfs(neighbor, graph, visited);
        }

        visited.remove(curr);

        return total;
    }

    private static void solvePartTwo() {
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
            Set<String> requiredVisited = new HashSet<>();
            Set<String> requiredNodes = new HashSet<>(Arrays.asList("fft", "dac"));

            int count = 0;

            System.out.println("Part two count: " + count);
            // Should be: 
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static int graphDfsWithRequiredNodesVisited() {
        return 0;
    }
}