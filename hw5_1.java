/**
 * Title: Max Heap
 * Abstract: The user will enter values to build a tree which will then be sorted into a Max Heap. The user can then
 *  insert a value, delete the max value, or enter a command to display the Max Heap.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 12/7/2021
 */

import java.util.*;

public class hw5_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int initialSizeOfBinaryTree = input.nextInt();
        ArrayList<Integer> binaryTree = new ArrayList<Integer>();
        binaryTree.add(Integer.MAX_VALUE);
        
        // build binary tree
        for(int i = 1; i <= initialSizeOfBinaryTree; i++) { // WORKS
            binaryTree.add(input.nextInt());
        }
        
        if(checkIfMaxHeap(binaryTree, binaryTree.size() / 2) == false) { // WORKS
            System.out.println("This is NOT a heap.");
            initialHeapification(binaryTree);
        }
        else {
            System.out.println("This is a heap.");
        }
        
        int numberOfActions = input.nextInt();
        
        // take and perform user action
        for(int i = 0; i < numberOfActions; i++) { // WORKS
            switch(input.next()) {
                case "display":
                    display(binaryTree);
                    break;
                case "displayMax":
                    displayMax(binaryTree);
                    break;
                case "insert":
                    Integer newLeaf = input.nextInt();
                    insert(newLeaf, binaryTree);
                    break;
                case "deleteMax":
                    deleteMax(binaryTree);
                    break;
                default:
                    break;
            }
        }
    }
    
    // traverses the tree to determine if it is a Max Heap
    public static boolean checkIfMaxHeap(ArrayList<Integer> binaryTree, int parentNode) {
        if(parentNode == 1) {
            return true;
        }
        
        int leftChild = 2 * parentNode;
        int rightChild = (2 * parentNode) + 1;
        Integer max = 0;
        
        if(leftChild <= binaryTree.size() - 1 && binaryTree.get(leftChild) > binaryTree.get(parentNode)) {
            return false;
        }
        else {
            checkIfMaxHeap(binaryTree, parentNode - 1);
        }
        if(rightChild <= binaryTree.size() - 1 && binaryTree.get(rightChild) > binaryTree.get(max)) {
            return false;
        }
        return checkIfMaxHeap(binaryTree, parentNode - 1);
    }
    
    // display the heap
    public static void display(ArrayList<Integer> currentHeap) {
        for(int i = 1; i < currentHeap.size(); i++) {
            System.out.print(currentHeap.get(i) + " ");
        }
        System.out.println();
    }
    
    // display the root node
    public static void displayMax(ArrayList<Integer> currentHeap) {
        System.out.println(currentHeap.get(1));
    }
    
    // delete the root node
    public static ArrayList<Integer> deleteMax(ArrayList<Integer> heapToAlter) {
        heapToAlter.set(1, heapToAlter.get(heapToAlter.size() - 1));
        heapToAlter.remove(heapToAlter.size() - 1);
        return postDeleteHeapification(heapToAlter);
    }
    
    // insert new leaf
    public static ArrayList<Integer> insert(Integer newNumber, ArrayList<Integer> heapToAlter) {
        heapToAlter.add(newNumber);
        return postInsertHeapification(heapToAlter);
    }
    
    // first instance of forming the heap after user-created binary tree has been created
    // starts at the last parent node in the tree and begins heapification comparing parent to child
    public static ArrayList<Integer> initialHeapification(ArrayList<Integer> treeToHeapify) {
        for(int i = treeToHeapify.size() / 2; i >= 1; i--) {
            buildMaxHeap(treeToHeapify, i);
        }
        return treeToHeapify;
    }
    
    // used to help in the building of the initialHeapification function
    public static ArrayList<Integer> buildMaxHeap(ArrayList<Integer> treeToHeapify, int parentNode) {
        int leftChild = 2 * parentNode;
        int rightChild = (2 * parentNode) + 1;
        Integer max = 0;
        if(leftChild <= treeToHeapify.size() - 1 && treeToHeapify.get(leftChild) > treeToHeapify.get(parentNode)) {
            max = leftChild;
        }
        else {
            max = parentNode;
        }
        if(rightChild <= treeToHeapify.size() - 1 && treeToHeapify.get(rightChild) > treeToHeapify.get(max)) {
            max = rightChild;
        }
        if(max != parentNode) {
            Integer temp = treeToHeapify.get(parentNode);
            treeToHeapify.set(parentNode, treeToHeapify.get(max));
            treeToHeapify.set(max, temp);
            buildMaxHeap(treeToHeapify, max);
        }
        return treeToHeapify;
    }
    
    // begins at the newly inserted leaf and heapifies by comparing child to parent
    public static ArrayList<Integer> postInsertHeapification(ArrayList<Integer> currentHeap) {
        reheapifyAfterInsert(currentHeap, currentHeap.size() - 1);
        return currentHeap;
    }
    
    // used to help in the rehepification process following the insertion of a new leaf
    public static ArrayList<Integer> reheapifyAfterInsert(ArrayList<Integer> treeToHeapify, int currentLeaf) {
        int parent = currentLeaf / 2;
        Integer max = 0;
        
        if(parent > 0 && treeToHeapify.get(parent) < treeToHeapify.get(currentLeaf)) {
            Integer temp = treeToHeapify.get(currentLeaf);
            treeToHeapify.set(currentLeaf, treeToHeapify.get(parent));
            treeToHeapify.set(parent, temp);
            reheapifyAfterInsert(treeToHeapify, parent);
        }
        
        return treeToHeapify;
    }
    
    
    // begins at the root and heapifies by comparing root to children
    public static ArrayList<Integer> postDeleteHeapification(ArrayList<Integer> currentHeap) {
        reheapifyAfterDelete(currentHeap, 1);
        return currentHeap;
    }
    
    // used to help in the rehipification process following the deletion of the max value/root
    public static ArrayList<Integer> reheapifyAfterDelete(ArrayList<Integer> treeToHeapify, int currentNode) {
        int leftChild = 2 * currentNode;
        int rightChild = (2 * currentNode) + 1;
        Integer max = 0;
        
        if(rightChild <= treeToHeapify.size() - 1 && treeToHeapify.get(leftChild) < treeToHeapify.get(rightChild)) {
            max = rightChild;
        }
        else {
            max = leftChild;
        }
        
        if(max <= treeToHeapify.size() - 1 && treeToHeapify.get(max) > treeToHeapify.get(currentNode)) {
            Integer temp = treeToHeapify.get(max);
            treeToHeapify.set(max, treeToHeapify.get(currentNode));
            treeToHeapify.set(currentNode, temp);
            reheapifyAfterDelete(treeToHeapify, max);
        }
        return treeToHeapify;
    }
    
}