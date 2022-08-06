/**
 * Title: Graph Visualized Through Adjacency Matrix and List
 * Abstract: A small program that takes user input to create a graph.
 *  The first value determines the size of the 2D matrix. The second
 *  value determines the amount of entries the user will input. The program
 *  then determines the sources and destinations of the graph and outputs
 *  them as a Adjacency List.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/3/2021
 */

import java.util.*;
import java.lang.*;

public class hw1_3 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int entries = 0;
        int[][] matrix = new int[size][size];
        List<List<Integer>> sources = new ArrayList<List<Integer>>();
        
        // fill matrix with 0s
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        
        // create a list of lists
        for(int i = 0; i < size; i++) {
            List<Integer> destinations = new ArrayList<Integer>();
            sources.add(destinations);
        }
        
        entries = input.nextInt();
        
        // update matrix with user entries
        for(int i = 0; i < entries; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            if((x >= 0) && (x < size) && (y >=0 ) && (y < size)) {
                matrix[x][y] = 1;
            }
        }
        
        // fill the sources list with with destinations
        for(int row = 0; row < size; row++) {
            for(int column = 0; column < size; column++) {
                if(matrix[row][column] != 0) {
                    sources.get(row).add(column);
                }
                else {
                    continue;
                }
            }
        }
        
        // output in Dr. Byun's format
        for(int i = 0; i < sources.size(); i++) {
            int track = 0;
            if(sources.get(i).isEmpty()) {
                System.out.print(i);
            }
            for(int j = 0; j < sources.get(i).size(); j++) {
                if(track == 0) {
                    System.out.print(i + "->" + sources.get(i).get(j));
                    track++;
                }
                else {
                    System.out.print("->" + sources.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
}