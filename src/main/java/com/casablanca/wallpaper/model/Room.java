package com.casablanca.wallpaper.model;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Room {

    private static final String DIMENSIONS_DELIMITER = "x";
    private static final String DIMENSIONS_PATTERN = "(\\d+)x(\\d+)x(\\d+)";

    private final int length;
    private final int width;
    private final int height;

    /**
     * @param dimensions length x width x height
     */
    public Room(String dimensions) {
        var p = Pattern.compile(DIMENSIONS_PATTERN);
        var m = p.matcher(dimensions);
        if (!m.matches()) {
            throw new IllegalArgumentException("The value does not match the pattern: " + dimensions);
        }
        var xes = dimensions.split(DIMENSIONS_DELIMITER);
        this.length = Integer.parseInt(xes[0]);
        this.width = Integer.parseInt(xes[1]);
        this.height = Integer.parseInt(xes[2]);
    }

    /**
     * Calculates number of total square feet of wallpaper the company should order for all rooms from input
     *
     * @return calculated square
     */
    public int calculateSquare() {
        return (2 * length * width) + (2 * width * height) + (2 * height * length) + additionalArea();
    }

    /**
     * Cubic shape
     *
     * @return true if the room is cubic length == width == height
     */
    public boolean isCubic() {
        return (length == width) && (length == height);
    }

    /**
     * Method calculates additional area
     * sort dimensions and multiply the first two dimensions
     *
     * @return additional area
     */
    private int additionalArea() {
        List<Integer> dimensions = new java.util.ArrayList<>(List.of(length, width, height));
        Collections.sort(dimensions);
        return dimensions.get(0) * dimensions.get(1);
    }

    @Override
    public String toString() {
        return length + DIMENSIONS_DELIMITER + width + DIMENSIONS_DELIMITER + height;
    }
}
