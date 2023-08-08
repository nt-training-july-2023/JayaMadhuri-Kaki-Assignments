package customException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfString{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		List<String> s = new ArrayList<>();
//		s=null;
		s.add("Madhuri");
		s.add("Jaya");
		System.out.println("Enter index of the array: ");
		try {
		int index = sc.nextInt();
		System.out.println(s.get(index));
		}
		catch(NullPointerException e) {
			System.out.println("String List is Empty");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("String list index Out of bounds");
		}
		sc.close();
	}
}