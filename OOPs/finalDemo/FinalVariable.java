package finalDemo;
public class FinalVariable{
	public static void main(String args[]) {
		final int x = 10;
		System.out.println(x); //10
		System.out.println("if we change x value during executing then compile time error will come "
				+ "final varibles should not be changed during execution");
	}
}