package map;

import java.util.HashMap;
import java.util.Scanner;

public class ContainsDemo {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,String> m = new HashMap<Integer,String>();
		m.put(1,"madhuri");
		m.put(2,"jaya");
		m.put(3,"jaya");
		System.out.println("1.find a key \n2.find a value");
		int n;
		do {
		System.out.println("Enter a option: ");
		n = sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Enter key value to check if it exists: ");
			int key = sc.nextInt();
			boolean b = m.containsKey(key);
			if(b == true) {
				System.out.println(key+" is found");
			}else {
				System.out.println(key+" is not found");
			}
			break;
		case 2:
			System.out.println("Enter key value to check if it exists: ");
			String value = sc.next();
			boolean b1 = m.containsValue(value);
			if(b1 == true) {
				System.out.println(value+" is found");
			}else {
				System.out.println(value+" is not found");
			}
			break;
		default:
			System.out.println("Enter correct option");
			System.exit(0);
		}
		}while(n<=2);
		sc.close();
	}
	
}
