package finalDemo;
public class FinalInitialization {
	final static int Number;
	final int j;
	FinalInitialization() {
		this.j = 10;
	}
	static {
		Number=10;
	}
	public static void main(String args[]) {
		FinalInitialization finalIni = new FinalInitialization();
		final int i;
		i = 10;
		System.out.println(Number);
		System.out.println(i);
		System.out.println(finalIni.j);
	}
}