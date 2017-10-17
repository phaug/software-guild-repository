
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author patri
 */
public class HealthyHearts {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double age, maxHeart, targetHeartRateMin, targetHeartRateMax;

        System.out.println("What's your age?");
        age = sc.nextInt();
        System.out.println("Your age is " + age);
        maxHeart = 220 - age;
        targetHeartRateMin = maxHeart * .50;
        targetHeartRateMax = maxHeart * .85;
        System.out.println("Your max heart rate should be " + maxHeart);
        System.out.println("Your target heart zone is between " + targetHeartRateMin + " - " + targetHeartRateMax + " beats per minute.");
        

    }
}
