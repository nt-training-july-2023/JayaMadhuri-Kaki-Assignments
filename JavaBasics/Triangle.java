import java.util.Scanner;
public class Triangle{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Base of the Triangle: ");
		double a = sc.nextDouble();
		System.out.println("Enter Height of the Triangle: ");
		double b = sc.nextDouble();
		System.out.println("Area of the Triangle: "+(0.5*a*b));
		sc.close();
	}
}