package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05Part01 {
    private static final String filePath = "src/day04/input.txt";

    public static void main(String[] args) {


        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
