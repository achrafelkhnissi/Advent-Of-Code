package day01.part01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/day01/part01/input.txt";
        int sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                List<Integer> numbers = line.chars()
                        .filter(Character::isDigit)
                        .map(Character::getNumericValue)
                        .boxed()
                        .toList();

                if (numbers.isEmpty()) {
                    continue;
                }

                int firstDigit = numbers.get(0);
                int lastDigit = numbers.get(numbers.size() - 1);

                sum += Integer.parseInt(firstDigit + "" + lastDigit);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sum);
    }
}