import java.util.Scanner;
public class Factorial {
	public int fact(int a) {
		if(a == 0) {
			return 1;
		}
		else {
			return a * fact(a-1);
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Factorial f = new Factorial();
		System.out.println("Enter number to find factorial: ");
		int a = sc.nextInt();
		System.out.println("factorial of number "+a+" is: "+f.fact(a));
		sc.close();
	}
}