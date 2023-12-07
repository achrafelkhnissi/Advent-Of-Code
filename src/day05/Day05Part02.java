package day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05Part02 {
    private static final String filePath = "src/day04/input.txt";

    public static void main(String[] args) {


        try
        {
            List<String> lines = Files.readAllLines(Path.of(filePath));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
