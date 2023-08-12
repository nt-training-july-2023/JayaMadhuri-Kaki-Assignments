package currentThread;

class Sum extends Thread{
	int a,b;
	Sum(int a, int b){
		this.a = a;
		this.b = b;
	}
	public void run() {
		System.out.println("Sum is: "+(a+b)+"..."+Sum.currentThread());
	}
}
class Multiplication extends Thread{
	int a,b;
	Multiplication(int a, int b){
		this.a = a;
		this.b = b;
	}
	public void run() {
		System.out.println("Multiplication is: "+(a-b)+"..."+Multiplication.currentThread());
	}
}
class Divison extends Thread{
	int a,b;
	Divison(int a, int b){
		this.a = a;
		this.b = b;
	}
	public void run() {
		System.out.println("Divison is: "+(a/b)+"..."+Divison.currentThread());
	}
}
public class CurrentThreadDemo {
	public static void main(String args[]) {
		int a = 10, b = 5;
		Sum s = new Sum(a,b);
		s.start();
		Multiplication m = new Multiplication(a,b);
		m.start();
		Multiplication.currentThread();
		Divison d = new Divison(a,b);
		d.start();
		Divison.currentThread();
	}
}
