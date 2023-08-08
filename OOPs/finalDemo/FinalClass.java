package finalDemo;
final class FinalClass {
	static int x = 10, y = 10;
	public static void main(String args[]) {
		System.out.println(x+y);
		System.out.println("If a class is declared as a final then no other class can extend the class or inherited by other class"
				+ "it provide high security so elements do not change from external access");
	}
}