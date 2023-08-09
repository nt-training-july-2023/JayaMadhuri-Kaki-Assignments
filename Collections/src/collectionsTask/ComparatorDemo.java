package collectionsTask;

import java.util.ArrayList;
import java.util.Collections;

public class ComparatorDemo{
	public static void main(String args[]) {
		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee(2,"madhuri",23));
		list.add(new Employee(1,"jaya",24));
		Collections.sort(list, new IdComparator());
		for(Employee i : list) {
			System.out.println(i.id+" , "+i.name+" , "+i.age);
		}
	}
}
