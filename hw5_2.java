/**
 * Title: Time Efficiency Trials of Mergesort and Quicksort
 * Abstract: This program measures the elapsed time it takes for mergesort and quicksort to each sort the
 *  same array filled with random values. When both sorting algorithms are finished executing, their
 *  respective results are then printed so the user can view them and compare.
 * Name: Jordan Guzman
 * ID: 0913
 * Date: 12/7/2021
 * Mergesort and Quicksort Algorithms Credit:
 *      Mergesort: https://www.interviewbit.com/tutorial/merge-sort-algorithm/
 *      Quicksort: https://www.algolist.net/Algorithms/Sorting/Quicksort
 */

import java.util.*;

public class hw5_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sizeOfArray = input.nextInt();
        Integer[] arrayOfNumbers = new Integer[sizeOfArray];
        int[] arrayOfRandomNumbers = new int[sizeOfArray];
        Random rand = new Random(sizeOfArray);
        
        for(int i = 0; i < sizeOfArray; i++) {
        	arrayOfNumbers[i] = i;	
        }
        
        List<Integer> temp = Arrays.asList(arrayOfNumbers);
        
        Collections.shuffle(temp);
        
        for(int i = 0; i < temp.size(); i++) {
            arrayOfRandomNumbers[i] = (int)temp.get(i);
        }
        
        double startMergesortTime = System.nanoTime();
        mergeSort(arrayOfRandomNumbers, 0, arrayOfRandomNumbers.length - 1);
        double endMergesortTime = System.nanoTime();
        double timeElapsedForMergesort = (endMergesortTime - startMergesortTime) / 1000000;
        
        double startQuicksortTime = System.nanoTime();
        quickSort(arrayOfRandomNumbers, 0, arrayOfRandomNumbers.length - 1);
        double endQuicksortTime = System.nanoTime();
        double timeElapsedForQuicksort = (endQuicksortTime - startQuicksortTime) / 1000000;
        
        System.out.println("===================== Execution Time ====================");
        System.out.println("Merge Sort:" + '\t' + timeElapsedForMergesort + " milliseconds");
        System.out.println("Quick Sort:" + '\t' + timeElapsedForQuicksort + " milliseconds");
        System.out.println("=========================================================");
        
    }
    
    // example of merge sort in Java
    // merge function take two intervals
    // one from start to mid
    // second from mid+1, to end
    // and merge them in sorted order
    public static void merge(int Arr[], int start, int mid, int end) {
    
    	// create a temp array
    	int temp[] = new int[end - start + 1];
    
    	// crawlers for both intervals and for temp
    	int i = start, j = mid+1, k = 0;
    
    	// traverse both arrays and in each iteration add smaller of both elements in temp 
    	while(i <= mid && j <= end) {
    		if(Arr[i] <= Arr[j]) {
    			temp[k] = Arr[i];
    			k += 1; i += 1;
    		}
    		else {
    			temp[k] = Arr[j];
    			k += 1; j += 1;
    		}
    	}
    
    	// add elements left in the first interval 
    	while(i <= mid) {
    		temp[k] = Arr[i];
    		k += 1; i += 1;
    	}
    
    	// add elements left in the second interval 
    	while(j <= end) {
    		temp[k] = Arr[j];
    		k += 1; j += 1;
    	}
    
    	// copy temp to original interval
    	for(i = start; i <= end; i += 1) {
    		Arr[i] = temp[i - start];
    	}
    }
    
    // Arr is an array of integer type
    // start and end are the starting and ending index of current interval of Arr
    
   public static void mergeSort(int Arr[], int start, int end) {
    
    	if(start < end) {
    		int mid = (start + end) / 2;
    		mergeSort(Arr, start, mid);
    		mergeSort(Arr, mid+1, end);
    		merge(Arr, start, mid, end);
    	}
    }
    
	public static int partition(int arr[], int left, int right)	{
	
		int i = left, j = right;
		
		int tmp;
		
		int pivot = arr[(left + right) / 2];
		
		
		
		while (i <= j) {
		
			while (arr[i] < pivot)
			
			  i++;
			
			while (arr[j] > pivot)
			
			  j--;
			
			if (i <= j) {
			
			  tmp = arr[i];
			
			  arr[i] = arr[j];
			
			  arr[j] = tmp;
			
			  i++;
			
			  j--;
			
			}
		
		};
		
		return i;
	}
		
		
		
	public static void quickSort(int arr[], int left, int right) {
	
		int index = partition(arr, left, right);
		
		if (left < index - 1)
		
		quickSort(arr, left, index - 1);
		
		if (index < right)
		
		quickSort(arr, index, right);
	}
}