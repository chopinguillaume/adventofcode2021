package fr.gchopin.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class Day01Test {

    @ParameterizedTest
    @MethodSource
    void day01(String filename, Integer expectedResult) {
        Input input = new Input(filename);
        List<Integer> measurements = input.getIntegers();

        int actualResult = Day01.numberOfIncreasingMeasurements(measurements);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.println("Result for %s is %s".formatted(filename, actualResult));
        }
    }

    private static Stream<Arguments> day01() {
        return Stream.of(
                Arguments.of("day01_test", 7),
                Arguments.of("day01", null));
    }
}
