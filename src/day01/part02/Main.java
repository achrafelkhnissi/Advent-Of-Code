package day01.part02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/day02/input.txt";
        int sum = 0;

        HashMap<String, Integer> numbersMap = new HashMap<>(
                Map.of("one", 1,
                        "two", 2,
                        "three", 3,
                        "four", 4,
                        "five", 5,
                        "six", 6,
                        "seven", 7,
                        "eight", 8,
                        "nine", 9));

        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            for (String line : lines) {

                String wordsToMatch = "one|two|three|four|five|six|seven|eight|nine";
                String anyNumber = "\\d";

                Pattern pattern = Pattern.compile(wordsToMatch + "|" + anyNumber);
                Matcher matcher = pattern.matcher(line);

                List<String> matchedElements = new ArrayList<>();

                while (matcher.find()) {
                    matchedElements.add(matcher.group());
                }

                if (matchedElements.isEmpty()) {
                    continue;
                }

                String firstElement = matchedElements.get(0);
                String lastElement = matchedElements.get(matchedElements.size() - 1);

                int firstNumber = numbersMap.containsKey(firstElement) ? numbersMap.get(firstElement) : Integer.parseInt(firstElement);
                int lastNumber = numbersMap.containsKey(lastElement) ? numbersMap.get(lastElement) : Integer.parseInt(lastElement);

                sum += Integer.parseInt((firstNumber + "" + lastNumber));
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        System.out.println(sum);
    }
}