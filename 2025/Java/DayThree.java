import java.io.File;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

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
                    int tens = Character.getNumericValue(line.charAt(i));

                    for (int j = i + 1; j < line.length(); j++) {
                        int ones = Character.getNumericValue(line.charAt(j));
                        int val = (tens * 10) + ones;
                        largest = Math.max(largest, val);
                    }
                }

                maxJoltage = maxJoltage + largest;
            }

            System.out.println("Part 1 Max Joltage: " + maxJoltage);
            // Should be 17524

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            Scanner scanner = new Scanner(new File("../Inputs/DayThree.txt"));
            long maxJoltage = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                StringBuilder builder = new StringBuilder();
                int linePosition = 0;
                int joltageLength = 12;

                for (int pos = 0; pos < joltageLength; pos++) {
                    int remaining = joltageLength - pos;

                    int maxDigit = -1; // Digits are in range [0-9] and position [0-line.length()] so use -1 as a placeholder
                    int maxPos = -1;

                    /*
                        Each line is larger than 12 values so each position in our joltage has a range from line[i] to line[n]
                        it can be in, in this for loop we check the range [i, n] and grab the largest available integer
                     */
                    for (int i = linePosition; i <= line.length() - remaining; i++) {
                        int digit = Character.getNumericValue(line.charAt(i));
                        if (digit > maxDigit) {
                            maxDigit = digit;
                            maxPos = i;
                        }
                    }

                    // Realistically it should never be -1 but just in case
                    if (maxDigit != -1) {
                        builder.append(maxDigit);
                        linePosition = maxPos + 1;
                    }
                }

                long val = Long.parseLong(builder.toString()); // Longs so we dont blow up
                maxJoltage = maxJoltage + val;
            }

            System.out.println("Part 2 Max Joltage: " + maxJoltage);
            // Should be

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
