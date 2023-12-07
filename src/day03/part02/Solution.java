package day03.part02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution {
    private static final String filePath = "src/day03/part02/input.txt";

    public static void main(String[] args) {

        int sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            String[][] map = new String[lines.size()][lines.get(0).length()];
            HashMap<String, ArrayList<Integer>> gearMap = new HashMap<>();

            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i).split("");
            }

            for (int i = 0; i < map.length; i++) {

                StringBuilder number = new StringBuilder();
                boolean isAdjacent = false; // is adjacent to *
                String symbolIndex = null; // the previous symbol

                for (int j = 0; j < map[0].length; j++) {

                    char currentChar = map[i][j].charAt(0);

                    if (isNumber(currentChar)) {
                        number.append(currentChar);
                        String currentSymbol = getAdjacentSymbol(map, i, j);
                        isAdjacent = isAdjacent || currentSymbol != null;

                        if (currentSymbol != null && !gearMap.containsKey(currentSymbol)) {
                            gearMap.put(currentSymbol, new ArrayList<>());
                        }

                        // Don't change the symbolIndex if the current symbol is null
                        symbolIndex = symbolIndex == null ? currentSymbol : symbolIndex;

                    }

                    if (!isNumber(currentChar) || j == map[0].length - 1) {
                        if (number.length() == 0 && isAdjacent) {

                            if (gearMap.containsKey(symbolIndex)) {
                                ArrayList<Integer> gear = gearMap.get(symbolIndex);
                                gear.add(Integer.parseInt(number.toString()));
                                gearMap.put(symbolIndex, gear);
                            } else {
                                gearMap.put(symbolIndex, new ArrayList<>(List.of(Integer.parseInt(number.toString()))));
                            }
                        }
                        number.setLength(0);
                        isAdjacent = false;
                        symbolIndex = null;
                    }
                }
            }

            // Multiply the numbers that are adjacent to the same *
            for (Map.Entry<String, ArrayList<Integer>> entry : gearMap.entrySet()) {
                ArrayList<Integer> gear = entry.getValue();
                sum +=  getGearRatio(gear);
            }

            System.out.println(sum);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getAdjacentSymbol(String[][] map, int i, int j) {
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
                return arrayToKey(new String[]{newI + "", newJ + ""});
            }
        }

        return null;
    }

    private static boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    private static boolean isSymbol(char c) {
        return c == '*';
    }

    private static boolean isOutOfBounds(int i, int j, String[][] map) {
        return i < 0 || i >= map.length || j < 0 || j >= map[0].length;
    }

    private static String arrayToKey(String[] arr) {
        return Arrays.toString(arr);
    }

    private static String[] keyToArray(String key) {
        return key.substring(1, key.length() - 1).split(", ");
    }

    private static int getGearRatio(ArrayList<Integer> gear) {

        if (gear.size() == 1) {
            return 0;
        }

        int ratio = 1;

        for (Integer number : gear) {
            ratio *= number;
        }

        return ratio;
    }

}
