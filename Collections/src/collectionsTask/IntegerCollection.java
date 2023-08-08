package collectionsTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IntegerCollection {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		List<Integer> list = new ArrayList<>();
		System.out.println("enter 20 elements into the list");
		for(int i=0;i<20;i++) {
			list.add(sc.nextInt());            //adding 20 elements into list
		}
		System.out.println(list);
		System.out.println("displaying list in reverse order without a loop");
		Collections.reverse(list);            //reverse a list without loop
		System.out.println(list);
		System.out.println("updating elements with 5 when element greater than 50");
		for(int i=0;i<20;i++) {
			if(list.get(i)>50){
				list.remove(i);
				list.add(i,5);                //updating elements with 5 when element greater than 50
			}
		}
		System.out.println(list);
		System.out.println("Elements less than 60");
		for(int i=0;i<20;i++) {
			if(list.get(i)<60){
				System.out.println(list.get(i));               //displaying elements less than 60
			}
		} 
		sc.close();
	}
}
