package staticDemo;
public class StaticMethod {
	 static int x = 10,y = 10;
	 static void add(int x,int y) {
		 System.out.println("Sum is: "+(x+y));
	 }
	 public static void main(String args[]) {
		 add(x,y);
	 }
}