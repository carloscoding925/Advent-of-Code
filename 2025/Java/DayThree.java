import java.io.File;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        solvePartOne();

        return;
    }

    private static void solvePartOne() {
        try {
            Scanner scanner = new Scanner(new File("../Inputs/DayThree.txt"));
            int maxJoltage = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int largest = 0;

                for (int i = 0; i < line.length() - 1; i++) {
                    for (int j = 0; j < line.length(); j++) {
                        
                    }
                }
            }

            System.out.println("Max Joltage: " + maxJoltage);

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
