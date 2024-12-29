import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ProblemTwo {

    static String readInFile(String filePath) {
        String fileInput = "";
        String tempString = "";

        try {
            Scanner fileReader = new Scanner(new File(filePath));
            while (fileReader.hasNextLine()) {
                tempString = fileReader.nextLine();
                fileInput = fileInput + tempString + "\n";
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileInput;
    }

    static void solvePartOne(String fileInput) {
        String[] fileLines = fileInput.split("\n");
        int safeReports = 0;

        for (int i = 0; i < fileLines.length; i++) {
            String reportNumbers[] = fileLines[i].split(" ");
            boolean isSafe = false;
            int changeAmount = 0;
            String direction = "increasing";

            int checkOne = Integer.parseInt(reportNumbers[0]);
            int checkTwo = Integer.parseInt(reportNumbers[1]);

            if (checkOne - checkTwo > 0) {
                direction = "decreasing";
            }
            else if (checkOne - checkTwo < 0) {
                direction = "increasing";
            }
            else if (checkOne - checkTwo == 0) {
                isSafe = false;
                continue;
            }

            for (int j = 0; j < reportNumbers.length - 1; j++) {
                int numOne = Integer.parseInt(reportNumbers[j]);
                int numTwo = Integer.parseInt(reportNumbers[j + 1]);

                if (direction == "increasing") {
                    changeAmount = numTwo - numOne;

                    if (changeAmount <= 0) {
                        isSafe = false;
                        break;
                    }
                    else if (changeAmount > 3) {
                        isSafe = false;
                        break;
                    }
                    else if (changeAmount <= 3) {
                        isSafe = true;
                    }
                }
                else {
                    changeAmount = numOne - numTwo;

                    if (changeAmount <= 0) {
                        isSafe = false;
                        break;
                    }
                    else if (changeAmount > 3) {
                        isSafe = false;
                        break;
                    }
                    else if (changeAmount <= 3) {
                        isSafe = true;
                    }
                }
            }

            if (isSafe) {
                safeReports++;
            }
        }

        System.out.println("The number of safe reports is: " + safeReports);
        // Answer should be: 421 Safe reports
    }

    static void solvePartTwo(String fileInput) {
        String[] fileLines = fileInput.split("\n");
        int safeReports = 0;

        for (int i = 0; i < fileLines.length; i++) {
            String reportNumbers[] = fileLines[i].split(" ");
            boolean isSafe = false;
            int changeAmount = 0;
            String direction = "increasing";

            int checkOne = Integer.parseInt(reportNumbers[0]);
            int checkTwo = Integer.parseInt(reportNumbers[1]);

            if (checkOne - checkTwo > 0) {
                direction = "decreasing";
            }
            else if (checkOne - checkTwo < 0) {
                direction = "increasing";
            }
            else if (checkOne - checkTwo == 0) {

            }
        }
    }

    public static void main(String[] args) {
        String fileInput = readInFile("Inputs/test.txt");
        solvePartOne(fileInput);
    }
}
