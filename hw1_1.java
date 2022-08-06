/**
 * Title: Minimum Distance Between Numbers
 * Abstract: A small program that takes user input and determines the
 *  minimum distance between the values. The first value sets the amount
 *  of numbers to be passed in. The program then calculates the
 *  minimum distance between the set of numbers.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/1/2021
 */

import java.util.*;
import java.lang.Math;

public class hw1_1 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        
        int size = input.nextInt();
        int[] numbers = new int[size];
        ArrayList<String> distances = new ArrayList<String>();
        int minDistance = 0;
        int nextDistance = 0;
        int count = 0;
        String s1 = "";
        String s2 = "";
        
        //fill array with user input
        for(int i = 0; i < size; i++) {
            numbers[i] = input.nextInt();
        }
        
        input.close();
        
        //insertion sort WORKS
        for(int i = 1; i < numbers.length; i++) {
            int temp = numbers[i];
            int j = i - 1;
            while(j >= 0 && numbers[j] > temp) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = temp;
        }
        
        //get first distanct and set it to minDistance
        if(numbers.length > 1) {
            minDistance = Math.abs(numbers[0] - numbers[1]);
            s1 = Integer.toString(numbers[0]);
            s2 = Integer.toString(numbers[1]);
            distances.add("Pairs:" +  s1+ " " + s2);
        }
        
        //finding min distance between numbers
        for(int i = 1; i < numbers.length - 1; i++) {
            if(numbers.length <= 1) {
                break;
            }
            s1 = Integer.toString(numbers[i]);
            s2 = Integer.toString(numbers[i + 1]);
            nextDistance = Math.abs(numbers[i] - numbers[i + 1]);
            if((nextDistance == minDistance) && (minDistance > 0) && (nextDistance > 0)) {
                minDistance = nextDistance;
                distances.add("Pair:" + s1 + " " + s2);
                count++;
            }
            else if((nextDistance < minDistance) && (minDistance > 0) && (nextDistance > 0)) {
                minDistance = nextDistance;
                distances.removeAll(distances);
                count = 0;
                distances.add("Pair:" + s1 + " " + s2);
                count++;
            }
            else {
                continue;
            }
        }
        
        int n = 0;
        System.out.println("Min Distance:" + minDistance);
        //loop to output pairs
        while(n < count) {
            System.out.println(distances.get(n));
            n++;
        }
    }
}