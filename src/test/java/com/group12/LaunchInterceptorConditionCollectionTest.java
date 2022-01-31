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

    // Tests for LIC #2

    @Test
    public void givenThreeValidPointsAndValidDeviation_whenLIC2_thenTrue(){
        List<Point> points = List.of(new Point(4, 0), new Point(0, 0), new Point(4, 3));
        double epsilon = 2.0;

        boolean result = launchInterceptorConditionCollection.LIC2(points,epsilon);

        assertTrue(result);
    }


    @Test
    public void givenTwoSetsOfPointsWithFirstSetInvalidAndValidDeviation_when_LIC2_thenTrue(){
        List<Point> points = List.of(new Point(4, 0), new Point(4, 0), new Point(4, 3), new Point(4, 0), new Point(0, 0), new Point(4, 3));
        double epsilon = 2.0;

        boolean result = launchInterceptorConditionCollection.LIC2(points,epsilon);

        assertTrue(result);
    }

    @Test
    public void givenNullPointsAndValidDeviation_whenLIC2_thenThrowIllegalArgumentException(){
        double epsilon = 2;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC2(null,epsilon));
    }

    @Test
    public void givenInvalidAmountOfPointsAndValidDeviation_whenLIC2_thenThrowIllegalArgumentException(){
        List<Point> points = List.of(new Point(4, 0), new Point(0, 0));
        double epsilon = 2;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC2(points,epsilon));
    }

    @Test
    public void givenPointsThatCoincideAndValidDeviation_whenLIC2_thenFalse(){
        List<Point> points = List.of(new Point(4, 0), new Point(4, 0), new Point(4, 3));
        double epsilon = 2;
        boolean result = launchInterceptorConditionCollection.LIC2(points,epsilon);
        assertFalse(result);
    }

    @Test
    public void givenThreeValidPointsAndInvalidDeviation_whenLIC2_thenThrowIllegalArgumentException(){
        List<Point> points = List.of(new Point(4, 0), new Point(0, 0), new Point(4, 3));
        double epsilon = 5;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC2(points,epsilon));
    }

    @Test
    public void givenThreeValidPointsThatCreateAngleBiggerThanPiMinusEpsilon_whenLIC2_thenFalse(){
        List<Point> points = List.of(new Point(4, 0), new Point(0, 0), new Point(4, 3));
        double epsilon = 2.7;

        boolean result = launchInterceptorConditionCollection.LIC2(points,epsilon);
        assertFalse(result);
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

    // Tests for LIC #4

    @Test
    public void givenAPointOnQuadIAndAPointOnQuadIIAndQPtsIs2AndQuads1_whenLIC4_thenTrue() {
        List<Point> points = List.of(new Point(1, 1), new Point(-1, 1));
        int qPts = 2;
        int quads = 1;

        boolean result = launchInterceptorConditionCollection.LIC4(points, qPts, quads);

        assertTrue(result);
    }

    @Test
    public void givenAPointOnPositiveXAxisAndAPointOnNegativeXAxisAndQPtsIs2AndQuads1_whenLIC4_thenTrue() {
        List<Point> points = List.of(new Point(1, 0), new Point(-1, 0));
        int qPts = 2;
        int quads = 1;

        boolean result = launchInterceptorConditionCollection.LIC4(points, qPts, quads);

        assertTrue(result);
    }

    @Test
    public void givenAPointOnNegativeYAxisAPointInQuadIVAndQPtsIs2AndQuads1_whenLIC4_thenTrue() {
        List<Point> points = List.of(new Point(0, -1), new Point(1, -1));
        int qPts = 2;
        int quads = 1;

        boolean result = launchInterceptorConditionCollection.LIC4(points, qPts, quads);

        assertTrue(result);
    }

    @Test
    public void givenAPointInOriginAndAPointInQuadIAndQPtsIs2AndQuads1_whenLIC4_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1));
        int qPts = 2;
        int quads = 1;

        boolean result = launchInterceptorConditionCollection.LIC4(points, qPts, quads);

        assertFalse(result);
    }

    @Test
    public void givenAPointOnNegativeYAxisAndAPointInQuadIIIAndQPtsIs2AndQuads1_whenLIC4_thenFalse() {
        List<Point> points = List.of(new Point(0, -1), new Point(-1, -1));
        int qPts = 2;
        int quads = 1;

        boolean result = launchInterceptorConditionCollection.LIC4(points, qPts, quads);

        assertFalse(result);
    }

    @Test
    public void givenTwoPointsAndQPtsIs3_whenLIC4_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1));
        int qPts = 3;
        int quads = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC4(points, qPts, quads));
    }

    @Test
    public void givenTwoPointsAndQPtsIs1_whenLIC4_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1));
        int qPts = 1;
        int quads = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC4(points, qPts, quads));
    }

    @Test
    public void givenQuadsIs0_whenLIC4_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1));
        int qPts = 2;
        int quads = 0;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC4(points, qPts, quads));
    }

    @Test
    public void givenQuadsIs4_whenLIC4_thenIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(1, 1));
        int qPts = 2;
        int quads = 4;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC4(points, qPts, quads));
    }

    // Tests for LIC #6
    @Test
    public void LIC6_NPTS_TooLow_ThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1)
        );
        double dist = 5;
        int n_pts = 2;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_NPTS_TooLarge_ThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1)
        );
        double dist = 5;
        int n_pts = points.size()+1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_NPTS_IsThree_NoThrow() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1)
        );
        double dist = 5;
        int n_pts = 3;

        assertDoesNotThrow(() -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_NPTS_IsNumPoints_NoThrow() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1)
        );
        double dist = 5;
        int n_pts = points.size();

        assertDoesNotThrow(() -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_NumPoints_TooLow_IsFalse() {
        List<Point> twoPoints = List.of(
                new Point(1, 0), new Point(0, 1)
        );
        double dist = 0.0;
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(twoPoints, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_NumPoints_IsThree_NoThrow() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0)
        );
        double dist = 5;
        int n_pts = 3;

        assertDoesNotThrow(() -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_Dist_TooLow_ThrowsIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1),
                new Point(0,0)
        );
        double dist = -1;
        int n_pts = 3;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_Dist_Zero_NoThrow() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1),
                new Point(0,0)
        );
        double dist = 0;
        int n_pts = 3;

        assertDoesNotThrow(() -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_Dist_Zero_IsTrue() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1),
                new Point(0,0)
        );
        double dist = 0;
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_Dist_Positive_NoThrow() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1),
                new Point(0,0)
        );
        double dist = 0;
        int n_pts = 3;

        assertDoesNotThrow(() -> launchInterceptorConditionCollection.LIC6(points, n_pts, dist));
    }

    @Test
    public void LIC6_Dist_Positive_IsTrue() {
        List<Point> points = List.of(
                new Point(1, 0), new Point(0, 1),
                new Point(-1,0), new Point(0, -1),
                new Point(0,0)
        );
        double dist = 0;
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_PointToLineDist_CloserThanDist_IsFalse1() {
        double mid = Math.sqrt(2)/2;
        List<Point> points = List.of(
                new Point(1, 0), new Point(mid, mid),
                new Point(0, 1), new Point(-mid, mid),
                new Point(-1,0), new Point(-mid, -mid),
                new Point(0, -1), new Point(mid, -mid),
                new Point(1, 0)
        );

        double dist = 1;
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToLineDist_CloserThanDist_IsFalse2() {
        double mid = Math.sqrt(2)/2;
        List<Point> points = List.of(
                new Point(1, 0), new Point(mid, mid),
                new Point(0, 1), new Point(-mid, mid),
                new Point(-1,0), new Point(-mid, -mid),
                new Point(0, -1), new Point(mid, -mid),
                new Point(1, 0)
        );

        double dist = 1;
        int n_pts = 4;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToLineDist_EqualToDist_IsFalse2() {
        double mid = Math.sqrt(2)/2;
        List<Point> points = List.of(
                new Point(1, 0), new Point(mid, mid),
                new Point(0, 1), new Point(-mid, mid),
                new Point(-1,0), new Point(-mid, -mid),
                new Point(0, -1), new Point(mid, -mid),
                new Point(1, 0)
        );

        double dist = 1;
        int n_pts = 5;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToLineDist_GreaterThanDist_IsTrue() {
        double mid = Math.sqrt(2)/2;
        List<Point> points = List.of(
                new Point(1, 0), new Point(mid, mid),
                new Point(0, 1), new Point(-mid, mid),
                new Point(-1,0), new Point(-mid, -mid),
                new Point(0, -1), new Point(mid, -mid),
                new Point(1, 0)
        );

        double dist = 0.99998;
        int n_pts = 5;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_PointToPointDist_SmallerThanDist_IsFalse() {
        List<Point> points = List.of(
                new Point(0, 0), new Point(0, 0), new Point(0, 0),
                new Point(2, 1), new Point(1, 2), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(0,0)
        );

        double dist = Math.sqrt(2)+0.000002;
        int n_pts = 3;
        // Expect to find a point where line dist = sqrt(2)
        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToPointDist_EqualToDist_IsFalse() {
        List<Point> points = List.of(
                new Point(0, 0), new Point(0, 0), new Point(0, 0),
                new Point(0, 0), new Point(0, 0), new Point(0,0),
                new Point(2, 1), new Point(1, 2), new Point(2, 1)
        );

        double dist = Math.sqrt(2);
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToPointDist_GreaterThanDist_IsTrue() {
        List<Point> points = List.of(
                new Point(2, 1), new Point(1, 2), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(0, 0),
                new Point(0, 0), new Point(0, 0), new Point(0,0)
        );

        double dist = Math.sqrt(2)-0.000002;
        int n_pts = 3;

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_LineToPointDist_NPTS_Equals_NumPoints_GreaterThanDist_IsTrue() {
        List<Point> points = List.of(
                new Point(2.000001, 1.000001), new Point(0, 0), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(2.000002,1.000002)
        );

        double dist = Math.sqrt(0.5) - 0.000005;
        int n_pts = points.size();

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_LineToPointDist_NPTS_Equals_NumPoints_EqualToDist_IsFalse() {
        List<Point> points = List.of(
                new Point(2.000001, 1.000001), new Point(0, 0), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(2.000002,1.000002)
        );

        double dist = Math.sqrt(0.5);
        int n_pts = points.size();

        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
    }

    @Test
    public void LIC6_PointToPointDist_NPTS_Equals_NumPoints_GreaterThanDist_IsTrue() {
        List<Point> points = List.of(
                new Point(1.9999996, 0.9999996), new Point(0, 0), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(2.0000004,1.0000004)
        );

        double dist = Math.sqrt(5) - 0.000005;
        int n_pts = points.size();

        // Expect that first and last points are considered equal and largest distance = sqrt(5)
        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertTrue(result);
    }

    @Test
    public void LIC6_PointToPointDist_NPTS_Equals_NumPoints_EqualToDist_IsFalse() {
        List<Point> points = List.of(
                new Point(1.9999996, 0.9999996), new Point(0, 0), new Point(2, 1),
                new Point(0, 0), new Point(0, 0), new Point(2.0000004,1.0000004)
        );

        double dist = Math.sqrt(5);
        int n_pts = points.size();

        // Expect that first and last points are considered equal and largest distance = sqrt(5)
        boolean result = launchInterceptorConditionCollection.LIC6(points, n_pts, dist);
        assertFalse(result);
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

    // Tests for LIC #10

    @Test
    public void givenThreePointsWithAreaGreaterThan1AndAreaIs1AndEPtsEqFPtsEq1_whenLIC10_thenTrue() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(0, 2),
                new Point(0, 0), new Point(2, 0));
        int ePts = 1;
        int fPts = 1;
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWithAreaEqualTo1AndAreaIs1AndEPtsEqFPtsEq1_whenLIC10_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(1, 1),
                new Point(0, 0), new Point(2, 0));
        int ePts = 1;
        int fPts = 1;
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWithAreaLessThan1AndAreaIs1AndEPtsEqFPtsEq1_whenLIC10_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(1, 1),
                new Point(0, 0), new Point(1, 0));
        int ePts = 1;
        int fPts = 1;
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area);

        assertFalse(result);
    }

    @Test
    public void lenOfPointsLessThan5_whenLIC10_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(1, 1), new Point(0, 0));
        int ePts = 1;
        int fPts = 1;
        double area = 1;

        boolean result = launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area);

        assertFalse(result);
    }

    @Test
    public void givenAreaLessThan0_whenLIC10_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(0, 2),
                new Point(0, 0), new Point(2, 0));
        int ePts = 1;
        int fPts = 1;
        double area = -0.1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area));
    }

    @Test
    public void givenPointsIsNull_whenLIC10_thenThrowIllegalArgumentException() {
        int ePts = 1;
        int fPts = 1;
        double area = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC10(null, ePts, fPts, area));
    }

    @Test
    public void ePtsLessThan1_whenLIC10_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(2, 0),
                new Point(2, 0), new Point(2, 0));
        int ePts = 0;
        int fPts = 1;
        double area = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area));
    }

    @Test
    public void lenOfPointsMinus3LessThanEPtsPlusFPts_whenLIC10_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(0, 2),
                new Point(0, 2), new Point(0, 2));

        int ePts = 10;
        int fPts = 10;
        double area = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC10(points, ePts, fPts, area));
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

    // Tests for LIC #13

    @Test
    public void givenFivePointsWithRadiusGreaterThan1AndRadius1Is1AndRadius2Is2_whenLIC13_thenTrue() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1.1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = 1;
        double radius2 = 2;

        boolean result = launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2);

        assertTrue(result);
    }

    @Test
    public void givenFivePointsWithRadiusIs1AndRadius1IsLessThan1AndRadius2Is1_whenLIC13_thenTrue() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = 0.9;
        double radius2 = 1;

        boolean result = launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2);

        assertTrue(result);
    }

    @Test
    public void givenFivePointsWithRadiusGreaterThan1AndRadius1Is1AndRadius2Is1_whenLIC13_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1.1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = 1;
        double radius2 = 1;

        boolean result = launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2);

        assertFalse(result);
    }

    @Test
    public void givenFivePointsWithRadiusGreaterThan1AndRadius2Is1AndRadius2Is2_whenLIC13_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1.1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = 2;
        double radius2 = 2;

        boolean result = launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2);

        assertFalse(result);
    }

    @Test
    public void givenLessThanFivePoints_whenLIC13_thenFalse() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1.1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = 1;
        double radius2 = 2;

        boolean result = launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2);

        assertFalse(result);
    }

    @Test
    public void givenRadiusIsLessThan0_whenLIC13_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        );
        int aPts = 1, bPts = 1;
        double radius1 = -0.01;
        double radius2 = 1;

        assertThrows(IllegalArgumentException.class,
                () -> launchInterceptorConditionCollection.LIC13(points, aPts, bPts, radius1, radius2));
    }


    // Tests for LIC #14

    @Test
    public void givenThreePointsWithAreaEq2AndArea1Is1AndArea2Is3_whenLIC14_thenTrue() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(2, 0),
                                     new Point(0, 0), new Point(0, 2));
        int ePts = 1;
        int fPts = 1;
        double area1 = 1;
        double area2 = 3;

        boolean result = launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2);

        assertTrue(result);
    }

    @Test
    public void givenThreePointsWithAreaEq2AndArea1Is3AndArea2Is3_whenLIC14_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(2, 0),
                                     new Point(0, 0), new Point(0, 2));
        int ePts = 1;
        int fPts = 1;
        double area1 = 3;
        double area2 = 3;

        boolean result = launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2);

        assertFalse(result);
    }

    @Test
    public void givenThreePointsWithAreaEq2AndArea1Is1AndArea2Is1_whenLIC14_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(2, 0),
                                     new Point(0, 0), new Point(0, 2));
        int ePts = 1;
        int fPts = 1;
        double area1 = 1;
        double area2 = 1;

        boolean result = launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2);

        assertFalse(result);
    }

    @Test
    public void lenOfPointsLessThan5_whenLIC14_thenFalse() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(1, 1), new Point(0, 0));
        int ePts = 1;
        int fPts = 1;
        double area1 = 1;
        double area2 = 1;

        boolean result = launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2);

        assertFalse(result);
    }

    @Test
    public void givenPointsIsNull_whenLIC14_thenThrowIllegalArgumentException() {
        int ePts = 1;
        int fPts = 1;
        double area1 = 1;
        double area2 = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC14(null, ePts, fPts, area1, area2));
    }

    @Test
    public void ePtsLessThan1_whenLIC14_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(2, 0),
                                     new Point(2, 0), new Point(2, 0));
        int ePts = 0;
        int fPts = 1;
        double area1 = 1;
        double area2 = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2));
    }

    @Test
    public void lenOfPointsMinus3LessThanEPtsPlusFPts_whenLIC14_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 2), new Point(0, 2),
                                     new Point(0, 2), new Point(0, 2));
        int ePts = 10;
        int fPts = 10;
        double area1 = 1;
        double area2 = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2));
    }

    @Test
    public void area1LessThan0_whenLIC14_thenThrowIllegalArgumentException() {
        List<Point> points = List.of(new Point(0, 0), new Point(0, 0), new Point(0, 2),
                                     new Point(0, 0), new Point(2, 0));
        int ePts = 1;
        int fPts = 1;
        double area1 = -0.1;
        double area2 = 1;

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorConditionCollection.LIC14(points, ePts, fPts, area1, area2));
    }
}
