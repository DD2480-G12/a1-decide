package com.group12;

import com.group12.model.Point;

import java.util.List;

public class LaunchInterceptorConditionCollection {

    private static final double PI = 3.1415926535;

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
     * LIC #2 checks if the given angle which is constructed from the 3 given data points is not in the range of Pi's deviations (exclusive).
     *
     * @param points  list of radar echods ({@link Point})
     * @param epsilon the deviation from Pi
     * @return true if the angle is smaller than Pi - <b>epsilon</b> or greater than Pi + <b>epsilon</b>
     * @throws IllegalArgumentException is thrown if <b>points</b> is null, <b>points</b> does not contain 3 elements,
     *                                  if the first or last element of <b>points</b> coincides with the second element of <b>points</b>,
     *                                  if 0 <= <b>epsilon</b> < Pi does not hold.
     */
    public boolean LIC2(List<Point> points, double epsilon) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            throw new IllegalArgumentException("There has to be atleast three points");
        }
        if (doubleCompare(epsilon, 0) == -1 || doubleCompare(epsilon, PI) >= 0) {
            throw new IllegalArgumentException("Epsilon must hold these conditions: 0 <= Epsilon < Pi");
        }
        for (int i = 0; i < points.size() - 2; i += 3) {
            Point point1 = points.get(i);
            Point vertex = points.get(i + 1);
            Point point2 = points.get(i + 2);
            if (doubleCompare(point1.getX(), vertex.getX()) == 0 && doubleCompare(point1.getY(), vertex.getY()) == 0 ||
                    (doubleCompare(point2.getX(), vertex.getX()) == 0 && doubleCompare(point2.getY(), vertex.getY()) == 0)
            ) {
                continue;
            }
            double angle = angleFromThreePoints(vertex, point1, point2);
            if (doubleCompare(angle, PI - epsilon) == -1 || doubleCompare(angle, PI + epsilon) == 1) {
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
     * @param area1  has to be greater than or equal to 0 (zero)
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
     * LIC #6
     *
     * @param points points list of radar echos ({@link Point})
     * @param nPts number of consecutive intervening radar echos
     * @param dist has to be greater than or equal to zero
     * @return true if there is a set of <b>nPts</b> consecutive points in <b>points</b> (and points.size() >= 3) s.t.
     * 1) the distance between the point and the line defined by the first and last points in the set is greater
     * than <b>dist</b> or 2) the first and last points in the set are considered equal and there is a point
     * between them at a distance greater than <b>dist</b> from the coinciding point between the first and the last.
     * false otherwise.
     * @throws IllegalArgumentException is thrown if <b>points</b> is null, if
     * <b>nPts</b> is less than 3 or greater than the size of <b>points</b>, or if <b>dist</b> is less
     * than 0 (zero)
     */

    public boolean LIC6(List<Point> points, int nPts, double dist) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }

        // The condition is not met when NUMPOINTS < 3
        if (points.size() < 3) {
            return false;
        }

        // Requirement on nPts is 3 <= N_PTS <= NUMPOINTS
        if (nPts < 3 || nPts > points.size()) {
            throw new IllegalArgumentException("Parameter nPts has to be within 3 and points.size() inclusive");
        }

        // Require DIST to be greater than or equal to zero
        if (doubleCompare(dist, 0) < 0) {
            throw new IllegalArgumentException("Dist cannot be less than zero");
        }

        // For each line defined by the points points[i] and points[i+nPts-1]
        for (int i = 0; i < points.size() - nPts + 1; i++) {

            // The points {points[i]..points[i+nPts-1]} define a set of nPts points
            Point linePoint1 = points.get(i);
            Point linePoint2 = points.get(i + nPts - 1);

            // If linePoint1 and linePoint2 are equal, choose a point for distance calculations which is the mean
            if (pointsEqual(linePoint1, linePoint2)) {
                double meanX = linePoint1.getX()/2 + linePoint2.getX()/2;
                double meanY = linePoint1.getY()/2 + linePoint2.getY()/2;

                Point coincidingPoint = new Point(meanX, meanY);

                for(int j = i + 1; j < i + nPts - 1; j++) {
                    Point p = points.get(j);

                    double d = distance(coincidingPoint, p);

                    if (doubleCompare(d, dist) == 1) {
                        return true;
                    }
                }
            } else {
                // For each point points[i+1]..points[i+nPts-2]
                for(int j = i + 1; j < i + nPts - 1; j++) {
                    Point p = points.get(j);

                    double d = distanceFromLine(linePoint1, linePoint2, p);

                    if (doubleCompare(d, dist) == 1) {
                        return true;
                    }
                }
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
     * LIC #8 checks if there exists at least one set of three data points separated by exactly <b>aPts</b>
     * and <b>bPts</b> consecutive intervening points respectively that cannot all be contained within or on a circle
     * of the given <b>radius1</b> (RADIUS1). The condition is not met when less than 5 points are provided.
     *
     * @param points  list of radar echos ({@link Point})
     * @param aPts    number of consecutive intervening points between 1st and 2nd points (greater than 0).
     * @param bPts    number of consecutive intervening points between 2nd and 3rd points (greater than 0).
     * @param radius1 has to be greater than 0 (zero)
     * @return true if at least one set of three data points separated by <b>aPts</b> and <b>bPts</b> points forms a
     * circle with radius greater than given <b>radius1</b>, false otherwise.
     * Also return false when less than 5 points are provided.
     * @throws IllegalArgumentException is thrown if <b>radius</b> is less than 0 (zero)
     */
    public boolean LIC8(List<Point> points, int aPts, int bPts, double radius1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 5) {
            return false;
        }
        if (aPts < 1 || bPts < 1 || aPts + bPts > points.size() - 3) {
            throw new IllegalArgumentException("Invalid aPts or bPts: must be greater than 0 and form at least 1 separation");
        }
        if (doubleCompare(radius1, 0) < 0) {
            throw new IllegalArgumentException("Radius cannot be less than zero");
        }
        for (int i = 0; i < points.size() - 2 - aPts - bPts; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + aPts + 1);
            Point point3 = points.get(i + aPts + bPts + 2);
            if (doubleCompare(radiusOfSmallestCircle(point1, point2, point3), radius1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #10 checks if there exists at least one set of three data points separated by exactly <b>ePts</b> and <b>fPts</b>
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater
     * than area1. The condition is not met when <b>points</b> has less than 5 elements.
     *
     * @param points list of radar echos ({@link Point})
     * @param ePts first consecutive intervening points
     * @param fPts second consecutive intervening points
     * @param area1 the area of the triangle
     * @return true if at least one set of three data points separated by exactly <b>ePts</b> and <b>fPts</b>
     * consecutive intervening points, respectively forms a triangle with an area greater than <b>area1</b>, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>points</b> is null,
     *                                  if <b>ePts</b> or <b>fPts</b> is less than 1, or if the length
     *                                  of <b>points</b> - 3 is less than <b>ePts</b> + <b>fPts</b>.
     */
    public boolean LIC10(List<Point> points, int ePts, int fPts, double area1) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 5) {
            return false;
        }
        if (ePts < 1 || fPts < 1) {
            throw new IllegalArgumentException("ePts and fPts must both be 1 or larger");
        }
        if (points.size() - 3 < ePts + fPts) {
            throw new IllegalArgumentException("The size of points - 3 must be more or equal to ePts + fPts");
        }
        if (doubleCompare(area1, 0) == -1) {
            throw new IllegalArgumentException("area1 cannot be less than zero");
        }
        for (int i = 0; i < points.size() - ePts - fPts - 2; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1 + ePts);
            Point point3 = points.get(i + 2 + ePts + fPts);
            if (doubleCompare(areaOfTriangle(point1, point2, point3), area1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #11 checks if there exists two data points which are separated by <b>gPts</b> consecutive data points where
     * the x-coordinate of the former data point is greater than the x-coordinate of the latter data point.
     * The condition is not met if the number data points is less than three.
     *
     * @param points list of radar echos ({@link Point})
     * @param gPts number of consecutive intervening points
     * @return true if there exists two points that are separeated by <b>gPts</b> points where the former point has a
     *         greater x-coordinate than the latter point, if not then false. Also false if <b>points.size()</b> < 3
     * @throws IllegalArgumentException is thrown if <b>points</b> is null or the following does not hold:
     *                                  1 <= <b>gPts</b> <= <b>points.size()</b> - 2
     */
    public boolean LIC11(List<Point> points, int gPts) throws IllegalArgumentException{
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 3) {
            return false;
        }
        if(!(1 <= gPts) || !(gPts <= points.size() - 2)){
            throw new IllegalArgumentException("Invalid gPts or points, the following must hold: 1 <= gPts <= points.size() - 2");
        }
        for(int i = 0; i < points.size() - gPts - 1; i++){
            int j = i + gPts + 1;
            Point pointi = points.get(i);
            Point pointj = points.get(j);
            if(doubleCompare(pointj.getX() - pointi.getX(), 0) == -1){
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

    /**
     * LIC #14 checks if there exists at least one set of three data points separated by exactly <b>ePts</b> and <b>fPts</b>
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater
     * than <b>area1</b>, AND if there exist three data points separated by exactly <b>ePts</b> and <b>fPts</b> consecutive
     * intervening points, respectively, that are the vertices of a triangle with area less than
     * <b>area2</b>. The condition is not met when <b>points</b> has less than 5 elements.
     *
     * @param points list of radar echos ({@link Point})
     * @param ePts first consecutive intervening points
     * @param fPts second consecutive intervening points
     * @param area1 the area of the first triangle
     * @param area2 the area of the second triangle
     * @return true if at least one set of three data points separated by exactly <b>ePts</b> and <b>fPts</b>
     * consecutive intervening points, respectively forms a triangle with an area greater than <b>area1</b>,
     * and if at least one set of three data points separated by exactly <b>ePts</b> and <b>fPts</b>
     * consecutive intervening points, respectively forms a triangle with an area less than <b>area2</b>,
     * and the length of <b>points</b> is 5 or more, false otherwise.
     * @throws IllegalArgumentException is thrown if <b>points</b> is null,
     *                                  or if <b>ePts</b> or <b>fPts</b> is less than 1,
     *                                  or if the length of <b>points</b> - 3 is less than <b>ePts</b> + <b>fPts</b>,
     *                                  or if <b>area1</b> or <b>area2</b> is less than 0.
     */
    public boolean LIC14(List<Point> points, int ePts, int fPts, double area1, double area2) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 5) {
            return false;
        }
        if (ePts < 1 || fPts < 1) {
            throw new IllegalArgumentException("ePts and fPts must both be 1 or larger");
        }
        if (points.size() - 3 < ePts + fPts) {
            throw new IllegalArgumentException("The size of points - 3 must be more or equal to ePts + fPts");
        }
        if (doubleCompare(area1, 0) == -1 || doubleCompare(area2, 0) == -1) {
            throw new IllegalArgumentException("area1 and area2 cannot be less than zero");
        }

        boolean existsAreaGreaterThanArea1 = false;
        boolean existsAreaLessThanArea2 = false;

        for (int i = 0; i < points.size() - ePts - fPts - 2; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1 + ePts);
            Point point3 = points.get(i + 2 + ePts + fPts);
            if (doubleCompare(areaOfTriangle(point1, point2, point3), area1) == 1) {
                existsAreaGreaterThanArea1 = true;
            }
            if (doubleCompare(areaOfTriangle(point1, point2, point3), area2) == -1) {
                existsAreaLessThanArea2 = true;
            }
            if (existsAreaGreaterThanArea1 && existsAreaLessThanArea2) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC #13 checks if there exists at least one set of three data points separated by exactly <b>aPts</b>
     * and <b>bPts</b> consecutive intervening points respectively that cannot all be contained within or on a circle
     * of the given <b>radius1</b> (RADIUS1). And there exists at least another set of three data points separated by
     * exactly <b>aPts</b> and <b>bPts</b> consecutive intervening points respectively that can be contained within or
     * on a circle of the given <b>radius2</b> (RADIUS2). The condition is not met when less than 5 points are provided.
     *
     * @param points  list of radar echos ({@link Point})
     * @param aPts    number of consecutive intervening points between 1st and 2nd points (greater than 0).
     * @param bPts    number of consecutive intervening points between 2nd and 3rd points (greater than 0).
     * @param radius1 has to be greater than 0 (zero)
     * @param radius2 has to be greater than 0 (zero)
     * @return true if at least one set of three data points separated by <b>aPts</b> and <b>bPts</b> points forms a
     * circle with radius greater than given <b>radius1</b>, and at least one set of three data points separated by
     * <b>aPts</b> and <b>bPts</b> points forms a circle with radius less than given <b>radius2</b>, false otherwise.
     * Also return false when less than 5 points are provided.
     * @throws IllegalArgumentException is thrown if arguments are illegal.
     */
    public boolean LIC13(List<Point> points, int aPts, int bPts, double radius1, double radius2) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException("Points list cannot be null");
        }
        if (points.size() < 5) {
            return false;
        }
        if (aPts < 1 || bPts < 1 || aPts + bPts > points.size() - 3) {
            throw new IllegalArgumentException("Invalid aPts or bPts: must be greater than 0 and form at least 1 separation");
        }
        if (doubleCompare(radius1, 0) < 0 || doubleCompare(radius2, 0) < 0) {
            throw new IllegalArgumentException("Radius cannot be less than zero");
        }

        boolean threePointsHasRadiusGreaterThanRadius1 = false;
        for (int i = 0; i < points.size() - 2 - aPts - bPts; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + aPts + 1);
            Point point3 = points.get(i + aPts + bPts + 2);
            if (doubleCompare(radiusOfSmallestCircle(point1, point2, point3), radius1) == 1) {
                threePointsHasRadiusGreaterThanRadius1 = true;
                break;
            }
        }

        if (!threePointsHasRadiusGreaterThanRadius1) {
            return false;
        }

        for (int i = 0; i < points.size() - 2 - aPts - bPts; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + aPts + 1);
            Point point3 = points.get(i + aPts + bPts + 2);
            if (doubleCompare(radiusOfSmallestCircle(point1, point2, point3), radius2) != 1) {
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

    private boolean pointsEqual(Point p1, Point p2)
    {
        return doubleCompare(p1.getX(), p2.getX()) == 0 && doubleCompare(p1.getY(), p2.getY()) == 0;
    }

    private double distanceFromLine(Point linePoint1, Point linePoint2, Point point)
    {
        double x0 = point.getX();
        double y0 = point.getY();

        double x1 = linePoint1.getX();
        double x2 = linePoint2.getX();
        double y1 = linePoint1.getY();
        double y2 = linePoint2.getY();

        double base_mult_height = Math.abs((x2 - x1) * (y1 - y0) - (x1 - x0) * (y2 - y1));
        double base = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        return base_mult_height/base;
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

    private double angleFromThreePoints(Point vertex, Point point1, Point point2) {
        double ax = vertex.getX() - point1.getX();
        double ay = vertex.getY() - point1.getY();
        double bx = vertex.getX() - point2.getX();
        double by = vertex.getY() - point2.getY();
        double maga = Math.sqrt(Math.pow(ax, 2) + Math.pow(ay, 2));
        double magb = Math.sqrt(Math.pow(bx, 2) + Math.pow(by, 2));
        return Math.acos((ax * bx + ay * by) / (maga * magb));
    }
}
