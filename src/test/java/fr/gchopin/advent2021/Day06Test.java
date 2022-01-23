package fr.gchopin.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class Day06Test {

    private static Stream<Arguments> part1() {
        return Stream.of(
            Arguments.of("day06_test.txt", 5934L),
            Arguments.of("day06.txt", null));
    }

    @ParameterizedTest
    @MethodSource
    void part1(String filename, Long expectedResult) {
        Input input = new Input(filename);
        String inputValues = input.getLines().get(0);

        List<Integer> integers =
            inputValues.replaceAll(",", "")
                       .chars()
                       .mapToObj(c -> Integer.parseInt(String.valueOf((char) c)))
                       .toList();

        long actualResult = Day06.numberOfFishAfter80Days(integers);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }

    private static Stream<Arguments> part1WithPart2Method() {
        return Stream.of(
            Arguments.of("day06_test.txt", 5934L),
            Arguments.of("day06.txt", null));
    }

    @ParameterizedTest
    @MethodSource
    void part1WithPart2Method(String filename, Long expectedResult) {
        Input input = new Input(filename);
        String inputValues = input.getLines().get(0);

        List<Integer> integers =
            inputValues.replaceAll(",", "")
                       .chars()
                       .mapToObj(c -> Integer.parseInt(String.valueOf((char) c)))
                       .toList();

        long actualResult = Day06.numberOfFishAfterXDays(integers, 80);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }

    private static Stream<Arguments> part2() {
        return Stream.of(
            Arguments.of("day06_test.txt", 26984457539L),
            Arguments.of("day06.txt", null));
    }

    @ParameterizedTest
    @MethodSource
    void part2(String filename, Long expectedResult) {
        Input input = new Input(filename);
        String inputValues = input.getLines().get(0);

        List<Integer> integers =
            inputValues.replaceAll(",", "")
                       .chars()
                       .mapToObj(c -> Integer.parseInt(String.valueOf((char) c)))
                       .toList();

        long actualResult = Day06.numberOfFishAfterXDays(integers, 256);

        if (expectedResult != null) {
            Assertions.assertEquals(expectedResult, actualResult);
        } else {
            System.out.printf("Result for %s is %s%n", filename, actualResult);
        }
    }
}
