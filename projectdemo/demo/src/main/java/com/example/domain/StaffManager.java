package com.example.domain;

import java.awt.Point;

import com.example.domain.gameObject.Staff;

public class StaffManager {

    public Staff staff;
    public Integer screenWidth;

    public StaffManager(Staff s){
        staff = s;
        screenWidth = 1200; // Default value;
    }

    public void moveStaff(int x){
        int staffX = staff.getX() + (x * staff.getSpeed());
        staffX = Math.max(0, Math.min(staffX, screenWidth - staff.getWidth()));
        staff.setLocation(new Point(staffX, staff.getY()));
        staff.getHitBox().setLocation(staff.getLocation());
    }



    
}