import java.util.Scanner;
public class Reverse {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to Reverse: ");
		int number = sc.nextInt();
		int reverseNumber = number;
		int r=0, n;
		while(number > 0) {
			n = number % 10;
			r = r * 10 + n;
			number = number / 10;
		}
		System.out.println("Reverse of the Number "+reverseNumber+" is "+r);
		sc.close();
	}
}