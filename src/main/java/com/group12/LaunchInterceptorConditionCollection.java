package com.group12;

import com.group12.model.Point;

import java.util.List;

public class LaunchInterceptorConditionCollection {

    /**
     * LIC #0 checks if there exists at least one set of two consecutive data points that are a distance greater than
     * the given <b>length1</b>.
     *
     * @param points  list of radar echos ({@link Point})
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
        if (doubleCompare(length1, 0) < 0) {
            throw new IllegalArgumentException("Length cannot be less than zero");
        }
        for (int i = 0; i < points.size() - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            if (doubleCompare(distance(point1, point2), length1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #1 checks if there exists at least one set of three consecutive data points that cannot all be contained
     * within or on a circle of the given <b>radius</b> (RADIUS1).
     *
     * @param points  list of radar echos ({@link Point})
     * @param radius1 has to be greater than 0 (zero)
     * @return true if at least one set of three consecutive data points forms a circle with radius greater than
     * given <b>radius</b>, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>radius</b> is less than 0 (zero)
     */
    public boolean LIC1(List<Point> points, double radius1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            throw new IllegalArgumentException("There has to be at least three points, size of given points="
                    + points.size());
        }
        if (doubleCompare(radius1, 0) < 0) {
            throw new IllegalArgumentException("Radius cannot be less than zero");
        }
        for (int i = 0; i < points.size() - 2; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            Point point3 = points.get(i + 2);
            if (doubleCompare(radiusOfSmallestCircle(point1, point2, point3), radius1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #3 checks if there exists at least one set of three consecutive data points that are the vertices of a triangle
     * with an area greater than <b>area1</b>.
     *
     * @param points list of radar echos ({@link Point})
     * @param area1 has to be greater than or equal to 0 (zero)
     * @return true if at least one set of three consecutive data points forms a triangle with an area greater than
     * given <b>area1</b>, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>area1</b> is less than 0 (zero)
     */
    public boolean LIC3(List<Point> points, double area1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            throw new IllegalArgumentException("There has to be at least three points, size of given points="
                    + points.size());
        }
        if (doubleCompare(area1, 0) < 0) {
            throw new IllegalArgumentException("Area cannot be less than zero");
        }
        for (int i = 0; i < points.size() - 2; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            Point point3 = points.get(i + 2);
            if (doubleCompare(areaOfTriangle(point1, point2, point3), area1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #7 checks if at least one set of two data points separated by exactly <b>kPts</b> consecutive intervening
     * points that are a distance greater than <b>length1</b> apart. The condition is not met when <b>points</b> has
     * less than three elements.
     *
     * @param points  points list of radar echos ({@link Point})
     * @param kPts    number of consecutive intervening radar echos
     * @param length1 has to be greater than 0 (zero)
     * @return true if two points <b>kPts</b> apart has a distance greater than <b>length1</b> or number of radar echos
     * is less than 3, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>points</b> is null, if
     *                                  <b>kPts</b> is less than 1 or greater than two less than size of <b>points</b>, or if <b>length1</b> is less
     *                                  than 0 (zero)
     */
    public boolean LIC7(List<Point> points, int kPts, double length1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            return false;
        }
        if (kPts < 1 || kPts > points.size() - 2) {
            throw new IllegalArgumentException("kPts has to be within 1 and points.size() - 2 inclusive");
        }
        if (doubleCompare(length1, 0) < 0) {
            throw new IllegalArgumentException("Length cannot be less than zero");
        }
        for (int i = 0; i < points.size() - kPts - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + kPts + 1);
            if (doubleCompare(distance(point1, point2), length1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #12 checks if at least one set of two data points, separated by exatcly <b>kPts</b> consecutive intervening
     * points, which are a distance greater than the length <b>length1</b> apart. In addition, there exists at least
     * one set of two data points (which can be the same or different from thw two data points just mentioned),
     * separated by exactly <b>kPts</b> consecutive intervening points, that are a distance less than the length
     * <b>length2</b> apart.
     *
     * @param points  points list of radar echos ({@link Point})
     * @param kPts    number of consecutive intervening radar echos
     * @param length1 has to be greater than 0 (zero)
     * @param length2 has to be freater tan 0 (zero)
     * @return true if the above conditions are met. false otherwise or if there are less than three points.
     * @throws IllegalArgumentException <ul>
     *     <li>
     *         if <b>points</b> is null.
     *     </li>
     *     <li>
     *         if <b>kPts</b> less than 1 or greater than the number of points minus 2.
     *     </li>
     *     <li>
     *         if <b>length1</b> or <b>length2</b> is negative.
     *     </li>
     * </ul>
     */
    public boolean LIC12(List<Point> points, int kPts, double length1, double length2) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            return false;
        }
        if (kPts < 1 || kPts > points.size() - 2) {
            throw new IllegalArgumentException("kPts has to be within 1 and points.size() - 2 inclusive");
        }
        if (doubleCompare(length1, 0) < 0 || doubleCompare(length2, 0) < 0) {
            throw new IllegalArgumentException("Length cannot be less than zero");
        }
        boolean twoPointsHasDistanceGreaterThanLength1 = false;
        for (int i = 0; i < points.size() - kPts - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + kPts + 1);
            if (doubleCompare(distance(point1, point2), length1) > 0) {
                twoPointsHasDistanceGreaterThanLength1 = true;
            }
        }
        if (!twoPointsHasDistanceGreaterThanLength1) {
            return false;
        }
        for (int i = 0; i < points.size() - kPts - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + kPts + 1);
            if (doubleCompare(distance(point1, point2), length2) < 0) {
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

    private int doubleCompare(double a, double b) {
        if (Math.abs(a - b) < 0.000001) return 0;
        if (a < b) return -1;
        return 1;
    }

    private double radiusOfSmallestCircle(Point point1, Point point2, Point point3) {
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();
        double x3 = point3.getX();
        double y3 = point3.getY();

        // Cramer's Rule
        double a = 2 * (x1 - x2);
        double b = 2 * (y1 - y2);
        double c = x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2;
        double d = 2 * (x1 - x3);
        double e = 2 * (y1 - y3);
        double f = x1 * x1 + y1 * y1 - x3 * x3 - y3 * y3;
        double g = a * e - b * d;

        // If g=0, the three points are linear. The longest distance among three points is the diameter.
        if (doubleCompare(g, 0.0) == 0) {
            double d1 = distance(point1, point2);
            double d2 = distance(point2, point3);
            double d3 = distance(point1, point3);
            return Math.max(Math.max(d1, d2), d3) / 2;
        }

        // Otherwise, calculate the center of circle. The distance should be equal to 3 points.
        double circleX = (c * e - f * b) / g;
        double circleY = (a * f - d * c) / g;
        return distance(point1, new Point(circleX, circleY));
    }

    private double areaOfTriangle(Point point1, Point point2, Point point3) {
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();
        double x3 = point3.getX();
        double y3 = point3.getY();
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2;
    }
}
