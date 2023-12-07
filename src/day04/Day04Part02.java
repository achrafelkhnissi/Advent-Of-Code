package day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day04Part02 {
    private static final String filePath = "src/day04/input.txt";

    public static void main(String[] args) {

        int numberOfScratchcards = 0;

        try
        {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            HashMap<Integer, Integer> copies = new HashMap<>();

            for (String line : lines) {

                String[] split = line.split(":"); // [0] = cardId, [1] = winningNumbers | myNumbers
                String[] numbers = split[1].split("\\|"); // [0] = winningNumbers, [1] = myNumbers
                String winningNumbers = numbers[0];
                String myNumbers = numbers[1];

                String[] cardIdSplit = split[0].split(" "); // [0] = "Card", [-1] = cardId
                String cardId = split[0].split(" ")[cardIdSplit.length - 1];

                int numberOfCopies = 0;

                copies.putIfAbsent(toInt(cardId), numberOfCopies + 1); // 1 for the original

                numberOfCopies = getNumberOfCopies(winningNumbers, myNumbers);

                // Update numberOfCopies for each cardId
                for (int i = 1; i <= numberOfCopies; i++) {

                    if (i == lines.size()) {
                        break;
                    }

                    int newCardId = toInt(cardId) + i;

                    if (!copies.containsKey(newCardId) && i < lines.size()) {
                        copies.put(newCardId, 1);
                    }

                    // (copies.get(toInt(cardId)) : Each copy of the cardId wins the same amount of its original
                    copies.put(newCardId, copies.get(newCardId) + (copies.get(toInt(cardId))));
                }

            }

            // Sum all copies
            for (int i = 1; i <= copies.size(); i++) {
                numberOfScratchcards += copies.get(i);
            }

            System.out.println(numberOfScratchcards);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static int toInt(String s) {
        return Integer.parseInt(s);
    }

    public static int getNumberOfCopies(String winningNumbers, String myNumbers) {

        int numberOfWinningNumbers = 0;

        ArrayList<String> winningNumbersList = new ArrayList<>(Arrays.asList(winningNumbers.split(" ")));
        ArrayList<String> myNumbersList = new ArrayList<>(Arrays.asList(myNumbers.split(" ")));

        // Check if winning numbers are in my numbers
        for (String number : myNumbersList) {

            if (number.length() == 0) {
                continue;
            }

            if (winningNumbersList.contains(number)) {
                numberOfWinningNumbers++;
            }
        }

        return numberOfWinningNumbers;
    }

}
