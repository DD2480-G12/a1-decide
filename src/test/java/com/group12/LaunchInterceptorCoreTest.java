package com.group12;

import com.group12.model.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaunchInterceptorCoreTest {

    private LaunchInterceptorCore launchInterceptorCore;

    @BeforeEach
    public void setup() {
        launchInterceptorCore = new LaunchInterceptorCore();
    }

    @Test
    public void given2x1CMVWithTrueFalseValuesAnd2x2LCMWithANDDConnector_whenCalculatePUM_thenExpectedPUM() {
        boolean[] cmv = {true, false};
        Connector[][] lcm = {{Connector.ANDD, Connector.ANDD}, {Connector.ANDD, Connector.ANDD}};
        boolean[][] expectedPum = {{true, false}, {false, false}};

        boolean[][] actualPum = launchInterceptorCore.calculatePUM(cmv, lcm);

        assertArrayEquals(expectedPum, actualPum);
    }

    @Test
    public void given2x1CMVWithTrueFalseValuesAnd2x2LCMWithORRConnector_whenCalculatePUM_thenExpectedPUM() {
        boolean[] cmv = {true, false};
        Connector[][] lcm = {{Connector.ORR, Connector.ORR}, {Connector.ORR, Connector.ORR}};
        boolean[][] expectedPum = {{true, true}, {true, false}};

        boolean[][] actualPum = launchInterceptorCore.calculatePUM(cmv, lcm);

        assertArrayEquals(expectedPum, actualPum);
    }

    @Test
    public void given2x1CMVWithTrueFalseValuesAnd2x2LCMWithNOTUSEDConnector_whenCalculatePUM_thenExpectedPUM() {
        boolean[] cmv = {true, false};
        Connector[][] lcm = {{Connector.NOTUSED, Connector.NOTUSED}, {Connector.NOTUSED, Connector.NOTUSED}};
        boolean[][] expectedPum = {{true, true}, {true, true}};

        boolean[][] actualPum = launchInterceptorCore.calculatePUM(cmv, lcm);

        assertArrayEquals(expectedPum, actualPum);
    }

    @Test
    public void given2x1CMVWithTrueFalseValuesAnd2x1LCM_whenCalculatePUM_thenThrowIllegalArgumentException() {
        boolean[] cmv = {true, false};
        Connector[][] lcm = {{Connector.ANDD}, {Connector.ANDD}};

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorCore.calculatePUM(cmv, lcm));
    }

    @Test
    public void given2x1CMVWithTrueFalseValuesAnd1x2LCM_whenCalculatePUM_thenThrowIllegalArgumentException() {
        boolean[] cmv = {true, false};
        Connector[][] lcm = {{Connector.ANDD, Connector.ANDD}};

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorCore.calculatePUM(cmv, lcm));
    }

    @Test
    public void givenLCMIsNull_whenCalculatePUM_thenThrowIllegalArgumentException() {
        boolean[] cmv = {true, false};

        assertThrows(IllegalArgumentException.class, () -> launchInterceptorCore.calculatePUM(cmv, null));
    }
}
