package fr.gchopin.advent2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {


    public static int numberOfOverlapsWithoutDiagonals(List<String> input) {
        return numberOfOverlaps(input, Day05::drawWithoutDiagonals);
    }

    public static int numberOfOverlapsWithDiagonals(List<String> input) {
        return numberOfOverlaps(input, Day05::drawWithDiagonals);
    }

    private static int numberOfOverlaps(List<String> input, DrawingFunction drawingFunction) {
        Grid grid = new Grid();

        Pattern pattern = Pattern.compile("(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)");
        for (String inputLine : input) {
            Matcher matcher = pattern.matcher(inputLine);
            boolean matchFound = matcher.find();
            if (!matchFound) {
                throw new IllegalStateException("Error while reading line : " + inputLine);
            }
            int x1 = Integer.parseInt(matcher.group("x1"));
            int y1 = Integer.parseInt(matcher.group("y1"));
            int x2 = Integer.parseInt(matcher.group("x2"));
            int y2 = Integer.parseInt(matcher.group("y2"));

            drawingFunction.apply(grid, x1, x2, y1, y2);
        }

        return grid.countOverlappingPoints();

    }

    private static void drawWithoutDiagonals(Grid grid, int x1, int x2, int y1, int y2) {
        if (x1 == x2) {
            drawVerticalLine(grid, x1, y1, y2);
        } else if (y1 == y2) {
            drawHorizontalLine(grid, x1, x2, y2);
        }
    }

    private static void drawWithDiagonals(Grid grid, int x1, int x2, int y1, int y2) {
        if (x1 == x2) {
            drawVerticalLine(grid, x1, y1, y2);
        } else if (y1 == y2) {
            drawHorizontalLine(grid, x1, x2, y2);
        } else {
            drawDiagonalLine(grid, x1, x2, y1, y2);
        }
    }

    private static void drawHorizontalLine(Grid grid, int x1, int x2, int y) {
        int xMin = Math.min(x1, x2);
        int xMax = Math.max(x1, x2);

        while (xMin <= xMax) {
            grid.increment(xMin, y);
            xMin++;
        }
    }

    private static void drawVerticalLine(Grid grid, int x, int y1, int y2) {
        int yMin = Math.min(y1, y2);
        int yMax = Math.max(y1, y2);

        while (yMin <= yMax) {
            grid.increment(x, yMin);
            yMin++;
        }
    }

    private static void drawDiagonalLine(Grid grid, int x1, int x2, int y1, int y2) {

        int xA = Math.min(x1, x2);
        int xB = Math.max(x1, x2);
        int yA = xA == x1 ? y1 : y2;
        int yB = xB == x1 ? y1 : y2;

        if (xA < xB && yA < yB) {
            while (xA <= xB) {
                grid.increment(xA, yA);
                xA++;
                yA++;
            }
        }

        if (xA < xB && yA > yB) {
            while (xA <= xB) {
                grid.increment(xA, yA);
                xA++;
                yA--;
            }
        }
    }

    static class Grid {
        Map<Integer, Map<Integer, Integer>> points = new HashMap<>();

        public void increment(int x, int y) {
            points.putIfAbsent(x, new HashMap<>());
            points.get(x).putIfAbsent(y, 0);

            points.get(x).computeIfPresent(y, (key, currentValue) -> currentValue + 1);
        }

        public int countOverlappingPoints() {
            return points.values().stream()
                         .map(values -> values.values().stream()
                                              .filter(i -> i > 1)
                                              .count())
                         .reduce(0L, Long::sum)
                         .intValue();
        }
    }

    @FunctionalInterface
    public interface DrawingFunction {
        public void apply(Grid grid, int x1, int x2, int y1, int y2);
    }
}
