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
}
