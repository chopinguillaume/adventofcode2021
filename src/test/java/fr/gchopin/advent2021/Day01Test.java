package fr.gchopin.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class Day01Test {


    private static Stream<Arguments> part1() {
        return Stream.of(
            Arguments.of("day01_test", 7),
            Arguments.of("day01", null));
    }

    @ParameterizedTest
    @MethodSource
    void part1(String filename, Integer expectedResult) {
        Input input = new Input(filename);
        List<Integer> measurements = input.getIntegers();

        int actualResult = Day01.numberOfIncreasingMeasurements(measurements);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }

    private static Stream<Arguments> part2() {
        return Stream.of(
            Arguments.of("day01_test", 5),
            Arguments.of("day01", null));
    }

    @ParameterizedTest
    @MethodSource
    void part2(String filename, Integer expectedResult) {
        Input input = new Input(filename);
        List<Integer> measurements = input.getIntegers();

        int actualResult = Day01.numberOfIncreasingMeasurementWindows(measurements);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }

}
