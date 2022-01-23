package fr.gchopin.advent2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 {

    private Day06() {
    }

    /**
     * This method is too slow for part 2
     */
    public static long numberOfFishAfter80Days(List<Integer> initialFish) {
        List<Integer> fish = new ArrayList<>(initialFish);
        for (int i = 0; i < 80; i++) {
            fish = increaseDayByOne(fish);
        }
        return fish.size();
    }

    private static List<Integer> increaseDayByOne(List<Integer> fish) {
        // Count how many new fish will be added on this day
        long numberNewFish = fish.stream()
                                 .filter(i -> i == 0)
                                 .count();

        // Change all counter values (decrement or reset)
        fish = fish.stream()
                   .map(i -> i > 0 ? i - 1 : 6)
                   .collect(Collectors.toList());

        // Add new fish
        List<Integer> fishToAdd = IntStream.iterate(8, IntUnaryOperator.identity()).limit(numberNewFish).boxed().toList();

        fish.addAll(fishToAdd);

        return fish;
    }

    public static long numberOfFishAfterXDays(List<Integer> initialFish, int numberOfDays) {
        // Initialize the map : counts all fish (value) with the same counter (key)
        Map<Integer, Long> fish = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            // At first, all counter values have 0 fish
            fish.put(i, 0L);
        }

        // Count the initial fish
        for (Integer f : initialFish) {
            fish.computeIfPresent(f, (key, value) -> value + 1);
        }

        // Simulate the days
        for (int i = 0; i < numberOfDays; i++) {
            fish = increaseDayByOne(fish);
        }
        return fish.values().stream().reduce(Long::sum).orElse(0L);
    }

    private static Map<Integer, Long> increaseDayByOne(Map<Integer, Long> fish) {
        // Count how many new fish will be added on this day
        long numberNewFish = fish.get(0);

        Map<Integer, Long> newFish = new HashMap<>();
        // Decrement all counters from 0 to 7 (included)
        for (int i = 0; i <= 7; i++) {
            newFish.put(i, fish.get(i + 1));
        }
        // Reset all counters that got to 0, and reset them to 6
        newFish.computeIfPresent(6, (key, value) -> value + numberNewFish);

        // Add new fish with a counter of 8
        newFish.put(8, numberNewFish);

        return newFish;
    }
}
