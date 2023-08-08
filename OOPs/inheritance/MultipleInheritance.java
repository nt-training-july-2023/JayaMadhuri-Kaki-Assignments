package inheritance;
interface Parent1 {
	 void demoParent1();
}
interface Parent2 {
	 void demoParent2();
}
public class MultipleInheritance implements Parent1,Parent2{
	public void demoParent1() {
		System.out.println("Parent 1");
	}
	public void demoParent2() {
		System.out.println("Parent 2");
	}
	public static void main(String args[]) {
		MultipleInheritance multiple = new MultipleInheritance();
		multiple.demoParent1();
		multiple.demoParent2();
	}
}