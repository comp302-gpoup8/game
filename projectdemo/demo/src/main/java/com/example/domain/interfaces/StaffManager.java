package com.example.domain.interfaces;

import java.awt.Point;

import com.example.domain.gameObject.Staff;

/**
 * Staff Manager class
 * Responsible for the Movement of the staff, and making sure it stays within the game boundaries.
 */
public interface StaffManager {

    /**
     * Handles the staff movement.
     * @param staff
     * @param x
     * @param screenWidth
     */
    public static void moveStaff(Staff staff, int x, int screenWidth) {
        int staffX = staff.getX() + (x * staff.getSpeed());
        staffX = Math.max(0, Math.min(staffX, screenWidth - staff.getWidth()));
        staff.setLocation(new Point(staffX, staff.getY()));
        staff.getHitBox().setLocation(staff.getLocation());
    }
    //TODO
    //Right now the staff jumps between point to point. It should have a more smooth movement.
    //Probably can be fixed by finding the optimal speed but it may be a more complex issue.



    public static double rotateStaff(Staff staff, int x){
        double staffDw;
        if(x == 0){
            if (staff.getRotation() > 0) {
                staffDw = -45;
            }
            else if (staff.getRotation() < 0){
                staffDw = 45;
            }
            else{
                staffDw = 0;
            }
        }
        else if (staff.getRotation() < 45 && staff.getRotation() > -45){
            staffDw = x * 20;
        }
        else{
            staffDw = 0;
        }
        return staffDw/30;
    }
}