/**
 * Title: Collect Maximum Number of Coins on n x m Board
 * Abstract: This program uses a dynamic programming approach to find the maximum number of coins
 *  that can be collected on an n x m board with constraints of only being able to move right or down.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 12/14/2021
 */

import java.util.*;

public class hw6_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int rows = input.nextInt();
        int columns = input.nextInt();
        int[][] board = new int[rows][columns];
        ArrayList<ArrayList<Integer>> optimalPath = new ArrayList<ArrayList<Integer>>();
        
        // construct board with user input
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                board[i][j] = input.nextInt();
            }
        }
        
        traverseAndUpdateBoard(board);
        backtrack(board, optimalPath);
        output(optimalPath, board);
    }
    
    // traverses the board to set values corresponding to paths taken
    public static int[][] traverseAndUpdateBoard(int[][] board) {
        int max = 0;
        for(int row = 0; row < board.length; row++) {
            max = 0;
            for(int column = 1; column < board[row].length; column++) {
                // checks for initial value at starting cell
                if(row == 0 && column == 1 && board[row][column - 1] != 0) {
                    board[row][column] += board[row][column - 1];
                }
                
                // avoids boundary error for checking cells above current cell
                if(row == 0) {
                    if(board[row][column - 1] > board[row][column]) {
                        board[row][column] += board[row][column - 1];
                    }
                }
                else {
                    // finds max value between left cell and top cell
                    if(board[row][column - 1] > board[row - 1][column]) {
                        max = board[row][column - 1];
                    }
                    else {
                        max = board[row - 1][column];
                    }
                    board[row][column] += max;
                }
            }
        }
        return board;
    }
    
    // backtracks to find the path taken to get the max amount of coins
    public static ArrayList<ArrayList<Integer>> backtrack(int[][] board, ArrayList<ArrayList<Integer>> path) {
        int row = board.length - 1;
        int column = board[row].length - 1;
        ArrayList<Integer> rows = new ArrayList<Integer>();
        ArrayList<Integer> columns = new ArrayList<Integer>();
        boolean notAtStart = true;
        
        while(notAtStart == true) {
            int tempRow = 0;
            int tempColumn = 0;
            
            if(row == 0 && column == 0) {
                tempRow = row + 1;
                rows.add(tempRow);
                tempColumn = column + 1;
                columns.add(tempColumn);
                notAtStart = false;
                break;
            }
            
            // avoids boundary error for checking cells above current cell
            if(row == 0 && column != 0) {
                tempRow = row + 1;
                rows.add(tempRow);
                tempColumn = column + 1;
                columns.add(tempColumn);
                // move left
                column--;
            }
            // avoids boundary error for checking cells left of the current cell
            else if(row != 0 && column == 0) {
                tempRow = row + 1;
                rows.add(tempRow);
                tempColumn = column + 1;
                columns.add(tempColumn);
                row--;
            }
            else {
                // if left cell and top cell are equal, then move left
                if(board[row][column - 1] == board[row - 1][column]) {
                    tempRow = row + 1;
                    rows.add(tempRow);
                    tempColumn = column + 1;
                    columns.add(tempColumn);
                    // move left
                    column--;
                }
                // finds max value between left cell and top cell
                else if(board[row][column - 1] > board[row - 1][column]) {
                    tempRow = row + 1;
                    rows.add(tempRow);
                    tempColumn = column + 1;
                    columns.add(tempColumn);
                    // move left
                    column--;
                }
                else {
                    tempRow = row + 1;
                    rows.add(tempRow);
                    tempColumn = column + 1;
                    columns.add(tempColumn);
                    // move up
                    row--;
                }
            }
        }

        // build 2d arraylist to hold row and column values
        for(int i = 0; i < rows.size(); i++) {
            path.add(new ArrayList<Integer>());
        }
        
        // fill 2d array with row and column of max values to build path
        for(int i = 0; i < rows.size(); i++) {
            path.get(i).add(rows.get(i));
            path.get(i).add(columns.get(i));
        }
        return path;
    }
    
    // print output according to Dr. Byun's specifications
    public static void output(ArrayList<ArrayList<Integer>> path, int[][] board) {
        int row = board.length - 1;
        int column = board[row].length - 1;
        System.out.println("Max coins:" + board[row][column]);
        System.out.print("Path:");
        for(int i = path.size() - 1; i >= 0 ; i--) {
            if(i == 0) {
                System.out.println("(" + path.get(i).get(0) + "," + path.get(i).get(1) + ")");
            }
            else {
                System.out.print("(" + path.get(i).get(0) + "," + path.get(i).get(1) + ")");
                System.out.print("->");
            }
        }
    }
}