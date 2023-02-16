package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.math.*;
import java.util.Collections;
public class Main {
    public static void main(String[] args) throws Exception{

        File fin =  new File("task.in");
        File fout = new File("task.out");

        Scanner sc = new Scanner(fin);
        PrintWriter pw = new PrintWriter(fout);

        int numberOfSides = sc.nextInt();
        double angle = 180.0 - (360.0/numberOfSides);
        double angleDelta = 180.0/numberOfSides;
        double currAngle = angle;
        double[] distances = new double[numberOfSides];

        distances[0]=0.0f;
        distances[1]=1.0;

        double Area =0.0;

        for (int i = 2; i<numberOfSides; i++){
            distances[i] = findThirdSide(distances[i-1], currAngle);
            currAngle-=angleDelta;
        }
        distances[numberOfSides-1]=1.0;
        int p1=0;
        int p2;
        int p3;

        int diff = numberOfSides/3;
        //Дальше выбираем координаты трех точек самого большого треугольника
        if(numberOfSides % 3 == 0){
            p2=p1+diff;
            p3=p2+diff;
        }
        else{
            if(diff*3+2==numberOfSides){
                p2=diff+1;
                p3=p2+diff+1;
            }
            else{
                p2=diff;
                p3=p2+diff+1;
            }
        }

        Area+=findArea(distances[p2-p1], distances[p3-p2], distances[distances.length-p3]);

        Area+=findFullArea(distances, p1,p2,p3);
        pw.write(Double.toString(Area));
        pw.close();
        sc.close();
    }
    private static double findThirdSide(double a, double alpha){
        alpha=Math.toRadians(alpha);
        return Math.sqrt(a*a + 1.0 - 2.0*a*Math.cos(alpha));
    }
    private static double findArea(double a, double b, double c){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    private static double findFullArea(double[] distances, int p1, int p2, int p3){
        // если разница в координатах больше 3, значит внутри можно построить еще треугольник(и)
        double fullArea = 0.0;
        if(p2-p1>3){
            fullArea += occupy(distances,p1+1, p2-1 );
        }
        if(p3-p2>3){
            fullArea += occupy(distances, p2+1, p3-1 );
        }
        if(distances.length - p3 > 3){
            fullArea += occupy( distances,p3+1, distances.length-1 );
        }
        return fullArea;
    }
    private static double occupy(double[] distances, int left, int right ){
        int mid = left+(right-left)/2;
        double area = findArea(distances[mid-left], distances[right-mid], distances[right-left]);
        if(mid-left>3) {
            area+=occupy(distances, left+1, mid-1);
        }
        if(right-mid>3) {
            area+=occupy(distances, mid+1, right-1);
        }
        return area;

    }

}