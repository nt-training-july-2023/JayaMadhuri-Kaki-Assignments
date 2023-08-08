package staticDemo;
public class StaticClass {
	private static int x = 10;
	static class Inner {
		public void show() {
			System.out.println("This is static Inner class method");
		}
	}
	public static void main() {
		StaticClass.Inner s = new StaticClass.Inner();
		s.show();
	}
}