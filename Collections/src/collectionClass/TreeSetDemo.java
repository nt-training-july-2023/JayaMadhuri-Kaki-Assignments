package collectionClass;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String args[]) {
		Set<String> l = new TreeSet<>();
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
