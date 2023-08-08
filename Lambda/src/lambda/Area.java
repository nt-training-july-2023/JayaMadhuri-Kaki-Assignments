package lambda;

import java.util.Scanner;

public class Area {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Rectangle\n2.Square\n3.Cylinder\n4.cube\n5.Sphere\n6.Circle");
		int option;
		do {
		System.out.println("Enter Option: ");
		option = sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("Enter length and breadth of rectangle: ");
			double length = sc.nextDouble();
			double breadth = sc.nextDouble();
			Shape rectangle = ()->{
				System.out.println("Area of Rectangle: "+(length*breadth));
			};
			rectangle.area();
			break;
		case 2:
			System.out.println("Enter side of a square: ");
			double a = sc.nextDouble();
			Shape square = ()->{
				System.out.println("Area of square: "+(a*a));
			};
			square.area();
			break;
		case 3:
			System.out.println("Enter radius and height of a cylinder: ");
			double r = sc.nextDouble();
			double h = sc.nextDouble();
			Shape cylinder = ()->{
				System.out.println("Area of cylinder: "+(2*Math.PI*r*(h+r)));
			};
			cylinder.area();
			break;
		case 4:
			System.out.println("Enter side of a cube: ");
			double side = sc.nextDouble();
			Shape cube = ()->{
				System.out.println("Area of cube: "+(6*side*side));
			};
			cube.area();
			break;
		case 5:
			System.out.println("Enter radius of a sphere: ");
			double radius = sc.nextDouble();
			Shape sphere = ()->{
				System.out.println("Area of sphere: "+(4*Math.PI*radius));
			};
			sphere.area();
			break;
		case 6:
			System.out.println("Enter radius of a circle: ");
			double rad = sc.nextDouble();
			Shape circle = ()->{
				System.out.println("Area of circle: "+(Math.PI*rad*rad));
			};
			circle.area();
			break;
		default:
			System.out.println("Enter Correct Option");
		}
		}while(option<=6);
		System.out.println("Exit.........");
		sc.close();
	}
}
