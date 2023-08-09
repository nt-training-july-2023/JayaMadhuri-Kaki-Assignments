package collectionClass;

import java.util.Iterator;
import java.util.Vector;

public class VectorDemo {
	public static void main(String args[]) {
		Vector<String> l = new Vector<>();
		l.add("10");
		l.add("20");
		l.add("30");
		System.out.println("Elements in list");
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		l.remove("30");
		System.out.println("list after removing 30: "+l);
		System.out.println("size of list: "+l.size());
	}
}