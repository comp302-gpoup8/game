package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.close();
        switch (choice) {
            case 1 -> System.out.println("Hello World");
            default -> System.out.println("Heya!");
        }
    }
}