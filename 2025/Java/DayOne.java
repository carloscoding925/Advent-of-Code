import java.io.File;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            Scanner scanner = new Scanner(new File("../Inputs/DayOne.txt"));
            int position = 50;
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char direction = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));

                if (direction == 'L') {
                    position = position - value;

                    while (position < 0) {
                        position = position + 100;
                    }
                }
                else {
                    position = position + value;

                    while (position > 99) {
                        position = position - 100;
                    }
                }

                if (position == 0) {
                    count++;
                }
            }

            System.out.println("Part One Count: " + count);
            // For Part 1, should be 1145

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            Scanner scanner = new Scanner(new File("../Inputs/DayOne.txt"));
            int position = 50;
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char direction = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));

                if (direction == 'L') {
                    if (position == 0) {
                        count = count + (value / 100);
                    } 
                    else if (value >= position) {
                        count = count + ((value - position) / 100 + 1);
                    }

                    position = position - value;
                    while (position < 0) {
                        position = position + 100;
                    }
                }
                else {
                    if (position == 0) {
                        count = count + (value / 100);
                    } 
                    else if (value >= 100 - position) {
                        count = count + ((value - (100 - position)) / 100 + 1);
                    }

                    position = position + value;
                    while (position > 99) {
                        position = position - 100;
                    }
                }
            }

            System.out.println("Part Two Count: " + count);
            // For Part 2, should be 6561

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
