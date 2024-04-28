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
}