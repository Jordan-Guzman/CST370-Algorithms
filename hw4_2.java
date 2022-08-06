/**
 * Title: Max Value
 * Abstract: This program uses a divide and conquer technique to traverse an array and
 *  find and return its max value.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/30/2021
 */

import java.util.*;

public class hw4_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arraySize = input.nextInt();
        int[] numbers = new int[arraySize];
        
        // fill array with user input values
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = input.nextInt();
        }
        
        int max = findMaxValue(numbers, 0, numbers.length - 1);
        System.out.println(max);
    }
    
    // recursive function to determine the max value of the array
    public static int findMaxValue(int[] arr, int i, int j) {
        if(i == j) {
            return arr[i];
        }
        int max1 = findMaxValue(arr, i, (i + j)/2);
        int max2 = findMaxValue(arr, (i + j)/2 + 1, j);
        
        if(max1 >= max2) {
            return max1;
        } else {
            return max2;
        }
    }
}