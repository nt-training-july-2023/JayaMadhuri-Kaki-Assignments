package collectionMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class Methods {
	public static void main(String args[]) {
		Collection<String> l = new ArrayList<>();       //creating a list
		l.add("jaya");  //inserting elements to list
		l.add("madhuri");
		l.add("madhu");
		System.out.println("elements in list1: "+l);
		Collection<String> l2 = new ArrayList<>();
		l2.addAll(l);      //adding all the elements in list1 to list2
		System.out.println("elements in list2: "+l2);
		l.remove("madhuri");         //removing an element
		System.out.println("After removing a element:"+l);
		l2.removeAll(l);              //removing all the elements in list2 that are present in list1 
		System.out.println("After removing all the common elements that are in list1:"+l2);
		Iterator<String> it = l.iterator();               //using iterator method for iterating the list
		System.out.println("Iterating list1 using iterator method:");  
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		l.clear();     //removing all the elements from list
		System.out.println("Elements in the list1 after clearing list using clear method: "+l);
		System.out.println("checks if madhu exist in list2: "+l2.contains("madhu"));   //checking if element exist in the list
		System.out.println("list2 elements: "+l2);
		Collection<String> l3 = new ArrayList<>();
		l3.add("madhuri");
		System.out.println("checks if list2 exist in list3: "+l3.containsAll(l2));     //check if list2 items exist in list3
		System.out.println("check if list1 is empty: "+l.isEmpty());   //check if list is empty
		System.out.println("hashcode for list2: "+l2.hashCode());      //converting list2 to hashcode
		System.out.println("check if two lists are equal: "+l2.equals(l3));  //check if two lists are equal
		System.out.println("parallel stream for list2: "+l2.parallelStream());     //parallel stream for list2
		l3.add("jaya");
		System.out.println("elements of list3: "+l3);
		System.out.println("remove elements that are not present in l2: "+l3.retainAll(l2)); //remove elements in list3 that are not present l2
		System.out.println("elements of list3: "+l3);
		l3.add("kaki");
		System.out.println("size of list3: "+l2.size());   //size of list
		System.out.println(l3.toString());        //converting list to string
	}
}
