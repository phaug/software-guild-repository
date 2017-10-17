/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class fibonnacci {

    public static void main(String[] args) {
        {
            int a, b, c;
            int num = 0;
            a = 1;
            b = 1;
            int sum = 0;

            while (num < 4000000) {
                c = a + b;
                a = b;
                b = c;
                num = b;
                if (num > 4000000) {
                    break;
                }

                if (num % 2 == 0) {
                    System.out.println(num);
                    sum = sum + num;
                    System.out.println("sum :" + sum);
                }
            }
        }
    }
}
