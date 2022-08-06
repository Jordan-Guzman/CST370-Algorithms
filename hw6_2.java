/**
 * Title: Floyd's Algorithm for Finding Shortest Path Between Vertices
 * Abstract: This program finds the shortest path from one vertex to another vertex by using Floyd's Algorithm
 *  to determine if a path is reachable by another and finding the minimum weight associated with the edge
 *  connecting them.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 12/14/2021
 */

import java.util.*;

public class hw6_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfVertices = input.nextInt();
        int[][] distanceMatrix = new int[numberOfVertices][numberOfVertices];
        
        // builds the distance matrix with user-provided input
        for(int i = 0; i < numberOfVertices; i++) {
            for(int j = 0; j < numberOfVertices; j++) {
                distanceMatrix[i][j] = input.nextInt();
            }
        }
        
        System.out.println();
        System.out.println();
        floydsAlgorithm(distanceMatrix);
        printTable(distanceMatrix);
    }
    
    // prints the table according to Dr. Byun's specifications
    public static void printTable(int[][] table) {
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // implements Floyd's Algorithm for finding the shortest path between vertices
    public static int[][] floydsAlgorithm(int[][] table) {
        for(int k = 0; k < table.length; k++) {
            for(int i = 0; i < table.length; i++) {
                for(int j = 0; j < table.length; j++) {
                    // no direct path exists
                    if(table[i][k] == -1 || table[k][j] == -1) {
                        continue;
                    }
                    // cell i, j contains an existing weight for a path and tests the 
                    // new value/path against it to see which is smaller
                    else if(table[i][j] != -1) {
                        if(table[i][j] < table[i][k] + table[k][j]) {
                            table[i][j] = table[i][j];
                        }
                        else {
                            table[i][j] = table[i][k] + table[k][j];
                        }
                    }
                    // an indirect path exists between two vertices
                    else {
                        table[i][j] = table[i][k] + table[k][j];
                    }
                }
            }
        }
        return table;
    }
}