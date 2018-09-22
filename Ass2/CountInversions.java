/* The main is written by
   Fang Dong - 09/06/2014 for Lab 2.
   
   Modified by Nelson Dai(V00815253)
*/
import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;


public class CountInversions{

	/* ... My actual code ... */
	public static int CountInversions(int[] A){
		int omg = 0;                     
		int j;     			        
		for(int i=(A.length-1);i>0;i--){    
			j=i;                            
			while(A[j-1] > A[j]){			
			    int temp = A[j];			
			    A[j] = A[j-1];				
			    A[j-1] = temp;				
			    if(j < (A.length-1)){		
			    	j++;					
			    }							
			    omg ++;					
			}								
		}									
		return omg;						
	}
	 /*...My actual code ends here...*/


	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();
		
		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);
		
		int[] array = new int[inputVector.size()];
		
		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);
		
		
		long startTime = System.currentTimeMillis();
		
		int inversionCount = CountInversions(array);
		
		long endTime = System.currentTimeMillis();
		
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		
		//System.out.printf("Array %s values which add to 225.\n",TripleExists? "contains":"does not contain");
		System.out.println("Number of inversions: " + inversionCount);
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}
}
