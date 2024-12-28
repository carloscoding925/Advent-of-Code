import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ProblemOne{

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

    static int[][] parseInput(String fileInput) {
        String[] inputLines = fileInput.split("\n");
        int[][] inputNumbers = new int[inputLines.length][2];

        for (int i = 0; i < inputLines.length; i++) {
            String[] numberStrings = inputLines[i].trim().split("\\s+");
            inputNumbers[i][0] = Integer.parseInt(numberStrings[0]);
            inputNumbers[i][1] = Integer.parseInt(numberStrings[1]);
        }

        return inputNumbers;
    }

    static void solvePartOne(int[] leftArray, int[] rightArray) {
        int[] distancesArray = new int[leftArray.length];

        for (int i = 0; i < leftArray.length; i++) {
            if (leftArray[i] > rightArray[i]) {
                distancesArray[i] = leftArray[i] - rightArray[i];
            }
            else if (rightArray[i] > leftArray[i]) {
                distancesArray[i] = rightArray[i] - leftArray[i];
            }   
            else {
                distancesArray[i] = 0;
            }
        }

        int sum = 0;

        for (int i = 0; i < distancesArray.length; i++) {
            sum = sum + distancesArray[i];
        }

        System.out.println("The sum of the distances is: " + sum);
        // For part 1, the solution should be 1765812
    }

    static void solvePartTwo(int[] leftArray, int[] rightArray) {
        int similarityScore = 0;
        HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();

        for (int i = 0; i < rightArray.length; i++) {
            if (occurences.containsKey(rightArray[i])) {
                occurences.put(rightArray[i], occurences.get(rightArray[i]) + 1);
            }
            else {
                occurences.put(rightArray[i], 1);
            }
        }

        for (int i = 0; i < leftArray.length; i++) {
            if (occurences.containsKey(leftArray[i])) {
                similarityScore = similarityScore + (occurences.get(leftArray[i]) * leftArray[i]);
            }
            else {
                continue;
            }
        }

        System.out.println("The similarity score is: " + similarityScore);
        // For part 2, the solution should be 20520794
    }

    public static void main(String[] args) {
        String fileInput = readInFile("Inputs/ProblemOne.txt");

        int[][] inputNumbers = parseInput(fileInput);

        int[] leftArray = new int[inputNumbers.length];
        int[] rightArray = new int[inputNumbers.length];

        for (int i = 0; i < inputNumbers.length; i++) {
            leftArray[i] = inputNumbers[i][0];
            rightArray[i] = inputNumbers[i][1];
        }

        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

        solvePartOne(leftArray, rightArray);
        solvePartTwo(leftArray, rightArray);
    }
}
