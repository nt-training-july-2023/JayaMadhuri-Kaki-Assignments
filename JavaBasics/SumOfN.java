import java.util.Scanner;
public class SumOfN {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter N value to find Sum Of first N numbers: ");
		int n = sc.nextInt();
		int sum = n*(n+1)/2;
		System.out.println(sum);
		sc.close();
	}
}