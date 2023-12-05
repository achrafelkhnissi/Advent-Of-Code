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

                for (int j = 0; j < map[0].length; j++) {

                    char currentChar = map[i][j].charAt(0);

                    if (Character.isDigit(currentChar)) {
                        number.append(currentChar);
                    } else if (!number.isEmpty()) {
                        // Check if the number is adjacent to a symbol
                        boolean isAdjacentToSymbol = checkAdjacentSymbols(map, String.valueOf(number), i, j - number.length());

                        if (isAdjacentToSymbol) {
                            sum += Integer.parseInt(number.toString());
                        }
                        number = new StringBuilder();
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

    private static boolean checkAdjacentSymbols(String[][] map, String number, int i, int j) {

        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + number.length(); l++) {

                // Check if the current element is out of bounds
                if (k < 0 || k >= map.length || l < 0 || l >= map[0].length) {
                    continue;
                }

                if (!Character.isDigit(map[k][l].charAt(0)) && !map[k][l].equals(".")) {
                    return true;
                }
            }
        }

        return false;
    }
}
