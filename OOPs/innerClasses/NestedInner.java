package innerClasses;
public class NestedInner {
	public void methodClass1() {
		System.out.println("This is Main class method..");
	}
	class Inner {
		public void methodClassInner() {
			System.out.println("This is Inner class method..");
		}
	}
	public static void main(String args[]) {
		NestedInner n = new NestedInner();
		n.methodClass1();
		NestedInner.Inner i = n.new Inner();
		i.methodClassInner();
	}
}