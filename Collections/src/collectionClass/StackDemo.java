package collectionClass;

import java.util.Iterator;
import java.util.Stack;

public class StackDemo {
	public static void main(String args[]) {
		Stack<String> l = new Stack<>();
		l.push("10");
		l.push("20");
		l.push("30");
		System.out.println("Elements in list");
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		l.pop();
		System.out.println("list after removing one element: "+l);
		System.out.println("size of list: "+l.size());
	}
}
