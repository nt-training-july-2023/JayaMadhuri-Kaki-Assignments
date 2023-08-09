package map;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Threshold {
	public static void print(HashMap<Integer,String> m,int i) {
		System.out.println("displaying elements of size: "+m.size());
		for(Entry<Integer,String> e: m.entrySet()) {       //displaying map
			System.out.println("key: "+e.getKey());
			System.out.println("value: "+e.getValue());
		}
		int size = m.size();
		if(size >= i) {
			m.clear();
			System.out.println("cleared successfully");
		}else {
			System.out.println("not cleared because size of threshold is large");
		}
	}
	public static void add(int a, String b, HashMap<Integer, String> m) {
		m.put(a,b);
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,String> map = new HashMap<>();
		map.put(1,"madhuri");
		map.put(2,"jaya");
		map.put(3,"kaki");
		int n;
		do {
		System.out.println(".....Options.....\n1. to display map and clear if it is greater than threshold value \n2.insert elements to map");
		System.out.println("Enter correct option: ");
		n = sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Enter threshold value: ");
			int threshold = sc.nextInt();
			print(map,threshold);
			break;
		case 2: 
			System.out.println("Enter number of elements to add");
			int num = sc.nextInt();
			for(int i=0;i<num;i++) {
				System.out.println("Enter Employee id: ");
				int id = sc.nextInt();
				System.out.println("Enter Employee name: ");
				String empname = sc.next();
				add(id,empname,map);
			}
			System.out.println("Successfully inserted...");
			for(Entry<Integer,String> e:map.entrySet()) {
				System.out.println("Key: "+e.getKey());
				System.out.println("Value: "+e.getValue());
			}
			break;
		}
		}while(n<=2);
		sc.close();
	}
}
