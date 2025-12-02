import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayTwo {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTwo.txt"));

            List<String> allRanges = new ArrayList<>();
            for (String line : lines) {
                String[] ranges = line.split(",");

                for (String range : ranges) {
                    if (!range.trim().isEmpty()) {
                        allRanges.add(range.trim());
                    }
                }
            }

            long sum = 0;
            for (String range : allRanges) {
                String[] ids = range.split("-");
                long left = Long.parseLong(ids[0]);
                long right = Long.parseLong(ids[1]);

                while (left <= right) {
                    String idString = String.valueOf(left);
                    int idLength = idString.length();

                    if (idLength == 1 || (idLength % 2 != 0)) {
                        left++;
                        continue;
                    }

                    int start = 0;
                    int halfLength = idLength / 2;
                    boolean isSymmetric = true;

                    while (halfLength < idLength) {
                        if (idString.charAt(start) != idString.charAt(halfLength)) {
                            isSymmetric = false;
                            break;
                        }
                        
                        start++;
                        halfLength++;
                    }

                    if (isSymmetric) {
                        sum = sum + left;
                    }

                    left++;
                }
            }

            System.out.println("Part 1 Total Sum: " + sum);
            // Should be 64215794229
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayTwo.txt"));

            List<String> allRanges = new ArrayList<>();
            for (String line : lines) {
                String[] ranges = line.split(",");

                for (String range : ranges) {
                    if (!range.trim().isEmpty()) {
                        allRanges.add(range.trim());
                    }
                }
            }

            long sum = 0;
            for (String range : allRanges) {
                String[] ids = range.split("-");
                long left = Long.parseLong(ids[0]);
                long right = Long.parseLong(ids[1]);

                while (left <= right) {
                    String idString = String.valueOf(left);
                    int idLength = idString.length();
                    boolean hasSequence = false;

                    for (int sequenceLength = 1; sequenceLength <= idLength / 2; sequenceLength++) {
                        if (idLength % sequenceLength == 0) {
                            String seqString = idString.substring(0, sequenceLength);
                            int repetitions = idLength / sequenceLength;

                            if (seqString.repeat(repetitions).equals(idString)) {
                                hasSequence = true;
                            }
                        }
                    }

                    if (hasSequence) {
                        sum = sum + left;
                    }

                    left++;
                }
            }

            System.out.println("Part 2 Total Sum: " + sum);
            // Should be 85513235135
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }
}
