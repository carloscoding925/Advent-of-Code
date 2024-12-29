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

    static String[] parseInput(String fileInput) {
        String[] fileLines = fileInput.split("\n");
        String[] reportNumbers = new String[fileLines.length];

        for (int i = 0; i < fileLines.length; i++) {
            String[] line = fileLines[i].split(" ");

            for (int j = 0; j < line.length; j++) {
                if (reportNumbers[i] == null) {
                    reportNumbers[i] = line[j];
                }
                else {
                    reportNumbers[i] = reportNumbers[i].concat(line[j]);
                }
            }
        }

        return reportNumbers;
    }

    static void solvePartOne(String[] reportNumbers) {
        int safeReports = 0;

        for (int i = 0; i < reportNumbers.length; i++) {
            boolean isSafe = false;
            int changeAmount = 0;
            String direction = "increasing";

            int checkOne = Character.getNumericValue(reportNumbers[i].charAt(0));
            int checkTwo = Character.getNumericValue(reportNumbers[i].charAt(1));

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

            for (int j = 0; j < reportNumbers[i].length() - 1; j++) {
                int numOne = Character.getNumericValue(reportNumbers[i].charAt(j));
                int numTwo = Character.getNumericValue(reportNumbers[i].charAt(j + 1));

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
                System.out.println("Is safe: " + reportNumbers[i]);
            }
        }

        System.out.println("The number of safe reports is: " + safeReports);
    }

    public static void main(String[] args) {
        String fileInput = readInFile("Inputs/test.txt");
        String[] reportNumbers = parseInput(fileInput);
        
        // Error in parseInput, does not work for double digit numbers, need to fix
        
        solvePartOne(reportNumbers);
    }
}
