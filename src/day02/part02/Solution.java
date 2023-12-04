package day02.part02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final String filePath = "src/day02/part02/input.txt";

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            int sum = calculateCubesSum(lines);
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int calculateCubesSum(List<String> lines) {
        int sum = 0;

        for (String line : lines) {
            Map<String, Integer> rbgMap = initializeColorMap();
            updateColorMap(line, rbgMap);
            sum += calculatePower(rbgMap);
        }

        return sum;
    }

    private static Map<String, Integer> initializeColorMap() {
        return new HashMap<>(Map.of("red", 0, "blue", 0, "green", 0));
    }

    private static void updateColorMap(String line, Map<String, Integer> rbgMap) {
        String[] game = line.split(":")[1].split(";|,|\\s+");

        for (int i = 0; i < game.length; i += 1) {
            if (!game[i].isBlank()) {
                String color = game[i + 1];
                int value = Integer.parseInt(game[i]);
                rbgMap.put(color, Math.max(value, rbgMap.get(color)));
                i += 1;
            }
        }
    }

    private static int calculatePower(Map<String, Integer> rbgMap) {
        return rbgMap.get("red") * rbgMap.get("blue") * rbgMap.get("green");
    }
}
