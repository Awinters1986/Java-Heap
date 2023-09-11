import java.io.BufferedReader;
import java.io.FileReader;

public class PS1Sort {
	
	public static void main(String[] args) {
	       PS1Sort ps1 = new PS1Sort();
	       UASong[] A = new UASong[10];
	       String file = "ps1.txt";
	       String sort = "msort";
	       String field = "rt";
	       int r = 10;
	       
	       ps1.load(A, file);
	       long start = System.currentTimeMillis();
	       
	       if(field.equals("song")) {
	    	   if(sort.equals("isort")) {
	               ps1.isortn(A);
	           } else if(sort.equals("bsort")) {
	               ps1.bsortn(A);
	           } else {
	               ps1.msortn(A, 0, A.length);
	           }
           } else {
               if(sort.equals("isort")) {
	               ps1.isortr(A);
	           } else if(sort.equals("bsort")) {
	               ps1.bsortr(A);
	           } else {
	               ps1.msortr(A, 0, r);
	           }
           }
	       
	       long end = System.currentTimeMillis();
	       long runtime = end - start;
	        
	       ps1.rt(runtime);
	}
	
	
    public void load(UASong[] A, String file) {
        String line;
        int i = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                 
                A[i] = new UASong(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                i++;    
            }
            
            br.close();
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void isortn(UASong[] A) {
        UASong temp = new UASong();
        int i = 0;
        
        System.out.println(A[2].getName());
        for(int j = 1; j < A.length; j++) {
        	temp = A[j];
            i = j - 1;
            
            int keyConv = charConvert(A[j].getName());
            
            while((i >= 0) && (charConvert(A[i].getName()) > keyConv)) {
            	
                A[i + 1] = A[i];
                
                i = i - 1;
            }
            A[i + 1] = temp;
        }
        print(A);
    }
    
    public void isortr(UASong[] A) {
        UASong temp = new UASong();
        int i = 0;
        
        for(int j = 1; j < A.length; j++) {
        	temp = A[j];
            i = j - 1;
            
            int keyConv = A[j].getSongrt();
            
            
            while((i >= 0) && (A[i].getSongrt() > keyConv)) {
            	
                A[i + 1] = A[i];
                
                i = i - 1;
            }
            A[i + 1] = temp;
        }
        print(A);
    }
    
    public void bsortn(UASong[] A){
                
        for(int k = 0; k < A.length; k++) {
            for(int i = 1; i < A.length; i++) {
                if(charConvert(A[i - 1].getName()) > charConvert(A[i].getName())) {
                    swap(A, i - 1, i);
                }
            }
        }
        
        print(A);
    }
    
    public void bsortr(UASong[] A){
        
        for(int k = 0; k < A.length; k++) {
            for(int i = 1; i < A.length; i++) {
                if(A[i - 1].getSongrt() > A[i].getSongrt()) {
                    swap(A, i - 1, i);
                }
            }
        }
        
        print(A);
    }
    
     public void swap(UASong[] A, int i, int j){        
        UASong temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    public void msortn(UASong[] A, int p, int r) {
        
        if(p < r){
            int q = ((p + r)/2);
            msortn(A, p, q);
            msortn(A, q + 1, r);
            mergen(A, p, q, r);
        }

        print(A);
    }
    
    public void mergen(UASong[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        
        UASong[] left = new UASong[n1];
        UASong[] right = new UASong[n2];
        
        for(int i = 1; i < (left.length - 1); i++) {
            left[i] = A[p + i - 1];
        }
        
        for(int j = 1; j < n2; j++) {
            right[j] = A[q + j];
        }
        
        int i = 1;
        int j = 1;
        
        for(int k = p; k < r; k++) {
           int l = charConvert(left[i].getName());
           int ri = charConvert(right[j].getName());
           if (l <= ri) {
               A[k] = left[i];
               i = i + 1;
           } else {
               A[k] = right[j];
               j = j + 1;
           }
        }
    }
    
    public void msortr(UASong[] A, int p, int r) {
        
        if(p < r){
            int q = ((p + r)/2);
            msortr(A, p, q);
            msortr(A, q + 1, r);
            merger(A, p, q, r);
        }

        print(A);
    }
    
    public void merger(UASong[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        
        UASong[] left = new UASong[n1];
        UASong[] right = new UASong[n2];
        
        for(int i = 1; i < (left.length - 1); i++) {
            left[i] = A[p + i - 1];
        }
        
        for(int j = 1; j < n2; j++) {
            right[j] = A[q + j];
        }
        
        int i = 1;
        int j = 1;
        
        for(int k = p; k < r; k++) {
           int l = left[i].getSongrt();
           int ri = right[j].getSongrt();
           if (l <= ri) {
               A[k] = left[i];
               i = i + 1;
           } else {
               A[k] = right[j];
               j = j + 1;
           }
        }
    }
    
    public int charConvert(String key) {
    	char a = key.charAt(0);
        int ascii = (int)a;
        return ascii;
    }
    
    public void rt(long runtime) {
        //TimeField.setText(String.valueOf(runtime));
    	System.out.println(runtime);
    }
    
    public void print(UASong[] A) {
    	for(int i = 0; i < A.length; i++) {
    		System.out.println(A[i].toString());
    	}
    }
	
	
	
	
	  public static class UASong {
	        public String artist;
	        public String name;
	        public int songrt;
	        
	         public UASong() {
	            artist = "";
	            name = "";
	            songrt = 0;
	        }
	        
	        public UASong(String artist, String name, int songrt) {
	            this.artist = artist;
	            this.name = name;
	            this.songrt = songrt;
	        }
	        
	        public String getArtist() {
	            return artist;
	        }
	        
	        public String getName() {
	            return name;
	        }
	        
	        public int getSongrt() {
	            return songrt;
	        }
	        
	        public void setArtist(String artist) {
	            this.artist = artist;
	        }
	        
	        public void setName(String name) {
	            this.name = name;
	        }
	        
	        public void setSongrt(int songrt) {
	            this.songrt = songrt;
	        }
	        
	        public String toString() {			
				return String.format("Song: " + this.artist + " - " + this.name + " - " + this.songrt);
			}
	    }

}
