package innerClasses;
interface Example {
	int x=10; 
	void getX();
}
public class Anonymous {
	public static void main(String args[]) {
		Example e = new Example() {
			public void getX() {
				System.out.println("x value is: "+x);
			}
		};	
		e.getX();
	}
}