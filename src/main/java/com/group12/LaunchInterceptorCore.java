package com.group12;

import com.group12.model.Connector;
import com.group12.model.Point;

import java.util.Arrays;
import java.util.List;

public class LaunchInterceptorCore {

    public boolean[] calculateCMV(Parameters parameters, List<Point> points){
        if(points == null){
            throw new IllegalArgumentException("points cannot be null");
        }
        if(points.size() < 2 || points.size() > 100){
            throw new IllegalArgumentException("points must only contain between 2 and 100 elements (inclusive)");
        }
        boolean[] cmvValues = new boolean[15];
        LaunchInterceptorConditionCollection licco = new LaunchInterceptorConditionCollection();
        cmvValues[0] = licco.LIC0(points, parameters.length1);
        cmvValues[1] = licco.LIC1(points, parameters.radius1);
        cmvValues[2] = licco.LIC2(points, parameters.epsilon);
        cmvValues[3] = licco.LIC3(points, parameters.area1);
        cmvValues[4] = licco.LIC4(points, parameters.qPts, parameters.quads);
        cmvValues[5] = licco.LIC5(points);
        cmvValues[6] = licco.LIC6(points, parameters.nPts, parameters.dist);
        cmvValues[7] = licco.LIC7(points, parameters.kPts, parameters.length1);
        cmvValues[8] = licco.LIC8(points, parameters.aPts, parameters.bPts, parameters.radius1);
        cmvValues[9] = licco.LIC9(points, parameters.cPts, parameters.dPts, parameters.epsilon);
        cmvValues[10] = licco.LIC10(points, parameters.ePts, parameters.fPts, parameters.area1);
        cmvValues[11] = licco.LIC11(points, parameters.gPts);
        cmvValues[12] = licco.LIC12(points, parameters.kPts, parameters.length1, parameters.length2);
        cmvValues[13] = licco.LIC13(points, parameters.aPts, parameters.bPts, parameters.radius1, parameters.radius2);
        cmvValues[14] = licco.LIC14(points, parameters.ePts, parameters.fPts, parameters.area1, parameters.area2);
        return cmvValues;
    }

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

    /**
     * Calculates Final Unlocking Vector (FUV) by going through the PUM and the PUV.
     *
     * @param pum Preliminary Unlocking Matrix
     * @param puv Preliminary Unlocking Vector
     * @return Final Unlocking Vector
     * @throws IllegalArgumentException if <b>puv</b> is null or if the length of <b>pum</b> and <b>puv</b>
     * is not the same.
     */
    public boolean[] calculateFUV(boolean[][] pum, boolean[] puv) throws IllegalArgumentException {
        if (puv == null) {
            throw new IllegalArgumentException("puv cannot be null");
        }
        if (pum.length != puv.length) {
            throw new IllegalArgumentException("pum has to have " + puv.length + " number of rows");
        }

        boolean[] fuv = new boolean[pum.length];
        for (int i = 0; i < pum.length; i++) {
            if (!puv[i]) {
                fuv[i] = true;
            } else {
                boolean allTrue = true;
                for (int j = 0; j < pum[i].length; j++) {
                    if (!pum[i][j]) {
                        allTrue = false;
                        break;
                    }
                }
                if (allTrue) {
                    fuv[i] = true;
                } else {
                    fuv[i] = false;
                }
            }
        }
        return fuv;
    }
}
