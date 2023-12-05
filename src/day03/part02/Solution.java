package day03.part02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final String filePath = "src/day03/part02/input.txt";

    public static void main(String[] args) {

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
