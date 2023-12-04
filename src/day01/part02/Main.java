package day01.part02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Main {

    private static final HashMap<String, Integer> numbersMap = new HashMap<>(
            Map.of("one", 1,
                    "two", 2,
                    "three", 3,
                    "four", 4,
                    "five", 5,
                    "six", 6,
                    "seven", 7,
                    "eight", 8,
                    "nine", 9
            ));
    public static void main(String[] args) {
        String fileName = "src/day01/part02/input.txt";
        int sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            for (String line : lines) {

                List<String> elements = extractElements(line);

                if (elements.isEmpty()) {
                    continue;
                }

                int firstNumber = getNumber(elements.get(0));
                int lastNumber = getNumber(elements.get(elements.size() - 1));

                sum += Integer.parseInt((firstNumber + "" + lastNumber));
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        System.out.println(sum);
    }

    private static List<String> extractElements(String line) {
        List<String> elements = new ArrayList<>();
        List<String> numKeys = new ArrayList<>(numbersMap.keySet());

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                elements.add(String.valueOf(line.charAt(i)));
                continue;
            }

            for (int j = 3; j <= 5; j++) {
                if (i + j > line.length()) {
                    break;
                }

                String sub = line.substring(i, i + j);

                if (numKeys.contains(sub)) {
                    elements.add(sub);
                    i += j - 2; // -2 so that the next iteration starts at the last char of the previous match
                    break;
                }
            }
        }
        return elements;
    }

    private static int getNumber(String element) {
        return numbersMap.containsKey(element) ? numbersMap.get(element) : Integer.parseInt(element);
    }
}