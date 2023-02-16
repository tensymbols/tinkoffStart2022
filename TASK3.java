package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        int minNok = Integer.MAX_VALUE;
        int num1 = 1;
        int num2 = number-num1;
        for(int i = 1; i<=number/2; i++){
            int nok = findNok(i,number - i);
            if(nok<minNok){
                num1 = i;
                num2 = number-i;
                minNok = nok;
            }
        }
        System.out.println(num1);
        System.out.println(num2);
        sc.close();
    }
    public static int findNok(int a, int b){
        int A=a;
        int B=b;
        while(A!=B){
            if(A>B) A = A-B;
            else B=B-A;
        }
        return a*b/A;
    }
}