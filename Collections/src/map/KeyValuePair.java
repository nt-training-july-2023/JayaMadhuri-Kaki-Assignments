package map;

import java.util.HashMap;
import java.util.Scanner;

public class KeyValuePair {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		m.put(1,"madhuri");
		m.put(2,"jaya");
		m.put(3,"jaya");
		System.out.println("Enter key: ");
		int k = sc.nextInt();
		System.out.println("Enter value: ");
		String name = sc.next();
		if(m.containsKey(k)== true) {
			if(m.get(k).equals(name)) {
				m.remove(k);
				System.out.println("key-value pair is removed from the map");
			}
			else {
				System.out.println("key value pair does found");
			}
		}else {
			System.out.println("key value pair does found");
		}
		sc.close();
	}
}
