import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DaySix {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySix.txt"));
            int inputLength = lines.size();

            List<String[]> nums = new ArrayList<>();
            for (int i = 0; i < inputLength - 1; i++) {
                String[] lineValues = lines.get(i).trim().split("\\s+");
                nums.add(lineValues);
            }
            int numsSize = nums.size();

            String operationString = lines.getLast();
            String[] operations = operationString.trim().split("\\s+");
            long total = 0;

            for (int j = 0; j < operations.length; j++) {
                long result = 0;

                if (operations[j].equals("*")) {
                    result = Integer.parseInt(nums.get(0)[j]);

                    for (int k = 1; k < numsSize; k++) {
                        result = result * Integer.parseInt(nums.get(k)[j]);
                    }
                }
                else {
                    result = Integer.parseInt(nums.get(0)[j]);

                    for (int k = 1; k < numsSize; k++) {
                        result = result + Integer.parseInt(nums.get(k)[j]);
                    }
                }

                total = total + result;
            }

            System.out.println("Part 1 Total: " + total);
            // Should be: 5361735137219
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DaySix.txt"));
            int inputLength = lines.size();

            List<String> rows = new ArrayList<>();
            for (int i = 0; i < inputLength - 1; i++) {
                rows.add(lines.get(i));
            }

            String operationRow = lines.getLast();
            int maxLength = operationRow.length();
            long total = 0;

            List<Long> currentNumbers = new ArrayList<>();
            char currentOperation = ' ';

            for (int col = maxLength - 1; col >= 0; col--) {
                boolean process = true;

                for (String row : rows) {
                    if (col < row.length() && !Character.isWhitespace(row.charAt(col))) {
                        process = false;
                        break;
                    }
                }

                if (col < operationRow.length() && !Character.isWhitespace(operationRow.charAt(col))) {
                    process = false;
                }

                if (process) {
                    if (!currentNumbers.isEmpty() && currentOperation != ' ') {
                        long result = currentNumbers.get(0);

                        for (int i = 1; i < currentNumbers.size(); i++) {
                            if (currentOperation == '*') {
                                result = result * currentNumbers.get(i);
                            }
                            else {
                                result = result + currentNumbers.get(i);
                            }
                        }

                        total = total + result;
                        currentNumbers.clear();
                        currentOperation = ' ';
                    }
                }
                else {
                    char opChar = operationRow.charAt(col);

                    if (opChar == '*' || opChar == '+') {
                        currentOperation = opChar;
                    }

                    StringBuilder builder = new StringBuilder();
                    for (String row : rows) {
                        if (col < row.length()) {
                            char digit = row.charAt(col);

                            if (!Character.isWhitespace(digit)) {
                                builder.append(digit);
                            }
                        }
                    }

                    if (builder.length() > 0) {
                        currentNumbers.add(Long.parseLong(builder.toString()));
                    }
                }
            }

            if (!currentNumbers.isEmpty() && currentOperation != ' ') {
                long result = currentNumbers.get(0);

                for (int i = 1; i < currentNumbers.size(); i++) {
                    if (currentOperation == '*') {
                        result = result * currentNumbers.get(i);
                    }
                    else {
                        result = result + currentNumbers.get(i);
                    }
                }

                total = total + result;
            }

            System.out.println("Part 2 Total: " + total);
            // Should be: 11744693538946
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
