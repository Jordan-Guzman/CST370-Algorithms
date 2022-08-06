/**
 * Title: Consecutive Integers
 * Abstract: Accepts a sequence of inputs from a user. The inputs are then sorted. The inputs array is
 *  traversed to find consecutive integers. The consecutive integers are shortened to be output in the form
 *  start - end of the sequence of consecutive integers. The entire sorted array is then output with the
 *  shortened sequences.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 
 */ 

import java.util.*;

public class hw3_1 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfValues = input.nextInt();
        ArrayList<Integer> values = new ArrayList<Integer>();
        
        // fill the array with user input values
        for(int i = 0; i < numberOfValues; i++) {
            values.add(input.nextInt());
        }
        
        // sort the array in ascending order
        Collections.sort(values);
        
        boolean start = false;
        boolean end = true;
        
        for(int i = 1; i < values.size(); i++) {
            // checks for the beginning of a sequence of consecutive integers
            if((start == false) && (values.get(i) - values.get(i - 1) == 1)) {
                System.out.print(values.get(i - 1) + "-");
                start = true;
                end = false;
            }
            // non-consecutive integer
            else if((start == false) && (end == true) && (values.get(i) - values.get(i - 1) != 1)) {
                System.out.print(values.get(i - 1) + " ");
                
                // print the last value of the arraylist if the last two integers are not consecutive
                if(i == values.size() - 1) {
                    System.out.println(values.get(i));
                }
            }
            // checks for the ending of a sequence of consecutive integers
            else if((start == true) && (end == false) && (values.get(i) - values.get(i - 1) != 1) && (values.get(i - 1) - values.get(i - 2) == 1)) {
                System.out.print(values.get(i - 1) + " ");
                start = false;
                end = true;
                
                // print the last value of the arraylist if the last two integers are not consecutive but the two before are consecutive
                if(i == values.size() - 1) {
                    System.out.println(values.get(i));
                }
            }
            // print the last value of the arraylist if the last two integers are consecutive
            else if((i == values.size() - 1)) {
                System.out.println(values.get(i));
            }
            else {
                continue;
            }
        }
    }
}