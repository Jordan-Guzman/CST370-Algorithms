/**
 * Title: Recursive Palindrome Checker
 * Abstract: The user enters a words, which will then be run through a function that
 *  recursively checks whether or not the word is a palindrome.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/9/2021
 */
 
import java.util.*;
import java.lang.*;

public class hw2_3 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String isPalindrome = "TRUE";
        String notPalindrome = "FALSE";
        String word = input.nextLine();
        word = word.replaceAll("\\s+", "");
        word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if(palindrome(word, 0)) {
            System.out.println(isPalindrome);
        }
        else {
            System.out.println(notPalindrome);
        }
    }

    // recursive function for palindrome checking
    public static boolean palindrome(String s, int i) {
        int num = i;
        if(s.charAt(num) != (s.charAt((s.length() - num) - 1))) {
            return false;
        }
        else if(num == (s.length() - 1)) {
            return true;
        }
        else {
            return palindrome(s, ++num);
        }
    }
}