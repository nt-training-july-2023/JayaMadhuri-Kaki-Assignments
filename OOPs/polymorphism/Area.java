package polymorphism;
public class Area {
	static double area(double radius) {
		return Math.PI*radius*radius;
	}
	static double area(double base,double height) {
		return 0.2*base*height;
	}
	public static void main(String args[]) {
		System.out.println("area of circle: "+area(2));
		System.out.println("area of triangle: "+area(2,3));
	}
}