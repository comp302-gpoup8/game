package com.example.domain.elements;

public interface StaffMovement {

    public static void moveLeft(Staff staff){
        staff.hitBox.x -= staff.speed;
        staff.setLocation(staff.hitBox.getLocation());
    }

    public static void moveRight(Staff staff) {
        staff.hitBox.x += staff.speed;
        staff.setLocation(staff.hitBox.getLocation());
    }
}
