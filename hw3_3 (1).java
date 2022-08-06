/**
 * Title: Depth-First Search
 * Abstract: This program implements Depth-First Search by means of recursion and through the use
 *  of an adjacency matrix.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/16/2021
 */

import java.util.*;

public class hw3_3 {
    static Stack<Integer> currentNode;
    static Integer[] mark;
    static Integer count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfVertices = input.nextInt();
        int numberOfEdges = input.nextInt();
        int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
        currentNode = new Stack<Integer>();
        mark = new Integer[numberOfVertices];

        for(int i = 0; i < mark.length; i++) {
            mark[i] = 0;
        }

        // initialize adjacency matrix
        adjacencyMatrix = initAdjacencyMatrix(numberOfVertices, numberOfVertices);

        // input user passed values into adjacency matrix
        for(int i = 0; i < numberOfEdges; i++) {
            adjacencyMatrix[input.nextInt()][input.nextInt()] = 1;
        }

        // print2DArray(adjacencyMatrix);

        depthFirstSearch(adjacencyMatrix, 0, 0);
    }

    // initialize with 0 values
    public static int[][] initAdjacencyMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    // print to make sure it filled correctly
    // for test purposes only
    public static void print2DArray(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // print the mark array to show order in which nodes were visited
    public static void printMarkArray(Integer[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Mark" + "[" + i + "]" + ":" + arr[i]);
        }
    }

    public static void end() {
        printMarkArray(mark);
    }

    public static void depthFirstSearch(int[][] matrix, int row, int column) {
        // base condition
        if(currentNode.empty() && mark[0] != 0) {
            end();
        }
        // reach the last index of the adjacency matrix, pop last value from stack and return to trigger base condition
        else if((row == matrix.length - 1) && (column == matrix[0].length - 1)) {
            if(currentNode.size() > 1) {
                currentNode.pop();
                Integer temp = currentNode.peek();
                depthFirstSearch(matrix, temp, 0);
            }
            else if(currentNode.size() == 1) {
                Integer temp = currentNode.peek();
                currentNode.pop();
                depthFirstSearch(matrix, temp, 0);
            }
            else {
                depthFirstSearch(matrix, row, column);
            }
        }
        // first traversal sets mark[0] = 1 starting at matrix[0][0]
        else if((row == 0) && (column == 0) && (mark[0] == 0)) {
            currentNode.push(0);
            mark[0] = ++count;
            depthFirstSearch(matrix, row, column + 1);
        }
        // traversal lands on node and goes to the row corresponding the the column in which it was found
        else if((matrix[row][column] == 1) && (mark[column] == 0)) {
            currentNode.push(column);
            mark[column] = ++count;
            depthFirstSearch(matrix, column, 0);
        }
        // move to the next index in the traversal
        else {
            if(column == matrix[0].length - 1) {
                depthFirstSearch(matrix, row + 1, 0);
            }
            else {
                depthFirstSearch(matrix, row, column + 1);
            }
        }
    }
}