package group8.domain.objects;

import java.awt.Point;
import java.awt.Dimension;
import lombok.NonNull;

public class HitBox {

    private Point p1,p2,p3,p4;
    public int x,y,width,height;

    public HitBox(@NonNull Point location, @NonNull Dimension size){
        p1 = location;
        p2 = new Point(location.x+size.width, location.y);
        p3 = new Point(location.x+size.width, location.y+size.height);
        p4 = new Point(location.x, location.y+size.height);
        x = p1.x;
        y = p1.y;
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
    }

    public boolean intersects(HitBox h){
        Point closest1 = this.p1 ,closest2 = h.p1;
        double distance = -1;
        double tempd;
        Point[] aa = {this.p1, this.p2, this.p3, this.p4};
        Point[] ba = {h.p1, h.p2, h.p3, h.p4};
        for(Point a : aa){
            for(Point b : ba){
                if(distance != -1){
                    tempd = a.distance(b);
                    if(tempd < distance){
                        distance = tempd;
                        closest1 = a;
                        closest2 = b;
                    }
                }
                else{
                    distance = a.distance(b);
                    closest1 = a;
                    closest2 = b;
                }
            }
        }

        double hArea = this.height*this.width;
        double areaOfTriangles = 0.5*(Math.abs(closest2.x*(p1.y-p2.y) + p1.x*(p2.y-closest2.y) + p2.x*(p1.y-closest2.y)) +
                                    Math.abs(closest2.x*(p2.y-p3.y) + p2.x*(p3.y-closest2.y) + p3.x*(p2.y-closest2.y)) +
                                    Math.abs(closest2.x*(p3.y-p4.y) + p3.x*(p4.y-closest2.y) + p4.x*(p3.y-closest2.y)) +
                                    Math.abs(closest2.x*(p1.y-p4.y) + p1.x*(p4.y-closest2.y) + p4.x*(p1.y-closest2.y)));
        
        if (areaOfTriangles > hArea){
            return false;
        }
        else{
            return true;
        }
    }

    public void rotate(int angle){
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

        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
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
        width = (int) p1.distance(p2);
        height = (int) p1.distance(p4);
    }

    public double getCenterX(){
        return (p1.x + p3.x)/2;
    }

    public double getCenterY(){
        return (p1.y + p3.y)/2;
    }

    public Dimension getSize(){
        return new Dimension((int) p1.distance(p2), (int) p1.distance(p4));
    }

}
