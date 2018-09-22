/* The main is written by
   Fang Dong - 09/06/2014 for Lab 2.
   
   Modified by Nelson Dai(V00815253)
*/
import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class TripleSum {
	public static boolean TripleSum225(int[] A){

		int n = A.length;

		

		/* ... My actual code ... */

		//initialize a new arry contains -1 in every index		
		int[] good = new int[226];
		for(int c=0;c<226;c++){
 		   good[c]=-1;	
		}

		//put all values that less than 225 into the arry with the same index as itself	
		for(int j=0; j<n; j++){
		   if(A[j]<=225)
		    good[A[j]] = A[j];			
		}
               
		//calculate all possible combinations that makes 225 and check if those combination exist
		   for (int a = 0; a < 226; a++) {
                   for (int b = 0; b < 226; b++) {
            	   for (int c = 0; c < 226; c++) {
                    if (a + b + c == 225 && good[a] != -1 && good[b] != -1 && good[c] != -1)
                    return true;           
            }
        }
    }
		return false;
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

		boolean TripleExists = TripleSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s values which add to 225.\n",TripleExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}

}
