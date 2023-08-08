package inheritance;
class InheritanceSingle {
	 void inheritance() {
		System.out.println("Inheritance....");
	}
}
public class SingleInheritance extends InheritanceSingle{
	 void singleInheritance() {
		System.out.println("Single Inheritance");
	}
	public static void main(String args[]) {
		SingleInheritance single = new SingleInheritance();
		single.inheritance();
		single.singleInheritance();
	}
}