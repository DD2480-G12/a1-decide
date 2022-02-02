package com.group12;

import com.group12.model.Connector;
import com.group12.model.Point;

import java.util.List;

public class LaunchInterceptor {

    private final LaunchInterceptorCore launchInterceptorCore;

    // LIC parameters:
    Parameters parameters;

    // Input:
    public List<Point> points;
    public Connector[][] lcm;
    public boolean[] puv;

    // Output:
    boolean launch;

    public LaunchInterceptor(Parameters parameters, List<Point> points, Connector[][] lcm, boolean[] puv) {
        this.launchInterceptorCore = new LaunchInterceptorCore();
        this.parameters = parameters;
        this.points = points;
        this.lcm = lcm;
        this.puv = puv;
    }

    public void decide() {
        try {
            boolean[] cmv = launchInterceptorCore.calculateCMV(parameters, points);
            boolean[][] pum = launchInterceptorCore.calculatePUM(cmv, lcm);
            boolean[] fuv = launchInterceptorCore.calculateFUV(pum, puv);
            launch = isAllTrue(fuv);
            System.out.println(launch ? "YES" : "NO");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. " + e.getMessage());
        }
    }

    private boolean isAllTrue(boolean[] booleans) {
        for (boolean b : booleans) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
