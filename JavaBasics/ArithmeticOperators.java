import java.util.Scanner;
public class ArithmeticOperators{
	static int a;
    static int b;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a and b values: ");
		a = sc.nextInt();
		b = sc.nextInt();
		System.out.println("Addition "+(a+b));
		System.out.println("Multiplication "+a*b);
		System.out.println("Subtraction "+(a-b));
		System.out.println("divison "+a/b);
		System.out.println("remainder "+a%b);
		sc.close();
	}
}