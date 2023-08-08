import java.util.Scanner;
public class Palindrome {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number to Check palindrome or not");
		int number = sc.nextInt();
		int num = number, n, rem = 0;
		while(number>0) {
			n = number % 10;
			rem = rem * 10 + n;
			number = number / 10;
		}
		if(num == rem) {
			System.out.println(num+" is a palindrome");
		}
		else {
			System.out.println(num+" is not a palindrome");
		}
		sc.close();
	}
}