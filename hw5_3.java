/**
 * Title: Hash Table
 * Abstract: This program builds a hash table and rehashes the table if the number of elements entered by the user causes
 *  the load factor to exceed 0.5. This hash table uses linear probing when a collision occurs. The user can enter commands
 *  to display the status of an entry in the hash table; to display the table size; to insert an entry into the table; or to
 *  search for a value in the table.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 12/7/2021
 */

import java.util.*;

public class hw5_3 {
    public static int count = 1;
    public static ArrayList<Integer> rehashedTable = new ArrayList<Integer>();
    public static ArrayList<Integer> hashTable = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int initialSizeOfTable = input.nextInt();
        int numberOfCommands = input.nextInt();
        
        // fill initial values of hashTable as null
        for(int i = 0; i < initialSizeOfTable; i++) {
            hashTable.add(null);
        }
        
        for(int i = 0; i < numberOfCommands; i++) {
            switch(input.next()) {
                case "displayStatus":
                    displayStatus(input.nextInt(), hashTable);
                    break;
                case "tableSize":
                    tableSize(hashTable);
                    break;
                case "insert":
                    Integer userValue = input.nextInt();
                    if(calculateLoadFactor(count, hashTable) == true) {
                        insert(userValue, hashTable);
                    }
                    else {
                        rehashTable(userValue);
                    }
                    break;
                case "search":
                    Integer searchValue = input.nextInt();
                    search(searchValue, hashTable);
                    break;
                default:
                    break;
            }
        }
    }
    
    // performs hash function operation
    public static int hashFunction(Integer value, int sizeOfTable) {
        return value % sizeOfTable;
    }
    
    // display the hash table size
    public static void tableSize(ArrayList<Integer> table) {
        System.out.println(table.size());
    }
    
    // search for a value in the hash table
    public static void search(Integer value, ArrayList<Integer> table) {
        if(value != table.get(hashFunction(value, table.size()))) {
            // scan the entire table
            int i = hashFunction(value, table.size());
            while(i < table.size()) {
                if(value == table.get(i)) {
                    System.out.println(value + " Found");
                    break;
                }
                if(i == table.size() - 1) {
                    System.out.println(value + " Not found");
                }
                i++;
            }
        }
        else if(value == table.get(hashFunction(value, table.size()))) {
            System.out.println(value + " Found");
        }
        else {
            System.out.println(value + " Not found");
        }
    }
    
    // display the value of an index in the hash table
    public static void displayStatus(int valueOfIndex, ArrayList<Integer> table) {
        if(table.get(valueOfIndex) == null) {
            System.out.println("Empty");
        }
        else {
            System.out.println(table.get(valueOfIndex));
        }
    }
    
    // insert new value into the table
    public static ArrayList<Integer> insert(Integer value, ArrayList<Integer> table) {
        int index = hashFunction(value, table.size());
        count++;
        while(table.get(index) != null) {
            index = linearProbing(table, index);
        }
        table.set(index, value);
        return table;
    }
    
    // searches for the next empty index following a collision
    public static int linearProbing(ArrayList<Integer> table, int index) {
        if(table.get(index) != null) {
            index += 1;
            linearProbing(hashTable, index);
        }
        return index;
    }
    
    // determines if the calculated load factor is exceeded
    public static boolean calculateLoadFactor(int n, ArrayList<Integer> table) {
        double loadFactor = (double) n / table.size();
        if(loadFactor <= 0.5) {
            return true;
        }
        else {
            return false;
        }
    }
    
    // rehashes the hash table upon the load factor being exceeded
    public static void rehashTable(Integer insertLast) {
        count = 1;
        int sizeOfNewHashTable = resizeIsPrime(hashTable.size() * 2);
        
        // build "empty" rehashedTable
        for(int i = 0; i < sizeOfNewHashTable; i++) {
            rehashedTable.add(null);
        }
        
        // copy values from hashTable to rehashedTable
        for(int i = 0; i < hashTable.size(); i++) {
            if(hashTable.get(i) != null) {
                insert(hashTable.get(i), rehashedTable);
            }
        }
        // insert the last entry before the table needed rehashing
        insert(insertLast, rehashedTable);
        
        // clear the original hash table
        hashTable.clear();
        
        // copy values from rehashedTable back to hashTable
        for(int i = 0; i < rehashedTable.size(); i++) {
            hashTable.add(rehashedTable.get(i));
        }
        
        // remove all values from rehashedTable
        rehashedTable.clear();
    }
    
    // credit to https://www.youtube.com/watch?v=Hk9n0cWE2OI
    // used to determine if the next index is prime when rehashing the hash table
    public static int resizeIsPrime(int indexForSizing) { // WORKS
        int newSize = 0;
        for(int n = indexForSizing; n < 2 * n; n++) {
            boolean prime = true;
            
            int i = 2;
            while(i <= n / 2) {
                if(n % i == 0) {
                    prime = false;
                    break;
                }
                i++;
            }
            
            if(prime) {
                newSize = n;
                break;
            }
        }
        return newSize;
    }
}