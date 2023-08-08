import java.util.Scanner;
public class IncDec {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to Pre and Post Increment and Decrement: ");
		int a = sc.nextInt();
		System.out.println("Post Increment "+(a++));
		System.out.println("Post Decrement "+(a--));
		System.out.println("Pre Increment "+(++a));
		System.out.println("Pre Decrement "+(--a));
		sc.close();
	}
}