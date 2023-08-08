package inheritance;
class GrandParent {
	void showGrandParent() {
		System.out.println("This method is grand parents....");
	}
}
class Parent extends GrandParent{
	void showParent() {
		System.out.println("This method is parents....");
	}
}
public class MultilevelInheritance extends Parent{
	void showChildern() {
		System.out.println("This method is Childern....");
	}
	public static void main(String args[]) {
		MultilevelInheritance multilevel = new MultilevelInheritance();
		multilevel.showGrandParent();
		multilevel.showParent();
		multilevel.showChildern();
	}
}