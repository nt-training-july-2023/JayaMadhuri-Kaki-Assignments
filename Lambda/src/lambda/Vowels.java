package lambda;

import java.util.Scanner;

public class Vowels {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String to manipulate: ");
		String s1 = sc.nextLine();
		Alphabets a= (s)->{
			String s2 = s.replaceAll("[aeiouAeiou]", "#");
			System.out.println(s2);
		};
		a.convert(s1);
		sc.close();
	}
}
