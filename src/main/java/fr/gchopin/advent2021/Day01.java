package fr.gchopin.advent2021;

import java.util.List;

/**
 * Day01
 */
public class Day01 {

    private Day01() {
    }

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

    public static int numberOfIncreasingMeasurementWindows(List<Integer> measurements) {
        int numberOfIncreasingWindows = 0;

        Integer previousWindow = null;

        for (int i = 0; i < measurements.size() - 2; i++) {
            int newWindow = measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2);
            if(previousWindow != null && newWindow > previousWindow){
                numberOfIncreasingWindows++;
            }
            previousWindow = newWindow;
        }

        return numberOfIncreasingWindows;
    }
}
