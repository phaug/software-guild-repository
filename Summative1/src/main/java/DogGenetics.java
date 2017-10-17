
import java.util.Random;
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
public class DogGenetics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random dogBreedPercentages = new Random();
        int breed1, breed2, breed3, breed4, breed5;

        System.out.println("What's your dog's name?");
        String dogName = sc.nextLine();

        breed1 = dogBreedPercentages.nextInt(101);
        breed2 = dogBreedPercentages.nextInt(101 - breed1);
        breed3 = dogBreedPercentages.nextInt(101 - breed1 - breed2);
        breed4 = dogBreedPercentages.nextInt(101 - breed1 - breed2 - breed3);
        breed5 = 101 - breed1 - breed2 - breed3 - breed4;

        System.out.println("Well then, I have this highly reliable report for "+ dogName + "'s prestigious background right here.");
        System.out.println("Your dog is " + breed1 + " % Border collie.");
        System.out.println("Your dog is " + breed2 + " % Golden Retriever.");
        System.out.println("Your dog is " + breed3 + " % German Shepherd.");
        System.out.println("Your dog is " + breed4 + " % Fantastic Mutt.");
        System.out.println("Your dog is " + breed5 + " % Power Pup.");
    }
}


