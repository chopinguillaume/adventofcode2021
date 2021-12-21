package fr.gchopin.advent2021;

import java.util.List;

/**
 * Day01
 */
public class Day01 {

    public static int numberOfIncreasingMeasurements(List<Integer> measurements) {
        int numberOfIncreasingMeasurements = 0;
        Integer previousMeasurement = null;

        for (Integer m : measurements) {
            if (previousMeasurement != null && m > previousMeasurement) {
                numberOfIncreasingMeasurements++;
            }
            previousMeasurement = m;
        }

        return numberOfIncreasingMeasurements;
    }
}