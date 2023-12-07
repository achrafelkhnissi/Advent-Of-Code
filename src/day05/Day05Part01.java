package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05Part01 {
    private static final String filePath = "src/day05/input.txt";

    public static void main(String[] args) {

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            Iterator<String> iterator = lines.iterator();

            ArrayList<Integer> seeds = getSeeds(iterator.next());

            ArrayList< ArrayList<Integer> > seedToSoilMap = getMap(iterator);
            HashMap<Integer, Integer> seedToSoil = getHashMap(seedToSoilMap);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<Integer> getSeeds(String line) {
        return getValues(line.split(":")[1]);
    }

    private static ArrayList<Integer> getValues(String line) {
        ArrayList<Integer> values = new ArrayList<>();

        String[] valueSplit = line.split(" ");

        for (String value : valueSplit) {

            if (value.isEmpty())
                continue;

            values.add(Integer.parseInt(value));
        }

        return values;
    }

    private static ArrayList< ArrayList<Integer> > getMap(Iterator<String> iterator) {
        ArrayList< ArrayList<Integer> > map = new ArrayList<>();
        String line;

        // Skip all empty lines
        while (iterator.hasNext()) {
            line = iterator.next();
            if (!line.isEmpty())
                break;
        }

        while (iterator.hasNext()) {
            line = iterator.next();
            if (line.isEmpty())
                break;

            map.add(getValues(line));
        }

        return map;
    }

    private static HashMap<Integer, Integer> getHashMap(ArrayList< ArrayList<Integer> > map) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (ArrayList<Integer> record : map) {
            int destRangeStart = record.get(0);
            int srcRangeStart = record.get(1);
            int rangeLength = record.get(2);

            // Calculate the range of values
            for (int i = 0; i < rangeLength; i++) {
                hashMap.putIfAbsent(destRangeStart + i, srcRangeStart + i);
            }
        }
        return hashMap;
    }

}
