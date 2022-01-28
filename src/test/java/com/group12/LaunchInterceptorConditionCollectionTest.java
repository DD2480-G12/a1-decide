package com.group12;

import com.group12.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchInterceptorConditionCollectionTest {

    private LaunchInterceptorConditionCollection launchInterceptorConditionCollection;

    @BeforeEach
    public void setup() {
        launchInterceptorConditionCollection = new LaunchInterceptorConditionCollection();
    }

    // Tests for LIC #0

    @Test
    public void givenTwoPointsWithDistanceGreaterThan2AndLengthIs2_whenLIC0_thenTrue() {
        List<Point> points = List.of(new Point(2, 2), new Point(4, 1.99));
        double length = 2;

        boolean result = launchInterceptorConditionCollection.LIC0(points, length);

        assertTrue(result);
    }

    @Test
    public void givenTwoPointsWithDistanceEqualTo2AndLengthIs2_whenLIC0_thenFalse() {
        List<Point> points = List.of(new Point(2, 2), new Point(4, 2));
        double length = 2;

        boolean result = launchInterceptorConditionCollection.LIC0(points, length);

        assertFalse(result);
    }

    @Test
    public void givenTwoPointsWithDistanceLessThan2AndLengthIs2_whenLIC0_thenFalse() {
        List<Point> points = List.of(new Point(2, 2.5), new Point(3.9, 1.99));
        double length = 2;

        boolean result = launchInterceptorConditionCollection.LIC0(points, length);

        assertFalse(result);
    }

    @Test
    public void givenLengthIsLessThan0_whenLIC0_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(2, 2), new Point(4, 1.99));
        double length = -0.01;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC0(points, length));
    }

    @Test
    public void givenPointsIsNull_whenLIC0_thenThrowIllegalArgumentException() {
        double length = 2;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC0(null, length));
    }

    @Test
    public void givenOnePoint_whenLIC0_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(2, 2));
        double length = 2;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC0(points, length));
    }

    // Tests for LIC #1

    @Test
    public void givenThreePointsWithRadiusGreaterThan1AndRadiusIs1_whenLIC1_thenTrue() {
        List<Point> points = List.of(new Point(-1, 0), new Point(0, 1.1), new Point(1, 0));
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC1(points, radius);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWithRadiusEqualTo1AndRadiusIs1_whenLIC1_thenFalse() {
        List<Point> points = List.of(new Point(-1, 0), new Point(0, 1), new Point(1, 0));
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC1(points, radius);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWithRadiusLessThan1AndRadiusIs1_whenLIC1_thenFalse() {
        List<Point> points = List.of(new Point(-1, 0), new Point(0, 1), new Point(0.9, 0));
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC1(points, radius);

        assertFalse(result);
    }

    @Test
    public void givenThreeLinearPointsWithRadiusGreaterThan1AndRadiusIs1_whenLIC1_thenTrue() {
        List<Point> points = List.of(new Point(-1, 0), new Point(0, 0), new Point(1.1, 0));
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC1(points, radius);

        assertTrue(result);
    }

    @Test
    public void givenRadiusIsLessThan0_whenLIC1_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(-1, 0), new Point(0, 1), new Point(1, 0));
        double radius = -0.01;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC1(points, radius));
    }

    @Test
    public void givenPointsIsNull_whenLIC1_thenThrowIllegalArgumentException() {
        double radius = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC1(null, radius));
    }

    @Test
    public void givenTwoPoint_whenLIC1_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(2, 2), new Point(1, 1));
        double radius = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC1(points, radius));
    }

    // Tests for LIC #3

    @Test
    public void givenThreePointsWithAreaGreaterThan1AndAreaIs1_whenLIC3_thenTrue() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(2, 0));
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC3(points, area);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWithAreaEqualTo1AndAreaIs1_whenLIC3_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1), new Point(2, 0));
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC3(points, area);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWithAreaLessThan1AndAreaIs1_whenLIC3_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1), new Point(1, 0));
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC3(points, area);

        assertFalse(result);
    }

    @Test
    public void givenAreaIsLessThan0_whenLIC3_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(2, 0));
        double area = -0.01;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC3(points, area));
    }

    @Test
    public void givenPointsIsNull_whenLIC3_thenThrowIllegalArgumentException() {
        double area = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC3(null, area));
    }

    @Test
    public void givenTwoPoints_whenLIC3_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2));
        double area = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC3(points, area));
    }

    // Tests for LIC #7

    @Test
    public void givenThreePointsWhereTwoPointsHasDistanceGreaterThan2AndKPtsIs1AndLengthIs2_whenLIC7_thenTrue() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0.01));
        int kPts = 1;
        double length1 = 2;

        boolean result = launchInterceptorConditionCollection.LIC7(points, kPts, length1);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWhereTwoPointsHasDistanceEqualTo2AndKPtsIs1AndLengthIs2_whenLIC7_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 1;
        double length1 = 2;

        boolean result = launchInterceptorConditionCollection.LIC7(points, kPts, length1);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWhereTwoPointsHasDistanceLessThan2AndKPtsIs1AndLengthIs2_whenLIC7_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(1.99, 0));
        int kPts = 1;
        double length1 = 2;

        boolean result = launchInterceptorConditionCollection.LIC7(points, kPts, length1);

        assertFalse(result);
    }

    @Test
    public void givenTwoPoints_whenLIC7_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(2, 0.01));
        int kPts = 1;
        double length1 = 2;

        boolean result = launchInterceptorConditionCollection.LIC7(points, kPts, length1);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsAndKPtsIs0AndLengthIs2_whenLIC7_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0.01));
        int kPts = 0;
        double length1 = 2;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC7(points, kPts, length1));
    }

    @Test
    public void givenThreePointsAndKPtsIs2AndLengthIs2_whenLIC7_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0.01));
        int kPts = 2;
        double length1 = 2;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC7(points, kPts, length1));
    }

    @Test
    public void givenLengthIsLessThan0_whenLIC7_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0.01));
        int kPts = 1;
        double length1 = -0.1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC7(points, kPts, length1));
    }
}
