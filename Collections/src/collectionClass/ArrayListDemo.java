package collectionClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
	public static void main(String args[]) {
		List<String> l = new ArrayList<>();
		l.add("10");
		l.add("20");
		l.add("30");
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		l.remove("30");
		System.out.println("list after removing 30: "+l);
		System.out.println(l.size());
	}
}
