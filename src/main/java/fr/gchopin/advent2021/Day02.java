package fr.gchopin.advent2021;

import java.util.List;

public class Day02 {

    private Day02() {
    }

    public static int planCourse(List<String> instructions) {
        int horizontalPosition = 0;
        int depth = 0;

        for (String line : instructions) {
            String[] splits = line.split(" ");
            String instruction = splits[0];
            int value = Integer.parseInt(splits[1]);
            switch (instruction) {
                case "forward" -> horizontalPosition += value;
                case "down" -> depth += value;
                case "up" -> depth -= value;
                default -> throw new IllegalStateException("Unknown instruction : " + instruction);
            }
        }

        return horizontalPosition * depth;
    }

    public static int planCourseWithAim(List<String> instructions) {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (String line : instructions) {
            String[] splits = line.split(" ");
            String instruction = splits[0];
            int value = Integer.parseInt(splits[1]);
            switch (instruction) {
                case "forward" -> {
                    horizontalPosition += value;
                    depth += value * aim;
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
                default -> throw new IllegalStateException("Unknown instruction : " + instruction);
            }
        }

        return horizontalPosition * depth;
    }
}
