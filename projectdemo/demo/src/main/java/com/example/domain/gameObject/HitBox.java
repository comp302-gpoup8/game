package com.example.domain.gameObject;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;

public class HitBox {

    private Point p1,p2,p3,p4;
    public int x,y,width,height;
    private int rotation = 0;

    public HitBox(Point location, Dimension size){
        p1 = location;
        p2 = new Point(location.x+size.width, location.y);
        p3 = new Point(location.x+size.width, location.y+size.height);
        p4 = new Point(location.x, location.y+size.height);
        x = p1.x;
        y = p1.y;
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean intersects(HitBox h){
        if (this.rotation == 0 && h.rotation == 0){
            Rectangle a = new Rectangle(p1, getSize());
            Rectangle b = new Rectangle(h.p1, h.getSize());
            return a.intersects(b); 
        }
        else {
            Point[] aa = {this.p1, this.p2, this.p3, this.p4};
            Point[] ba = {h.p1, h.p2, h.p3, h.p4};

            int intersections = 0;
            for (Point p : ba){
                intersections = 0;
                Point prev = aa[aa.length - 1];
                for (Point next : aa) {
                    if ((prev.y <= p.y && p.y < next.y) || (prev.y >= p.y && p.y > next.y)) {
                        double dy = next.y - prev.y;
                        double dx = next.x - prev.x;
                        double x = (p.y - prev.y) / dy * dx + prev.x;
                        if (x > p.x) {
                            intersections++;
                        }
                    }
                    prev = next;
                }   
                if (intersections % 2 == 1){
                    return true;
                }
            }
            return false;
        }
    }

    public void rotate(double angle){
        double cx = this.getCenterX();
        double cy = this.getCenterY();

        double x11 = p1.x - cx;
        double y11 = p1.y - cy;
        double x12 = x11 * Math.cos(angle) - y11 * Math.sin(angle);
        double y12 = x11 * Math.sin(angle) + y11 * Math.cos(angle);
        p1.x = (int) (x12 + cx);
        p1.y = (int) (y12 + cy);

        double x21 = p2.x - cx;
        double y21 = p2.y - cy;
        double x22 = x21 * Math.cos(angle) - y21 * Math.sin(angle);
        double y22 = x21 * Math.sin(angle) + y21 * Math.cos(angle);
        p2.x = (int) (x22 + cx);
        p2.y = (int) (y22 + cy);

        double x31 = p3.x - cx;
        double y31 = p3.y - cy;
        double x32 = x31 * Math.cos(angle) - y31 * Math.sin(angle);
        double y32 = x31 * Math.sin(angle) + y31 * Math.cos(angle);
        p3.x = (int) (x32 + cx);
        p3.y = (int) (y32 + cy);

        double x41 = p4.x - cx;
        double y41 = p4.y - cy;
        double x42 = x41 * Math.cos(angle) - y41 * Math.sin(angle);
        double y42 = x41 * Math.sin(angle) + y41 * Math.cos(angle);
        p4.x = (int) (x42 + cx);
        p4.y = (int) (y42 + cy);

        x = p1.x;
        y = p1.y;
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);

        rotation += angle;
    }

    public Point getLocation(){
        return p1;
    }

    public void setLocation(Point location){
        int dx = p1.x - location.x;
        int dy = p1.y - location.y;
        p1.setLocation(p1.x-dx, p1.y-dy);
        p2.setLocation(p2.x-dx, p2.y-dy);
        p3.setLocation(p3.x-dx, p3.y-dy);
        p4.setLocation(p4.x-dx, p4.y-dy);
        x = (int) p1.x;
        y = (int) p1.x;
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
    }

    public void setLocation(int x, int y){
        int dx = p1.x - x;
        int dy = p1.y - y;
        p1.setLocation(p1.x-dx, p1.y-dy);
        p2.setLocation(p2.x-dx, p2.y-dy);
        p3.setLocation(p3.x-dx, p3.y-dy);
        p4.setLocation(p4.x-dx, p4.y-dy);
        x = (int) p1.x;
        y = (int) p1.x;
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
    }

    public int getCenterX(){
        return (p1.x + p3.x)/2;
    }

    public int getCenterY(){
        return (p1.y + p3.y)/2;
    }

    public Dimension getSize(){
        return new Dimension((int) p1.distance(p2), (int) p1.distance(p4));
    }

    public void setSize(Dimension size){
        p2 = new Point(p1.x+size.width, p1.y);
        p3 = new Point(p1.x+size.width, p1.y+size.height);
        p4 = new Point(p1.x, p1.y+size.height);
    }

}
