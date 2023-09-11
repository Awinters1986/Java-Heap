/*  Date:           06/15/2020
 *  Filename:       UAHeap.java
 *  Description:    Implementation of a priority queue using a heap
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
 
public class UAHeap {
	
    static final int INITIAL_SIZE = 1;		//just to declare the array with the default starting point

    private int[] A;		//This will be used to store the values of the heap

    // Default, no-argument constructor
    public UAHeap() {
       this(INITIAL_SIZE); 
    }

    // Construct a UAHeap with the size provided
    public UAHeap(int size) {
		
    }
    
    // Retrieves the min value from the heap
    public int getMinValue(int[] A, int hs) {
    	
    	int min = A[0];
    	
    	for(int i = 1; i < hs; ++i) {
    		min = Math.min(min, A[i]);
    	}
    	
    	return min;
    }
    
    // Removes and returns the min value from the heap while preserving the heap properties
    public int removeMinValue(int[] A, int hs) {
    	
    	int min = A[0];
    	int temp = 0;
    	
    	for(int i = 1; i < hs; ++i) {
    		min = Math.min(min, A[i]);
    	}
    	
    	temp = A[min]; 
    	A[min] = A[hs];
    	A[hs] = temp;
    	hs = hs - 1;
    	
    	heapifyDown(A, 0, min);
    	
    	return min;
	    
    }
    
    // Builds the heap structure by starting from ((int) n/2)
    public void buildMinHeap(int[] A) {
    	int hs = A.length;
    	for(int i = A.length/2; i < A.length; i++) {
    		minHeapify(A, i);
    	}
    }
   
    // Insert a value into the heap    
    public void insertValue(int[] A, int value) {
	    int hs = size(A) + 1;
	    A[hs] = Integer.MIN_VALUE;
	    heapifyDown(A, hs, value);
    }
    
    // Returns the number of elements within the heap
    public int size(int[] A) {
    	int count = 0;
    	int i = 0;
    	
    	while(A[i] != 0 && i < A.length) {
    		count++;
    		i++;
    	}
    	
    	return count;
    }
    
    // Reorganizes an element at the given index moving downward (if needed)
    public void heapifyDown(int[] A, int index, int value) {
    	if(value > A[index]) {
    		return;
    	} else {
    		A[index] = value;
    		
    		int temp = 0;
    		
    		while((index > 1) && (A[parent(index)] > A[index])) {
    			temp = A[index]; 
    	    	A[1] = A[parent(index)];
    	    	A[index] = temp;
    			index = parent(index);
    		}
    	}
    }
    
    // Decreases the value at the specified index and moves it upward (if needed).
    public void heapifyUp(int[] A, int index, int value) {
    	if(value < A[index]) {
    		return;
    	} else {
    		A[index] = value;
    		
    		int temp = 0;
    		
    		while((index > 1) && (A[parent(index)] < A[index])) {
    			temp = A[index]; 
    	    	A[1] = A[parent(index)];
    	    	A[index] = temp;
    			index = parent(index);
    		}
    	}	
    }
    
    // Loads data into the heap from a file (line-delimited)
    public void loadData(int[] A, String filename) {
    	String line;
    	int i = 0;
    	
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader(filename));
	    	
	    	while((line = br.readLine()) != null) {
                 
                A[i] = Integer.parseInt(line);
                i++;    
            }
	    	
	    	br.close();
	    	
    	} catch(Exception e) {
    		System.out.println("Error: " + e);
    	}
    }
    
    // Writes the contents of the heap into the specified file (line-delimited)
    public void saveDataToFile(int[] A, String filename) {
    	String line;
    	int hs = size(A);
    	
    	for(int i = 0; i < size(A); i++) {
    		System.out.println(A[i]);
    	}
    	
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
    		
    		for(int i = 0; i < hs; i++) {
    			bw.write(A[i]);
    			System.out.println();
    		}
    		
    		bw.close();
    	} catch(Exception e) {
    		System.out.println("Error: " + e);
    	}   
    }
    
    // Returns the values from the heap in ascending order
    public int[] getSortedHeap(int[] A) {
	    buildMaxHeap(A);
	    
	    int temp = 0;
	    int hs = size(A);
	    
	    for(int i = (hs - 1); i > 0; i--) {
	    	temp = A[0]; 
	    	A[0] = A[i];
	    	A[i] = temp;
	    	hs = (hs - 1);
	    	maxHeapify(A, i, 0);
	    }
	    
	    return A;
    }
    
    public int left(int i) {
    	return (2 * i);
    }
    
    public int right(int i) {
    	return ((2 * i) + 1);
    }
    
    public int parent(int i) {
    	return (i / 2);
    }
    
    public void maxHeapify(int[] A, int hs, int i) {
    	int l = left(i);
    	int r = right(i);
    	int largest = i;
    	int temp = 0;
    	
    	if((l <= hs) && (A[l] > A[i])) {
    		largest = l;
    	} else {
    		largest  = i;
    	}
    	
    	if((r <= hs) && (A[r] > A[largest])) {
    		largest = r;
    	}
    	
    	if(largest != i) {
    		temp = A[i]; 
	    	A[i] = A[largest];
	    	A[largest] = temp;
    		maxHeapify(A, hs, largest);
    	}
    }
    
    public void buildMaxHeap(int[] A) {
    	int hs = size(A);
    	for(int i = A.length/2; i >= 0; i--) {
    		maxHeapify(A, hs, i);
    	}
    }
    
    public void minHeapify(int[] A, int i) {
    	int l = left(i);
    	int r = right(i);
    	int smallest = 0;
    	int hs = size(A);
    	int temp = 0;
    	
    	if((l <= hs) && (A[l] < A[i])) {
    		smallest = l;
    	} else {
    		smallest = i;
    	}
    	
    	if((r <= hs) && (A[r] < A[smallest])) {
    		smallest = r;
    	}
    	
    	if(smallest != i) {    		
    		temp = A[i]; 
	    	A[smallest] = A[i];
	    	A[i] = temp;
    		minHeapify(A, smallest);
    	}
    }
    
    public static void main (String[] args) {
    	String filename = "heap.txt";
    	String file = "heapWrite.txt";
    	
    	int[] A = new int[12];
    	
    	UAHeap heap = new UAHeap();
    }  
}

