package abstraction;
abstract class Demo {
	void demo1() {
		System.out.println("This is demo1..method in class1");
	}
	abstract void demo2();
}
public class AbstractDemo extends Demo{
	void demo2() {
		System.out.println("This is demo2..method in class2");
	}
	public static void main(String args[]) {
		Demo abs = new AbstractDemo();
		abs.demo2();
		abs.demo1();
	}
}