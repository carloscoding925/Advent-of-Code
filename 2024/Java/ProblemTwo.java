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
    static void parseInput(String fileInput) {
        
    }

    public static void main(String[] args) {
        String fileInput = readInFile("Inputs/test.txt");
    }
}
