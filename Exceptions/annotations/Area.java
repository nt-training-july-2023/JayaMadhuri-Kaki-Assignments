package annotations;

import java.util.Scanner;

public class Area implements Circle,Rectangle,Triangle{
	@Override
	public double getCircleArea(double radius) {
		return Math.PI*(radius*radius);
	}
	@Override
	public double getTriangleArea(double base, double height) {
		return 0.5*base*height;
	}
	@Override
	public double getRectangleArea(double length, double breadth) {
		return length*breadth;
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Area area = new Area();
		System.out.println("Enter Number 1.Find Circle \n 2.Find Rectangle \n 3.Find Triangle: ");
		int Number = sc.nextInt();
		switch(Number) {
		case 1: System.out.println("Enter radius: ");
		double radius = sc.nextDouble();
		System.out.println("Area of circle: "+area.getCircleArea(radius));
		break;
		case 2: System.out.println("Enter length and breadth: ");
		double length = sc.nextDouble();
		double breadth = sc.nextDouble();
		System.out.println("Area of rectangle: "+area.getRectangleArea(length,breadth));
		break;
		case 3: System.out.println("Enter base and height: ");
		double base = sc.nextDouble();
		double height = sc.nextDouble();
		System.out.println("Area of triangle: "+area.getTriangleArea(base,height));
		break;
		default: System.out.println("Select Correct Option");
		}
		sc.close();
	}
}