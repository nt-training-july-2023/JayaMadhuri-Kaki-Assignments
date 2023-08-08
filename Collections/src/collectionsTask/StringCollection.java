package collectionsTask;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class StringCollection {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Set<String> list = new LinkedHashSet<>();
		System.out.println("enter 20 elements into the list");
		for(int i=0;i<20;i++) {
			list.add(sc.next());            //adding 20 elements into list duplicates will not be added
		}
		System.out.println("duplicate will not be added and insertion order will be preserved: "+list);
		System.out.println("deleting element 3 in the list: "+list.remove("3"));
		System.out.println("After deletion: "+list);
		System.out.println("checks if list is empty or not: "+list.isEmpty());
		System.out.println("size of list: "+list.size());
		System.out.println("getclass: "+list.getClass());
		list.clear();  //clear the list
		sc.close();
	}
}
