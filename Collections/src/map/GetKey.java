package map;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class GetKey {
	public static void add(int a, String b, HashMap<Integer, String> m) {
		m.put(a,b);
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		m.put(1,"madhuri");
		m.put(2,"jaya");
		m.put(3,"jaya");
		System.out.println("1.Find employee id with name \n2.Add elements to map");
		int n;
		do {
		System.out.println("Enter a option: ");
		n = sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Enter Employee name to find employee id: ");
			String name = sc.next();
			for(Entry<Integer, String> e: m.entrySet()) {
				String s = e.getValue();
				if(s.equals(name)) {
					System.out.println("key is: "+e.getKey());
				}
			}
			break;
		case 2:
			System.out.println("Enter number of elements to add");
			int num = sc.nextInt();
			for(int i=0;i<num;i++) {
				System.out.println("Enter Employee id: ");
				int id = sc.nextInt();
				System.out.println("Enter Employee name: ");
				String empname = sc.next();
				add(id,empname,m);
			}
			System.out.println("Successfully inserted...");
			for(Entry<Integer,String> e:m.entrySet()) {
				System.out.println("Key: "+e.getKey());
				System.out.println("Value: "+e.getValue());
			}
			break;
		default:
			System.out.println("Enter correct option..... Exit");
			System.exit(0);
		}
		}while(n<=2);
		sc.close();
	}
}
