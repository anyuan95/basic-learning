package org.example.basic.oj.leetcode.Q478;

import java.util.Random;

class Solution {
    private double xCenter;
    private double yCenter;
    private double radius;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {
        final Random random = new Random();
        double len = Math.sqrt(random.nextDouble() * radius * radius), angular = random.nextDouble() * 2 * Math.PI;
        double newX = xCenter + len * Math.cos(angular), newY = yCenter + len * Math.sin(angular);
        return new double[]{newX, newY};
    }

}
