/**
 * Title: Partitioning Numbers
 * Abstract: This program recursively traverses an array and swaps negative numbers from the end of an array with positive
 *  numbers that are in the front of the array. At the end, it returns the array with all of the negative numbers in the
 *  front and all of the positive numbers toward the end.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/30/2021
 */

import java.util.*;

public class hw4_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arraySize = input.nextInt();
        int[] firstApproachArray = new int[arraySize];
        int[] secondApproachArray = new int[arraySize];
        
        // fill arrays with user values
        for(int i = 0; i < arraySize; i++) {
            firstApproachArray[i] = input.nextInt();
            secondApproachArray[i] = firstApproachArray[i];
        }
        
        firstApproach(firstApproachArray, 0, firstApproachArray.length - 1);
        printFistApproachResults(firstApproachArray);
        System.out.println();
        secondApproach(secondApproachArray, 0, 0);
        printSecondApproachResults(secondApproachArray);
        System.out.println();
    }
    
    // uses the approach where i starts at index 0 and j starts at index arr.length - 1
    public static int[] firstApproach(int[] arr, int i, int j) {
        if(i > j) {
            return arr;
        }
        else if(arr[i] > 0 && arr[j] > 0) {
            return firstApproach(arr, i, j - 1);
        }
        else if(arr[i] < 0 && arr[j] > 0) {
            return firstApproach(arr, i + 1, j);
        }
        else if(arr[i] < 0 && arr[j] < 0) {
            return firstApproach(arr, i + 1, j);
        }
        else {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            return firstApproach(arr, i, j);
        }
    }
    
    // uses the approach where i and j start at index 0
    public static int[] secondApproach(int[] arr, int i, int j) {
        if(j == arr.length - 1) {
            return arr;
        }
        else if(i == 0 && j == 0) {
            return secondApproach(arr, i, j + 1);
        }
        else if(arr[i] > 0 && arr[j] > 0) {
            return secondApproach(arr, i, j + 1);
        }
        else if(arr[i] < 0 && arr[j] > 0) {
            return secondApproach(arr, i + 1, j + 1);
        }
        else if(arr[i] < 0 && arr[j] < 0) {
            return secondApproach(arr, i + 1, j + 1);
        }
        else {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            return secondApproach(arr, i + 1, j + 1);
        }
    }
    
    public static void printFistApproachResults(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    public static void printSecondApproachResults(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}   