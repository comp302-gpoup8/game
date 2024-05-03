package com.example.domain.interfaces;

import java.awt.Point;

/**
 * Computation Helper class
 * Contains some of the frequent computations used in the rest of the code.
 * Will be much more useful later on the assignment.
 */
public interface ComputationHelper {

    /**
     * Adds the given points together.
     * @param a : Point
     * @param b : Point
     * @return The new point that is the two points added together.
     */
    public static Point addPoints(Point a, Point b){
        return new Point(a.x + b.x, a.y + b.y);
    }

    /**
     * Deduces Point b's values from Point a
     * @param a : Point
     * @param b : Point
     * @return A new point a - b.
     */
    public static Point deducePoints(Point a, Point b){
        return new Point(a.x - b.x, a.y - b.y);
    }

    /**
     * Allows to create a new point by incrementing only the X value of the current point.
     * @param a : Point
     * @param x : Integer
     * @return new Point.
     */
    public static Point addX(Point a, int x){
        return new Point(a.x + x, a.y);
    }


    /**
     * Allows to create a new point by incrementing only the Y value of the current point.
     * @param a : Point
     * @param y : Integer
     * @return new Point.
     */
    public static Point addY(Point a, int y){
        return new Point (a.x, a.y + y);
    }

    /**
     * Multiplies the coordinates of the point by m
     * @param a : Point
     * @param m : Multiplier
     * @return Multiplied point.
     */
    public static Point multiply(Point a, int m){
        return new Point(a.x * m, a.y * m);
    }

    @Deprecated
    /**
     * Calculates the normal vector. Used for a previous version of bounce logic.
     * May be useful later.
     * @param v
     * @param w
     * @return
     */
    public static Point normal(Point v, Point w){
        Point normal;
        if (Math.abs(v.x - w.x) >= Math.abs(v.y - w.y)){
            normal = new Point(v.x < w.x ? -1 : 1, 0);
        } else {
            normal = new Point(0, v.y < w.y ? -1 : 1);
        }
        return normal;
    }

    /**
     * Calculates the dot product of the given points (treated as vectors.)
     * @param p : Point
     * @param q : Point
     * @return
     */
    public static int dotProduct(Point p, Point q){
        return (p.x * q.x) + (p.y * q.y);
    }

    /**
     * Calculates the reflection direction for bounce logic, using the dot product of the two points.
     * @param p : Point
     * @param q : Point
     * @return
     */
    public static Point reflectionCalculator(Point p, Point q){
        int dotProduct = dotProduct(p, q);
        int x = p.x - 2 * dotProduct * q.x;
        int y = p.y - 2 * dotProduct * q.y;
        return new Point(x, y);
    }

    @Deprecated
    /**
     * Used to normalize the values of a point.
     * Is not working as intended.
     * @param p
     */
    public static void normalize(Point p){
        double len = Math.sqrt(p.x * p.x + p.y * p.y);
        p.x /= len;
        p.y /= len;
    }
}