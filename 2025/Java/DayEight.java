import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEight {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayEight.txt"));

            List<int[]> coordinates = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                
                int[] coord = new int[parts.length];
                for (int i = 0; i < coord.length; i++) {
                    coord[i] = Integer.parseInt(parts[i]);
                }

                coordinates.add(coord);
            }

            List<double[]> edges = new ArrayList<>();
            for (int i = 0; i < coordinates.size(); i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    double distance = distance(coordinates.get(i), coordinates.get(j));

                    double[] edge = new double[3];
                    edge[0] = i;
                    edge[1] = j;
                    edge[2] = distance;

                    edges.add(edge);
                }
            }

            Collections.sort(edges, (a, b) -> Double.compare(a[2], b[2]));

            int[] parent = new int[coordinates.size()];
            int[] size = new int[coordinates.size()];
            for (int i = 0; i < coordinates.size(); i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < 1000 && i < edges.size(); i++) {
                int from = (int) edges.get(i)[0];
                int to = (int) edges.get(i)[1];

                union(parent, size, from, to);
            }

            Map<Integer, Integer> sizeMap = new HashMap<>();
            for (int i = 0; i < coordinates.size(); i++) {
                int root = find(parent, i);
                sizeMap.put(root, size[root]);
            }

            List<Integer> circuitSizes = new ArrayList<>(sizeMap.values());
            Collections.sort(circuitSizes, Collections.reverseOrder());

            long result = (long) circuitSizes.get(0) * circuitSizes.get(1) * circuitSizes.get(2);
            System.out.println("Part 1 Result: " + result);
            // Should be: 79560
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static double distance(int[] p1, int[] p2) {
        long dx = p1[0] - p2[0];
        long dy = p1[1] - p2[1];
        long dz = p1[2] - p2[2];

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }

    private static void union(int[] parent, int[] size, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX == rootY) {
            return;
        }

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] = size[rootY] + size[rootX];
        }
        else {
            parent[rootY] = rootX;
            size[rootX] = size[rootX] + size[rootY];
        }

        return;
    }

    private static void solvePartTwo() {

    }
}
