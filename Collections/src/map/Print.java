package map;

import java.util.HashMap;
import java.util.Map.Entry;

public class Print {
	public static void main(String args[]) {
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		m.put(1,"madhuri");
		m.put(2,"jaya");
		m.put(3,"jaya");
		System.out.println("Keys: ");
		for(Entry<Integer,String> e: m.entrySet()) {       //displaying keys in map
			System.out.println("key: "+e.getKey());
		}
		System.out.println("Values: ");
		for(Entry<Integer,String> e: m.entrySet()) { 
			System.out.println("value: "+e.getValue());     //displaying values in map
		}
	}
}
