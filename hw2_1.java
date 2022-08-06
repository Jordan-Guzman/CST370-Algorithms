/**
 * Title: Difference Between Two Timestamps
 * Abstract: This program takes 2 timestamps input by the user then calculates the difference
 *  between the timestamps and outputs the new timestamp as a result of that difference.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/9/2021
 */ 

import java.lang.*;
import java.util.*;

public class hw2_1 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int[] time1 = new int[3];
        int[] time2 = new int[3];
        int[] timeDifference = new int[3];
        String outputTime = "";
        String s1 = input.next();
        String s2 = input.next();
        char[] charsForS1 = s1.toCharArray();
        char[] charsForS2 = s2.toCharArray();
        String convertCharToString = "";
        int convertStringToInt = 0;
        int j = 0;
        int track = 0;
        
        // save time1 values
        for(int i = 0; i < charsForS1.length; i++) {
            if(charsForS1[i] != ':') {
                track++;
                convertCharToString += String.valueOf(charsForS1[i]);
                if((track == 2) && (j < 3)) {
                    convertStringToInt = Integer.valueOf(convertCharToString);
                    time1[j] = convertStringToInt;
                    convertCharToString = "";
                    track = 0;
                }
                else {
                    continue;
                }
            }
            else {
                j++;
            }
        }
        
        convertCharToString = "";
        convertStringToInt = 0;
        j = 0;
        track = 0;
        
        // save time2 values
        for(int i = 0; i < charsForS2.length; i++) {
            if(charsForS2[i] != ':') {
                track++;
                convertCharToString += String.valueOf(charsForS2[i]);
                if((track == 2) && (j < 3)) {
                    convertStringToInt = Integer.valueOf(convertCharToString);
                    time2[j] = convertStringToInt;
                    convertCharToString = "";
                    track = 0;
                }
                else {
                    continue;
                }
            }
            else {
                j++;
            }
        }
        
        // calculate difference between time2 and time1 and save to new array
        for(int i = 0; i < 3; i++) {
            if(i == 0) { // hours
                if(time2[i] < time1[i]) {
                    timeDifference[i] = 24 + time2[i] - time1[i];
                }
                else {
                    timeDifference[i] = time2[i] - time1[i];
                }
            }
            else if(i == 1) { // minutes
                if(time2[i] < time1[i]) {
                    timeDifference[i - 1]--;
                    timeDifference[i] = 60 + time2[i] - time1[i];
                }
                else {
                    timeDifference[i] = time2[i] - time1[i];
                }
            }
            else if(i == 2) { // seconds
                if(time2[i] < time1[i]) {
                    timeDifference[i - 1]--;
                    timeDifference[i] = 60 + time2[i] - time1[i];
                }
                else {
                    timeDifference[i] = time2[i] - time1[i];
                }
            }
            else {
                continue;
            }
        }
        
        // format correctly for output in form of hh:mm:ss
        for(int i = 0; i < 3; i++) {
            if(timeDifference[i] < 10) {
                if(i == 2) {
                    outputTime += "0" + Integer.toString(timeDifference[i]);
                }
                else {
                    outputTime += "0" + Integer.toString(timeDifference[i]) + ":";
                }
            }
            else {
                if(i == 2) {
                    outputTime += Integer.toString(timeDifference[i]);
                }
                else {
                    outputTime += Integer.toString(timeDifference[i]) + ":";
                }
            }
        }
        
        System.out.println(outputTime);
    }
}