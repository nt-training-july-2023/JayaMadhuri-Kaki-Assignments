import java.util.Scanner;
public class QuadraticRoots {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a value: ");
		double a = sc.nextDouble();
		System.out.println("Enter b value: ");
		double b = sc.nextDouble();
		System.out.println("Enter c value: ");
		double c = sc.nextDouble();
		double root = (b*b)-4*a*c;
		if(root>0) {
			double root1 = (-b+Math.sqrt(root))/(2*a);
			double root2 = (-b-Math.sqrt(root))/(2*a);
			System.out.println(root1+" or "+root2+" is the root");
		}
		else if(root == 0) {
			double root1 = -b/(2*a);
			System.out.println(root1+" is the root");
		}
		else {
			double real = -b/(2*a);
			double imaginary = Math.sqrt(-root)/(2*a);
			System.out.println("the real part is "+real+" and imaginary part is "+imaginary);
		}
		sc.close();
	}
}