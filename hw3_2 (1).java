/**
 * Title: Traveling Salesperson Problem
 * Abstract: This program uses an adjacency matrix to find the most cost-effective path
 *  based on user input of sources, destinations, and costs of edges.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/16/2021
 */
 
import java.util.*;
 
public class hw3_2 {
    // global variables
    static ArrayList<ArrayList<Integer>> pathsAndCosts;
   
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfVertices = input.nextInt();
        int numberOfEdges = input.nextInt();
        int[][] graph = new int[numberOfVertices][numberOfVertices];
        int startingVertex = 0;
        int minimumCost = 0;
        int previousCost = 0;
        ArrayList<Integer> costs = new ArrayList<Integer>();

 
        // initialize graph with 0s
        for(int i = 0; i < numberOfVertices; i++) {
            for(int j = 0; j < numberOfVertices; j++) {
                graph[i][j] = 0;
            }
        }
       
        // fills graph with edge value from source to destination and the cost value of the path
        for(int i = 0; i < numberOfEdges; i++) {
            graph[input.nextInt()][input.nextInt()] = input.nextInt();
        }
       
        startingVertex = input.nextInt();
       
        ArrayList<Integer> verticesArray = new ArrayList<Integer>();
       
        for(int i = 0; i < numberOfVertices; i++) {
            verticesArray.add(i);
        }
       
        verticesArray.remove(startingVertex);
       
        int[] permutationArray = new int[verticesArray.size()];
        for(int i = 0; i < permutationArray.length; i++) {
            permutationArray[i] = verticesArray.get(i);
        }
       
        pathsAndCosts = new ArrayList<ArrayList<Integer>>();
       
        permute(permutationArray, permutationArray[0], startingVertex);


        // Dr. Byun: You should initialize "previousCost" with a big number like below.
        previousCost = Integer.MAX_VALUE;
        
        for(int i = 0; i < pathsAndCosts.size(); i++) {
            int cost = 0;
            for(int j = 0; j < pathsAndCosts.get(i).size(); j++) {
                if(j == pathsAndCosts.get(i).size() - 1) {
                    if((cost != 0 ) && (cost < previousCost)) {
                        minimumCost = i;
                        previousCost = cost;
                    }
                }
                else {
                    // Dr. Byun: Check if the cost is 0 because 0 means "infinity".
                    //           In that case, you should skip the path because it can't be a solution.
                    if ((graph[pathsAndCosts.get(i).get(j)][pathsAndCosts.get(i).get(j + 1)]) == 0)
                    {
                        cost = Integer.MAX_VALUE;
                        break;
                    }
                    cost += graph[pathsAndCosts.get(i).get(j)][pathsAndCosts.get(i).get(j + 1)];
                }
            }
            costs.add(cost);
        }
       
        // Output according to Dr. Byun's specifications
        if(costs.get(minimumCost) == Integer.MAX_VALUE) {
            System.out.print("Path:");
        }
        else {
            System.out.print("Path:");
            for(int i = 0; i < pathsAndCosts.get(minimumCost).size(); i++) {
                if(i == pathsAndCosts.get(minimumCost).size() - 1) {
                    System.out.print(pathsAndCosts.get(minimumCost).get(i));
                }
                else {
                    System.out.print(pathsAndCosts.get(minimumCost).get(i) + "->");
                }
            }
        }
        System.out.println();
        if(costs.get(minimumCost) == Integer.MAX_VALUE) {
            System.out.println("Cost:" + -1);
        }
        else {
            System.out.println("Cost:" + costs.get(minimumCost));
        }
    }
   
    public static void permute(int[] input, int startIndex, int source) {
        int size = input.length;
        if(size == startIndex + 1) {
            pathsAndCosts.add(new ArrayList<Integer>());
            pathsAndCosts.get(pathsAndCosts.size() - 1).add(source);
            for(int i = 0; i < size; i++) {
                pathsAndCosts.get(pathsAndCosts.size() - 1).add(input[i]);
            }
            pathsAndCosts.get(pathsAndCosts.size() - 1).add(source);
        }
        else {
            for(int i = startIndex; i < size; i++) {
                int temp = input[i];
                input[i] = input[startIndex];
                input[startIndex] = temp;
                permute(input, startIndex + 1, source);
                temp = input[i];
                input[i] = input[startIndex];
                input[startIndex] = temp;
            }
        }
    }
}