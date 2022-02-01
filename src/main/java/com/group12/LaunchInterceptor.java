package com.group12;

import com.group12.model.Connector;
import com.group12.model.Point;

import java.util.List;

public class LaunchInterceptor {

    // LIC parameters:
    Parameters parameters;

    // Input:
    public List<Point> points;
    public Connector[][] lcm;
    public boolean[] puv;

    // Output:
    boolean launch;

    public void decide() {

    }

    public static void main(String[] args) {
        LaunchInterceptor launchInterceptor = new LaunchInterceptor();
        launchInterceptor.decide();
    }
}
