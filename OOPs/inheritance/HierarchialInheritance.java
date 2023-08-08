package inheritance;
class Mother {
	void showMother() {
		System.out.println("This is Mother....");
	}
}
class Son extends Mother{
	void showSon() {
		System.out.println("This is son....");
	}
}
public class HierarchialInheritance extends Mother{
	void showDaughter() {
		System.out.println("This is Daughter....");
	}
	public static void main(String args[]) {
		HierarchialInheritance hierarchy = new HierarchialInheritance();
		hierarchy.showMother();
		hierarchy.showDaughter();
		//hierarchy.showSon();   Error
	}
}