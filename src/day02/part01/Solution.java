package day02.part01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class Solution {
    private static final String filePath = "src/day02/part01/input.txt";
    private static final HashMap<String, Integer> rbgCubesMap = new HashMap<>(
            java.util.Map.of("red", 12,
                    "green", 13,
                    "blue", 14));

    public static void main(String[] args) {

        int sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                int id = parseGameId(line);
                sum += evaluateGamePossibility(line) ? id : 0;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sum);
    }

    private static int parseGameId(String line) {
        return Integer.parseInt(line.split(":")[0].split(" ")[1]);
    }

    private static boolean evaluateGamePossibility(String line) {
        String[] game = line.split(":")[1].split(";|,|\\s+");

        for (int i = 0; i < game.length; i += 1) {
            if (!game[i].isBlank()) {
                String color = game[i + 1];
                int value = Integer.parseInt(game[i]);
                if (value > rbgCubesMap.getOrDefault(color, 0)) {
                    return false;
                }
                i += 1;
            }
        }
        return true;
    }
}