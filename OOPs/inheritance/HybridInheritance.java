package inheritance;
public class HybridInheritance extends Son {
	void showChildren() {
		System.out.println("This is children..");
	}
	public static void main(String args[]) {
		HybridInheritance hybrid = new HybridInheritance();
		hybrid.showMother();
		hybrid.showSon();
		hybrid.showChildren();
	}
}