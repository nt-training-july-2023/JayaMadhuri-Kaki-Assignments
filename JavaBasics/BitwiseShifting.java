import java.util.Scanner;
public class BitwiseShifting {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a and b values: ");
		int a = sc.nextInt();
		int b = sc.nextInt();
		int i = a>>2;
		System.out.println("Right shift by 2: "+i);
		int j = a<<2;
		System.out.println("Left shift by 2: "+j);
		int k = a>>>2;
		System.out.println("unsigned right shift by 2 "+k);
		System.out.println(".......bitwise.......");
		System.out.println("bitwise Inclusive OR "+(a|b));
		System.out.println("bitwise AND "+(a&b));
		System.out.println("bitwise Exclusive OR "+(a^b));
		System.out.println("bitwise Unary Complement "+(~a));
		sc.close();
	}
}