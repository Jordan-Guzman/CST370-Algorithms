/**
 * Title: Maximum Frequency of Numbers
 * Abstract: A small program that takes user input and determines the maximum
 *  frequency of between the values entered. The first value sets the amount
 *  of numbers to be passed in. The program then calculates the maximum
 *  frequency within the set of numbers.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/3/2021
 */

import com.sun.security.auth.module.NTSystem;

import java.util.*;

public class hw1_2 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int count = 1;
        int next = 0;
        int frequency = 0;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        

        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

        // fill map with user values
        for(int i = 0; i < size; i++) {
            next = input.nextInt();
            count = hm.containsKey(next) ? hm.get(next) : 0;
            hm.put(next, count + 1);
        }

        // find max frequency
        int maxValuesInMap = (Collections.max(hm.values()));
        for(Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if(entry.getValue() == maxValuesInMap) {
                numbers.add(entry.getKey());
                frequency = entry.getValue();
            }
        }
        
        Collections.sort(numbers, Collections.reverseOrder());
        
        System.out.println("Frequency:" + frequency);
        System.out.print("Number:");
        
        // output in Dr. Byun's format
        for(int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            System.out.print(" ");
        }
    }
}