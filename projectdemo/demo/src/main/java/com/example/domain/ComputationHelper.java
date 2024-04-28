package com.example.domain;

import java.awt.Point;

public interface ComputationHelper {

    public static Point addPoints(Point a, Point b){
        return new Point(a.x + b.x, a.y + b.y);
    }

    public static Point deducePoints(Point a, Point b){
        return new Point(a.x - b.x, a.y - b.y);
    }

    public static Point addX(Point a, int x){
        return new Point(a.x + x, a.y);
    }

    public static Point addY(Point a, int y){
        return new Point (a.x, a.y + y);
    }

    public static Point multiply(Point a, int m){
        return new Point(a.x * m, a.y * m);
    }

    public static Point normal(Point v, Point w){
        Point normal;
        if (Math.abs(v.x - w.x) >= Math.abs(v.y - w.y)){
            normal = new Point(v.x < w.x ? -1 : 1, 0);
        } else {
            normal = new Point(0, v.y < w.y ? -1 : 1);
        }
        return normal;
    }

    public static int dotProduct(Point p, Point q){
        return (p.x * q.x) + (p.y * q.y);
    }

    public static Point reflectionCalculator(Point p, Point q){
        int dotProduct = dotProduct(p, q);
        int x = p.x - 2 * dotProduct * q.x;
        int y = p.y - 2 * dotProduct * q.y;
        return new Point(x, y);
    }

    public static void normalize(Point p){
        double len = Math.sqrt(p.x * p.x + p.y * p.y);
        p.x /= len;
        p.y /= len;
    }
}