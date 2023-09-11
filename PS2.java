import java.io.BufferedReader;
import java.io.FileReader;

public class PS2 {
	
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
    
    public void saveDataToFile(int[] A) {
    	String line;
    	int hs = size(A);
    	
    	for(int i = 0; i < size(A); i++) {
    		System.out.println(A[i]);
    	}
    }
    
    public int size(int[] A) {
    	int count = 0;
    	int i = 0;
    	
    	while(A[i] != 0 && i < A.length) {
    		count++;
    		i++;
    	}
    	
    	return count;
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
    
    public void uaHeap1(int[] A) {
    	int hs = size(A);
    	for ( int i = (A.length)/2; i > 0; i-- ) { 
    		maxHeapify(A,hs, i); 
    	}
     }
    	
    public void uaHeap2(int[] A) { 
    	int hs = 1;
    	for ( int i = 2; i < A.length; i++ ) { 
    		heapInsert(A, A[i]);
    	}
    }
    
    public void heapInsert(int[] A, int key) { 
    	int hs = size(A) + 1; 
    	A[hs] = Integer.MIN_VALUE; 
    	increaseKey(A, hs, key);
    }
    
    public void increaseKey(int[] A, int i, int key) { 
    	int temp = 0;
    	
    	if ( key < A[i] ) { 
    		System.out.println("Error!"); 
    		}
    	A[i] = key;
    	while ( i > 1 && (A[parent(i)] < A[i] )) {    		
    		temp = A[i]; 
	    	A[i] = A[parent(i)];
	    	A[i] = temp;
			i = parent(i);
    	}
    	
    }

    
    public static void main (String[] args) {
    	PS2 ps = new PS2();
    	String filename = "heap.txt";
    	
    	int[] A = new int[12];
    	
    	int hs = ps.size(A);
    	
    	ps.loadData(A, filename);
    	
    	ps.uaHeap1(A);
    	ps.saveDataToFile(A);
    	
    	System.out.println("*****************");
    	
    	ps.uaHeap2(A);
    	ps.saveDataToFile(A);
    }
}
