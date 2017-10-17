/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author patri
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner scanner = new Scanner(System.in);

//    public UserIOConsoleImpl() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Double.parseDouble(input);
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            String input = scanner.nextLine();
            double d = Double.parseDouble(input);
            if (d >= min && d <= max) {
                return d;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);

    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Float.parseFloat(input);

    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        do {
            print(prompt);
            String input = scanner.nextLine();
            float f = Float.parseFloat(input);
            if (f >= min && f <= max) {
                return f;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        do {
            print(prompt);
            int i = scanner.nextInt();
            scanner.nextLine();
            if (i >= min && i <= max) {
                return i;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Long.parseLong(input); //Not sure, is this right?
    }


    @Override
    public long readLong(String prompt, long min, long max) {
        print(prompt);
        String input = scanner.nextLine();
        return Long.parseLong(input, 0);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return input;
    }

}
