/**
 * Title: Topological Sort
 * Abstract: This program implements a Kahn's Topological Sorting algorithm to sort an array
 *  filled with values provided by user input.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 11/30/2021
 */

import java.util.*;

public class hw4_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfVertices = input.nextInt();
        int numberOfEdges = input.nextInt();
        int[] inDegree = new int[numberOfVertices];
        int[] inDegreeCopyToPrint = new int[numberOfVertices];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        int source = 0;
        int destination = 0;
        ArrayList<Integer> queueOfOrder = new ArrayList<Integer>();
        ArrayList<Integer> orderVisited = new ArrayList<Integer>();
        
        // instantiates a 2D arraylist with empty nested arraylists and the inDegree array with 0s
        for(int i = 0; i < numberOfVertices; i++) {
            graph.add(new ArrayList<Integer>());
            inDegree[i] = 0;
            inDegreeCopyToPrint[i] = 0;
        }
        
        // adds sources and destinations into the arraylists nested within the main arraylist
        for(int i = 0; i < numberOfEdges; i++) {
            source = input.nextInt();
            destination = input.nextInt();
            graph.get(source).add(destination);
            inDegree[destination] += 1;
            inDegreeCopyToPrint[destination] += 1;
        }
        
        graph = sortInConventialOrder(graph);
        
        orderVisited = topologicalSort(graph, inDegree, queueOfOrder, orderVisited);
        
        printInDegreeOrder(inDegreeCopyToPrint);
        printTopologicalOrder(orderVisited, graph);
    }
    
    // print in-degree array according to Dr. Byun's specifications
    public static void printInDegreeOrder(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println("In-degree" + "[" + i + "]" + ":" + arr[i]);
        }
    }
    
    // print topologically-ordered arraylist according to Dr. Byun's specifications
    public static void printTopologicalOrder(ArrayList<Integer> topologicallySorted, ArrayList<ArrayList<Integer>> initialGraph) {
        if(topologicallySorted.size() < initialGraph.size()) {
            System.out.println("No Order:");
        }
        else {
            System.out.print("Order:");
            for(int i = 0; i < topologicallySorted.size() - 1; i++) {
                System.out.print(topologicallySorted.get(i) + "->");
            }
            System.out.println(topologicallySorted.get(topologicallySorted.size() - 1));
        }
    }
    
    // sort nested arraylists in conventional order used for class
    public static ArrayList<ArrayList<Integer>> sortInConventialOrder(ArrayList<ArrayList<Integer>> arrayListGraph) {
        for(int i = 0; i < arrayListGraph.size(); i++) {
            Collections.sort(arrayListGraph.get(i));
        }
        return arrayListGraph;
    }
    
    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> arrayListGraph, int[] arrOfInDegree, 
        ArrayList<Integer> queue, ArrayList<Integer> sorted) {
        // initial traversal to find nodes that don't have incoming edges and push them to queue
        for(int i = 0; i < arrOfInDegree.length; i++) {
            if(arrOfInDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // traverse the queue to topologically sort nodes
        while(!queue.isEmpty()) {
            int currentVertex = queue.get(0);
            sorted.add(currentVertex);
            queue.remove(0);
            
            // decrement non-zero values of the in-degree array corresponding to the nested arraylist
            for(int i = 0; i < arrayListGraph.get(currentVertex).size(); i++) {
                // checks the indexes of the nested arraylist corresponding to the index of the main arraylist that was pushed onto the queue
                // this removes incoming edges to nodes that were connected to a source node that had no incoming edges
                if(arrOfInDegree[arrayListGraph.get(currentVertex).get(i)] != 0) {
                    arrOfInDegree[arrayListGraph.get(currentVertex).get(i)] -= 1;
                }
            }
            
            // push zero-valued node of the in-degree array corresponding to the nested arraylist onto the queue
            for(int i = 0; i < arrayListGraph.get(currentVertex).size(); i++) {
                /* 
                 * checks the indexes of the nested arraylist corresponding to the index of the main arraylist that was pushed onto 
                 * the queue this runs through the indexes again to push any nodes that had incoming edges removed which resulted in 
                 * now having zero incoming edges
                 */
                if(arrOfInDegree[arrayListGraph.get(currentVertex).get(i)] == 0) {
                    queue.add(arrayListGraph.get(currentVertex).get(i));
                }
            }
        }
        return sorted;
    }
}