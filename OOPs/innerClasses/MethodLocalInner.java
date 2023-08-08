package innerClasses;
public class MethodLocalInner {
	static void demo() {
		System.out.println("class inside the method..");
		class MethodInner {
			public void demoInner() {
				System.out.println("Inner class");
			}
		}
		MethodInner inner = new MethodInner();
		inner.demoInner();
	}
	public static void main(String args[]) {
		demo();
	}
}