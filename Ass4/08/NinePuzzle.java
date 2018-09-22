import java.util.*;
import java.io.File;

public class NinePuzzle{

	//The total number of possible boards is 9! = 1*2*3*4*5*6*7*8*9 = 362880
	public static final int NUM_BOARDS = 362880;

	
	/*  SolveNinePuzzle(B)
		Given a valid 9-puzzle board (with the empty space represented by the 
		value 0),return true if the board is solvable and false otherwise. 
		If the board is solvable, a sequence of moves which solves the board
		will be printed, using the printBoard function below.
	*/
	public static boolean SolveNinePuzzle(int[][] B) {
		int[][] graph = new int[NUM_BOARDS][4];
		graph = list();
		return(bfs(graph, getIndexFromBoard(B), 0));
	}

	
	public static int[][] list() {
        int [][] boards = new int[NUM_BOARDS][4];
        int m = 0; 
       	while(m < NUM_BOARDS) {
       		int n = 0;
			while(n < 4) {
				boards[m][n] = -1;
				n++;
			}
			m++;
		}
        
		int row = -1;
		int column = -1;
		int i = 0;
		while(i < NUM_BOARDS) {
			int[][] puzzle = getBoardFromIndex(i);
			int j = 0;
			while(j < 3) {
				int k = 0;
            	while(k < 3) {
               		if(puzzle[j][k] == 0){
						row = j;
						column = k;
					}
					k++;
				}
				j++;	
			}

			int bound = 0; 
            
			if(row > 0){
                int[][] getBoards = getBoardFromIndex(i);  
                getBoards[row][column] = getBoards[row - 1][column];
                getBoards[row - 1][column] = 0;
                int index = getIndexFromBoard(getBoards);
                boards[i][bound] = index;
                bound++;
			} 
			
			if(row < 2){
	            int[][] getBoards = getBoardFromIndex(i);   
	            getBoards[row][column] = getBoards[row + 1][column];
	            getBoards[row + 1][column] = 0;
	            int index = getIndexFromBoard(getBoards);
	            boards[i][bound] = index;
	            bound++;
			}
            
			if(column > 0){
           		int[][] getBoards = getBoardFromIndex(i);     
                getBoards[row][column] = getBoards[row][column - 1];
                getBoards[row][column - 1] = 0;
                int index = getIndexFromBoard(getBoards);
                boards[i][bound] = index;
                bound++;
			}
			
			if(column < 2){
	            int[][] getBoards = getBoardFromIndex(i);     
	           	getBoards[row][column] = getBoards[row][column + 1];
	           	getBoards[row][column + 1] = 0;
              	int index = getIndexFromBoard(getBoards);
            	boards[i][bound] = index;
            	bound++;	
			}
			i++;
		}
        return boards;
	}
	

	public static boolean bfs(int[][] graph,int position, int zero) {
		boolean[] result = new boolean[NUM_BOARDS]; 
        ArrayDeque<Integer> queue1 = new ArrayDeque<Integer>();       
		ArrayDeque<Integer[]> queue2 = new ArrayDeque<Integer[]>(); 
        
        int length = result.length;
        int i = 0;
		while(i < length) { 
			result[i] = false;
			i++;
		}
        
		queue1.add(position); 
        Integer[] array= new Integer[1];
        array[0] = position; 
        queue2.add(array);
        result[position] = true; 
		
		while(queue1.size() != 0) { 
			int remove = queue1.remove(); 
            Integer[] rearrange = queue2.remove(); 
            if(remove != zero){ 
				result[remove] = true;
				int j = 0;
                while(j < 4) { 
					int element = graph[remove][j];
					if(element == -1){ 
						break; 
					}
                    if(result[element] == false) { 
						queue1.add(element);
						Integer[] key = new Integer[rearrange.length + 1]; 
						
						int k = 0;
						while(k < rearrange.length) {
							key[k] = rearrange[k];
							k++;
						}
						key[rearrange.length] = element;
						queue2.add(key);
					}
					j++;
				}
			} else { 
				int j = 0;
				while(j < rearrange.length) {
					printBoard(getBoardFromIndex(rearrange[j]));
					j++;
				}
				return true;
			}
		}
		return false;
	}


	/*  printBoard(B)
		Print the given 9-puzzle board. The SolveNinePuzzle method above should
		use this method when printing the sequence of moves which solves the input
		board. If any other method is used (e.g. printing the board manually), the
		submission may lose marks.
	*/
	public static void printBoard(int[][] B){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				System.out.printf("%d ",B[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	
	/* Board/Index conversion functions
	   These should be treated as black boxes (i.e. don't modify them, don't worry about
	   understanding them). The conversion scheme used here is adapted from
		 W. Myrvold and F. Ruskey, Ranking and Unranking Permutations in Linear Time,
		 Information Processing Letters, 79 (2001) 281-284. 
	*/
	public static int getIndexFromBoard(int[][] B){
		int i,j,tmp,s,n;
		int[] P = new int[9];
		int[] PI = new int[9];
		for (i = 0; i < 9; i++){
			P[i] = B[i/3][i%3];
			PI[P[i]] = i;
		}
		int id = 0;
		int multiplier = 1;
		for(n = 9; n > 1; n--){
			s = P[n-1];
			P[n-1] = P[PI[n-1]];
			P[PI[n-1]] = s;
			
			tmp = PI[s];
			PI[s] = PI[n-1];
			PI[n-1] = tmp;
			id += multiplier*s;
			multiplier *= n;
		}
		return id;
	}
		
	public static int[][] getBoardFromIndex(int id){
		int[] P = new int[9];
		int i,n,tmp;
		for (i = 0; i < 9; i++)
			P[i] = i;
		for (n = 9; n > 0; n--){
			tmp = P[n-1];
			P[n-1] = P[id%n];
			P[id%n] = tmp;
			id /= n;
		}
		int[][] B = new int[3][3];
		for(i = 0; i < 9; i++)
			B[i/3][i%3] = P[i];
		return B;
	}
	

	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read boards until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading board %d\n",graphNum);
			int[][] B = new int[3][3];
			int valuesRead = 0;
			for (int i = 0; i < 3 && s.hasNextInt(); i++){
				for (int j = 0; j < 3 && s.hasNextInt(); j++){
					B[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < 9){
				System.out.printf("Board %d contains too few values.\n",graphNum);
				break;
			}
			System.out.printf("Attempting to solve board %d...\n",graphNum);
			long startTime = System.currentTimeMillis();
			boolean isSolvable = SolveNinePuzzle(B);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			if (isSolvable)
				System.out.printf("Board %d: Solvable.\n",graphNum);
			else
				System.out.printf("Board %d: Not solvable.\n",graphNum);
		}
		graphNum--;
		System.out.printf("Processed %d board%s.\n Average Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>1)?totalTimeSeconds/graphNum:0);

	}

}
