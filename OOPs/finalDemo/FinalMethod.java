package finalDemo;
class Example {
	final void method() {
		System.out.println("final method");
	}
}
public class FinalMethod extends Example{
	public static void main(String args[]) {
		FinalMethod e = new FinalMethod();
		e.method();
		System.out.println("final method can be inherited but override is not possible"
				+" by declaring class as final we can't use the class or its members but when we declare final methods we can inherit them");
	}
}