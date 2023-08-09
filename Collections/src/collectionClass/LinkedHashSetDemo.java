package collectionClass;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
	public static void main(String args[]) {
		Set<String> l = new LinkedHashSet<>();
		l.add("20");
		l.add("10");
		l.add("20");
		System.out.println("Elements in list");
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		l.remove("10");
		System.out.println("list after removing 10: "+l);
		System.out.println("size of list: "+l.size());
	}
}
