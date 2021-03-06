package com.group12;

import com.group12.model.Connector;
import com.group12.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchInterceptorTest {

    /**
     * Input for this test is generated by extracting and concatenating arguments from each LICs unit tests that
     * meets the condition (i.e. return true) of each LIC, except for LIC 9 and 12 since it was a bit tricky. And in
     * order to compensate for this, LCM is built in a way that the connectors of LIC 9 and 12 is not considering them
     * when calculating PUM.
     *
     * Note: this doesn't mean that the given test input doesn't evaluate LIC 9 and 12 to true, instead this approach
     * was taken in order to eliminate ambiguity.
     *
     * @throws Exception
     */
    @Test
    public void givenValidInputAndFUVIsAllTrue_whenDecide_thenLaunch() throws Exception {
        Parameters parameters = new Parameters();
        parameters.length1 = 2;
        parameters.radius1 = 1;
        parameters.epsilon = 2.0;
        parameters.area1 = 1;
        parameters.qPts = 2;
        parameters.quads = 1;
        parameters.dist = 0;
        parameters.nPts = 3;
        parameters.kPts = 1;
        parameters.aPts = 1;
        parameters.bPts = 1;
        parameters.cPts = 1;
        parameters.dPts = 1;
        parameters.ePts = 1;
        parameters.fPts = 1;
        parameters.gPts = 2;
        parameters.radius2 = 2;
        parameters.area2 = 3;
        List<Point> points = List.of(
                new Point(2, 2), new Point(4, 1.99), new Point(-1, 0), new Point(0, 1.1),
                new Point(1, 0), new Point(4, 0), new Point(0, 0), new Point(4, 3),
                new Point(0, 0), new Point(0, 2), new Point(2, 0), new Point(1, 1),
                new Point(-1, 1), new Point(1, 0), new Point(0, 0), new Point(1, 0),
                new Point(0, 1), new Point(-1, 0), new Point(0, -1), new Point(1, 0),
                new Point(0, 1), new Point(-1, 0), new Point(0, -1), new Point(0, 0),
                new Point(0, 0), new Point(1, 0), new Point(2, 0.01), new Point(-1, 0),
                new Point(0, 0), new Point(0, 1.1), new Point(0, 0), new Point(1, 0),
                new Point(0, 0), new Point(0, 0), new Point(0, 2), new Point(0, 0),
                new Point(2, 0), new Point(0, 0), new Point(4, 0), new Point(1, 0),
                new Point(1, 0), new Point(3, 0), new Point(-1, 0), new Point(0, 0),
                new Point(0, 1.1), new Point(0, 0), new Point(1, 0), new Point(0, 0),
                new Point(0, 0), new Point(2, 0), new Point(0, 0), new Point(0, 2)
        );
        Connector[][] lcm = {
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
        };
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        LaunchInterceptor launchInterceptor = new LaunchInterceptor(parameters, points, lcm, puv);

        String launchText = tapSystemOut(launchInterceptor::decide);

        assertEquals("YES", launchText.trim());
    }

    /**
     * Input setup for this test contains only a "false" condition for LIC 0. Remaining LICs will not be taken into
     * consideration by applying NOTUSED connector for each of them.
     *
     * @throws Exception
     */
    @Test
    public void givenValidInputAndFUVIsNotAllTrue_whenDecide_thenNoLaunch() throws Exception {
        Parameters parameters = new Parameters();
        parameters.length1 = 2;
        parameters.qPts = 2;
        List<Point> points = List.of(new Point(2, 2), new Point(4, 2));
        Connector[][] lcm = {
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
                {Connector.ORR, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED, Connector.NOTUSED},
        };
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        LaunchInterceptor launchInterceptor = new LaunchInterceptor(parameters, points, lcm, puv);

        String launchText = tapSystemOut(launchInterceptor::decide);

        assertEquals("NO", launchText.trim());
    }

    /**
     * {@link LaunchInterceptor#decide()} should complain when points are null.
     *
     * @throws Exception
     */
    @Test
    public void givenPointsIsNull_whenDecide_thenErrorMessage() throws Exception {
        Parameters parameters = new Parameters();
        Connector[][] lcm = {
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
        };
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        LaunchInterceptor launchInterceptor = new LaunchInterceptor(parameters, null, lcm, puv);

        String launchText = tapSystemOut(launchInterceptor::decide);

        assertEquals("Invalid input. points cannot be null", launchText.trim());
    }

    /**
     * {@link LaunchInterceptor#decide()} should complain when there is only one point.
     *
     * @throws Exception
     */
    @Test
    public void givenOnePoint_whenDecide_thenErrorMessage() throws Exception {
        Parameters parameters = new Parameters();
        List<Point> points = List.of(new Point(0, 0));
        Connector[][] lcm = {
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
        };
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        LaunchInterceptor launchInterceptor = new LaunchInterceptor(parameters, points, lcm, puv);

        String launchText = tapSystemOut(launchInterceptor::decide);

        assertEquals("Invalid input. points must only contain between 2 and 100 elements (inclusive)",
                launchText.trim());
    }

    /**
     * {@link LaunchInterceptor#decide()} should complain when there are more than 100 points.
     *
     * @throws Exception
     */
    @Test
    public void given101Points_whenDecide_thenErrorMessage() throws Exception {
        Parameters parameters = new Parameters();
        List<Point> points = IntStream.range(0, 101).mapToObj(i -> new Point(i, 1)).collect(Collectors.toList());
        Connector[][] lcm = {
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR, Connector.ORR},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
                {Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD, Connector.ORR, Connector.ANDD, Connector.ANDD},
        };
        boolean[] puv = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

        LaunchInterceptor launchInterceptor = new LaunchInterceptor(parameters, points, lcm, puv);

        String launchText = tapSystemOut(launchInterceptor::decide);

        assertEquals("Invalid input. points must only contain between 2 and 100 elements (inclusive)",
                launchText.trim());
    }
}
