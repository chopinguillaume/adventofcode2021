package fr.gchopin.advent2021;

import java.util.List;

public class Day03 {
    public static int powerConsumption(List<String> binaryNumbers) {
        int binarySize = binaryNumbers.get(0).length();

        int[] numberOfZeroesPerPosition = new int[binarySize];
        int[] numberOfOnesPerPosition = new int[binarySize];

        // Read the input and count the bits for each position
        for (String binary : binaryNumbers) {
            for (int i = 0; i < binary.length(); i++) {
                char c = binary.charAt(i);
                if (c == '0') {
                    numberOfZeroesPerPosition[i]++;
                } else {
                    numberOfOnesPerPosition[i]++;
                }
            }
        }

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();

        // Create the rates from the number of bits
        for (int i = 0; i < binarySize; i++) {
            if (numberOfZeroesPerPosition[i] > numberOfOnesPerPosition[i]) {
                gammaRate.append('0');
                epsilonRate.append('1');
            } else {
                gammaRate.append('1');
                epsilonRate.append('0');
            }
        }

        return binaryValue(gammaRate.toString()) * binaryValue(epsilonRate.toString());
    }

    static int binaryValue(String binary) {
        return Integer.parseUnsignedInt(binary, 2);
    }

    public static int lifeSupportRating(List<String> binaryNumbers) {
        String oxygenGeneratorRating = oxygenGeneratorRating(binaryNumbers);
        String CO2ScrubberRating = CO2ScrubberRating(binaryNumbers);

        return binaryValue(oxygenGeneratorRating) * binaryValue(CO2ScrubberRating);
    }


    private static String oxygenGeneratorRating(List<String> binaryNumbers) {
        List<String> filteredNumbers = recursiveMostCommonFilterAtIndex(binaryNumbers, 0);
        return filteredNumbers.get(0);
    }
    private static String CO2ScrubberRating(List<String> binaryNumbers) {
        List<String> filteredNumbers = recursiveLeastCommonFilterAtIndex(binaryNumbers, 0);
        return filteredNumbers.get(0);
    }

    private static List<String> recursiveMostCommonFilterAtIndex(List<String> binaryNumbers, int index) {
        char mostCommonBit = mostCommonBit(binaryNumbers, index);
        List<String> filtered = binaryNumbers.stream().filter(s -> s.charAt(index) == mostCommonBit).toList();
        if (filtered.size() == 1) {
            return filtered;
        } else {
            return recursiveMostCommonFilterAtIndex(filtered, index + 1);
        }
    }
    private static List<String> recursiveLeastCommonFilterAtIndex(List<String> binaryNumbers, int index) {
        char leastCommonBit = leastCommonBit(binaryNumbers, index);
        List<String> filtered = binaryNumbers.stream().filter(s -> s.charAt(index) == leastCommonBit).toList();
        if (filtered.size() == 1) {
            return filtered;
        } else {
            return recursiveLeastCommonFilterAtIndex(filtered, index + 1);
        }
    }

    private static char mostCommonBit(List<String> binaryNumbers, int index) {
        int numberOfZeroes = 0;
        int numberOfOnes = 0;

        for (String binaryNumber : binaryNumbers) {
            if (binaryNumber.charAt(index) == '0') {
                numberOfZeroes++;
            } else {
                numberOfOnes++;
            }
        }

        if (numberOfZeroes > numberOfOnes) {
            return '0';
        } else {
            return '1';
        }
    }
    private static char leastCommonBit(List<String> binaryNumbers, int index) {
        char mostCommonBit = mostCommonBit(binaryNumbers, index);
        return mostCommonBit == '0' ? '1' : '0';
    }
}
