
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
public class RockPaperScissors {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random rockPaperScissors = new Random();
        int numberOfGames;
        int userWin = 0, computerWin = 0, tie = 0;
        int userThrow, computerThrow;
        String moreGames = "y";

        while (moreGames.equals("y")) {
            System.out.println("Let's play rock, paper, scissors!  How many games would you like to play? Please pick between 1-10.");
            numberOfGames = userInput.nextInt();

            if (numberOfGames > 10 || numberOfGames < 1) {
                System.out.println("Do you know how to read? Incorrect amount of games, you're done.3");
                break;
            } else {
                for (int i = 0; i < numberOfGames; i++) {

                    System.out.println("Let's Play! Rock = 1, paper = 2, scissors = 3...Ready...Rock, paper, scissors, shoot!");
                    userThrow = userInput.nextInt();
                    userInput.nextLine();
                    computerThrow = rockPaperScissors.nextInt(3) + 1;
                    while (userThrow < 0 || userThrow > 3) {
                        System.out.println("Do you understand math? I said pick one through 3.");
                        userThrow = userInput.nextInt();
                        userInput.nextLine();
                    }
                    String winner = validateResults(userThrow, computerThrow);
                    if (winner.equals("user")){
                        userWin++;
                    }else if (winner.equals("computer")){
                        computerWin++;
                    }else {
                        tie++;
                    }
                }
                System.out.println("You won " + userWin + " games!");
                System.out.println("The computer " + computerWin + " games!");
                System.out.println("You tied " + tie + " games.");
                if (userWin > computerWin) {
                    System.out.println("You won!");
                } else if (userWin < computerWin) {
                    System.out.println("You loser, you lost to a computer.");
                } else {
                    System.out.println("You tied the computer, meh.");
                }
                System.out.println("Would you like to play again? Y/N?");
                moreGames = userInput.nextLine();
            }
        }
        System.out.println("Thanks for playing!");
    }

    public static String validateResults(int userThrow, int computerThrow) {

        String winner = "";

        if (userThrow == computerThrow) {
            System.out.println("We tied.");
            winner = "tie";
        } else if (userThrow == 1 && computerThrow == 2) {
            System.out.println("You lose.");
            winner = "computer";
        } else if (userThrow == 2 && computerThrow == 3) {
            System.out.println("You lose.");
            winner = "computer";
        } else if (userThrow == 3 && computerThrow == 1) {
            System.out.println("You lose.");
            winner = "computer";
        } else if (userThrow == 1 && computerThrow == 3) {
            System.out.println("You win!");
            winner = "user";
        } else if (userThrow == 2 && computerThrow == 1) {
            System.out.println("You win!");
            winner = "user";
        } else if (userThrow == 3 && computerThrow == 2) {
            System.out.println("You win!");
            winner = "user";
        }
        return winner;
    }

}
