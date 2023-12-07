package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day04Part01 {
    private static final String filePath = "src/day04/input.txt";

    public static void main(String[] args) {

        int totalMatches = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
//                ArrayList<String> winningNumbers = new ArrayList<>();

                int match = 0;
                boolean isFirstMatch = true;

                String[] split = line.split(":");
                String[] numbers = split[1].split("\\|");
                String winningNumbers = numbers[0];
                String myNumbers = numbers[1];

                ArrayList<String> winningNumbersList = new ArrayList<>(Arrays.asList(winningNumbers.split(" ")));
                ArrayList<String> myNumbersList = new ArrayList<>(Arrays.asList(myNumbers.split(" ")));

                for (String number : myNumbersList) {

                    if (number.isEmpty()) {
                        continue;
                    }

                    if (winningNumbersList.contains(number)) {

                        if (isFirstMatch) {
                            isFirstMatch = false;
                            match = 1;
                        } else {
                            match *= 2;
                        }
                    }
                }

                totalMatches += match;
            }

            System.out.println(totalMatches);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
