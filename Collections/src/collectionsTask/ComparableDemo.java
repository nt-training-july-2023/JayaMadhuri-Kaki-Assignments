package collectionsTask;

import java.util.ArrayList;
import java.util.Collections;

public class ComparableDemo implements Comparable<ComparableDemo>{
	int id;
	String name;
	int age;
	public static void main(String args[]) {
		ArrayList<ComparableDemo> list = new ArrayList<>();
		list.add(new ComparableDemo(2,"madhuri",22));
		list.add(new ComparableDemo(1,"jaya",24));
		Collections.sort(list);							//sorting using comparable
		for(ComparableDemo i:list) {
			System.out.println(i.id+" ,"+i.name+" ,"+i.age);
		}
	}
	ComparableDemo(int id, String name, int age){       //parameter constructor
		this.id = id;
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(ComparableDemo o) {			//must implement method in comparable
		if(id==o.id) {
			return 0;									//check if id is equal to next id
		}
		else if(id>o.id) {
			return 1; 									//check if id is greater than next id
		}
		else {
			return -1;								    //check if id is less than next id
		}
	}
}
