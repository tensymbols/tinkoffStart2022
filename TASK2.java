package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(fin);

        int a,b,c;
        int x,y,z;

        a=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();
        x=sc.nextInt();
        y=sc.nextInt();
        z=sc.nextInt();

        int currNok = findNok(findNok(a,b), c);

        x*=(currNok/a);
        y*=(currNok/b);
        z*=(currNok/c);

        x/=currNok;
        y/=currNok;
        z/=currNok;

        System.out.println(combs((x+y+z)+2, 2 ));
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
    static int combs(int n, int k)
    {
        if (k > n){
            return 0;
        }

        long  M = 1000000007;
        long  inverted[] = new long[k + 1];
        inverted[0] = 1;
        if(k+1>=2) {
            inverted[1] = 1;
        }

        for (int i = 2; i <= k; i++) {
            inverted[i] = M - (M / i) * inverted[(int) (M % i)] % M;
        }
  
        int result = 1;

        for (int i = 2; i <= k; i++) {
            result = (int) (((result % M) * (inverted[i] % M)) % M);
        }

        for (int i = n; i >= (n - k + 1); i--) {
            result = (int) (((result % M) * (i % M)) % M);
        }
        return result;
    }
}