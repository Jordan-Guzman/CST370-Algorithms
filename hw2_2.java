/**
 * Title: Combinations Organizer
 * Abstract: The user enters a number of entries to be displayed. All possible combinations
 *  of the entries will be displayed alongside the decimal count of the number of possible
 *  combinations and the binary number converted from the corresponding decimal number.
 *  The entries being displayed correspond the the 1-bit being displayed in the binary number.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/9/2021
 */
 
import java.util.*;
import java.lang.*;
 
public class hw2_2 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfEntries = input.nextInt();
        int combinationsSize = (int)Math.pow(2, numberOfEntries);
        
        String[] combinations = new String[combinationsSize];
        
        ArrayList<String> entries = new ArrayList<String>();
        
        // fill arraylist with entries
        for(int i = 0; i < numberOfEntries + 1; i++) {
            if(i == 0) {
                entries.add("EMPTY");
            }
            else {
                entries.add(input.next());
            }
        }
        
        int[] decimalValues = new int[combinationsSize];
        
        // fill array with decimal values from 0 -- combinationsSize
        for(int i = 0; i < combinationsSize; i++) {
            decimalValues[i] = i;
        }
        
        String[] binaryValues = new String[decimalValues.length];
        
        // fill array with binary values from 0 - size of decimals array
        for(int i = 0; i < binaryValues.length; i++) {
            String s  = Integer.toBinaryString(i);
            binaryValues[i] = insertLeadingZero(s, Integer.toBinaryString(decimalValues.length - 1).length(), s.length());
        }
        
        // format output according to Dr. Byun's style
        for(int i = 0; i < decimalValues.length; i++) {
            System.out.println(decimalValues[i] + ":" + binaryValues[i] + ":" + selectEntries(entries, binaryValues[i]));
        }
        
    }
    
    // method for inserting leading 0s
    public static String insertLeadingZero(String s, int maxBitSize, int currentBit) {
        String binaryNumber = s;
        String newBinaryNumber = "";
        int numberToAppend = maxBitSize - currentBit;
        for(int i = 0; i < numberToAppend; i++) {
            newBinaryNumber += "0";
        }
        newBinaryNumber += binaryNumber;
        
        return newBinaryNumber;
    }
    
    // method for selecting correct entries to display
    public static String selectEntries(ArrayList<String> arr, String s) {
        String binaryString = "";
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.valueOf(s.charAt(i)).equals("1")) {
                binaryString += (arr.get(i + 1) + " ");
                count += 1;
            }
        }
        
        if(count == 0)  {
            binaryString = arr.get(0);
        }
        
        return binaryString;
    }
}