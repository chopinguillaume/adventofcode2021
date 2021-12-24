package fr.gchopin.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class Day05Test {

    private static Stream<Arguments> part1() {
        return Stream.of(
            Arguments.of("day05_test.txt", 5),
            Arguments.of("day05.txt", 8350),
            Arguments.of("day05.txt", null));
    }

    @ParameterizedTest
    @MethodSource
    void part1(String filename, Integer expectedResult) {
        Input input = new Input(filename);
        List<String> inputValues = input.getLines();

        int actualResult = Day05.numberOfOverlapsWithoutDiagonals(inputValues);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }

    private static Stream<Arguments> part2() {
        return Stream.of(
            Arguments.of("day05_test.txt", 12),
            Arguments.of("day05.txt", 19374),
            Arguments.of("day05.txt", null));
    }

    @ParameterizedTest
    @MethodSource
    void part2(String filename, Integer expectedResult) {
        Input input = new Input(filename);
        List<String> inputValues = input.getLines();

        int actualResult = Day05.numberOfOverlapsWithDiagonals(inputValues);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }
}
