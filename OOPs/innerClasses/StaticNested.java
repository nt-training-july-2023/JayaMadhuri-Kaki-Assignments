package innerClasses;
public class StaticNested {
	public static void demo() {
		System.out.println("method in main class");
	}
	static class Inner {
		public static void demoInner() {
			System.out.println("method in Inner class");
			demo();
		}
	}
	public static void main(String args[]) {
		StaticNested.Inner.demoInner();
	}
}