package staticDemo;
public class StaticBlock {
	static {
		System.out.println("This is static block executes only once");
	}
	StaticBlock() {
		System.out.println("This is a constructor");
	}
	public static void main(String args[]) {
		StaticBlock s = new StaticBlock();
		StaticBlock s1 = new StaticBlock();
	}
}