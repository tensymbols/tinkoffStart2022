package org.example;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) throws Exception{

        File fin =  new File("task.in");
        File fout = new File("task.out");
        Scanner sc = new Scanner(fin);
        PrintWriter pw = new PrintWriter(fout);
        int quantity = Integer.parseInt(sc.nextLine());
        String[] words = sc.nextLine().split(" ");
        String colors = sc.nextLine();

        int counter=0;
        int currPos=0;
        for(int i = 0; i <words.length; i++){
            for(int j = 1; j < words[i].length(); j++){
                System.out.println( colors.charAt(currPos+j));
                if(colors.charAt(currPos+j)==colors.charAt(currPos+j-1) ){
                    counter++;
                    break;
                }
            }
            currPos+=words[i].length();
        }
        pw.println(counter);
        pw.close();
        sc.close();

    }
}