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

    // Tests for LIC #8

    @Test
    public void givenFivePointsWithRadiusGreaterThan1AndRadiusIs1_whenLIC8_thenTrue() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1.1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius);

        assertTrue(result);
    }

    @Test
    public void givenFivePointsWithRadiusEqualTo1AndRadiusIs1_whenLIC8_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius);

        assertFalse(result);
    }

    @Test
    public void givenFivePointsWithRadiusLessThan1AndRadiusIs1_whenLIC8_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(0.9, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius);

        assertFalse(result);
    }

    @Test
    public void givenFiveLinearPointsWithRadiusGreaterThan1AndRadiusIs1_whenLIC8_thenTrue() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1.1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius);

        assertTrue(result);
    }

    @Test
    public void givenLessThanFivePoints_whenLIC8_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1.1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = 1;

        boolean result = launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius);

        assertFalse(result);
    }

    @Test
    public void givenRadiusIsLessThan0_whenLIC8_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius = -0.01;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius));
    }

    @Test
    public void givenPointsIsNull_whenLIC8_thenThrowIllegalArgumentException() {
        int aPts = 1, bPts = 1;
        double radius = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC8(null, aPts, bPts, radius));
    }

    @Test
    public void givenFivePointsAndAPtsLessThan1_whenLIC8_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 0, bPts = 1;
        double radius = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius));
    }

    @Test
    public void givenFivePointsAndAPtsIs1AndBPtsIs2_whenLIC8_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 2;
        double radius = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC8(points, aPts, bPts, radius));
    }

    // Tests for LIC 11

    @Test
    public void givenThreePointsAndgPtsIs1_whenLIC11_thenTrue(){
        List<Point> points = List.of(new Point(1, 0), new Point(0, 0), new Point(0, 0));
        int gPts = 1;

        boolean result = launchInterceptorConditionCollection.LIC11(points, gPts);

        assertTrue(result);

    }

    @Test
    public void givenThreePointsAndgPtsIs1_whenLIC11_thenFalse(){
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(1, 0));
        int gPts = 1;

        boolean result = launchInterceptorConditionCollection.LIC11(points, gPts);

        assertFalse(result);

    }

    @Test
    public void givenFivePointsAndgPtsIs2_whenLIC11_thenTrue(){
        List<Point> points = List.of(new Point(0, 0), new Point(4, 0), new Point(1, 0), new Point(1, 0), new Point(3, 0));
        int gPts = 2;

        boolean result = launchInterceptorConditionCollection.LIC11(points, gPts);

        assertTrue(result);

    }

    @Test
    public void givenNullPointsAndgPtsIs2_whenLIC11_thenThrowIllegalArgumentException(){
        List<Point> points = null;
        int gPts = 2;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC11(points,gPts));

    }

    @Test
    public void givenThreePointsAndgPtsGreaterThanNumberOfPointsMinus2_whenLIC11_thenThrowIllegalArgumentException(){
        List<Point> points = List.of(new Point(1, 0), new Point(0, 0), new Point(0, 0));
        int gPts = 5;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC11(points,gPts));
    }

    @Test
    public void givenThreePointsAndgPtsLesserThanOne_whenLIC11_thenThrowIllegalArgumentException(){
        List<Point> points = List.of(new Point(1, 0), new Point(0, 0), new Point(0, 0));
        int gPts = -1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC11(points,gPts));
    }

    @Test
    public void givenTwoPointsAndgPtsIs1_whenLIC11_thenFalse(){
        List<Point> points = List.of(new Point(1, 0), new Point(0, 0));
        int gPts = 1;

        boolean result = launchInterceptorConditionCollection.LIC11(points, gPts);

        assertFalse(result);
    }

    @Test
    public void givenFourPointsAndgPtsIs3_whenLIC11_thenThrowIllegalArgumentException(){
        List<Point> points = List.of(new Point(1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 0));
        int gPts = 3;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC11(points,gPts));
    }

    // Tests for LIC 12

    @Test
    public void givenThreePointsWithDistanceGreaterThan2AndLessThan1AndKPtsIs1AndLength1Is1p9AnsLength2Is2p1_whenLIC12_thenTrue() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 1;
        double length1 = 1.9;
        double length2 = 2.1;

        boolean result = launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWithDistanceGreaterThan2AndLessThan1AndKPtsIs1AndLength1Is2AnsLength2Is2p1_whenLIC12_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 1;
        double length1 = 2;
        double length2 = 2.1;

        boolean result = launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWithDistanceGreaterThan2AndLessThan1AndKPtsIs1AndLength1Is2p1AnsLength2Is1p9_whenLIC12_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 1;
        double length1 = 2.1;
        double length2 = 1.9;

        boolean result = launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    public void givenTwoPoints_whenLIC12_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(2, 0));
        int kPts = 1;
        double length1 = 1.9;
        double length2 = 2.1;

        boolean result = launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsAndKPtsIs0_whenLIC12_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 0;
        double length1 = 1.9;
        double length2 = 2.1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2));
    }

    @Test
    public void givenThreePointsAndKPtsIs2_whenLIC12_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        int kPts = 0;
        double length1 = 1.9;
        double length2 = 2.1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC12(points, kPts, length1, length2));
    }
}
