/*
 *Author: Nelson Dai-V00815253
 *CSC 225-Assignment 3
 *July 1st/2016
*/

import java.util.*;
import java.util.Scanner;

public class  SortedSumsOfCubics {	
	
	/*public static int getCube(int i, int j) {
		return i*i*i+j*j*j;
	}*/

	public static void SortedSumsOfCubics (int i) {
		PriorityQueue<triple> list= new PriorityQueue<triple> ();
		for (int c=0;c<i+1;c++) {
			list.add(new triple(c,0));
		}

		triple tmp;
		int last = -1;
		while(!list.isEmpty()) {
			tmp=list.poll();
			if (tmp.item!=last) {
				System.out.print(tmp+" ");
			}
			last=tmp.item;
			if (tmp.j<tmp.i) {
				list.add(new triple(tmp.i,tmp.j+1));
			}
		}
	}
	
	public static void main(String [] args) {
		Scanner aScanner = new Scanner(System.in);
		System.out.print("Please enter an number: ");
			String aString = aScanner.nextLine();
		int n= Integer.parseInt(aString);
		SortedSumsOfCubics(n);
		System.out.println();
	}	
}

class triple implements Comparable<triple> {
	int i,j,item;
	triple(int i,int j) {
		this.i=i;
		this.j=j;
		item=i*i*i+j*j*j;
	}
	public int compareTo(triple other) {
		return item-other.item;
	}
	public String toString() {
		return item+"";
	}	
}



