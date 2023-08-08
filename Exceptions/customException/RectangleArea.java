package customException;

import java.util.Scanner;

public class RectangleArea {
	public static void area(double length, double breadth) throws InvalidDimensionException{
		if(length<=0) {
			throw new InvalidDimensionException("Length can't be Negative or Zero...");
		}
		else if(breadth<=0) {
			throw new InvalidDimensionException("Breadth can't be Negative or Zero...");
		}
		else {
			System.out.println("Area of Rectangle: "+(length*breadth));
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter Length of Rectangle: ");
			double l = sc.nextDouble();
			System.out.println("Enter Breadth of Rectangle: ");
			double b = sc.nextDouble();
			area(l,b);
		}
		catch(InvalidDimensionException e) {
			System.out.println(e);
		}
		sc.close();
	}
}