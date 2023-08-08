package polymorphism;
class Hello {
	public void greet() {
		System.out.println("Hello This is a method in Hello class...");
	}
}
class Hii extends Hello {
	@Override
	public void greet() {
		System.out.println("Hii This is a method in Hii class....");
	}
}
public class OverRiding {
	public static void main(String args[]) {
		Hello hello = new Hello();
		Hello hii = new Hii();
		hello.greet();
		hii.greet();
	}
}
