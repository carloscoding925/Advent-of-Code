import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ProblemOne{
    public static void main(String[] args) {
        String fileInput = "";
        String tempString = "";

        System.out.println("Problem One");

        try {
            Scanner fileReader = new Scanner(new File("Inputs/ProblemOne.txt"));
            while (fileReader.hasNextLine()) {
                tempString = fileReader.nextLine();
                fileInput = fileInput + tempString + "\n";
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] inputLines = fileInput.split("\n");
        int[][] inputNumbers = new int[inputLines.length][2];

        for (int i = 0; i < inputLines.length; i++) {
            String[] numberStrings = inputLines[i].trim().split("\\s+");
            inputNumbers[i][0] = Integer.parseInt(numberStrings[0]);
            inputNumbers[i][1] = Integer.parseInt(numberStrings[1]);
        }

        int[] leftArray = new int[inputLines.length];
        int[] rightArray = new int[inputLines.length];

        for (int i = 0; i < inputNumbers.length; i++) {
            leftArray[i] = inputNumbers[i][0];
            rightArray[i] = inputNumbers[i][1];
        }

        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

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
        // For problem 1, the solution should be 1765812
    }
}