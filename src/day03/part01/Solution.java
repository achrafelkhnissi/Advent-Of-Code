package day03.part01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution {
    private static final String filePath = "src/day03/part01/input.txt";

    public static void main(String[] args) {

        int sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            String[][] map = new String[lines.size()][lines.get(0).length()];

            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i).split("");
            }

            for (int i = 0; i < map.length; i++) {

                StringBuilder number = new StringBuilder();
                boolean isAdjacent = false;

                for (int j = 0; j < map[0].length; j++) {

                    char currentChar = map[i][j].charAt(0);

                    if (isNumber(currentChar)) {
                        number.append(currentChar);
                        isAdjacent = isAdjacent || checkAdjacentSymbols(map, i, j);
                    }

                    if (!isNumber(currentChar) || j == map[0].length - 1) {
                        if (number.isEmpty() && isAdjacent) {
                            sum += Integer.parseInt(number.toString());
                        }
                        number.setLength(0);
                        isAdjacent = false;
                    }
                }
            }

            System.out.println(sum);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static int numberOfDigits(int number) {
        return (int) Math.log10(number) + 1;
    }

    private static boolean checkAdjacentSymbols(String[][] map, int i, int j) {
        final int[][] dirs = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (isOutOfBounds(newI, newJ, map)) {
                continue;
            }

            char currentChar = map[newI][newJ].charAt(0);


            if (isSymbol(currentChar)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    private static boolean isSymbol(char c) {
        return c != '.' && !isNumber(c);
    }

    private static boolean isOutOfBounds(int i, int j, String[][] map) {
        return i < 0 || i >= map.length || j < 0 || j >= map[0].length;
    }

}
