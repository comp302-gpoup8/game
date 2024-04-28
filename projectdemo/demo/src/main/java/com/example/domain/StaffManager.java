package com.example.domain;

import java.awt.Point;

import com.example.domain.gameObject.Staff;

public interface StaffManager {

    public static void moveStaff(Staff staff, int x, int screenWidth){
        int staffX = staff.getX() + (x * staff.getSpeed());
        staffX = Math.max(0, Math.min(staffX, screenWidth - staff.getWidth()));
        staff.setLocation(new Point(staffX, staff.getY()));
        staff.getHitBox().setLocation(staff.getLocation());
    }



    
}