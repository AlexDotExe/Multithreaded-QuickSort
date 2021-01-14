import java.io.*;
import java.*;
public class Lab5p2 {
	
	static class firstSplit extends Thread{
		int l,r;
		public firstSplit(int left,int right){
		l = left;
		r = right;
		}
		
		@Override
		public void run(){
		Qs q = new Qs();
 		q.quickS(l, r);
		}
	}
	
	static class Qs{
		static double[] arr;
		static int runs;
		firstSplit thread1;
	public Qs(double[] d){
		arr =d;
		runs = 0;
		}
	public Qs(){
		if(arr== null)throw new IllegalArgumentException();
	}
	void swap(int left, int right){
		if (left <= right) {
			double temp;
			temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
		}
	}
	
	void quickS(int left,int right) {
		runs++;
		int i = left, j = right;
		double pivot = arr[(left + right) / 2];
	
		while (i <= j) {
			
			while (arr[i] < pivot)	i++;
			while (arr[j] > pivot)  j--;
			if (i <= j) {
				swap(i,j);
				i++;
				j--;
			}
		}
		if(runs < 2 && left < j){
		thread1 = new firstSplit(left,j); thread1.start();} 
		else if (left < j)quickS(left, j);
		if (i < right)quickS(i, right);
		}
	
	double[] getArr(){
		return arr;
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fill an existing array with random doubles
		double[] ArrayToBeSorted = new double[10000];
		for (int i = 0; i < 10000; ++i)	ArrayToBeSorted[i] = Math.random( );
		
		Qs thread0 = new Qs(ArrayToBeSorted);	
		thread0.quickS(0,9999);
		ArrayToBeSorted = thread0.getArr();
		
		try{
			for(int i =0; i< ArrayToBeSorted.length;i++){
				System.out.println(ArrayToBeSorted[i]);
			}
			for(int i =0; i< ArrayToBeSorted.length;i++)
			if(ArrayToBeSorted[i]>ArrayToBeSorted[i+1]){
				System.out.println("Array is Unsorted");break;}
		}catch(ArrayIndexOutOfBoundsException e){
			if(ArrayToBeSorted[9998] > ArrayToBeSorted[9999]) System.out.println("Array is unsorted");
			else System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Array is Sorted"); }
		}
	}