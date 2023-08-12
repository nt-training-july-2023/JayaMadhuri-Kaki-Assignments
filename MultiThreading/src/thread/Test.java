package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Fibnocci extends Thread{
	private int i = 0, j = 1, a, k = 0;
	Fibnocci(int a){
		this.a = a;
	}
	public void run() {
		System.out.println("fibnocci series below number: ");
		while(k < a) {
			k=i+j;
			if(k<a) {
			System.out.println(k);
			}
			i=j;
			j=k;
		}
	}
	
}
class ReverseList extends Thread{
	private ArrayList<Integer> l;
	ReverseList(ArrayList<Integer> l){
		this.l = l;
	}
	public void run() {
		Collections.reverse(l);
		System.out.println("reverse the list: "+l);
	}
}
class SumOfList extends Thread{
	private ArrayList<Integer> l;
	SumOfList(ArrayList<Integer> l){
		this.l = l;
	}
	public void run() {
		int sum=0;
		for(Integer i:l) {
			sum = sum+i;
		}
		System.out.println("Sum of all the Numbers: "+sum);
	}
}
public class Test {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		System.out.println("Enter Number: ");
		int n=sc.nextInt();
		for(int i=1;i<n;i++) {
			list.add(i);
		}
		System.out.println("Numbers below "+n+" are: ");
		System.out.println(list);
		Fibnocci f = new Fibnocci(n);
		f.start();
		ReverseList r = new ReverseList(list);
		r.start();
		SumOfList s = new SumOfList(list);
		s.start();
		sc.close();
	}
}
