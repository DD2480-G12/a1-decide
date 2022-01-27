package com.group12;

import com.group12.model.Point;

import java.util.List;

public class LaunchInterceptorConditionCollection {

    /**
     * LIC #0 checks if there exists at least one set of two consecutive data points that are a distance greater than
     * the given <b>length1</b>.
     *
     * @param points list of radar echos ({@link Point})
     * @param length1 has to be greater than 0 (zero)
     * @return true if two consecutive points are greater than the given length1, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>length1</b> is less than 0 (zero)
     */
    public boolean LIC0(List<Point> points, double length1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 2) {
            throw new IllegalArgumentException("There has to be at least two points, size of given points="
                    + points.size());
        }
        if (length1 < 0) {
            throw new IllegalArgumentException("Length cannot be less than zero");
        }
        for (int i = 0; i < points.size() - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            if (distance(point1, point2) > length1) {
                return true;
            }
        }
        return false;
    }

    private double distance(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
