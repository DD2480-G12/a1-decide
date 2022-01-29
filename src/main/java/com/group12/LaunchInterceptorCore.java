package com.group12;

import com.group12.model.Connector;

import java.util.Arrays;

public class LaunchInterceptorCore {

    /**
     * Calculates Preliminary Unlocking Matrix (PUM) by combining the Conditions Met Vector (CMV) and
     * Logical Connector Matrix (LCM). LCM is assumed to be symmetric and only containing defined connectors
     * {@link Connector}.
     *
     * @param cmv Condition Met Vector
     * @param lcm Logical Connector Matrix
     * @return Preliminary Unlocking Matrix
     * @throws IllegalArgumentException if <b>lcm</b> is null or has mismatching dimensions with cmv.
     */
    public boolean[][] calculatePUM(boolean[] cmv, Connector[][] lcm) throws IllegalArgumentException {
        if (lcm == null) {
            throw new IllegalArgumentException("lcm cannot be null");
        }
        if (lcm.length != cmv.length) {
            throw new IllegalArgumentException("lcm has to have " + cmv.length + " number of rows");
        }
        boolean numberOfConnectorsPerColumn = Arrays.stream(lcm)
                .allMatch(connectors -> connectors.length == cmv.length);
        if (!numberOfConnectorsPerColumn) {
            throw new IllegalArgumentException("lcm has to have " + cmv.length + " number of columns");
        }

        boolean[][] pum = new boolean[cmv.length][cmv.length];
        for (int i = 0; i < cmv.length; i++) {
            for (int j = i; j < cmv.length; j++) {
                boolean result = switch (lcm[i][j]) {
                    case ANDD -> cmv[i] && cmv[j];
                    case ORR -> cmv[i] || cmv[j];
                    case NOTUSED -> true;
                };
                pum[i][j] = result;
                pum[j][i] = result;
            }
        }
        return pum;
    }
}
